package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.net.URLUtil;
import java.io.File;
import java.net.URL;

public class wY {
   public static File pC() {
      try {
         Class<wY> var0 = wY.class;
         URL var1 = var0.getProtectionDomain().getCodeSource().getLocation();
         File var2 = URLUtil.urlToFile(var1);
         if (!var2.isFile()) {
            if (!var2.getName().equals("bin")) {
               var2 = new File(var2, "bin");
            }

            if (!var2.isDirectory()) {
               return null;
            }
         }

         return var2;
      } catch (Exception var3) {
         return null;
      }
   }
}
