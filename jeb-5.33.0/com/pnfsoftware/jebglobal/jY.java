package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IEnginesPlugin;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.core.ScriptMetadata;
import com.pnfsoftware.jeb.core.ScriptMetadataParser;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJOptimizer;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.DirectoryEnumerator;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.Closeable;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class jY {
   protected static final ILogger pC = GlobalLog.getLogger(jY.class);
   protected File A;
   protected File kS;
   protected Map wS = new LinkedHashMap();
   private static final IdentityHashMap UT = new IdentityHashMap();

   public jY(File var1) {
      if (var1 != null && var1.isDirectory()) {
         this.A = var1;
         this.kS = new File(var1, "scripts");
         if (!this.kS.exists()) {
            this.kS.mkdir();
         }

         if (!this.kS.isDirectory()) {
            throw new IllegalArgumentException("Expected a folder: " + this.kS);
         }
      } else {
         throw new IllegalArgumentException("Expected a folder: " + var1);
      }
   }

   protected final ClassLoader pC() {
      URL[] var1;
      try {
         var1 = new URL[]{this.kS.toURI().toURL()};
      } catch (MalformedURLException var3) {
         throw new RuntimeException(var3);
      }

      return new URLClassLoader(var1);
   }

   public Collection A() {
      return Collections.unmodifiableCollection(this.wS.values());
   }

   private void pC(QM var1) {
      if (var1.E != null) {
         try {
            var1.E.dispose();
         } catch (Exception var4) {
         }

         var1.E = null;
      }

      if (var1.UT != null) {
         ClassLoader var2 = var1.UT.getClassLoader();
         if (var2 instanceof Closeable) {
            try {
               ((Closeable)var2).close();
            } catch (Exception var3) {
            }
         }

         var1.UT = null;
      }
   }

   public synchronized List pC(Class var1, boolean var2) {
      if (var2) {
         for (String var4 : new HashSet(this.wS.keySet())) {
            if (!new File(var4).exists()) {
               QM var5 = (QM)this.wS.get(var4);
               this.pC(var5);
               this.wS.remove(var4);
            }
         }

         Iterator var11 = new DirectoryEnumerator(this.kS, this.kS(), true).list().iterator();

         label57:
         while (true) {
            Class var7;
            IPlugin var8;
            File var13;
            String var15;
            while (true) {
               if (!var11.hasNext()) {
                  break label57;
               }

               var13 = (File)var11.next();
               var15 = var13.getAbsolutePath();
               QM var6 = (QM)this.wS.get(var15);
               if (var6 == null || var6.pC()) {
                  try {
                     if (var6 != null) {
                        this.pC(var6);
                     }

                     if (!this.pC(var13, var1)) {
                        var7 = this.pC(var13);
                        var8 = this.pC(var7);
                        break;
                     }
                  } catch (Throwable var10) {
                     pC.catching(var10, Strings.ff(S.L("An error occurred when attempting to load script plugin: %s"), var15));
                  }
               }
            }

            QM var17 = new QM(this, var7, var8, true, var13);
            this.wS.put(var15, var17);
         }
      }

      ArrayList var12 = new ArrayList();

      for (IPluginFileEntry var16 : this.A()) {
         if (var16.isValidPlugin()) {
            IPlugin var18 = var16.getPluginObject();
            if (var1.isAssignableFrom(var18.getClass())) {
               var12.add(var16);
            }
         }
      }

      return var12;
   }

   public List A(Class var1, boolean var2) {
      ArrayList var3 = new ArrayList();

      for (IPluginFileEntry var5 : this.pC(var1, var2)) {
         var3.add(var5.getPluginObject());
      }

      return var3;
   }

   protected abstract String kS();

   protected abstract String wS();

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   protected boolean pC(File var1, Class var2) {
      String var3;
      try {
         var3 = (String)UT.get(var2);
         if (var3 == null) {
            return false;
         }
      } catch (Exception var13) {
         return false;
      }

      try {
         ScriptMetadata var4 = ScriptMetadataParser.extract(var1, this.wS());
         if (!Strings.isBlank(var4.getType()) && !var4.getType().equals(var3)) {
            return true;
         }
      } catch (Exception var12) {
         return false;
      }

      Iterator var5;
      try {
         var5 = IO.readLines(var1).iterator();
      } catch (Exception var9) {
         return false;
      }

      while (true) {
         String var6;
         try {
            if (!var5.hasNext()) {
               break;
            }

            var6 = (String)var5.next();
            if (var2 != IEOptimizer.class && var6.contains("com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt") && var6.contains("EOptimizer")) {
               return true;
            }
         } catch (Exception var7) {
            break;
         }

         try {
            if (var2 != ICOptimizer.class && var6.contains("com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt") && var6.contains("COptimizer")) {
               return true;
            }
         } catch (Exception var11) {
            break;
         }

         try {
            if (var2 != IDOptimizer.class && var6.contains("com.pnfsoftware.jeb.core.units.code.android.ir") && var6.contains("DOptimizer")) {
               return true;
            }
         } catch (Exception var8) {
            break;
         }

         try {
            if (var2 != IJOptimizer.class && var6.contains("com.pnfsoftware.jeb.core.units.code.java") && var6.contains("JOptimizer")) {
               return true;
            }
         } catch (Exception var10) {
            break;
         }
      }

      return false;
   }

   protected abstract Class pC(File var1) throws Exception;

   protected IPlugin pC(Class var1) throws Exception {
      return (IPlugin)var1.getDeclaredConstructor().newInstance();
   }

   static {
      UT.put(IEnginesPlugin.class, "engines");
      UT.put(IEOptimizer.class, "gendec-ir");
      UT.put(ICOptimizer.class, "gendec-ast");
      UT.put(IDOptimizer.class, "dexdec-ir");
      UT.put(IJOptimizer.class, "dexdec-ast");
   }
}
