package com.pnfsoftware.jeb.util.primitives;

public class Floats {
   public static int getSpecialType(float var0) {
      return getNaNType(Float.floatToRawIntBits(var0));
   }

   public static int getSpecialType(int var0) {
      if ((var0 & 2139095040) == 2139095040) {
         if ((var0 & 8388607) != 0) {
            return (var0 & 4194304) != 0 ? 1 : 2;
         } else {
            return 3;
         }
      } else {
         return 0;
      }
   }

   public static int getNaNType(float var0) {
      return getNaNType(Float.floatToRawIntBits(var0));
   }

   public static int getNaNType(int var0) {
      boolean var1 = (var0 & 2139095040) == 2139095040 && (var0 & 8388607) != 0;
      if (!var1) {
         return 0;
      } else {
         return (var0 & 4194304) != 0 ? 1 : 2;
      }
   }

   public static boolean isQuietNaN(float var0) {
      return isQuietNaN(Float.floatToRawIntBits(var0));
   }

   public static boolean isQuietNaN(int var0) {
      boolean var1 = (var0 & 2139095040) == 2139095040 && (var0 & 8388607) != 0;
      return !var1 ? false : (var0 & 4194304) != 0;
   }

   public static boolean isSignalingNaN(float var0) {
      return isSignalingNaN(Float.floatToRawIntBits(var0));
   }

   public static boolean isSignalingNaN(int var0) {
      boolean var1 = (var0 & 2139095040) == 2139095040 && (var0 & 8388607) != 0;
      return !var1 ? false : (var0 & 4194304) == 0;
   }

   public static float fromFP16Bits(int var0) {
      int var1 = var0 >> 15 & 1;
      int var2 = var0 >> 10 & 31;
      int var3 = var0 & 1023;
      if (var2 == 0) {
         return var3 == 0 ? 0.0F : var3 / 1.6777216E7F;
      } else if (var2 == 31) {
         if (var3 != 0) {
            return Float.NaN;
         } else {
            return var1 == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
         }
      } else {
         int var4 = var1 << 31;
         int var5 = var2 - 15 + 127 << 23;
         int var6 = var3 << 13;
         int var7 = var4 | var5 | var6;
         return Float.intBitsToFloat(var7);
      }
   }

   public static float fromBF16Bits(int var0) {
      int var1 = var0 >> 15 & 1;
      int var2 = var0 >> 7 & 0xFF;
      int var3 = var0 & 127;
      if (var2 == 0) {
         return var3 == 0 ? 0.0F : (float)(2.0E-126 * (var3 / 128.0));
      } else if (var2 == 255) {
         if (var3 != 0) {
            return Float.NaN;
         } else {
            return var1 == 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
         }
      } else {
         int var4 = var1 << 31;
         int var5 = var2 << 23;
         int var6 = var3 << 16;
         int var7 = var4 | var5 | var6;
         return Float.intBitsToFloat(var7);
      }
   }
}
