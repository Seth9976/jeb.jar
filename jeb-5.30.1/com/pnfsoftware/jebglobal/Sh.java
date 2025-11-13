package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

public class Sh {
   private static final de.CU q = (var0, var1) -> (var0[0] & 32) != 0;
   private static final Ef[] RF = new Ef[]{YH.Dz, YH.mI, cn.xW};
   private static final Je[] xK = new Je[]{
      new Qg("AND", RF),
      new Qg("BIC", RF),
      new Qg("ORR", RF),
      new Qg("ORN", RF),
      new Qg("EOR", RF),
      new Qg("EON", RF),
      new Qg("AND", RF).q(de.cC),
      new Qg("BIC", RF).q(de.cC)
   };
   private static final Je Dw = new Qg("MOV", YH.Dz, YH.ui);
   private static final Je Uv = new Qg("MVN", YH.Dz, cn.xW);
   private static final Je oW = new Qg("TST", YH.mI, cn.xW);
   private static final Je[] gO = new Je[]{new Qg("ADD", RF).q(q), new Qg("SUB", RF).q(q)};
   private static final Je nf = new Qg("CMN", YH.mI, cn.xW);
   private static final Je gP = new Qg("CMP", YH.mI, cn.xW);
   private static final Je za = new Qg("NEG", YH.Dz, cn.xW).q(q);
   private static final Ef lm = new eD();
   private static final Je[] zz = new Je[]{
      new Qg("ADD", YH.CE, YH.wF, lm),
      new Qg("ADD", YH.PV, YH.wF, lm).q(de.cC),
      new Qg("SUB", YH.CE, YH.wF, lm),
      new Qg("SUB", YH.PV, YH.wF, lm).q(de.cC),
      new Qg("ADD", YH.io, YH.qa, lm),
      new Qg("ADD", YH.Uv, YH.qa, lm).q(de.cC),
      new Qg("SUB", YH.io, YH.qa, lm),
      new Qg("SUB", YH.Uv, YH.qa, lm).q(de.cC)
   };
   private static final Je[] JY = new Je[]{new Qg("CMN", YH.wF, lm), new Qg("CMP", YH.wF, lm), new Qg("CMN", YH.qa, lm), new Qg("CMP", YH.qa, lm)};
   private static final Je[] HF = new Je[]{new Qg("ADC", YH.Dz, YH.mI, YH.ui).q(q), new Qg("SBC", YH.Dz, YH.mI, YH.ui).q(q)};
   private static final Je LK = new Qg("NGC", YH.Dz, YH.ui).q(q);
   private static final Je[] io = new Je[]{new Qg("CCMN", YH.mI, YH.ui, go.zz, wJ.RF), new Qg("CCMP", YH.mI, YH.ui, go.zz, wJ.RF)};
   private static final Je[] qa = new Je[]{new Qg("CCMN", YH.mI, go.xW, go.zz, wJ.RF), new Qg("CCMP", YH.mI, go.xW, go.zz, wJ.RF)};
   private static final Je[] Hk = new Je[]{
      new Qg("CSEL", YH.Dz, YH.mI, YH.ui, wJ.RF),
      new Qg("CSINC", YH.Dz, YH.mI, YH.ui, wJ.RF),
      new Qg("CSINV", YH.Dz, YH.mI, YH.ui, wJ.RF),
      new Qg("CSNEG", YH.Dz, YH.mI, YH.ui, wJ.RF)
   };
   private static final wJ Me = new wJ(mZ.q, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(13, 3), dX.RF(DirectEncodedMemoryArea.get(12, 1))));
   private static final Je PV = new Qg("CSET", YH.Dz, Me);
   private static final Je oQ = new Qg("CINC", YH.Dz, YH.mI, Me);
   private static final Je xW = new Qg("CSETM", YH.Dz, Me);
   private static final Je KT = new Qg("CINV", YH.Dz, YH.mI, Me);
   private static final Je Gf = new Qg("CNEG", YH.Dz, YH.mI, Me);
   private static final Ef[] Ef = new Ef[]{YH.Dz, YH.mI};
   private static final Ef[] cC = new Ef[]{YH.Dz, YH.mI, YH.ui};
   private static final dD sH = new dD(DirectEncodedMemoryArea.get(31, 1), 1L, 0L);
   private static final dD CE = new dD(DirectEncodedMemoryArea.get(31, 1), 0L, 1L);
   private static final dD wF = new dD(DirectEncodedMemoryArea.get(31, 1), 1L, 4398046511104L);
   private static final Je[] If = new Je[]{
      new Qg(0, "SUBP", YH.Uv, YH.qa, YH.Hk).q(wF),
      null,
      new Qg(2, "UDIV", cC),
      new Qg(3, "SDIV", cC),
      new Qg(4, "IRG", YH.io, YH.qa, YH.LK).q(wF),
      new Qg(5, "GMI", YH.Uv, YH.qa, YH.zz).q(wF),
      null,
      null,
      new Qg(8, "LSL", cC),
      new Qg(9, "LSR", cC),
      new Qg(10, "ASR", cC),
      new Qg(11, "ROR", cC),
      new Qg(12, "PACGA", YH.Uv, YH.nf, YH.Hk).q(QJ.IY).q(sH),
      null,
      null,
      null,
      new Qg(16, "CRC32B", cC).q(CE),
      new Qg(17, "CRC32H", cC).q(CE),
      new Qg(18, "CRC32W", cC).q(CE),
      new Qg(19, "CRC32X", YH.PV, YH.KT, YH.zz).q(sH),
      new Qg(20, "CRC32CB", cC).q(CE),
      new Qg(21, "CRC32CH", cC).q(CE),
      new Qg(22, "CRC32CW", cC).q(CE),
      new Qg(23, "CRC32CX", YH.PV, YH.KT, YH.zz).q(sH),
      new Qg(24, "SMAX", cC).q(QJ.fn),
      new Qg(25, "UMAX", cC).q(QJ.fn),
      new Qg(26, "SMIN", cC).q(QJ.fn),
      new Qg(27, "UMIN", cC).q(QJ.fn)
   };
   private static final Je[] Dz = new Je[]{new Qg("SUBPS", YH.Uv, YH.qa, YH.Hk).q(wF)};
   private static final Je mI = new Qg("CMPP", YH.qa, YH.Hk).q(wF);
   private static final dD jq = new dD(DirectEncodedMemoryArea.get(5, 5), dD.RF);
   private static final Je[][] ui = new Je[][]{
      {
            new Qg("RBIT", Ef),
            new Qg("REV16", Ef),
            new Qg("REV", YH.PV, YH.KT),
            new Qg("REV", YH.Uv, YH.nf).q(sH),
            new Qg("CLZ", Ef),
            new Qg("CLS", Ef),
            new Qg("CTZ", Ef).q(QJ.fn),
            new Qg("CNT", Ef).q(QJ.fn),
            new Qg("ABS", Ef).q(QJ.fn)
      },
      {
            new Qg(0, "PACIA", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(1, "PACIB", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(2, "PACDA", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(3, "PACDB", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(4, "AUTIA", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(5, "AUTIB", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(6, "AUTDA", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(7, "AUTDB", YH.Uv, YH.qa).q(QJ.IY),
            new Qg(8, "PACIZA", YH.Uv).q(QJ.IY).q(jq),
            new Qg(9, "PACIZB", YH.Uv).q(QJ.IY).q(jq),
            new Qg(10, "PACDZA", YH.Uv).q(QJ.IY).q(jq),
            new Qg(11, "PACDZB", YH.Uv).q(QJ.IY).q(jq),
            new Qg(12, "AUTIZA", YH.Uv).q(QJ.IY).q(jq),
            new Qg(13, "AUTIZB", YH.Uv).q(QJ.IY).q(jq),
            new Qg(14, "AUTDZA", YH.Uv).q(QJ.IY).q(jq),
            new Qg(15, "AUTDZB", YH.Uv).q(QJ.IY).q(jq),
            new Qg(16, "XPACI", YH.Uv).q(QJ.IY).q(jq),
            new Qg(17, "XPACD", YH.Uv).q(QJ.IY).q(jq)
      }
   };
   private static final Je TX = new Qg("REV32", YH.Uv, YH.nf);
   private static final Ef[] Rr = new Ef[]{YH.Dz, YH.mI, YH.ui, YH.jq};
   private static final Je[] EB = new Je[]{new Qg("MADD", Rr), new Qg("MSUB", Rr)};
   private static final Je[] Xo = new Je[]{new Qg("MUL", cC), new Qg("MNEG", cC)};
   private static final Ef[] Bu = new Ef[]{YH.Uv, YH.KT, YH.Ef, YH.lm};
   private static final Je[] IN = new Je[]{
      null,
      null,
      new Qg("SMADDL", Bu),
      new Qg("SMSUBL", Bu),
      new Qg("SMULH", cC),
      null,
      null,
      null,
      null,
      null,
      new Qg("UMADDL", Bu),
      new Qg("UMSUBL", Bu),
      new Qg("UMULH", cC),
      null,
      null,
      null
   };
   private static final Ef[] rL = new Ef[]{YH.Uv, YH.KT, YH.Ef};
   private static final Je[] eJ = new Je[]{new Qg("SMULL", rL), new Qg("SMNEGL", rL), new Qg("UMULL", rL), new Qg("UMNEGL", rL)};
   private static final Je[] YN = new Je[]{new Qg("SETF8", YH.KT).q(QJ.fw), new Qg("SETF16", YH.KT).q(QJ.fw)};
   private static final Je Rv = new Qg("RMIF", YH.nf, go.Dz, go.zz).q(QJ.fw);

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 64) >>> 6;
      int var2 = var0[0] & 16;
      int var3 = (var0[0] & 1) << 3 | (var0[1] & 224) >>> 5;
      int var4 = var0[2] & 8;
      int var5 = (var0[0] & 128) >>> 7;
      int var6 = (var0[0] & 32) >>> 5;
      int var7 = (var0[1] & 192) >>> 6;
      int var8 = DirectEncodedMemoryArea.get(0, 5).decodeInt(var0);
      int var9 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var0);
      int var10 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
      int var11 = (var0[2] & 252) >>> 2;
      int var12 = (var0[0] & 96) >>> 5;
      if (var2 == 0) {
         if (var3 < 8) {
            int var21 = (var0[1] & 32) >>> 5;
            if (var5 == 0 && (var0[2] & 128) != 0) {
               return Qg.q(var0, "Data-Processing Register: Logical");
            } else {
               int var25 = var12 << 1 | var21;
               if (var25 == 2 && var7 == 0 && var11 == 0 && var9 == 31) {
                  return Dw;
               } else if (var25 == 3 && var9 == 31) {
                  return Uv;
               } else {
                  return var25 == 6 && var8 == 31 ? oW : xK[var25];
               }
            }
         } else if ((var3 & 1) != 0) {
            int var20 = (var0[2] & 28) >>> 2;
            if (var20 < 5 && var7 < 1) {
               int var28 = var5 << 2 | var1 << 1 | var6;
               return var6 != 0 && var8 == 31 ? JY[var28 >>> 1] : zz[var28];
            } else {
               return Qg.q(var0, "Data-Processing Register: Add/Substract Extended");
            }
         } else if (var7 != 3 && (var5 != 0 || (var0[2] & 128) == 0)) {
            if (var1 == 0 && var6 != 0 && var8 == 31) {
               return nf;
            } else if (var1 == 1 && var6 != 0 && var8 == 31) {
               return gP;
            } else {
               return var1 == 1 && var9 == 31 ? za : gO[var1];
            }
         } else {
            return Qg.q(var0, "Data-Processing Register: Add/Substract");
         }
      } else {
         switch (var3) {
            case 0:
               if (var11 == 0) {
                  if (var1 == 1 && var9 == 31) {
                     return LK;
                  }

                  return HF[var1];
               } else {
                  if ((var11 & 15) == 2) {
                     if (var0[0] == 58 && var0[1] == 0 && var8 == 13 && (var0[2] & 128) == 0) {
                        int var19 = (var0[2] & 64) >>> 6;
                        return YN[var19];
                     }
                  } else if ((var11 & 31) == 1 && var0[0] == -70 && (var0[3] & 16) == 0) {
                     return Rv;
                  }

                  return Qg.q(var0, "Data-Processing Register: Add/Substract with carry");
               }
            case 1:
            case 3:
            case 5:
            case 7:
               return Qg.q(var0, "Data-Processing Register");
            case 2:
               if (var6 != 0 && (var0[2] & 4) == 0 && (var0[3] & 16) == 0) {
                  if (var4 == 0) {
                     return io[var1];
                  }

                  return qa[var1];
               }

               return Qg.q(var0, "Data-Processing Register: Conditional compare");
            case 4:
               int var18 = (var0[2] & 12) >>> 2;
               int var24 = var1 << 1 | var18 & 1;
               int var27 = (var0[2] & 240) >>> 4;
               if (var6 == 0 && var18 < 2) {
                  if (var24 == 1) {
                     if (var10 == 31 && var9 == 31 && var27 < 14) {
                        return PV;
                     }

                     if (var10 != 31 && var9 != 31 && var27 < 14 && var9 == var10) {
                        return oQ;
                     }
                  } else if (var24 == 2) {
                     if (var10 == 31 && var9 == 31 && var27 < 14) {
                        return xW;
                     }

                     if (var10 != 31 && var9 != 31 && var27 < 14 && var9 == var10) {
                        return KT;
                     }
                  } else if (var24 == 3 && var27 < 14 && var9 == var10) {
                     return Gf;
                  }

                  return Hk[var24];
               }

               return Qg.q(var0, "Data-Processing Register: Conditional select");
            case 6:
               if (var1 == 0) {
                  String var17 = "Data-Processing Register: 2 sources";
                  int var23 = (var0[2] & 252) >>> 2;
                  if (var6 == 0) {
                     return Qg.q(If, var23, var0, var17);
                  } else {
                     if (var6 != 0) {
                        if (var23 == 0 && var8 == 31) {
                           return mI;
                        }

                        return Qg.q(Dz, var23, var0, var17);
                     }

                     return Qg.q(var0, var17);
                  }
               } else {
                  String var13 = "Data-Processing Register: 1 source";
                  int var22 = (var0[2] & 252) >>> 2;
                  int var26 = var0[1] & 31;
                  if (var6 != 0) {
                     return Qg.q(var0, var13);
                  } else {
                     if (var26 == 0 && var22 == 2 && var5 == 1) {
                        return TX;
                     }

                     return Qg.q(ui, var26, var22, var0, var13);
                  }
               }
            default:
               int var14 = (var0[1] & 224) >>> 5;
               int var15 = (var0[2] & 128) >>> 7;
               int var16 = (var0[2] & 124) >>> 2;
               if (var12 == 0) {
                  if (var14 == 0) {
                     if (var16 == 31) {
                        return Xo[var15];
                     }

                     return EB[var15];
                  }

                  if (var5 == 1) {
                     if (var16 != 31 || var14 != 1 && var14 != 5) {
                        return IN[var14 << 1 | var15];
                     }

                     return eJ[var14 >>> 1 | var15];
                  }
               }

               return Qg.q(var0, "Data-Processing Register: 3 sources");
         }
      }
   }
}
