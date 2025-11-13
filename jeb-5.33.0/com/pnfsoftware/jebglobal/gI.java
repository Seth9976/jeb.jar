package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class gI {
   private static final hg oT = hg.pC(268961284);
   private static final hg fI = hg.pC(537396740);
   private static final hg WR = hg.pC(805832196);
   private static final hg NS = hg.pC(1074267652);
   private static final hg vP = hg.pC(oT.getFlags() | 1048576);
   private static final hg xC = hg.pC(fI.getFlags() | 1048576);
   private static final hg ED = hg.pC(WR.getFlags() | 1048576);
   private static final hg Sc = hg.pC(NS.getFlags() | 1048576);
   private static final ji ah = new gR(ji.DH.wS);
   private static final ji eP = new Fm();
   public static final tz[] pC = new tz[]{
      new UC(0, "STMDA", wT.ys, OO.pC),
      new UC(1, "LDMDA", wT.ys, OO.pC).ys().pC(fI),
      new UC(2, "STM", wT.ys, OO.pC),
      new UC(3, "LDM", wT.ys, OO.pC).ys().pC(oT),
      new UC(4, "STMDB", wT.ys, OO.pC),
      new UC(5, "LDMDB", wT.ys, OO.pC).ys().pC(WR),
      new UC(6, "STMIB", wT.ys, OO.pC),
      new UC(7, "LDMIB", wT.ys, OO.pC).ys().pC(NS),
      new UC(8, "STMDA", wT.ld, OO.pC).A(),
      new UC(9, "LDMDA", wT.ld, OO.pC).ys().pC(xC).pC(cT.vP),
      new UC(10, "STM", wT.ld, OO.pC).A(),
      new UC(11, "LDM", wT.ld, OO.pC).ys().pC(vP).pC(cT.vP),
      new UC(12, "STMDB", wT.ld, OO.pC).A(),
      new UC(13, "LDMDB", wT.ld, OO.pC).ys().pC(ED).pC(cT.vP),
      new UC(14, "STMIB", wT.ld, OO.pC).A(),
      new UC(15, "LDMIB", wT.ld, OO.pC).ys().pC(Sc).pC(cT.vP)
   };
   public static final tz A = new UC("POP", OO.pC).pC(vP);
   public static final tz kS = new UC("PUSH", OO.pC);
   public static final tz wS = new UC("PUSH", OO.wS);
   public static final tz UT = new UC("POP", OO.UT).pC(vP);
   public static final tz E = new UC("STM", LY.UT, OO.A);
   public static final tz sY = new UC("LDM", LY.E, OO.A).pC(oT);
   public static final tz[] ys = new tz[]{
      new UC(0, "STM", wT.ys, OO.kS).pC(ji.rl),
      new UC(1, "STM", wT.ld, OO.kS).A().pC(ah),
      new UC(2, "LDM", wT.ys, OO.kS).ys().pC(oT).pC(eP),
      new UC(3, "LDM", wT.ld, OO.kS).ys().pC(vP).pC(eP).pC(cT.vP),
      new UC(4, "STMDB", wT.ys, OO.kS),
      new UC(5, "STMDB", wT.ld, OO.kS).A(),
      new UC(6, "LDMDB", wT.ys, OO.kS).ys().pC(WR),
      new UC(7, "LDMDB", wT.ld, OO.kS).ys().pC(ED).pC(cT.vP)
   };
   public static final tz ld = new UC("POP", OO.kS).pC(vP);
   public static final tz gp = new UC("PUSH", OO.kS);

   public static tz pC(byte[] var0) throws ProcessorException {
      if (var0[2] == 0 && var0[3] == 0) {
         return UC.pC(var0, "Arm Load/Store Multiple");
      } else {
         int var1 = ZC.wS(var0, 0);
         int var2 = ZC.kS(var0, 3) | ZC.pC(var0, 2) | ZC.A(var0, 1) | var1;
         if ((var0[0] & 15) == 8 && (var0[1] & 255) == 189) {
            return A;
         } else if ((var0[0] & 15) == 9 && (var0[1] & 255) == 45) {
            return kS;
         } else {
            return (var0[1] & 64) != 0 && (var1 == 0 || (var0[2] & 128) == 0) && (var0[1] & 32) != 0 ? null : pC[var2];
         }
      }
   }

   public static tz A(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 32) >>> 5;
      int var3 = (var0[1] & 16) >>> 4;
      if (var1 == 0 || var1 == 3) {
         return var3 == 0 && (var0[1] & 15) == 13 && (var0[2] & 255) == 192 && (var0[3] & 224) == 0
               || var3 == 1 && (var0[2] & 255) == 192 && (var0[3] & 255) == 0
            ? bb.oT[(var1 == 0 ? 0 : 4) | var3 << 1 | var2]
            : UC.pC(var0, "Arm Load/Store Multiple");
      } else if (var0[2] == 0 && var0[3] == 0) {
         return UC.pC(var0, "Arm Load/Store Multiple");
      } else if ((var3 != 0 || (var0[2] & 128) == 0) && (var0[2] & 32) == 0) {
         if ((var0[1] & 15) == 13) {
            if (var2 == 1 && var1 == 1 && var3 == 1) {
               return ld;
            }

            if (var2 == 1 && var1 == 2 && var3 == 0) {
               return gp;
            }
         }

         int var4 = (var1 == 1 ? 0 : 4) | var3 << 1 | var2;
         return ys[var4];
      } else {
         return UC.pC(var0, "Arm Load/Store Multiple");
      }
   }
}
