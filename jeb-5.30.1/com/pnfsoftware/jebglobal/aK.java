package com.pnfsoftware.jebglobal;

public enum aK {
   q(0),
   RF(1),
   xK(2),
   Dw(3),
   Uv(4),
   oW(5),
   gO(6),
   nf(6),
   gP(7),
   za(8),
   lm(9),
   zz(10),
   JY(11),
   HF(12),
   LK(13),
   io(14),
   qa(15),
   Hk(16),
   Me(17),
   PV(18),
   oQ(19),
   xW(20),
   KT(21),
   Gf(22),
   Ef(23),
   cC(24),
   sH(25),
   CE(26),
   wF(27),
   If(28),
   Dz(29),
   mI(30);

   private final int jq;

   private aK(int var3) {
      this.jq = var3;
   }

   public int q() {
      return this.jq;
   }

   public static aK q(int var0) {
      for (aK var4 : values()) {
         if (var4.jq == var0) {
            return var4;
         }
      }

      return q;
   }
}
