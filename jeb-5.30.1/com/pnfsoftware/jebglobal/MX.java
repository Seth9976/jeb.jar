package com.pnfsoftware.jebglobal;

public class MX {
   public static final long q = 16L;
   public static final long RF = 32L;
   public static final long xK = 64L;
   public static final long Dw = 128L;
   public static final long Uv = 256L;
   public static final long oW = 512L;
   public static final long gO = 768L;
   public static final long nf = 1024L;
   public static final long gP = 1280L;
   public static final dD za = new dD(16L);
   public static final dD lm = new dD(32L);
   public static final dD zz = new dD(64L);
   public static final dD JY = new dD(128L);
   public static final dD HF = new dD(256L);
   public static final dD LK = new dD(768L);
   public static final dD io = new dD(1024L);
   public static final dD qa = new dD(1280L);

   public static boolean q() {
      return true;
   }

   public static boolean q(byte[] var0) {
      return (var0[2] & 3) == 1;
   }

   public static boolean RF(byte[] var0) {
      return q() && (var0[0] & 240) == 224;
   }
}
