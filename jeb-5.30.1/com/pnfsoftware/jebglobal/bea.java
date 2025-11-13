package com.pnfsoftware.jebglobal;

public enum bea {
   q(1),
   RF(3),
   xK(4),
   Dw(5),
   Uv(6),
   oW(7),
   gO(8),
   nf(9),
   gP(10),
   za(11),
   lm(12),
   zz(15),
   JY(16),
   HF(18);

   public static final int LK = 1;
   public static final int io = 3;
   public static final int qa = 4;
   public static final int Hk = 5;
   public static final int Me = 6;
   public static final int PV = 7;
   public static final int oQ = 8;
   public static final int xW = 9;
   public static final int KT = 10;
   public static final int Gf = 11;
   public static final int Ef = 12;
   public static final int cC = 15;
   public static final int sH = 16;
   public static final int CE = 18;
   private final int wF;

   private bea(int var3) {
      this.wF = var3;
   }

   public static bea q(int var0) {
      for (bea var4 : values()) {
         if (var4.wF == var0) {
            return var4;
         }
      }

      throw new RuntimeException();
   }

   @Override
   public String toString() {
      return "CONSTANT_" + super.toString();
   }
}
