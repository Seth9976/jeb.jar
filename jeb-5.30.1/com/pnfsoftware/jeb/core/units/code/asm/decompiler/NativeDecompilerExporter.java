package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.CodeUtil;
import com.pnfsoftware.jeb.core.units.code.DecompilationContext;
import com.pnfsoftware.jeb.core.units.code.DecompilationOptions;
import com.pnfsoftware.jeb.core.units.code.DecompilerExporter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class NativeDecompilerExporter extends DecompilerExporter {
   private INativeCodeUnit pbcu;

   public NativeDecompilerExporter(INativeDecompilerUnit decomp) {
      super(decomp);
      this.pbcu = decomp.getCodeUnit();
   }

   @Override
   protected String generateMethodFileName(String s) {
      return IO.sanitizePath(s.replace("::", "/") + this.decomp.getOutputType().getPreferredExtension(), false, false);
   }

   @Override
   protected String generateClassFileName(String s) {
      return this.generateMethodFileName(s);
   }

   @Override
   public boolean process() {
      this.reset();
      long currentTimeMillis = System.currentTimeMillis();
      DecompilationOptions.Builder instance = DecompilationOptions.Builder.newInstance();
      if (this.methodTimeoutMs > 0L) {
         instance.maxTimePerMethod(this.methodTimeoutMs);
      }

      if (this.totalTimeoutMs > 0L) {
         instance.maxTimeTotal(this.totalTimeoutMs);
      }

      DecompilationContext decompilationContext = new DecompilationContext(instance.build());
      decompilationContext.setCallback(this.callback);
      boolean b = true;
      List list = (List<? super String>)CodeUtil.collectMethods(this.pbcu, true, true, this.pattern, false)
         .stream()
         .map(codeMethod -> codeMethod.getAddress())
         .collect(Collectors.toList());
      if (!this.decomp.decompileMethods(list, decompilationContext)) {
         b = false;
      }

      List list2 = (List<? super String>)CodeUtil.collectClasses(this.pbcu, true, true, this.pattern, false)
         .stream()
         .map(codeClass -> codeClass.getAddress())
         .collect(Collectors.toList());
      if (!this.decomp.decompileClasses(list2, decompilationContext)) {
         b = false;
      }

      long currentTimeMillis2 = System.currentTimeMillis();
      if (this.outputFolder != null) {
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
                  String decompiledMethodText = NativeDecompilerExporter.this.decomp.getDecompiledMethodText(this.val$addr);
                  if (decompiledMethodText != null) {
                     if (NativeDecompilerExporter.this.headerString != null) {
                        decompiledMethodText = NativeDecompilerExporter.this.headerString + decompiledMethodText;
                     }

                     File file = new File(NativeDecompilerExporter.this.outputFolder, NativeDecompilerExporter.this.generateMethodFileName(this.val$addr));

                     try {
                        IO.writeFile(file, Strings.encodeLocal(decompiledMethodText), true);
                        NativeDecompilerExporter.this.writeCount.incrementAndGet();
                     } catch (IOException var4) {
                        NativeDecompilerExporter.logger.error(S.L("Cannot write output file: %s. Error: %s"), file, var4.getMessage());
                        NativeDecompilerExporter.this.writeErrorCount.incrementAndGet();
                     }
                  }
               }
            });
         }

         Iterator iterator2 = list2.iterator();

         while (iterator2.hasNext()) {
            fixedThreadPool.submit(new Runnable() {
               final String val$addr = (String)iterator.next();

               @Override
               public void run() {
                  String decompiledClassText = NativeDecompilerExporter.this.decomp.getDecompiledClassText(this.val$addr);
                  if (decompiledClassText != null) {
                     if (NativeDecompilerExporter.this.headerString != null) {
                        decompiledClassText = NativeDecompilerExporter.this.headerString + decompiledClassText;
                     }

                     File file = new File(NativeDecompilerExporter.this.outputFolder, NativeDecompilerExporter.this.generateClassFileName(this.val$addr));

                     try {
                        IO.writeFile(file, Strings.encodeLocal(decompiledClassText), true);
                        NativeDecompilerExporter.this.writeCount.incrementAndGet();
                     } catch (IOException var4) {
                        NativeDecompilerExporter.logger.error(S.L("Cannot write output file: %s. Error: %s"), file, var4.getMessage());
                        NativeDecompilerExporter.this.writeErrorCount.incrementAndGet();
                     }
                  }
               }
            });
         }

         try {
            fixedThreadPool.shutdown();
            if (!fixedThreadPool.awaitTermination(max, TimeUnit.MILLISECONDS)) {
               fixedThreadPool.shutdownNow();
            }
         } catch (InterruptedException var16) {
            fixedThreadPool.shutdownNow();
            logger.warning(S.L("Interrupted..."));
         }

         logger.debug(
            "%d files written in %d ms (%d errors)", this.writeCount.get(), System.currentTimeMillis() - currentTimeMillis2, this.writeErrorCount.get()
         );
      }

      if (this.writeErrorCount.get() > 0) {
         b = false;
      }

      this.errormap = new HashMap(decompilationContext.getErrorMap());
      if (this.discardDecompiledItemsAfterProcessing) {
         this.decomp.runGarbageCollection();
      }

      return b;
   }
}
