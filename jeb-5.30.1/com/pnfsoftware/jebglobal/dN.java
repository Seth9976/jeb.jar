package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public enum dN {
   q(1),
   RF(2),
   xK(3),
   Dw(4),
   Uv(5),
   oW(6),
   gO(7),
   nf(8),
   gP(9),
   za(10),
   lm(20),
   zz(21),
   JY(30),
   HF(40),
   LK(41),
   io(42),
   qa(43),
   Hk(44),
   Me(45),
   PV(46),
   oQ(90),
   xW(99),
   KT(100);

   private static final ILogger Gf = GlobalLog.getLogger(dN.class);
   private final int Ef;

   private dN(int var3) {
      this.Ef = var3;
   }

   public byte q() {
      return (byte)this.Ef;
   }

   public static dN q(int var0) {
      for (dN var4 : values()) {
         if (var4.Ef == var0) {
            return var4;
         }
      }

      Object[] var10000 = new Object[]{var0};
      return null;
   }
}
