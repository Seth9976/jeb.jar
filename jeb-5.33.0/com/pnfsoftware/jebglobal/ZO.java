package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class ZO {
   private static final IEncodedMemoryArea A = DirectEncodedMemoryArea.get(22, 2);
   public static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(22, 1);
   private static final IEncodedMemoryArea kS = EncodedMemoryAreaList.fromBits(21, 22);
   private static final IEncodedMemoryArea wS = DirectEncodedMemoryArea.get(29, 1);
   private static final IX UT = var0 -> {
      switch (ER.xM.decodeInt(var0)) {
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
   private static final Hu E = ER.pC(0, XW.A);
   private static final Hu sY = ER.pC(5, XW.A);
   private static final Hu ys = ER.pC(16, XW.A);
   private static final Hu ld = ER.pC(5, UT);
   private static final ZW gp = new ZW(DirectEncodedMemoryArea.get(19, 1), 0L, 16L);
   private static final ZW oT = new ZW(pC, 1L, 0L);
   private static final ZW fI = oT;
   private static final ZW WR = new ZW(wS, 16L, 0L);
   private static final ZW NS = new ZW(kS, 1L, 16L, 0L, 0L);
   private static final ZW vP = new ZW(A, 16L, 1L, 0L, 0L);
   private static final ZW xC = new ZW(pC, 0L, 1L);
   private static final ZW ED = xC;
   private static final ZW Sc = new ZW(DirectEncodedMemoryArea.get(20, 3), 1L, 16L, 0L, 0L, 0L, 0L, 0L, 0L);
   private static final tz[] ah = new tz[]{
      new UC(0, "SUQADD", ER.ED, ER.Sc),
      new UC(1, "SQABS", ER.ED, ER.Sc),
      new UC(2, "CMGT", ER.z, ER.Ek, IV.DQ),
      new UC(3, "CMEQ", ER.z, ER.Ek, IV.DQ),
      new UC(4, "CMLT", ER.z, ER.Ek, IV.DQ),
      new UC(5, "ABS", ER.z, ER.Ek),
      UC.pC,
      new UC(7, "SQXTN", ER.eP, ER.rl),
      new UC(0, "USQADD", ER.ED, ER.Sc),
      new UC(1, "SQNEG", ER.ED, ER.Sc),
      new UC(2, "CMGE", ER.z, ER.Ek, IV.DQ),
      new UC(3, "CMLE", ER.z, ER.Ek, IV.DQ),
      UC.pC,
      new UC(5, "NEG", ER.z, ER.Ek),
      new UC(6, "SQXTUN", ER.eP, ER.rl),
      new UC(7, "UQXTN", ER.eP, ER.rl)
   };
   private static final Hu[] eP = new Hu[]{ER.Bf, ER.Pe};
   private static final Hu[] UO = new Hu[]{ER.Bf, ER.Pe, bI.pC(new ZW(ER.Bc, 32, 64, 16, 16))};
   private static final tz[] Ab = new tz[]{
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
      new UC(26, "FCVTNS", eP).pC(gp),
      new UC(27, "FCVTMS", eP).pC(gp),
      new UC(28, "FCVTAS", eP).pC(gp),
      new UC(69889, "SCVTF", eP).pC(gp),
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
      new UC(44, "FCMGT", UO).pC(gp),
      new UC(45, "FCMEQ", UO).pC(gp),
      new UC(46, "FCMLT", UO).pC(gp),
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
      new UC(58, "FCVTPS", eP).pC(gp),
      new UC(59, "FCVTZS", eP).pC(gp),
      null,
      new UC(61, "FRECPE", eP).pC(gp),
      null,
      new UC(63, "FRECPX", eP).pC(gp),
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
      new UC(86, "FCVTXN", ER.sO, ER.os).pC(gp).pC(oT),
      null,
      null,
      null,
      new UC(90, "FCVTNU", eP).pC(gp),
      new UC(91, "FCVTMU", eP).pC(gp),
      new UC(92, "FCVTAU", eP).pC(gp),
      new UC(93, "UCVTF", eP).pC(gp),
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
      new UC(108, "FCMGE", UO).pC(gp),
      new UC(109, "FCMLE", UO).pC(gp),
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
      new UC(122, "FCVTPU", eP).pC(gp),
      new UC(123, "FCVTZU", eP).pC(gp),
      null,
      new UC(125, "FRSQRTE", eP).pC(gp),
      null,
      null
   };
   private static final tz[] rl = new tz[]{new UC("SHA1H", ER.sY, ER.ys), new UC("SHA1SU1", E, sY), new UC("SHA256SU0", E, sY)};
   private static final tz[] z = new tz[]{
      new UC("SHA1C", ER.Cu, ER.ys, ys),
      new UC("SHA1P", ER.Cu, ER.ys, ys),
      new UC("SHA1M", ER.Cu, ER.ys, ys),
      new UC("SHA1SU0", E, sY, ys),
      new UC("SHA256H", ER.Cu, ER.hZ, ys),
      new UC("SHA256H2", ER.Cu, ER.hZ, ys),
      new UC("SHA256SU1", E, sY, ys)
   };
   private static final tz Ek = new UC("ADDP", ER.gp, ER.pC(5, XW.E));
   private static final tz hK = new UC("FADDP", ER.kU, ld).pC(WR);
   private static final tz[] Er = new tz[]{
      new UC("FMAXNMP", ER.kU, ld).pC(WR), new UC("FMAXP", ER.kU, ld).pC(WR), new UC("FMINNMP", ER.kU, ld).pC(WR), new UC("FMINP", ER.kU, ld).pC(WR)
   };
   private static final tz[] FE = new tz[]{
      new UC("SQDMLAL", ER.Ab, ER.Sc, ER.ah), new UC("SQDMLSL", ER.Ab, ER.Sc, ER.ah), new UC("SQDMULL", ER.Ab, ER.Sc, ER.ah), null
   };
   private static final Hu[] Aj = new Hu[]{ER.z, ER.Ek, ER.hK};
   private static final Hu[] EX = new Hu[]{ER.ED, ER.Sc, ER.ah};
   private static final Hu[] LM = new Hu[]{ER.Er, ER.FE, ER.Aj};
   private static final tz[] mv = new tz[]{
      UC.pC,
      new UC(1, "SQADD", EX),
      UC.pC,
      UC.pC,
      UC.pC,
      new UC(5, "SQSUB", EX),
      new UC(6, "CMGT", Aj),
      new UC(7, "CMGE", Aj),
      new UC(8, "SSHL", Aj),
      new UC(9, "SQSHL", EX),
      new UC(10, "SRSHL", Aj),
      new UC(11, "SQRSHL", EX),
      new UC(12, "ADD", Aj),
      new UC(13, "CMTST", Aj),
      UC.pC,
      new UC(15, "SQDMULH", LM),
      UC.pC,
      new UC(1, "UQADD", EX),
      UC.pC,
      UC.pC,
      UC.pC,
      new UC(5, "UQSUB", EX),
      new UC(6, "CMHI", Aj),
      new UC(7, "CMHS", Aj),
      new UC(8, "USHL", Aj),
      new UC(9, "UQSHL", EX),
      new UC(10, "URSHL", Aj),
      new UC(11, "UQRSHL", EX),
      new UC(12, "SUB", Aj),
      new UC(13, "CMEQ", Aj),
      UC.pC,
      new UC(15, "SQRDMULH", LM)
   };
   private static final tz[] sO = new tz[]{new UC("SQRDMLAH", LM), new UC("SQRDMLSH", LM)};
   private static final Hu[] os = new Hu[]{ER.ZN, ER.OB, ER.pF};
   private static final tz[][] Cu = new tz[][]{
      {null, null, null, new UC("FMULX", os).pC(NS), new UC("FCMEQ", os).pC(NS), null, null, new UC("FRECPS", os).pC(NS)},
      {null, null, null, null, null, null, null, new UC("FRSQRTS", os).pC(NS)},
      {null, null, null, null, new UC("FCMGE", os).pC(NS), new UC("FACGE", os).pC(NS)},
      {null, null, new UC("FABD", os).pC(NS), null, new UC("FCMGT", os).pC(NS), new UC("FACGT", os).pC(NS)}
   };
   private static final tz hZ = new UC("MOV", MJ.pC(0), MJ.pC(5, 17));
   private static final Hu[] UW = new Hu[]{ER.gp, ER.oT, Gh.xC};
   private static final Hu[] PR = new Hu[]{ER.gp, ER.oT, Gh.ED};
   private static final Hu[] cX = new Hu[]{VC.ys, VC.ld, Gh.xC};
   private static final Hu[] DQ = new Hu[]{ER.RW, ER.e, Gh.xC};
   private static final tz[] ZN = new tz[]{
      new UC(0, "SSHR", UW).pC(fI),
      null,
      new UC(2, "SSRA", UW).pC(fI),
      null,
      new UC(4, "SRSHR", UW).pC(fI),
      null,
      new UC(6, "SRSRA", UW).pC(fI),
      null,
      null,
      null,
      new UC(10, "SHL", PR).pC(fI),
      null,
      null,
      null,
      new UC(14, "SQSHL", VC.UT, VC.E, Gh.ED),
      null,
      null,
      null,
      new UC(18, "SQSHRN", cX).pC(ED),
      new UC(19, "SQRSHRN", cX).pC(ED),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(28, "SCVTF", DQ).pC(Sc),
      null,
      null,
      new UC(31, "FCVTZS", DQ).pC(Sc),
      new UC(32, "USHR", UW).pC(fI),
      null,
      new UC(34, "USRA", UW).pC(fI),
      null,
      new UC(36, "URSHR", UW).pC(fI),
      null,
      new UC(38, "URSRA", UW).pC(fI),
      null,
      new UC(40, "SRI", UW).pC(fI),
      null,
      new UC(42, "SLI", PR).pC(fI),
      null,
      new UC(44, "SQSHLU", VC.UT, VC.E, Gh.ED),
      null,
      new UC(46, "UQSHL", VC.UT, VC.E, Gh.ED),
      null,
      new UC(48, "SQSHRUN", cX).pC(ED),
      new UC(49, "SQRSHRUN", cX).pC(ED),
      new UC(50, "UQSHRN", cX).pC(ED),
      new UC(51, "UQRSHRN", cX).pC(ED),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(60, "UCVTF", DQ).pC(Sc),
      null,
      null,
      new UC(63, "FCVTZU", DQ).pC(Sc)
   };
   private static final tz[] OB = new tz[]{
      null,
      new UC(1, "FMLA", ER.EX, ER.LM, GS.ys).pC(vP),
      null,
      new UC(3, "SQDMLAL", ER.Ab, ER.UO, GS.UT),
      null,
      new UC(5, "FMLS", ER.EX, ER.LM, GS.ys).pC(vP),
      null,
      new UC(7, "SQDMLSL", ER.Ab, ER.UO, GS.UT),
      null,
      new UC(9, "FMUL", ER.EX, ER.LM, GS.ys).pC(vP),
      null,
      new UC(11, "SQDMULL", ER.Ab, ER.UO, GS.UT),
      new UC(12, "SQDMULH", ER.eP, ER.UO, GS.UT),
      new UC(13, "SQRDMULH", ER.eP, ER.UO, GS.UT),
      null,
      null
   };
   private static final tz[] pF = new tz[]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(9, "FMULX", ER.EX, ER.LM, GS.ys).pC(vP),
      null,
      null,
      null,
      new UC(13, "SQRDMLAH", ER.Er, ER.FE, GS.UT).pC(Le.fI),
      null,
      new UC(15, "SQRDMLSH", ER.Er, ER.FE, GS.UT).pC(Le.fI)
   };

   public static tz pC(byte[] var0) throws ProcessorException {
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
            return var7 == 0 ? OB[var22] : pF[var22];
         } else if (var2 == 2) {
            int var21 = (var0[2] & 248) >>> 3;
            int var25 = (var0[1] & 120) >>> 3;
            return var25 == 0 ? UC.pC(var0, "Advanced SIMD scalar shift by immediate") : ZN[var7 << 5 | var21];
         } else {
            return UC.pC(var0, "Advanced SIMD scalar shift by immediate");
         }
      } else if ((var3 & 4) == 0) {
         if (var1 == 5 && (var6 & 35) == 0) {
            int var20 = DirectEncodedMemoryArea.get(12, 3).decodeInt(var0);
            return var8 == 0 && var20 < 7 ? z[var20] : UC.pC(var0, "Cryptographic three-register SHA");
         } else if (var2 == 0 && var3 < 4 && (var6 & 33) == 1) {
            int var24 = var0[1] & 31;
            int var11 = DirectEncodedMemoryArea.get(11, 4).decodeInt(var0);
            return var7 == 0 && (var24 & 15) != 0 && var11 == 0 ? hZ : UC.pC(var0, "Advanced SIMD scalar copy");
         } else if (var3 >= 8 && (var6 & 49) == 1) {
            String var19 = "Advanced SIMD scalar three same FP16";
            int var23 = DirectEncodedMemoryArea.get(11, 3).decodeInt(var0);
            return UC.pC(Cu, var7 << 1 | (var8 & 2) >>> 1, var23, var0, var19);
         } else {
            if ((var6 & 33) == 33) {
               int var18 = (var0[2] & 120) >>> 3;
               if (!Le.pC()) {
                  return null;
               }

               if (var7 != 0 && var18 < 2) {
                  return sO[var18];
               }
            }

            return UC.pC(var0, "Advanced SIMD scalar copy");
         }
      } else if ((var5 & 1) != 0) {
         String var17 = "Advanced SIMD scalar three same";
         int var10 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
         if (var10 != 1 && !MathUtil.betweenInclusive(var10, 5L, 11L) && !MathUtil.betweenInclusive(var10, 16L, 17L) && var10 != 22) {
            return var10 >= 24 ? UC.pC(Cu, var7 << 1 | (var8 & 2) >>> 1, var10 & 7, var0, var17) : UC.pC(var0, var17);
         } else {
            if (var10 >= 16) {
               if (var10 == 22) {
                  var10 = 15;
               } else {
                  var10 -= 4;
               }
            }

            return mv[var7 << 4 | var10];
         }
      } else if ((var5 & 3) != 0) {
         if (var4 == 0) {
            switch (var3) {
               case 4:
               case 12:
                  int var15 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                  if (var15 != 3 && !MathUtil.betweenInclusive(var15, 7L, 11L) && var15 != 18 && var15 != 20) {
                     return Ab[var7 << 6 | (var8 & 2) << 4 | var15];
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

                  return ah[var7 << 3 | var15];
               case 5:
               case 13:
                  if (var1 == 5) {
                     int var14 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                     if (var8 == 0 && var14 < 3) {
                        return rl[var14 & 3];
                     }

                     return UC.pC(var0, "Cryptographic two-register SHA");
                  }
                  break;
               case 6:
               case 14:
                  int var13 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                  if (var7 == 0 && var13 == 27 && var8 == 3) {
                     return Ek;
                  }

                  if (var13 == 13 && var8 < 2) {
                     return hK;
                  }

                  if (var13 != 12 && var13 != 15) {
                     return UC.pC(var0, "Advanced SIMD scalar pairwise");
                  }

                  return Er[var8 & 2 | var13 & 1];
               case 7:
               case 8:
               case 9:
               case 10:
               case 11:
               default:
                  break;
               case 15:
                  int var12 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
                  return Ab[var7 << 6 | (var8 & 2) << 4 | var12];
            }
         }

         return UC.pC(var0, "Advanced SIMD Scalar");
      } else {
         if (var7 == 0) {
            int var9 = (var0[2] & 240) >>> 4;
            if (MathUtil.betweenInclusive(var8, 1L, 2L) && (var9 == 9 || var9 == 11 || var9 == 13)) {
               return FE[var9 >>> 1 & 3];
            }
         }

         return UC.pC(var0, "Advanced SIMD scalar three different");
      }
   }
}
