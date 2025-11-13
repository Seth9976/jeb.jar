package com.pnfsoftware.jebglobal;

public class ZC {
   public static int pC(byte[] var0, int var1, int var2, int var3) {
      return var3 >= 0 ? (var0[var1] & var2) >>> var3 : (var0[var1] & var2) << -var3;
   }

   public static int pC(byte[] var0, int var1) {
      return pC(var0, 0, 1, -var1);
   }

   public static int A(byte[] var0, int var1) {
      return pC(var0, 1, 128, 7 - var1);
   }

   public static int kS(byte[] var0, int var1) {
      return pC(var0, 1, 32, 5 - var1);
   }

   public static int wS(byte[] var0, int var1) {
      return pC(var0, 1, 16, 4 - var1);
   }

   public static int UT(byte[] var0, int var1) {
      return ((var0[0] & 240) == 240 ? 1 : 0) << var1;
   }

   public static int E(byte[] var0, int var1) {
      return ((var0[1] & 15) == 15 ? 1 : 0) << var1;
   }
}
