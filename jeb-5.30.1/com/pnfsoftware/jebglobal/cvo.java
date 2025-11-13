package com.pnfsoftware.jebglobal;

@Deprecated
public class cvo {
   public static boolean q(String var0) {
      return var0.startsWith("?");
   }

   public static String RF(String var0) {
      if (var0.startsWith("_")) {
         if (var0.matches(".*@[0-9]+")) {
            var0 = var0.substring(1, var0.lastIndexOf("@"));
         } else {
            var0 = var0.substring(1);
         }
      } else if (var0.startsWith("@") && var0.matches(".*@[0-9]+")) {
         var0 = var0.substring(1, var0.lastIndexOf("@"));
      } else if (var0.matches(".*@@[0-9]+")) {
         var0 = var0.substring(0, var0.lastIndexOf("@@"));
      }

      return var0;
   }
}
