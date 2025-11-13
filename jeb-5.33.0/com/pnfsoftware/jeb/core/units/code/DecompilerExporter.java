package com.pnfsoftware.jeb.core.units.code;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pnfsoftware.jeb.client.AbstractContext;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.base.IProgressCallback;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class DecompilerExporter {
   protected static final ILogger logger = GlobalLog.getLogger(DecompilerExporter.class);
   protected IDecompilerUnit decomp;
   protected File outputFolder;
   protected String headerString;
   protected Pattern pattern;
   protected long methodTimeoutMs;
   protected long totalTimeoutMs;
   protected IProgressCallback callback;
   protected boolean discardDecompiledItemsAfterProcessing;
   protected boolean usesCustomDecompWriter;
   protected AtomicInteger writeCount;
   protected AtomicInteger writeErrorCount;
   protected Map errormap;

   public DecompilerExporter(IDecompilerUnit decomp) {
      if (decomp == null) {
         throw new IllegalArgumentException("Provide a decompiler");
      } else {
         this.decomp = decomp;
         this.setHeaderString("Decompiled by JEB v" + AbstractContext.app_ver);
      }
   }

   protected void reset() {
      this.writeCount = new AtomicInteger();
      this.writeErrorCount = new AtomicInteger();
      this.errormap = null;
   }

   public IDecompilerUnit getDecompiler() {
      return this.decomp;
   }

   public void setOutputFolder(File file) throws IOException {
      this.setOutputFolder(file, null);
   }

   public void setOutputFolder(File outputFolder, Boolean b) throws IOException {
      if (outputFolder == null) {
         this.outputFolder = null;
      } else {
         if (outputFolder.exists()) {
            if (outputFolder.isFile()) {
               throw new IOException("The specified output exists already and is not a folder");
            }
         } else if (!IO.createDirectory(outputFolder)) {
            throw new IOException("Cannot create output directory");
         }

         this.outputFolder = outputFolder;
      }

      if (b != null) {
         this.setDiscardDecompiledItemsAfterProcessing(b);
      }
   }

   public File getOutputFolder() {
      return this.outputFolder;
   }

   public void setHeaderString(String s) {
      if (s != null && !Strings.isBlank(s)) {
         this.headerString = "// " + Strings.replaceNewLines(s, " / ") + Strings.LINESEP + Strings.LINESEP;
      } else {
         this.headerString = null;
      }
   }

   public String getHeaderString() {
      return this.headerString;
   }

   public void setSignaturePattern(Pattern pattern) {
      this.pattern = pattern;
   }

   public Pattern getSignaturePattern() {
      return this.pattern;
   }

   public void setMethodTimeout(long methodTimeoutMs) {
      this.methodTimeoutMs = methodTimeoutMs;
   }

   public long getMethodTimeout() {
      return this.methodTimeoutMs;
   }

   public void setTotalTimeout(long totalTimeoutMs) {
      this.totalTimeoutMs = totalTimeoutMs;
   }

   public long getTotalTimeout() {
      return this.totalTimeoutMs;
   }

   public void setCallback(IProgressCallback callback) {
      this.callback = callback;
   }

   public IProgressCallback getCallback() {
      return this.callback;
   }

   public void setDiscardDecompiledItemsAfterProcessing(boolean discardDecompiledItemsAfterProcessing) {
      this.discardDecompiledItemsAfterProcessing = discardDecompiledItemsAfterProcessing;
   }

   public boolean isKeepDecompiledItemsAfterProcessing() {
      return this.discardDecompiledItemsAfterProcessing;
   }

   public final boolean export() {
      return this.process();
   }

   public abstract boolean process();

   protected void customizeOptions(boolean b, DecompilationOptions.Builder builder) {
   }

   public boolean processTopLevelClasses() {
      this.reset();
      long currentTimeMillis = System.currentTimeMillis();
      List list = (List<? super String>)CodeUtil.collectClasses(this.decomp.getCodeUnit(), true, true, this.pattern, false)
         .stream()
         .map(codeClass -> codeClass.getAddress())
         .collect(Collectors.toList());
      DecompilationOptions.Builder instance = DecompilationOptions.Builder.newInstance();
      if (this.methodTimeoutMs > 0L) {
         instance.maxTimePerMethod(this.methodTimeoutMs);
      }

      if (this.totalTimeoutMs > 0L) {
         instance.maxTimeTotal(this.totalTimeoutMs);
      }

      this.customizeOptions(true, instance);
      DecompilationContext decompilationContext = new DecompilationContext(instance.build());
      decompilationContext.setCallback(this.callback);
      boolean decompileClasses = this.decomp.decompileClasses(list, decompilationContext);
      long currentTimeMillis2 = System.currentTimeMillis();
      if (this.outputFolder != null && !this.usesCustomDecompWriter) {
         long max = Long.MAX_VALUE;
         if (this.getTotalTimeout() != 0L) {
            max = Math.max(10000L, this.getTotalTimeout() - (currentTimeMillis2 - currentTimeMillis));
         }

         ExecutorService fixedThreadPool = Executors.newFixedThreadPool(32, new ThreadFactoryBuilder().setDaemon(true).build());
         final Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            fixedThreadPool.submit(new Runnable() {
               final String val$addr = (String)iterator.next();

               @Override
               public void run() {
                  DecompilerExporter.this.writeClassDecompilation(this.val$addr);
               }
            });
         }

         try {
            fixedThreadPool.shutdown();
            if (!fixedThreadPool.awaitTermination(max, TimeUnit.MILLISECONDS)) {
               fixedThreadPool.shutdownNow();
            }
         } catch (InterruptedException var14) {
            fixedThreadPool.shutdownNow();
            logger.warning(S.L("Interrupted..."));
         }

         logger.debug(
            "%d files written in %d ms (%d errors)", this.writeCount.get(), System.currentTimeMillis() - currentTimeMillis2, this.writeErrorCount.get()
         );
      }

      if (this.writeErrorCount.get() > 0) {
         decompileClasses = false;
      }

      this.errormap = new HashMap(decompilationContext.getErrorMap());
      if (this.discardDecompiledItemsAfterProcessing) {
         this.decomp.runGarbageCollection();
      }

      return decompileClasses;
   }

   public boolean processMethods() {
      this.reset();
      long currentTimeMillis = System.currentTimeMillis();
      List list = (List<? super String>)CodeUtil.collectMethods(this.decomp.getCodeUnit(), true, false, this.pattern, false)
         .stream()
         .map(codeMethod -> codeMethod.getAddress())
         .collect(Collectors.toList());
      DecompilationOptions.Builder instance = DecompilationOptions.Builder.newInstance();
      if (this.methodTimeoutMs > 0L) {
         instance.maxTimePerMethod(this.methodTimeoutMs);
      }

      if (this.totalTimeoutMs > 0L) {
         instance.maxTimeTotal(this.totalTimeoutMs);
      }

      this.customizeOptions(false, instance);
      DecompilationContext decompilationContext = new DecompilationContext(instance.build());
      decompilationContext.setCallback(this.callback);
      boolean decompileMethods = this.decomp.decompileMethods(list, decompilationContext);
      long currentTimeMillis2 = System.currentTimeMillis();
      if (this.outputFolder != null && !this.usesCustomDecompWriter) {
         long max = Long.MAX_VALUE;
         if (this.getTotalTimeout() != 0L) {
            max = Math.max(10000L, this.getTotalTimeout() - (currentTimeMillis2 - currentTimeMillis));
         }

         ExecutorService fixedThreadPool = Executors.newFixedThreadPool(32, new ThreadFactoryBuilder().setDaemon(true).build());
         final Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            fixedThreadPool.submit(new Runnable() {
               final String val$addr = (String)iterator.next();

               @Override
               public void run() {
                  DecompilerExporter.this.writeMethodDecompilation(this.val$addr);
               }
            });
         }

         try {
            fixedThreadPool.shutdown();
            if (!fixedThreadPool.awaitTermination(max, TimeUnit.MILLISECONDS)) {
               fixedThreadPool.shutdownNow();
            }
         } catch (InterruptedException var14) {
            fixedThreadPool.shutdownNow();
            logger.warning(S.L("Interrupted..."));
         }

         logger.debug(
            "%d method files written in %d ms (%d errors)", this.writeCount.get(), System.currentTimeMillis() - currentTimeMillis2, this.writeErrorCount.get()
         );
      }

      if (this.writeErrorCount.get() > 0) {
         decompileMethods = false;
      }

      this.errormap = new HashMap(decompilationContext.getErrorMap());
      if (this.discardDecompiledItemsAfterProcessing) {
         this.decomp.runGarbageCollection();
      }

      return decompileMethods;
   }

   protected String generateMethodFileName(String s) {
      return s + this.decomp.getOutputType().getPreferredExtension();
   }

   protected void writeMethodDecompilation(String s) {
      if (this.outputFolder == null) {
         throw new IllegalStateException();
      } else {
         String decompiledMethodText = this.decomp.getDecompiledMethodText(s);
         if (decompiledMethodText != null) {
            if (this.headerString != null) {
               decompiledMethodText = this.headerString + decompiledMethodText;
            }

            File file = new File(this.outputFolder, this.generateMethodFileName(s));

            try {
               IO.writeFile(file, Strings.encodeLocal(decompiledMethodText), true);
               this.writeCount.incrementAndGet();
            } catch (IOException var5) {
               logger.error(S.L("Cannot write output file: %s. Error: %s"), file, var5.getMessage());
               this.writeErrorCount.incrementAndGet();
            }
         }
      }
   }

   protected String generateClassFileName(String s) {
      return s + this.decomp.getOutputType().getPreferredExtension();
   }

   protected void writeClassDecompilation(String s) {
      if (this.outputFolder == null) {
         throw new IllegalStateException();
      } else {
         ISourceUnit decompiledUnit = this.decomp.getDecompiledUnit(s);
         String s2;
         if (decompiledUnit != null) {
            s2 = decompiledUnit.getSource();
         } else {
            s2 = this.decomp.getDecompiledClassText(s);
         }

         if (s2 != null) {
            if (this.headerString != null) {
               s2 = this.headerString + s2;
            }

            File file = new File(this.outputFolder, this.generateClassFileName(s));

            try {
               IO.writeFile(file, Strings.encodeLocal(s2), true);
               this.writeCount.incrementAndGet();
            } catch (IOException var6) {
               logger.error(S.L("Cannot write output file: %s. Error: %s"), file, var6.getMessage());
               this.writeErrorCount.incrementAndGet();
            }
         }
      }
   }

   public Integer getGeneratedFileCount() {
      return this.writeCount.get();
   }

   public Map getErrors() {
      if (this.errormap == null) {
         throw new IllegalStateException();
      } else {
         return this.errormap;
      }
   }
}
