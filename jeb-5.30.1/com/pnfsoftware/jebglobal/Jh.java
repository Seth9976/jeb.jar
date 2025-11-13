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

public class Jh implements IPluginManager {
   private static final ILogger lm = GlobalLog.getLogger(Jh.class);
   private zJ zz;
   private File JY;
   private File HF;
   private boolean LK;
   private URLClassLoader io;
   List q = new ArrayList();
   List RF = new ArrayList();
   List xK = new ArrayList();
   List Dw = new ArrayList();
   List Uv = new ArrayList();
   List oW = new ArrayList();
   List gO = new ArrayList();
   List nf = new ArrayList();
   GA gP;
   Rd za;

   public Jh(IEnginesContext var1, File var2, File var3, boolean var4) {
      if (var1 == null) {
         throw new NullPointerException("The plugin manager needs a reference to an active JEB engines context");
      } else {
         this.zz = (zJ)var1;
         this.JY = var2;
         this.HF = var3;
         this.LK = var4;
      }
   }

   public boolean q() {
      if (!this.JY.isDirectory()) {
         return false;
      } else {
         try {
            this.gP = new GA(this.JY, this.HF);
            return true;
         } catch (JebException var2) {
            lm.catching(var2, S.L("The Java script plugin manager cannot be initialized"));
            return false;
         }
      }
   }

   public boolean RF() {
      if (!this.JY.isDirectory()) {
         return false;
      } else {
         try {
            this.za = new Rd(this.JY);
            return true;
         } catch (JebException var2) {
            lm.catching(var2, S.L("The Python script plugin manager cannot be initialized"));
            return false;
         }
      }
   }

   @Override
   public IEnginesContext getEnginesContext() {
      return this.zz;
   }

   @Override
   public ClassLoader getClassloader() {
      return this.io;
   }

   @Override
   public List load(File var1) {
      Jh.eo var2 = new Jh.eo();
      var2.q(var1);
      return this.q(var2);
   }

   @Override
   public Class load(String var1, String var2) {
      Jh.eo var3 = new Jh.eo();
      var3.q(var1, Arrays.asList(var2));
      List var4 = this.q(var3);
      return var4.isEmpty() ? null : (Class)var4.get(0);
   }

   List q(Jh.eo var1) {
      ArrayList var2 = new ArrayList();
      URLClassLoader var3 = var1.q.getClassloader(this.io);

      try {
         for (String var5 : var1.RF) {
            Class var6 = null;

            try {
               var6 = this.q(var3, var5);
            } catch (Exception var14) {
               lm.error(S.L("Error loading dev plugin: %s"), var5);
               lm.catching(var14);
            }

            if (var6 != null && this.q(var6)) {
               var2.add(var6);
            }
         }

         for (File var17 : var1.xK) {
            List var18 = null;

            try {
               var18 = this.q(var3, var17);
            } catch (Exception var13) {
               lm.error(S.L("Error loading jar plugin: %s"), var17);
               lm.catching(var13);
            }

            if (var18 != null) {
               for (Class var8 : var18) {
                  if (this.q(var8)) {
                     var2.add(var8);
                  }
               }
            }
         }
      } finally {
         this.io = var3;
      }

      return var2;
   }

   Class q(ClassLoader var1, String var2) {
      Class var3;
      try {
         var3 = var1.loadClass(var2);
      } catch (ClassNotFoundException var5) {
         lm.catching(var5);
         return null;
      }

      if (!IPlugin.class.isAssignableFrom(var3)) {
         lm.error(S.L("Not a plugin type: %s"), var3);
         return null;
      } else {
         lm.debug(S.L("Development plugin loaded: %s"), var3);
         return var3;
      }
   }

   List q(ClassLoader var1, File var2) {
      Attributes var4;
      try {
         URL var3 = var2.toURI().toURL();
         URL var5 = new URL("jar", "", var3 + "!/");
         JarURLConnection var6 = (JarURLConnection)var5.openConnection();
         var4 = var6.getMainAttributes();
      } catch (IOException var14) {
         lm.catching(var14);
         return null;
      }

      String var15 = var4 == null ? null : var4.getValue("JebPlugin-entryclass");
      if (var15 == null) {
         lm.warn(S.L("Error loading Jar plugin, no JebPlugin-class attribute found in manifest") + ": " + var2.getAbsolutePath());
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
                  lm.catching(var13);
                  return null;
               }

               if (!IPlugin.class.isAssignableFrom(var11)) {
                  lm.error(S.L("Not a plugin type: %s"), var11.getName());
                  return null;
               }

               lm.debug(S.L("External plugin loaded: %s"), var11.getName());
               var16.add(var11);
            }
         }

         return var16;
      }
   }

   public boolean q(Class var1) {
      if (IEnginesPlugin.class.isAssignableFrom(var1)) {
         IEnginesPlugin var3 = (IEnginesPlugin)RF(var1);
         if (var3 != null) {
            this.RF.add(new jx(null, var1, var3, false, null));
            return true;
         }
      } else if (IUnitPlugin.class.isAssignableFrom(var1)) {
         if (wq.q(var1)) {
            this.q.add(var1);
            return true;
         }
      } else if (IUnitContribution.class.isAssignableFrom(var1)) {
         IUnitContribution var4 = (IUnitContribution)RF(var1);
         if (var4 != null) {
            this.xK.add(var4);
            return true;
         }
      } else if (INativeDecompilerExtension.class.isAssignableFrom(var1)) {
         INativeDecompilerExtension var5 = (INativeDecompilerExtension)RF(var1);
         if (var5 != null) {
            this.Dw.add(var5);
            adw.q(var5);
            return true;
         }
      } else if (IEOptimizer.class.isAssignableFrom(var1)) {
         IEOptimizer var6 = (IEOptimizer)RF(var1);
         if (var6 != null) {
            this.Uv.add(new jx(null, var1, var6, false, null));
            return true;
         }
      } else if (ICOptimizer.class.isAssignableFrom(var1)) {
         ICOptimizer var7 = (ICOptimizer)RF(var1);
         if (var7 != null) {
            this.oW.add(new jx(null, var1, var7, false, null));
            return true;
         }
      } else if (IDOptimizer.class.isAssignableFrom(var1)) {
         IDOptimizer var8 = (IDOptimizer)RF(var1);
         if (var8 != null) {
            this.gO.add(new jx(null, var1, var8, false, null));
            return true;
         }
      } else if (IJOptimizer.class.isAssignableFrom(var1)) {
         IJOptimizer var9 = (IJOptimizer)RF(var1);
         if (var9 != null) {
            this.nf.add(new jx(null, var1, var9, false, null));
            return true;
         }
      } else {
         lm.error(S.L("Unsupported plugin type: %s"), var1);
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
         for (IPluginFileEntry var5 : this.RF) {
            IPlugin var6 = var5.getPluginObject();
            if (var6 != null) {
               var3.add(var6);
            }
         }
      }

      if (var1.isAssignableFrom(IEOptimizer.class)) {
         for (IPluginFileEntry var13 : this.Uv) {
            IPlugin var17 = var13.getPluginObject();
            if (var17 != null) {
               var3.add(var17);
            }
         }
      }

      if (var1.isAssignableFrom(ICOptimizer.class)) {
         for (IPluginFileEntry var14 : this.oW) {
            IPlugin var18 = var14.getPluginObject();
            if (var18 != null) {
               var3.add(var18);
            }
         }
      }

      if (var1.isAssignableFrom(IDOptimizer.class)) {
         for (IPluginFileEntry var15 : this.gO) {
            IPlugin var19 = var15.getPluginObject();
            if (var19 != null) {
               var3.add(var19);
            }
         }
      }

      if (var1.isAssignableFrom(IJOptimizer.class)) {
         for (IPluginFileEntry var16 : this.nf) {
            IPlugin var20 = var16.getPluginObject();
            if (var20 != null) {
               var3.add(var20);
            }
         }
      }

      if (this.gP != null) {
         List var11 = this.gP.RF(var1, var2);
         var3.addAll(var11);
      }

      if (this.za != null) {
         List var12 = this.za.RF(var1, var2);
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
         var3.addAll(this.RF);
      }

      if (var1.isAssignableFrom(IEOptimizer.class)) {
         var3.addAll(this.Uv);
      }

      if (var1.isAssignableFrom(ICOptimizer.class)) {
         var3.addAll(this.oW);
      }

      if (var1.isAssignableFrom(IDOptimizer.class)) {
         var3.addAll(this.gO);
      }

      if (var1.isAssignableFrom(IJOptimizer.class)) {
         var3.addAll(this.nf);
      }

      if (this.gP != null) {
         List var4 = this.gP.q(var1, var2);
         var3.addAll(var4);
      }

      if (this.za != null) {
         List var5 = this.za.q(var1, var2);
         var3.addAll(var5);
      }

      return var3;
   }

   public static Object RF(Class var0) {
      try {
         return var0.getConstructor().newInstance();
      } catch (ReflectiveOperationException var2) {
         lm.catching(var2);
         return null;
      }
   }

   public static class eo {
      ClasspathCollector q;
      List RF = new ArrayList();
      List xK = new ArrayList();

      public eo() {
         this.q = new ClasspathCollector();
      }

      public void q(String var1) {
         if (var1 != null) {
            this.q.add(var1);
         }
      }

      public void RF(String var1) {
         if (var1 != null) {
            this.RF.add(var1);
         }
      }

      public void q(String var1, List var2) {
         if (var1 != null) {
            this.q.add(var1);
         }

         if (var2 != null) {
            this.RF.addAll(var2);
         }
      }

      public void q(File var1) {
         for (File var4 : new ST(var1).q()) {
            if (this.q.addFromJar(var4)) {
               this.xK.add(var4);
            }
         }
      }

      public void RF(File var1) {
         if (var1.isDirectory()) {
            this.q.add(var1.getPath());

            for (File var4 : new ST(var1).q()) {
               if (this.q.addFromJar(var4)) {
                  this.xK.add(var4);
               }
            }
         }
      }
   }
}
