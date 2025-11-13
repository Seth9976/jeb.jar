package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jebglobal.chk;

class uX {
   static int pC(byte[] var0, int var1) {
      return var0[var1] & 0xFF;
   }

   static int A(byte[] var0, int var1) {
      return EndianUtil.littleEndianBytesToInt(var0, var1);
   }

   static long kS(byte[] var0, int var1) {
      return EndianUtil.littleEndianBytesToLong(var0, var1);
   }

   static uX.Av wS(byte[] var0, int var1) {
      uX.Av var2 = ys(var0, var1);
      if (var2.A > 5) {
         throw new RuntimeException();
      } else {
         var2.pC = MathUtil.signExtend(var2.pC, 32);
         return var2;
      }
   }

   static uX.Av UT(byte[] var0, int var1) {
      return ys(var0, var1);
   }

   static uX.Av E(byte[] var0, int var1) {
      uX.Av var2 = sY(var0, var1);
      if (var2.A > 5) {
         throw new RuntimeException();
      } else {
         if (var2.pC > 4294967295L) {
            var2.pC &= 4294967295L;
         }

         return var2;
      }
   }

   static uX.Av sY(byte[] var0, int var1) {
      long var2 = 0L;
      byte var4 = -1;
      byte var5 = 0;

      int var6;
      for (var6 = 0; var4 < 0; var6++) {
         if (var6 == 10) {
            throw new chk("Illegal ULEB128-encoded integer", var1 + var6);
         }

         var4 = var0[var1 + var6];
         var2 |= (long)(var4 & 127) << var5;
         var5 += 7;
      }

      return new uX.Av(var2, var6);
   }

   static uX.Av ys(byte[] var0, int var1) {
      long var2 = 0L;
      byte var4 = -1;
      int var5 = 0;

      for (byte var6 = 0; var4 < 0; var5++) {
         if (var5 == 10) {
            throw new chk("Illegal SLEB128-encoded integer", var1 + var5);
         }

         var4 = var0[var1 + var5];
         var2 |= (long)(var4 & 127) << var6;
         var6 += 7;
      }

      if ((var4 & 64) != 0) {
         if (var5 == 1) {
            var2 |= -128L;
         } else if (var5 == 2) {
            var2 |= -16384L;
         } else if (var5 == 3) {
            var2 |= -2097152L;
         } else if (var5 == 4) {
            var2 |= -268435456L;
         } else if (var5 == 5) {
            var2 |= -34359738368L;
         } else if (var5 == 6) {
            var2 |= -4398046511104L;
         } else if (var5 == 7) {
            var2 |= -562949953421312L;
         } else if (var5 == 8) {
            var2 |= -72057594037927936L;
         } else if (var5 == 9) {
            var2 |= Long.MIN_VALUE;
         }
      }

      return new uX.Av(var2, var5);
   }

   static class Av {
      long pC;
      int A;

      Av(long var1, int var3) {
         this.pC = var1;
         this.A = var3;
      }
   }
}
