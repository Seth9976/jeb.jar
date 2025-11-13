package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.Version;
import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

public class lB {
   private Map pC = new HashMap();

   public lB(File var1) {
      File[] var2 = null;
      if (var1.isFile()) {
         var2 = new File[]{var1};
      } else if (var1.isDirectory()) {
         var2 = var1.listFiles();
      }

      if (var2 != null) {
         for (File var6 : var2) {
            if (var6.isFile() && var6.getName().toLowerCase().endsWith(".jar")) {
               lB.Av var7 = this.pC(var6);
               if (var7 != null) {
                  lB.Av var8 = (lB.Av)this.pC.get(var7.A);
                  if (var8 == null) {
                     this.pC.put(var7.A, var7);
                  } else if (var7.kS.compareTo(var8.kS) > 0) {
                     this.pC.put(var7.A, var7);
                  }
               }
            }
         }
      }
   }

   public List pC() {
      ArrayList var1 = new ArrayList();

      for (String var3 : this.pC.keySet()) {
         var1.add(((lB.Av)this.pC.get(var3)).pC);
      }

      return var1;
   }

   private lB.Av pC(File var1) {
      Attributes var3;
      try {
         URL var2 = var1.toURI().toURL();
         URL var4 = new URL("jar", "", var2 + "!/");
         JarURLConnection var5 = (JarURLConnection)var4.openConnection();
         var3 = var5.getMainAttributes();
         if (var3 == null) {
            return null;
         }
      } catch (IOException var6) {
         return null;
      }

      lB.Av var7 = new lB.Av();
      var7.pC = var1;
      var7.A = var3.getValue("JebPlugin-entryclass");
      if (var7.A == null) {
         return null;
      } else {
         var7.kS = Version.parseFromString(var3.getValue("JebPlugin-version"));
         if (var7.kS == null) {
            var7.kS = Version.create(0, 0, 1);
         }

         return var7;
      }
   }

   public static class Av {
      File pC;
      String A;
      Version kS;
   }
}
