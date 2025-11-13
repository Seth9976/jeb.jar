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

public class ST {
   private Map q = new HashMap();

   public ST(File var1) {
      File[] var2 = null;
      if (var1.isFile()) {
         var2 = new File[]{var1};
      } else if (var1.isDirectory()) {
         var2 = var1.listFiles();
      }

      if (var2 != null) {
         for (File var6 : var2) {
            if (var6.isFile() && var6.getName().toLowerCase().endsWith(".jar")) {
               ST.eo var7 = this.q(var6);
               if (var7 != null) {
                  ST.eo var8 = (ST.eo)this.q.get(var7.RF);
                  if (var8 == null) {
                     this.q.put(var7.RF, var7);
                  } else if (var7.xK.compareTo(var8.xK) > 0) {
                     this.q.put(var7.RF, var7);
                  }
               }
            }
         }
      }
   }

   public List q() {
      ArrayList var1 = new ArrayList();

      for (String var3 : this.q.keySet()) {
         var1.add(((ST.eo)this.q.get(var3)).q);
      }

      return var1;
   }

   private ST.eo q(File var1) {
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

      ST.eo var7 = new ST.eo();
      var7.q = var1;
      var7.RF = var3.getValue("JebPlugin-entryclass");
      if (var7.RF == null) {
         return null;
      } else {
         var7.xK = Version.parseFromString(var3.getValue("JebPlugin-version"));
         if (var7.xK == null) {
            var7.xK = Version.create(0, 0, 1);
         }

         return var7;
      }
   }

   public static class eo {
      File q;
      String RF;
      Version xK;
   }
}
