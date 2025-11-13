package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class cme {
   private static final ILogger q = GlobalLog.getLogger(cme.class);
   private static final cmf[] RF = new cmf[]{
      cmf.q("ADDI", true, cmf.RF),
      cmf.q("ADDIU", true),
      cmf.q("SLTI", true),
      cmf.q("SLTIU", true),
      cmf.q("ANDI", false),
      cmf.q("ORI", false),
      cmf.q("XORI", false),
      cmf.q("AUI", true, cmf.xK)
   };
   private static final cmf xK = cmf.q("DAUI", true, cmf.HF);
   private static final cmf Dw = new cmf("LUI", clz.xK, clx.xW);
   private static final cmf Uv = new cmf("LI", clz.xK, clx.gP);
   private static final cmf[] oW = new cmf[]{cmf.RF("DADDI", true, cmf.lm), cmf.RF("DADDIU", true, cmf.za), cmf.oW("LDL", cmf.lm), cmf.oW("LDR", cmf.lm)};
   private static final cmf[] gO = new cmf[]{
      cmf.Dw("LB"), cmf.Dw("LH"), cmf.Dw("LWL", cmf.RF), cmf.Dw("LW"), cmf.Dw("LBU"), cmf.Dw("LHU"), cmf.Dw("LWR", cmf.RF), cmf.oW("LWU", cmf.za)
   };
   private static final cmf[] nf = new cmf[]{
      cmf.Dw("SB"),
      cmf.Dw("SH"),
      cmf.Dw("SWL", cmf.RF),
      cmf.Dw("SW"),
      cmf.oW("SDL", cmf.lm),
      cmf.oW("SDR", cmf.lm),
      cmf.Dw("SWR", cmf.RF),
      new cmf("CACHE", cmf.RF, clx.Hk, cmf.LK)
   };
   private static final cmf[] gP = new cmf[]{
      cmf.Dw("LL", cmf.nf),
      cmf.Uv("LWC1"),
      cmf.Dw("LWC2", cmf.RF),
      new cmf("PREF", cmf.gP, clx.Hk, cmf.LK),
      cmf.oW("LLD", cmf.lm),
      cmf.Uv("LDC1"),
      cmf.Dw("LDC2", cmf.nf),
      cmf.oW("LD", cmf.za)
   };
   private static final cmf za = cmf.Dw("LWC3");
   private static final cmf[] lm = new cmf[]{
      cmf.Dw("SC", cmf.nf),
      cmf.Uv("SWC1"),
      cmf.Dw("SWC2", cmf.RF),
      cmf.q,
      cmf.oW("SCD", cmf.za),
      cmf.Uv("SDC1", cmf.gO),
      cmf.Dw("SDC2", cmf.nf),
      cmf.oW("SD", cmf.za)
   };
   private static final clw[] zz = new clw[]{clz.q, clx.Dw};
   private static final cmf[] JY = new cmf[]{new cmf("LAPC", zz), new cmf("LWPC", cmf.xK, zz), new cmf("LWUPC", cmf.HF, zz)};
   private static final cmf HF = new cmf("LDPC", cmf.HF, clz.q, clx.xK);
   private static final cmf LK = new cmf("AUIPC", cmf.xK, clz.q, clx.gP);
   private static final cmf io = new cmf("ALUIPC", cmf.xK, clz.q, clx.gP);

   public static cmf q(byte[] var0, clg var1, boolean var2, int var3) throws ProcessorException {
      int var4 = (var0[0] & 224) >>> 5;
      int var5 = (var0[0] & 28) >>> 2;
      int var6 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
      int var7 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var4) {
         case 0:
            switch (var5) {
               case 0:
                  return cmo.q(var0, var1, var2);
               case 1:
                  return cmo.RF(var0, var1, var2);
               case 2:
                  return cmp.nf;
               case 3:
                  return cmp.gP;
               case 4:
                  if (var6 == 0) {
                     return var7 == 0 ? cmp.lm : cmp.Me;
                  }

                  return cmp.HF;
               case 5:
                  if (var6 == 0) {
                     return cmp.PV;
                  }

                  return cmp.LK;
               case 6:
                  if (var6 == 0) {
                     return cmp.oQ;
                  }

                  return cmp.q(var7, var6);
               case 7:
                  if (var6 == 0) {
                     return cmp.xW;
                  }

                  return cmp.RF(var7, var6);
               default:
                  return cmf.q(var0, "000");
            }
         case 1:
            if (var1.RF() && var5 == 0) {
               cmf var9 = cmp.xK(var7, var6);
               if (var9 != null) {
                  return var9;
               }
            } else {
               if (var5 == 7 && var7 == 0) {
                  return Dw;
               }

               if (var5 == 1 && var7 == 0) {
                  return Uv;
               }
            }

            return RF[var5];
         case 2:
            switch (var5) {
               case 0:
                  return cmo.q(var0);
               case 1:
                  return cmo.xK(var0, var1, var2);
               case 2:
                  return cmo.RF(var0);
               case 3:
                  return cmo.xK(var0);
               case 4:
                  return cmp.io;
               case 5:
                  return cmp.qa;
               case 6:
                  if (var6 == 0) {
                     return cmp.KT;
                  }

                  return cmp.Dw(var7, var6);
               case 7:
                  if (var6 == 0) {
                     return cmp.Gf;
                  }

                  return cmp.Uv(var7, var6);
               default:
                  return cmf.q(var0, "010");
            }
         case 3:
            switch (var5) {
               case 0:
                  if (var1.RF()) {
                     return cmp.oW(var7, var6);
                  } else if (var1.xK()) {
                     return oW[var5];
                  }
               default:
                  return cmf.q(var0, "011_" + var5);
               case 1:
               case 2:
               case 3:
                  return oW[var5];
               case 4:
                  return cmo.Dw(var0, var1, var2);
               case 5:
                  if (var1.RF()) {
                     if (var7 == 0) {
                        return cmf.RF(var0, "3" + var5);
                     }

                     return xK;
                  }

                  return cmp.za;
               case 6:
                  return cmo.Dw(var0);
               case 7:
                  return cmo.q(var0, var1, var2, var3);
            }
         case 4:
            return gO[var5];
         case 5:
            return nf[var5];
         case 6:
            if (var1.RF()) {
               if (var5 == 2) {
                  return cmp.zz;
               }

               if (var5 == 6) {
                  return cmp.q(var0, var7);
               }
            }

            if (var5 == 3 && var1.q() <= clg.xK.q()) {
               return za;
            }

            return gP[var5];
         case 7:
            if (var1.RF()) {
               if (var5 == 2) {
                  return cmp.JY;
               }

               if (var5 == 6) {
                  return cmp.RF(var0, var7);
               }
            }

            if (var5 == 3) {
               int var8 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
               if (var8 < 24) {
                  return JY[var8 >>> 3];
               }

               if (var8 < 28) {
                  return HF;
               }

               if (var8 == 30) {
                  return LK;
               }

               if (var8 == 31) {
                  return io;
               }
            }

            return lm[var5];
         default:
            return null;
      }
   }
}
