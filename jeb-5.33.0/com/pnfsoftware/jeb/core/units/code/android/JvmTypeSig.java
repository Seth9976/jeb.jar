package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.base.Couple;

public class JvmTypeSig {
   public static void verify(String var0) {
      verifyType(var0);
   }

   public static void verifyType(String var0) {
      int var1 = getDimensionCount(var0);
      verifyClass(var0.substring(var1));
   }

   public static void verifyClass(String var0) {
      if (var0.length() < 3) {
         throw new IllegalArgumentException();
      } else if (!var0.startsWith("L") || !var0.endsWith(";")) {
         throw new IllegalArgumentException();
      }
   }

   public static int getDimensionCount(String var0) {
      int var1 = 0;

      while (var0.charAt(var1) == '[') {
         var1++;
      }

      return var1;
   }

   public static Couple parseArrayType(String var0) {
      int var1 = 0;

      while (var0.charAt(var1) == '[') {
         var1++;
      }

      return new Couple(var0.substring(var1), var1);
   }
}
