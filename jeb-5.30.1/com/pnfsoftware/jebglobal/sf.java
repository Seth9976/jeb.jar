package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class sf {
   private static final IEncodedMemoryArea RF = DirectEncodedMemoryArea.get(22, 2);
   public static final IEncodedMemoryArea q = DirectEncodedMemoryArea.get(22, 1);
   private static final IEncodedMemoryArea xK = EncodedMemoryAreaList.fromBits(21, 22);
   private static final IEncodedMemoryArea Dw = DirectEncodedMemoryArea.get(29, 1);
   private static final Dm Uv = var0 -> {
      switch (XD.Gu.decodeInt(var0)) {
         case 0:
            return "2H";
         case 1:
         default:
            return null;
         case 2:
            return "2S";
         case 3:
            return "2D";
      }
   };
   private static final Ef oW = XD.q(0, kE.RF);
   private static final Ef gO = XD.q(5, kE.RF);
   private static final Ef nf = XD.q(16, kE.RF);
   private static final Ef gP = XD.q(5, Uv);
   private static final dD za = new dD(DirectEncodedMemoryArea.get(19, 1), 0L, 16L);
   private static final dD lm = new dD(q, 1L, 0L);
   private static final dD zz = lm;
   private static final dD JY = new dD(Dw, 16L, 0L);
   private static final dD HF = new dD(xK, 1L, 16L, 0L, 0L);
   private static final dD LK = new dD(RF, 16L, 1L, 0L, 0L);
   private static final dD io = new dD(q, 0L, 1L);
   private static final dD qa = io;
   private static final dD Hk = new dD(DirectEncodedMemoryArea.get(20, 3), 1L, 16L, 0L, 0L, 0L, 0L, 0L, 0L);
   private static final Je[] Me = new Je[]{
      new Qg(0, "SUQADD", XD.oQ, XD.xW),
      new Qg(1, "SQABS", XD.oQ, XD.xW),
      new Qg(2, "CMGT", XD.CE, XD.wF, go.Ri),
      new Qg(3, "CMEQ", XD.CE, XD.wF, go.Ri),
      new Qg(4, "CMLT", XD.CE, XD.wF, go.Ri),
      new Qg(5, "ABS", XD.CE, XD.wF),
      Qg.xK,
      new Qg(7, "SQXTN", XD.Gf, XD.sH),
      new Qg(0, "USQADD", XD.oQ, XD.xW),
      new Qg(1, "SQNEG", XD.oQ, XD.xW),
      new Qg(2, "CMGE", XD.CE, XD.wF, go.Ri),
      new Qg(3, "CMLE", XD.CE, XD.wF, go.Ri),
      Qg.xK,
      new Qg(5, "NEG", XD.CE, XD.wF),
      new Qg(6, "SQXTUN", XD.Gf, XD.sH),
      new Qg(7, "UQXTN", XD.Gf, XD.sH)
   };
   private static final Ef[] PV = new Ef[]{XD.AB, XD.CY};
   private static final Ef[] oQ = new Ef[]{XD.AB, XD.CY, Nd.q(new dD(XD.GY, 32, 64, 16, 16))};
   private static final Je[] xW = new Je[]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(26, "FCVTNS", PV).q(za),
      new Qg(27, "FCVTMS", PV).q(za),
      new Qg(28, "FCVTAS", PV).q(za),
      new Qg(69889, "SCVTF", PV).q(za),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(44, "FCMGT", oQ).q(za),
      new Qg(45, "FCMEQ", oQ).q(za),
      new Qg(46, "FCMLT", oQ).q(za),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(58, "FCVTPS", PV).q(za),
      new Qg(59, "FCVTZS", PV).q(za),
      null,
      new Qg(61, "FRECPE", PV).q(za),
      null,
      new Qg(63, "FRECPX", PV).q(za),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(86, "FCVTXN", XD.EB, XD.Xo).q(za).q(lm),
      null,
      null,
      null,
      new Qg(90, "FCVTNU", PV).q(za),
      new Qg(91, "FCVTMU", PV).q(za),
      new Qg(92, "FCVTAU", PV).q(za),
      new Qg(93, "UCVTF", PV).q(za),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(108, "FCMGE", oQ).q(za),
      new Qg(109, "FCMLE", oQ).q(za),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(122, "FCVTPU", PV).q(za),
      new Qg(123, "FCVTZU", PV).q(za),
      null,
      new Qg(125, "FRSQRTE", PV).q(za),
      null,
      null
   };
   private static final Je[] KT = new Je[]{new Qg("SHA1H", XD.lm, XD.zz), new Qg("SHA1SU1", oW, gO), new Qg("SHA256SU0", oW, gO)};
   private static final Je[] Gf = new Je[]{
      new Qg("SHA1C", XD.Bu, XD.zz, nf),
      new Qg("SHA1P", XD.Bu, XD.zz, nf),
      new Qg("SHA1M", XD.Bu, XD.zz, nf),
      new Qg("SHA1SU0", oW, gO, nf),
      new Qg("SHA256H", XD.Bu, XD.IN, nf),
      new Qg("SHA256H2", XD.Bu, XD.IN, nf),
      new Qg("SHA256SU1", oW, gO, nf)
   };
   private static final Je Ef = new Qg("ADDP", XD.HF, XD.q(5, kE.oW));
   private static final Je cC = new Qg("FADDP", XD.nY, gP).q(JY);
   private static final Je[] sH = new Je[]{
      new Qg("FMAXNMP", XD.nY, gP).q(JY), new Qg("FMAXP", XD.nY, gP).q(JY), new Qg("FMINNMP", XD.nY, gP).q(JY), new Qg("FMINP", XD.nY, gP).q(JY)
   };
   private static final Je[] CE = new Je[]{
      new Qg("SQDMLAL", XD.cC, XD.xW, XD.KT), new Qg("SQDMLSL", XD.cC, XD.xW, XD.KT), new Qg("SQDMULL", XD.cC, XD.xW, XD.KT), null
   };
   private static final Ef[] wF = new Ef[]{XD.CE, XD.wF, XD.If};
   private static final Ef[] If = new Ef[]{XD.oQ, XD.xW, XD.KT};
   private static final Ef[] Dz = new Ef[]{XD.Dz, XD.mI, XD.jq};
   private static final Je[] mI = new Je[]{
      Qg.xK,
      new Qg(1, "SQADD", If),
      Qg.xK,
      Qg.xK,
      Qg.xK,
      new Qg(5, "SQSUB", If),
      new Qg(6, "CMGT", wF),
      new Qg(7, "CMGE", wF),
      new Qg(8, "SSHL", wF),
      new Qg(9, "SQSHL", If),
      new Qg(10, "SRSHL", wF),
      new Qg(11, "SQRSHL", If),
      new Qg(12, "ADD", wF),
      new Qg(13, "CMTST", wF),
      Qg.xK,
      new Qg(15, "SQDMULH", Dz),
      Qg.xK,
      new Qg(1, "UQADD", If),
      Qg.xK,
      Qg.xK,
      Qg.xK,
      new Qg(5, "UQSUB", If),
      new Qg(6, "CMHI", wF),
      new Qg(7, "CMHS", wF),
      new Qg(8, "USHL", wF),
      new Qg(9, "UQSHL", If),
      new Qg(10, "URSHL", wF),
      new Qg(11, "UQRSHL", If),
      new Qg(12, "SUB", wF),
      new Qg(13, "CMEQ", wF),
      Qg.xK,
      new Qg(15, "SQRDMULH", Dz)
   };
   private static final Je[] jq = new Je[]{new Qg("SQRDMLAH", Dz), new Qg("SQRDMLSH", Dz)};
   private static final Ef[] ui = new Ef[]{XD.zx, XD.ZT, XD.Ri};
   private static final Je[][] TX = new Je[][]{
      {null, null, null, new Qg("FMULX", ui).q(HF), new Qg("FCMEQ", ui).q(HF), null, null, new Qg("FRECPS", ui).q(HF)},
      {null, null, null, null, null, null, null, new Qg("FRSQRTS", ui).q(HF)},
      {null, null, null, null, new Qg("FCMGE", ui).q(HF), new Qg("FACGE", ui).q(HF)},
      {null, null, new Qg("FABD", ui).q(HF), null, new Qg("FCMGT", ui).q(HF), new Qg("FACGT", ui).q(HF)}
   };
   private static final Je Rr = new Qg("MOV", kA.q(0), kA.q(5, 17));
   private static final Ef[] EB = new Ef[]{XD.HF, XD.LK, wS.io};
   private static final Ef[] Xo = new Ef[]{XD.HF, XD.LK, wS.qa};
   private static final Ef[] Bu = new Ef[]{GT.nf, GT.gP, wS.io};
   private static final Ef[] IN = new Ef[]{XD.Tq, XD.Yp, wS.io};
   private static final Je[] rL = new Je[]{
      new Qg(0, "SSHR", EB).q(zz),
      null,
      new Qg(2, "SSRA", EB).q(zz),
      null,
      new Qg(4, "SRSHR", EB).q(zz),
      null,
      new Qg(6, "SRSRA", EB).q(zz),
      null,
      null,
      null,
      new Qg(10, "SHL", Xo).q(zz),
      null,
      null,
      null,
      new Qg(14, "SQSHL", GT.Uv, GT.oW, wS.qa),
      null,
      null,
      null,
      new Qg(18, "SQSHRN", Bu).q(qa),
      new Qg(19, "SQRSHRN", Bu).q(qa),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(28, "SCVTF", IN).q(Hk),
      null,
      null,
      new Qg(31, "FCVTZS", IN).q(Hk),
      new Qg(32, "USHR", EB).q(zz),
      null,
      new Qg(34, "USRA", EB).q(zz),
      null,
      new Qg(36, "URSHR", EB).q(zz),
      null,
      new Qg(38, "URSRA", EB).q(zz),
      null,
      new Qg(40, "SRI", EB).q(zz),
      null,
      new Qg(42, "SLI", Xo).q(zz),
      null,
      new Qg(44, "SQSHLU", GT.Uv, GT.oW, wS.qa),
      null,
      new Qg(46, "UQSHL", GT.Uv, GT.oW, wS.qa),
      null,
      new Qg(48, "SQSHRUN", Bu).q(qa),
      new Qg(49, "SQRSHRUN", Bu).q(qa),
      new Qg(50, "UQSHRN", Bu).q(qa),
      new Qg(51, "UQRSHRN", Bu).q(qa),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(60, "UCVTF", IN).q(Hk),
      null,
      null,
      new Qg(63, "FCVTZU", IN).q(Hk)
   };
   private static final Je[] eJ = new Je[]{
      null,
      new Qg(1, "FMLA", XD.ui, XD.TX, Fo.nf).q(LK),
      null,
      new Qg(3, "SQDMLAL", XD.cC, XD.Ef, Fo.Uv),
      null,
      new Qg(5, "FMLS", XD.ui, XD.TX, Fo.nf).q(LK),
      null,
      new Qg(7, "SQDMLSL", XD.cC, XD.Ef, Fo.Uv),
      null,
      new Qg(9, "FMUL", XD.ui, XD.TX, Fo.nf).q(LK),
      null,
      new Qg(11, "SQDMULL", XD.cC, XD.Ef, Fo.Uv),
      new Qg(12, "SQDMULH", XD.Gf, XD.Ef, Fo.Uv),
      new Qg(13, "SQRDMULH", XD.Gf, XD.Ef, Fo.Uv),
      null,
      null
   };
   private static final Je[] YN = new Je[]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(9, "FMULX", XD.ui, XD.TX, Fo.nf).q(LK),
      null,
      null,
      null,
      new Qg(13, "SQRDMLAH", XD.Dz, XD.mI, Fo.Uv).q(QJ.LL),
      null,
      new Qg(15, "SQRDMLSH", XD.Dz, XD.mI, Fo.Uv).q(QJ.LL)
   };

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 240) >>> 4;
      int var2 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var3 = (var0[1] & 120) >>> 3;
      int var4 = (var0[1] & 6) >>> 1;
      int var5 = (var0[2] & 252) >>> 2;
      int var6 = (var0[1] & 7) << 6 | (var0[2] & 252) >>> 2;
      int var7 = (var0[0] & 32) >>> 5;
      int var8 = (var0[1] & 192) >>> 6;
      if (var2 >= 2) {
         if ((var5 & 1) == 0) {
            int var22 = (var0[2] & 240) >>> 4;
            return var7 == 0 ? eJ[var22] : YN[var22];
         } else if (var2 == 2) {
            int var21 = (var0[2] & 248) >>> 3;
            int var25 = (var0[1] & 120) >>> 3;
            return var25 == 0 ? Qg.q(var0, "Advanced SIMD scalar shift by immediate") : rL[var7 << 5 | var21];
         } else {
            return Qg.q(var0, "Advanced SIMD scalar shift by immediate");
         }
      } else if ((var3 & 4) == 0) {
         if (var1 == 5 && (var6 & 35) == 0) {
            int var20 = DirectEncodedMemoryArea.get(12, 3).decodeInt(var0);
            return var8 == 0 && var20 < 7 ? Gf[var20] : Qg.q(var0, "Cryptographic three-register SHA");
         } else if (var2 == 0 && var3 < 4 && (var6 & 33) == 1) {
            int var24 = var0[1] & 31;
            int var11 = DirectEncodedMemoryArea.get(11, 4).decodeInt(var0);
            return var7 == 0 && (var24 & 15) != 0 && var11 == 0 ? Rr : Qg.q(var0, "Advanced SIMD scalar copy");
         } else if (var3 >= 8 && (var6 & 49) == 1) {
            String var19 = "Advanced SIMD scalar three same FP16";
            int var23 = DirectEncodedMemoryArea.get(11, 3).decodeInt(var0);
            return Qg.q(TX, var7 << 1 | (var8 & 2) >>> 1, var23, var0, var19);
         } else {
            if ((var6 & 33) == 33) {
               int var18 = (var0[2] & 120) >>> 3;
               if (!QJ.q()) {
                  return null;
               }

               if (var7 != 0 && var18 < 2) {
                  return jq[var18];
               }
            }

            return Qg.q(var0, "Advanced SIMD scalar copy");
         }
      } else if ((var5 & 1) != 0) {
         String var17 = "Advanced SIMD scalar three same";
         int var10 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
         if (var10 != 1 && !MathUtil.betweenInclusive(var10, 5L, 11L) && !MathUtil.betweenInclusive(var10, 16L, 17L) && var10 != 22) {
            return var10 >= 24 ? Qg.q(TX, var7 << 1 | (var8 & 2) >>> 1, var10 & 7, var0, var17) : Qg.q(var0, var17);
         } else {
            if (var10 >= 16) {
               if (var10 == 22) {
                  var10 = 15;
               } else {
                  var10 -= 4;
               }
            }

            return mI[var7 << 4 | var10];
         }
      } else if ((var5 & 3) != 0) {
         if (var4 == 0) {
            switch (var3) {
               case 4:
               case 12:
                  int var15 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                  if (var15 != 3 && !MathUtil.betweenInclusive(var15, 7L, 11L) && var15 != 18 && var15 != 20) {
                     return xW[var7 << 6 | (var8 & 2) << 4 | var15];
                  }

                  if (var15 == 3) {
                     var15 = 0;
                  } else {
                     var15 -= 6;
                     if (var15 == 12) {
                        var15 = 6;
                     } else if (var15 == 14) {
                        var15 = 7;
                     }
                  }

                  return Me[var7 << 3 | var15];
               case 5:
               case 13:
                  if (var1 == 5) {
                     int var14 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                     if (var8 == 0 && var14 < 3) {
                        return KT[var14 & 3];
                     }

                     return Qg.q(var0, "Cryptographic two-register SHA");
                  }
                  break;
               case 6:
               case 14:
                  int var13 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                  if (var7 == 0 && var13 == 27 && var8 == 3) {
                     return Ef;
                  }

                  if (var13 == 13 && var8 < 2) {
                     return cC;
                  }

                  if (var13 != 12 && var13 != 15) {
                     return Qg.q(var0, "Advanced SIMD scalar pairwise");
                  }

                  return sH[var8 & 2 | var13 & 1];
               case 7:
               case 8:
               case 9:
               case 10:
               case 11:
               default:
                  break;
               case 15:
                  int var12 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                  return xW[var7 << 6 | (var8 & 2) << 4 | var12];
            }
         }

         return Qg.q(var0, "Advanced SIMD Scalar");
      } else {
         if (var7 == 0) {
            int var9 = (var0[2] & 240) >>> 4;
            if (MathUtil.betweenInclusive(var8, 1L, 2L) && (var9 == 9 || var9 == 11 || var9 == 13)) {
               return CE[var9 >>> 1 & 3];
            }
         }

         return Qg.q(var0, "Advanced SIMD scalar three different");
      }
   }
}
