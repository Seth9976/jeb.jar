package com.pnfsoftware.jebglobal;

public class NN {
   private static final ji ah = new Ef(ji.DH.pC, ji.DH.wS, ji.DH.ys);
   private static final Hu[] eP = new Hu[]{wT.ys, wT.pC, wT.wS};
   private static final Hu[] UO = new Hu[]{wT.ys, wT.pC, wT.wS, wT.UT};
   private static final Hu[] Ab = new Hu[]{wT.UT, wT.ys, wT.pC, wT.wS};
   public static final tz[] pC = new tz[]{
      new UC("MUL", eP).pC(ji.Ek).pC(cT.ys).pC(12, 4),
      new UC("MLA", UO).pC(ji.Ek).pC(cT.ys),
      new UC("UMAAL", Ab).pC(cT.ys, cT.oT).pC(20, 1),
      new UC("MLS", UO).pC(cT.ys).pC(20, 1),
      new UC("UMULL", Ab).pC(ji.Ek).pC(cT.ys, cT.oT),
      new UC("UMLAL", Ab).pC(ji.Ek).pC(cT.ys, cT.oT),
      new UC("SMULL", Ab).pC(ji.Ek).pC(cT.ys, cT.oT),
      new UC("SMLAL", Ab).pC(ji.Ek).pC(cT.ys, cT.oT)
   };
   public static final tz[] A = new tz[]{
      new UC("SMLABB", UO).pC(cT.ys),
      new UC("SMLATB", UO).pC(cT.ys),
      new UC("SMLABT", UO).pC(cT.ys),
      new UC("SMLATT", UO).pC(cT.ys),
      new UC("SMLAWB", UO).pC(cT.ys),
      new UC("SMULWB", eP).pC(cT.ys),
      new UC("SMLAWT", UO).pC(cT.ys),
      new UC("SMULWT", eP).pC(cT.ys),
      new UC("SMLALBB", Ab).pC(cT.ys, cT.oT),
      new UC("SMLALTB", Ab).pC(cT.ys, cT.oT),
      new UC("SMLALBT", Ab).pC(cT.ys, cT.oT),
      new UC("SMLALTT", Ab).pC(cT.ys, cT.oT),
      new UC("SMULBB", eP).pC(cT.ys),
      new UC("SMULTB", eP).pC(cT.ys),
      new UC("SMULBT", eP).pC(cT.ys),
      new UC("SMULTT", eP).pC(cT.ys)
   };
   public static final tz[] kS = new tz[]{
      new UC("SMLAD", UO).pC(cT.ys),
      new UC("SMUAD", eP).pC(cT.ys),
      new UC("SMLADX", UO).pC(cT.ys),
      new UC("SMUADX", eP).pC(cT.ys),
      new UC("SMLSD", UO).pC(cT.ys),
      new UC("SMUSD", eP).pC(cT.ys),
      new UC("SMLSDX", UO).pC(cT.ys),
      new UC("SMUSDX", eP).pC(cT.ys)
   };
   public static final tz[] wS = new tz[]{
      new UC("SMLALD", Ab).pC(cT.ys, cT.oT),
      new UC("SMLALD", Ab).pC(cT.ys, cT.oT),
      new UC("SMLALDX", Ab).pC(cT.ys, cT.oT),
      new UC("SMLALDX", Ab).pC(cT.ys, cT.oT),
      new UC("SMLSLD", Ab).pC(cT.ys, cT.oT),
      new UC("SMLSLD", Ab).pC(cT.ys, cT.oT),
      new UC("SMLSLDX", Ab).pC(cT.ys, cT.oT),
      new UC("SMLSLDX", Ab).pC(cT.ys, cT.oT)
   };
   public static final tz[] UT = new tz[]{
      new UC(0, "SMMLA", UO).pC(cT.ys),
      new UC(1, "SMMUL", eP).pC(cT.ys),
      new UC(2, "SMMLAR", UO).pC(cT.ys),
      new UC(3, "SMMULR", eP).pC(cT.ys),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(12, "SMMLS", UO).pC(cT.ys),
      new UC(13, "SMMLS", UO).pC(cT.ys),
      new UC(14, "SMMLSR", UO).pC(cT.ys),
      new UC(15, "SMMLSR", UO).pC(cT.ys)
   };
   public static final tz E = new UC("SDIV", eP).pC(cT.ys);
   public static final tz sY = new UC("UDIV", eP).pC(cT.ys);
   private static final Hu[] rl = new Hu[]{wT.wS, wT.ys, wT.pC};
   private static final Hu[] z = new Hu[]{wT.wS, wT.ys, wT.pC, wT.UT};
   private static final Hu[] Ek = new Hu[]{wT.UT, wT.wS, wT.ys, wT.pC};
   public static final tz[] ys = new tz[]{
      new UC(0, "MLA", z).pC(cT.ys),
      new UC(1, "MUL", rl).pC(cT.ys).pC(ah),
      new UC(2, "MLS", z).pC(cT.ys),
      new UC(3, "MLS", z).pC(cT.ys),
      null,
      null,
      null,
      null,
      new UC(8, "SMLABB", z).pC(cT.ys),
      new UC(9, "SMULBB", rl).pC(cT.ys),
      new UC(10, "SMLABT", z).pC(cT.ys),
      new UC(11, "SMULBT", rl).pC(cT.ys),
      new UC(12, "SMLATB", z).pC(cT.ys),
      new UC(13, "SMULTB", rl).pC(cT.ys),
      new UC(14, "SMLATT", z).pC(cT.ys),
      new UC(15, "SMULTT", rl).pC(cT.ys),
      new UC(16, "SMLAD", z).pC(cT.ys),
      new UC(17, "SMUAD", rl).pC(cT.ys),
      new UC(18, "SMLADX", z).pC(cT.ys),
      new UC(19, "SMUADX", rl).pC(cT.ys),
      null,
      null,
      null,
      null,
      new UC(24, "SMLAWB", z).pC(cT.ys),
      new UC(25, "SMULWB", rl).pC(cT.ys),
      new UC(26, "SMLAWT", z).pC(cT.ys),
      new UC(27, "SMULWT", rl).pC(cT.ys),
      null,
      null,
      null,
      null,
      new UC(32, "SMLSD", z).pC(cT.ys),
      new UC(33, "SMUSD", rl).pC(cT.ys),
      new UC(34, "SMLSDX", z).pC(cT.ys),
      new UC(35, "SMUSDX", rl).pC(cT.ys),
      null,
      null,
      null,
      null,
      new UC(40, "SMMLA", z).pC(cT.ys),
      new UC(41, "SMMUL", rl).pC(cT.ys),
      new UC(42, "SMMLAR", z).pC(cT.ys),
      new UC(43, "SMMULR", rl).pC(cT.ys),
      null,
      null,
      null,
      null,
      new UC(48, "SMMLS", z).pC(cT.ys),
      new UC(49, "SMMLS", z).pC(cT.ys),
      new UC(50, "SMMLSR", z).pC(cT.ys),
      new UC(51, "SMMLSR", z).pC(cT.ys),
      null,
      null,
      null,
      null,
      new UC(56, "USADA8", z).pC(cT.ys),
      new UC(57, "USAD8", rl).pC(cT.ys),
      null,
      null,
      null,
      null,
      null,
      null
   };
   public static final tz ld = new UC("SMULL", Ek).pC(cT.ys, cT.oT);
   public static final tz gp = new UC("UMULL", Ek).pC(cT.ys, cT.oT);
   public static final tz oT = new UC("SMLAL", Ek).pC(cT.ys, cT.oT);
   public static final tz[] fI = new tz[]{
      new UC("SMLALBB", Ek).pC(cT.ys, cT.oT),
      new UC("SMLALBT", Ek).pC(cT.ys, cT.oT),
      new UC("SMLALTB", Ek).pC(cT.ys, cT.oT),
      new UC("SMLALTT", Ek).pC(cT.ys, cT.oT),
      new UC("SMLALD", Ek).pC(cT.ys, cT.oT),
      new UC("SMLALDX", Ek).pC(cT.ys, cT.oT),
      null,
      null
   };
   public static final tz WR = new UC("SMLSLD", Ek).pC(cT.ys, cT.oT);
   public static final tz NS = new UC("SMLSLDX", Ek).pC(cT.ys, cT.oT);
   public static final tz vP = new UC("UMLAL", Ek).pC(cT.ys, cT.oT);
   public static final tz xC = new UC("UMAAL", Ek).pC(cT.ys, cT.oT);
   public static final tz ED = new UC("SDIV", rl).pC(cT.ys);
   public static final tz Sc = new UC("UDIV", rl).pC(cT.ys);

   public static int pC(byte[] var0) {
      return (var0[3] & 224) >>> 4 | ((var0[2] & 240) == 240 ? 1 : 0);
   }
}
