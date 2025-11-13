package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class FI {
   private static final IEncodedMemoryArea RF = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea xK = g.q;
   private static final IEncodedMemoryArea Dw = g.RF;
   private static final IEncodedMemoryArea Uv = DirectEncodedMemoryArea.get(14, 1);
   private static final IEncodedMemoryArea oW = g.xK;
   private static final IEncodedMemoryArea gO = g.Dw;
   private static final IEncodedMemoryArea nf = DirectEncodedMemoryArea.get(16, 6);
   private static final IEncodedMemoryArea gP = DirectEncodedMemoryArea.get(21, 2);
   private static final IEncodedMemoryArea za = DirectEncodedMemoryArea.get(21, 4);
   private static final IEncodedMemoryArea lm = g.Uv;
   private static final IEncodedMemoryArea zz = g.oW;
   private static final IEncodedMemoryArea JY = DirectEncodedMemoryArea.get(23, 2);
   private static final IEncodedMemoryArea HF = new EncodedMemoryAreaList(JY, xK);
   private static final IEncodedMemoryArea LK = new EncodedMemoryAreaList(JY, Dw);
   private static final IEncodedMemoryArea io = new EncodedMemoryAreaList(JY, Uv);
   private static final dD qa = g.gO;
   private static final Ef Hk = Bf.q(YH.qa, YH.zz);
   private static final Ef Me = Bf.q(YH.qa, YH.zz, cn.gP, VirtualEncodedMemoryArea._1);
   private static final Ef PV = Bf.q(YH.qa, YH.zz, cn.gP, VirtualEncodedMemoryArea._2);
   private static final Ef oQ = Bf.q(YH.qa, YH.zz, cn.gP, VirtualEncodedMemoryArea._4);
   private static final Ef xW = Bf.q(YH.qa, YH.zz, cn.gP, JY);
   private static final Ef KT = Bf.q(YH.qa, YH.LK);
   private static final Ef Gf = Bf.q(YH.qa, YH.LK, cn.gP, VirtualEncodedMemoryArea._1);
   private static final Ef Ef = Bf.q(YH.qa, YH.LK, cn.gP, VirtualEncodedMemoryArea._2);
   private static final Ef cC = Bf.q(YH.qa, YH.LK, cn.gP, JY);
   private static final Ef sH = Bf.q(YH.qa, Bq.fQ, new EncodedMemoryAreaList(Uv, VirtualEncodedMemoryArea.get(2, 2)), null, Bf.Uv);
   private static final Ef CE = Bf.q(YH.qa, Bq.fQ, new EncodedMemoryAreaList(Uv, VirtualEncodedMemoryArea.get(2, 2)), JY, Bf.Uv);
   private static final Ef wF = Bf.q(YH.qa, Bq.fQ, new EncodedMemoryAreaList(lm, VirtualEncodedMemoryArea.get(2, 2)), null, Bf.Uv);
   private static final Ef If = Bf.q(YH.qa, Bq.fQ, new EncodedMemoryAreaList(lm, VirtualEncodedMemoryArea.get(2, 2)), VirtualEncodedMemoryArea._1, Bf.Uv);
   private static final Ef Dz = Bf.q(YH.qa, Bq.fQ, new EncodedMemoryAreaList(lm, VirtualEncodedMemoryArea.get(2, 2)), VirtualEncodedMemoryArea._2, Bf.Uv);
   private static final Ef mI = Bf.q(YH.qa, Bq.fQ, new EncodedMemoryAreaList(lm, VirtualEncodedMemoryArea.get(2, 2)), Dw, Bf.Uv);
   private static final Ef jq = Bf.q(YH.qa, Bq.fi);
   private static final Ef ui = Bf.q(YH.qa, Bq.fi, new EncodedMemoryAreaList(Uv, VirtualEncodedMemoryArea.get(2, 2)), null, Bf.Uv);
   private static final Ef TX = Bf.q(YH.qa, Bq.fi, new EncodedMemoryAreaList(Uv, VirtualEncodedMemoryArea.get(2, 2)), JY, Bf.Uv);
   private static final Ef Rr = Bf.q(YH.qa, Bq.fi, new EncodedMemoryAreaList(lm, VirtualEncodedMemoryArea.get(2, 2)), null, Bf.Uv);
   private static final Ef EB = Bf.q(YH.qa, Bq.fi, new EncodedMemoryAreaList(lm, VirtualEncodedMemoryArea.get(2, 2)), Dw, Bf.Uv);
   private static final Ef Xo = Bf.q(YH.qa, Bq.fi, new EncodedMemoryAreaList(lm, VirtualEncodedMemoryArea.get(2, 2)), JY, Bf.Uv);
   private static final Ef Bu = Bf.q(YH.qa, Bq.fi, cn.gP, Dw);
   private static final Ef IN = Bf.q(YH.qa, Bq.fi, cn.gP, JY);
   private static final Ef rL = Bf.q(YH.qa, EncodedMemoryAreaList.shift(oW, 4), Bf.Dw);
   private static final Ef eJ = Bf.q(YH.qa, EncodedMemoryAreaList.shift(oW, 5), Bf.Dw);
   private static final Ef YN = q(YH.qa, go.Me);
   private static final Ef Rv = q(YH.qa, new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, dX.q(oW, true, dX.q(gP), false)));
   private static final Ef zx = q(YH.qa, new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, dX.q(oW, true, dX.q(zz), false)));
   private static final Ef ZT = q(YH.qa, new go(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, dX.q(oW, true, dX.q(JY), false)));
   private static final Ef Ri = Bf.q(Bq.Rv, dX.xK(gO, JY));
   private static final Ef GY = Bf.q(Bq.zx, dX.xK(gO, JY));
   private static final Ef Wx = Bf.q(YH.qa, nf);
   private static final Ef AB = Bf.q(YH.qa, EncodedMemoryAreaList.shift(nf, 1));
   private static final Ef CY = Bf.q(YH.qa, EncodedMemoryAreaList.shift(nf, 2));
   private static final Ef WI = Bf.q(YH.qa, dX.xK(nf, JY));
   private static final Ef Tq = q(YH.qa, go.jq);
   private static final Ef Yp = q(YH.qa, go.GY);
   private static final Ef Gu = Bf.q(Bq.Rv, YH.LK);
   private static final Ef nY = Bf.q(Bq.zx, YH.LK);
   private static final Ef lF = new xT(64, Bq.Gf);
   private static final Ef nq = new xT(64, Bq.Ef);
   private static final Ef NX = new xT(64, Bq.cC);
   private static final Ef br = new xT(64, Bq.sH);
   private static final Ef tW = new xT(64, Bq.wF);
   private static final Ef ZA = new xT(64, Bq.Dz);
   private static final Ef Ov = new xT(64, Bq.mI);
   private static final Ef Lj = new yc(RF, gP, Dm.PV);
   private static final Ef nv = new yc(RF, zz, Dm.Uv);
   private static final Ef LL = new yc(RF, JY, Dm.Uv);
   private static final String[] PQ = new String[]{
      "PLDL1KEEP",
      "PLDL1STRM",
      "PLDL2KEEP",
      "PLDL2STRM",
      "PLDL3KEEP",
      "PLDL3STRM",
      null,
      null,
      "PSTL1KEEP",
      "PSTL1STRM",
      "PSTL2KEEP",
      "PSTL2STRM",
      "PSTL3KEEP",
      "PSTL3STRM",
      null,
      null
   };
   public static final wJ q = new wJ(jD.eo.q, PQ, DirectEncodedMemoryArea.get(0, 5), "#%d");
   private static final Ef[] fQ = new Ef[]{lF, Bq.mJ, Hk};
   private static final Ef[] fi = new Ef[]{nq, Bq.mJ, Hk};
   private static final Ef[] bl = new Ef[]{NX, Bq.mJ, Hk};
   private static final Ef[] jb = new Ef[]{nq, Bq.mJ, Me};
   private static final Ef[] pQ = new Ef[]{NX, Bq.mJ, Me};
   private static final Ef[] kf = new Ef[]{NX, Bq.mJ, PV};
   private static final Ef[] GM = new Ef[]{lF, Bq.mJ, KT};
   private static final Ef[] TQ = new Ef[]{nq, Bq.mJ, KT};
   private static final Ef[] Yw = new Ef[]{NX, Bq.mJ, KT};
   private static final Ef[] IY = new Ef[]{nq, Bq.mJ, Gf};
   private static final Ef[] qR = new Ef[]{NX, Bq.mJ, Gf};
   private static final Ef[] YA = new Ef[]{NX, Bq.mJ, Ef};
   private static final Ef[] fw = new Ef[]{nq, Bq.mJ, wF};
   private static final Ef[] Wp = new Ef[]{nq, Bq.mJ, If};
   private static final Ef[] cY = new Ef[]{nq, Bq.mJ, Dz};
   private static final Ef[] PY = new Ef[]{NX, Bq.mJ, jq};
   private static final Ef[] cR = new Ef[]{NX, Bq.mJ, Rr};
   private static final Ef[] eC = new Ef[]{NX, Bq.mJ, Xo};
   private static final Ef[] ND = new Ef[]{NX, Bq.mJ, IN};
   private static final Ef[] Qu = new Ef[]{br, Bq.mJ, xW};
   private static final Ef[] jh = new Ef[]{ZA, Bq.mJ, xW};
   private static final Ef[] Jf = new Ef[]{ZA, Bq.mJ, cC};
   private static final Ef[] vC = new Ef[]{Ov, Bq.mJ, xW};
   private static final Ef[] of = new Ef[]{lF, Bq.mJ, YN};
   private static final Ef[] os = new Ef[]{nq, Bq.mJ, YN};
   private static final Ef[] iu = new Ef[]{NX, Bq.mJ, YN};
   private static final Ef[] fn = new Ef[]{br, Bq.mJ, YN};
   private static final Ef[] ZU = new Ef[]{ZA, Bq.mJ, YN};
   private static final Ef[] Sz = new Ef[]{Ov, Bq.mJ, YN};
   private static final Ef[] fq = new Ef[]{Ov, Bq.mJ, rL};
   private static final Ef[] mJ = new Ef[]{Ov, Bq.mJ, eJ};
   private static final Ef[] Bs = new Ef[]{NX, Bq.mJ, GY};
   private static final Ef[] rV = new Ef[]{lF, Bq.mJ, Wx};
   private static final Ef[] WX = new Ef[]{nq, Bq.mJ, Wx};
   private static final Ef[] CB = new Ef[]{nq, Bq.mJ, AB};
   private static final Ef[] C = new Ef[]{NX, Bq.mJ, AB};
   private static final Ef[] GC = new Ef[]{NX, Bq.mJ, Wx};
   private static final Ef[] KF = new Ef[]{NX, Bq.mJ, CY};
   private static final Ef[] rk = new Ef[]{tW, Bq.mJ, WI};
   private static final Ef[] cy = new Ef[]{nq, Bq.mJ, Gu};
   private static final Ef[] jk = new Ef[]{NX, Bq.mJ, nY};
   private static final Ef[] Cl = new Ef[]{br, Bq.mJ, nY};
   private static final Ef[] hM = new Ef[]{nq, Bq.mJ, Ri};
   private static final Ef[] kv = new Ef[]{NX, Bq.Sz, jq};
   private static final Ef[] oS = new Ef[]{NX, Bq.Sz, IN};
   private static final Ef[] FG = new Ef[]{nq, Bq.Sz, sH};
   private static final Ef[] Al = new Ef[]{NX, Bq.Sz, ui};
   private static final Ef[] Kn = new Ef[]{nq, Bq.Sz, CE};
   private static final Ef[] vh = new Ef[]{NX, Bq.Sz, TX};
   private static final Ef[] Rd = new Ef[]{br, Bq.Sz, xW};
   private static final Ef[] Eq = new Ef[]{ZA, Bq.Sz, xW};
   private static final Ef[] hP = new Ef[]{Ov, Bq.Sz, xW};
   private static final Ef[] wN = new Ef[]{ZA, Bq.Sz, YN};
   private static final Ef[] Uc = new Ef[]{br, Bq.Sz, YN};
   private static final Ef[] TB = new Ef[]{Ov, Bq.Sz, YN};
   private static final Ef[] dg = new Ef[]{nq, Bq.Sz, Ri};
   private static final Ef[] hw = new Ef[]{NX, Bq.Sz, GY};
   private static final Ef[] gm = new Ef[]{nq, Bq.Sz, Gu};
   private static final Ef[] uY = new Ef[]{NX, Bq.Sz, nY};
   private static final Ef[] sc = new Ef[]{br, Bq.Sz, nY};
   private static final Ef[] wQ = new Ef[]{Lj, Bq.mJ, xW};
   private static final Ef[] Oj = new Ef[]{Lj, Bq.Sz, xW};
   private static final Ef[] VW = new Ef[]{Lj, Bq.mJ, Rv};
   private static final Ef[] ap = new Ef[]{Lj, Bq.Sz, Rv};
   private static final Ef[] RL = new Ef[]{LL, Bq.mJ, oQ};
   private static final Ef[] hy = new Ef[]{nv, Bq.Sz, oQ};
   private static final Ef[] Xi = new Ef[]{LL, Bq.mJ, ZT};
   private static final Ef[] Ag = new Ef[]{nv, Bq.Sz, zx};
   private static final Ef[] rp = new Ef[]{Bq.xW, Yp};
   private static final Ef[] CW = new Ef[]{Bq.IY, Yp};
   private static final Je qm = new Qg("LDR", Bq.IY, Yp).q(QJ.NX);
   private static final Je LR = new Qg("LDR", Bq.xW, Yp).q(QJ.NX);
   private static final Je Uz = new iQ(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, Dw, q, Bq.Sz, Tq).q(QJ.NX);
   private static final Je dF = new iQ(
         new String[]{"LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", null, null, "LD1W", "LDFF1W"}, LK, fw
      )
      .q(QJ.NX);
   private static final Je kk = new iQ(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, Dw, q, Bq.Sz, mI).q(QJ.NX);
   private static final Je Rc = new iQ(new String[]{"LD1SH", "LDFF1SH", "LD1H", "LDFF1H"}, Dw, Wp).q(QJ.NX);
   private static final Je jz = new iQ(new String[]{null, null, "LD1W", "LDFF1W"}, Dw, cY).q(QJ.NX);
   private static final Je MT = new iQ(
         new String[]{"LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", null, null, "LD1W", "LDFF1W"}, LK, hM
      )
      .q(QJ.NX);
   private static final Je bY = new Qg("LD1RB", rk).q(QJ.NX);
   private static final Je LS = new Qg("LD1RH", rk).q(QJ.NX);
   private static final Je fG = new Qg("LD1RW", rk).q(QJ.NX);
   private static final Je[] cO = new Je[]{
      bY,
      bY,
      bY,
      bY,
      new Qg("LD1RSW", KF).q(QJ.NX),
      LS,
      LS,
      LS,
      new Qg("LD1RSH", C).q(QJ.NX),
      new Qg("LD1RSH", CB).q(QJ.NX),
      fG,
      fG,
      new Qg("LD1RSB", GC).q(QJ.NX),
      new Qg("LD1RSB", WX).q(QJ.NX),
      new Qg("LD1RSB", rV).q(QJ.NX),
      new Qg("LD1RD", rk).q(QJ.NX)
   };
   private static final Je wr = new iQ(new String[]{"LDNT1SB", "LDNT1B", "LDNT1SH", "LDNT1H", null, "LDNT1W"}, HF, cy).q(QJ.br);
   private static final Je pe = new iQ(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, JY, q, Bq.Sz, Hk).q(QJ.NX);
   private static final Je Gg = new iQ(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, JY, q, Bq.Sz, Ri).q(QJ.NX);
   private static final Je[][] CK = new Je[][]{
      {new Qg("LD1RQB", vC).q(QJ.NX), new Qg("LD1ROB", vC).q(QJ.rk)},
      {new Qg("LD1RQH", vC).q(QJ.NX), new Qg("LD1ROH", vC).q(QJ.rk)},
      {new Qg("LD1RQW", vC).q(QJ.NX), new Qg("LD1ROW", vC).q(QJ.rk)},
      {new Qg("LD1RQD", vC).q(QJ.NX), new Qg("LD1ROD", vC).q(QJ.rk)}
   };
   private static final Je[][] PW = new Je[][]{
      {new Qg("LD1RQB", fq).q(QJ.NX), new Qg("LD1ROB", mJ).q(QJ.rk)},
      {new Qg("LD1RQH", fq).q(QJ.NX), new Qg("LD1ROH", mJ).q(QJ.rk)},
      {new Qg("LD1RQW", fq).q(QJ.NX), new Qg("LD1ROW", mJ).q(QJ.rk)},
      {new Qg("LD1RQD", fq).q(QJ.NX), new Qg("LD1ROD", mJ).q(QJ.rk)}
   };
   private static final Je zm = new iQ(new String[]{null, null, "LD1W", "LD1D"}, JY, fn).q(QJ.tW);
   private static final Je Wn = new Qg("LD1B", jh).q(QJ.NX);
   private static final Je eG = new Qg("LD1H", jh).q(QJ.NX);
   private static final Je Id = new Qg("LD1W", jh).q(QJ.NX);
   private static final Je[] Dk = new Je[]{
      Wn,
      Wn,
      Wn,
      Wn,
      new Qg("LD1SW", kf).q(QJ.NX),
      eG,
      eG,
      eG,
      new Qg("LD1SH", pQ).q(QJ.NX),
      new Qg("LD1SH", jb).q(QJ.NX),
      Id,
      Id,
      new Qg("LD1SB", bl).q(QJ.NX),
      new Qg("LD1SB", fi).q(QJ.NX),
      new Qg("LD1SB", fQ).q(QJ.NX),
      new Qg("LD1D", jh).q(QJ.NX)
   };
   private static final Je dS = new Qg("LDFF1B", Jf).q(QJ.NX);
   private static final Je cb = new Qg("LDFF1H", Jf).q(QJ.NX);
   private static final Je BU = new Qg("LDFF1W", Jf).q(QJ.NX);
   private static final Je[] xG = new Je[]{
      dS,
      dS,
      dS,
      dS,
      new Qg("LDFF1SW", YA).q(QJ.NX),
      cb,
      cb,
      cb,
      new Qg("LDFF1SH", qR).q(QJ.NX),
      new Qg("LDFF1SH", IY).q(QJ.NX),
      BU,
      BU,
      new Qg("LDFF1SB", Yw).q(QJ.NX),
      new Qg("LDFF1SB", TQ).q(QJ.NX),
      new Qg("LDFF1SB", GM).q(QJ.NX),
      new Qg("LDFF1D", Jf).q(QJ.NX)
   };
   private static final Je wS = new iQ(new String[]{null, null, "LD1W", "LD1D"}, JY, Qu).q(QJ.tW);
   private static final Je Oz = new iQ(new String[]{null, "LD2Q", "LD3Q", "LD4Q"}, JY, RL).q(QJ.tW);
   private static final Je yn = new Qg("LD1B", ZU).q(QJ.NX);
   private static final Je es = new Qg("LD1H", ZU).q(QJ.NX);
   private static final Je o = new Qg("LD1W", ZU).q(QJ.NX);
   private static final Je[] gl = new Je[]{
      yn,
      yn,
      yn,
      yn,
      new Qg("LD1SW", iu).q(QJ.NX),
      es,
      es,
      es,
      new Qg("LD1SH", iu).q(QJ.NX),
      new Qg("LD1SH", os).q(QJ.NX),
      o,
      o,
      new Qg("LD1SB", iu).q(QJ.NX),
      new Qg("LD1SB", os).q(QJ.NX),
      new Qg("LD1SB", of).q(QJ.NX),
      new Qg("LD1D", ZU).q(QJ.NX)
   };
   private static final Je tX = new Qg("LDNF1B", ZU).q(QJ.NX);
   private static final Je Qt = new Qg("LDNF1H", ZU).q(QJ.NX);
   private static final Je JW = new Qg("LDNF1W", ZU).q(QJ.NX);
   private static final Je[] Ub = new Je[]{
      tX,
      tX,
      tX,
      tX,
      new Qg("LDNF1SW", iu).q(QJ.NX),
      Qt,
      Qt,
      Qt,
      new Qg("LDNF1SH", iu).q(QJ.NX),
      new Qg("LDNF1SH", os).q(QJ.NX),
      JW,
      JW,
      new Qg("LDNF1SB", iu).q(QJ.NX),
      new Qg("LDNF1SB", os).q(QJ.NX),
      new Qg("LDNF1SB", of).q(QJ.NX),
      new Qg("LDNF1D", iu).q(QJ.NX)
   };
   private static final Je tb = new iQ(new String[]{"LDNT1B", "LDNT1H", "LDNT1W", "LDNT1D"}, JY, vC).q(QJ.NX);
   private static final Je yW = new iQ(
         new String[]{null, "LD2B", "LD3B", "LD4B", null, "LD2H", "LD3H", "LD4H", null, "LD2W", "LD3W", "LD4W", null, "LD2D", "LD3D", "LD4D"}, za, wQ
      )
      .q(QJ.NX);
   private static final Je JF = new iQ(new String[]{"LDNT1B", "LDNT1H", "LDNT1W", "LDNT1D"}, JY, Sz).q(QJ.NX);
   private static final Je uz = new iQ(new String[]{null, "LD2Q", "LD3Q", "LD4Q"}, JY, Xi).q(QJ.tW);
   private static final Je Xz = new iQ(
         new String[]{null, "LD2B", "LD3B", "LD4B", null, "LD2H", "LD3H", "LD4H", null, "LD2W", "LD3W", "LD4W", null, "LD2D", "LD3D", "LD4D"}, za, VW
      )
      .q(QJ.NX);
   private static final Je iK = new iQ(
         new String[]{
            "LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"
         },
         LK,
         cR
      )
      .q(QJ.NX);
   private static final Je ZE = new iQ(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, Dw, q, Bq.Sz, EB).q(QJ.NX);
   private static final Je Jh = new iQ(
         new String[]{null, null, null, null, "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"}, LK, eC
      )
      .q(QJ.NX);
   private static final Je iO = new iQ(new String[]{"LDNT1SB", "LDNT1B", "LDNT1SH", "LDNT1H", "LDNT1SW", "LDNT1W", null, "LDNT1D"}, io, jk).q(QJ.br);
   private static final Je Qe = new Qg("LD1Q", Cl).q(QJ.tW);
   private static final Je dW = new iQ(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, JY, q, Bq.Sz, GY).q(QJ.NX);
   private static final Je HK = new iQ(
         new String[]{
            "LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"
         },
         LK,
         Bs
      )
      .q(QJ.NX);
   private static final Je uw = new iQ(
         new String[]{
            "LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"
         },
         LK,
         PY
      )
      .q(QJ.NX);
   private static final Je fe = new iQ(
         new String[]{null, null, null, null, "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"}, LK, ND
      )
      .q(QJ.NX);
   private static final Je Kl = new iQ(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, Dw, q, Bq.Sz, Bu).q(QJ.NX);
   private static final Je So = new Qg("STR", rp).q(QJ.NX);
   private static final Je AG = new Qg("ST1B", Eq).q(QJ.NX);
   private static final Je er = new Qg("ST1H", Eq).q(qa);
   private static final Je SM = new Qg("ST1W", Eq).q(QJ.NX);
   private static final Je[] bj = new Je[]{
      AG, AG, AG, AG, null, er, er, er, new Qg("ST1W", Rd).q(QJ.tW), null, SM, SM, null, null, new Qg("ST1D", Rd).q(QJ.tW), new Qg("ST1D", Eq).q(QJ.NX)
   };
   private static final Je GO = new Qg("STR", CW).q(QJ.NX);
   private static final Je QZ = new iQ(new String[]{null, "ST2Q", "ST3Q", "ST4Q"}, zz, Ag).q(QJ.tW);
   private static final Je Up = new iQ(new String[]{null, "ST2Q", "ST3Q", "ST4Q"}, zz, hy).q(QJ.tW);
   private static final Je HO = new iQ(new String[]{"STNT1B", "STNT1H", "STNT1W", "STNT1D"}, JY, uY).q(QJ.br);
   private static final Je cv = new iQ(new String[]{"STNT1B", "STNT1H", "STNT1W", null}, JY, gm).q(QJ.br);
   private static final Je lk = new Qg("ST1Q", sc).q(QJ.tW);
   private static final Je sa = new iQ(new String[]{"STNT1B", "STNT1H", "STNT1W", "STNT1D"}, JY, hP).q(QJ.NX);
   private static final Je WJ = new iQ(
         new String[]{null, "ST2B", "ST3B", "ST4B", null, "ST2H", "ST3H", "ST4H", null, "ST2W", "ST3W", "ST4W", null, "ST2D", "ST3D", "ST4D"}, za, Oj
      )
      .q(QJ.NX);
   private static final Je[] pL = new Je[]{
      new iQ(new String[]{"ST1B", "ST1H", "ST1W", "ST1D"}, JY, Al).q(QJ.NX),
      new iQ(new String[]{null, "ST1H", "ST1W", "ST1D"}, JY, vh).q(QJ.NX),
      new iQ(new String[]{"ST1B", "ST1H", "ST1W", null}, JY, FG).q(QJ.NX),
      new iQ(new String[]{null, "ST1H", "ST1W", null}, JY, Kn).q(QJ.NX)
   };
   private static final Je[] aH = new Je[]{
      new iQ(new String[]{"ST1B", "ST1H", "ST1W", "ST1D"}, JY, kv).q(QJ.NX),
      new iQ(new String[]{null, "ST1H", "ST1W", "ST1D"}, JY, oS).q(QJ.NX),
      new iQ(new String[]{"ST1B", "ST1H", "ST1W", "ST1D"}, JY, hw).q(QJ.NX),
      new iQ(new String[]{"ST1B", "ST1H", "ST1W", null}, JY, dg).q(QJ.NX)
   };
   private static final Je yc = new Qg("ST1B", wN).q(QJ.NX);
   private static final Je eb = new Qg("ST1H", wN).q(qa);
   private static final Je zj = new Qg("ST1W", wN).q(QJ.NX);
   private static final Je[] aV = new Je[]{
      yc, yc, yc, yc, null, eb, eb, eb, new Qg("ST1W", Uc).q(QJ.tW), null, zj, zj, null, null, new Qg("ST1D", Uc).q(QJ.tW), new Qg("ST1D", wN).q(QJ.NX)
   };
   private static final Je Qo = new iQ(new String[]{"STNT1B", "STNT1H", "STNT1W", "STNT1D"}, JY, TB).q(QJ.NX);
   private static final Je lN = new iQ(
         new String[]{null, "ST2B", "ST3B", "ST4B", null, "ST2H", "ST3H", "ST4H", null, "ST2W", "ST3W", "ST4W", null, "ST2D", "ST3D", "ST4D"}, za, ap
      )
      .q(QJ.NX);

   static final Ef q(Ef var0, Ef var1) {
      return (var2, var3) -> {
         CW var4 = var1.buildOperand(var2, var3);
         ZD var5 = null;
         if (var4.getOperandValue() != 0L) {
            var5 = ZD.q(var4, DH.eo.LK);
         }

         return new wh(var0.buildOperand(var2, var3), var5, false, true, var3);
      };
   }

   static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 96) >>> 5;
      int var3 = (var0[2] & 224) >>> 5;
      int var4 = (var0[3] & 16) >>> 4;
      if (var3 < 4) {
         if (var1 == 3) {
            if ((var2 & 2) == 0) {
               if (var3 == 0 && var4 == 0) {
                  return qm;
               }

               if (var3 == 2) {
                  return LR;
               }
            } else if (var4 == 0) {
               return Uz;
            }
         } else {
            if ((var2 & 1) == 0) {
               return dF;
            }

            if (var1 == 0 && var4 == 0) {
               return kk;
            }

            if (var1 == 1) {
               return Rc;
            }

            if (var1 == 2) {
               return jz;
            }
         }
      } else {
         if (var2 == 1) {
            return MT;
         }

         if ((var2 & 2) != 0) {
            return cO[var1 << 2 | (var0[2] & 96) >>> 5];
         }

         if (var3 < 6) {
            return wr;
         }

         if (var3 == 6 && var4 == 0) {
            if ((var0[1] & 31) == 31) {
               return null;
            }

            return pe;
         }

         if (var3 == 7 && var4 == 0) {
            return Gg;
         }
      }

      return null;
   }

   static Je RF(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 96) >>> 5;
      int var2 = (var0[1] & 16) >>> 4;
      int var3 = (var0[2] & 224) >>> 5;
      switch (var3) {
         case 0:
            if ((var0[1] & 31) == 31) {
               return null;
            }

            return Qg.q(CK, JY.decodeInt(var0), var1, var0, "SVE load and broadcast quadword (scalar plus scalar)");
         case 1:
            if (var2 == 0) {
               return Qg.q(PW, JY.decodeInt(var0), var1, var0, "SVE load and broadcast quadword (scalar plus immediate)");
            } else {
               if (var1 == 0) {
                  return zm;
               }

               return null;
            }
         case 2:
            if ((var0[1] & 31) == 31) {
               return null;
            }

            return Dk[za.decodeInt(var0)];
         case 3:
            return xG[za.decodeInt(var0)];
         case 4:
            if (var1 == 0) {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return wS;
            } else {
               if (var1 == 1) {
                  if ((var0[1] & 31) == 31) {
                     return null;
                  }

                  return Oz;
               }

               return null;
            }
         case 5:
            if (var2 == 0) {
               return gl[za.decodeInt(var0)];
            }

            return Ub[za.decodeInt(var0)];
         case 6:
            if (var1 == 0) {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return tb;
            } else {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return yW;
            }
         case 7:
            if (var1 == 0) {
               if (var2 == 0) {
                  return JF;
               }

               return uz;
            } else {
               if (var2 == 0) {
                  return Xz;
               }

               return null;
            }
         default:
            return null;
      }
   }

   static Je xK(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 96) >>> 5;
      int var3 = (var0[2] & 224) >>> 5;
      int var4 = (var0[3] & 16) >>> 4;
      if (var3 < 4) {
         if ((var2 & 1) == 0) {
            return iK;
         } else {
            return var1 == 0 ? ZE : Jh;
         }
      } else {
         if (var2 == 0) {
            if (var3 == 4 || var3 == 6) {
               return iO;
            }

            if (var3 == 5 && var2 == 0) {
               return Qe;
            }

            if (var3 == 7 && var4 == 0) {
               return dW;
            }
         } else {
            if (var2 == 1) {
               return HK;
            }

            if (var2 == 2) {
               return uw;
            }

            if (var1 != 0) {
               return fe;
            }

            if (var4 == 0) {
               return Kl;
            }
         }

         return null;
      }
   }

   static Je Dw(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 3) {
         case 0:
            int var7 = (var0[0] & 1) << 2 | (var0[1] & 192) >>> 6;
            int var3 = (var0[3] & 16) >>> 4;
            if (var7 == 6 && var3 == 0) {
               return GO;
            } else {
               if (var7 < 4) {
                  int var4 = (var0[1] & 48) >>> 4;
                  if (var4 == 0) {
                     return QZ;
                  }

                  if (var4 >= 2) {
                     if ((var0[1] & 31) == 31) {
                        return null;
                     }

                     return Up;
                  }
               }

               return null;
            }
         case 1:
            int var6 = (var0[0] & 1) << 2 | (var0[1] & 192) >>> 6;
            if ((var0[1] & 32) == 0) {
               if ((var6 & 1) == 0) {
                  return HO;
               }

               return cv;
            } else {
               if (var6 == 0) {
                  return lk;
               }

               return null;
            }
         case 2:
            int var5 = (var0[0] & 1) << 2 | (var0[1] & 192) >>> 6;
            if (var5 == 6) {
               return So;
            } else {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return bj[za.decodeInt(var0)];
            }
         case 3:
            if ((var0[1] & 31) == 31) {
               return null;
            } else {
               if ((var0[1] & 96) == 0) {
                  return sa;
               }

               return WJ;
            }
         case 4:
         case 6:
            return pL[(var0[1] & 96) >>> 5];
         case 5:
            return aH[(var0[1] & 96) >>> 5];
         case 7:
            int var2 = (var0[1] & 16) >>> 4;
            if (var2 == 0) {
               return aV[za.decodeInt(var0)];
            } else {
               if ((var0[1] & 96) == 0) {
                  return Qo;
               }

               return lN;
            }
         default:
            return null;
      }
   }
}
