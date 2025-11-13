package com.pnfsoftware.jeb.util.primitives;

public class Booleans {
   public static boolean isTrue(Boolean var0) {
      return var0 != null && var0;
   }

   public static boolean isFalse(Boolean var0) {
      return var0 != null && !var0;
   }

   public static boolean toBoolean(Boolean var0) {
      return var0 != null && var0;
   }

   public static boolean equals(Boolean var0, Boolean var1) {
      if (var0 == null) {
         return var1 == null;
      } else {
         return var1 == null ? false : var0.equals(var1);
      }
   }
}
