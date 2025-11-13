package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

class GD {
   private static final IEncodedMemoryArea q = DirectEncodedMemoryArea.get(22, 2);
   private static final dD RF = new dD(q, 0L, 0L, 1L, 16L);
   private static final Je[] xK = new Je[]{new Qg("FCCMP", XD.Hk, XD.PV, go.zz, wJ.RF), new Qg("FCCMPE", XD.Hk, XD.PV, go.zz, wJ.RF)};
   private static final Je Dw = new Qg("FCSEL", XD.qa, XD.Hk, XD.PV, wJ.RF);
   private static final dD Uv = new dD(DirectEncodedMemoryArea.get(22, 2), 32, 64, 0, 16);
   private static final Ef oW = Nd.q(Uv);
   private static final Je[] gO = new Je[]{new Qg("FCMP", XD.Hk, XD.PV), new Qg("FCMP", XD.Hk, oW), new Qg("FCMPE", XD.Hk, XD.PV), new Qg("FCMPE", XD.Hk, oW)};
   private static final Ef nf = new jO(DirectEncodedMemoryArea.get(10, 6), 64);
   private static final Je[] gP = new Je[]{
      new Qg("SCVTF", XD.qa, YH.mI, nf), new Qg("UCVTF", XD.qa, YH.mI, nf), new Qg("FCVTZS", YH.Dz, XD.Hk, nf), new Qg("FCVTZU", YH.Dz, XD.Hk, nf)
   };
   private static final Je[] za = new Je[]{
      new Qg("FCVTNS", YH.Dz, XD.Rv).q(RF),
      new Qg("FCVTNU", YH.Dz, XD.Rv).q(RF),
      new Qg("SCVTF", XD.YN, YH.mI).q(RF),
      new Qg("UCVTF", XD.YN, YH.mI).q(RF),
      new Qg("FCVTAS", YH.Dz, XD.Rv).q(RF),
      new Qg("FCVTAU", YH.Dz, XD.Rv).q(RF),
      new Qg("FMOV", YH.Dz, XD.Rv).q(RF),
      new Qg("FMOV", XD.YN, YH.mI).q(RF)
   };
   private static final Je[] lm = new Je[]{
      new Qg("FMOV", YH.Uv, new VP(XD.q(5, Dm.Dw), VirtualEncodedMemoryArea._1)), new Qg("FMOV", new VP(XD.q(0, Dm.Dw), VirtualEncodedMemoryArea._1), YH.nf)
   };
   private static final Je[] zz = new Je[]{
      null,
      null,
      new Qg("FCVTPS", YH.Dz, XD.Rv).q(RF),
      new Qg("FCVTPU", YH.Dz, XD.Rv).q(RF),
      new Qg("FCVTMS", YH.Dz, XD.Rv).q(RF),
      new Qg("FCVTMU", YH.Dz, XD.Rv).q(RF),
      new Qg("FCVTZS", YH.Dz, XD.Rv).q(RF),
      new Qg("FCVTZU", YH.Dz, XD.Rv).q(RF)
   };
   private static final Je JY = new Qg("FJCVTZS", YH.Dz, XD.Hk).q(QJ.YA);
   private static final Je[] HF = new Je[]{
      new Qg("FMADD", XD.qa, XD.Hk, XD.PV, XD.Me),
      new Qg("FMSUB", XD.qa, XD.Hk, XD.PV, XD.Me),
      new Qg("FNMADD", XD.qa, XD.Hk, XD.PV, XD.Me),
      new Qg("FNMSUB", XD.qa, XD.Hk, XD.PV, XD.Me)
   };
   private static final Je[] LK = new Je[]{
      new Qg("FMUL", XD.qa, XD.Hk, XD.PV),
      new Qg("FDIV", XD.qa, XD.Hk, XD.PV),
      new Qg("FADD", XD.qa, XD.Hk, XD.PV),
      new Qg("FSUB", XD.qa, XD.Hk, XD.PV),
      new Qg("FMAX", XD.qa, XD.Hk, XD.PV),
      new Qg("FMIN", XD.qa, XD.Hk, XD.PV),
      new Qg("FMAXNM", XD.qa, XD.Hk, XD.PV),
      new Qg("FMINNM", XD.qa, XD.Hk, XD.PV)
   };
   private static final Je io = new Qg("FNMUL", XD.qa, XD.Hk, XD.PV);
   private static final Je[] qa = new Je[]{
      new Qg("FMOV", XD.YN, new ub(32, DirectEncodedMemoryArea.get(13, 8))),
      new Qg("FMOV", XD.YN, new ub(64, DirectEncodedMemoryArea.get(13, 8))),
      null,
      new Qg("FMOV", XD.YN, new ub(16, DirectEncodedMemoryArea.get(13, 8))).q(RF)
   };
   private static final Je[] Hk = new Je[]{
      new Qg(0, "FMOV", XD.YN, XD.Rv).q(RF),
      new Qg(1, "FABS", XD.YN, XD.Rv).q(RF),
      new Qg(2, "FNEG", XD.YN, XD.Rv).q(RF),
      new Qg(3, "FSQRT", XD.YN, XD.Rv).q(RF),
      null,
      null,
      null,
      null,
      new Qg(8, "FRINTN", XD.YN, XD.Rv).q(RF),
      new Qg(9, "FRINTP", XD.YN, XD.Rv).q(RF),
      new Qg(10, "FRINTM", XD.YN, XD.Rv).q(RF),
      new Qg(11, "FRINTZ", XD.YN, XD.Rv).q(RF),
      new Qg(12, "FRINTA", XD.YN, XD.Rv).q(RF),
      null,
      new Qg(14, "FRINTX", XD.YN, XD.Rv).q(RF),
      new Qg(15, "FRINTI", XD.YN, XD.Rv).q(RF),
      new Qg(16, "FRINT32Z", XD.qa, XD.Hk).q(QJ.cR),
      new Qg(17, "FRINT32X", XD.qa, XD.Hk).q(QJ.cR),
      new Qg(18, "FRINT64Z", XD.qa, XD.Hk).q(QJ.cR),
      new Qg(19, "FRINT64X", XD.qa, XD.Hk).q(QJ.cR)
   };
   private static final Je[] Me = new Je[]{
      null,
      new Qg("FCVT", XD.HF, XD.Hk),
      null,
      new Qg("FCVT", XD.gP, XD.Hk),
      new Qg("FCVT", XD.lm, XD.Hk),
      null,
      new Qg("BFCVT", XD.gP, XD.zz).q(QJ.eC),
      new Qg("FCVT", XD.gP, XD.Hk),
      null,
      null,
      null,
      null,
      new Qg("FCVT", XD.lm, XD.za),
      new Qg("FCVT", XD.HF, XD.za),
      null,
      null
   };

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 120) >>> 3;
      int var3 = (var0[2] & 252) >>> 2;
      int var4 = (var0[0] & 128) >>> 7;
      int var6 = (var0[0] & 32) >>> 5;
      int var7 = (var0[1] & 192) >>> 6;
      if (var1 > 1) {
         return var4 != 1 && var6 != 1 && var7 <= 1
            ? HF[(var0[1] & 32) >>> 4 | (var0[2] & 128) >>> 7]
            : Qg.q(var0, "Floating-point data-processing (3 source)");
      } else if ((var2 & 4) == 0) {
         int var17 = var0[1] & 7;
         int var19 = (var0[1] & 24) >>> 3;
         int var10 = (var0[2] & 252) >>> 2;
         if (var6 != 1 && var7 <= 1 && var17 <= 3 && (var19 == 3 || var17 >= 2) && (var19 == 0 || var17 < 2) && (var4 != 0 || var10 >= 32)) {
            int var11 = (var19 == 0 ? 0 : 2) | var17 & 1;
            return gP[var11];
         } else {
            return Qg.q(var0, "Conversion between floating-point and fixed-point");
         }
      } else if (var3 == 0) {
         int var16 = var0[1] & 7;
         int var18 = (var0[1] & 24) >>> 3;
         if (var18 != 0) {
            if (var7 == 1 && var16 == 6 && var18 == 3 && var4 == 0 && var6 == 0) {
               return JY;
            } else {
               if (var7 == 2) {
                  if (var18 == 1 && var16 >= 6) {
                     return lm[var16 & 1];
                  }
               } else if (var16 < 2) {
                  return zz[var18 << 1 | var16];
               }

               return Qg.q(var0, "Conversion between floating-point and integer");
            }
         } else {
            return var16 < 6 || (var4 != 0 || var7 != 1) && (var4 == 0 || var7 != 0) ? za[var16] : Qg.q(var0, "Conversion between floating-point and integer");
         }
      } else if ((var3 & 1) != 0) {
         int var15 = (var0[3] & 16) >>> 4;
         if ((var3 & 2) == 0) {
            return var4 != 1 && var6 != 1 && var7 <= 1 ? xK[var15] : Qg.q(var0, "Floating-point conditional compare");
         } else {
            return var4 != 1 && var6 != 1 && var7 <= 1 ? Dw : Qg.q(var0, "Floating-point conditional select");
         }
      } else if ((var3 & 2) != 0) {
         int var14 = (var0[2] & 240) >>> 4;
         if (var4 == 1 || var6 == 1 || var7 > 1 || var14 > 8) {
            return Qg.q(var0, "Floating-point data-processing (2 source)");
         } else {
            return var14 == 8 ? io : LK[var14 & 7];
         }
      } else if ((var3 & 4) != 0) {
         int var13 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var0);
         return var4 != 1 && var6 != 1 && var13 == 0 ? qa[var7] : Qg.q(var0, "Floating-point immediate");
      } else if ((var3 & 8) != 0) {
         int var12 = (var0[2] & 192) >>> 6;
         int var9 = var0[3] & 31;
         return var4 != 1 && var6 != 1 && var7 <= 1 && var12 == 0 && (var9 & 7) == 0 ? gO[var9 >>> 3] : Qg.q(var0, "Floating-point compare");
      } else if ((var3 & 16) != 0) {
         int var8 = DirectEncodedMemoryArea.get(15, 6).decodeInt(var0);
         if (var4 != 0 || var6 != 0 || var7 == 2 || var8 >= 20) {
            return Qg.q(var0, "Floating-point data-processing (1 source)");
         } else if (var8 >= 4 && var8 < 8) {
            return Me[var7 << 2 | var8 - 4];
         } else {
            return var8 >= 16 && var7 >= 2
               ? Qg.q(var0, "Floating-point data-processing (1 source)")
               : Qg.q(Hk, var8, var0, "Floating-point data-processing (1 source)");
         }
      } else {
         return Qg.q(var0, "Floating point");
      }
   }
}
