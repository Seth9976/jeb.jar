package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class cgd {
   private static final cfk[] A = cgc.Sc;
   private static final cfs[] kS = new cfs[]{
      new cfs("BLTZ", cgc.pC, cfo.wS, null, A),
      new cfs("BGEZ", cgc.pC, cfo.sY, null, A),
      new cfs("BLTZL", cgc.kS, cfo.wS, cfs.ys, A),
      new cfs("BGEZL", cgc.kS, cfo.sY, cfs.ys, A),
      cfs.pC,
      cfs.pC,
      new cfs("DAHI", cfs.NS, cfn.pC, cfl.ld),
      cfs.pC
   };
   private static final cfk[] wS = new cfk[]{cfn.pC, cfl.ld};
   private static final cfs[] UT = new cfs[]{
      new cfs("TGEI", cfs.ys, wS),
      new cfs("TGEIU", cfs.ys, wS),
      new cfs("TLTI", cfs.ys, wS),
      new cfs("TLTIU", cfs.ys, wS),
      new cfs("TEQI", cfs.ys, wS),
      cfs.pC,
      new cfs("TNEI", cfs.ys, wS),
      cfs.pC
   };
   private static final cfs[] E = new cfs[]{
      new cfs("BLTZAL", cgc.wS, cfo.wS, cfs.A, A),
      new cfs("BGEZAL", cgc.wS, cfo.sY, cfs.A, A),
      new cfs("BLTZALL", cgc.E, cfo.wS, cfs.ys, A),
      new cfs("BGEZALL", cgc.E, cfo.sY, cfs.ys, A),
      cfs.pC,
      cfs.pC,
      cfs.pC,
      new cfs("SIGRIE", cfs.kS, cfl.Ab)
   };
   static final cfh pC = new cfh(131841);
   private static final cfs[] sY = new cfs[]{new cfs("NAL", pC, cfs.kS), new cfs("BAL", cgc.wS, cfl.A)};
   private static final cfk ys = new cfm(cfl.ld, cfn.pC);
   private static final cfs[] ld = new cfs[]{new cfs("DATI", cfs.NS, wS), new cfs("SYNCI", cfs.wS, ys)};

   public static cfs pC(byte[] var0, com.pnfsoftware.jeb.corei.parsers.mips.Tb var1, boolean var2) throws ProcessorException {
      int var3 = (var0[1] & 24) >>> 3;
      int var4 = var0[1] & 7;
      int var5 = DirectEncodedMemoryArea.get(21, 5).decodeInt(var0);
      switch (var3) {
         case 0:
            if (var4 != 4 && var4 != 5) {
               if (var4 == 7) {
                  return cfs.A(var0, "Regimm00");
               }

               return kS[var4];
            }

            return cfs.pC(var0, "Regimm00");
         case 1:
            if (var4 != 5 && var4 != 7) {
               return UT[var4];
            }

            return cfs.pC(var0, "Regimm01");
         case 2:
            if (var5 == 0 && var4 < 2) {
               return sY[var4];
            } else {
               if (MathUtil.betweenInclusive(var4, 4L, 6L)) {
                  return cfs.pC(var0, "Regimm10");
               }

               return E[var4];
            }
         case 3:
            if (var5 == 0 && var4 == 0) {
               return cga.kS;
            } else if (var5 == 0 && var4 == 4) {
               return cga.A;
            } else if (var5 == 0 && var4 == 5) {
               return cga.wS;
            } else {
               if (var4 < 6) {
                  return cfs.pC(var0, "Regimm11");
               }

               return ld[var4 - 6];
            }
         default:
            return null;
      }
   }
}
