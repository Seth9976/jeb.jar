package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IEnginesContext;
import com.pnfsoftware.jeb.core.IEnginesPlugin;
import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.IPluginFileEntry;
import com.pnfsoftware.jeb.core.IPluginManager;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.units.IUnitPlugin;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.ICOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJOptimizer;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ClasspathCollector;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Attributes;

public class MG implements IPluginManager {
   private static final ILogger oT = GlobalLog.getLogger(MG.class);
   private Nj fI;
   private File WR;
   private File NS;
   private boolean vP;
   private URLClassLoader xC;
   List pC = new ArrayList();
   List A = new ArrayList();
   List kS = new ArrayList();
   List wS = new ArrayList();
   List UT = new ArrayList();
   List E = new ArrayList();
   List sY = new ArrayList();
   List ys = new ArrayList();
   OX ld;
   Ee gp;

   public MG(IEnginesContext var1, File var2, File var3, boolean var4) {
      if (var1 == null) {
         throw new NullPointerException("The plugin manager needs a reference to an active JEB engines context");
      } else {
         this.fI = (Nj)var1;
         this.WR = var2;
         this.NS = var3;
         this.vP = var4;
      }
   }

   public boolean pC() {
      if (!this.WR.isDirectory()) {
         return false;
      } else {
         try {
            this.ld = new OX(this.WR, this.NS);
            return true;
         } catch (JebException var2) {
            oT.catching(var2, S.L("The Java script plugin manager cannot be initialized"));
            return false;
         }
      }
   }

   public boolean A() {
      if (!this.WR.isDirectory()) {
         return false;
      } else {
         try {
            this.gp = new Ee(this.WR);
            return true;
         } catch (JebException var2) {
            oT.catching(var2, S.L("The Python script plugin manager cannot be initialized"));
            return false;
         }
      }
   }

   @Override
   public IEnginesContext getEnginesContext() {
      return this.fI;
   }

   @Override
   public ClassLoader getClassloader() {
      return this.xC;
   }

   @Override
   public List load(File var1) {
      MG.Av var2 = new MG.Av();
      var2.pC(var1);
      return this.pC(var2);
   }

   @Override
   public Class load(String var1, String var2) {
      MG.Av var3 = new MG.Av();
      var3.pC(var1, Arrays.asList(var2));
      List var4 = this.pC(var3);
      return var4.isEmpty() ? null : (Class)var4.get(0);
   }

   List pC(MG.Av var1) {
      ArrayList var2 = new ArrayList();
      URLClassLoader var3 = var1.pC.getClassloader(this.xC);

      try {
         for (String var5 : var1.A) {
            Class var6 = null;

            try {
               var6 = this.pC(var3, var5);
            } catch (Exception var14) {
               oT.error(S.L("Error loading dev plugin: %s"), var5);
               oT.catching(var14);
            }

            if (var6 != null && this.pC(var6)) {
               var2.add(var6);
            }
         }

         for (File var17 : var1.kS) {
            List var18 = null;

            try {
               var18 = this.pC(var3, var17);
            } catch (Exception var13) {
               oT.error(S.L("Error loading jar plugin: %s"), var17);
               oT.catching(var13);
            }

            if (var18 != null) {
               for (Class var8 : var18) {
                  if (this.pC(var8)) {
                     var2.add(var8);
                  }
               }
            }
         }
      } finally {
         this.xC = var3;
      }

      return var2;
   }

   Class pC(ClassLoader var1, String var2) {
      Class var3;
      try {
         var3 = var1.loadClass(var2);
      } catch (ClassNotFoundException var5) {
         oT.catching(var5);
         return null;
      }

      if (!IPlugin.class.isAssignableFrom(var3)) {
         oT.error(S.L("Not a plugin type: %s"), var3);
         return null;
      } else {
         oT.debug(S.L("Development plugin loaded: %s"), var3);
         return var3;
      }
   }

   List pC(ClassLoader var1, File var2) {
      Attributes var4;
      try {
         URL var3 = var2.toURI().toURL();
         URL var5 = new URL("jar", "", var3 + "!/");
         JarURLConnection var6 = (JarURLConnection)var5.openConnection();
         var4 = var6.getMainAttributes();
      } catch (IOException var14) {
         oT.catching(var14);
         return null;
      }

      String var15 = var4 == null ? null : var4.getValue("JebPlugin-entryclass");
      if (var15 == null) {
         oT.warn(S.L("Error loading Jar plugin, no JebPlugin-class attribute found in manifest") + ": " + var2.getAbsolutePath());
         return null;
      } else {
         ArrayList var16 = new ArrayList();

         for (String var10 : Strings.splitall(var15, "\\s+")) {
            var10 = var10.trim();
            if (!var10.isEmpty()) {
               Class var11;
               try {
                  var11 = Class.forName(var10, true, var1);
               } catch (ClassNotFoundException var13) {
                  oT.catching(var13);
                  return null;
               }

               if (!IPlugin.class.isAssignableFrom(var11)) {
                  oT.error(S.L("Not a plugin type: %s"), var11.getName());
                  return null;
               }

               oT.debug(S.L("External plugin loaded: %s"), var11.getName());
               var16.add(var11);
            }
         }

         return var16;
      }
   }

   public boolean pC(Class var1) {
      if (IEnginesPlugin.class.isAssignableFrom(var1)) {
         IEnginesPlugin var3 = (IEnginesPlugin)A(var1);
         if (var3 != null) {
            this.A.add(new QM(null, var1, var3, false, null));
            return true;
         }
      } else if (IUnitPlugin.class.isAssignableFrom(var1)) {
         if (XR.pC(var1)) {
            this.pC.add(var1);
            return true;
         }
      } else if (IUnitContribution.class.isAssignableFrom(var1)) {
         IUnitContribution var4 = (IUnitContribution)A(var1);
         if (var4 != null) {
            this.kS.add(var4);
            return true;
         }
      } else if (INativeDecompilerExtension.class.isAssignableFrom(var1)) {
         INativeDecompilerExtension var5 = (INativeDecompilerExtension)A(var1);
         if (var5 != null) {
            this.wS.add(var5);
            ace.pC(var5);
            return true;
         }
      } else if (IEOptimizer.class.isAssignableFrom(var1)) {
         IEOptimizer var6 = (IEOptimizer)A(var1);
         if (var6 != null) {
            this.UT.add(new QM(null, var1, var6, false, null));
            return true;
         }
      } else if (ICOptimizer.class.isAssignableFrom(var1)) {
         ICOptimizer var7 = (ICOptimizer)A(var1);
         if (var7 != null) {
            this.E.add(new QM(null, var1, var7, false, null));
            return true;
         }
      } else if (IDOptimizer.class.isAssignableFrom(var1)) {
         IDOptimizer var8 = (IDOptimizer)A(var1);
         if (var8 != null) {
            this.sY.add(new QM(null, var1, var8, false, null));
            return true;
         }
      } else if (IJOptimizer.class.isAssignableFrom(var1)) {
         IJOptimizer var9 = (IJOptimizer)A(var1);
         if (var9 != null) {
            this.ys.add(new QM(null, var1, var9, false, null));
            return true;
         }
      } else {
         oT.error(S.L("Unsupported plugin type: %s"), var1);
      }

      return false;
   }

   @Override
   public List getPlugins(Class var1) {
      return this.getPlugins(var1, true);
   }

   @Override
   public List getPlugins(Class var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      if (var1.isAssignableFrom(IEnginesPlugin.class)) {
         for (IPluginFileEntry var5 : this.A) {
            IPlugin var6 = var5.getPluginObject();
            if (var6 != null) {
               var3.add(var6);
            }
         }
      }

      if (var1.isAssignableFrom(IEOptimizer.class)) {
         for (IPluginFileEntry var13 : this.UT) {
            IPlugin var17 = var13.getPluginObject();
            if (var17 != null) {
               var3.add(var17);
            }
         }
      }

      if (var1.isAssignableFrom(ICOptimizer.class)) {
         for (IPluginFileEntry var14 : this.E) {
            IPlugin var18 = var14.getPluginObject();
            if (var18 != null) {
               var3.add(var18);
            }
         }
      }

      if (var1.isAssignableFrom(IDOptimizer.class)) {
         for (IPluginFileEntry var15 : this.sY) {
            IPlugin var19 = var15.getPluginObject();
            if (var19 != null) {
               var3.add(var19);
            }
         }
      }

      if (var1.isAssignableFrom(IJOptimizer.class)) {
         for (IPluginFileEntry var16 : this.ys) {
            IPlugin var20 = var16.getPluginObject();
            if (var20 != null) {
               var3.add(var20);
            }
         }
      }

      if (this.ld != null) {
         List var11 = this.ld.A(var1, var2);
         var3.addAll(var11);
      }

      if (this.gp != null) {
         List var12 = this.gp.A(var1, var2);
         var3.addAll(var12);
      }

      return var3;
   }

   @Override
   public List getPluginEntries(Class var1) {
      return this.getPluginEntries(var1, true);
   }

   @Override
   public List getPluginEntries(Class var1, boolean var2) {
      ArrayList var3 = new ArrayList();
      if (var1.isAssignableFrom(IEnginesPlugin.class)) {
         var3.addAll(this.A);
      }

      if (var1.isAssignableFrom(IEOptimizer.class)) {
         var3.addAll(this.UT);
      }

      if (var1.isAssignableFrom(ICOptimizer.class)) {
         var3.addAll(this.E);
      }

      if (var1.isAssignableFrom(IDOptimizer.class)) {
         var3.addAll(this.sY);
      }

      if (var1.isAssignableFrom(IJOptimizer.class)) {
         var3.addAll(this.ys);
      }

      if (this.ld != null) {
         List var4 = this.ld.pC(var1, var2);
         var3.addAll(var4);
      }

      if (this.gp != null) {
         List var5 = this.gp.pC(var1, var2);
         var3.addAll(var5);
      }

      return var3;
   }

   public static Object A(Class var0) {
      try {
         return var0.getConstructor().newInstance();
      } catch (ReflectiveOperationException var2) {
         oT.catching(var2);
         return null;
      }
   }

   public static class Av {
      ClasspathCollector pC;
      List A = new ArrayList();
      List kS = new ArrayList();

      public Av() {
         this.pC = new ClasspathCollector();
      }

      public void pC(String var1) {
         if (var1 != null) {
            this.pC.add(var1);
         }
      }

      public void A(String var1) {
         if (var1 != null) {
            this.A.add(var1);
         }
      }

      public void pC(String var1, List var2) {
         if (var1 != null) {
            this.pC.add(var1);
         }

         if (var2 != null) {
            this.A.addAll(var2);
         }
      }

      public void pC(File var1) {
         for (File var4 : new lB(var1).pC()) {
            if (this.pC.addFromJar(var4)) {
               this.kS.add(var4);
            }
         }
      }

      public void A(File var1) {
         if (var1.isDirectory()) {
            this.pC.add(var1.getPath());

            for (File var4 : new lB(var1).pC()) {
               if (this.pC.addFromJar(var4)) {
                  this.kS.add(var4);
               }
            }
         }
      }
   }
}
