package com.pnfsoftware.jeb.util.primitives;

public class Doubles {
   public static int getSpecialType(double var0) {
      return getSpecialType(Double.doubleToRawLongBits(var0));
   }

   public static int getSpecialType(long var0) {
      if ((var0 & 9218868437227405312L) == 9218868437227405312L) {
         if ((var0 & 4503599627370495L) != 0L) {
            return (var0 & 2251799813685248L) != 0L ? 1 : 2;
         } else {
            return 3;
         }
      } else {
         return 0;
      }
   }

   public static int getNaNType(double var0) {
      return getNaNType(Double.doubleToRawLongBits(var0));
   }

   public static int getNaNType(long var0) {
      boolean var2 = (var0 & 9218868437227405312L) == 9218868437227405312L && (var0 & 4503599627370495L) != 0L;
      if (!var2) {
         return 0;
      } else {
         return (var0 & 2251799813685248L) != 0L ? 1 : 2;
      }
   }

   public static boolean isQuietNaN(double var0) {
      return isQuietNaN(Double.doubleToRawLongBits(var0));
   }

   public static boolean isQuietNaN(long var0) {
      boolean var2 = (var0 & 9218868437227405312L) == 9218868437227405312L && (var0 & 4503599627370495L) != 0L;
      return !var2 ? false : (var0 & 2251799813685248L) != 0L;
   }

   public static boolean isSignalingNaN(double var0) {
      return isSignalingNaN(Double.doubleToRawLongBits(var0));
   }

   public static boolean isSignalingNaN(long var0) {
      boolean var2 = (var0 & 9218868437227405312L) == 9218868437227405312L && (var0 & 4503599627370495L) != 0L;
      return !var2 ? false : (var0 & 2251799813685248L) == 0L;
   }
}
