package com.pnfsoftware.jebglobal;

public enum yv {
   pC(0),
   A(1),
   kS(2),
   wS(3),
   UT(4),
   E(5),
   sY(6),
   ys(6),
   ld(7),
   gp(8),
   oT(9),
   fI(10),
   WR(11),
   NS(12),
   vP(13),
   xC(14),
   ED(15),
   Sc(16),
   ah(17),
   eP(18),
   UO(19),
   Ab(20),
   rl(21),
   z(22),
   Ek(23),
   hK(24),
   Er(25),
   FE(26),
   Aj(27),
   EX(28),
   LM(29),
   mv(30);

   private final int sO;

   private yv(int var3) {
      this.sO = var3;
   }

   public int pC() {
      return this.sO;
   }

   public static yv pC(int var0) {
      for (yv var4 : values()) {
         if (var4.sO == var0) {
            return var4;
         }
      }

      return pC;
   }
}
