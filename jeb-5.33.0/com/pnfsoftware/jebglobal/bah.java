package com.pnfsoftware.jebglobal;

public enum bah {
   pC(1),
   A(3),
   kS(4),
   wS(5),
   UT(6),
   E(7),
   sY(8),
   ys(9),
   ld(10),
   gp(11),
   oT(12),
   fI(15),
   WR(16),
   NS(18);

   private final int vP;

   private bah(int var3) {
      this.vP = var3;
   }

   public static bah pC(int var0) {
      for (bah var4 : values()) {
         if (var4.vP == var0) {
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
