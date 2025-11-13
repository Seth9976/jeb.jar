package com.pnfsoftware.jeb.util.format;

public class PluralFormatter {
   public static String count(Number var0, String var1, String var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("No value provided");
      } else if (var0.doubleValue() < 2.0) {
         return var1;
      } else {
         return var2 == null ? var1 : var2;
      }
   }

   public static String countS(Number var0, String var1) {
      return count(var0, var1, var1 + "s");
   }
}
