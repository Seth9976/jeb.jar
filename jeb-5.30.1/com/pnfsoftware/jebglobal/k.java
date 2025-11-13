package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

public class k {
   public static int q(int var0, int var1) {
      return var1 * (var0 / var1);
   }

   public static long q(long var0, int var2) {
      return var2 * (var0 / var2);
   }

   public static int q(byte[] var0, IEncodedMemoryArea var1) {
      return EncodedMemoryAreaUtil.signExtendInt(var0, var1);
   }

   public static int RF(byte[] var0, IEncodedMemoryArea var1) {
      return EncodedMemoryAreaUtil.zeroExtend(var0, var1);
   }

   public static long xK(byte[] var0, IEncodedMemoryArea var1) {
      return EncodedMemoryAreaUtil.signExtendLong(var0, var1);
   }

   public static long q(byte[] var0, int var1, int var2) {
      return EncodedMemoryAreaUtil.zeroExtend(var0, var1, var2);
   }

   public static int RF(int var0, int var1) {
      return Integer.rotateRight(var1, 2 * var0);
   }

   public static int xK(int var0, int var1) {
      switch (var0) {
         case 0:
            return var1;
         case 1:
            return var1 << 16 | var1;
         case 2:
            return var1 << 24 | var1 << 8;
         case 3:
            return var1 << 24 | var1 << 16 | var1 << 8 | var1;
         default:
            int var2 = 128 | var1;
            return Integer.rotateRight(var2, var0 << 1 | (var1 & 128) >>> 7);
      }
   }

   public static long q(int var0, int var1, long var2) {
      int var4 = (var1 >>> 1) * 8;
      if (var1 < 8) {
         return var2 << var4;
      } else if (var1 < 12) {
         var4 = var1 < 10 ? 0 : 8;
         return var2 << var4;
      } else if (var1 == 12) {
         byte var13 = 8;
         return var2 << var13 | 255L;
      } else if (var1 == 13) {
         byte var12 = 16;
         return var2 << var12 | 65535L;
      } else if (var0 == 0) {
         if (var1 == 14) {
            return var2;
         } else {
            int var15 = (int)(var2 & 1L);
            int var6 = (int)(var2 & 1L);
            int var16 = ~var6 & 1;
            long var17 = (var15 << 12 | var16 << 11 | var6 << 10 | var6 << 9 | var6 << 8 | var6 << 7 | var2 & 127L) << 3;
            return var17 << 16;
         }
      } else if (var1 != 14) {
         return 0L;
      } else {
         long var5 = 0L;

         for (int var7 = 0; var7 < 8; var7++) {
            long var8 = (int)((var2 & 1 << var7) >>> var7);
            int var10 = var7 * 8;

            for (int var11 = 0; var11 < 8; var11++) {
               var5 |= var8 << var10 + var11;
            }
         }

         return var5;
      }
   }

   public static double q(int var0) {
      int var1 = (var0 & 128) >> 7;
      int var2 = (var0 & 64) >> 6;
      int var3 = ~var2 & 1;
      int var4 = (var0 & 48) >>> 4;
      double var5 = (16.0 + (var0 & 15)) / 16.0;
      return (var1 == 0 ? 1.0 : -1.0) * Math.pow(2.0, (var3 << 2 | var4) - 3) * var5;
   }

   public static int RF(int var0) {
      int var1 = (var0 & 127) << 3;
      int var2 = (var0 & 128) >> 7;
      int var3 = (var0 & 64) >> 6;
      int var4 = ~var3 & 1;
      return var1 | var2 << 15 | var4 << 14 | var3 << 13 | var3 << 12 | var3 << 11 | var3 << 10;
   }

   public static long q(long var0, int var2, int var3) {
      if (var3 % var2 != 0) {
         throw new RuntimeException("Replicate can not operate");
      } else {
         return RF(var0, var2, var3 / var2);
      }
   }

   public static long RF(long var0, int var2, int var3) {
      long var4 = 0L;

      for (int var6 = 0; var6 < var3; var6++) {
         var4 |= var0 << var6 * var2;
      }

      return var4;
   }

   public static long xK(int var0) {
      return MathUtil.makeMask(var0);
   }

   public static int Dw(int var0) {
      int var1 = 0;

      while (var0 != 0) {
         var1 += var0 & 1;
         var0 >>>= 1;
      }

      return var1;
   }

   public static int q(long var0) {
      int var2;
      for (var2 = -1; var0 != 0L; var2++) {
         var0 >>>= 1;
      }

      return var2;
   }

   public static int RF(long var0) {
      int var2 = -1;
      if (var0 == 0L) {
         return var2;
      } else {
         var2++;

         while ((var0 & 1L) == 0L) {
            var2++;
            var0 >>>= 1;
         }

         return var2;
      }
   }

   public static int q(Op var0) {
      String var1 = var0.oW();
      switch (var1) {
         case "8":
         case "S8":
         case "U8":
         case "I8":
            return 8;
         case "16":
         case "S16":
         case "U16":
         case "I16":
         case "F16":
         case "BF16":
            return 16;
         case "32":
         case "I32":
         case "F32":
         default:
            return 32;
         case "I64":
         case "F64":
            return 64;
      }
   }

   public static int RF(Op var0) {
      return Integer.parseInt(var0.oW()) >>> 3;
   }

   public static int xK(Op var0) {
      String var1 = var0.oW();
      switch (var1) {
         case "8":
            return 8;
         case "16":
            return 4;
         case "32":
            return 2;
         default:
            return 1;
      }
   }

   public static boolean Dw(Op var0) {
      return var0.oW() != null && var0.oW().startsWith("S");
   }
}
