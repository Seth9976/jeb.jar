package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class cmq {
   private static final clw[] RF = cmp.Hk;
   private static final cmf[] xK = new cmf[]{
      new cmf("BLTZ", cmp.q, cmb.Dw, null, RF),
      new cmf("BGEZ", cmp.q, cmb.gO, null, RF),
      new cmf("BLTZL", cmp.xK, cmb.Dw, cmf.nf, RF),
      new cmf("BGEZL", cmp.xK, cmb.gO, cmf.nf, RF),
      cmf.q,
      cmf.q,
      new cmf("DAHI", cmf.HF, clz.q, clx.gP),
      cmf.q
   };
   private static final clw[] Dw = new clw[]{clz.q, clx.gP};
   private static final cmf[] Uv = new cmf[]{
      new cmf("TGEI", cmf.nf, Dw),
      new cmf("TGEIU", cmf.nf, Dw),
      new cmf("TLTI", cmf.nf, Dw),
      new cmf("TLTIU", cmf.nf, Dw),
      new cmf("TEQI", cmf.nf, Dw),
      cmf.q,
      new cmf("TNEI", cmf.nf, Dw),
      cmf.q
   };
   private static final cmf[] oW = new cmf[]{
      new cmf("BLTZAL", cmp.Dw, cmb.Dw, cmf.RF, RF),
      new cmf("BGEZAL", cmp.Dw, cmb.gO, cmf.RF, RF),
      new cmf("BLTZALL", cmp.oW, cmb.Dw, cmf.nf, RF),
      new cmf("BGEZALL", cmp.oW, cmb.gO, cmf.nf, RF),
      cmf.q,
      cmf.q,
      cmf.q,
      new cmf("SIGRIE", cmf.xK, clx.xW)
   };
   static final clt q = new clt(131841);
   private static final cmf[] gO = new cmf[]{new cmf("NAL", q, cmf.xK), new cmf("BAL", cmp.Dw, clx.RF)};
   private static final clw nf = new cly(clx.gP, clz.q);
   private static final cmf[] gP = new cmf[]{new cmf("DATI", cmf.HF, Dw), new cmf("SYNCI", cmf.Dw, nf)};

   public static cmf q(byte[] var0, clg var1, boolean var2) throws ProcessorException {
      int var3 = (var0[1] & 24) >>> 3;
      int var4 = var0[1] & 7;
      int var5 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var3) {
         case 0:
            if (var4 != 4 && var4 != 5) {
               if (var4 == 7) {
                  return cmf.RF(var0, "Regimm00");
               }

               return xK[var4];
            }

            return cmf.q(var0, "Regimm00");
         case 1:
            if (var4 != 5 && var4 != 7) {
               return Uv[var4];
            }

            return cmf.q(var0, "Regimm01");
         case 2:
            if (var5 == 0 && var4 < 2) {
               return gO[var4];
            } else {
               if (MathUtil.betweenInclusive(var4, 4L, 6L)) {
                  return cmf.q(var0, "Regimm10");
               }

               return oW[var4];
            }
         case 3:
            if (var5 == 0 && var4 == 0) {
               return cmn.xK;
            } else if (var5 == 0 && var4 == 4) {
               return cmn.RF;
            } else if (var5 == 0 && var4 == 5) {
               return cmn.Dw;
            } else {
               if (var4 < 6) {
                  return cmf.q(var0, "Regimm11");
               }

               return gP[var4 - 6];
            }
         default:
            return null;
      }
   }
}
