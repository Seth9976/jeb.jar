package com.pnfsoftware.jebglobal;

import java.util.ArrayDeque;
import java.util.Deque;

public class Lf {
   public static boolean q(mZ var0) {
      return var0 != null && var0.Dw();
   }

   public static int q(byte[] var0) {
      return (var0[1] & 240) >>> 4;
   }

   public static Deque RF(byte[] var0) {
      return q(var0[1]);
   }

   public static Deque q(byte var0) {
      int var1 = var0 & 15;
      int var2 = (var0 & 16) >>> 4;
      boolean var3 = false;
      ArrayDeque var4 = new ArrayDeque();

      for (int var5 = 1; var5 <= 4; var5++) {
         if (var3) {
            var4.push(var1 % 2 == var2 ? Boolean.TRUE : Boolean.FALSE);
         } else if (var1 % 2 != 0) {
            var3 = true;
         }

         var1 >>>= 1;
      }

      return var4;
   }

   public static boolean xK(byte[] var0) {
      return var0[0] == -65 && (var0[1] & 15) != 0;
   }
}
