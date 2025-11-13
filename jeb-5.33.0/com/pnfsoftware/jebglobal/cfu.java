package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class cfu {
   private static final cfk[] A = new cfk[]{cfn.kS, cfn.ys, cfl.fI};
   private static final cfs[] kS = new cfs[]{
      new cfs("MFC0", A),
      new cfs("DMFC0", cfs.gp, A),
      new cfs("MFHC0", cfs.UT, A),
      cfs.pC,
      new cfs("MTC0", A),
      new cfs("DMTC0", cfs.gp, A),
      new cfs("MTHC0", cfs.UT, A),
      cfs.pC
   };
   private static final cfs[] wS = new cfs[]{new cfs("RDPGPR", cfs.wS, cfn.ys, cfn.kS), new cfs("WRPGPR", cfs.wS, cfn.ys, cfn.kS)};
   private static final cfs UT = new cfs("DI", cfs.wS, cfn.wS);
   private static final cfs E = new cfs("EI", cfs.wS, cfn.wS);
   private static final cfs[] sY = new cfs[]{
      cfs.pC, new cfs("TLBR"), new cfs("TLBWI"), new cfs("TLBINV"), new cfs("TLBINVF"), cfs.pC, new cfs("TLBWR"), cfs.pC
   };
   static final cfh pC = new cfh(1);
   private static final cfs ys = new cfs("TLBP");
   private static final cfs ld = new cfs("ERET", pC);
   private static final cfs gp = new cfs("ERETNC", pC);
   private static final cfs oT = new cfs("DERET", pC);
   private static final cfs fI = new cfs("WAIT");
   private static final cfk[] WR = new cfk[]{cfn.kS, cfn.gp};
   private static final cfs[] NS = new cfs[]{
      new cfs("MFC1", WR),
      new cfs("DMFC1", cfs.gp, WR),
      new cfs("CFC1", WR),
      new cfs("MFHC1", cfs.wS, WR),
      new cfs("MTC1", WR),
      new cfs("DMTC1", cfs.gp, WR),
      new cfs("CTC1", WR),
      new cfs("MTHC1", cfs.wS, WR)
   };
   private static final com.pnfsoftware.jeb.corei.parsers.mips.Av vP = new cfv();
   private static final cfk xC = cfn.Ab;
   private static final cfs ED = new cfs("BC1", vP, cgc.pC, cfo.xC, cfs.A, xC, cfl.A);
   private static final cfs Sc = new cfs("BC1EQZ", cgc.pC, cfo.NS, cfs.kS, cfn.UT, cfl.A);
   private static final cfs ah = new cfs("BC1NEZ", cgc.pC, cfo.vP, cfs.kS, cfn.UT, cfl.A);
   private static final com.pnfsoftware.jeb.corei.parsers.mips.Av eP = new cfw();
   private static final cfk[] UO = new cfk[]{cfn.oT, cfn.gp, cfn.UT};
   private static final cfk[] Ab = new cfk[]{cfn.oT, cfn.gp};
   private static final cfs[] rl = new cfs[]{
      new cfs("ADD", eP, null, UO),
      new cfs("SUB", eP, null, UO),
      new cfs("MUL", eP, null, UO),
      new cfs("DIV", eP, null, UO),
      new cfs("SQRT", eP, null, Ab),
      new cfs("ABS", eP, null, Ab),
      new cfs("MOV", eP, null, Ab),
      new cfs("NEG", eP, null, Ab)
   };
   private static final cfs[] z = new cfs[]{
      new cfs("ROUND.L", eP, cfs.wS, Ab),
      new cfs("TRUNC.L", eP, cfs.wS, Ab),
      new cfs("CEIL.L", eP, cfs.wS, Ab),
      new cfs("FLOOR.L", eP, cfs.wS, Ab),
      new cfs("ROUND.W", eP, null, Ab),
      new cfs("TRUNC.W", eP, null, Ab),
      new cfs("CEIL.W", eP, null, Ab),
      new cfs("FLOOR.W", eP, null, Ab)
   };
   private static final cfs[] Ek = new cfs[]{
      new cfs("SEL", eP, cfs.kS, UO),
      cfs.pC,
      new cfs("MOVZ", eP, cfs.A, cfn.oT, cfn.gp, cfn.kS),
      new cfs("MOVN", eP, cfs.A, cfn.oT, cfn.gp, cfn.kS),
      new cfs("SELEQZ", eP, cfs.kS, UO),
      new cfs("RECIP", eP, cfs.wS, Ab),
      new cfs("RSQRT", eP, cfs.wS, Ab),
      new cfs("SELNEZ", eP, cfs.kS, UO)
   };
   private static final cfs[] hK = new cfs[]{
      new cfs("MOVF", eP, cfs.A, cfn.oT, cfn.gp, cfn.UO), new cfs("MOVT", eP, cfs.A, cfn.oT, cfn.gp, cfn.UO), cfs.pC, cfs.pC
   };
   private static final cfs[] Er = new cfs[]{
      new cfs("MADDF", eP, cfs.kS, UO),
      new cfs("MSUBF", eP, cfs.kS, UO),
      new cfs("RINT", eP, cfs.kS, Ab),
      new cfs("CLASS", eP, cfs.kS, Ab),
      new cfs("MIN", eP, cfs.kS, UO),
      new cfs("MAX", eP, cfs.kS, UO),
      new cfs("MINA", eP, cfs.kS, UO),
      new cfs("MAXA", eP, cfs.kS, UO)
   };
   private static final cfs[] FE = new cfs[]{
      new cfs("CVT.S", eP, null, Ab),
      new cfs("CVT.D", eP, null, Ab),
      cfs.pC,
      cfs.pC,
      new cfs("CVT.W", eP, null, Ab),
      new cfs("CVT.L", eP, null, Ab),
      new cfs("CVT.PS", eP, cfs.A, UO),
      cfs.pC
   };
   private static final com.pnfsoftware.jeb.corei.parsers.mips.Av Aj = new cfx();
   private static final cfs EX = new cfs("C", Aj, cfs.A, cfn.rl, cfn.gp, cfn.UT);
   private static final com.pnfsoftware.jeb.corei.parsers.mips.Av LM = new cfy();
   private static final cfs mv = new cfs("CMP", LM, cfs.kS, UO);
   private static final cfs[] sO = new cfs[]{new cfs("CVT.S.PU", cfs.E, Ab), new cfs("CVT.S.PL", cfs.E, Ab)};
   private static final cfs[] os = new cfs[]{
      new cfs("PLL", eP, cfs.E, UO), new cfs("PLU", eP, cfs.E, UO), new cfs("PUL", eP, cfs.E, UO), new cfs("PUU", eP, cfs.E, UO)
   };
   private static final cfk[] Cu = new cfk[]{cfn.kS, cfn.ys, cfl.UO};
   private static final cfs[] hZ = new cfs[]{
      new cfs("MFC2", Cu),
      new cfs("DMFC2", cfs.gp, Cu),
      new cfs("CFC2", Cu),
      new cfs("MFHC2", cfs.wS, Cu),
      new cfs("MTC2", Cu),
      new cfs("DMTC2", cfs.gp, Cu),
      new cfs("CTC2", Cu),
      new cfs("MTHC2", cfs.wS, Cu)
   };
   private static final cfk UW = new cfm(cfl.ys, cfn.ys);
   private static final cfs[] PR = new cfs[]{
      new cfs("BC2", vP, cgc.pC, cfo.ah, cfs.A, xC, cfl.A),
      new cfs("BC2EQZ", cgc.pC, cfo.ED, cfs.kS, cfn.E, cfl.A),
      new cfs("LWC2", cfs.kS, cfn.kS, UW),
      new cfs("SWC2", cfs.kS, cfn.kS, UW),
      cfs.pC,
      new cfs("BC2NEZ", cgc.pC, cfo.vP, cfs.kS, cfn.E, cfl.A),
      new cfs("LDC2", cfs.kS, cfn.kS, UW),
      new cfs("SDC2", cfs.kS, cfn.kS, UW)
   };
   private static final com.pnfsoftware.jeb.corei.parsers.mips.Av cX = new cfz();
   private static final cfk[] DQ = new cfk[]{cfn.oT, cfn.A, cfn.gp, cfn.UT};
   private static final cfs[] ZN = new cfs[]{
      new cfs("MADD", cX, cfs.E, DQ), new cfs("MSUB", cX, cfs.E, DQ), new cfs("NMADD", cX, cfs.E, DQ), new cfs("NMSUB", cX, cfs.E, DQ)
   };
   private static final cfk OB = new cfm(cfn.kS, cfn.pC);
   private static final cfk[] pF = new cfk[]{cfn.oT, OB};
   private static final cfk[] Bc = new cfk[]{cfn.gp, OB};
   private static final cfs[] OI = new cfs[]{
      new cfs("LWXC1", cfs.E, pF),
      new cfs("LDXC1", cfs.E, pF),
      new cfs("LUXC1", cfs.E, pF),
      cfs.pC,
      new cfs("SWXC1", cfs.E, Bc),
      new cfs("SDXC1", cfs.E, Bc),
      new cfs("SUXC1", cfs.E, Bc),
      new cfs("PREFX", cfs.E, cfl.Sc, OB)
   };
   private static final cfs Bf = new cfs("ALNV", cX, cfs.E, cfn.oT, cfn.gp, cfn.UT, cfn.pC);

   public static cfs pC(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      int var2 = var1 >>> 3;
      int var3 = var1 & 7;
      switch (var2) {
         case 0:
            if (var3 == 3) {
               return cfs.A(var0, "Cop0 00");
            }

            if (DirectEncodedMemoryArea.get(3, 8).decodeInt(var0) == 0) {
               return kS[var3];
            }
            break;
         case 1:
            if (var3 == 0 || var3 == 4) {
               return cfs.A(var0, "Cop0 01");
            }

            if ((var3 & 3) == 2 && DirectEncodedMemoryArea.get(0, 11).decodeInt(var0) == 0) {
               return wS[var3 >>> 2];
            }

            if (var3 == 3 && DirectEncodedMemoryArea.get(0, 5).decodeInt(var0) == 0 && DirectEncodedMemoryArea.get(6, 10).decodeInt(var0) == 384) {
               return (var0[3] & 32) == 0 ? UT : E;
            }
            break;
         case 2:
         case 3:
            return wS(var0);
      }

      return cfs.pC(var0, "Cop0");
   }

   private static cfs wS(byte[] var0) throws ProcessorException {
      int var1 = (var0[3] & 56) >>> 3;
      int var2 = var0[3] & 7;
      if (var2 != 0 || var1 != 2 && var1 != 5 && var1 != 7) {
         if (var1 != 1 || !MathUtil.betweenInclusive(var2, 1L, 4L) && var2 != 6) {
            if (var1 == 4 && var2 == 0) {
               return fI;
            } else {
               int var3 = DirectEncodedMemoryArea.get(6, 19).decodeInt(var0);
               if (var3 == 0) {
                  switch (var1) {
                     case 0:
                        return sY[var2];
                     case 1:
                        if (var2 == 0) {
                           return ys;
                        }
                     case 2:
                     default:
                        break;
                     case 3:
                        if (var2 == 0) {
                           return ld;
                        }

                        if (var2 == 7) {
                           return oT;
                        }
                  }
               } else if (var3 == 1 && var1 == 3 && var2 == 0) {
                  return gp;
               }

               return cfs.pC(var0, "Cop0");
            }
         } else {
            return cfs.A(var0, "Cop0 C0");
         }
      } else {
         return cfs.A(var0, "Cop0 C0");
      }
   }

   public static cfs pC(byte[] var0, com.pnfsoftware.jeb.corei.parsers.mips.Tb var1, boolean var2) throws ProcessorException {
      int var3 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      int var4 = var3 >>> 3;
      int var5 = var3 & 7;
      switch (var4) {
         case 0:
            if (DirectEncodedMemoryArea.get(0, 11).decodeInt(var0) == 0) {
               return NS[var5];
            }
            break;
         case 1:
            if (var5 == 0) {
               return ED;
            }

            if (var5 == 1) {
               return Sc;
            }

            if (var5 == 5) {
               return ah;
            }

            if (MathUtil.betweenInclusive(var5, 1L, 3L) || var5 == 7) {
               return cfs.A(var0, "Cop1 01");
            }
            break;
         case 2:
            int var6 = (var0[3] & 56) >>> 3;
            int var7 = var0[3] & 7;
            switch (var5) {
               case 0:
               case 1:
                  return UT(var0);
               case 2:
               case 3:
               default:
                  return cfs.pC(var0, "Cop1");
               case 4:
               case 5:
                  if (var6 < 2 || var6 < 4 && MathUtil.betweenInclusive(var7, 1L, 3L)) {
                     return mv;
                  }

                  if (var6 == 4 && var7 < 2) {
                     return FE[var7];
                  }

                  return cfs.pC(var0, "Cop1");
               case 6:
                  if (!var1.A() || var2) {
                     int var8 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
                     if (var6 == 0 && !MathUtil.betweenInclusive(var7, 3L, 4L)) {
                        return UT(var0);
                     }

                     if (var6 == 2 && MathUtil.betweenInclusive(var7, 1L, 3L)) {
                        return UT(var0);
                     }

                     if ((var6 == 4 || var6 == 5) && var7 == 0 && var8 == 0) {
                        return sO[var6 - 4];
                     }

                     if (var6 == 5 && var7 >= 4) {
                        return os[var7 - 4];
                     }

                     if (var6 == 6 || var6 == 7) {
                        return UT(var0);
                     }
                  }

                  return cfs.pC(var0, "Cop1");
            }
         case 3:
            return cfs.A(var0, "Cop1 11 BZ.x/BNZ.x");
      }

      return cfs.pC(var0, "Cop1");
   }

   private static cfs UT(byte[] var0) throws ProcessorException {
      int var1 = (var0[3] & 56) >>> 3;
      int var2 = var0[3] & 7;
      int var3 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
      int var4 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      boolean var5 = var4 == 16;
      switch (var1) {
         case 0:
            if (var2 < 4 || var3 == 0) {
               return rl[var2];
            }
            break;
         case 1:
            if (var3 == 0) {
               return z[var2];
            }
            break;
         case 2:
            if (var2 != 5 && var2 != 6 || var3 == 0) {
               if (var2 == 1) {
                  return hK[var0[1] & 3];
               }

               return Ek[var2];
            }
            break;
         case 3:
            if (var2 != 2 && var2 != 3 || var3 == 0) {
               return Er[var2];
            }
            break;
         case 4:
            if ((var2 >= 6 || var3 == 0) && (var2 != 0 || !var5) && (var2 != 1 && var2 != 6 || var5)) {
               return FE[var2];
            }
         case 5:
         default:
            break;
         case 6:
         case 7:
            if ((var0[3] & 192) == 0) {
               return EX;
            }
      }

      return cfs.pC(var0, "Cop1.S/.D");
   }

   public static cfs A(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      int var2 = var1 >>> 3;
      int var3 = var1 & 7;
      switch (var2) {
         case 0:
            return hZ[var3];
         case 1:
            return PR[var3];
         case 2:
         case 3:
            return cfs.A(var0, "Cop2 C2");
         default:
            return cfs.pC(var0, "Cop2");
      }
   }

   public static cfs kS(byte[] var0) throws ProcessorException {
      int var1 = (var0[3] & 56) >>> 3;
      int var2 = var0[3] & 7;
      switch (var1) {
         case 0:
         case 1:
            if ((
                  var1 == 0 && DirectEncodedMemoryArea.get(11, 5).decode(var0) == 0L
                     || var1 == 1 && (var2 == 7 || DirectEncodedMemoryArea.get(6, 5).decode(var0) == 0L)
               )
               && (var2 < 2 || (var2 & 5) == 5)) {
               if (var2 > 2) {
                  var2 >>>= 1;
               }

               return OI[var1 << 2 | var2];
            }
         case 2:
         default:
            break;
         case 3:
            if (var2 == 6) {
               return Bf;
            }
            break;
         case 4:
         case 5:
         case 6:
         case 7:
            if (var2 < 2 || var2 == 6) {
               return ZN[var1 - 4];
            }
      }

      return cfs.pC(var0, "Cop1X");
   }
}
