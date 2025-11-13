package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

public class Lx {
   private static final de Me = new de.qV(de.EE.q, de.EE.RF).RF(DirectEncodedMemoryArea.get(4, 2));
   private static final Ef[] PV = new Ef[]{Pc.nf, Pc.lm, Pc.Dw};
   public static final Je[][] q = new Je[][]{
      null,
      {Qg.q("SADD16", PV), Qg.q("SADD8", PV), Qg.q("SASX", PV), null, Qg.q("SSAX", PV), null, Qg.q("SSUB16", PV), Qg.q("SSUB8", PV)},
      {Qg.q("QADD16", PV), Qg.q("QADD8", PV), Qg.q("QASX", PV), null, Qg.q("QSAX", PV), null, Qg.q("QSUB16", PV), Qg.q("QSUB8", PV)},
      {Qg.q("SHADD16", PV), Qg.q("SHADD8", PV), Qg.q("SHASX", PV), null, Qg.q("SHSAX", PV), null, Qg.q("SHSUB16", PV), Qg.q("SHSUB8", PV)},
      null,
      {Qg.q("UADD16", PV), Qg.q("UADD8", PV), Qg.q("UASX", PV), null, Qg.q("USAX", PV), null, Qg.q("USUB16", PV), Qg.q("USUB8", PV)},
      {Qg.q("UQADD16", PV), Qg.q("UQADD8", PV), Qg.q("UQASX", PV), null, Qg.q("UQSAX", PV), null, Qg.q("UQSUB16", PV), Qg.q("UQSUB8", PV)},
      {Qg.q("UHADD16", PV), Qg.q("UHADD8", PV), Qg.q("UHASX", PV), null, Qg.q("UHSAX", PV), null, Qg.q("UHSUB16", PV), Qg.q("UHSUB8", PV)}
   };
   public static final Je[][] RF = new Je[][]{
      {
            Qg.q("SADD8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("QADD8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("SHADD8", Pc.gO, Pc.lm, Pc.Dw),
            null,
            Qg.q("UADD8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UQADD8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UHADD8", Pc.gO, Pc.lm, Pc.Dw)
      },
      {
            Qg.q("SADD16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("QADD16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("SHADD16", Pc.gO, Pc.lm, Pc.Dw),
            null,
            Qg.q("UADD16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UQADD16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UHADD16", Pc.gO, Pc.lm, Pc.Dw)
      },
      {
            Qg.q("SASX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("QASX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("SHASX", Pc.gO, Pc.lm, Pc.Dw),
            null,
            Qg.q("UASX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UQASX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UHASX", Pc.gO, Pc.lm, Pc.Dw)
      },
      null,
      {
            Qg.q("SSUB8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("QSUB8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("SHSUB8", Pc.gO, Pc.lm, Pc.Dw),
            null,
            Qg.q("USUB8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UQSUB8", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UHSUB8", Pc.gO, Pc.lm, Pc.Dw)
      },
      {
            Qg.q("SSUB16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("QSUB16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("SHSUB16", Pc.gO, Pc.lm, Pc.Dw),
            null,
            Qg.q("USUB16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UQSUB16", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UHSUB16", Pc.gO, Pc.lm, Pc.Dw)
      },
      {
            Qg.q("SSAX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("QSAX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("SHSAX", Pc.gO, Pc.lm, Pc.Dw),
            null,
            Qg.q("USAX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UQSAX", Pc.gO, Pc.lm, Pc.Dw),
            Qg.q("UHSAX", Pc.gO, Pc.lm, Pc.Dw)
      },
      null
   };
   public static final Je[] xK = new Je[]{
      Qg.q("QADD", Pc.nf, Pc.Dw, Pc.lm), Qg.q("QSUB", Pc.nf, Pc.Dw, Pc.lm), Qg.q("QDADD", Pc.nf, Pc.Dw, Pc.lm), Qg.q("QDSUB", Pc.nf, Pc.Dw, Pc.lm)
   };
   public static final Je[] Dw = new Je[]{
      Qg.q("QADD", Pc.gO, Pc.Dw, Pc.lm), Qg.q("QDADD", Pc.gO, Pc.Dw, Pc.lm), Qg.q("QSUB", Pc.gO, Pc.Dw, Pc.lm), Qg.q("QDSUB", Pc.gO, Pc.Dw, Pc.lm)
   };
   public static final Je[][] Uv = new Je[][]{
      {Qg.q("SXTAB16", Pc.nf, Pc.lm, cn.Hk), Qg.q("SXTB16", Pc.nf, cn.Hk), Qg.q("UXTAB16", Pc.nf, Pc.lm, cn.Hk), Qg.q("UXTB16", Pc.nf, cn.Hk)},
      null,
      {Qg.q("SXTAB", Pc.nf, Pc.lm, cn.Hk), Qg.q("SXTB", Pc.nf, cn.Hk), Qg.q("UXTAB", Pc.nf, Pc.lm, cn.Hk), Qg.q("UXTB", Pc.nf, cn.Hk)},
      {Qg.q("SXTAH", Pc.nf, Pc.lm, cn.Hk), Qg.q("SXTH", Pc.nf, cn.Hk), Qg.q("UXTAH", Pc.nf, Pc.lm, cn.Hk), Qg.q("UXTH", Pc.nf, cn.Hk)}
   };
   public static final Je[] oW = new Je[]{
      new Qg("SXTH", iv.Dw, iv.Uv), new Qg("SXTB", iv.Dw, iv.Uv), new Qg("UXTH", iv.Dw, iv.Uv), new Qg("UXTB", iv.Dw, iv.Uv)
   };
   public static final Je[] gO = new Je[]{
      Qg.q("SXTAH", Pc.gO, Pc.lm, cn.PV),
      Qg.q("SXTH", Pc.gO, cn.PV).q(Me),
      Qg.q("UXTAH", Pc.gO, Pc.lm, cn.PV),
      Qg.q("UXTH", Pc.gO, cn.PV).q(Me),
      Qg.q("SXTAB16", Pc.gO, Pc.lm, cn.PV),
      Qg.q("SXTB16", Pc.gO, cn.PV),
      Qg.q("UXTAB16", Pc.gO, Pc.lm, cn.PV),
      Qg.q("UXTB16", Pc.gO, cn.PV),
      Qg.q("SXTAB", Pc.gO, Pc.lm, cn.PV),
      Qg.q("SXTB", Pc.gO, cn.PV).q(Me),
      Qg.q("UXTAB", Pc.gO, Pc.lm, cn.PV),
      Qg.q("UXTB", Pc.gO, cn.PV).q(Me)
   };
   public static final Je nf = Qg.q("PKHBT", Pc.nf, Pc.lm, cn.HF);
   public static final Je gP = Qg.q("PKHTB", Pc.nf, Pc.lm, cn.HF);
   public static final Je za = Qg.q("PKHBT", Pc.gO, Pc.lm, cn.io);
   public static final Je lm = Qg.q("PKHTB", Pc.gO, Pc.lm, cn.io);
   public static final Je zz = Qg.q("SSAT16", Pc.nf, go.Hk, Pc.Dw);
   public static final Je JY = Qg.q("USAT16", Pc.nf, go.qa, Pc.Dw);
   public static final Je HF = Qg.q("SSAT", Pc.nf, go.KT, cn.HF);
   public static final Je LK = Qg.q("USAT", Pc.nf, go.xW, cn.HF);
   public static final Je io = Qg.q("SSAT16", Pc.gO, go.HF, Pc.lm);
   public static final Je qa = Qg.q("USAT16", Pc.gO, go.zz, Pc.lm);
   public static final Je[] Hk = new Je[]{
      Qg.q("SSAT", Pc.gO, go.oQ, cn.oQ), Qg.q("SSAT", Pc.gO, go.oQ, cn.oQ), Qg.q("USAT", Pc.gO, go.PV, cn.oQ), Qg.q("USAT", Pc.gO, go.PV, cn.oQ)
   };
}
