package com.pnfsoftware.jebglobal;

public class Do {
   private static final de Me = new pM(de.EE.q, de.EE.Dw, de.EE.nf);
   private static final Ef[] PV = new Ef[]{Pc.lm, Pc.Dw, Pc.gO};
   private static final Ef[] oQ = new Ef[]{Pc.lm, Pc.Dw, Pc.gO, Pc.nf};
   private static final Ef[] xW = new Ef[]{Pc.nf, Pc.lm, Pc.Dw, Pc.gO};
   public static final Je[] q = new Je[]{
      new Qg("MUL", PV).q(de.Ef).q(QI.nf).q(12, 4),
      new Qg("MLA", oQ).q(de.Ef).q(QI.nf),
      new Qg("UMAAL", xW).q(QI.nf, QI.lm).q(20, 1),
      new Qg("MLS", oQ).q(QI.nf).q(20, 1),
      new Qg("UMULL", xW).q(de.Ef).q(QI.nf, QI.lm),
      new Qg("UMLAL", xW).q(de.Ef).q(QI.nf, QI.lm),
      new Qg("SMULL", xW).q(de.Ef).q(QI.nf, QI.lm),
      new Qg("SMLAL", xW).q(de.Ef).q(QI.nf, QI.lm)
   };
   public static final Je[] RF = new Je[]{
      new Qg("SMLABB", oQ).q(QI.nf),
      new Qg("SMLATB", oQ).q(QI.nf),
      new Qg("SMLABT", oQ).q(QI.nf),
      new Qg("SMLATT", oQ).q(QI.nf),
      new Qg("SMLAWB", oQ).q(QI.nf),
      new Qg("SMULWB", PV).q(QI.nf),
      new Qg("SMLAWT", oQ).q(QI.nf),
      new Qg("SMULWT", PV).q(QI.nf),
      new Qg("SMLALBB", xW).q(QI.nf, QI.lm),
      new Qg("SMLALTB", xW).q(QI.nf, QI.lm),
      new Qg("SMLALBT", xW).q(QI.nf, QI.lm),
      new Qg("SMLALTT", xW).q(QI.nf, QI.lm),
      new Qg("SMULBB", PV).q(QI.nf),
      new Qg("SMULTB", PV).q(QI.nf),
      new Qg("SMULBT", PV).q(QI.nf),
      new Qg("SMULTT", PV).q(QI.nf)
   };
   public static final Je[] xK = new Je[]{
      new Qg("SMLAD", oQ).q(QI.nf),
      new Qg("SMUAD", PV).q(QI.nf),
      new Qg("SMLADX", oQ).q(QI.nf),
      new Qg("SMUADX", PV).q(QI.nf),
      new Qg("SMLSD", oQ).q(QI.nf),
      new Qg("SMUSD", PV).q(QI.nf),
      new Qg("SMLSDX", oQ).q(QI.nf),
      new Qg("SMUSDX", PV).q(QI.nf)
   };
   public static final Je[] Dw = new Je[]{
      new Qg("SMLALD", xW).q(QI.nf, QI.lm),
      new Qg("SMLALD", xW).q(QI.nf, QI.lm),
      new Qg("SMLALDX", xW).q(QI.nf, QI.lm),
      new Qg("SMLALDX", xW).q(QI.nf, QI.lm),
      new Qg("SMLSLD", xW).q(QI.nf, QI.lm),
      new Qg("SMLSLD", xW).q(QI.nf, QI.lm),
      new Qg("SMLSLDX", xW).q(QI.nf, QI.lm),
      new Qg("SMLSLDX", xW).q(QI.nf, QI.lm)
   };
   public static final Je[] Uv = new Je[]{
      new Qg(0, "SMMLA", oQ).q(QI.nf),
      new Qg(1, "SMMUL", PV).q(QI.nf),
      new Qg(2, "SMMLAR", oQ).q(QI.nf),
      new Qg(3, "SMMULR", PV).q(QI.nf),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(12, "SMMLS", oQ).q(QI.nf),
      new Qg(13, "SMMLS", oQ).q(QI.nf),
      new Qg(14, "SMMLSR", oQ).q(QI.nf),
      new Qg(15, "SMMLSR", oQ).q(QI.nf)
   };
   public static final Je oW = new Qg("SDIV", PV).q(QI.nf);
   public static final Je gO = new Qg("UDIV", PV).q(QI.nf);
   private static final Ef[] KT = new Ef[]{Pc.gO, Pc.lm, Pc.Dw};
   private static final Ef[] Gf = new Ef[]{Pc.gO, Pc.lm, Pc.Dw, Pc.nf};
   private static final Ef[] Ef = new Ef[]{Pc.nf, Pc.gO, Pc.lm, Pc.Dw};
   public static final Je[] nf = new Je[]{
      new Qg(0, "MLA", Gf).q(QI.nf),
      new Qg(1, "MUL", KT).q(QI.nf).q(Me),
      new Qg(2, "MLS", Gf).q(QI.nf),
      new Qg(3, "MLS", Gf).q(QI.nf),
      null,
      null,
      null,
      null,
      new Qg(8, "SMLABB", Gf).q(QI.nf),
      new Qg(9, "SMULBB", KT).q(QI.nf),
      new Qg(10, "SMLABT", Gf).q(QI.nf),
      new Qg(11, "SMULBT", KT).q(QI.nf),
      new Qg(12, "SMLATB", Gf).q(QI.nf),
      new Qg(13, "SMULTB", KT).q(QI.nf),
      new Qg(14, "SMLATT", Gf).q(QI.nf),
      new Qg(15, "SMULTT", KT).q(QI.nf),
      new Qg(16, "SMLAD", Gf).q(QI.nf),
      new Qg(17, "SMUAD", KT).q(QI.nf),
      new Qg(18, "SMLADX", Gf).q(QI.nf),
      new Qg(19, "SMUADX", KT).q(QI.nf),
      null,
      null,
      null,
      null,
      new Qg(24, "SMLAWB", Gf).q(QI.nf),
      new Qg(25, "SMULWB", KT).q(QI.nf),
      new Qg(26, "SMLAWT", Gf).q(QI.nf),
      new Qg(27, "SMULWT", KT).q(QI.nf),
      null,
      null,
      null,
      null,
      new Qg(32, "SMLSD", Gf).q(QI.nf),
      new Qg(33, "SMUSD", KT).q(QI.nf),
      new Qg(34, "SMLSDX", Gf).q(QI.nf),
      new Qg(35, "SMUSDX", KT).q(QI.nf),
      null,
      null,
      null,
      null,
      new Qg(40, "SMMLA", Gf).q(QI.nf),
      new Qg(41, "SMMUL", KT).q(QI.nf),
      new Qg(42, "SMMLAR", Gf).q(QI.nf),
      new Qg(43, "SMMULR", KT).q(QI.nf),
      null,
      null,
      null,
      null,
      new Qg(48, "SMMLS", Gf).q(QI.nf),
      new Qg(49, "SMMLS", Gf).q(QI.nf),
      new Qg(50, "SMMLSR", Gf).q(QI.nf),
      new Qg(51, "SMMLSR", Gf).q(QI.nf),
      null,
      null,
      null,
      null,
      new Qg(56, "USADA8", Gf).q(QI.nf),
      new Qg(57, "USAD8", KT).q(QI.nf),
      null,
      null,
      null,
      null,
      null,
      null
   };
   public static final Je gP = new Qg("SMULL", Ef).q(QI.nf, QI.lm);
   public static final Je za = new Qg("UMULL", Ef).q(QI.nf, QI.lm);
   public static final Je lm = new Qg("SMLAL", Ef).q(QI.nf, QI.lm);
   public static final Je[] zz = new Je[]{
      new Qg("SMLALBB", Ef).q(QI.nf, QI.lm),
      new Qg("SMLALBT", Ef).q(QI.nf, QI.lm),
      new Qg("SMLALTB", Ef).q(QI.nf, QI.lm),
      new Qg("SMLALTT", Ef).q(QI.nf, QI.lm),
      new Qg("SMLALD", Ef).q(QI.nf, QI.lm),
      new Qg("SMLALDX", Ef).q(QI.nf, QI.lm),
      null,
      null
   };
   public static final Je JY = new Qg("SMLSLD", Ef).q(QI.nf, QI.lm);
   public static final Je HF = new Qg("SMLSLDX", Ef).q(QI.nf, QI.lm);
   public static final Je LK = new Qg("UMLAL", Ef).q(QI.nf, QI.lm);
   public static final Je io = new Qg("UMAAL", Ef).q(QI.nf, QI.lm);
   public static final Je qa = new Qg("SDIV", KT).q(QI.nf);
   public static final Je Hk = new Qg("UDIV", KT).q(QI.nf);

   public static int q(byte[] var0) {
      return (var0[3] & 224) >>> 4 | ((var0[2] & 240) == 240 ? 1 : 0);
   }
}
