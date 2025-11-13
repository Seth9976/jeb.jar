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

public abstract class Nz {
   protected static final ILogger q = GlobalLog.getLogger(Nz.class);
   protected File RF;
   protected File xK;
   protected Map Dw = new LinkedHashMap();
   private static final IdentityHashMap Uv = new IdentityHashMap();

   public Nz(File var1) {
      if (var1 != null && var1.isDirectory()) {
         this.RF = var1;
         this.xK = new File(var1, "scripts");
         if (!this.xK.exists()) {
            this.xK.mkdir();
         }

         if (!this.xK.isDirectory()) {
            throw new IllegalArgumentException("Expected a folder: " + this.xK);
         }
      } else {
         throw new IllegalArgumentException("Expected a folder: " + var1);
      }
   }

   protected final ClassLoader q() {
      URL[] var1;
      try {
         var1 = new URL[]{this.xK.toURI().toURL()};
      } catch (MalformedURLException var3) {
         throw new RuntimeException(var3);
      }

      return new URLClassLoader(var1);
   }

   public Collection RF() {
      return Collections.unmodifiableCollection(this.Dw.values());
   }

   private void q(jx var1) {
      if (var1.oW != null) {
         try {
            var1.oW.dispose();
         } catch (Exception var4) {
         }

         var1.oW = null;
      }

      if (var1.Uv != null) {
         ClassLoader var2 = var1.Uv.getClassLoader();
         if (var2 instanceof Closeable) {
            try {
               ((Closeable)var2).close();
            } catch (Exception var3) {
            }
         }

         var1.Uv = null;
      }
   }

   public synchronized List q(Class var1, boolean var2) {
      if (var2) {
         for (String var4 : new HashSet(this.Dw.keySet())) {
            if (!new File(var4).exists()) {
               jx var5 = (jx)this.Dw.get(var4);
               this.q(var5);
               this.Dw.remove(var4);
            }
         }

         Iterator var11 = new DirectoryEnumerator(this.xK, this.xK(), true).list().iterator();

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
               jx var6 = (jx)this.Dw.get(var15);
               if (var6 == null || var6.RF()) {
                  try {
                     if (var6 != null) {
                        this.q(var6);
                     }

                     if (!this.q(var13, var1)) {
                        var7 = this.q(var13);
                        var8 = this.q(var7);
                        break;
                     }
                  } catch (Throwable var10) {
                     q.catching(var10, Strings.ff(S.L("An error occurred when attempting to load script plugin: %s"), var15));
                  }
               }
            }

            jx var17 = new jx(this, var7, var8, true, var13);
            this.Dw.put(var15, var17);
         }
      }

      ArrayList var12 = new ArrayList();

      for (IPluginFileEntry var16 : this.RF()) {
         if (var16.isValidPlugin()) {
            IPlugin var18 = var16.getPluginObject();
            if (var1.isAssignableFrom(var18.getClass())) {
               var12.add(var16);
            }
         }
      }

      return var12;
   }

   public List RF(Class var1, boolean var2) {
      ArrayList var3 = new ArrayList();

      for (IPluginFileEntry var5 : this.q(var1, var2)) {
         var3.add(var5.getPluginObject());
      }

      return var3;
   }

   protected abstract String xK();

   protected abstract String Dw();

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   protected boolean q(File var1, Class var2) {
      String var3;
      try {
         var3 = (String)Uv.get(var2);
         if (var3 == null) {
            return false;
         }
      } catch (Exception var13) {
         return false;
      }

      try {
         ScriptMetadata var4 = ScriptMetadataParser.extract(var1, this.Dw());
         if (!Strings.isBlank(var4.getType()) && !var4.getType().equals(var3)) {
            return true;
         }
      } catch (Exception var12) {
         return false;
      }

      Iterator var5;
      try {
         var5 = com.pnfsoftware.jeb.util.io.IO.readLines(var1).iterator();
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

   protected abstract Class q(File var1) throws Exception;

   protected IPlugin q(Class var1) throws Exception {
      return (IPlugin)var1.getDeclaredConstructor().newInstance();
   }

   static {
      Uv.put(IEnginesPlugin.class, "engines");
      Uv.put(IEOptimizer.class, "gendec-ir");
      Uv.put(ICOptimizer.class, "gendec-ast");
      Uv.put(IDOptimizer.class, "dexdec-ir");
      Uv.put(IJOptimizer.class, "dexdec-ast");
   }
}
