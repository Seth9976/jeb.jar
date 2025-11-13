package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.net.URLUtil;
import java.io.File;
import java.net.URL;

public class qx {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 500;

   public static File q() {
      try {
         Class<qx> var0 = qx.class;
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
