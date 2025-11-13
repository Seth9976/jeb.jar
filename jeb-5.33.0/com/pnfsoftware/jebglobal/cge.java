package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.io.EndianUtil;

class cge {
   private static final cfs pC = new cfs("NOP");
   private static final cfs A = new cfs("SSNOP");
   private static final cfs kS = new cfs("EHB");
   private static final cfs wS = new cfs("PAUSE");
   private static final cfs[] UT = new cfs[]{
      new cfs("SLL", cfn.ys, cfn.kS, cfl.vP),
      cfs.pC,
      cfs.pC,
      new cfs("SRA", cfn.ys, cfn.kS, cfl.vP),
      cfs.pC("SLLV"),
      new cfs("LSA", cfs.kS, cfn.ys, cfn.pC, cfn.kS, cfl.oT),
      cfs.pC,
      cfs.pC("SRAV")
   };
   private static final cfs[] E = new cfs[]{new cfs("SRL", cfn.ys, cfn.kS, cfl.vP), new cfs("ROTR", cfn.ys, cfn.kS, cfl.vP)};
   private static final cfs[] sY = new cfs[]{cfs.pC("SRLV"), cfs.pC("ROTRV")};
   private static final cfs[] ys = new cfs[]{new cfs("MOVF", cfs.A, cfn.ys, cfn.pC, cfn.UO), new cfs("MOVT", cfs.A, cfn.ys, cfn.pC, cfn.UO)};
   private static final com.pnfsoftware.jeb.corei.parsers.mips.Av ld = new cgf();
   private static final cfs[] gp = new cfs[]{
      new cfs("JR", ld, cgc.pC, cfs.A, cfn.pC),
      new cfs("JALR", ld, cgc.wS, null, cfn.ld, cfn.pC),
      cfs.pC("MOVZ", cfs.ld),
      cfs.pC("MOVN", cfs.ld),
      new cfs("SYSCALL", cfl.rl),
      new cfs("BREAK", cfl.ah, cfl.xC),
      new cfs("SDBBP", cfs.kS, cfl.rl),
      new cfs("SYNC", cfs.sY, cfl.xC)
   };
   private static final cfs oT = new cfs("JR", ld, cgc.pC, null, cfn.pC);
   private static final cfs[] fI = new cfs[]{
      new cfs("MFHI", cfs.A, cfn.ys), new cfs("MTHI", cfs.A, cfn.pC), new cfs("MFLO", cfs.A, cfn.ys), new cfs("MTLO", cfs.A, cfn.pC)
   };
   private static final cfs[] WR = new cfs[]{
      new cfs("DSLLV", cfs.gp, cfn.NS, cfn.vP, cfn.xC),
      new cfs("DLSA", cfs.NS, cfn.NS, cfn.xC, cfn.vP, cfl.oT),
      new cfs("DSRLV", cfs.gp, cfn.NS, cfn.vP, cfn.xC),
      new cfs("DSRAV", cfs.gp, cfn.NS, cfn.vP, cfn.xC),
      cfs.pC,
      cfs.pC,
      new cfs("DROTRV", cfs.WR, cfn.NS, cfn.vP, cfn.xC),
      cfs.pC
   };
   private static final cfs[] NS = new cfs[]{
      new cfs("CLZ", cfs.kS, cfn.ys, cfn.pC),
      new cfs("CLO", cfs.kS, cfn.ys, cfn.pC),
      new cfs("DCLZ", cfs.NS, cfn.ys, cfn.pC),
      new cfs("DCLO", cfs.NS, cfn.ys, cfn.pC)
   };
   private static final cfs[] vP = new cfs[]{
      new cfs("MULT", cfs.A, cfn.ED),
      new cfs("MULTU", cfs.A, cfn.ED),
      new cfs("DIV", cfs.A, cfn.ED),
      new cfs("DIVU", cfs.A, cfn.ED),
      new cfs("DMULT", cfs.oT, cfn.Sc),
      new cfs("DMULTU", cfs.oT, cfn.Sc),
      new cfs("DDIV", cfs.oT, cfn.Sc),
      new cfs("DDIVU", cfs.oT, cfn.Sc)
   };
   private static final cfs[] xC = new cfs[]{
      cfs.pC("MUL", cfs.kS),
      cfs.pC("MUH", cfs.kS),
      cfs.pC("MULU", cfs.kS),
      cfs.pC("MUHU", cfs.kS),
      cfs.pC("DIV", cfs.kS),
      cfs.pC("MOD", cfs.kS),
      cfs.pC("DIVU", cfs.kS),
      cfs.pC("MODU", cfs.kS),
      cfs.A("DMUL", cfs.kS),
      cfs.A("DMUH", cfs.kS),
      cfs.A("DMULU", cfs.kS),
      cfs.A("DMUHU", cfs.kS),
      cfs.A("DDIV", cfs.NS),
      cfs.A("DMOD", cfs.NS),
      cfs.A("DDIVU", cfs.NS),
      cfs.A("DMODU", cfs.NS)
   };
   private static final cfs[] ED = new cfs[]{
      cfs.pC("ADD"), cfs.pC("ADDU"), cfs.pC("SUB"), cfs.pC("SUBU"), cfs.pC("AND"), cfs.pC("OR"), cfs.pC("XOR"), cfs.pC("NOR")
   };
   private static final cfs[] Sc = new cfs[]{
      cfs.pC, cfs.pC, cfs.pC("SLT"), cfs.pC("SLTU"), cfs.A("DADD", cfs.gp), cfs.A("DADDU", cfs.gp), cfs.A("DSUB", cfs.gp), cfs.A("DSUBU", cfs.gp)
   };
   private static final cfs[] ah = new cfs[]{
      cfs.kS("TGE", cfs.sY),
      cfs.kS("TGEU", cfs.sY),
      cfs.kS("TLT", cfs.sY),
      cfs.kS("TLTU", cfs.sY),
      cfs.kS("TEQ", cfs.sY),
      cfs.pC("SELEQZ", cfs.kS),
      cfs.kS("TNE", cfs.sY),
      cfs.pC("SELNEZ")
   };
   private static final cfs[] eP = new cfs[]{
      new cfs("DSLL", cfs.gp, cfn.NS, cfn.vP, cfl.vP),
      cfs.pC,
      new cfs("DSRL", cfs.gp, cfn.NS, cfn.vP, cfl.vP),
      new cfs("DSRA", cfs.gp, cfn.NS, cfn.vP, cfl.vP),
      new cfs("DSLL32", cfs.gp, cfn.NS, cfn.vP, cfl.vP),
      cfs.pC,
      new cfs("DSRL32", cfs.gp, cfn.NS, cfn.vP, cfl.vP),
      new cfs("DSRA32", cfs.gp, cfn.NS, cfn.vP, cfl.vP),
      cfs.pC,
      cfs.pC,
      new cfs("DROTR", cfs.WR, cfn.NS, cfn.vP, cfl.vP),
      cfs.pC,
      cfs.pC,
      cfs.pC,
      new cfs("DROTR32", cfs.WR, cfn.NS, cfn.vP, cfl.vP),
      cfs.pC
   };
   private static final cfs UO = new cfs("MOVE", cfn.ys, cfn.pC);
   private static final cfs Ab = new cfs("NEG", cfn.ys, cfn.kS);
   private static final cfs rl = new cfs("NEGU", cfn.ys, cfn.kS);
   private static final cfs z = new cfs("NOT", cfn.ys, cfn.pC);
   private static final cfs Ek = new cfs("DNEG", cfs.gp, cfn.NS, cfn.vP);
   private static final cfs hK = new cfs("DNEGU", cfs.gp, cfn.NS, cfn.vP);
   private static final cfs Er = cfs.pC("UDI");
   private static final cfs[] FE = new cfs[]{
      new cfs("MADD", cfs.A, cfn.ED),
      new cfs("MADDU", cfs.A, cfn.ED),
      new cfs("MUL", cfs.A, cfn.ah),
      cfs.pC,
      new cfs("MSUB", cfs.A, cfn.ED),
      new cfs("MSUBU", cfs.A, cfn.ED),
      cfs.pC,
      cfs.pC
   };
   private static final cfs[] Aj = new cfs[]{
      new cfs("CLZ", cfs.A, cfn.ys, cfn.pC),
      new cfs("CLO", cfs.A, cfn.ys, cfn.pC),
      cfs.pC,
      cfs.pC,
      new cfs("DCLZ", cfs.fI, cfn.ys, cfn.pC),
      new cfs("DCLO", cfs.fI, cfn.ys, cfn.pC),
      cfs.pC,
      cfs.pC
   };
   private static final cfs EX = new cfs("SDBBP", cfs.A, cfl.rl);
   private static final cfk LM = new cgg();
   private static final cfk mv = new cgh();
   private static final cfk sO = new cgi();
   private static final cfk os = new cgj();
   private static final cfk Cu = new cgk();
   private static final cfk hZ = new cgl();
   private static final cfk[] UW = new cfk[]{cfn.kS, cfn.pC, cfl.vP, os};
   private static final cfk[] PR = new cfk[]{cfn.vP, cfn.xC, cfl.vP, os};
   private static final cfk[] cX = new cfk[]{cfn.vP, cfn.xC, cfl.vP, Cu};
   private static final cfk[] DQ = new cfk[]{cfn.vP, cfn.xC, hZ, os};
   private static final cfk[] ZN = new cfk[]{cfn.kS, cfn.pC, cfl.vP, LM};
   private static final cfk[] OB = new cfk[]{cfn.vP, cfn.xC, cfl.vP, LM};
   private static final cfk[] pF = new cfk[]{cfn.vP, cfn.xC, cfl.vP, mv};
   private static final cfk[] Bc = new cfk[]{cfn.vP, cfn.xC, hZ, sO};
   private static final cge.Av[] OI = new cge.Av[]{
      new cge.Av("EXT", cfs.wS, UW, 0, 31, 1, 32, 1, 32),
      new cge.Av("DEXTM", cfs.WR, cX, 0, 31, 33, 64, 33, 64),
      new cge.Av("DEXTU", cfs.WR, DQ, 32, 63, 1, 32, 33, 64),
      new cge.Av("DEXT", cfs.WR, PR, 0, 31, 1, 32, 1, 63),
      new cge.Av("INS", cfs.wS, ZN, 0, 31, 1, 32, 1, 32),
      new cge.Av("DINSM", cfs.WR, pF, 0, 31, 2, 64, 33, 64),
      new cge.Av("DINSU", cfs.WR, Bc, 32, 63, 1, 32, 33, 64),
      new cge.Av("DINS", cfs.WR, OB, 0, 31, 1, 32, 1, 32)
   };
   private static final cfk Bf = new cfm(cfl.sY, cfn.pC);
   private static final cfk[] Pe = new cfk[]{cfn.kS, Bf};
   private static final cfs[] ck = new cfs[]{
      cfs.pC,
      new cfs("LWLE", cfs.A, Pe),
      new cfs("LWRE", cfs.A, Pe),
      new cfs("CACHEE", cfl.Sc, Bf),
      new cfs("SBE", Pe),
      new cfs("SHE", Pe),
      new cfs("SCE", Pe),
      new cfs("SWE", Pe),
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      new cfs("SCXE", cfs.kS, Pe),
      cfs.pC
   };
   private static final cfs[] RW = new cfs[]{
      cfs.pC,
      new cfs("SWLE", cfs.A, Pe),
      new cfs("SWRE", cfs.A, Pe),
      new cfs("PREFE", cfl.Sc, Bf),
      cfs.pC,
      new cfs("CACHE", cfl.Sc, Bf),
      new cfs("SC", cfs.kS, Pe),
      new cfs("SCD", cfs.NS, Pe),
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      new cfs("SCX", cfs.kS, Pe),
      new cfs("SCDX", cfs.NS, Pe)
   };
   private static final cfs[] e = new cfs[]{
      new cfs("LBUE", Pe),
      new cfs("LHUE", Pe),
      cfs.pC,
      cfs.pC,
      new cfs("LBE", Pe),
      new cfs("LHE", Pe),
      new cfs("LLE", Pe),
      new cfs("LWE", Pe),
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      new cfs("LLXE", cfs.kS, Pe),
      cfs.pC
   };
   private static final cfs[] xM = new cfs[]{
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      new cfs("PREF", cfs.kS, cfl.Sc, Bf),
      new cfs("LL", cfs.kS, Pe),
      new cfs("LLD", cfs.NS, Pe),
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      cfs.pC,
      new cfs("LLX", cfs.kS, Pe),
      new cfs("LLDX", cfs.NS, Pe)
   };
   private static final cfs kU = new cfs("RDHWR", cfs.wS, cfn.kS, cfn.sY, cfl.xC);
   private static final cfs Kq = new cfs("WSBH", cfs.wS, cfn.ys, cfn.kS);
   private static final cfs[] go = new cfs[]{
      new cfs("BITSWAP", cfs.kS, cfn.ys, cfn.kS),
      new cfs("ALIGN", cfs.kS, cfn.ys, cfn.pC, cfn.kS, cfl.gp),
      new cfs("SEB", cfn.ys, cfn.kS),
      new cfs("SEH", cfn.ys, cfn.kS)
   };
   private static final cfs[] JF = new cfs[]{
      new cfs("DBITSWAP", cfs.NS, cfn.NS, cfn.vP),
      cfs.pC,
      new cfs("DSBH", cfs.NS, cfn.NS, cfn.vP),
      cfs.pC,
      cfs.pC,
      new cfs("DSHD", cfs.NS, cfn.NS, cfn.vP),
      cfs.pC,
      cfs.pC
   };
   private static final cfs Nq = new cfs("DALIGN", cfs.NS, cfn.NS, cfn.xC, cfn.vP, cfl.WR);

   public static cfs pC(byte[] var0, com.pnfsoftware.jeb.corei.parsers.mips.Tb var1, boolean var2) throws ProcessorException {
      int var3 = (var0[3] & 56) >>> 3;
      int var4 = var0[3] & 7;
      int var5 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
      int var6 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
      int var7 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      int var8 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
      switch (var3) {
         case 0:
            if (var4 == 0) {
               int var10 = EndianUtil.bigEndianBytesToInt(var0, 0);
               if (var10 == 0) {
                  return pC;
               }

               if (var10 == 64) {
                  return A;
               }

               if (var10 == 192) {
                  return kS;
               }

               if (var10 == 320) {
                  return wS;
               }

               if (var7 != 0) {
                  return cfs.pC(var0, "Special 0x");
               }
            } else {
               if (var4 == 1) {
                  if (var8 == 0 && (var0[1] & 2) == 0) {
                     return ys[var0[1] & 1];
                  }

                  return cfs.pC(var0, "MOVCI");
               }

               if (var4 == 2) {
                  if (var7 >>> 1 != 0) {
                     return cfs.pC(var0, "SRL");
                  }

                  return E[(var0[1] & 32) >>> 5];
               }

               if (var4 == 5) {
                  if (var8 > 3) {
                     return cfs.pC(var0, "LSA");
                  }
               } else if (var4 == 6) {
                  if (var8 >>> 1 != 0) {
                     return cfs.pC(var0, "SRLV");
                  }

                  return sY[(var0[3] & 64) >>> 6];
               }
            }

            if ((var4 != 0 && var4 != 3 || var7 == 0) && (var4 != 4 && var4 != 7 || var8 == 0)) {
               return UT[var4];
            }

            return cfs.pC(var0, "Special 0x");
         case 1:
            if (var4 == 0 && DirectEncodedMemoryArea.get(11, 10).decodeInt(var0) != 0) {
               return cfs.pC(var0, "JR");
            } else {
               if (var4 == 1) {
                  if (var6 != 0) {
                     return cfs.pC(var0, "JALR");
                  }

                  if (var5 == 0 && var1.A()) {
                     return oT;
                  }
               } else {
                  if ((var4 == 2 || var4 == 3) && var8 != 0) {
                     return cfs.pC(var0, "MOVZ/MOVN");
                  }

                  if (var4 == 7 && DirectEncodedMemoryArea.get(11, 15).decodeInt(var0) != 0) {
                     return cfs.pC(var0, "SYNC");
                  }
               }

               return gp[var4];
            }
         case 2:
            if (var4 < 4) {
               if (var6 == 0 && var8 == 0) {
                  if ((var4 & 1) == 0 && var7 == 0 || (var4 & 1) != 0 && var5 == 0) {
                     return fI[var4];
                  }

                  if ((var4 & 1) == 0 && (var7 & 28) == 0 || (var4 & 1) != 0 && (var5 & 28) == 0) {
                     return cga.vP[var4];
                  }
               }

               if (var6 == 0 && var8 == 1) {
                  return NS[var4];
               }

               return cfs.A(var0, "Special 2x");
            } else {
               int var9 = var4 & 3 | (var0[3] & 64) >>> 4;
               if ((var9 == 1 || var8 <= 1) && var8 <= 3) {
                  return WR[var9];
               }

               return cfs.pC(var0, "Special 2x");
            }
         case 3:
            if (var8 == 0 && var5 < 4) {
               return var5 == 0 ? vP[var4] : cga.ED[var4];
            } else {
               if ((var8 & 30) == 2) {
                  return xC[var4 << 1 | var8 & 1];
               }

               return cfs.A(var0, "Special 3x");
            }
         case 4:
            if (var8 != 0) {
               return cfs.pC(var0, "Special 4x");
            } else if (var4 == 2 && var7 == 0) {
               return Ab;
            } else if (var4 == 3 && var7 == 0) {
               return rl;
            } else if ((var4 == 1 || var4 == 5) && var6 == 0) {
               if (var5 == var7) {
                  return pC;
               }

               return UO;
            } else {
               if (var4 == 7 && var6 == 0) {
                  return z;
               }

               return ED[var4];
            }
         case 5:
            if (var8 != 0) {
               return cfs.pC(var0, "Special 5x");
            } else if (var4 == 6 && var7 == 0) {
               return Ek;
            } else {
               if (var4 == 7 && var7 == 0) {
                  return hK;
               }

               return Sc[var4];
            }
         case 6:
            return ah[var4];
         case 7:
            if (var7 < 2) {
               return eP[var4 | var7 << 3];
            }

            return cfs.A(var0, "Special 7x");
         default:
            return null;
      }
   }

   public static cfs A(byte[] var0, com.pnfsoftware.jeb.corei.parsers.mips.Tb var1, boolean var2) throws ProcessorException {
      if (var2 || !var1.A()) {
         int var3 = (var0[3] & 56) >>> 3;
         int var4 = var0[3] & 7;
         switch (var3) {
            case 0:
               if (var4 < 6 && var4 != 3) {
                  int var5 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
                  int var6 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
                  if (var5 == 0 && (var4 == 2 || var6 < 4)) {
                     return var6 != 0 && var4 != 2 ? cga.xC[var4] : FE[var4];
                  }

                  return cfs.pC(var0, "special2 000");
               }
               break;
            case 4:
               if ((var0[3] & 12) == 0 && (var0[2] & 1) == 0) {
                  return Aj[var4];
               }
               break;
            case 7:
               if (var4 == 7) {
                  return EX;
               }
         }
      }

      return Er;
   }

   public static cfs pC(byte[] var0, int var1) throws ProcessorException {
      int var2 = (var0[3] & 56) >>> 3;
      int var3 = var0[3] & 7;
      int var4 = var3 | (var0[3] & 64) >>> 3;
      switch (var2) {
         case 0:
            return OI[var3].pC(var0, var1);
         case 1:
            if (var3 == 2) {
               return cga.pC[DirectEncodedMemoryArea.get(6, 5).decodeInt(var0)];
            } else if (var3 == 4) {
               return cga.UT;
            } else {
               if (var3 == 5) {
                  return cga.E;
               }

               return null;
            }
         case 2:
            int var9 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
            switch (var3) {
               case 0:
                  int var16 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
                  if (var9 == 20 && var16 != 0) {
                     return null;
                  }

                  return cga.sY[var9];
               case 1:
                  int var15 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
                  return var15 == 0 || (var9 < 0 || var9 > 2) && (var9 < 8 || var9 > 10) ? cga.ys[var9] : null;
               case 2:
                  int var14 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
                  if (var9 == 2 && (var0[0] & 3) != 0) {
                     return null;
                  } else {
                     if (var9 != 2 && var9 != 10 && var14 != 0) {
                        return null;
                     }

                     return cga.ld[var9];
                  }
               case 3:
                  if ((var9 & 26) == 0 && (var0[0] & 3) != 0) {
                     return null;
                  } else {
                     if (((var9 & 26) == 8 || var9 == 25) && (var0[0] & 2) != 0) {
                        return null;
                     }

                     return cga.gp[var9];
                  }
               case 4:
                  int var13 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
                  if (var9 == 20 && var13 != 0) {
                     return null;
                  }

                  return cga.Sc[var9];
               case 5:
                  int var12 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
                  if (var12 == 0 || (var9 < 0 || var9 > 2) && (var9 < 8 || var9 > 10) && (var9 < 16 || var9 > 18)) {
                     return cga.ah[var9];
                  }

                  return null;
               case 6:
                  int var11 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
                  if (var9 != 10 && var9 != 18) {
                     if (var9 == 2) {
                        if (var0[0] != 124) {
                           return null;
                        }
                     } else if (var11 != 0) {
                        return null;
                     }
                  }

                  return cga.eP[var9];
               case 7:
                  if ((var9 & 26) == 0 && (var0[0] & 3) != 0) {
                     return null;
                  } else {
                     if (((var9 & 26) == 8 || var9 == 25) && (var0[0] & 2) != 0) {
                        return null;
                     }

                     return cga.UO[var9];
                  }
               default:
                  return null;
            }
         case 3:
            if (var3 == 0) {
               int var8 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
               return cga.oT[var8];
            }

            return ck[var4];
         case 4:
            if (var3 == 0) {
               return pC(var0);
            } else {
               if (var3 == 4) {
                  return A(var0);
               }

               return RW[var4];
            }
         case 5:
            return e[var4];
         case 6:
            int var7 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
            switch (var3) {
               case 0:
                  if ((var0[2] & 224) != 0) {
                     return null;
                  }

                  return cga.fI[var7];
               case 1:
                  if (var7 == 16 && (var0[2] & 224) != 0) {
                     return null;
                  }

                  return cga.WR[var7];
               case 2:
               case 3:
               case 6:
               case 7:
               default:
                  return xM[var4];
               case 4:
                  if ((var0[2] & 224) != 0) {
                     return null;
                  }

                  return cga.Ab[var7];
               case 5:
                  if (var4 == 13) {
                     if (var7 == 16 && (var0[2] & 192) != 0) {
                        return null;
                     }

                     return cga.rl[var7];
                  }

                  return xM[var4];
            }
         case 7:
            int var5 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
            if (var3 == 0) {
               int var10 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
               if (var10 < 18 || var10 > 19) {
                  if ((var0[2] & 224) != 0) {
                     return null;
                  }

                  if ((var10 == 27 || var10 == 31) && (var0[1] & 31) != 0) {
                     return null;
                  }

                  if (var10 == 26 && (var0[1] & 15) != 0) {
                     return null;
                  }
               }

               return cga.NS[var10];
            } else if (var3 == 4) {
               int var6 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
               if ((var0[2] & 224) != 0) {
                  return null;
               } else if ((var6 == 27 || var6 == 31) && (var0[1] & 31) != 0) {
                  return null;
               } else {
                  if (var6 == 26 && (var0[1] & 7) != 0) {
                     return null;
                  }

                  return cga.z[var6];
               }
            } else {
               if (var3 == 3 && var5 == 0 && (var0[2] & 6) == 0) {
                  return kU;
               }

               return cfs.pC(var0, "Special3 7x");
            }
         default:
            return cfs.A(var0, "Special3");
      }
   }

   private static cfs pC(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(9, 2).decodeInt(var0);
      int var2 = DirectEncodedMemoryArea.get(6, 3).decodeInt(var0);
      int var3 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var1) {
         case 0:
            if (var2 == 0 && var3 == 0) {
               return go[var1];
            }

            if (var2 == 2 && var3 == 0) {
               return Kq;
            }
            break;
         case 1:
            if (var2 < 4) {
               return go[var1];
            }
            break;
         case 2:
         case 3:
            if (var2 == 0 && var3 == 0) {
               return go[var1];
            }
      }

      return cfs.pC(var0, "Special3 BSHFL");
   }

   private static cfs A(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(9, 2).decodeInt(var0);
      int var2 = DirectEncodedMemoryArea.get(6, 3).decodeInt(var0);
      int var3 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var1) {
         case 0:
            if (var3 == 0) {
               return JF[var2];
            }
         default:
            return cfs.pC(var0, "Special3 DBSHFL");
         case 1:
            return Nq;
      }
   }

   private static class Av {
      private cfs pC;
      private int A;
      private int kS;
      private int wS;
      private int UT;
      private int E;
      private int sY;

      public Av(String var1, com.pnfsoftware.jeb.corei.parsers.mips.Tb[] var2, cfk[] var3, int var4, int var5, int var6, int var7, int var8, int var9) {
         this.pC = new cfs(var1, var2, var3);
         this.A = var4;
         this.kS = var5;
         this.wS = var6;
         this.UT = var7;
         this.E = var8;
         this.sY = var9;
      }

      public cfs pC(byte[] var1, int var2) throws ProcessorException {
         cfj[] var3 = this.pC.pC(var1, var2);
         int var4 = (int)var3[2].getOperandValue();
         int var5 = (int)var3[3].getOperandValue();
         int var6 = var4 + var5;
         return this.A <= var4 && var4 <= this.kS && this.wS <= var5 && var5 <= this.UT && this.E <= var6 && var6 <= this.sY ? this.pC : null;
      }
   }
}
