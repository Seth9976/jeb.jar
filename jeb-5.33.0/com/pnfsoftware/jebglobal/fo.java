package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

class fo {
   private static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(22, 2);
   private static final ZW A = new ZW(pC, 0L, 0L, 1L, 16L);
   private static final tz[] kS = new tz[]{new UC("FCCMP", ER.NS, ER.xC, IV.UT, Ag.A), new UC("FCCMPE", ER.NS, ER.xC, IV.UT, Ag.A)};
   private static final tz wS = new UC("FCSEL", ER.WR, ER.NS, ER.xC, Ag.A);
   private static final ZW UT = new ZW(DirectEncodedMemoryArea.get(22, 2), 32, 64, 0, 16);
   private static final Hu E = bI.pC(UT);
   private static final tz[] sY = new tz[]{new UC("FCMP", ER.NS, ER.xC), new UC("FCMP", ER.NS, E), new UC("FCMPE", ER.NS, ER.xC), new UC("FCMPE", ER.NS, E)};
   private static final Hu ys = new OC(DirectEncodedMemoryArea.get(10, 6), 64);
   private static final tz[] ld = new tz[]{
      new UC("SCVTF", ER.WR, sQ.Aj, ys), new UC("UCVTF", ER.WR, sQ.Aj, ys), new UC("FCVTZS", sQ.FE, ER.NS, ys), new UC("FCVTZU", sQ.FE, ER.NS, ys)
   };
   private static final tz[] gp = new tz[]{
      new UC("FCVTNS", sQ.FE, ER.DQ).pC(A),
      new UC("FCVTNU", sQ.FE, ER.DQ).pC(A),
      new UC("SCVTF", ER.cX, sQ.Aj).pC(A),
      new UC("UCVTF", ER.cX, sQ.Aj).pC(A),
      new UC("FCVTAS", sQ.FE, ER.DQ).pC(A),
      new UC("FCVTAU", sQ.FE, ER.DQ).pC(A),
      new UC("FMOV", sQ.FE, ER.DQ).pC(A),
      new UC("FMOV", ER.cX, sQ.Aj).pC(A)
   };
   private static final tz[] oT = new tz[]{
      new UC("FMOV", sQ.A, new Yu(ER.pC(5, IX.wS), VirtualEncodedMemoryArea._1)), new UC("FMOV", new Yu(ER.pC(0, IX.wS), VirtualEncodedMemoryArea._1), sQ.UT)
   };
   private static final tz[] fI = new tz[]{
      null,
      null,
      new UC("FCVTPS", sQ.FE, ER.DQ).pC(A),
      new UC("FCVTPU", sQ.FE, ER.DQ).pC(A),
      new UC("FCVTMS", sQ.FE, ER.DQ).pC(A),
      new UC("FCVTMU", sQ.FE, ER.DQ).pC(A),
      new UC("FCVTZS", sQ.FE, ER.DQ).pC(A),
      new UC("FCVTZU", sQ.FE, ER.DQ).pC(A)
   };
   private static final tz WR = new UC("FJCVTZS", sQ.FE, ER.NS).pC(Le.Ek);
   private static final tz[] NS = new tz[]{
      new UC("FMADD", ER.WR, ER.NS, ER.xC, ER.vP),
      new UC("FMSUB", ER.WR, ER.NS, ER.xC, ER.vP),
      new UC("FNMADD", ER.WR, ER.NS, ER.xC, ER.vP),
      new UC("FNMSUB", ER.WR, ER.NS, ER.xC, ER.vP)
   };
   private static final tz[] vP = new tz[]{
      new UC("FMUL", ER.WR, ER.NS, ER.xC),
      new UC("FDIV", ER.WR, ER.NS, ER.xC),
      new UC("FADD", ER.WR, ER.NS, ER.xC),
      new UC("FSUB", ER.WR, ER.NS, ER.xC),
      new UC("FMAX", ER.WR, ER.NS, ER.xC),
      new UC("FMIN", ER.WR, ER.NS, ER.xC),
      new UC("FMAXNM", ER.WR, ER.NS, ER.xC),
      new UC("FMINNM", ER.WR, ER.NS, ER.xC)
   };
   private static final tz xC = new UC("FNMUL", ER.WR, ER.NS, ER.xC);
   private static final tz[] ED = new tz[]{
      new UC("FMOV", ER.cX, new eK(32, DirectEncodedMemoryArea.get(13, 8))),
      new UC("FMOV", ER.cX, new eK(64, DirectEncodedMemoryArea.get(13, 8))),
      null,
      new UC("FMOV", ER.cX, new eK(16, DirectEncodedMemoryArea.get(13, 8))).pC(A)
   };
   private static final tz[] Sc = new tz[]{
      new UC(0, "FMOV", ER.cX, ER.DQ).pC(A),
      new UC(1, "FABS", ER.cX, ER.DQ).pC(A),
      new UC(2, "FNEG", ER.cX, ER.DQ).pC(A),
      new UC(3, "FSQRT", ER.cX, ER.DQ).pC(A),
      null,
      null,
      null,
      null,
      new UC(8, "FRINTN", ER.cX, ER.DQ).pC(A),
      new UC(9, "FRINTP", ER.cX, ER.DQ).pC(A),
      new UC(10, "FRINTM", ER.cX, ER.DQ).pC(A),
      new UC(11, "FRINTZ", ER.cX, ER.DQ).pC(A),
      new UC(12, "FRINTA", ER.cX, ER.DQ).pC(A),
      null,
      new UC(14, "FRINTX", ER.cX, ER.DQ).pC(A),
      new UC(15, "FRINTI", ER.cX, ER.DQ).pC(A),
      new UC(16, "FRINT32Z", ER.WR, ER.NS).pC(Le.EX),
      new UC(17, "FRINT32X", ER.WR, ER.NS).pC(Le.EX),
      new UC(18, "FRINT64Z", ER.WR, ER.NS).pC(Le.EX),
      new UC(19, "FRINT64X", ER.WR, ER.NS).pC(Le.EX)
   };
   private static final tz[] ah = new tz[]{
      null,
      new UC("FCVT", ER.gp, ER.NS),
      null,
      new UC("FCVT", ER.UT, ER.NS),
      new UC("FCVT", ER.sY, ER.NS),
      null,
      new UC("BFCVT", ER.UT, ER.ys).pC(Le.LM),
      new UC("FCVT", ER.UT, ER.NS),
      null,
      null,
      null,
      null,
      new UC("FCVT", ER.sY, ER.E),
      new UC("FCVT", ER.gp, ER.E),
      null,
      null
   };

   public static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 120) >>> 3;
      int var3 = (var0[2] & 252) >>> 2;
      int var4 = (var0[0] & 128) >>> 7;
      int var6 = (var0[0] & 32) >>> 5;
      int var7 = (var0[1] & 192) >>> 6;
      if (var1 > 1) {
         return var4 != 1 && var6 != 1 && var7 <= 1
            ? NS[(var0[1] & 32) >>> 4 | (var0[2] & 128) >>> 7]
            : UC.pC(var0, "Floating-point data-processing (3 source)");
      } else if ((var2 & 4) == 0) {
         int var17 = var0[1] & 7;
         int var19 = (var0[1] & 24) >>> 3;
         int var10 = (var0[2] & 252) >>> 2;
         if (var6 != 1 && var7 <= 1 && var17 <= 3 && (var19 == 3 || var17 >= 2) && (var19 == 0 || var17 < 2) && (var4 != 0 || var10 >= 32)) {
            int var11 = (var19 == 0 ? 0 : 2) | var17 & 1;
            return ld[var11];
         } else {
            return UC.pC(var0, "Conversion between floating-point and fixed-point");
         }
      } else if (var3 == 0) {
         int var16 = var0[1] & 7;
         int var18 = (var0[1] & 24) >>> 3;
         if (var18 != 0) {
            if (var7 == 1 && var16 == 6 && var18 == 3 && var4 == 0 && var6 == 0) {
               return WR;
            } else {
               if (var7 == 2) {
                  if (var18 == 1 && var16 >= 6) {
                     return oT[var16 & 1];
                  }
               } else if (var16 < 2) {
                  return fI[var18 << 1 | var16];
               }

               return UC.pC(var0, "Conversion between floating-point and integer");
            }
         } else {
            return var16 < 6 || (var4 != 0 || var7 != 1) && (var4 == 0 || var7 != 0) ? gp[var16] : UC.pC(var0, "Conversion between floating-point and integer");
         }
      } else if ((var3 & 1) != 0) {
         int var15 = (var0[3] & 16) >>> 4;
         if ((var3 & 2) == 0) {
            return var4 != 1 && var6 != 1 && var7 <= 1 ? kS[var15] : UC.pC(var0, "Floating-point conditional compare");
         } else {
            return var4 != 1 && var6 != 1 && var7 <= 1 ? wS : UC.pC(var0, "Floating-point conditional select");
         }
      } else if ((var3 & 2) != 0) {
         int var14 = (var0[2] & 240) >>> 4;
         if (var4 == 1 || var6 == 1 || var7 > 1 || var14 > 8) {
            return UC.pC(var0, "Floating-point data-processing (2 source)");
         } else {
            return var14 == 8 ? xC : vP[var14 & 7];
         }
      } else if ((var3 & 4) != 0) {
         int var13 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var0);
         return var4 != 1 && var6 != 1 && var13 == 0 ? ED[var7] : UC.pC(var0, "Floating-point immediate");
      } else if ((var3 & 8) != 0) {
         int var12 = (var0[2] & 192) >>> 6;
         int var9 = var0[3] & 31;
         return var4 != 1 && var6 != 1 && var7 <= 1 && var12 == 0 && (var9 & 7) == 0 ? sY[var9 >>> 3] : UC.pC(var0, "Floating-point compare");
      } else if ((var3 & 16) != 0) {
         int var8 = DirectEncodedMemoryArea.get(15, 6).decodeInt(var0);
         if (var4 != 0 || var6 != 0 || var7 == 2 || var8 >= 20) {
            return UC.pC(var0, "Floating-point data-processing (1 source)");
         } else if (var8 >= 4 && var8 < 8) {
            return ah[var7 << 2 | var8 - 4];
         } else {
            return var8 >= 16 && var7 >= 2
               ? UC.pC(var0, "Floating-point data-processing (1 source)")
               : UC.pC(Sc, var8, var0, "Floating-point data-processing (1 source)");
         }
      } else {
         return UC.pC(var0, "Floating point");
      }
   }
}
