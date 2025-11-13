package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class cmh {
   private static final clw[] RF = new clw[]{clz.xK, clz.nf, clx.zz};
   private static final cmf[] xK = new cmf[]{
      new cmf("MFC0", RF),
      new cmf("DMFC0", cmf.za, RF),
      new cmf("MFHC0", cmf.Uv, RF),
      cmf.q,
      new cmf("MTC0", RF),
      new cmf("DMTC0", cmf.za, RF),
      new cmf("MTHC0", cmf.Uv, RF),
      cmf.q
   };
   private static final cmf[] Dw = new cmf[]{new cmf("RDPGPR", cmf.Dw, clz.nf, clz.xK), new cmf("WRPGPR", cmf.Dw, clz.nf, clz.xK)};
   private static final cmf Uv = new cmf("DI", cmf.Dw, clz.Dw);
   private static final cmf oW = new cmf("EI", cmf.Dw, clz.Dw);
   private static final cmf[] gO = new cmf[]{cmf.q, new cmf("TLBR"), new cmf("TLBWI"), new cmf("TLBINV"), new cmf("TLBINVF"), cmf.q, new cmf("TLBWR"), cmf.q};
   static final clt q = new clt(1);
   private static final cmf nf = new cmf("TLBP");
   private static final cmf gP = new cmf("ERET", q);
   private static final cmf za = new cmf("ERETNC", q);
   private static final cmf lm = new cmf("DERET", q);
   private static final cmf zz = new cmf("WAIT");
   private static final clw[] JY = new clw[]{clz.xK, clz.za};
   private static final cmf[] HF = new cmf[]{
      new cmf("MFC1", JY),
      new cmf("DMFC1", cmf.za, JY),
      new cmf("CFC1", JY),
      new cmf("MFHC1", cmf.Dw, JY),
      new cmf("MTC1", JY),
      new cmf("DMTC1", cmf.za, JY),
      new cmf("CTC1", JY),
      new cmf("MTHC1", cmf.Dw, JY)
   };
   private static final ckh LK = new cmi();
   private static final clw io = clz.xW;
   private static final cmf qa = new cmf("BC1", LK, cmp.q, cmb.io, cmf.RF, io, clx.RF);
   private static final cmf Hk = new cmf("BC1EQZ", cmp.q, cmb.HF, cmf.xK, clz.Uv, clx.RF);
   private static final cmf Me = new cmf("BC1NEZ", cmp.q, cmb.LK, cmf.xK, clz.Uv, clx.RF);
   private static final ckh PV = new cmj();
   private static final clw[] oQ = new clw[]{clz.lm, clz.za, clz.Uv};
   private static final clw[] xW = new clw[]{clz.lm, clz.za};
   private static final cmf[] KT = new cmf[]{
      new cmf("ADD", PV, null, oQ),
      new cmf("SUB", PV, null, oQ),
      new cmf("MUL", PV, null, oQ),
      new cmf("DIV", PV, null, oQ),
      new cmf("SQRT", PV, null, xW),
      new cmf("ABS", PV, null, xW),
      new cmf("MOV", PV, null, xW),
      new cmf("NEG", PV, null, xW)
   };
   private static final cmf[] Gf = new cmf[]{
      new cmf("ROUND.L", PV, cmf.Dw, xW),
      new cmf("TRUNC.L", PV, cmf.Dw, xW),
      new cmf("CEIL.L", PV, cmf.Dw, xW),
      new cmf("FLOOR.L", PV, cmf.Dw, xW),
      new cmf("ROUND.W", PV, null, xW),
      new cmf("TRUNC.W", PV, null, xW),
      new cmf("CEIL.W", PV, null, xW),
      new cmf("FLOOR.W", PV, null, xW)
   };
   private static final cmf[] Ef = new cmf[]{
      new cmf("SEL", PV, cmf.xK, oQ),
      cmf.q,
      new cmf("MOVZ", PV, cmf.RF, clz.lm, clz.za, clz.xK),
      new cmf("MOVN", PV, cmf.RF, clz.lm, clz.za, clz.xK),
      new cmf("SELEQZ", PV, cmf.xK, oQ),
      new cmf("RECIP", PV, cmf.Dw, xW),
      new cmf("RSQRT", PV, cmf.Dw, xW),
      new cmf("SELNEZ", PV, cmf.xK, oQ)
   };
   private static final cmf[] cC = new cmf[]{
      new cmf("MOVF", PV, cmf.RF, clz.lm, clz.za, clz.oQ), new cmf("MOVT", PV, cmf.RF, clz.lm, clz.za, clz.oQ), cmf.q, cmf.q
   };
   private static final cmf[] sH = new cmf[]{
      new cmf("MADDF", PV, cmf.xK, oQ),
      new cmf("MSUBF", PV, cmf.xK, oQ),
      new cmf("RINT", PV, cmf.xK, xW),
      new cmf("CLASS", PV, cmf.xK, xW),
      new cmf("MIN", PV, cmf.xK, oQ),
      new cmf("MAX", PV, cmf.xK, oQ),
      new cmf("MINA", PV, cmf.xK, oQ),
      new cmf("MAXA", PV, cmf.xK, oQ)
   };
   private static final cmf[] CE = new cmf[]{
      new cmf("CVT.S", PV, null, xW),
      new cmf("CVT.D", PV, null, xW),
      cmf.q,
      cmf.q,
      new cmf("CVT.W", PV, null, xW),
      new cmf("CVT.L", PV, null, xW),
      new cmf("CVT.PS", PV, cmf.RF, oQ),
      cmf.q
   };
   private static final ckh wF = new cmk();
   private static final cmf If = new cmf("C", wF, cmf.RF, clz.KT, clz.za, clz.Uv);
   private static final ckh Dz = new cml();
   private static final cmf mI = new cmf("CMP", Dz, cmf.xK, oQ);
   private static final cmf[] jq = new cmf[]{new cmf("CVT.S.PU", cmf.oW, xW), new cmf("CVT.S.PL", cmf.oW, xW)};
   private static final cmf[] ui = new cmf[]{
      new cmf("PLL", PV, cmf.oW, oQ), new cmf("PLU", PV, cmf.oW, oQ), new cmf("PUL", PV, cmf.oW, oQ), new cmf("PUU", PV, cmf.oW, oQ)
   };
   private static final clw[] TX = new clw[]{clz.xK, clz.nf, clx.oQ};
   private static final cmf[] Rr = new cmf[]{
      new cmf("MFC2", TX),
      new cmf("DMFC2", cmf.za, TX),
      new cmf("CFC2", TX),
      new cmf("MFHC2", cmf.Dw, TX),
      new cmf("MTC2", TX),
      new cmf("DMTC2", cmf.za, TX),
      new cmf("CTC2", TX),
      new cmf("MTHC2", cmf.Dw, TX)
   };
   private static final clw EB = new cly(clx.nf, clz.nf);
   private static final cmf[] Xo = new cmf[]{
      new cmf("BC2", LK, cmp.q, cmb.Me, cmf.RF, io, clx.RF),
      new cmf("BC2EQZ", cmp.q, cmb.qa, cmf.xK, clz.oW, clx.RF),
      new cmf("LWC2", cmf.xK, clz.xK, EB),
      new cmf("SWC2", cmf.xK, clz.xK, EB),
      cmf.q,
      new cmf("BC2NEZ", cmp.q, cmb.LK, cmf.xK, clz.oW, clx.RF),
      new cmf("LDC2", cmf.xK, clz.xK, EB),
      new cmf("SDC2", cmf.xK, clz.xK, EB)
   };
   private static final ckh Bu = new cmm();
   private static final clw[] IN = new clw[]{clz.lm, clz.RF, clz.za, clz.Uv};
   private static final cmf[] rL = new cmf[]{
      new cmf("MADD", Bu, cmf.oW, IN), new cmf("MSUB", Bu, cmf.oW, IN), new cmf("NMADD", Bu, cmf.oW, IN), new cmf("NMSUB", Bu, cmf.oW, IN)
   };
   private static final clw eJ = new cly(clz.xK, clz.q);
   private static final clw[] YN = new clw[]{clz.lm, eJ};
   private static final clw[] Rv = new clw[]{clz.za, eJ};
   private static final cmf[] zx = new cmf[]{
      new cmf("LWXC1", cmf.oW, YN),
      new cmf("LDXC1", cmf.oW, YN),
      new cmf("LUXC1", cmf.oW, YN),
      cmf.q,
      new cmf("SWXC1", cmf.oW, Rv),
      new cmf("SDXC1", cmf.oW, Rv),
      new cmf("SUXC1", cmf.oW, Rv),
      new cmf("PREFX", cmf.oW, clx.Hk, eJ)
   };
   private static final cmf ZT = new cmf("ALNV", Bu, cmf.oW, clz.lm, clz.za, clz.Uv, clz.q);

   public static cmf q(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      int var2 = var1 >>> 3;
      int var3 = var1 & 7;
      switch (var2) {
         case 0:
            if (var3 == 3) {
               return cmf.RF(var0, "Cop0 00");
            }

            if (DirectEncodedMemoryArea.get(3, 8).decodeInt(var0) == 0) {
               return xK[var3];
            }
            break;
         case 1:
            if (var3 == 0 || var3 == 4) {
               return cmf.RF(var0, "Cop0 01");
            }

            if ((var3 & 3) == 2 && DirectEncodedMemoryArea.get(0, 11).decodeInt(var0) == 0) {
               return Dw[var3 >>> 2];
            }

            if (var3 == 3 && DirectEncodedMemoryArea.get(0, 5).decodeInt(var0) == 0 && DirectEncodedMemoryArea.get(6, 10).decodeInt(var0) == 384) {
               return (var0[3] & 32) == 0 ? Uv : oW;
            }
            break;
         case 2:
         case 3:
            return Dw(var0);
      }

      return cmf.q(var0, "Cop0");
   }

   private static cmf Dw(byte[] var0) throws ProcessorException {
      int var1 = (var0[3] & 56) >>> 3;
      int var2 = var0[3] & 7;
      if (var2 != 0 || var1 != 2 && var1 != 5 && var1 != 7) {
         if (var1 != 1 || !MathUtil.betweenInclusive(var2, 1L, 4L) && var2 != 6) {
            if (var1 == 4 && var2 == 0) {
               return zz;
            } else {
               int var3 = DirectEncodedMemoryArea.get(6, 19).decodeInt(var0);
               if (var3 == 0) {
                  switch (var1) {
                     case 0:
                        return gO[var2];
                     case 1:
                        if (var2 == 0) {
                           return nf;
                        }
                     case 2:
                     default:
                        break;
                     case 3:
                        if (var2 == 0) {
                           return gP;
                        }

                        if (var2 == 7) {
                           return lm;
                        }
                  }
               } else if (var3 == 1 && var1 == 3 && var2 == 0) {
                  return za;
               }

               return cmf.q(var0, "Cop0");
            }
         } else {
            return cmf.RF(var0, "Cop0 C0");
         }
      } else {
         return cmf.RF(var0, "Cop0 C0");
      }
   }

   public static cmf q(byte[] var0, clg var1, boolean var2) throws ProcessorException {
      int var3 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      int var4 = var3 >>> 3;
      int var5 = var3 & 7;
      switch (var4) {
         case 0:
            if (DirectEncodedMemoryArea.get(0, 11).decodeInt(var0) == 0) {
               return HF[var5];
            }
            break;
         case 1:
            if (var5 == 0) {
               return qa;
            }

            if (var5 == 1) {
               return Hk;
            }

            if (var5 == 5) {
               return Me;
            }

            if (MathUtil.betweenInclusive(var5, 1L, 3L) || var5 == 7) {
               return cmf.RF(var0, "Cop1 01");
            }
            break;
         case 2:
            int var6 = (var0[3] & 56) >>> 3;
            int var7 = var0[3] & 7;
            switch (var5) {
               case 0:
               case 1:
                  return Uv(var0);
               case 2:
               case 3:
               default:
                  return cmf.q(var0, "Cop1");
               case 4:
               case 5:
                  if (var6 < 2 || var6 < 4 && MathUtil.betweenInclusive(var7, 1L, 3L)) {
                     return mI;
                  }

                  if (var6 == 4 && var7 < 2) {
                     return CE[var7];
                  }

                  return cmf.q(var0, "Cop1");
               case 6:
                  if (!var1.RF() || var2) {
                     int var8 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
                     if (var6 == 0 && !MathUtil.betweenInclusive(var7, 3L, 4L)) {
                        return Uv(var0);
                     }

                     if (var6 == 2 && MathUtil.betweenInclusive(var7, 1L, 3L)) {
                        return Uv(var0);
                     }

                     if ((var6 == 4 || var6 == 5) && var7 == 0 && var8 == 0) {
                        return jq[var6 - 4];
                     }

                     if (var6 == 5 && var7 >= 4) {
                        return ui[var7 - 4];
                     }

                     if (var6 == 6 || var6 == 7) {
                        return Uv(var0);
                     }
                  }

                  return cmf.q(var0, "Cop1");
            }
         case 3:
            return cmf.RF(var0, "Cop1 11 BZ.x/BNZ.x");
      }

      return cmf.q(var0, "Cop1");
   }

   private static cmf Uv(byte[] var0) throws ProcessorException {
      int var1 = (var0[3] & 56) >>> 3;
      int var2 = var0[3] & 7;
      int var3 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
      int var4 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      boolean var5 = var4 == 16;
      switch (var1) {
         case 0:
            if (var2 < 4 || var3 == 0) {
               return KT[var2];
            }
            break;
         case 1:
            if (var3 == 0) {
               return Gf[var2];
            }
            break;
         case 2:
            if (var2 != 5 && var2 != 6 || var3 == 0) {
               if (var2 == 1) {
                  return cC[var0[1] & 3];
               }

               return Ef[var2];
            }
            break;
         case 3:
            if (var2 != 2 && var2 != 3 || var3 == 0) {
               return sH[var2];
            }
            break;
         case 4:
            if ((var2 >= 6 || var3 == 0) && (var2 != 0 || !var5) && (var2 != 1 && var2 != 6 || var5)) {
               return CE[var2];
            }
         case 5:
         default:
            break;
         case 6:
         case 7:
            if ((var0[3] & 192) == 0) {
               return If;
            }
      }

      return cmf.q(var0, "Cop1.S/.D");
   }

   public static cmf RF(byte[] var0) throws ProcessorException {
      int var1 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      int var2 = var1 >>> 3;
      int var3 = var1 & 7;
      switch (var2) {
         case 0:
            return Rr[var3];
         case 1:
            return Xo[var3];
         case 2:
         case 3:
            return cmf.RF(var0, "Cop2 C2");
         default:
            return cmf.q(var0, "Cop2");
      }
   }

   public static cmf xK(byte[] var0) throws ProcessorException {
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

               return zx[var1 << 2 | var2];
            }
         case 2:
         default:
            break;
         case 3:
            if (var2 == 6) {
               return ZT;
            }
            break;
         case 4:
         case 5:
         case 6:
         case 7:
            if (var2 < 2 || var2 == 6) {
               return rL[var1 - 4];
            }
      }

      return cmf.q(var0, "Cop1X");
   }
}
