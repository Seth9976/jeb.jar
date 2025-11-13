package com.pnfsoftware.jeb.corei.parsers.dex;

import org.jsoup.nodes.Element;

public class kY {
   public static String q(Element var0) {
      if (!var0.tagName().equals("link")) {
         return null;
      } else {
         String var1 = var0.attr("href");
         if (var1 == null) {
            return null;
         } else {
            if (var1.contains("?")) {
               var1 = var1.substring(0, var1.indexOf("?"));
            }

            String var2 = var0.attr("rel");
            return !var2.equals("stylesheet") && !var1.endsWith(".css") ? null : var1;
         }
      }
   }

   public static String q(String var0) {
      int var2 = 0;

      while (true) {
         int var3 = var0.indexOf("#", var2);
         int var4 = var0.indexOf("rgb", var2);
         if (var3 != -1 && var4 != -1) {
            if (var3 < var4) {
               var4 = -1;
            } else {
               var3 = -1;
            }
         }

         if (var3 != -1) {
            int var8 = var0.indexOf(";", var3);
            if (var8 == -1) {
               break;
            }

            String var6 = var0.substring(var3 + 1, var8);
            var0 = var0.replace(var6, RF(var6));
            var2 = var3 + 1;
         } else {
            if (var4 == -1) {
               break;
            }

            var3 = var0.indexOf("(", var2);
            if (var3 == -1) {
               break;
            }

            int var5 = var0.indexOf(")", var3);
            if (var5 == -1) {
               break;
            }

            String var1 = var0.substring(var3 + 1, var5);
            var0 = var0.replace(var1, xK(var1));
            var2 = var3 + 1;
         }
      }

      return var0;
   }

   private static CharSequence RF(String var0) {
      var0 = var0.trim();
      if (var0.length() == 3) {
         var0 = "" + var0.charAt(0) + var0.charAt(0) + var0.charAt(1) + var0.charAt(1) + var0.charAt(2) + var0.charAt(2);
      }

      return var0.length() == 6 ? q(var0.substring(0, 2), 16) + q(var0.substring(2, 4), 16) + q(var0.substring(4, 6), 16) : var0;
   }

   private static CharSequence xK(String var0) {
      String[] var1 = var0.split(",");
      if (var1.length < 3) {
         return var0;
      } else {
         for (int var2 = 0; var2 < var1.length; var2++) {
            var1[var2] = var1[var2].trim();
         }

         return q(var1[0], 10) + q(var1[1], 10) + q(var1[2], 10);
      }
   }

   private static String q(String var0, int var1) {
      try {
         String var2 = Integer.toString(255 - Integer.parseInt(var0, var1), 16);
         return var2.length() == 1 ? "0" + var2 : var2;
      } catch (NumberFormatException var3) {
         return var0;
      }
   }
}
