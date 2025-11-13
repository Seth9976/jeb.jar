package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public enum lz {
   pC(1),
   A(2),
   kS(3),
   wS(4),
   UT(5),
   E(6),
   sY(7),
   ys(8),
   ld(9),
   gp(10),
   oT(20),
   fI(21),
   WR(30),
   NS(40),
   vP(41),
   xC(42),
   ED(43),
   Sc(44),
   ah(45),
   eP(46),
   UO(90),
   Ab(99),
   rl(100);

   private static final ILogger z = GlobalLog.getLogger(lz.class);
   private final int Ek;

   private lz(int var3) {
      this.Ek = var3;
   }

   public byte pC() {
      return (byte)this.Ek;
   }

   public static lz pC(int var0) {
      for (lz var4 : values()) {
         if (var4.Ek == var0) {
            return var4;
         }
      }

      Object[] var10000 = new Object[]{var0};
      return null;
   }
}
