package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.format.Strings;

public class u {
   public static long pC(int var0, int var1, int var2, int var3) throws ProcessorException {
      return pC(var0, var1, var2, true, var3).pC;
   }

   public static u.Av A(int var0, int var1, int var2, int var3) throws ProcessorException {
      return pC(var0, var1, var2, false, var3);
   }

   private static u.Av pC(int var0, int var1, int var2, boolean var3, int var4) throws ProcessorException {
      int var5 = pC(var0 << 6 | ~var1 & 63, 7);
      if (var5 < 1) {
         throw new ProcessorException(Strings.f("Reserved Value for Bitmask: %xh", var0 << 12 | var1 << 6 | var2));
      } else {
         int var6 = (int)Gq.kS(var5);
         if (var3 && (var1 & var6) == var6) {
            throw new ProcessorException(Strings.f("Reserved Value for Bitmask: %xh", var0 << 12 | var1 << 6 | var2));
         } else {
            int var7 = var1 & var6;
            int var8 = var2 & var6;
            int var9 = 1 << var5;
            long var10 = Gq.kS(var7 + 1);
            u.Av var12 = new u.Av();
            var12.pC = Gq.pC(pC(var10, var9, var8), var9, var4);
            if (!var3) {
               int var13 = var7 - var8;
               int var14 = var13 & var9 - 1;
               long var15 = Gq.kS(var14 + 1);
               var12.A = Gq.pC(var15, var9, var4);
            }

            return var12;
         }
      }
   }

   private static long pC(long var0, int var2, int var3) {
      long var4 = 1L << var2 - 1;

      while (var3 != 0) {
         if (var3 > 0) {
            long var6 = var0 & 1L;
            var0 = var0 >>> 1 | (var6 == 0L ? 0L : var4);
            var3--;
         } else {
            long var8 = var0 & var4;
            var0 = var0 << 1 | (var8 == 0L ? 0 : 1);
            var3++;
         }
      }

      return var0;
   }

   private static int pC(int var0, int var1) {
      for (int var2 = var1 - 1; var2 != 0; var2--) {
         if ((var0 & 1 << var2) != 0) {
            return var2;
         }
      }

      return -1;
   }

   public static int pC(lw var0) {
      String var1 = var0.pC();
      switch (var1) {
         case "B":
            return 8;
         case "H":
            return 16;
         case "S":
            return 32;
         case "D":
            return 64;
         default:
            return A(var0);
      }
   }

   public static int A(lw var0) {
      String var1 = var0.pC();
      switch (var1) {
         case "4B":
         case "2H":
            return 32;
         case "8B":
         case "4H":
         case "2S":
         case "1D":
            return 64;
         case "16B":
         case "8H":
         case "4S":
         case "2D":
         case "1Q":
            return 128;
         case "/Z":
         case "/M":
            return var0.getOperandBitsize();
         default:
            return -1;
      }
   }

   public static int kS(lw var0) {
      String var1 = var0.pC();
      switch (var1) {
         case "8B":
         case "16B":
            return 1;
         case "4H":
         case "8H":
            return 2;
         case "2S":
         case "4S":
            return 4;
         case "1D":
         case "2D":
            return 8;
         default:
            return -1;
      }
   }

   public static int wS(lw var0) {
      String var1 = var0.pC();
      switch (var1) {
         case "1D":
            return 1;
         case "2D":
         case "2S":
            return 2;
         case "4S":
         case "4H":
            return 4;
         case "8B":
         case "8H":
            return 8;
         case "16B":
            return 16;
         default:
            return -1;
      }
   }

   public static class Av {
      public long pC;
      public long A;
   }
}
