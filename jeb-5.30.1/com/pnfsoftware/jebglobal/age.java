package com.pnfsoftware.jebglobal;

public class age {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 16;
   public static final int oW = 32;
   public static final int gO = 64;
   public static final int nf = 64;
   public static final int gP = 128;
   public static final int za = 128;
   public static final int lm = 256;
   public static final int zz = 512;
   public static final int JY = 1024;
   public static final int HF = 2048;
   public static final int LK = 4096;
   public static final int io = 8192;
   public static final int qa = 16384;
   public static final int Hk = 65536;
   public static final int Me = 131072;
   public static final int PV = 268435456;
   public static final int oQ = 536870912;
   public static final int xW = 30239;
   public static final int KT = 20703;
   public static final int Gf = 204287;

   public static int q(int var0) {
      int var1 = 0;
      var1 |= q(var0, 1, 1);
      var1 |= q(var0, 2, 2);
      var1 |= q(var0, 4, 4);
      var1 |= q(var0, 8, 8);
      var1 |= q(var0, 16, 16);
      var1 |= q(var0, 64, 64);
      var1 |= q(var0, 128, 128);
      var1 |= q(var0, 512, 512);
      var1 |= q(var0, 1024, 1024);
      var1 |= q(var0, 4096, 4096);
      var1 |= q(var0, 16384, 16384);
      var1 |= q(var0, 65536, 65536);
      var1 |= q(var0, 536870912, 536870912);
      return var1 | q(var0, 268435456, 268435456);
   }

   private static int q(int var0, int var1, int var2) {
      return (var0 & var1) != 0 ? var2 : 0;
   }
}
