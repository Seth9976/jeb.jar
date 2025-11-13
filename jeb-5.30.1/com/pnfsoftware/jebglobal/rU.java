package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class rU {
   private static final OQ lm = OQ.q(268961284);
   private static final OQ zz = OQ.q(537396740);
   private static final OQ JY = OQ.q(805832196);
   private static final OQ HF = OQ.q(1074267652);
   private static final OQ LK = OQ.q(lm.getFlags() | 1048576);
   private static final OQ io = OQ.q(zz.getFlags() | 1048576);
   private static final OQ qa = OQ.q(JY.getFlags() | 1048576);
   private static final OQ Hk = OQ.q(HF.getFlags() | 1048576);
   private static final de Me = new KB(de.EE.Dw);
   private static final de PV = new YR();
   public static final Je[] q = new Je[]{
      new Qg(0, "STMDA", Pc.lm, rh.q),
      new Qg(1, "LDMDA", Pc.lm, rh.q).nf().q(zz),
      new Qg(2, "STM", Pc.lm, rh.q),
      new Qg(3, "LDM", Pc.lm, rh.q).nf().q(lm),
      new Qg(4, "STMDB", Pc.lm, rh.q),
      new Qg(5, "LDMDB", Pc.lm, rh.q).nf().q(JY),
      new Qg(6, "STMIB", Pc.lm, rh.q),
      new Qg(7, "LDMIB", Pc.lm, rh.q).nf().q(HF),
      new Qg(8, "STMDA", Pc.zz, rh.q).RF(),
      new Qg(9, "LDMDA", Pc.zz, rh.q).nf().q(io).q(QI.LK),
      new Qg(10, "STM", Pc.zz, rh.q).RF(),
      new Qg(11, "LDM", Pc.zz, rh.q).nf().q(LK).q(QI.LK),
      new Qg(12, "STMDB", Pc.zz, rh.q).RF(),
      new Qg(13, "LDMDB", Pc.zz, rh.q).nf().q(qa).q(QI.LK),
      new Qg(14, "STMIB", Pc.zz, rh.q).RF(),
      new Qg(15, "LDMIB", Pc.zz, rh.q).nf().q(Hk).q(QI.LK)
   };
   public static final Je RF = new Qg("POP", rh.q).q(LK);
   public static final Je xK = new Qg("PUSH", rh.q);
   public static final Je Dw = new Qg("PUSH", rh.Dw);
   public static final Je Uv = new Qg("POP", rh.Uv).q(LK);
   public static final Je oW = new Qg("STM", iv.nf, rh.RF);
   public static final Je gO = new Qg("LDM", iv.gP, rh.RF).q(lm);
   public static final Je[] nf = new Je[]{
      new Qg(0, "STM", Pc.lm, rh.xK).q(de.KT),
      new Qg(1, "STM", Pc.zz, rh.xK).RF().q(Me),
      new Qg(2, "LDM", Pc.lm, rh.xK).nf().q(lm).q(PV),
      new Qg(3, "LDM", Pc.zz, rh.xK).nf().q(LK).q(PV).q(QI.LK),
      new Qg(4, "STMDB", Pc.lm, rh.xK),
      new Qg(5, "STMDB", Pc.zz, rh.xK).RF(),
      new Qg(6, "LDMDB", Pc.lm, rh.xK).nf().q(JY),
      new Qg(7, "LDMDB", Pc.zz, rh.xK).nf().q(qa).q(QI.LK)
   };
   public static final Je gP = new Qg("POP", rh.xK).q(LK);
   public static final Je za = new Qg("PUSH", rh.xK);

   public static Je q(byte[] var0) throws ProcessorException {
      if (var0[2] == 0 && var0[3] == 0) {
         return Qg.q(var0, "Arm Load/Store Multiple");
      } else {
         int var1 = HS.Dw(var0, 0);
         int var2 = HS.xK(var0, 3) | HS.q(var0, 2) | HS.RF(var0, 1) | var1;
         if ((var0[0] & 15) == 8 && (var0[1] & 255) == 189) {
            return RF;
         } else if ((var0[0] & 15) == 9 && (var0[1] & 255) == 45) {
            return xK;
         } else {
            return (var0[1] & 64) != 0 && (var1 == 0 || (var0[2] & 128) == 0) && (var0[1] & 32) != 0 ? null : q[var2];
         }
      }
   }

   public static Je RF(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 32) >>> 5;
      int var3 = (var0[1] & 16) >>> 4;
      if (var1 == 0 || var1 == 3) {
         return var3 == 0 && (var0[1] & 15) == 13 && (var0[2] & 255) == 192 && (var0[3] & 224) == 0
               || var3 == 1 && (var0[2] & 255) == 192 && (var0[3] & 255) == 0
            ? oY.lm[(var1 == 0 ? 0 : 4) | var3 << 1 | var2]
            : Qg.q(var0, "Arm Load/Store Multiple");
      } else if (var0[2] == 0 && var0[3] == 0) {
         return Qg.q(var0, "Arm Load/Store Multiple");
      } else if ((var3 != 0 || (var0[2] & 128) == 0) && (var0[2] & 32) == 0) {
         if ((var0[1] & 15) == 13) {
            if (var2 == 1 && var1 == 1 && var3 == 1) {
               return gP;
            }

            if (var2 == 1 && var1 == 2 && var3 == 0) {
               return za;
            }
         }

         int var4 = (var1 == 1 ? 0 : 4) | var3 << 1 | var2;
         return nf[var4];
      } else {
         return Qg.q(var0, "Arm Load/Store Multiple");
      }
   }
}
