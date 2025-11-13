package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.io.EndianUtil;

class cmr {
   private static final cmf q = new cmf("NOP");
   private static final cmf RF = new cmf("SSNOP");
   private static final cmf xK = new cmf("EHB");
   private static final cmf Dw = new cmf("PAUSE");
   private static final cmf[] Uv = new cmf[]{
      new cmf("SLL", clz.nf, clz.xK, clx.LK),
      cmf.q,
      cmf.q,
      new cmf("SRA", clz.nf, clz.xK, clx.LK),
      cmf.q("SLLV"),
      new cmf("LSA", cmf.xK, clz.nf, clz.q, clz.xK, clx.lm),
      cmf.q,
      cmf.q("SRAV")
   };
   private static final cmf[] oW = new cmf[]{new cmf("SRL", clz.nf, clz.xK, clx.LK), new cmf("ROTR", clz.nf, clz.xK, clx.LK)};
   private static final cmf[] gO = new cmf[]{cmf.q("SRLV"), cmf.q("ROTRV")};
   private static final cmf[] nf = new cmf[]{new cmf("MOVF", cmf.RF, clz.nf, clz.q, clz.oQ), new cmf("MOVT", cmf.RF, clz.nf, clz.q, clz.oQ)};
   private static final ckh gP = new cms();
   private static final cmf[] za = new cmf[]{
      new cmf("JR", gP, cmp.q, cmf.RF, clz.q),
      new cmf("JALR", gP, cmp.Dw, null, clz.gP, clz.q),
      cmf.q("MOVZ", cmf.gP),
      cmf.q("MOVN", cmf.gP),
      new cmf("SYSCALL", clx.KT),
      new cmf("BREAK", clx.Me, clx.io),
      new cmf("SDBBP", cmf.xK, clx.KT),
      new cmf("SYNC", cmf.gO, clx.io)
   };
   private static final cmf lm = new cmf("JR", gP, cmp.q, null, clz.q);
   private static final cmf[] zz = new cmf[]{
      new cmf("MFHI", cmf.RF, clz.nf), new cmf("MTHI", cmf.RF, clz.q), new cmf("MFLO", cmf.RF, clz.nf), new cmf("MTLO", cmf.RF, clz.q)
   };
   private static final cmf[] JY = new cmf[]{
      new cmf("DSLLV", cmf.za, clz.HF, clz.LK, clz.io),
      new cmf("DLSA", cmf.HF, clz.HF, clz.io, clz.LK, clx.lm),
      new cmf("DSRLV", cmf.za, clz.HF, clz.LK, clz.io),
      new cmf("DSRAV", cmf.za, clz.HF, clz.LK, clz.io),
      cmf.q,
      cmf.q,
      new cmf("DROTRV", cmf.JY, clz.HF, clz.LK, clz.io),
      cmf.q
   };
   private static final cmf[] HF = new cmf[]{
      new cmf("CLZ", cmf.xK, clz.nf, clz.q),
      new cmf("CLO", cmf.xK, clz.nf, clz.q),
      new cmf("DCLZ", cmf.HF, clz.nf, clz.q),
      new cmf("DCLO", cmf.HF, clz.nf, clz.q)
   };
   private static final cmf[] LK = new cmf[]{
      new cmf("MULT", cmf.RF, clz.qa),
      new cmf("MULTU", cmf.RF, clz.qa),
      new cmf("DIV", cmf.RF, clz.qa),
      new cmf("DIVU", cmf.RF, clz.qa),
      new cmf("DMULT", cmf.lm, clz.Hk),
      new cmf("DMULTU", cmf.lm, clz.Hk),
      new cmf("DDIV", cmf.lm, clz.Hk),
      new cmf("DDIVU", cmf.lm, clz.Hk)
   };
   private static final cmf[] io = new cmf[]{
      cmf.q("MUL", cmf.xK),
      cmf.q("MUH", cmf.xK),
      cmf.q("MULU", cmf.xK),
      cmf.q("MUHU", cmf.xK),
      cmf.q("DIV", cmf.xK),
      cmf.q("MOD", cmf.xK),
      cmf.q("DIVU", cmf.xK),
      cmf.q("MODU", cmf.xK),
      cmf.RF("DMUL", cmf.xK),
      cmf.RF("DMUH", cmf.xK),
      cmf.RF("DMULU", cmf.xK),
      cmf.RF("DMUHU", cmf.xK),
      cmf.RF("DDIV", cmf.HF),
      cmf.RF("DMOD", cmf.HF),
      cmf.RF("DDIVU", cmf.HF),
      cmf.RF("DMODU", cmf.HF)
   };
   private static final cmf[] qa = new cmf[]{cmf.q("ADD"), cmf.q("ADDU"), cmf.q("SUB"), cmf.q("SUBU"), cmf.q("AND"), cmf.q("OR"), cmf.q("XOR"), cmf.q("NOR")};
   private static final cmf[] Hk = new cmf[]{
      cmf.q, cmf.q, cmf.q("SLT"), cmf.q("SLTU"), cmf.RF("DADD", cmf.za), cmf.RF("DADDU", cmf.za), cmf.RF("DSUB", cmf.za), cmf.RF("DSUBU", cmf.za)
   };
   private static final cmf[] Me = new cmf[]{
      cmf.xK("TGE", cmf.gO),
      cmf.xK("TGEU", cmf.gO),
      cmf.xK("TLT", cmf.gO),
      cmf.xK("TLTU", cmf.gO),
      cmf.xK("TEQ", cmf.gO),
      cmf.q("SELEQZ", cmf.xK),
      cmf.xK("TNE", cmf.gO),
      cmf.q("SELNEZ")
   };
   private static final cmf[] PV = new cmf[]{
      new cmf("DSLL", cmf.za, clz.HF, clz.LK, clx.LK),
      cmf.q,
      new cmf("DSRL", cmf.za, clz.HF, clz.LK, clx.LK),
      new cmf("DSRA", cmf.za, clz.HF, clz.LK, clx.LK),
      new cmf("DSLL32", cmf.za, clz.HF, clz.LK, clx.LK),
      cmf.q,
      new cmf("DSRL32", cmf.za, clz.HF, clz.LK, clx.LK),
      new cmf("DSRA32", cmf.za, clz.HF, clz.LK, clx.LK),
      cmf.q,
      cmf.q,
      new cmf("DROTR", cmf.JY, clz.HF, clz.LK, clx.LK),
      cmf.q,
      cmf.q,
      cmf.q,
      new cmf("DROTR32", cmf.JY, clz.HF, clz.LK, clx.LK),
      cmf.q
   };
   private static final cmf oQ = new cmf("MOVE", clz.nf, clz.q);
   private static final cmf xW = new cmf("NEG", clz.nf, clz.xK);
   private static final cmf KT = new cmf("NEGU", clz.nf, clz.xK);
   private static final cmf Gf = new cmf("NOT", clz.nf, clz.q);
   private static final cmf Ef = new cmf("DNEG", cmf.za, clz.HF, clz.LK);
   private static final cmf cC = new cmf("DNEGU", cmf.za, clz.HF, clz.LK);
   private static final cmf sH = cmf.q("UDI");
   private static final cmf[] CE = new cmf[]{
      new cmf("MADD", cmf.RF, clz.qa),
      new cmf("MADDU", cmf.RF, clz.qa),
      new cmf("MUL", cmf.RF, clz.Me),
      cmf.q,
      new cmf("MSUB", cmf.RF, clz.qa),
      new cmf("MSUBU", cmf.RF, clz.qa),
      cmf.q,
      cmf.q
   };
   private static final cmf[] wF = new cmf[]{
      new cmf("CLZ", cmf.RF, clz.nf, clz.q),
      new cmf("CLO", cmf.RF, clz.nf, clz.q),
      cmf.q,
      cmf.q,
      new cmf("DCLZ", cmf.zz, clz.nf, clz.q),
      new cmf("DCLO", cmf.zz, clz.nf, clz.q),
      cmf.q,
      cmf.q
   };
   private static final cmf If = new cmf("SDBBP", cmf.RF, clx.KT);
   private static final clw Dz = new cmt();
   private static final clw mI = new cmu();
   private static final clw jq = new cmv();
   private static final clw ui = new cmw();
   private static final clw TX = new cmx();
   private static final clw Rr = new cmy();
   private static final clw[] EB = new clw[]{clz.xK, clz.q, clx.LK, ui};
   private static final clw[] Xo = new clw[]{clz.LK, clz.io, clx.LK, ui};
   private static final clw[] Bu = new clw[]{clz.LK, clz.io, clx.LK, TX};
   private static final clw[] IN = new clw[]{clz.LK, clz.io, Rr, ui};
   private static final clw[] rL = new clw[]{clz.xK, clz.q, clx.LK, Dz};
   private static final clw[] eJ = new clw[]{clz.LK, clz.io, clx.LK, Dz};
   private static final clw[] YN = new clw[]{clz.LK, clz.io, clx.LK, mI};
   private static final clw[] Rv = new clw[]{clz.LK, clz.io, Rr, jq};
   private static final cmr.eo[] zx = new cmr.eo[]{
      new cmr.eo("EXT", cmf.Dw, EB, 0, 31, 1, 32, 1, 32),
      new cmr.eo("DEXTM", cmf.JY, Bu, 0, 31, 33, 64, 33, 64),
      new cmr.eo("DEXTU", cmf.JY, IN, 32, 63, 1, 32, 33, 64),
      new cmr.eo("DEXT", cmf.JY, Xo, 0, 31, 1, 32, 1, 63),
      new cmr.eo("INS", cmf.Dw, rL, 0, 31, 1, 32, 1, 32),
      new cmr.eo("DINSM", cmf.JY, YN, 0, 31, 2, 64, 33, 64),
      new cmr.eo("DINSU", cmf.JY, Rv, 32, 63, 1, 32, 33, 64),
      new cmr.eo("DINS", cmf.JY, eJ, 0, 31, 1, 32, 1, 32)
   };
   private static final clw ZT = new cly(clx.gO, clz.q);
   private static final clw[] Ri = new clw[]{clz.xK, ZT};
   private static final cmf[] GY = new cmf[]{
      cmf.q,
      new cmf("LWLE", cmf.RF, Ri),
      new cmf("LWRE", cmf.RF, Ri),
      new cmf("CACHEE", clx.Hk, ZT),
      new cmf("SBE", Ri),
      new cmf("SHE", Ri),
      new cmf("SCE", Ri),
      new cmf("SWE", Ri),
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      new cmf("SCXE", cmf.xK, Ri),
      cmf.q
   };
   private static final cmf[] Wx = new cmf[]{
      cmf.q,
      new cmf("SWLE", cmf.RF, Ri),
      new cmf("SWRE", cmf.RF, Ri),
      new cmf("PREFE", clx.Hk, ZT),
      cmf.q,
      new cmf("CACHE", clx.Hk, ZT),
      new cmf("SC", cmf.xK, Ri),
      new cmf("SCD", cmf.HF, Ri),
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      new cmf("SCX", cmf.xK, Ri),
      new cmf("SCDX", cmf.HF, Ri)
   };
   private static final cmf[] AB = new cmf[]{
      new cmf("LBUE", Ri),
      new cmf("LHUE", Ri),
      cmf.q,
      cmf.q,
      new cmf("LBE", Ri),
      new cmf("LHE", Ri),
      new cmf("LLE", Ri),
      new cmf("LWE", Ri),
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      new cmf("LLXE", cmf.xK, Ri),
      cmf.q
   };
   private static final cmf[] CY = new cmf[]{
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      new cmf("PREF", cmf.xK, clx.Hk, ZT),
      new cmf("LL", cmf.xK, Ri),
      new cmf("LLD", cmf.HF, Ri),
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      cmf.q,
      new cmf("LLX", cmf.xK, Ri),
      new cmf("LLDX", cmf.HF, Ri)
   };
   private static final cmf WI = new cmf("RDHWR", cmf.Dw, clz.xK, clz.gO, clx.io);
   private static final cmf Tq = new cmf("WSBH", cmf.Dw, clz.nf, clz.xK);
   private static final cmf[] Yp = new cmf[]{
      new cmf("BITSWAP", cmf.xK, clz.nf, clz.xK),
      new cmf("ALIGN", cmf.xK, clz.nf, clz.q, clz.xK, clx.za),
      new cmf("SEB", clz.nf, clz.xK),
      new cmf("SEH", clz.nf, clz.xK)
   };
   private static final cmf[] Gu = new cmf[]{
      new cmf("DBITSWAP", cmf.HF, clz.HF, clz.LK),
      cmf.q,
      new cmf("DSBH", cmf.HF, clz.HF, clz.LK),
      cmf.q,
      cmf.q,
      new cmf("DSHD", cmf.HF, clz.HF, clz.LK),
      cmf.q,
      cmf.q
   };
   private static final cmf nY = new cmf("DALIGN", cmf.HF, clz.HF, clz.io, clz.LK, clx.JY);

   public static cmf q(byte[] var0, clg var1, boolean var2) throws ProcessorException {
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
                  return q;
               }

               if (var10 == 64) {
                  return RF;
               }

               if (var10 == 192) {
                  return xK;
               }

               if (var10 == 320) {
                  return Dw;
               }

               if (var7 != 0) {
                  return cmf.q(var0, "Special 0x");
               }
            } else {
               if (var4 == 1) {
                  if (var8 == 0 && (var0[1] & 2) == 0) {
                     return nf[var0[1] & 1];
                  }

                  return cmf.q(var0, "MOVCI");
               }

               if (var4 == 2) {
                  if (var7 >>> 1 != 0) {
                     return cmf.q(var0, "SRL");
                  }

                  return oW[(var0[1] & 32) >>> 5];
               }

               if (var4 == 5) {
                  if (var8 > 3) {
                     return cmf.q(var0, "LSA");
                  }
               } else if (var4 == 6) {
                  if (var8 >>> 1 != 0) {
                     return cmf.q(var0, "SRLV");
                  }

                  return gO[(var0[3] & 64) >>> 6];
               }
            }

            if ((var4 != 0 && var4 != 3 || var7 == 0) && (var4 != 4 && var4 != 7 || var8 == 0)) {
               return Uv[var4];
            }

            return cmf.q(var0, "Special 0x");
         case 1:
            if (var4 == 0 && DirectEncodedMemoryArea.get(11, 10).decodeInt(var0) != 0) {
               return cmf.q(var0, "JR");
            } else {
               if (var4 == 1) {
                  if (var6 != 0) {
                     return cmf.q(var0, "JALR");
                  }

                  if (var5 == 0 && var1.RF()) {
                     return lm;
                  }
               } else {
                  if ((var4 == 2 || var4 == 3) && var8 != 0) {
                     return cmf.q(var0, "MOVZ/MOVN");
                  }

                  if (var4 == 7 && DirectEncodedMemoryArea.get(11, 15).decodeInt(var0) != 0) {
                     return cmf.q(var0, "SYNC");
                  }
               }

               return za[var4];
            }
         case 2:
            if (var4 < 4) {
               if (var6 == 0 && var8 == 0) {
                  if ((var4 & 1) == 0 && var7 == 0 || (var4 & 1) != 0 && var5 == 0) {
                     return zz[var4];
                  }

                  if ((var4 & 1) == 0 && (var7 & 28) == 0 || (var4 & 1) != 0 && (var5 & 28) == 0) {
                     return cmn.LK[var4];
                  }
               }

               if (var6 == 0 && var8 == 1) {
                  return HF[var4];
               }

               return cmf.RF(var0, "Special 2x");
            } else {
               int var9 = var4 & 3 | (var0[3] & 64) >>> 4;
               if ((var9 == 1 || var8 <= 1) && var8 <= 3) {
                  return JY[var9];
               }

               return cmf.q(var0, "Special 2x");
            }
         case 3:
            if (var8 == 0 && var5 < 4) {
               return var5 == 0 ? LK[var4] : cmn.qa[var4];
            } else {
               if ((var8 & 30) == 2) {
                  return io[var4 << 1 | var8 & 1];
               }

               return cmf.RF(var0, "Special 3x");
            }
         case 4:
            if (var8 != 0) {
               return cmf.q(var0, "Special 4x");
            } else if (var4 == 2 && var7 == 0) {
               return xW;
            } else if (var4 == 3 && var7 == 0) {
               return KT;
            } else if ((var4 == 1 || var4 == 5) && var6 == 0) {
               if (var5 == var7) {
                  return q;
               }

               return oQ;
            } else {
               if (var4 == 7 && var6 == 0) {
                  return Gf;
               }

               return qa[var4];
            }
         case 5:
            if (var8 != 0) {
               return cmf.q(var0, "Special 5x");
            } else if (var4 == 6 && var7 == 0) {
               return Ef;
            } else {
               if (var4 == 7 && var7 == 0) {
                  return cC;
               }

               return Hk[var4];
            }
         case 6:
            return Me[var4];
         case 7:
            if (var7 < 2) {
               return PV[var4 | var7 << 3];
            }

            return cmf.RF(var0, "Special 7x");
         default:
            return null;
      }
   }

   public static cmf RF(byte[] var0, clg var1, boolean var2) throws ProcessorException {
      if (var2 || !var1.RF()) {
         int var3 = (var0[3] & 56) >>> 3;
         int var4 = var0[3] & 7;
         switch (var3) {
            case 0:
               if (var4 < 6 && var4 != 3) {
                  int var5 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
                  int var6 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
                  if (var5 == 0 && (var4 == 2 || var6 < 4)) {
                     return var6 != 0 && var4 != 2 ? cmn.io[var4] : CE[var4];
                  }

                  return cmf.q(var0, "special2 000");
               }
               break;
            case 4:
               if ((var0[3] & 12) == 0 && (var0[2] & 1) == 0) {
                  return wF[var4];
               }
               break;
            case 7:
               if (var4 == 7) {
                  return If;
               }
         }
      }

      return sH;
   }

   public static cmf q(byte[] var0, int var1) throws ProcessorException {
      int var2 = (var0[3] & 56) >>> 3;
      int var3 = var0[3] & 7;
      int var4 = var3 | (var0[3] & 64) >>> 3;
      switch (var2) {
         case 0:
            return zx[var3].q(var0, var1);
         case 1:
            if (var3 == 2) {
               return cmn.q[DirectEncodedMemoryArea.get(6, 5).decodeInt(var0)];
            } else if (var3 == 4) {
               return cmn.Uv;
            } else {
               if (var3 == 5) {
                  return cmn.oW;
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

                  return cmn.gO[var9];
               case 1:
                  int var15 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
                  return var15 == 0 || (var9 < 0 || var9 > 2) && (var9 < 8 || var9 > 10) ? cmn.nf[var9] : null;
               case 2:
                  int var14 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
                  if (var9 == 2 && (var0[0] & 3) != 0) {
                     return null;
                  } else {
                     if (var9 != 2 && var9 != 10 && var14 != 0) {
                        return null;
                     }

                     return cmn.gP[var9];
                  }
               case 3:
                  if ((var9 & 26) == 0 && (var0[0] & 3) != 0) {
                     return null;
                  } else {
                     if (((var9 & 26) == 8 || var9 == 25) && (var0[0] & 2) != 0) {
                        return null;
                     }

                     return cmn.za[var9];
                  }
               case 4:
                  int var13 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
                  if (var9 == 20 && var13 != 0) {
                     return null;
                  }

                  return cmn.Hk[var9];
               case 5:
                  int var12 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
                  if (var12 == 0 || (var9 < 0 || var9 > 2) && (var9 < 8 || var9 > 10) && (var9 < 16 || var9 > 18)) {
                     return cmn.Me[var9];
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

                  return cmn.PV[var9];
               case 7:
                  if ((var9 & 26) == 0 && (var0[0] & 3) != 0) {
                     return null;
                  } else {
                     if (((var9 & 26) == 8 || var9 == 25) && (var0[0] & 2) != 0) {
                        return null;
                     }

                     return cmn.oQ[var9];
                  }
               default:
                  return null;
            }
         case 3:
            if (var3 == 0) {
               int var8 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
               return cmn.lm[var8];
            }

            return GY[var4];
         case 4:
            if (var3 == 0) {
               return q(var0);
            } else {
               if (var3 == 4) {
                  return RF(var0);
               }

               return Wx[var4];
            }
         case 5:
            return AB[var4];
         case 6:
            int var7 = DirectEncodedMemoryArea.get(6, 5).decodeInt(var0);
            switch (var3) {
               case 0:
                  if ((var0[2] & 224) != 0) {
                     return null;
                  }

                  return cmn.zz[var7];
               case 1:
                  if (var7 == 16 && (var0[2] & 224) != 0) {
                     return null;
                  }

                  return cmn.JY[var7];
               case 2:
               case 3:
               case 6:
               case 7:
               default:
                  return CY[var4];
               case 4:
                  if ((var0[2] & 224) != 0) {
                     return null;
                  }

                  return cmn.xW[var7];
               case 5:
                  if (var4 == 13) {
                     if (var7 == 16 && (var0[2] & 192) != 0) {
                        return null;
                     }

                     return cmn.KT[var7];
                  }

                  return CY[var4];
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

               return cmn.HF[var10];
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

                  return cmn.Gf[var6];
               }
            } else {
               if (var3 == 3 && var5 == 0 && (var0[2] & 6) == 0) {
                  return WI;
               }

               return cmf.q(var0, "Special3 7x");
            }
         default:
            return cmf.RF(var0, "Special3");
      }
   }

   private static cmf q(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(9, 2).decodeInt(var0);
      int var2 = DirectEncodedMemoryArea.get(6, 3).decodeInt(var0);
      int var3 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var1) {
         case 0:
            if (var2 == 0 && var3 == 0) {
               return Yp[var1];
            }

            if (var2 == 2 && var3 == 0) {
               return Tq;
            }
            break;
         case 1:
            if (var2 < 4) {
               return Yp[var1];
            }
            break;
         case 2:
         case 3:
            if (var2 == 0 && var3 == 0) {
               return Yp[var1];
            }
      }

      return cmf.q(var0, "Special3 BSHFL");
   }

   private static cmf RF(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(9, 2).decodeInt(var0);
      int var2 = DirectEncodedMemoryArea.get(6, 3).decodeInt(var0);
      int var3 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var1) {
         case 0:
            if (var3 == 0) {
               return Gu[var2];
            }
         default:
            return cmf.q(var0, "Special3 DBSHFL");
         case 1:
            return nY;
      }
   }

   private static class eo {
      private cmf q;
      private int RF;
      private int xK;
      private int Dw;
      private int Uv;
      private int oW;
      private int gO;

      public eo(String var1, clg[] var2, clw[] var3, int var4, int var5, int var6, int var7, int var8, int var9) {
         this.q = new cmf(var1, var2, var3);
         this.RF = var4;
         this.xK = var5;
         this.Dw = var6;
         this.Uv = var7;
         this.oW = var8;
         this.gO = var9;
      }

      public cmf q(byte[] var1, int var2) throws ProcessorException {
         clv[] var3 = this.q.RF(var1, var2);
         int var4 = (int)var3[2].getOperandValue();
         int var5 = (int)var3[3].getOperandValue();
         int var6 = var4 + var5;
         return this.RF <= var4 && var4 <= this.xK && this.Dw <= var5 && var5 <= this.Uv && this.oW <= var6 && var6 <= this.gO ? this.q : null;
      }
   }
}
