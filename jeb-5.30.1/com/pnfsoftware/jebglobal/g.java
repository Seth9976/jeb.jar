package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class g {
   private static final IEncodedMemoryArea nf = DirectEncodedMemoryArea.get(0, 1);
   private static final IEncodedMemoryArea gP = DirectEncodedMemoryArea.get(3, 1);
   private static final IEncodedMemoryArea za = DirectEncodedMemoryArea.get(4, 1);
   private static final IEncodedMemoryArea lm = DirectEncodedMemoryArea.get(5, 1);
   private static final IEncodedMemoryArea zz = DirectEncodedMemoryArea.get(5, 3);
   private static final IEncodedMemoryArea JY = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea HF = DirectEncodedMemoryArea.get(5, 6);
   private static final IEncodedMemoryArea LK = DirectEncodedMemoryArea.get(8, 1);
   private static final IEncodedMemoryArea io = DirectEncodedMemoryArea.get(8, 2);
   private static final IEncodedMemoryArea qa = DirectEncodedMemoryArea.get(10, 1);
   private static final IEncodedMemoryArea Hk = DirectEncodedMemoryArea.get(10, 2);
   private static final IEncodedMemoryArea Me = DirectEncodedMemoryArea.get(10, 3);
   private static final IEncodedMemoryArea PV = DirectEncodedMemoryArea.get(10, 4);
   private static final IEncodedMemoryArea oQ = new EncodedMemoryAreaList(Hk, nf);
   private static final IEncodedMemoryArea xW = new EncodedMemoryAreaList(Hk, gP);
   private static final IEncodedMemoryArea KT = new EncodedMemoryAreaList(Hk, za);
   private static final IEncodedMemoryArea Gf = DirectEncodedMemoryArea.get(11, 2);
   private static final IEncodedMemoryArea Ef = DirectEncodedMemoryArea.get(11, 6);
   private static final IEncodedMemoryArea cC = DirectEncodedMemoryArea.get(12, 1);
   private static final IEncodedMemoryArea sH = DirectEncodedMemoryArea.get(12, 2);
   static final IEncodedMemoryArea q = DirectEncodedMemoryArea.get(13, 1);
   static final IEncodedMemoryArea RF = DirectEncodedMemoryArea.get(13, 2);
   private static final IEncodedMemoryArea CE = new EncodedMemoryAreaList(q, za);
   private static final IEncodedMemoryArea wF = DirectEncodedMemoryArea.get(15, 1);
   private static final IEncodedMemoryArea If = new EncodedMemoryAreaList(wF, q, za);
   private static final IEncodedMemoryArea Dz = DirectEncodedMemoryArea.get(16, 1);
   private static final IEncodedMemoryArea mI = new EncodedMemoryAreaList(Dz, Hk);
   private static final IEncodedMemoryArea jq = DirectEncodedMemoryArea.get(16, 2);
   private static final IEncodedMemoryArea ui = new EncodedMemoryAreaList(jq, za);
   private static final IEncodedMemoryArea TX = DirectEncodedMemoryArea.get(16, 3);
   static final IEncodedMemoryArea xK = DirectEncodedMemoryArea.get(16, 4);
   static final IEncodedMemoryArea Dw = DirectEncodedMemoryArea.get(16, 5);
   private static final IEncodedMemoryArea Rr = DirectEncodedMemoryArea.get(17, 1);
   private static final IEncodedMemoryArea EB = DirectEncodedMemoryArea.get(17, 2);
   private static final IEncodedMemoryArea Xo = DirectEncodedMemoryArea.get(19, 2);
   private static final IEncodedMemoryArea Bu = new EncodedMemoryAreaList(Xo, Hk);
   private static final IEncodedMemoryArea IN = new EncodedMemoryAreaList(Xo, DirectEncodedMemoryArea.get(11, 1));
   private static final IEncodedMemoryArea rL = DirectEncodedMemoryArea.get(20, 1);
   private static final IEncodedMemoryArea eJ = new EncodedMemoryAreaList(rL, DirectEncodedMemoryArea.get(11, 1));
   static final IEncodedMemoryArea Uv = DirectEncodedMemoryArea.get(22, 1);
   static final IEncodedMemoryArea oW = DirectEncodedMemoryArea.get(22, 2);
   private static final IEncodedMemoryArea YN = new EncodedMemoryAreaList(Uv, Xo);
   private static final IEncodedMemoryArea Rv = new EncodedMemoryAreaList(oW, cC);
   private static final IEncodedMemoryArea zx = new EncodedMemoryAreaList(oW, Dw);
   private static final IEncodedMemoryArea ZT = new EncodedMemoryAreaList(oW, DirectEncodedMemoryArea.get(18, 3));
   private static final IEncodedMemoryArea Ri = new EncodedMemoryAreaList(Uv, EB);
   private static final IEncodedMemoryArea GY = DirectEncodedMemoryArea.get(23, 1);
   private static final String[] Wx = new String[]{"#0", "#90", "#180", "#270"};
   private static final dD AB = new dD(XD.Uv, 262144L, 1L, 1L, 1L);
   private static final dD CY = new dD(XD.Uv, 262144L, 262144L, 1L, 1L);
   private static final dD WI = new dD(XD.Uv, 131072L, 131072L, 131072L, 1L);
   static final dD gO = new dD(XD.Uv, 1L, 131072L, 131072L, 131072L);
   private static final dD Tq = new dD(XD.Uv, 1L, 262144L, 262144L, 262144L);
   private static final dD Yp = new dD(EB, 1L, 262144L, 262144L, 262144L);
   private static final dD Gu = Tq;
   private static final dD nY = new dD(XD.Uv, 1L, 1L, 131072L, 1L);
   private static final dD lF = new dD(XD.Uv, 1L, 1L, 262144L, 1L);
   private static final dD nq = new dD(XD.Uv, 1L, 1L, 131072L, 131072L);
   private static final dD NX = new dD(XD.Uv, 1L, 1L, 262144L, 262144L);
   private static final dD br = new dD(XD.Uv, 1L, 1L, 1L, 131072L);
   private static final dD tW = new dD(
      new EncodedMemoryAreaList(XD.Uv, DirectEncodedMemoryArea.get(13, 1)), 131072L, 1L, 131072L, 131072L, 131072L, 131072L, 131072L, 131072L
   );
   private static final dD ZA = new dD(DirectEncodedMemoryArea.get(4, 1), 131072L, 1L);
   private static final dD Ov = new dD(YN, 1L, 262144L, 262144L, 1L, 262144L, 1L, 1L, 1L);
   private static final Ef[] Lj = new Ef[]{XD.HF, Bq.Sz, Bq.Ri};
   private static final Ef[] nv = new Ef[]{XD.oQ, Bq.Sz, Bq.Ri};
   private static final Ef[] LL = new Ef[]{XD.oQ, Bq.Sz, XD.oQ, Bq.Ri};
   private static final Ef[] PQ = new Ef[]{XD.oQ, Bq.Sz, Bq.Ri};
   private static final Ef[] fQ = new Ef[]{XD.oQ, Bq.Sz, XD.oQ, Bq.Ri};
   private static final Ef[] fi = new Ef[]{com.pnfsoftware.jebglobal.yu.HF, Bq.Sz, Bq.Ri};
   private static final Ef[] bl = new Ef[]{Bq.qR, Bq.Qu};
   private static final Ef[] jb = new Ef[]{Bq.qR, Bq.Qu, Bq.os};
   private static final Ef[] pQ = new Ef[]{Bq.qR, Bq.ND, Bq.qR};
   private static final Ef[] kf = new Ef[]{Bq.YA, Bq.jh};
   private static final Ef[] GM = new Ef[]{Bq.fw, Bq.jh};
   private static final Ef[] TQ = new Ef[]{Bq.YA, Bq.ZU};
   private static final Ef[] Yw = new Ef[]{Bq.YA, Bq.rV, Bq.jh, Bq.iu};
   private static final Ef[] IY = new Ef[]{Bq.qR, Bq.mJ, Bq.Ri, Bq.ZA};
   private static final Ef[] qR = new Ef[]{Bq.qR, Bq.mJ, Bq.Ri, Nd.q(Nd.Uv)};
   private static final Ef[] YA = new Ef[]{Bq.qR, Bq.mJ, Bq.Ri, Bq.fi};
   private static final Ef[] fw = new Ef[]{Bq.YA, Bq.CB, Bq.jh, Bq.iu};
   private static final Ef[] Wp = new Ef[]{Bq.YA, Bq.CB, Bq.jh};
   private static final Ef[] cY = new Ef[]{Bq.YA, Bq.WX, Bq.jh};
   private static final Ef[] PY = new Ef[]{Bq.YA, Bq.CB, Bq.jh, Bq.YA};
   private static final Ef[] cR = new Ef[]{Bq.YA, Bq.C, Bq.jh};
   private static final Ef[] eC = new Ef[]{Bq.YA, Bq.rL};
   private static final Ef[] ND = new Ef[]{Bq.xW, Bq.jh};
   private static final Ef[] Qu = new Ef[]{Bq.CE, Bq.Qu};
   private static final Ef[] jh = new Ef[]{Bq.CE, Bq.Sz, Bq.Ri};
   private static final Ef[] Jf = new Ef[]{Bq.CE, Bq.Sz, Bq.CE, Bq.Ri};
   private static final Ef[] vC = new Ef[]{Bq.CE, Bq.rV, Bq.Ri, Bq.ZA};
   private static final Ef[] of = new Ef[]{Bq.CE, Bq.fq, Bq.CE, Bq.Ri};
   private static final Ef[] os = new Ef[]{Bq.CE, Bq.fq, Bq.CE, Bq.zx};
   private static final Ef[] iu = new Ef[]{Bq.CE, Bq.fq, Bq.Ri};
   private static final Ef[] fn = new Ef[]{Bq.CE, Bq.fq, Bq.Ri, Bq.ZA};
   private static final Ef[] ZU = new Ef[]{Bq.CE, Bq.fq, Bq.ZA, Bq.Ri};
   private static final Ef[] Sz = new Ef[]{Bq.CE, Bq.WX, Bq.Ri};
   private static final Ef[] fq = new Ef[]{Bq.Gf, Bq.WX, Bq.Gf, Bq.YN};
   private static final Ef[] mJ = new Ef[]{Bq.Gf, Bq.fq, Bq.YN, Bq.PQ};
   private static final Ef[] Bs = new Ef[]{Bq.Gf, Bq.fq, Bq.YN};
   private static final Ef[] rV = new Ef[]{Bq.Gf, Bq.fq, Bq.Rv};
   private static final Ef[] WX = new Ef[]{Bq.Gf, Bq.fq, Bq.zx};
   private static final Ef[] CB = new Ef[]{Bq.Ef, Bq.fq, Bq.YN};
   private static final Ef[] C = new Ef[]{Bq.Ef, Bq.fq, Bq.Rv};
   private static final Ef[] GC = new Ef[]{Bq.Ef, Bq.fq, Bq.zx};
   private static final Ef[] KF = new Ef[]{Bq.cC, Bq.fq, Bq.YN};
   private static final Ef[] rk = new Ef[]{Bq.cC, Bq.fq, Bq.Rv};
   private static final Ef[] cy = new Ef[]{Bq.cC, Bq.fq, Bq.zx};
   private static final Ef[] jk = new Ef[]{Bq.sH, Bq.fq, Bq.ZT};
   private static final Ef[] Cl = new Ef[]{Bq.CE, Bq.mJ, Bq.Ri, Bq.ZA};
   private static final Ef[] hM = new Ef[]{Bq.If, Bq.fq, Bq.GY};
   private static final Ef[] kv = new Ef[]{Bq.xW, Bq.rL};
   private static final Ef[] oS = new Ef[]{Bq.CE, Bq.Ri};
   private static final Ef[] FG = new Ef[]{Bq.CE, Bq.Ri, Bq.ZA};
   private static final Ef[] Al = new Ef[]{Bq.CE, Bq.Ri, Bq.fi};
   private static final Ef[] Kn = new Ef[]{Bq.Gf, Bq.YN, Bq.PQ};
   private static final Ef[] vh = new Ef[]{Bq.Gf, Bq.eJ};
   private static final Ef[] Rd = new Ef[]{Bq.Gf, Bq.eJ, Bq.LL};
   private static final Ef[] Eq = new Ef[]{Bq.Ef, Bq.eJ, Bq.LL};
   private static final Ef[] hP = new Ef[]{Bq.Ef, Bq.YN, Bq.PQ};
   private static final Ef[] wN = new Ef[]{Bq.Ef, Bq.Rv, Bq.fQ};
   private static final Ef[] Uc = new Ef[]{Bq.cC, Bq.zx, Bq.fi};
   private static final Ef[] TB = new Ef[]{Bq.sH, Bq.ZT, Bq.bl};
   private static final Ef[] dg = new Ef[]{Bq.cC, Bq.zx};
   private static final Ef[] hw = new Ef[]{Bq.CE, Bq.CE};
   private static final Ef[] gm = new Ef[]{Bq.CE, Bq.CE, Bq.Ri};
   private static final Ef[] uY = new Ef[]{Bq.CE, Bq.CE, Bq.Ri, go.lm};
   private static final Ef[] sc = new Ef[]{Bq.Ef, Bq.Ef, Bq.Rv};
   private static final Ef[] wQ = new Ef[]{Bq.cC, Bq.cC, Bq.fi, Bq.zx};
   private static final Ef[] Oj = new Ef[]{Bq.CE, Bq.ZA, Bq.Ri};
   private static final Ef VW = new Mf(var0 -> q(var0, Bq.Uv, zz, DH.eo.xK));
   private static final Ef ap = new Mf(var0 -> q(var0, Bq.Uv, zz, DH.eo.q));
   private static final Ef RL = new Mf(var0 -> q(var0, Bq.Dw, TX, DH.eo.xK));
   private static final Ef hy = new Mf(var0 -> q(var0, Bq.Dw, TX, DH.eo.q));
   private static final Ef[] Xi = new Ef[]{Bq.Xo, Bq.fq, Bq.Xo, VW};
   private static final Ef[] Ag = new Ef[]{Bq.Xo, Bq.fq, Bq.Xo, ap};
   private static final Ef[] rp = new Ef[]{Bq.Rr, Bq.Rr, Bq.WI, RL};
   private static final Ef[] CW = new Ef[]{Bq.Rr, Bq.WI, RL};
   private static final Ef[] qm = new Ef[]{Bq.Rr, Bq.WI, hy};
   private static final Ef LR = new VP(Bq.Yp, dX.oW(Dw));
   private static final Ef Uz = new VP(Bq.jb, Xo);
   private static final Ef dF = new VP(Bq.jb, Bu);
   private static final Ef kk = new VP(Bq.jb, IN);
   private static final Ef Rc = new VP(Bq.pQ, YN);
   private static final Ef jz = new VP(Bq.pQ, Xo);
   private static final Ef MT = new VP(Bq.pQ, IN);
   private static final Ef bY = new VP(Bq.GM, rL);
   private static final Ef LS = new VP(Bq.kf, Xo);
   private static final Ef fG = new VP(Bq.TQ, eJ);
   private static final Ef cO = new VP(Bq.TQ, rL);
   private static final Ef wr = new VP(Bq.Yw, rL);
   private static final Ef pe = new VP(Bq.tW, GY);
   private static final Ef Gg = new VP(Bq.tW, oW);
   private static final Ef CK = new VP(Bq.tW, Rv);
   private static final Ef PW = new zg(Bq.fn, YH.Wx, go.q(dX.oW(ZT)));
   private static final Ef[] zm = new Ef[]{Bq.Ef, Bq.eJ, Uz};
   private static final Ef[] Wn = new Ef[]{Bq.Ef, Bq.eJ, dF};
   private static final Ef[] eG = new Ef[]{Bq.cC, Bq.YN, bY};
   private static final Ef[] Id = new Ef[]{Bq.Gf, Bq.eJ, dF};
   private static final Ef[] Dk = new Ef[]{Bq.Gf, Bq.eJ, kk};
   private static final Ef[] dS = new Ef[]{Bq.Gf, Bq.YN, Rc};
   private static final Ef[] cb = new Ef[]{Bq.Ef, Bq.YN, jz};
   private static final Ef[] BU = new Ef[]{Bq.Ef, Bq.Rv, LS};
   private static final Ef[] xG = new Ef[]{Bq.cC, Bq.zx, wr};
   private static final Ef[] wS = new Ef[]{Bq.Ef, Bq.YN, MT};
   private static final Ef[] Oz = new Ef[]{Bq.cC, Bq.Rv, fG};
   private static final Ef yn = new VP(Bq.xW, Rr);
   private static final Ef es = new VP(Bq.xW, EB);
   private static final Ef o = new VP(Bq.xW, Ri);
   private static final Ef gl = new VP(Bq.rL, Rr);
   private static final Ef tX = new VP(Bq.rL, EB);
   private static final Ef Qt = new VP(Bq.rL, Ri);
   private static final Ef[] JW = new Ef[]{Bq.fw, gl};
   private static final Ef[] Ub = new Ef[]{Bq.Wp, tX};
   private static final Ef[] tb = new Ef[]{Bq.cY, Qt};
   private static final Ef[] yW = new Ef[]{yn, Bq.Jf};
   private static final Ef[] JF = new Ef[]{es, Bq.vC};
   private static final Ef[] uz = new Ef[]{o, Bq.of};
   private static final Ef Xz = new xT(64, Bq.Ri);
   private static final Ef iK = new xT(64, Bq.Ri, Bq.nY);
   private static final Ef ZE = new xT(64, Bq.eJ);
   private static final Ef Jh = new xT(64, Bq.eJ, Bq.lF);
   private static final Ef iO = new xT(64, Bq.YN);
   private static final Ef Qe = new xT(64, Bq.YN, Bq.nq);
   private static final Ef dW = new xT(64, Bq.NX, Bq.br);
   private static final Je[] HK = new Je[]{new Qg("MLA", fn).q(QJ.NX), new Qg("MLS", fn).q(QJ.NX), new Qg("MAD", ZU).q(QJ.NX), new Qg("MSB", ZU).q(QJ.NX)};
   private static final Je[] uw = new Je[]{
      new Qg("ADD", of).q(QJ.NX),
      new Qg("SUB", of).q(QJ.NX),
      null,
      new Qg("SUBR", of).q(QJ.NX),
      new Qg("ADDPT", of).q(QJ.NX, br, QJ.rV),
      new Qg("SUBPT", of).q(QJ.NX, br, QJ.rV),
      null,
      null,
      new Qg("SMAX", of).q(QJ.NX),
      new Qg("UMAX", of).q(QJ.NX),
      new Qg("SMIN", of).q(QJ.NX),
      new Qg("UMIN", of).q(QJ.NX),
      new Qg("SABD", of).q(QJ.NX),
      new Qg("UABD", of).q(QJ.NX),
      null,
      null
   };
   private static final Je[] fe = new Je[]{
      new Qg("MUL", of).q(QJ.NX),
      null,
      new Qg("SMULH", of).q(QJ.NX),
      new Qg("UMULH", of).q(QJ.NX),
      new Qg("SDIV", of).q(nq),
      new Qg("UDIV", of).q(nq),
      new Qg("SDIVR", of).q(nq),
      new Qg("UDIVR", of).q(nq),
      new Qg("ORR", of).q(QJ.NX),
      new Qg("EOR", of).q(QJ.NX),
      new Qg("AND", of).q(QJ.NX),
      new Qg("BIC", of).q(QJ.NX)
   };
   private static final Je[][] Kl = new Je[][]{
      {new Qg("SADDV", Lj).q(WI), new Qg("UADDV", Lj).q(QJ.NX)},
      {null, new Qg("ADDQV", fi).q(QJ.tW)},
      {new Qg("SMAXV", nv).q(QJ.NX), new Qg("UMAXV", nv).q(QJ.NX), new Qg("SMINV", nv).q(QJ.NX), new Qg("UMINV", nv).q(QJ.NX)},
      {new Qg("SMAXQV", fi).q(QJ.tW), new Qg("UMAXQV", fi).q(QJ.tW), new Qg("SMINQV", fi).q(QJ.tW), new Qg("UMINQV", fi).q(QJ.tW)},
      null,
      null,
      {new Qg("ORV", nv).q(QJ.NX), new Qg("EORV", nv).q(QJ.NX), new Qg("ANDV", nv).q(QJ.NX)},
      {new Qg("ORQV", fi).q(QJ.tW), new Qg("EORQV", fi).q(QJ.tW), new Qg("ANDQV", fi).q(QJ.tW)}
   };
   private static final Je So = new Qg("MOVPRFX", Bq.CE, Bq.Bs, Bq.Ri).q(QJ.NX);
   private static final Je[][] AG = new Je[][]{
      {
            new Qg("ASR", Xi).q(QJ.NX),
            new Qg("LSR", Xi).q(QJ.NX),
            null,
            new Qg("LSL", Ag).q(QJ.NX),
            new Qg("ASRD", Xi).q(QJ.NX),
            null,
            new Qg("SQSHL", Ag).q(QJ.br),
            new Qg("UQSHL", Ag).q(QJ.br)
      },
      {null, null, null, null, new Qg("SRSHR", Xi).q(QJ.br), new Qg("URSHR", Xi).q(QJ.br), null, new Qg("SQSHLU", Ag).q(QJ.NX)},
      {
            new Qg("ASR", of).q(QJ.NX),
            new Qg("LSR", of).q(QJ.NX),
            null,
            new Qg("LSL", of).q(QJ.NX),
            new Qg("ASRR", of).q(QJ.NX),
            new Qg("LSRR", of).q(QJ.NX),
            null,
            new Qg("LSLR", of).q(QJ.NX)
      },
      {new Qg("ASR", os).q(WI), new Qg("LSR", os).q(WI), null, new Qg("LSL", os).q(WI)}
   };
   private static final Je[][] er = new Je[][]{
      null,
      null,
      {
            new Qg("SXTB", iu).q(gO),
            new Qg("UXTB", iu).q(gO),
            new Qg("SXTH", iu).q(nq),
            new Qg("UXTH", iu).q(nq),
            new Qg("SXTW", iu).q(br),
            new Qg("UXTW", iu).q(br),
            new Qg("ABS", iu).q(QJ.NX),
            new Qg("NEG", iu).q(QJ.NX)
      },
      {
            new Qg("CLS", iu).q(QJ.NX),
            new Qg("CLZ", iu).q(QJ.NX),
            new Qg("CNT", iu).q(QJ.NX),
            new Qg("CNOT", iu).q(QJ.NX),
            new Qg("FABS", iu).q(gO),
            new Qg("FNEG", iu).q(gO),
            new Qg("NOT", iu).q(QJ.NX)
      }
   };
   private static final Je[] SM = new Je[]{
      new Qg("ADD", FG).q(QJ.NX),
      new Qg("SUB", FG).q(QJ.NX),
      new Qg("ADDPT", FG).q(QJ.NX, br, QJ.rV),
      new Qg("SUBPT", FG).q(QJ.NX, br, QJ.rV),
      new Qg("SQADD", FG).q(QJ.NX),
      new Qg("UQADD", FG).q(QJ.NX),
      new Qg("SQSUB", FG).q(QJ.NX),
      new Qg("UQSUB", FG).q(QJ.NX)
   };
   private static final Je[] bj = new Je[]{
      new Qg("AND", Uc).q(QJ.NX),
      new Qg("ORR", Uc).q(QJ.NX).q(new Qg("MOV", dg).q(QJ.NX), ag.q(Bq.rL, Bq.fi)),
      new Qg("EOR", Uc).q(QJ.NX),
      new Qg("BIC", Uc).q(QJ.NX)
   };
   private static final Je GO = new Qg("XAR", rp).q(QJ.br);
   private static final Je[] QZ = new Je[]{
      new Qg("EOR3", wQ).q(QJ.br),
      new Qg("BSL", wQ).q(QJ.br),
      new Qg("BCAX", wQ).q(QJ.br),
      new Qg("BSL1N", wQ).q(QJ.br),
      null,
      new Qg("BSL2N", wQ).q(QJ.br),
      null,
      new Qg("NBSL", wQ).q(QJ.br)
   };
   private static final Ef[] Up = new Ef[]{Bq.CE, go.cC, go.sH};
   private static final Ef[] HO = new Ef[]{Bq.CE, YH.Xo, go.sH};
   private static final Ef[] cv = new Ef[]{Bq.CE, go.cC, YH.eJ};
   private static final Ef[] lk = new Ef[]{Bq.CE, YH.Xo, YH.eJ};
   private static final Je[] sa = new Je[]{
      new Qg("INDEX", Up).q(QJ.NX), new Qg("INDEX", HO).q(QJ.NX), new Qg("INDEX", cv).q(QJ.NX), new Qg("INDEX", lk).q(QJ.NX)
   };
   private static final Je[] WJ = new Je[]{new Qg("ADDVL", YH.io, YH.Hk, go.wF).q(QJ.NX), new Qg("ADDPL", YH.io, YH.Hk, go.wF).q(QJ.NX)};
   private static final Je pL = new Qg("RDVL", YH.Uv, go.wF).q(QJ.NX);
   private static final Je[] aH = new Je[]{
      new Qg("MUL", FG).q(QJ.br),
      new Qg("PMUL", FG).q(AB),
      new Qg("SMULH", FG).q(QJ.br),
      new Qg("UMULH", FG).q(QJ.br),
      new Qg("SQDMULH", FG).q(QJ.br),
      new Qg("SQRDMULH", FG).q(QJ.br)
   };
   private static final Je[] yc = new Je[]{
      new Qg("ASR", Al).q(WI),
      new Qg("LSR", Al).q(WI),
      null,
      new Qg("LSL", Al).q(WI),
      new Qg("ASR", CW).q(QJ.NX),
      new Qg("LSR", CW).q(QJ.NX),
      null,
      new Qg("LSL", qm).q(QJ.NX)
   };
   private static final Je[] eb = new Je[]{
      new Qg("ADR", Bq.cC, Bf.q(Bq.zx, Bq.fi, VirtualEncodedMemoryArea.get(6, 4), Hk, Bf.Uv)).q(QJ.NX),
      new Qg("ADR", Bq.cC, Bf.q(Bq.zx, Bq.fi, VirtualEncodedMemoryArea.get(2, 4), Hk, Bf.Uv)).q(QJ.NX),
      new Qg("ADR", Bq.CE, Bf.q(Bq.Ri, Bq.ZA, VirtualEncodedMemoryArea.get(0, 4), Hk)).q(QJ.NX)
   };
   private static final Je zj = new Qg("FTSSEL", FG).q(gO);
   private static final Je aV = new Qg("FEXPA", oS).q(gO);
   private static final Je Qo = new Qg("MOVPRFX", kv).q(QJ.NX);
   private static final String[] lN = new String[]{
      "POW2",
      "VL1",
      "VL2",
      "VL3",
      "VL4",
      "VL5",
      "VL6",
      "VL7",
      "VL8",
      "VL16",
      "VL32",
      "VL64",
      "VL128",
      "VL256",
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
      "MUL4",
      "MUL3",
      "ALL"
   };
   private static final Ef gT = new wJ(jD.eo.q, lN, JY, "#%d");
   private static final Ef qr = new wJ(jD.eo.JY, lN, JY, "#%d");
   private static final Ef IJ = (var0, var1) -> {
      int var2 = var0[1] & 15;
      if (var2 == 0) {
         CW var3 = gT.buildOperand(var0, var1);
         return var3.getAlias(0L).equals("ALL") ? null : var3;
      } else {
         return ZD.q(gT.buildOperand(var0, var1), DH.eo.io, ++var2);
      }
   };
   private static final Ef[] Of = new Ef[]{Bq.CE, IJ};
   private static final Je[][] AN = new Je[][]{
      null,
      {new Qg("SQINCH", Of).q(QJ.NX), new Qg("UQINCH", Of).q(QJ.NX), new Qg("SQDECH", Of).q(QJ.NX), new Qg("UQDECH", Of).q(QJ.NX)},
      {new Qg("SQINCW", Of).q(QJ.NX), new Qg("UQINCW", Of).q(QJ.NX), new Qg("SQDECW", Of).q(QJ.NX), new Qg("UQDECW", Of).q(QJ.NX)},
      {new Qg("SQINCD", Of).q(QJ.NX), new Qg("UQINCD", Of).q(QJ.NX), new Qg("SQDECD", Of).q(QJ.NX), new Qg("UQDECD", Of).q(QJ.NX)}
   };
   private static final Ef[] RW = new Ef[]{YH.Uv, IJ};
   private static final Je[] YR = new Je[]{new Qg("CNTB", RW).q(QJ.NX), new Qg("CNTH", RW).q(QJ.NX), new Qg("CNTW", RW).q(QJ.NX), new Qg("CNTD", RW).q(QJ.NX)};
   private static final Je[][] fN = new Je[][]{
      null,
      {new Qg("INCH", Of).q(QJ.NX), new Qg("DECH", Of).q(QJ.NX)},
      {new Qg("INCW", Of).q(QJ.NX), new Qg("DECW", Of).q(QJ.NX)},
      {new Qg("INCD", Of).q(QJ.NX), new Qg("DECD", Of).q(QJ.NX)}
   };
   private static final Je[][] GH = new Je[][]{
      {new Qg("INCB", RW).q(QJ.NX), new Qg("DECB", RW).q(QJ.NX)},
      {new Qg("INCH", RW).q(QJ.NX), new Qg("DECH", RW).q(QJ.NX)},
      {new Qg("INCW", RW).q(QJ.NX), new Qg("DECW", RW).q(QJ.NX)},
      {new Qg("INCD", RW).q(QJ.NX), new Qg("DECD", RW).q(QJ.NX)}
   };
   private static final Ef[] BY = new Ef[]{YH.Uv, YH.PV, IJ};
   private static final Ef[] fK = new Ef[]{YH.PV, IJ};
   private static final Je[][] ou = new Je[][]{
      {
            new Qg("SQINCB", BY).q(QJ.NX),
            new Qg("UQINCB", fK).q(QJ.NX),
            new Qg("SQDECB", BY).q(QJ.NX),
            new Qg("UQDECB", fK).q(QJ.NX),
            new Qg("SQINCB", RW).q(QJ.NX),
            new Qg("UQINCB", RW).q(QJ.NX),
            new Qg("SQDECB", RW).q(QJ.NX),
            new Qg("UQDECB", RW).q(QJ.NX)
      },
      {
            new Qg("SQINCH", BY).q(QJ.NX),
            new Qg("UQINCH", fK).q(QJ.NX),
            new Qg("SQDECH", BY).q(QJ.NX),
            new Qg("UQDECH", fK).q(QJ.NX),
            new Qg("SQINCH", RW).q(QJ.NX),
            new Qg("UQINCH", RW).q(QJ.NX),
            new Qg("SQDECH", RW).q(QJ.NX),
            new Qg("UQDECH", RW).q(QJ.NX)
      },
      {
            new Qg("SQINCW", BY).q(QJ.NX),
            new Qg("UQINCW", fK).q(QJ.NX),
            new Qg("SQDECW", BY).q(QJ.NX),
            new Qg("UQDECW", fK).q(QJ.NX),
            new Qg("SQINCW", RW).q(QJ.NX),
            new Qg("UQINCW", RW).q(QJ.NX),
            new Qg("SQDECW", RW).q(QJ.NX),
            new Qg("UQDECW", RW).q(QJ.NX)
      },
      {
            new Qg("SQINCD", BY).q(QJ.NX),
            new Qg("UQINCD", fK).q(QJ.NX),
            new Qg("SQDECD", BY).q(QJ.NX),
            new Qg("UQDECD", fK).q(QJ.NX),
            new Qg("SQINCD", RW).q(QJ.NX),
            new Qg("UQINCD", RW).q(QJ.NX),
            new Qg("SQDECD", RW).q(QJ.NX),
            new Qg("UQDECD", RW).q(QJ.NX)
      }
   };
   private static final Ef DP = (var0, var1) -> {
      try {
         int var2 = 8 << Bq.gO(var0);
         return com.pnfsoftware.jebglobal.CW.q(64, xB.q(Rr.decodeInt(var0), HF.decodeInt(var0), Ef.decodeInt(var0), var2));
      } catch (eK var3) {
         throw new ProcessorException(var3);
      }
   };
   private static final Ef[] lA = new Ef[]{Bq.TX, Bq.TX, DP};
   private static final Je[] yu = new Je[]{
      new Qg("ORR", lA).q(QJ.NX),
      new Qg("EOR", lA).q(QJ.NX),
      new Qg("AND", lA).q(QJ.NX),
      new Qg("DUPM", Bq.TX, DP).q(QJ.NX).q(new Qg("MOV", Bq.TX, DP).q(QJ.NX), var0 -> RF(var0))
   };
   private static final Ef lz = new cn(go.eJ, cn.gP, DirectEncodedMemoryArea.get(13, 1).shift(3));
   private static final Ef Nu = (var0, var1) -> new ub(8 << XD.Uv.decodeInt(var0), DirectEncodedMemoryArea.get(5, 8)).buildOperand(var0, var1);
   private static final Ef[] YT = new Ef[]{Bq.CE, Bq.rk, lz};
   private static final Ef[] AY = new Ef[]{Bq.CE, Bq.KF, lz};
   private static final Ef[] Ld = new Ef[]{Bq.CE, Bq.KF, Nu};
   private static final Je[] XV = new Je[]{new Qg("MOV", YT).q(tW), new Qg("MOV", AY).q(tW), null, new Qg("FMOV", Ld).q(gO)};
   private static final Ef[] NY = new Ef[]{Bq.KT, Bq.KT, Bq.eJ, go.CY};
   private static final Ef[] xf = new Ef[]{Bq.KT, Jh, go.CY};
   private static final Je[] Ua = new Je[]{new Qg("EXT", NY).q(QJ.NX), new Qg("EXT", xf).q(QJ.br)};
   private static final Je jT = new iQ(new String[]{"ZIP1", "ZIP2", "UZP1", "UZP2", null, null, "TRN1", "TRN2"}, Me, TB).q(QJ.WX);
   private static final Je AD = new Qg("MOV", Bq.IN, new VP(Bq.Gu, dX.oW(zx)))
      .q(new Qg("MOV", Bq.IN, GT.za).q(QJ.NX), var0 -> k.Dw(zx.decodeInt(var0)) == 1)
      .q(QJ.NX);
   private static final Je[] ZY = new Je[]{new Qg("DUPQ", Bq.Bu, LR).q(QJ.tW), new Qg("EXTQ", Bq.KT, Bq.KT, Bq.eJ, go.qa).q(QJ.tW)};
   private static final Je Cw = new Qg("TBL", Bq.CE, iK, Bq.ZA).q(QJ.br);
   private static final Je bH = new Qg("TBX", FG).q(QJ.br);
   private static final Je rw = new Qg("TBXQ", FG).q(QJ.tW);
   private static final Je sE = new Qg("TBL", Bq.CE, Xz, Bq.ZA).q(QJ.NX);
   private static final Je BR = new Qg("MOV", Bq.CE, YH.ZT).q(QJ.NX);
   private static final Je wh = new Qg("INSR", Bq.CE, YH.Xo).q(QJ.NX);
   private static final Je[] iY = new Je[]{new Qg("PMOV", eC).q(QJ.tW), new Qg("PMOV", JW).q(QJ.tW), new Qg("PMOV", Ub).q(QJ.tW), new Qg("PMOV", tb).q(QJ.tW)};
   private static final Je[] pU = new Je[]{new Qg("PMOV", ND).q(QJ.tW), new Qg("PMOV", yW).q(QJ.tW), new Qg("PMOV", JF).q(QJ.tW), new Qg("PMOV", uz).q(QJ.tW)};
   private static final Ef[] g = new Ef[]{Bq.CE, Bq.CY};
   private static final Je[] oM = new Je[]{new Qg("SUNPKLO", g).q(gO), new Qg("SUNPKHI", g).q(gO), new Qg("UUNPKLO", g).q(gO), new Qg("UUNPKHI", g).q(gO)};
   private static final Je BQ = new Qg("INSR", Bq.CE, XD.xW).q(QJ.NX);
   private static final Je bc = new Qg("REV", oS).q(QJ.NX);
   private static final Je[] Qp = new Je[]{new Qg("PUNPKLO", GM).q(QJ.NX), new Qg("PUNPKHI", GM).q(QJ.NX)};
   private static final Je ra = new iQ(new String[]{"ZIP1", "ZIP2", "UZP1", "UZP2", "TRN1", "TRN2"}, Me, jb).q(QJ.NX);
   private static final Je EP = new Qg("REV", bl).q(QJ.NX);
   private static final Je sL = new iQ(new String[]{"ZIP1", "ZIP2", "UZP1", "UZP2", "TRN1", "TRN2"}, Me, FG).q(QJ.NX);
   private static final Je[] v = new Je[]{
      new Qg("MOV", Bq.CE, Bq.fq, XD.xW).q(QJ.NX),
      new Qg("COMPACT", jh).q(nq),
      new Qg("LASTA", YH.Rv, Bq.Sz, Bq.Ri).q(QJ.NX),
      new Qg("LASTB", YH.Rv, Bq.Sz, Bq.Ri).q(QJ.NX)
   };
   private static final Je[] hN = new Je[]{new Qg("LASTA", PQ).q(QJ.NX), new Qg("LASTB", PQ).q(QJ.NX)};
   private static final Je[] Rs = new Je[]{new Qg("REVB", iu).q(gO), new Qg("REVH", iu).q(nq), new Qg("REVW", iu).q(br), new Qg("RBIT", iu).q(QJ.NX)};
   private static final Je[] Bk = new Je[]{new Qg("CLASTA", Jf).q(QJ.NX), new Qg("CLASTB", Jf).q(QJ.NX), new Qg("MOV", Bq.CE, Bq.fq, YH.ZT).q(QJ.NX), null};
   private static final Je[] Zt = new Je[]{new Qg("CLASTA", fQ).q(QJ.NX), new Qg("CLASTB", fQ).q(QJ.NX)};
   private static final Je[] lv = new Je[]{new Qg("SPLICE", Jf).q(QJ.NX), new Qg("SPLICE", Bq.CE, Bq.Sz, iK).q(QJ.br)};
   private static final Je qx = new Qg("REVD", jk).q(QJ.NX);
   private static final Ef[] oL = new Ef[]{YH.Rr, Bq.Sz, YH.Rr, Bq.Ri};
   private static final Je[] HC = new Je[]{new Qg("CLASTA", oL).q(QJ.NX), new Qg("CLASTB", oL).q(QJ.NX)};
   private static final Je mo = new Qg("SEL", vC).q(QJ.NX).q(new Qg("MOV", Sz).q(QJ.NX), ag.q(Bq.xW, Bq.ZA));
   private static final CharSequence[] mS = new CharSequence[]{"HS", "HI", null, null, "GE", "GT", "EQ", "NE"};
   private static final CharSequence[] Lm = new CharSequence[]{"GE", "GT", "LT", "LE", "EQ", "NE"};
   private static final CharSequence[] MI = new CharSequence[]{"GE", "GT", "LT", "LE", "HS", "HI", "LO", "LS"};
   private static final CharSequence[] td = new CharSequence[]{"GE", "GT", "LT", "LE", "EQ", null, "NE", null};
   private static final CharSequence[] as = new CharSequence[]{"HS", "HI", "LO", "LS"};
   private static final CharSequence[] bm = new CharSequence[]{"GE", "GT", "EQ", "NE", "UO"};
   private static final CharSequence[] Ss = new CharSequence[]{"GE", "GT"};
   private static final de kN = new de.Nt(If, mS);
   private static final Je Sk = new Qg("CMP", IY).q(kN).q(QJ.NX);
   private static final de cz = new de.Nt(za, "EQ", "NE");
   private static final de nl = new de.Nt(If, MI);
   private static final Je jP = new Qg("CMP", YA).q(cz).q(WI);
   private static final Je Oy = new Qg("CMP", YA).q(nl).q(WI);
   private static final Ef[] RJ = new Ef[]{Bq.qR, Bq.mJ, Bq.Ri, go.Rr};
   private static final de Kc = new de.Nt(CE, as);
   private static final Je yS = new Qg("CMP", RJ).q(Kc).q(QJ.NX);
   private static final Ef[] i = new Ef[]{Bq.qR, Bq.mJ, Bq.Ri, go.sH};
   private static final de Zy = new de.Nt(If, Lm);
   private static final Je QF = new Qg("CMP", i).q(Zy).q(QJ.NX);
   private static final Je[] hE = new Je[]{
      new Qg("AND", fw).q(QJ.NX).q(new Qg("MOV", Wp).q(QJ.NX), ag.q(Bq.jh, Bq.iu)),
      new Qg("BIC", fw).q(QJ.NX),
      new Qg("EOR", fw).q(QJ.NX).q(new Qg("NOT", Wp).q(QJ.NX), ag.q(Bq.CB, Bq.iu)),
      new Qg("SEL", Yw).q(QJ.NX).q(new Qg("MOV", cY).q(QJ.NX), ag.q(Bq.YA, Bq.iu)),
      new Qg("AND", fw).q(de.cC).q(QJ.NX).q(new Qg("MOV", Wp).q(de.cC).q(QJ.NX), ag.q(Bq.jh, Bq.iu)),
      new Qg("BIC", fw).q(de.cC).q(QJ.NX),
      new Qg("EOR", fw).q(de.cC).q(QJ.NX).q(new Qg("NOT", Wp).q(de.cC).q(QJ.NX), ag.q(Bq.CB, Bq.iu)),
      null,
      new Qg("ORR", fw).q(QJ.NX).q(new Qg("MOV", kf).q(QJ.NX), ag.q(Bq.jh, Bq.CB, Bq.iu)),
      new Qg("ORN", fw).q(QJ.NX),
      new Qg("NOR", fw).q(QJ.NX),
      new Qg("NAND", fw).q(QJ.NX),
      new Qg("ORR", fw).q(de.cC).q(QJ.NX).q(new Qg("MOV", kf).q(de.cC).q(QJ.NX), ag.q(Bq.jh, Bq.CB, Bq.iu)),
      new Qg("ORN", fw).q(de.cC).q(QJ.NX),
      new Qg("NOR", fw).q(de.cC).q(QJ.NX),
      new Qg("NAND", fw).q(de.cC).q(QJ.NX)
   };
   private static final Je[] QH = new Je[]{
      new Qg("BRKPA", fw).q(QJ.NX), new Qg("BRKPB", fw).q(QJ.NX), new Qg("BRKPA", fw).q(de.cC).q(QJ.NX), new Qg("BRKPB", fw).q(de.cC).q(QJ.NX)
   };
   private static final Je[] lh = new Je[]{new Qg("BRKN", PY).q(QJ.NX), new Qg("BRKN", PY).q(de.cC).q(QJ.NX)};
   private static final Je[] DD = new Je[]{
      new Qg("BRKA", cR).q(QJ.NX), new Qg("BRKA", Wp).q(de.cC).q(ZA), new Qg("BRKB", cR).q(QJ.NX), new Qg("BRKB", Wp).q(de.cC).q(ZA)
   };
   private static final Je KW = new Qg("PTEST", Bq.rV, Bq.jh).q(QJ.NX);
   private static final Je sZ = new Qg("PFIRST", Bq.YA, Bq.ND, Bq.YA).q(QJ.NX);
   private static final Je Ab = new Qg("PFALSE", Bq.YA).q(QJ.NX);
   private static final de.CU bZ = (var0, var1) -> (var0[1] & 64) != 0;
   private static final Je bD = new Qg("RDFFR", TQ).q(bZ).q(QJ.NX);
   private static final Je Rf = new Qg("PNEXT", pQ).q(QJ.NX);
   private static final Je pi = new Qg("RDFFR", Bq.YA).q(QJ.NX);
   private static final de.CU Zh = (var0, var1) -> (var0[1] & 1) != 0;
   private static final Je xY = new Qg("PTRUE", Bq.qR, qr).q(Zh).q(QJ.NX);
   private static final de xE = new de.Nt(KT, MI);
   private static final Je SW = new Qg("WHILE", Bq.qR, YH.EB, YH.Bu).q(xE).q(QJ.NX);
   private static final de wq = new de.Nt(za, "EQ", "NE");
   private static final Je uk = new Qg("CTERM", YH.Xo, YH.eJ).q(wq).q(QJ.NX);
   private static final de ey = new de.Nt(za, "WR", "RW");
   private static final Je UH = new Qg("WHILE", Bq.qR, YH.nf, YH.zz).q(ey).q(QJ.br);
   private static final Je Kw = new Qg("PSEL", Bq.IY, Bq.rV, PW).q(QJ.tW);
   private static final de sV = new de.Nt(xW, MI);
   private static final de Pp = new de.Nt(oQ, MI);
   private static final Ef aq = new wJ(new String[]{"VLx2", "VLx4"}, qa);
   private static final Ef Jq = new wJ(new String[]{"VLx2", "VLx4"}, q);
   private static final Ef IK = new xT(64, Bq.PY, Bq.cR);
   private static final Je jR = new Qg("WHILE", Bq.jk, YH.nf, YH.zz, Jq).q(sV).q(QJ.tW);
   private static final Je kc = new Qg("WHILE", IK, YH.nf, YH.zz).q(Pp).q(QJ.tW);
   private static final Ef H = new xT(64, Bq.qR, Bq.eC);
   private static final Je jZ = new Qg("PEXT", Bq.qR, new VP(Bq.Cl, io)).q(QJ.tW);
   private static final Je pX = new Qg("PEXT", H, new VP(Bq.Cl, LK)).q(QJ.tW);
   private static final Je ch = new Qg("PTRUE", Bq.jk).q(QJ.tW);
   private static final Ef Hs = new cn(go.rL, cn.gP, DirectEncodedMemoryArea.get(13, 1).shift(3));
   private static final Ef[] Zf = new Ef[]{Bq.CE, Bq.CE, Hs};
   private static final Je[] iV = new Je[]{
      new Qg("ADD", Zf).q(QJ.NX),
      new Qg("SUB", Zf).q(QJ.NX),
      null,
      new Qg("SUBR", Zf).q(QJ.NX),
      new Qg("SQADD", Zf).q(QJ.NX),
      new Qg("UQADD", Zf).q(QJ.NX),
      new Qg("SQSUB", Zf).q(QJ.NX),
      new Qg("UQSUB", Zf).q(QJ.NX)
   };
   private static final Ef[] nW = new Ef[]{Bq.CE, Bq.CE, go.eJ};
   private static final Je[] np = new Je[]{new Qg("SMAX", nW).q(QJ.NX), new Qg("UMAX", Zf).q(QJ.NX), new Qg("SMIN", nW).q(QJ.NX), new Qg("UMIN", Zf).q(QJ.NX)};
   private static final Je[] Vu = new Je[]{new Qg("MUL", nW).q(QJ.NX)};
   private static final Je EC = new Qg("MOV", Bq.CE, lz).q(tW);
   private static final Je fM = new Qg("FMOV", Bq.CE, Nu).q(gO);
   private static final Je HW = new Qg("CNTP", YH.Uv, Bq.rV, Bq.Qu).q(QJ.NX);
   private static final Je Tn = new Qg("CNTP", YH.Uv, Bq.cy, aq).q(QJ.tW);
   private static final Ef[] y = new Ef[]{YH.Uv, Bq.Qu, YH.PV};
   private static final Ef[] Wc = new Ef[]{YH.Uv, Bq.Qu};
   private static final Ef[] xu = new Ef[]{YH.PV, Bq.Qu};
   private static final Je[][] yZ = new Je[][]{
      {
            new Qg("SQINCP", Qu).q(gO),
            null,
            null,
            null,
            new Qg("UQINCP", Qu).q(gO),
            null,
            null,
            null,
            new Qg("SQDECP", Qu).q(gO),
            null,
            null,
            null,
            new Qg("UQDECP", Qu).q(gO)
      },
      {
            new Qg("SQINCP", y).q(QJ.NX),
            null,
            new Qg("SQINCP", Wc).q(QJ.NX),
            null,
            new Qg("UQINCP", xu).q(QJ.NX),
            null,
            new Qg("UQINCP", Wc).q(QJ.NX),
            null,
            new Qg("SQDECP", y).q(QJ.NX),
            null,
            new Qg("SQDECP", Wc).q(QJ.NX),
            null,
            new Qg("UQDECP", xu).q(QJ.NX),
            null,
            new Qg("UQDECP", Wc).q(QJ.NX)
      },
      {new Qg("INCP", Qu).q(gO), null, null, null, new Qg("DECP", Qu).q(gO)},
      {new Qg("INCP", Wc).q(QJ.NX), null, null, null, new Qg("DECP", Wc).q(QJ.NX)}
   };
   private static final Je SD = new Qg("WRFFR", Bq.Qu).q(QJ.NX);
   private static final Je bA = new Qg("SETFFR").q(QJ.NX);
   private static final Ef[] Sn = new Ef[]{Bq.CE, Bq.Wx, Bq.Ov};
   private static final Ef[] df = new Ef[]{Bq.CE, Bq.CY, Bq.nv};
   private static final Ef[] zP = new Ef[]{Bq.CE, Bq.Ri, Bq.nv};
   private static final Ef tg = new wJ(Wx, Hk);
   private static final Ef[] rT = new Ef[]{Bq.CE, Bq.Ri, Bq.ZA, tg};
   private static final Ef[] sf = new Ef[]{Bq.CE, Bq.fq, Bq.CY};
   private static final Ef[] cp = new Ef[]{Bq.Gf, Bq.YN, jz, tg};
   private static final Ef[] CT = new Ef[]{Bq.Ef, Bq.Rv, cO, tg};
   private static final Je[] Xh = new Je[]{new Qg("SDOT", Sn).q(nq), new Qg("UDOT", Sn).q(nq), new Qg("SQDMLALBT", df).q(Tq), new Qg("SQDMLSLBT", df).q(Tq)};
   private static final Je nc = new Qg("CDOT", Bq.CE, Bq.Wx, Bq.Ov, tg).q(NX);
   private static final Je[] vg = new Je[]{new Qg("CMLA", rT).q(QJ.br), new Qg("SQRDCMLAH", rT).q(QJ.br)};
   private static final Je[] lq = new Je[]{
      new Qg("SMLALB", df).q(Tq),
      new Qg("SMLALT", df).q(Tq),
      new Qg("UMLALB", df).q(Tq),
      new Qg("UMLALT", df).q(Tq),
      new Qg("SMLSLB", df).q(Tq),
      new Qg("SMLSLT", df).q(Tq),
      new Qg("UMLSLB", df).q(Tq),
      new Qg("UMLSLT", df).q(Tq),
      new Qg("SQDMLALB", df).q(Tq),
      new Qg("SQDMLALT", df).q(Tq),
      new Qg("SQDMLSLB", df).q(Tq),
      new Qg("SQDMLSLT", df).q(Tq),
      new Qg("SQRDMLAH", FG).q(QJ.br),
      new Qg("SQRDMLSH", FG).q(QJ.br),
      new Qg("USDOT", Eq).q(nY, QJ.ND)
   };
   private static final Je zN = new iQ(
         new String[]{
            null, null, "SRSHL", "URSHL", null, null, "SRSHLR", "URSHLR", "SQSHL", "UQSHL", "SQRSHL", "UQRSHL", "SQSHLR", "UQSHLR", "SQRSHLR", "UQRSHLR"
         },
         xK,
         of
      )
      .q(QJ.br);
   private static final Je Tc = new iQ(new String[]{"SHADD", "UHADD", "SHSUB", "UHSUB", "SRHADD", "URHADD", "SHSUBR", "UHSUBR"}, TX, of).q(QJ.br);
   private static final Je hj = new iQ(new String[]{"SQADD", "UQADD", "SQSUB", "UQSUB", "SUQADD", "USQADD", "SQSUBR", "UQSUBR"}, TX, of).q(QJ.br);
   private static final Je[] wK = new Je[]{new Qg("SADALP", sf).q(Tq), new Qg("UADALP", sf).q(Tq)};
   private static final Je[] SH = new Je[]{new Qg("URECPE", iu).q(lF), new Qg("SQABS", iu).q(QJ.br), new Qg("URSQRTE", iu).q(lF), new Qg("SQNEG", iu).q(QJ.br)};
   private static final Je[] Ax = new Je[]{
      null,
      new Qg("ADDP", of).q(QJ.br),
      null,
      null,
      new Qg("SMAXP", of).q(QJ.br),
      new Qg("UMAXP", of).q(QJ.br),
      new Qg("SMINP", of).q(QJ.br),
      new Qg("UMINP", of).q(QJ.br)
   };
   private static final Je[] UC = new Je[]{
      new Qg("ZIPQ1", FG).q(QJ.tW),
      new Qg("ZIPQ2", FG).q(QJ.tW),
      new Qg("UZPQ1", FG).q(QJ.tW),
      new Qg("UZPQ2", FG).q(QJ.tW),
      null,
      null,
      new Qg("TBLQ", Bq.CE, Xz, Bq.ZA).q(QJ.tW)
   };
   private static final Je[] qP = new Je[]{new Qg("MLAPT", FG).q(QJ.NX, br, QJ.rV), null, new Qg("MADPT", Oj).q(QJ.NX, br, QJ.rV), null};
   private static final Je[] ex = new Je[]{new Qg("SCLAMP", FG).q(QJ.tW), new Qg("UCLAMP", FG).q(QJ.tW)};
   private static final Je[] me = new Je[]{new Qg("SDOT", hP).q(QJ.tW), new Qg("UDOT", hP).q(QJ.tW)};
   private static final Je[] b = new Je[]{new Qg("SDOT", cb).q(QJ.tW), new Qg("UDOT", cb).q(QJ.tW)};
   private static final Je[] LM = new Je[]{new Qg("SDOT", zm).q(QJ.NX), new Qg("UDOT", zm).q(QJ.NX), new Qg("SDOT", eG).q(QJ.NX), new Qg("UDOT", eG).q(QJ.NX)};
   private static final Je[] RB = new Je[]{
      null,
      null,
      new Qg("MLA", dS).q(QJ.br),
      new Qg("MLS", dS).q(QJ.br),
      new Qg("MLA", BU).q(QJ.br),
      new Qg("MLS", BU).q(QJ.br),
      new Qg("MLA", xG).q(QJ.br),
      new Qg("MLS", xG).q(QJ.br)
   };
   private static final Je[] PG = new Je[]{
      null,
      null,
      new Qg("SQRDMLAH", dS).q(QJ.br),
      new Qg("SQRDMLSH", dS).q(QJ.br),
      new Qg("SQRDMLAH", BU).q(QJ.br),
      new Qg("SQRDMLSH", BU).q(QJ.br),
      new Qg("SQRDMLAH", xG).q(QJ.br),
      new Qg("SQRDMLSH", xG).q(QJ.br)
   };
   private static final Je[] rU = new Je[]{new Qg("USDOT", zm).q(QJ.br, QJ.ND), new Qg("SUDOT", zm).q(QJ.br, QJ.ND)};
   private static final Je[] ln = new Je[]{
      new Qg("SQDMLALB", wS).q(QJ.br),
      new Qg("SQDMLALT", wS).q(QJ.br),
      new Qg("SQDMLSLB", wS).q(QJ.br),
      new Qg("SQDMLSLT", wS).q(QJ.br),
      new Qg("SQDMLALB", Oz).q(QJ.br),
      new Qg("SQDMLALT", Oz).q(QJ.br),
      new Qg("SQDMLSLB", Oz).q(QJ.br),
      new Qg("SQDMLSLT", Oz).q(QJ.br)
   };
   private static final Je[] NE = new Je[]{new Qg("CDOT", Bq.Ef, Bq.eJ, Uz, tg).q(QJ.br), new Qg("CDOT", Bq.cC, Bq.YN, bY, tg).q(QJ.br)};
   private static final Je[] rv = new Je[]{new Qg("CMLA", cp).q(QJ.br), new Qg("CMLA", CT).q(QJ.br)};
   private static final Je[] VL = new Je[]{new Qg("SQRDCMLAH", cp).q(QJ.br), new Qg("SQRDCMLAH", CT).q(QJ.br)};
   private static final Je[] if = new Je[]{
      new Qg("SMLALB", wS).q(QJ.br),
      new Qg("SMLALT", wS).q(QJ.br),
      new Qg("UMLALB", wS).q(QJ.br),
      new Qg("UMLALT", wS).q(QJ.br),
      new Qg("SMLSLB", wS).q(QJ.br),
      new Qg("SMLSLT", wS).q(QJ.br),
      new Qg("UMLSLB", wS).q(QJ.br),
      new Qg("UMLSLT", wS).q(QJ.br),
      new Qg("SMLALB", Oz).q(QJ.br),
      new Qg("SMLALT", Oz).q(QJ.br),
      new Qg("UMLALB", Oz).q(QJ.br),
      new Qg("UMLALT", Oz).q(QJ.br),
      new Qg("SMLSLB", Oz).q(QJ.br),
      new Qg("SMLSLT", Oz).q(QJ.br),
      new Qg("UMLSLB", Oz).q(QJ.br),
      new Qg("UMLSLT", Oz).q(QJ.br)
   };
   private static final Je[] SB = new Je[]{
      new Qg("SMULLB", wS).q(QJ.br),
      new Qg("SMULLT", wS).q(QJ.br),
      new Qg("UMULLB", wS).q(QJ.br),
      new Qg("UMULLT", wS).q(QJ.br),
      new Qg("SMULLB", Oz).q(QJ.br),
      new Qg("SMULLT", Oz).q(QJ.br),
      new Qg("UMULLB", Oz).q(QJ.br),
      new Qg("UMULLT", Oz).q(QJ.br)
   };
   private static final Je[] HV = new Je[]{
      new Qg("SQDMULLB", wS).q(QJ.br), new Qg("SQDMULLT", wS).q(QJ.br), new Qg("SQDMULLB", Oz).q(QJ.br), new Qg("SQDMULLT", Oz).q(QJ.br)
   };
   private static final Je[] EY = new Je[]{
      null,
      null,
      new Qg("SQDMULH", dS).q(QJ.br),
      new Qg("SQRDMULH", dS).q(QJ.br),
      new Qg("SQDMULH", BU).q(QJ.br),
      new Qg("SQRDMULH", BU).q(QJ.br),
      new Qg("SQDMULH", xG).q(QJ.br),
      new Qg("SQRDMULH", xG).q(QJ.br)
   };
   private static final Je[] Qm = new Je[]{null, new Qg("MUL", dS).q(QJ.br), new Qg("MUL", BU).q(QJ.br), new Qg("MUL", xG).q(QJ.br)};
   private static final Je nz = new iQ(
         new String[]{
            "SADDLB", "SADDLT", "UADDLB", "UADDLT", "SSUBLB", "SSUBLT", "USUBLB", "USUBLT", null, null, null, null, "SABDLB", "SABDLT", "UABDLB", "UABDLT"
         },
         PV,
         df
      )
      .q(Tq);
   private static final Je Pt = new iQ(new String[]{"SADDWB", "SADDWT", "UADDWB", "UADDWT", "SSUBWB", "SSUBWT", "USUBWB", "USUBWT"}, Me, zP).q(Tq);
   private static final Je[] kt = new Je[]{
      new Qg("SQDMULLB", df).q(Tq),
      new Qg("SQDMULLT", df).q(Tq),
      new Qg("PMULLB", df).q(QJ.br).q(new Qg("PMULLB", Bq.cC, Bq.Rv, Bq.fQ).q(QJ.br), var0 -> xK(var0) == 2),
      new Qg("PMULLT", df).q(QJ.br).q(new Qg("PMULLT", Bq.cC, Bq.Rv, Bq.fQ).q(QJ.br), var0 -> xK(var0) == 2),
      new Qg("SMULLB", df).q(Tq),
      new Qg("SMULLT", df).q(Tq),
      new Qg("UMULLB", df).q(Tq),
      new Qg("UMULLT", df).q(Tq)
   };
   private static final Je[] rf = new Je[]{new Qg("PMULLB", Bq.sH, Bq.zx, Bq.fi).q(QJ.br), new Qg("PMULLT", Bq.sH, Bq.zx, Bq.fi).q(QJ.br)};
   private static final Ef[] Oq = new Ef[]{Bq.EB, Bq.WI, hy};
   private static final Je[] FV = new Je[]{
      new Qg("SADDLBT", df).q(Tq),
      null,
      new Qg("SSUBLBT", df).q(Tq),
      new Qg("SSUBLTB", df).q(Tq),
      new Qg("EORBT", FG).q(QJ.br),
      new Qg("EORTB", FG).q(QJ.br),
      null,
      null,
      new Qg("SSHLLB", Oq).q(QJ.br),
      new Qg("SSHLLT", Oq).q(QJ.br),
      new Qg("USHLLB", Oq).q(QJ.br),
      new Qg("USHLLT", Oq).q(QJ.br),
      new Qg("BEXT", FG).q(QJ.NX, QJ.GC),
      new Qg("BDEP", FG).q(QJ.NX, QJ.GC),
      new Qg("BGRP", FG).q(QJ.NX, QJ.GC)
   };
   private static final Je[] zv = new Je[]{
      new Qg("SMMLA", Eq).q(QJ.NX, QJ.ND), null, new Qg("USMMLA", Eq).q(QJ.NX, QJ.ND), new Qg("UMMLA", Eq).q(QJ.NX, QJ.ND)
   };
   private static final Ef zg = new wJ(Wx, new EncodedMemoryAreaList(qa, VirtualEncodedMemoryArea._1));
   private static final Ef[] Xa = new Ef[]{Bq.CE, Bq.CE, Bq.Ri, zg};
   private static final Je[] XE = new Je[]{
      new Qg("SABALB", df).q(Tq),
      new Qg("SABALT", df).q(Tq),
      new Qg("UABALB", df).q(Tq),
      new Qg("UABALT", df).q(Tq),
      null,
      null,
      new Qg("CADD", Xa).q(QJ.br),
      new Qg("SQCADD", Xa).q(QJ.br),
      new Qg("SSRA", CW).q(QJ.br),
      new Qg("USRA", CW).q(QJ.br),
      new Qg("SRSRA", CW).q(QJ.br),
      new Qg("URSRA", CW).q(QJ.br),
      new Qg("SRI", CW).q(QJ.br),
      new Qg("SLI", qm).q(QJ.br),
      new Qg("SABA", FG).q(QJ.br),
      new Qg("UABA", FG).q(QJ.br)
   };
   private static final Ef[] DV = new Ef[]{Bq.jq, Bq.AB, Bq.Lj};
   private static final Je[] Jy = new Je[]{
      new Qg("ADCLB", DV).q(QJ.br), new Qg("ADCLT", DV).q(QJ.br), new Qg("SBCLB", DV).q(QJ.br), new Qg("SBCLT", DV).q(QJ.br)
   };
   private static final Ef[] yI = new Ef[]{Bq.Rr, Bq.Tq, RL};
   private static final Je uP = new iQ(
         new String[]{
            "SQSHRUNB",
            "SQSHRUNT",
            "SQRSHRUNB",
            "SQRSHRUNT",
            "SHRNB",
            "SHRNT",
            "RSHRNB",
            "RSHRNT",
            "SQSHRNB",
            "SQSHRNT",
            "SQRSHRNB",
            "SQRSHRNT",
            "UQSHRNB",
            "UQSHRNT",
            "UQRSHRNB",
            "UQRSHRNT"
         },
         PV,
         yI
      )
      .q(QJ.br);
   private static final Je dm = new iQ(
         new String[]{"SQRSHRUN", null, "SQRSHRN", "UQRSHRN"}, sH, Bq.Gf, dW, new go(dX.q(VirtualEncodedMemoryArea.get(16, 5), xK))
      )
      .q(QJ.tW);
   private static final Ef[] qB = new Ef[]{Bq.Rr, Bq.Tq};
   private static final Je Zg = new iQ(new String[]{"SQXTNB", "SQXTNT", "UQXTNB", "UQXTNT", "SQXTUNB", "SQXTUNT"}, Me, qB).q(Ov);
   private static final Je Nh = new iQ(new String[]{"SQCVTN", "UQCVTN", "SQCVTUN", null}, Gf, Bq.Gf, dW).q(QJ.tW);
   private static final Ef[] gp = new Ef[]{Bq.ui, Bq.Ri, Bq.ZA};
   private static final Je aj = new iQ(new String[]{"ADDHNB", "ADDHNT", "RADDHNB", "RADDHNT", "SUBHNB", "SUBHNT", "RSUBHNB", "RSUBHNT"}, Me, gp).q(Tq);
   private static final Je[] Hw = new Je[]{new Qg("MATCH", IY).q(CY), new Qg("NMATCH", IY).q(QJ.br)};
   private static final Je lu = new Qg("LUTI2", Bq.Gf, iO, CK).q(QJ.iu);
   private static final Je[] hC = new Je[]{
      new Qg("HISTSEG", FG).q(AB),
      new Qg("LUTI4", Bq.KT, ZE, pe).q(QJ.iu),
      lu,
      null,
      new Qg("LUTI2", Bq.KT, ZE, Gg).q(QJ.iu),
      new Qg("LUTI4", Bq.Gf, Qe, Gg).q(QJ.iu),
      lu,
      new Qg("LUTI4", Bq.Gf, iO, Gg).q(QJ.iu)
   };
   private static final Je sC = new Qg("HISTCNT", Cl).q(NX);
   private static final Je[] yY = new Je[]{new Qg("AESMC", hw).q(QJ.cy), new Qg("AESIMC", hw).q(QJ.cy)};
   private static final Je[] Uu = new Je[]{new Qg("AESE", gm).q(QJ.cy), new Qg("AESD", gm).q(QJ.cy), new Qg("SM4E", sc).q(QJ.Cl)};
   private static final Je[] KQ = new Je[]{new Qg("SM4EKEY", wN).q(QJ.Cl), new Qg("RAX1", Uc).q(QJ.jk)};
   private static final Ef bN = new wJ(Wx, RF);
   private static final Ef[] yL = new Ef[]{Bq.CE, Bq.fq, Bq.Ri, Bq.ZA, bN};
   private static final Je BV = new Qg("FCMLA", yL).q(gO);
   private static final Ef IG = new wJ(Wx, new EncodedMemoryAreaList(Dz, VirtualEncodedMemoryArea._1));
   private static final Ef[] KR = new Ef[]{Bq.CE, Bq.fq, Bq.CE, Bq.Ri, IG};
   private static final Je mW = new Qg("FCADD", KR).q(gO);
   private static final Je XZ = new iQ(new String[]{"FADDP", null, null, null, "FMAXNMP", "FMINNMP", "FMAXP", "FMINP"}, TX, of).q(Tq);
   private static final Je[][] Dq = new Je[][]{
      {null, null, new Qg("FCVTXNT", GC).q(QJ.br)},
      null,
      {new Qg("FCVTNT", rV).q(QJ.br), new Qg("FCVTLT", CB).q(QJ.br), new Qg("BFCVTNT", rV).q(QJ.eC)},
      {null, null, new Qg("FCVTNT", GC).q(QJ.br), new Qg("FCVTLT", rk).q(QJ.br)}
   };
   private static final Je[] ed = new Je[]{
      new Qg("FADDQV", fi).q(Gu),
      null,
      null,
      null,
      new Qg("FMAXNMQV", fi).q(Gu),
      new Qg("FMINNMQV", fi).q(Gu),
      new Qg("FMAXQV", fi).q(Gu),
      new Qg("FMINQV", fi).q(Gu)
   };
   private static final Je[] Yi = new Je[]{
      null,
      null,
      null,
      null,
      new Qg("FMLA", dS).q(QJ.NX),
      new Qg("FMLS", dS).q(QJ.NX),
      new Qg("BFMLA", dS).q(QJ.CB),
      new Qg("BFMLS", dS).q(QJ.CB),
      new Qg("FMLA", BU).q(QJ.NX),
      new Qg("FMLS", BU).q(QJ.NX),
      null,
      null,
      new Qg("FMLA", xG).q(QJ.NX),
      new Qg("FMLS", xG).q(QJ.NX)
   };
   private static final Je[] Et = new Je[]{new Qg("FCMLA", Bq.Gf, Bq.YN, jz, tg).q(QJ.NX), new Qg("FCMLA", Bq.Ef, Bq.Rv, cO, tg).q(QJ.NX)};
   private static final Je[] ZC = new Je[]{null, new Qg("FMUL", dS).q(QJ.NX), new Qg("FMUL", BU).q(QJ.NX), new Qg("FMUL", xG).q(QJ.NX)};
   private static final Je wf = new Qg("BFMUL", dS).q(QJ.CB);
   private static final Je Ur = new Qg("FCLAMP", FG).q(QJ.tW);
   private static final Je Ya = new Qg("BFCLAMP", Kn).q(QJ.CB);
   private static final Je ar = new Qg("FDOT", Dk).q(QJ.kv);
   private static final Je[][] Dm = new Je[][]{{new Qg("FDOT", cb).q(QJ.tW), ar, null, ar}, {new Qg("BFDOT", cb).q(QJ.C), new Qg("FDOT", zm).q(QJ.oS)}};
   private static final Je[] Ha = new Je[]{
      new Qg("FMLALB", wS).q(QJ.br),
      new Qg("FMLALT", wS).q(QJ.br),
      new Qg("FMLSLB", wS).q(QJ.br),
      new Qg("FMLSLT", wS).q(QJ.br),
      new Qg("BFMLALB", wS).q(QJ.C),
      new Qg("BFMLALT", wS).q(QJ.C),
      new Qg("BFMLSLB", wS).q(QJ.tW),
      new Qg("BFMLSLT", wS).q(QJ.tW)
   };
   private static final Je[] nE = new Je[]{new Qg("FDOT", hP).q(QJ.tW), new Qg("FDOT", Rd).q(QJ.kv), new Qg("BFDOT", hP).q(QJ.C), new Qg("FDOT", Eq).q(QJ.oS)};
   private static final Je[] ZF = new Je[]{
      new Qg("FMLALB", hP).q(QJ.br),
      new Qg("FMLALT", hP).q(QJ.br),
      new Qg("FMLSLB", hP).q(QJ.br),
      new Qg("FMLSLT", hP).q(QJ.br),
      new Qg("BFMLALB", hP).q(QJ.C),
      new Qg("BFMLALT", hP).q(QJ.C),
      new Qg("BFMLSLB", hP).q(QJ.tW),
      new Qg("BFMLSLT", hP).q(QJ.tW)
   };
   private static final Je Wk = new iQ(new String[]{"FMLALLBB", "FMLALLBT", "FMLALLTB", "FMLALLTT"}, sH, Eq).q(QJ.FG);
   private static final Je iN = new iQ(new String[]{"FMLALB", "FMLALT"}, cC, Rd).q(QJ.FG);
   private static final Je RH = new iQ(new String[]{"FMLALLBB", "FMLALLBT", "FMLALLTB", "FMLALLTT"}, oW, Wn).q(QJ.FG);
   private static final Je[] PS = new Je[]{null, new Qg("BFMMLA", hP).q(QJ.C), new Qg("FMMLA", wN).q(QJ.KF), new Qg("FMMLA", Uc).q(QJ.rk)};
   private static final Je RC = new iQ(new String[]{"FMLALB", "FMLALT"}, GY, Id).q(QJ.FG);
   private static final de Bc = new de.Nt(If, bm);
   private static final Je El = new Qg("FCM", IY).q(Bc).q(gO);
   private static final de iE = new de.Nt(q, Ss);
   private static final Je pp = new Qg("FAC", IY).q(iE).q(gO);
   private static final de aY = new de.Nt(ui, td);
   private static final Je Lc = new Qg("FCM", qR).q(aY).q(gO);
   private static final Je MR = new iQ(new String[]{"FADD", "FSUB", "FMUL", "FTSMUL", null, null, "FRECPS", "FRSQRTS"}, Me, FG).q(gO);
   private static final Je sN = new iQ(new String[]{"BFADD", "BFSUB", "BFMUL"}, Me, Kn).q(QJ.CB);
   private static final Je At = new iQ(
         new String[]{"FADD", "FSUB", "FMUL", "FSUBR", "FMAXNM", "FMINNM", "FMAX", "FMIN", "FABD", "FSCALE", "FMULX", null, "FDIVR", "FDIV"}, xK, of
      )
      .q(gO);
   private static final Je Yo = new iQ(new String[]{"BFADD", "BFSUB", "BFMUL", null, "BFMAXNM", "BFMINNM", "BFMAX", "BFMIN"}, xK, fq).q(QJ.CB);
   private static final Je dK = new iQ(new String[]{"FAMAX", "FAMIN"}, Dz, of).q(Tq, QJ.Gu);
   private static final Je Jr = new Qg("FTMAD", uY).q(gO);
   private static final Ef Ph = new Nd(Nd.Uv, new dD(lm, 0.5, 1.0));
   private static final Ef DR = new Nd(Nd.Uv, new dD(lm, 0.5, 2.0));
   private static final Ef QQ = new Nd(Nd.Uv, new dD(lm, 0.0, 1.0));
   private static final Ef[] Vg = new Ef[]{Bq.CE, Bq.fq, Bq.CE, Ph};
   private static final Ef[] HI = new Ef[]{Bq.CE, Bq.fq, Bq.CE, DR};
   private static final Ef[] dB = new Ef[]{Bq.CE, Bq.fq, Bq.CE, QQ};
   private static final Je[] oa = new Je[]{
      new Qg("FADD", Vg).q(gO),
      new Qg("FSUB", Vg).q(gO),
      new Qg("FMUL", HI).q(gO),
      new Qg("FSUBR", Vg).q(gO),
      new Qg("FMAXNM", dB).q(gO),
      new Qg("FMINNM", dB).q(gO),
      new Qg("FMAX", dB).q(gO),
      new Qg("FMIN", dB).q(gO)
   };
   private static final CharSequence[] wy = new CharSequence[]{"N", "P", "M", "Z", "A", null, "X", "I"};
   private static final de ru = new de.Nt(TX, wy);
   private static final Je Qb = new Qg("FRINT", iu).q(ru).q(gO);
   private static final Je[][] Q = new Je[][]{
      {null, null, new Qg("FCVTX", GC).q(QJ.br)},
      null,
      {new Qg("FCVT", rV).q(QJ.NX), new Qg("FCVT", CB).q(QJ.NX), new Qg("BFCVT", rV).q(QJ.C)},
      {new Qg("FCVT", WX).q(QJ.NX), new Qg("FCVT", KF).q(QJ.NX), new Qg("FCVT", GC).q(QJ.NX), new Qg("FCVT", rk).q(QJ.NX)}
   };
   private static final Je[] tk = new Je[]{new Qg("FRECPX", iu).q(gO), new Qg("FSQRT", iu).q(gO)};
   private static final Je[][] ON = new Je[][]{
      null,
      {
            null,
            null,
            new Qg("SCVTF", Bs).q(QJ.NX),
            new Qg("UCVTF", Bs).q(QJ.NX),
            new Qg("SCVTF", rV).q(QJ.NX),
            new Qg("UCVTF", rV).q(QJ.NX),
            new Qg("SCVTF", WX).q(QJ.NX),
            new Qg("UCVTF", WX).q(QJ.NX)
      },
      {null, null, null, null, new Qg("SCVTF", C).q(QJ.NX), new Qg("UCVTF", C).q(QJ.NX)},
      {
            new Qg("SCVTF", rk).q(QJ.NX),
            new Qg("UCVTF", rk).q(QJ.NX),
            null,
            null,
            new Qg("SCVTF", GC).q(QJ.NX),
            new Qg("UCVTF", GC).q(QJ.NX),
            new Qg("SCVTF", cy).q(QJ.NX),
            new Qg("UCVTF", cy).q(QJ.NX)
      }
   };
   private static final Je[][] hb = new Je[][]{
      {new Qg("FLOGB", hM).q(Yp)},
      {
            null,
            null,
            new Qg("FCVTZS", Bs).q(QJ.NX),
            new Qg("FCVTZU", Bs).q(QJ.NX),
            new Qg("FCVTZS", CB).q(QJ.NX),
            new Qg("FCVTZU", CB).q(QJ.NX),
            new Qg("FCVTZS", KF).q(QJ.NX),
            new Qg("FCVTZU", KF).q(QJ.NX)
      },
      {null, null, null, null, new Qg("FCVTZS", C).q(QJ.NX), new Qg("FCVTZU", C).q(QJ.NX)},
      {
            new Qg("FCVTZS", GC).q(QJ.NX),
            new Qg("FCVTZU", GC).q(QJ.NX),
            null,
            null,
            new Qg("FCVTZS", rk).q(QJ.NX),
            new Qg("FCVTZU", rk).q(QJ.NX),
            new Qg("FCVTZS", cy).q(QJ.NX),
            new Qg("FCVTZU", cy).q(QJ.NX)
      }
   };
   private static final Je VY = new iQ(new String[]{"FADDV", null, null, null, "FMAXNMV", "FMINNMV", "FMAXV", "FMINV"}, TX, nv).q(gO);
   private static final Je ym = new iQ(new String[]{"F1CVT", "F2CVT", "BF1CVT", "BF2CVT", "F1CVTLT", "F2CVTLT", "BF1CVTLT", "BF2CVTLT"}, mI, vh).q(QJ.hM);
   private static final Je[] eZ = new Je[]{
      new Qg("FCVTN", Bq.KT, Qe).q(QJ.hM), new Qg("FCVTNB", Bq.KT, dW).q(QJ.hM), new Qg("BFCVTN", Bq.KT, Qe).q(QJ.hM), new Qg("FCVTNT", Bq.KT, dW).q(QJ.hM)
   };
   private static final Je JH = new iQ(new String[]{"FRECPE", "FRSQRTE"}, Dz, oS).q(gO);
   private static final Je[] MY = new Je[]{new Qg("FADDA", LL).q(gO)};
   private static final Je Kd = new iQ(new String[]{"FMLA", "FMLS", "FNMLA", "FNMLS"}, RF, fn).q(gO);
   private static final Je Cj = new iQ(new String[]{"BFMLA", "BFMLS"}, q, mJ).q(QJ.CB);
   private static final Je Lw = new iQ(new String[]{"FMAD", "FMSB", "FNMAD", "FNMSB"}, RF, fn).q(gO);

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static int q(byte[] var0, IEncodedMemoryArea var1, IEncodedMemoryArea var2, DH.eo var3) {
      int var4 = 8 << k.q(var1.decode(var0));
      int var5 = var1.decodeInt(var0);
      int var6 = var5 << var2.getLength() | var2.decodeInt(var0);
      switch (var3) {
         case q:
            return var6 - var4;
         case xK:
         case RF:
         default:
            return 2 * var4 - var6;
      }
   }

   private static boolean q(long var0, long var2) {
      return (var0 & var2) == 0L || (var0 & var2) == var2;
   }

   private static boolean q(long var0, int var2) {
      long var3 = (1L << var2) - 1L;
      return (var0 & var3) == (var0 >>> var2 & var3);
   }

   private static boolean RF(byte[] var0) {
      if (var0[2] == -120 && var0[3] == 0) {
         System.out.println();
      }

      long var1;
      try {
         var1 = xB.q(Rr.decodeInt(var0), HF.decodeInt(var0), Ef.decodeInt(var0), 64);
      } catch (ProcessorException var3) {
         return false;
      }

      if ((var1 & 255L) != 0L) {
         if (q(var1, -256L)) {
            return false;
         }

         if (q(var1, 32) && q(var1, 4294967040L)) {
            return false;
         }

         if (q(var1, 32) && q(var1, 16) && q(var1, 65280L)) {
            return false;
         }

         if (q(var1, 32) && q(var1, 16) && q(var1, 8)) {
            return false;
         }
      } else {
         if (q(var1, -65536L)) {
            return false;
         }

         if (q(var1, 32) && q(var1, 4294901760L)) {
            return false;
         }

         if (q(var1, 32) && q(var1, 16)) {
            return false;
         }
      }

      return true;
   }

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 224) >>> 5;
      switch (var1) {
         case 0:
            return gO(var0);
         case 1:
            return lm(var0);
         case 2:
            return io(var0);
         case 3:
            return oQ(var0);
         case 4:
            return FI.q(var0);
         case 5:
            return FI.RF(var0);
         case 6:
            return FI.xK(var0);
         case 7:
            return FI.Dw(var0);
         default:
            return null;
      }
   }

   private static int xK(byte[] var0) {
      return (var0[1] & 192) >>> 6;
   }

   private static int Dw(byte[] var0) {
      return (var0[2] & 4) >>> 2;
   }

   private static int Uv(byte[] var0) {
      return (var0[2] & 12) >>> 2;
   }

   private static int oW(byte[] var0) {
      return (var0[3] & 16) >>> 4;
   }

   private static Je gO(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      switch (var1) {
         case 0:
         case 1:
            return var2 < 16 ? nf(var0) : gP(var0);
         default:
            if ((var2 & 24) == 0) {
               return (var2 & 6) == 0 ? yu[xK(var0)] : null;
            } else if ((var2 & 24) == 8) {
               int var3 = (var0[2] & 224) >>> 5;
               return var3 >= 4 && var3 != 6 ? null : XV[var3 >>> 1];
            } else {
               return za(var0);
            }
      }
   }

   private static Je nf(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 16) == 16) {
         return HK[(var1 & 32) >>> 4 | (var1 & 8) >>> 3];
      } else if ((var1 & 40) == 0) {
         int var3 = (var0[1] & 28) >>> 2;
         switch (var3) {
            case 0:
            case 1:
            case 2:
            case 3:
               return uw[var0[1] & 15];
            case 4:
            case 5:
            case 6:
               return fe[var0[1] & 15];
            case 7:
            default:
               return null;
         }
      } else {
         if ((var1 & 40) == 8) {
            int var2 = (var0[1] & 28) >>> 2;
            switch (var2) {
               case 4:
               case 5:
                  if ((var0[1] & 6) == 0) {
                     return So;
                  }
                  break;
               default:
                  return Qg.q(Kl, var2, var0[1] & 3, var0, "SVE Integer Reduction");
            }
         } else {
            if ((var1 & 40) == 32) {
               return Qg.q(AG, (var0[1] & 24) >>> 3, var0[1] & 7, var0, "SVE Bitwise Shift - Predicated");
            }

            if ((var1 & 40) == 40) {
               return Qg.q(er, (var0[1] & 24) >>> 3, var0[1] & 7, var0, "SVE Integer Unary Arithmetic - Predicated");
            }
         }

         return null;
      }
   }

   private static Je gP(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 56) == 0) {
         int var14 = (var0[2] & 28) >>> 2;
         return SM[var14];
      } else {
         if ((var1 & 56) == 8) {
            int var2 = (var0[2] & 28) >>> 2;
            if (var2 == 4) {
               return bj[xK(var0)];
            }

            if (var2 == 5) {
               return GO;
            }

            if (var2 >= 6) {
               return QZ[xK(var0) << 1 | Dw(var0)];
            }
         } else {
            if ((var1 & 60) == 16) {
               return sa[Uv(var0)];
            }

            if ((var1 & 60) == 20) {
               int var7 = (var0[1] & 64) >>> 6;
               int var3 = (var0[1] & 128) >>> 7;
               int var4 = (var1 & 2) >>> 1;
               int var5 = var3 << 1 | var4;
               switch (var5) {
                  case 0:
                     return WJ[var7];
                  case 1:
                     return R.q[var7];
                  case 2:
                     int var16 = var0[1] & 31;
                     return var16 == 31 ? pL : null;
                  case 3:
                     int var6 = var0[1] & 31;
                     return var6 == 31 ? R.RF : null;
               }
            } else {
               if ((var1 & 56) == 24) {
                  int var13 = (var0[2] & 28) >>> 2;
                  return Qg.q(aH, var13, var0, "SVE2 Integer Multiply - Unpredicated");
               }

               if ((var1 & 56) == 32) {
                  int var12 = (var0[2] & 28) >>> 2;
                  return Qg.q(yc, var12, var0, "SVE Bitwise Shift - Unpredicated");
               }

               if ((var1 & 60) == 40) {
                  int var10 = xK(var0);
                  var10 = var10 == 3 ? 2 : var10;
                  return Qg.q(eb, var10, var0, "SVE address generation");
               }

               if ((var1 & 60) == 44) {
                  int var8 = Uv(var0);
                  if (var8 == 0) {
                     return zj;
                  }

                  if (var8 == 2) {
                     if ((var0[1] & 31) == 0) {
                        return aV;
                     }
                  } else if (var8 == 3 && var0[1] == 32) {
                     return Qo;
                  }
               } else if ((var1 & 48) == 48) {
                  int var9 = (var0[1] & 16) >>> 4;
                  int var15 = (var0[2] & 56) >>> 3;
                  if (var9 == 0 && (var15 & 6) == 0) {
                     return Qg.q(AN, xK(var0), Uv(var0), var0, "SVE saturating inc/dec vector by element count");
                  }

                  if (var9 == 0 && var15 == 4) {
                     if (Dw(var0) == 0) {
                        return Qg.q(YR, xK(var0), var0, "SVE element count");
                     }
                  } else {
                     if (var9 == 1 && var15 == 0) {
                        return Qg.q(fN, xK(var0), (var0[2] & 4) >>> 2, var0, "SVE inc/dec vector by element count");
                     }

                     if (var9 == 1 && var15 == 4) {
                        return Qg.q(GH, xK(var0), (var0[2] & 4) >>> 2, var0, "SVE inc/dec register by element count");
                     }

                     if ((var15 & 6) == 6) {
                        return Qg.q(ou, xK(var0), (var0[1] & 16) >>> 2 | Uv(var0), var0, "SVE saturating inc/dec register by element count");
                     }
                  }
               }
            }
         }

         return null;
      }
   }

   private static Je za(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 56) == 0) {
         int var2 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
         if (var2 == 2) {
            int var13 = (var0[1] & 64) >>> 6;
            return Ua[var13];
         }

         int var3 = (var0[1] & 64) >>> 6;
         if (var3 == 0) {
            return jT;
         }
      } else if ((var1 & 48) == 0) {
         switch (var1 & 7) {
            case 0:
               return AD;
            case 1:
               int var10 = xK(var0);
               if ((var10 & 2) == 0 && (var10 != 1 || (var0[1] & 16) == 0)) {
                  return ZY[var10];
               }

               return null;
            case 2:
               return Cw;
            case 3:
               return bH;
            case 4:
               return sE;
            case 5:
               return rw;
            case 6:
               int var9 = (var0[1] & 24) >>> 3;
               int var14 = var0[1] & 7;
               int var4 = (var0[2] & 2) >>> 1;
               int var5 = oW(var0);
               if (var9 == 0) {
                  if (var14 == 0) {
                     return BR;
                  }

                  if (var14 == 4) {
                     return wh;
                  }
               } else if (var9 == 1) {
                  if ((var14 & 1) == 0 && var5 == 0) {
                     int var21 = xK(var0);
                     int var22 = (var0[1] & 6) >>> 1;
                     if (var21 == 0 && var22 == 0) {
                        return null;
                     }

                     int var23 = k.q((long)(var21 << 2 | var22));
                     return iY[var23];
                  }

                  if ((var14 & 1) == 1 && var4 == 0) {
                     int var6 = xK(var0);
                     int var7 = (var0[1] & 6) >>> 1;
                     if (var6 == 0 && var7 == 0) {
                        return null;
                     }

                     int var8 = k.q((long)(var6 << 2 | var7));
                     return pU[var8];
                  }
               } else if (var9 == 2) {
                  if (var14 < 4) {
                     return oM[var0[1] & 3];
                  }

                  if (var14 == 4) {
                     return BQ;
                  }
               } else if (var9 == 3 && var14 == 0) {
                  return bc;
               }
               break;
            default:
               return null;
         }
      } else if ((var1 & 56) == 16) {
         int var11 = xK(var0);
         int var15 = var0[1] & 31;
         int var17 = (var0[2] & 14) >>> 1;
         int var19 = oW(var0);
         if (var19 == 0) {
            if (var11 == 0 && (var15 & 30) == 16 && var17 == 0) {
               return Qp[var0[1] & 3];
            }

            if ((var15 & 16) == 0 && (var17 & 1) == 0) {
               return ra;
            }

            if (var15 == 20 && var17 == 0) {
               return EP;
            }
         }
      } else {
         if ((var1 & 56) == 24) {
            return sL;
         }

         if ((var1 & 48) == 32) {
            int var12 = (var0[1] & 16) >>> 4;
            int var16 = (var0[1] & 14) >>> 1;
            int var18 = var0[1] & 1;
            int var20 = (var0[2] & 32) >>> 5;
            if (var12 == 0) {
               if (var16 == 0) {
                  return v[var20 << 1 | var18];
               }

               if (var16 == 1 && var20 == 0) {
                  return hN[var18];
               }

               if ((var16 & 6) == 2 && var20 == 0) {
                  return Rs[var0[1] & 3];
               }

               if (var16 == 4) {
                  return Bk[var20 << 1 | var18];
               }

               if (var16 == 5 && var20 == 0) {
                  return Zt[var18];
               }

               if (var16 == 6 && var20 == 0) {
                  return lv[var18];
               }

               if (var16 == 7 && var20 == 0 && var18 == 0 && xK(var0) == 0) {
                  return qx;
               }
            } else if (var16 == 0 && var20 != 0) {
               return HC[var18];
            }
         } else if ((var1 & 48) == 48) {
            return mo;
         }
      }

      return null;
   }

   private static Je lm(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      if ((var1 & 2) == 0) {
         return var2 < 16 ? zz(var0) : JY(var0);
      } else {
         return var2 < 16 ? HF(var0) : LK(var0);
      }
   }

   private static Je zz(byte[] var0) {
      int var1 = (var0[2] & 64) >>> 6;
      if (var1 == 0) {
         int var2 = (var0[2] & 128) >>> 5 | (var0[2] & 32) >>> 4 | oW(var0);
         return (var2 & 6) == 2 ? jP : Sk;
      } else {
         return Oy;
      }
   }

   private static Je JY(byte[] var0) {
      return yS;
   }

   private static Je HF(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 62) >>> 1;
      int var2 = (var0[2] & 252) >>> 2;
      if ((var2 & 16) == 0) {
         int var3 = (var0[2] & 128) >>> 5 | (var0[2] & 32) >>> 4 | oW(var0);
         if (var3 < 6) {
            return QF;
         }
      } else {
         if ((var1 & 8) == 0 && (var2 & 48) == 16) {
            int var11 = xK(var0) << 2 | var0[2] & 2 | oW(var0);
            return hE[var11];
         }

         if ((var1 & 8) == 0 && (var2 & 48) == 48) {
            if ((var0[2] & 2) == 0) {
               int var10 = xK(var0) << 1 | oW(var0);
               return Qg.q(QH, var10, var0, "SVE propagate break from previous partition");
            }
         } else if ((var1 & 8) == 8 && (var2 & 48) == 16) {
            int var9 = (var0[1] & 128) >>> 7;
            int var12 = var0[1] & 15;
            int var13 = (var0[2] & 2) >>> 1;
            int var14 = oW(var0);
            if (var9 == 0 && var12 == 8 && var13 == 0 && var14 == 0) {
               return lh[xK(var0) & 1];
            }

            if (var12 == 0 && var13 == 0) {
               return DD[xK(var0)];
            }
         } else if ((var1 & 8) == 8 && (var2 & 48) == 48) {
            int var8 = var0[1] & 15;
            int var4 = (var0[2] & 56) >>> 3;
            int var5 = (var0[2] & 6) >>> 1;
            int var6 = (var0[2] & 1) << 3 | (var0[3] & 224) >>> 5;
            int var7 = oW(var0);
            if (var8 == 0 && (var5 & 1) == 0 && var7 == 0) {
               if (xK(var0) == 1 && (var0[3] & 15) == 0) {
                  return KW;
               }
            } else if (var8 == 8 && var4 == 0 && var5 == 0 && var7 == 0) {
               if (xK(var0) == 1) {
                  return sZ;
               }
            } else if (var8 == 8 && var4 == 4 && var5 == 2 && var6 == 0 && var7 == 0) {
               if (xK(var0) == 0) {
                  return Ab;
               }
            } else if (var8 == 8 && var4 == 6 && var5 == 0 && var7 == 0) {
               if ((xK(var0) & 2) == 0) {
                  return bD;
               }
            } else {
               if (var8 == 9 && var4 == 0 && var5 == 2 && var7 == 0) {
                  return Rf;
               }

               if (var8 == 9 && var4 == 6 && var5 == 0 && var6 == 0 && var7 == 0) {
                  if (xK(var0) == 0) {
                     return pi;
                  }
               } else if ((var8 & 14) == 8 && var4 == 4 && var5 < 2 && var7 == 0) {
                  return xY;
               }
            }
         }
      }

      return null;
   }

   private static Je LK(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 48) == 0) {
         int var2 = (var0[2] & 48) >>> 4;
         int var3 = Uv(var0);
         int var4 = var0[3] & 15;
         if (var2 < 2) {
            return SW;
         }

         if (var2 == 2 && var3 == 0 && var4 == 0) {
            if ((xK(var0) & 2) != 0) {
               return uk;
            }
         } else if (var2 == 3 && var3 == 0) {
            return UH;
         }
      } else if ((var1 & 48) == 16) {
         int var7 = oW(var0);
         if (var7 != 0) {
            int var10 = var0[1] & 31;
            int var15 = (var0[2] & 56) >>> 3;
            if ((var15 & 2) == 0) {
               return jR;
            }

            if ((var15 & 4) == 0) {
               return kc;
            }

            if (var15 == 6 && var10 == 0) {
               int var5 = var0[2] & 7;
               if (var5 < 4) {
                  return jZ;
               }

               if (var5 < 6) {
                  return pX;
               }
            } else if (var15 == 7 && var10 == 0 && (var0[2] & 7) == 0 && (var0[3] & 232) == 0) {
               return ch;
            }

            return null;
         }

         if ((var0[2] & 2) == 0) {
            return Kw;
         }
      } else if ((var1 & 48) == 48) {
         int var8 = (var0[1] & 24) >>> 3;
         int var11 = var0[1] & 1;
         if (var8 == 0) {
            return iV[var0[1] & 7];
         }

         if (var8 == 1) {
            return (var0[2] & 32) == 0 ? Qg.q(np, var0[1] & 7, var0, "SVE integer min/max immediate (unpredicated)") : null;
         }

         if (var8 == 2) {
            return (var0[2] & 32) == 0 ? Qg.q(Vu, var0[1] & 7, var0, "SVE integer multiply immediate (unpredicated)") : null;
         }

         if (var8 == 3 && var11 == 0) {
            if ((var0[1] & 6) == 0) {
               return EC;
            }
         } else if (var8 == 3 && var11 == 1 && (var0[1] & 6) == 0 && (var0[2] & 32) == 0) {
            return fM;
         }
      } else {
         int var9 = (var0[1] & 62) >>> 1;
         if ((var9 & 28) == 16) {
            int var12 = (var0[2] & 56) >>> 3;
            int var16 = (var0[2] & 2) >>> 1;
            int var19 = var0[1] & 7;
            if (var16 == 0) {
               if (var19 == 0) {
                  return HW;
               }
            } else if (var12 == 0 && var19 == 0) {
               return Tn;
            }
         } else {
            if ((var9 & 28) == 20 && (var1 & 60) == 32) {
               int var14 = (var0[1] & 4) >>> 1 | (var0[2] & 8) >>> 3;
               int var18 = (var0[1] & 3) << 2 | (var0[2] & 6) >>> 1;
               return Qg.q(yZ, var14, var18, var0, "SVE Inc/Dec by Predicate Count");
            }

            if ((var9 & 28) == 20 && (var1 & 60) == 36) {
               int var13 = var0[1] & 7;
               int var17 = (var0[2] & 14) >>> 1;
               int var20 = (var0[2] & 1) << 3 | (var0[3] & 224) >>> 5;
               int var6 = var0[3] & 31;
               if (var13 == 0 && var17 == 0 && var6 == 0) {
                  if (xK(var0) == 0) {
                     return SD;
                  }
               } else if (var13 == 4 && var17 == 0 && var20 == 0 && var6 == 0 && xK(var0) == 0) {
                  return bA;
               }
            }
         }
      }

      return null;
   }

   private static Je io(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      if ((var1 & 2) == 0) {
         return var2 < 16 ? qa(var0) : Hk(var0);
      } else {
         return var2 < 16 ? Me(var0) : PV(var0);
      }
   }

   private static Je qa(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 32) == 0) {
         int var6 = (var0[2] & 124) >>> 2;
         if (var6 < 4) {
            return Xh[var6];
         } else if (var6 < 8) {
            return nc;
         } else {
            return var6 < 16 ? vg[(var6 & 4) >>> 2] : Qg.q(lq, var6 & 15, var0, "SVE Integer Multiply-Add - Unpredicated");
         }
      } else {
         if ((var1 & 16) == 0) {
            int var2 = (var0[1] & 30) >>> 1;
            int var3 = (var0[2] & 32) >>> 5;
            if (var3 == 0) {
               if (var2 < 8) {
                  return zN;
               }

               if (var2 < 12) {
                  return Tc;
               }

               return hj;
            }

            if (var2 == 2) {
               return wK[var0[1] & 1];
            }

            if ((var2 & 10) == 0) {
               return Qg.q(SH, (var0[1] & 3) << 1 | (var0[1] & 8) >>> 3, var0, "SVE2 integer unary operations (predicated)");
            }

            if ((var2 & 12) == 8) {
               return Qg.q(Ax, var0[1] & 7, var0, "SVE2 integer unary operations (predicated)");
            }
         } else {
            if ((var1 & 8) != 0) {
               int var5 = (var0[2] & 28) >>> 2;
               return Qg.q(UC, var5, var0, "SVE permute vector elements (quadwords)");
            }

            if ((var1 & 4) != 0) {
               return qP[Uv(var0)];
            }

            if ((var1 & 2) == 0) {
               return ex[Dw(var0)];
            }

            int var4 = xK(var0);
            if (var4 == 0) {
               return me[Dw(var0)];
            }

            if (var4 == 2) {
               return b[Dw(var0)];
            }
         }

         return null;
      }
   }

   private static Je Hk(byte[] var0) {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 1) {
         case 0:
            int var14 = xK(var0);
            if (var14 < 2) {
               return null;
            }

            return LM[(var14 & 1) << 1 | Dw(var0)];
         case 1:
            int var13 = xK(var0) << 1 | Dw(var0);
            return RB[var13 < 2 ? var13 + 2 : var13];
         case 2:
            int var12 = xK(var0) << 1 | Dw(var0);
            return PG[var12 < 2 ? var12 + 2 : var12];
         case 3:
            int var11 = xK(var0);
            if (var11 >= 2 && var11 != 3) {
               return rU[Dw(var0)];
            }

            return null;
         case 4:
         case 5:
         case 6:
         case 7:
            int var10 = xK(var0);
            if (var10 < 2) {
               return null;
            }

            return ln[(var10 & 1) << 2 | (var0[2] & 16) >>> 3 | Dw(var0)];
         case 8:
         case 9:
            int var9 = xK(var0);
            if (var9 < 2) {
               return null;
            }

            return NE[var9 & 1];
         case 10:
         case 11:
         default:
            return null;
         case 12:
         case 13:
            int var8 = xK(var0);
            if (var8 < 2) {
               return null;
            }

            return rv[var8 & 1];
         case 14:
         case 15:
            int var7 = xK(var0);
            if (var7 < 2) {
               return null;
            }

            return VL[var7 & 1];
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
            int var6 = xK(var0);
            if (var6 < 2) {
               return null;
            }

            return if[(var6 & 1) << 3 | (var0[2] & 48) >>> 3 | Dw(var0)];
         case 24:
         case 25:
         case 26:
         case 27:
            int var5 = xK(var0);
            if (var5 < 2) {
               return null;
            }

            return SB[(var5 & 1) << 2 | (var0[2] & 16) >>> 3 | Dw(var0)];
         case 28:
         case 29:
            int var4 = xK(var0);
            if (var4 < 2) {
               return null;
            }

            return HV[(var4 & 1) << 1 | Dw(var0)];
         case 30:
            int var3 = xK(var0) << 1 | Dw(var0);
            return EY[var3 < 2 ? var3 + 2 : var3];
         case 31:
            if ((var1 & 1) == 0) {
               int var2 = xK(var0);
               return Qm[var2 == 0 ? 1 : var2];
            } else {
               return null;
            }
      }
   }

   private static Je Me(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 4) {
         case 2:
            int var6 = (var0[2] & 60) >>> 2;
            if (var6 == 6) {
               return Qg.q(zv, xK(var0), var0, "SVE integer matrix multiply accumulate");
            } else {
               if ((var6 & 12) == 8 && (xK(var0) & 2) != 0) {
                  return null;
               }

               return Qg.q(FV, var6, var0, "SVE Misc");
            }
         case 3:
            int var5 = (var0[2] & 60) >>> 2;
            int var4 = var5 >>> 1;
            if (var4 == 2) {
               return Qg.q(Jy, xK(var0) & 2 | Dw(var0), var0, "SVE2 integer add/subtract long with carry");
            } else {
               if (var4 == 3) {
                  if ((var0[1] & 30) != 0) {
                     return null;
                  }

                  var5 = var5 & 14 | var0[1] & 1;
               }

               return Qg.q(XE, var5, var0, "SVE Misc");
            }
         default:
            int var2 = (var0[2] & 96) >>> 5;
            if (var2 < 2) {
               return nz;
            } else if (var2 == 2) {
               return Pt;
            } else if (var2 == 3) {
               int var3 = (var0[2] & 28) >>> 2;
               return (var3 & 6) == 2 && xK(var0) == 0 ? rf[var3 & 1] : Qg.q(kt, var3, var0, "SVE2 integer multiply long");
            } else {
               return null;
            }
      }
   }

   private static Je PV(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 3) {
         case 4:
            return Hw[oW(var0)];
         case 5:
            int var8 = (var0[2] & 28) >>> 2;
            if (var8 == 1 && (var0[1] & 64) == 0) {
               return null;
            }

            return Qg.q(hC, var8, var0, "SVE2 Histogram Computation (Segment) and Lookup Table");
         case 6:
            return sC;
         case 7:
            int var7 = var0[1] & 31;
            int var9 = (var0[2] & 24) >>> 3;
            int var10 = (var0[2] & 3) << 3 | (var0[3] & 224) >>> 5;
            if (var9 == 0) {
               if (var7 == 0 && var10 == 0) {
                  if (xK(var0) == 0) {
                     return yY[Dw(var0)];
                  }
               } else if ((var7 & 30) == 2 && xK(var0) == 0) {
                  return Qg.q(Uu, (var0[1] & 1) << 1 | Dw(var0), var0, "SVE2 crypto destructive binary operations");
               }
            } else if (var9 == 2 && xK(var0) == 0) {
               return KQ[Dw(var0)];
            }

            return null;
         default:
            int var2 = (var0[1] & 128) >>> 7;
            int var3 = var0[1] & 7;
            int var4 = (var0[2] & 96) >>> 5;
            int var5 = Dw(var0);
            int var6 = (var0[3] & 32) >>> 5;
            if (var4 < 2) {
               if (var2 == 0) {
                  return uP;
               }

               if (var5 == 0 && var6 == 0 && (var0[1] & 80) == 16 && (var0[2] & 8) == 8) {
                  return dm;
               }
            } else if (var4 == 2) {
               if (var2 == 0 && var3 == 0) {
                  return Zg;
               }

               if (var2 == 0 && var3 == 1 && var5 == 0 && var6 == 0 && (var0[1] & 88) == 16) {
                  return Nh;
               }
            } else if (var4 == 3) {
               return aj;
            }

            return null;
      }
   }

   private static Je oQ(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      if ((var1 & 2) == 0) {
         return var2 < 16 ? xW(var0) : KT(var0);
      } else {
         return var2 < 16 ? Gf(var0) : Ef(var0);
      }
   }

   private static Je xW(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      int var2 = (var0[1] & 30) >>> 1;
      if ((var1 & 32) == 0) {
         return BV;
      } else {
         if ((var1 & 24) == 0) {
            if (var2 == 0) {
               return mW;
            }

            if ((var2 & 12) == 8) {
               return XZ;
            }
         } else if ((var1 & 24) == 8) {
            if ((var2 & 14) == 4) {
               return Qg.q(Dq, xK(var0), var0[1] & 3, var0, "SVE floating-point convert precision odd elements");
            }

            if ((var2 & 12) == 8) {
               return Qg.q(ed, var0[1] & 7, var0, "SVE floating-point recursive reduction (quadwords)");
            }
         }

         return null;
      }
   }

   private static Je KT(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      int var2 = xK(var0);
      switch (var1 >>> 2) {
         case 0:
            int var3 = var2 << 2 | Uv(var0);
            return Qg.q(Yi, var3 < 4 ? var3 + 4 : var3, var0, "SVE floating-point multiply-add (indexed)");
         case 1:
            if (var2 < 2) {
               return null;
            }

            return Et[var2 & 1];
         case 2:
            if ((var1 & 1) == 0) {
               if ((var0[2] & 8) != 0) {
                  return wf;
               }

               return ZC[var2 == 0 ? 1 : var2];
            } else {
               if ((var1 & 3) == 1) {
                  return var2 == 0 ? Ya : Ur;
               }

               return null;
            }
         case 3:
         case 7:
         case 13:
         default:
            return null;
         case 4:
         case 6:
            if ((var2 & 2) != 0) {
               return Qg.q(Ha, (var2 & 1) << 2 | (var0[2] & 32) >>> 4 | (var0[2] & 4) >>> 2, var0, "SVE floating-point multiply-add long (indexed)");
            } else {
               if ((var0[2] & 32) == 0) {
                  return Qg.q(Dm, var2 & 1, (var0[2] & 12) >>> 2, var0, "SVE BFloat16 floating-point dot product (indexed)");
               }

               return null;
            }
         case 5:
            if ((xK(var0) & 1) == 0) {
               return RC;
            }

            return null;
         case 8:
         case 9:
         case 10:
         case 11:
            if ((var1 & 6) == 0) {
               if ((var2 & 2) != 0) {
                  return Qg.q(ZF, (var2 & 1) << 2 | (var0[2] & 32) >>> 4 | (var0[2] & 4) >>> 2, var0, "SVE floating-point multiply-add long");
               }

               if ((var0[2] & 32) == 0) {
                  return Qg.q(nE, (var2 & 1) << 1 | (var0[2] & 4) >>> 2, var0, "SVE BFloat16 floating-point dot product");
               }
            }

            if ((var1 & 3) == 2 && (xK(var0) & 1) == 0) {
               if ((var2 & 2) == 0) {
                  return Wk;
               }

               if ((var0[2] & 32) == 0) {
                  return iN;
               }
            }

            return null;
         case 12:
            return RH;
         case 14:
            return (var1 & 3) == 1 ? PS[var2] : null;
      }
   }

   private static Je Gf(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      int var2 = xK(var0);
      switch (var1 >>> 3) {
         case 0:
            int var9 = (var0[2] & 28) >>> 2;
            if (xK(var0) == 0 && var9 <= 2) {
               return sN;
            }

            return MR;
         case 1:
            int var8 = (var0[1] & 24) >>> 3;
            switch (var8) {
               case 0:
                  return VY;
               case 1:
                  if ((var1 & 4) != 0) {
                     int var11 = var0[1] & 7;
                     if (var11 < 2 && var2 == 0) {
                        return ym;
                     }

                     if (var11 == 2 && var2 == 0 && (var0[3] & 32) == 0) {
                        return eZ[(var0[2] & 12) >>> 2];
                     }

                     if (var11 >= 6 && (var0[2] & 12) == 0) {
                        return JH;
                     }
                  }

                  return null;
               case 2:
                  if ((var0[1] & 4) == 0) {
                     long var10 = ui.decode(var0);
                     if (var10 <= 4L || (var10 & 1L) == 0L) {
                        return Lc;
                     }
                  }

                  return null;
               case 3:
                  if ((var0[1] & 4) == 0) {
                     return Qg.q(MY, var0[1] & 3, var0, "SVE floating-point serial reduction (predicated)");
                  }

                  return null;
               default:
                  return null;
            }
         case 2:
         case 3:
         case 6:
         case 7:
            long var7 = If.decode(var0);
            if (var7 <= 4L) {
               return El;
            } else {
               if ((var7 & 1L) != 0L) {
                  return pp;
               }

               return null;
            }
         case 4:
            int var6 = (var0[1] & 24) >>> 3;
            if (var6 < 2) {
               int var4 = var0[1] & 15;
               if (xK(var0) == 0 && var4 <= 8) {
                  return Yo;
               } else {
                  if (var4 >= 14) {
                     return dK;
                  }

                  return At;
               }
            } else if (var6 == 2 && (var0[2] & 28) == 0) {
               return Jr;
            } else {
               if (var6 == 3 && DirectEncodedMemoryArea.get(6, 4).decodeInt(var0) == 0) {
                  return oa[var0[1] & 7];
               }

               return null;
            }
         case 5:
            switch ((var0[1] & 28) >>> 2) {
               case 0:
               case 1:
                  if (TX.decode(var0) != 5L) {
                     return Qb;
                  }

                  return null;
               case 2:
                  return Qg.q(Q, var2, var0[1] & 3, var0, "SVE floating-point convert precision");
               case 3:
                  return Qg.q(tk, var0[1] & 3, var0, "SVE floating-point unary operations");
               case 4:
               case 5:
                  return Qg.q(ON, var2, var0[1] & 7, var0, "SVE integer convert to floating-point");
               case 6:
               case 7:
                  int var3 = var0[1] & 7;
                  if (var2 == 0 && (var3 & 1) == 0) {
                     var3 = 0;
                  }

                  return Qg.q(hb, var2, var3, var0, "SVE floating-point convert to integer");
               default:
                  return null;
            }
         default:
            return null;
      }
   }

   private static Je Ef(byte[] var0) {
      int var1 = (var0[2] & 128) >>> 7;
      if (var1 == 0) {
         return xK(var0) == 0 && (var0[2] & 64) == 0 ? Cj : Kd;
      } else {
         return Lw;
      }
   }
}
