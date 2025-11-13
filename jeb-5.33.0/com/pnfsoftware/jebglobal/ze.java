package com.pnfsoftware.jebglobal;

public class ze {
   public static final ZW pC = new ZW(16L);
   public static final ZW A = new ZW(32L);
   public static final ZW kS = new ZW(64L);
   public static final ZW wS = new ZW(128L);
   public static final ZW UT = new ZW(256L);
   public static final ZW E = new ZW(768L);
   public static final ZW sY = new ZW(1024L);
   public static final ZW ys = new ZW(1280L);

   public static boolean pC() {
      return true;
   }

   public static boolean pC(byte[] var0) {
      return (var0[2] & 3) == 1;
   }

   public static boolean A(byte[] var0) {
      return pC() && (var0[0] & 240) == 224;
   }
}
