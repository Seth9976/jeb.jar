package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class WW {
   private static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(10, 3);
   private static final ji.Av A = (var0, var1) -> (var0[0] & 32) != 0;
   private static final Hu[] kS = new Hu[]{sQ.FE, sQ.Aj, Dj.gp};
   private static final tz[] wS = new tz[]{
      new UC("AND", kS),
      new UC("BIC", kS),
      new UC("ORR", kS).pC(new UC("MOV", sQ.FE, sQ.LM), NH.pC(sQ.UT, 31).and(var0 -> (var0[1] & 192) == 0 && (var0[2] & 252) == 0)),
      new UC("ORN", kS).pC(new UC("MVN", sQ.FE, Dj.gp), NH.pC(sQ.UT, 31)),
      new UC("EOR", kS),
      new UC("EON", kS),
      new UC("AND", kS).pC(ji.hK).pC(new UC("TST", sQ.Aj, Dj.gp), NH.pC(sQ.A, 31)),
      new UC("BIC", kS).pC(ji.hK)
   };
   private static final tz[] UT = new tz[]{
      new UC("ADD", kS).pC(A).pC(new UC("CMN", sQ.Aj, Dj.gp), NH.pC(sQ.A, 31).and(var0 -> (var0[0] & 32) != 0)),
      new UC("SUB", kS)
         .pC(A)
         .pC(new UC("CMP", sQ.Aj, Dj.gp), NH.pC(sQ.A, 31).and(var0 -> (var0[0] & 32) != 0))
         .pC(new UC("NEG", sQ.FE, Dj.gp).pC(A), NH.pC(sQ.UT, 31))
   };
   private static final Hu E = new AD();
   private static final tz[] sY = new tz[]{
      new UC("ADD", sQ.Ek, sQ.hK, E),
      new UC("ADD", sQ.ED, sQ.hK, E).pC(ji.hK).pC(new UC("CMN", sQ.hK, E), NH.pC(sQ.A, 31)),
      new UC("SUB", sQ.Ek, sQ.hK, E),
      new UC("SUB", sQ.ED, sQ.hK, E).pC(ji.hK).pC(new UC("CMP", sQ.hK, E), NH.pC(sQ.A, 31)),
      new UC("ADD", sQ.WR, sQ.NS, E),
      new UC("ADD", sQ.A, sQ.NS, E).pC(ji.hK).pC(new UC("CMN", sQ.NS, E), NH.pC(sQ.A, 31)),
      new UC("SUB", sQ.WR, sQ.NS, E),
      new UC("SUB", sQ.A, sQ.NS, E).pC(ji.hK).pC(new UC("CMP", sQ.NS, E), NH.pC(sQ.A, 31))
   };
   private static final tz[] ys = new tz[]{
      new UC("ADC", sQ.FE, sQ.Aj, sQ.LM).pC(A), new UC("SBC", sQ.FE, sQ.Aj, sQ.LM).pC(A).pC(new UC("NGC", sQ.FE, sQ.LM).pC(A), NH.pC(sQ.UT, 31))
   };
   private static final tz[] ld = new tz[]{new UC("CCMN", sQ.Aj, sQ.LM, IV.UT, Ag.A), new UC("CCMP", sQ.Aj, sQ.LM, IV.UT, Ag.A)};
   private static final tz[] gp = new tz[]{new UC("CCMN", sQ.Aj, IV.vP, IV.UT, Ag.A), new UC("CCMP", sQ.Aj, IV.vP, IV.UT, Ag.A)};
   private static final tz[] oT = new tz[]{
      new UC("CSEL", sQ.FE, sQ.Aj, sQ.LM, Ag.A),
      new UC("CSINC", sQ.FE, sQ.Aj, sQ.LM, Ag.A),
      new UC("CSINV", sQ.FE, sQ.Aj, sQ.LM, Ag.A),
      new UC("CSNEG", sQ.FE, sQ.Aj, sQ.LM, Ag.A)
   };
   private static final Ag fI = new Ag(zj.pC, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(13, 3), TN.A(DirectEncodedMemoryArea.get(12, 1))));
   private static final tz WR = new UC("CSET", sQ.FE, fI);
   private static final tz NS = new UC("CINC", sQ.FE, sQ.Aj, fI);
   private static final tz vP = new UC("CSETM", sQ.FE, fI);
   private static final tz xC = new UC("CINV", sQ.FE, sQ.Aj, fI);
   private static final tz ED = new UC("CNEG", sQ.FE, sQ.Aj, fI);
   private static final Hu[] Sc = new Hu[]{sQ.FE, sQ.Aj};
   private static final Hu[] ah = new Hu[]{sQ.FE, sQ.Aj, sQ.LM};
   private static final ZW eP = new ZW(DirectEncodedMemoryArea.get(31, 1), 1L, 0L);
   private static final ZW UO = new ZW(DirectEncodedMemoryArea.get(31, 1), 0L, 1L);
   private static final ZW Ab = new ZW(DirectEncodedMemoryArea.get(31, 1), 1L, 4398046511104L);
   private static final tz[] rl = new tz[]{
      new UC(0, "SUBP", sQ.A, sQ.NS, sQ.vP).pC(Ab),
      null,
      new UC(2, "UDIV", ah),
      new UC(3, "SDIV", ah),
      new UC(4, "IRG", sQ.WR, sQ.NS, sQ.fI).pC(Ab),
      new UC(5, "GMI", sQ.A, sQ.NS, sQ.ld).pC(Ab),
      null,
      null,
      new UC(8, "LSL", ah),
      new UC(9, "LSR", ah),
      new UC(10, "ASR", ah),
      new UC(11, "ROR", ah),
      new UC(12, "PACGA", sQ.A, sQ.UT, sQ.vP).pC(Le.rl).pC(eP),
      null,
      null,
      null,
      new UC(16, "CRC32B", ah).pC(UO),
      new UC(17, "CRC32H", ah).pC(UO),
      new UC(18, "CRC32W", ah).pC(UO),
      new UC(19, "CRC32X", sQ.ED, sQ.eP, sQ.ld).pC(eP),
      new UC(20, "CRC32CB", ah).pC(UO),
      new UC(21, "CRC32CH", ah).pC(UO),
      new UC(22, "CRC32CW", ah).pC(UO),
      new UC(23, "CRC32CX", sQ.ED, sQ.eP, sQ.ld).pC(eP),
      new UC(24, "SMAX", ah).pC(Le.DQ),
      new UC(25, "UMAX", ah).pC(Le.DQ),
      new UC(26, "SMIN", ah).pC(Le.DQ),
      new UC(27, "UMIN", ah).pC(Le.DQ)
   };
   private static final tz[] z = new tz[]{new UC("SUBP", sQ.A, sQ.NS, sQ.vP).pC(ji.hK).pC(Ab).pC(new UC("CMPP", sQ.NS, sQ.vP).pC(Ab), NH.pC(sQ.A, 31))};
   private static final ZW Ek = new ZW(DirectEncodedMemoryArea.get(5, 5), ZW.A);
   private static final tz[][] hK = new tz[][]{
      {
            new UC("RBIT", Sc),
            new UC("REV16", Sc),
            new UC("REV", sQ.ED, sQ.eP),
            new UC("REV", sQ.A, sQ.UT).pC(eP),
            new UC("CLZ", Sc),
            new UC("CLS", Sc),
            new UC("CTZ", Sc).pC(Le.DQ),
            new UC("CNT", Sc).pC(Le.DQ),
            new UC("ABS", Sc).pC(Le.DQ)
      },
      {
            new UC(0, "PACIA", sQ.A, sQ.NS).pC(Le.rl),
            new UC(1, "PACIB", sQ.A, sQ.NS).pC(Le.rl),
            new UC(2, "PACDA", sQ.A, sQ.NS).pC(Le.rl),
            new UC(3, "PACDB", sQ.A, sQ.NS).pC(Le.rl),
            new UC(4, "AUTIA", sQ.A, sQ.NS).pC(Le.rl),
            new UC(5, "AUTIB", sQ.A, sQ.NS).pC(Le.rl),
            new UC(6, "AUTDA", sQ.A, sQ.NS).pC(Le.rl),
            new UC(7, "AUTDB", sQ.A, sQ.NS).pC(Le.rl),
            new UC(8, "PACIZA", sQ.A).pC(Le.rl).pC(Ek),
            new UC(9, "PACIZB", sQ.A).pC(Le.rl).pC(Ek),
            new UC(10, "PACDZA", sQ.A).pC(Le.rl).pC(Ek),
            new UC(11, "PACDZB", sQ.A).pC(Le.rl).pC(Ek),
            new UC(12, "AUTIZA", sQ.A).pC(Le.rl).pC(Ek),
            new UC(13, "AUTIZB", sQ.A).pC(Le.rl).pC(Ek),
            new UC(14, "AUTDZA", sQ.A).pC(Le.rl).pC(Ek),
            new UC(15, "AUTDZB", sQ.A).pC(Le.rl).pC(Ek),
            new UC(16, "XPACI", sQ.A).pC(Le.rl).pC(Ek),
            new UC(17, "XPACD", sQ.A).pC(Le.rl).pC(Ek)
      }
   };
   private static final tz Er = new UC("REV32", sQ.A, sQ.UT);
   private static final Hu[] FE = new Hu[]{sQ.FE, sQ.Aj, sQ.LM, sQ.EX};
   private static final tz[] Aj = new tz[]{
      new UC("MADD", FE).pC(new UC("MUL", ah), NH.pC(sQ.ys, 31)), new UC("MSUB", FE).pC(new UC("MNEG", ah), NH.pC(sQ.ys, 31))
   };
   private static final Hu[] EX = new Hu[]{sQ.A, sQ.eP, sQ.Ab, sQ.ys};
   private static final Hu[] LM = new Hu[]{sQ.A, sQ.eP, sQ.Ab};
   private static final Hu[] mv = new Hu[]{sQ.A, sQ.UT, sQ.ld, sQ.ys};
   private static final tz[] sO = new tz[]{
      null,
      null,
      new UC("SMADDL", EX).pC(new UC("SMULL", LM), NH.pC(sQ.ys, 31)),
      new UC("SMSUBL", EX).pC(new UC("SMNEGL", LM), NH.pC(sQ.ys, 31)),
      new UC("SMULH", ah),
      null,
      new UC("MADDPT", mv).pC(Le.Bf),
      new UC("MSUBPT", mv).pC(Le.Bf),
      null,
      null,
      new UC("UMADDL", EX).pC(new UC("UMULL", LM), NH.pC(sQ.ys, 31)),
      new UC("UMSUBL", EX).pC(new UC("UMNEGL", LM), NH.pC(sQ.ys, 31)),
      new UC("UMULH", ah),
      null,
      null,
      null
   };
   private static final tz[] os = new tz[]{new UC("SETF8", sQ.eP).pC(Le.hK), new UC("SETF16", sQ.eP).pC(Le.hK)};
   private static final tz Cu = new UC("RMIF", sQ.UT, IV.z, IV.UT).pC(Le.hK);
   private static final Hu hZ = new Dj(sQ.ld, Dj.pC, pC);
   private static final tz[] UW = new tz[]{
      null, null, null, null, new UC("ADDPT", sQ.WR, sQ.NS, hZ).pC(Le.Bf), null, new UC("SUBPT", sQ.WR, sQ.NS, hZ).pC(Le.Bf), null
   };

   public static tz pC(byte[] var0) throws ProcessorException {
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
      if (var2 != 0) {
         switch (var3) {
            case 0:
               if (var11 == 0) {
                  return ys[var1];
               } else {
                  if ((var11 & 15) == 2) {
                     if (var0[0] == 58 && var0[1] == 0 && var8 == 13 && (var0[2] & 128) == 0) {
                        int var20 = (var0[2] & 64) >>> 6;
                        return os[var20];
                     }
                  } else if ((var11 & 31) == 1) {
                     if (var0[0] == -70 && (var0[3] & 16) == 0) {
                        return Cu;
                     }
                  } else if ((var11 & 56) == 8) {
                     return UW[(var0[0] & 224) >>> 5];
                  }

                  return UC.pC(var0, "Data-Processing Register: Add/Substract with carry");
               }
            case 1:
            case 3:
            case 5:
            case 7:
               return UC.pC(var0, "Data-Processing Register");
            case 2:
               if (var6 != 0 && (var0[2] & 4) == 0 && (var0[3] & 16) == 0) {
                  if (var4 == 0) {
                     return ld[var1];
                  }

                  return gp[var1];
               }

               return UC.pC(var0, "Data-Processing Register: Conditional compare");
            case 4:
               int var19 = (var0[2] & 12) >>> 2;
               int var24 = var1 << 1 | var19 & 1;
               int var27 = (var0[2] & 240) >>> 4;
               if (var6 == 0 && var19 < 2) {
                  if (var24 == 1) {
                     if (var10 == 31 && var9 == 31 && var27 < 14) {
                        return WR;
                     }

                     if (var10 != 31 && var9 != 31 && var27 < 14 && var9 == var10) {
                        return NS;
                     }
                  } else if (var24 == 2) {
                     if (var10 == 31 && var9 == 31 && var27 < 14) {
                        return vP;
                     }

                     if (var10 != 31 && var9 != 31 && var27 < 14 && var9 == var10) {
                        return xC;
                     }
                  } else if (var24 == 3 && var27 < 14 && var9 == var10) {
                     return ED;
                  }

                  return oT[var24];
               }

               return UC.pC(var0, "Data-Processing Register: Conditional select");
            case 6:
               if (var1 == 0) {
                  String var18 = "Data-Processing Register: 2 sources";
                  int var23 = (var0[2] & 252) >>> 2;
                  if (var6 == 0) {
                     return UC.pC(rl, var23, var0, var18);
                  } else {
                     if (var6 != 0) {
                        return UC.pC(z, var23, var0, var18);
                     }

                     return UC.pC(var0, var18);
                  }
               } else {
                  String var17 = "Data-Processing Register: 1 source";
                  int var22 = (var0[2] & 252) >>> 2;
                  int var26 = var0[1] & 31;
                  if (var6 != 0) {
                     return UC.pC(var0, var17);
                  } else {
                     if (var26 == 0 && var22 == 2 && var5 == 1) {
                        return Er;
                     }

                     return UC.pC(hK, var26, var22, var0, var17);
                  }
               }
            default:
               int var21 = (var0[1] & 224) >>> 5;
               int var25 = (var0[2] & 128) >>> 7;
               if (var12 == 0) {
                  if (var21 == 0) {
                     return Aj[var25];
                  }

                  if (var5 == 1) {
                     return sO[var21 << 1 | var25];
                  }
               }

               return UC.pC(var0, "Data-Processing Register: 3 sources");
         }
      } else if (var3 < 8) {
         int var16 = (var0[1] & 32) >>> 5;
         if (var5 == 0 && (var0[2] & 128) != 0) {
            return UC.pC(var0, "Data-Processing Register: Logical");
         } else {
            int var14 = var12 << 1 | var16;
            return wS[var14];
         }
      } else if ((var3 & 1) != 0) {
         int var13 = (var0[2] & 28) >>> 2;
         if (var13 < 5 && var7 < 1) {
            int var15 = var5 << 2 | var1 << 1 | var6;
            return sY[var15];
         } else {
            return UC.pC(var0, "Data-Processing Register: Add/Substract Extended");
         }
      } else {
         return var7 != 3 && (var5 != 0 || (var0[2] & 128) == 0) ? UT[var1] : UC.pC(var0, "Data-Processing Register: Add/Substract");
      }
   }
}
