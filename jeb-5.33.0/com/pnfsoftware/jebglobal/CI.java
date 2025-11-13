package com.pnfsoftware.jebglobal;

public enum CI {
   pC(-1),
   A(0),
   kS(1),
   wS(2),
   UT(3),
   E(4);

   private final int sY;

   private CI(int var3) {
      this.sY = var3;
   }

   public static CI pC(int var0) {
      for (CI var4 : values()) {
         if (var4.sY == var0) {
            return var4;
         }
      }

      return pC;
   }
}
