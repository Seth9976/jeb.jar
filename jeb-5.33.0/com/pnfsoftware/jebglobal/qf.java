package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

public class qf {
   private static final ji ah = new ji.rQ(ji.DH.pC, ji.DH.A).A(DirectEncodedMemoryArea.get(4, 2));
   private static final Hu[] eP = new Hu[]{wT.UT, wT.ys, wT.pC};
   public static final tz[][] pC = new tz[][]{
      null,
      {UC.pC("SADD16", eP), UC.pC("SADD8", eP), UC.pC("SASX", eP), null, UC.pC("SSAX", eP), null, UC.pC("SSUB16", eP), UC.pC("SSUB8", eP)},
      {UC.pC("QADD16", eP), UC.pC("QADD8", eP), UC.pC("QASX", eP), null, UC.pC("QSAX", eP), null, UC.pC("QSUB16", eP), UC.pC("QSUB8", eP)},
      {UC.pC("SHADD16", eP), UC.pC("SHADD8", eP), UC.pC("SHASX", eP), null, UC.pC("SHSAX", eP), null, UC.pC("SHSUB16", eP), UC.pC("SHSUB8", eP)},
      null,
      {UC.pC("UADD16", eP), UC.pC("UADD8", eP), UC.pC("UASX", eP), null, UC.pC("USAX", eP), null, UC.pC("USUB16", eP), UC.pC("USUB8", eP)},
      {UC.pC("UQADD16", eP), UC.pC("UQADD8", eP), UC.pC("UQASX", eP), null, UC.pC("UQSAX", eP), null, UC.pC("UQSUB16", eP), UC.pC("UQSUB8", eP)},
      {UC.pC("UHADD16", eP), UC.pC("UHADD8", eP), UC.pC("UHASX", eP), null, UC.pC("UHSAX", eP), null, UC.pC("UHSUB16", eP), UC.pC("UHSUB8", eP)}
   };
   public static final tz[][] A = new tz[][]{
      {
            UC.pC("SADD8", wT.wS, wT.ys, wT.pC),
            UC.pC("QADD8", wT.wS, wT.ys, wT.pC),
            UC.pC("SHADD8", wT.wS, wT.ys, wT.pC),
            null,
            UC.pC("UADD8", wT.wS, wT.ys, wT.pC),
            UC.pC("UQADD8", wT.wS, wT.ys, wT.pC),
            UC.pC("UHADD8", wT.wS, wT.ys, wT.pC)
      },
      {
            UC.pC("SADD16", wT.wS, wT.ys, wT.pC),
            UC.pC("QADD16", wT.wS, wT.ys, wT.pC),
            UC.pC("SHADD16", wT.wS, wT.ys, wT.pC),
            null,
            UC.pC("UADD16", wT.wS, wT.ys, wT.pC),
            UC.pC("UQADD16", wT.wS, wT.ys, wT.pC),
            UC.pC("UHADD16", wT.wS, wT.ys, wT.pC)
      },
      {
            UC.pC("SASX", wT.wS, wT.ys, wT.pC),
            UC.pC("QASX", wT.wS, wT.ys, wT.pC),
            UC.pC("SHASX", wT.wS, wT.ys, wT.pC),
            null,
            UC.pC("UASX", wT.wS, wT.ys, wT.pC),
            UC.pC("UQASX", wT.wS, wT.ys, wT.pC),
            UC.pC("UHASX", wT.wS, wT.ys, wT.pC)
      },
      null,
      {
            UC.pC("SSUB8", wT.wS, wT.ys, wT.pC),
            UC.pC("QSUB8", wT.wS, wT.ys, wT.pC),
            UC.pC("SHSUB8", wT.wS, wT.ys, wT.pC),
            null,
            UC.pC("USUB8", wT.wS, wT.ys, wT.pC),
            UC.pC("UQSUB8", wT.wS, wT.ys, wT.pC),
            UC.pC("UHSUB8", wT.wS, wT.ys, wT.pC)
      },
      {
            UC.pC("SSUB16", wT.wS, wT.ys, wT.pC),
            UC.pC("QSUB16", wT.wS, wT.ys, wT.pC),
            UC.pC("SHSUB16", wT.wS, wT.ys, wT.pC),
            null,
            UC.pC("USUB16", wT.wS, wT.ys, wT.pC),
            UC.pC("UQSUB16", wT.wS, wT.ys, wT.pC),
            UC.pC("UHSUB16", wT.wS, wT.ys, wT.pC)
      },
      {
            UC.pC("SSAX", wT.wS, wT.ys, wT.pC),
            UC.pC("QSAX", wT.wS, wT.ys, wT.pC),
            UC.pC("SHSAX", wT.wS, wT.ys, wT.pC),
            null,
            UC.pC("USAX", wT.wS, wT.ys, wT.pC),
            UC.pC("UQSAX", wT.wS, wT.ys, wT.pC),
            UC.pC("UHSAX", wT.wS, wT.ys, wT.pC)
      },
      null
   };
   public static final tz[] kS = new tz[]{
      UC.pC("QADD", wT.UT, wT.pC, wT.ys), UC.pC("QSUB", wT.UT, wT.pC, wT.ys), UC.pC("QDADD", wT.UT, wT.pC, wT.ys), UC.pC("QDSUB", wT.UT, wT.pC, wT.ys)
   };
   public static final tz[] wS = new tz[]{
      UC.pC("QADD", wT.wS, wT.pC, wT.ys), UC.pC("QDADD", wT.wS, wT.pC, wT.ys), UC.pC("QSUB", wT.wS, wT.pC, wT.ys), UC.pC("QDSUB", wT.wS, wT.pC, wT.ys)
   };
   public static final tz[][] UT = new tz[][]{
      {UC.pC("SXTAB16", wT.UT, wT.ys, Dj.E), UC.pC("SXTB16", wT.UT, Dj.E), UC.pC("UXTAB16", wT.UT, wT.ys, Dj.E), UC.pC("UXTB16", wT.UT, Dj.E)},
      null,
      {UC.pC("SXTAB", wT.UT, wT.ys, Dj.E), UC.pC("SXTB", wT.UT, Dj.E), UC.pC("UXTAB", wT.UT, wT.ys, Dj.E), UC.pC("UXTB", wT.UT, Dj.E)},
      {UC.pC("SXTAH", wT.UT, wT.ys, Dj.E), UC.pC("SXTH", wT.UT, Dj.E), UC.pC("UXTAH", wT.UT, wT.ys, Dj.E), UC.pC("UXTH", wT.UT, Dj.E)}
   };
   public static final tz[] E = new tz[]{new UC("SXTH", LY.pC, LY.A), new UC("SXTB", LY.pC, LY.A), new UC("UXTH", LY.pC, LY.A), new UC("UXTB", LY.pC, LY.A)};
   public static final tz[] sY = new tz[]{
      UC.pC("SXTAH", wT.wS, wT.ys, Dj.ys),
      UC.pC("SXTH", wT.wS, Dj.ys).pC(ah),
      UC.pC("UXTAH", wT.wS, wT.ys, Dj.ys),
      UC.pC("UXTH", wT.wS, Dj.ys).pC(ah),
      UC.pC("SXTAB16", wT.wS, wT.ys, Dj.ys),
      UC.pC("SXTB16", wT.wS, Dj.ys),
      UC.pC("UXTAB16", wT.wS, wT.ys, Dj.ys),
      UC.pC("UXTB16", wT.wS, Dj.ys),
      UC.pC("SXTAB", wT.wS, wT.ys, Dj.ys),
      UC.pC("SXTB", wT.wS, Dj.ys).pC(ah),
      UC.pC("UXTAB", wT.wS, wT.ys, Dj.ys),
      UC.pC("UXTB", wT.wS, Dj.ys).pC(ah)
   };
   public static final tz ys = UC.pC("PKHBT", wT.UT, wT.ys, Dj.A);
   public static final tz ld = UC.pC("PKHTB", wT.UT, wT.ys, Dj.A);
   public static final tz gp = UC.pC("PKHBT", wT.wS, wT.ys, Dj.wS);
   public static final tz oT = UC.pC("PKHTB", wT.wS, wT.ys, Dj.wS);
   public static final tz fI = UC.pC("SSAT16", wT.UT, IV.oT, wT.pC);
   public static final tz WR = UC.pC("USAT16", wT.UT, IV.gp, wT.pC);
   public static final tz NS = UC.pC("SSAT", wT.UT, IV.xC, Dj.A);
   public static final tz vP = UC.pC("USAT", wT.UT, IV.vP, Dj.A);
   public static final tz xC = UC.pC("SSAT16", wT.wS, IV.sY, wT.ys);
   public static final tz ED = UC.pC("USAT16", wT.wS, IV.UT, wT.ys);
   public static final tz[] Sc = new tz[]{
      UC.pC("SSAT", wT.wS, IV.NS, Dj.ld), UC.pC("SSAT", wT.wS, IV.NS, Dj.ld), UC.pC("USAT", wT.wS, IV.WR, Dj.ld), UC.pC("USAT", wT.wS, IV.WR, Dj.ld)
   };
}
