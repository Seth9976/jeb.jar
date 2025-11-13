package com.pnfsoftware.jebglobal;

public class HS {
   public static int q(byte[] var0, int var1, int var2, int var3) {
      return var3 >= 0 ? (var0[var1] & var2) >>> var3 : (var0[var1] & var2) << -var3;
   }

   public static int q(byte[] var0, int var1) {
      return q(var0, 0, 1, -var1);
   }

   public static int RF(byte[] var0, int var1) {
      return q(var0, 1, 128, 7 - var1);
   }

   public static int xK(byte[] var0, int var1) {
      return q(var0, 1, 32, 5 - var1);
   }

   public static int Dw(byte[] var0, int var1) {
      return q(var0, 1, 16, 4 - var1);
   }

   public static int Uv(byte[] var0, int var1) {
      return ((var0[0] & 240) == 240 ? 1 : 0) << var1;
   }

   public static int oW(byte[] var0, int var1) {
      return ((var0[1] & 15) == 15 ? 1 : 0) << var1;
   }
}
