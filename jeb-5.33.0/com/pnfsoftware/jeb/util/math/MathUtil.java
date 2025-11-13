package com.pnfsoftware.jeb.util.math;

import java.util.Collection;

public class MathUtil {
   public static boolean almostEquals(double var0, double var2) {
      return Math.abs(var0 - var2) <= 1.0E-7;
   }

   public static boolean almostEquals(double var0, double var2, double var4) {
      return Math.abs(var0 - var2) <= var4;
   }

   public static long makeMask(int var0) {
      if (var0 < 0) {
         throw new IllegalArgumentException("Bitsize must be >= 0: " + var0);
      } else if (var0 > 64) {
         throw new IllegalArgumentException("Bitsize must be <= 64: " + var0);
      } else {
         return var0 == 64 ? -1L : (1L << var0) - 1L;
      }
   }

   public static long makeInverseMask(int var0) {
      if (var0 < 0) {
         throw new IllegalArgumentException("Bitsize must be >= 0: " + var0);
      } else if (var0 > 64) {
         throw new IllegalArgumentException("Bitsize must be <= 64: " + var0);
      } else {
         return var0 == 64 ? 0L : ~((1L << var0) - 1L);
      }
   }

   public static long makeOverflow(int var0) {
      if (var0 < 0) {
         throw new IllegalArgumentException("Bitsize must be >= 0: " + var0);
      } else if (var0 > 64) {
         throw new IllegalArgumentException("Bitsize must be <= 64: " + var0);
      } else {
         return var0 == 64 ? 0L : 1L << var0;
      }
   }

   public static boolean betweenExclusive(long var0, long var2, long var4) {
      return var0 > var2 && var0 < var4;
   }

   public static boolean betweenInclusive(long var0, long var2, long var4) {
      return var0 >= var2 && var0 <= var4;
   }

   public static int minPositive(int... var0) {
      int var1 = var0[0];

      for (int var5 : var0) {
         if (var1 < 0) {
            var1 = Math.max(var5, var1);
         } else if (var5 > 0) {
            var1 = Math.min(var5, var1);
         }
      }

      return var1;
   }

   private static long signExtend(long var0, int var2, int var3) {
      if (var3 != 32 && var3 != 64) {
         throw new IllegalArgumentException("unsupported size for sign extension: " + var3);
      } else if (var2 > var3) {
         throw new IllegalArgumentException("bitsize must be <= " + var3 + " (was " + var2 + ")");
      } else {
         if (var2 < var3) {
            long var4 = (1L << var2) - 1L;
            if ((var0 & 1L << var2 - 1) != 0L) {
               var0 |= ~var4;
            } else {
               var0 &= var4;
            }
         }

         return var0;
      }
   }

   public static long signExtend32(long var0, int var2) {
      return signExtend(var0, var2, 32);
   }

   public static int signExtend32(int var0, int var1) {
      return (int)signExtend(var0, var1, 32);
   }

   public static long signExtend(long var0, int var2) {
      return signExtend(var0, var2, 64);
   }

   private static long zeroExtend(long var0, int var2, int var3) {
      if (var3 != 32 && var3 != 64) {
         throw new IllegalArgumentException("unsupported size for zero extension: " + var3);
      } else if (var2 > var3) {
         throw new IllegalArgumentException("bitsize must be <= " + var3 + " (was " + var2 + ")");
      } else {
         if (var2 < var3) {
            var0 &= (1L << var2) - 1L;
         }

         return var0;
      }
   }

   public static long zeroExtend32(long var0, int var2) {
      return zeroExtend(var0, var2, 32);
   }

   public static long zeroExtend(long var0, int var2) {
      return zeroExtend(var0, var2, 64);
   }

   public static int msb(long var0, int var2) {
      if (var2 < 0) {
         throw new IllegalArgumentException("Bitsize must be >= 0: " + var2);
      } else if (var2 > 64) {
         throw new IllegalArgumentException("Bitsize must be <= 64: " + var2);
      } else {
         return (int)(var0 >>> var2 - 1 & 1L);
      }
   }

   public static int pow(int var0, int var1) {
      if (var1 == 0) {
         return 1;
      } else if (var1 == 1) {
         return var0;
      } else if (var1 < 0) {
         if (var0 == 0) {
            throw new IllegalArgumentException("Can not compute 0 pow " + var1);
         } else {
            return var0 == 1 ? 1 : 0;
         }
      } else {
         int var2 = var0;

         for (int var3 = 1; var3 < var1; var3++) {
            var2 *= var0;
         }

         return var2;
      }
   }

   public static long pow(long var0, long var2) {
      if (var2 == 0L) {
         return 1L;
      } else if (var2 == 1L) {
         return var0;
      } else if (var2 < 0L) {
         if (var0 == 0L) {
            throw new IllegalArgumentException("Can not compute 0 pow " + var2);
         } else {
            return var0 == 1L ? 1L : 0L;
         }
      } else {
         long var4 = var0;

         for (long var6 = 1L; var6 < var2; var6++) {
            var4 *= var0;
         }

         return var4;
      }
   }

   public static long unmaskedShiftLeft(long var0, int var2) {
      return var2 >= 64 ? 0L : var0 << var2;
   }

   public static long unmaskedShiftRight(long var0, int var2) {
      return var2 >= 64 ? 0L : var0 >>> var2;
   }

   public static long unmaskedArithShiftRight(long var0, int var2) {
      if (var2 >= 64) {
         return var0 < 0L ? -1L : 0L;
      } else {
         return var0 >> var2;
      }
   }

   public static int moduloPositive(int var0, int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("The divisor must be positive");
      } else {
         int var2 = var0 % var1;
         if (var2 < 0) {
            var2 += var1;
         }

         return var2;
      }
   }

   public static double avg(Collection var0) {
      if (var0.isEmpty()) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;
         int var3 = 1;

         for (Number var5 : var0) {
            var1 += (var5.doubleValue() - var1) / var3;
            var3++;
         }

         return var1;
      }
   }

   public static double avg(byte[] var0) {
      if (var0.length == 0) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;

         for (int var3 = 0; var3 < var0.length; var3++) {
            var1 += (var0[var3] - var1) / (var3 + 1);
         }

         return var1;
      }
   }

   public static double avg(char[] var0) {
      if (var0.length == 0) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;

         for (int var3 = 0; var3 < var0.length; var3++) {
            var1 += (var0[var3] - var1) / (var3 + 1);
         }

         return var1;
      }
   }

   public static double avg(short[] var0) {
      if (var0.length == 0) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;

         for (int var3 = 0; var3 < var0.length; var3++) {
            var1 += (var0[var3] - var1) / (var3 + 1);
         }

         return var1;
      }
   }

   public static double avg(int[] var0) {
      if (var0.length == 0) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;

         for (int var3 = 0; var3 < var0.length; var3++) {
            var1 += (var0[var3] - var1) / (var3 + 1);
         }

         return var1;
      }
   }

   public static double avg(long[] var0) {
      if (var0.length == 0) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;

         for (int var3 = 0; var3 < var0.length; var3++) {
            var1 += (var0[var3] - var1) / (var3 + 1);
         }

         return var1;
      }
   }

   public static double avg(float[] var0) {
      if (var0.length == 0) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;

         for (int var3 = 0; var3 < var0.length; var3++) {
            var1 += (var0[var3] - var1) / (var3 + 1);
         }

         return var1;
      }
   }

   public static double avg(double[] var0) {
      if (var0.length == 0) {
         throw new IllegalArgumentException();
      } else {
         double var1 = 0.0;

         for (int var3 = 0; var3 < var0.length; var3++) {
            var1 += (var0[var3] - var1) / (var3 + 1);
         }

         return var1;
      }
   }

   public static boolean isPowerOfTwo(int var0) {
      return var0 > 0 && (var0 & var0 - 1) == 0;
   }

   public static boolean isPowerOfTwo(long var0) {
      return var0 > 0L && (var0 & var0 - 1L) == 0L;
   }

   public static int log2(int var0) {
      if (var0 <= 0L) {
         throw new IllegalArgumentException();
      } else {
         int var1;
         for (var1 = 0; var0 > 1; var1++) {
            var0 >>>= 1;
         }

         return var1;
      }
   }

   public static int log2(long var0) {
      if (var0 <= 0L) {
         throw new IllegalArgumentException();
      } else {
         int var2;
         for (var2 = 0; var0 > 1L; var2++) {
            var0 >>>= 1;
         }

         return var2;
      }
   }

   public static int bitcount(int var0) {
      int var1 = 0;

      while (var0 != 0) {
         if ((var0 & 1) != 0) {
            var1++;
         }

         var0 >>>= 1;
      }

      return var1;
   }

   public static int bitcount(long var0) {
      int var2 = 0;

      while (var0 != 0L) {
         if ((var0 & 1L) != 0L) {
            var2++;
         }

         var0 >>>= 1;
      }

      return var2;
   }
}
