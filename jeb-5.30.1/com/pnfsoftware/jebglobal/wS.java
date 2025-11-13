package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class wS implements yu {
   private static final IEncodedMemoryArea Hk = DirectEncodedMemoryArea.get(22, 2);
   private static final IEncodedMemoryArea Me = DirectEncodedMemoryArea.get(30, 1);
   public static final IEncodedMemoryArea LK = DirectEncodedMemoryArea.get(22, 1);
   private static final de PV = new de.Nt(Me, "", "2");
   private static final de oQ = new de.Nt(Me, "B", "T");
   private static final Ef xW = XD.q(0, kE.Uv);
   private static final Ef KT = XD.q(5, kE.Uv);
   private static final Ef Gf = XD.q(16, kE.Uv);
   private static final Ef Ef = XD.q(5, kE.xK);
   private static final Ef cC = XD.q(16, kE.xK);
   private static final Ef sH = XD.q(0, kE.Dw);
   private static final Ef CE = XD.q(5, kE.Dw);
   private static final Ef wF = XD.q(0, kE.RF);
   private static final Ef If = XD.q(5, kE.RF);
   private static final Ef Dz = XD.q(0, kE.oW);
   private static final Dm mI = new kT(new dD(XD.Uv, 1, null, 2, 3));
   private static final Dm jq = new kT(1, new dD(XD.Uv, null, 2, 3, null));
   private static final Dm ui = new kT(1, new dD(XD.Uv, 1, null, null, 4));
   private static final Dm TX = new kT(1, new dD(XD.Uv, 1, 2, 3, null));
   private static final Dm Rr = new WX(new dD(XD.Uv, 0, 1, 2, null));
   private static final Dm EB = new ew(new dD(XD.Uv, 1, 2, 3, null));
   private static final Dm Xo = new ew(new dD(XD.Uv, 0, null, null, 3));
   private static final Ef Bu = XD.q(0, kT.xW);
   private static final Ef IN = XD.q(5, kT.xW);
   private static final Ef rL = XD.q(16, kT.xW);
   private static final Ef eJ = XD.q(0, kT.KT);
   private static final Ef YN = XD.q(5, kT.KT);
   private static final Ef Rv = XD.q(0, kT.Gf);
   private static final Ef zx = XD.q(5, kT.Gf);
   private static final Ef ZT = XD.q(0, kT.Ef);
   private static final Ef Ri = XD.q(5, kT.Ef);
   private static final Ef GY = XD.q(0, kT.cC);
   private static final Ef Wx = XD.q(5, kT.cC);
   private static final Ef AB = XD.q(0, kT.sH);
   private static final Ef CY = XD.q(5, kT.sH);
   private static final Ef WI = XD.q(16, kT.sH);
   private static final Ef Tq = XD.q(0, jq);
   private static final Ef Yp = XD.q(0, mI);
   private static final Ef Gu = XD.q(5, mI);
   private static final Ef nY = XD.q(0, EB);
   private static final Ef lF = XD.q(0, TX);
   private static final Ef nq = XD.q(5, TX);
   private static final Ef NX = XD.q(5, Rr);
   private static final Dm br = kT.wF;
   private static final Dm tW = kT.If;
   private static final Dm ZA = new kT(new dD(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(19, 1), LK), 2, 3, null, 1));
   private static final Dm Ov = new kT(new dD(LK, 2, null));
   private static final Dm Lj = new kT(new dD(LK, null, 2));
   private static final Dm nv = new kT(1, new dD(LK, null, 3));
   private static final Dm LL = new kT(1, new dD(LK, 2, 3));
   private static final IEncodedMemoryArea PQ = EncodedMemoryAreaList.fromBits(21, 22);
   private static final Dm fQ = new kT(new dD(PQ, null, 1, 2, 3));
   private static final Ef fi = XD.q(0, tW);
   private static final Ef bl = XD.q(5, tW);
   private static final Ef jb = XD.q(0, ZA);
   private static final Ef pQ = XD.q(5, ZA);
   private static final Ef kf = Nd.q(new dD(Me, 64, 128));
   private static final Dm GM = new Cr();
   private static final Dm TQ = new cE(1, null);
   private static final Ef Yw = XD.q(0, GM);
   private static final Ef IY = XD.q(0, TQ);
   private static final Ef qR = XD.q(5, GM);
   private static final Ef YA = XD.q(5, TQ);
   private static final Ef fw = new Df(16, Dm.oW, Fo.RF);
   private static final Ef Wp = new Df(16, Dm.nf, Fo.RF);
   private static final IEncodedMemoryArea cY = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(5, 5));
   private static final Ef PY = new go(cY);
   private static final Ef cR = new wS.eo(64, cY);
   private static final Ef eC = new wS.eo(32, cY);
   private static final Ef ND = new wS.eo(16, cY);
   private static final Ef Qu = new cn(PY, cn.gP, DirectEncodedMemoryArea.get(13, 1).shift(3));
   private static final Ef jh = new cn(PY, cn.gP, DirectEncodedMemoryArea.get(13, 2).shift(3));
   private static final Ef Jf = new cn(PY, VirtualEncodedMemoryArea.get(5, 32), EncodedMemoryAreaList.shift(dX.q(DirectEncodedMemoryArea.get(12, 1)), 3));
   static final Ef io = new Wr();
   static final Ef qa = new Mf(var0 -> {
      int var1 = xK(var0) >>> 1;
      if (var1 == 0) {
         return null;
      } else {
         int var2 = DirectEncodedMemoryArea.get(16, 7).decodeInt(var0);
         return var2 - var1;
      }
   });
   private static final go vC = new go(67174400, DirectEncodedMemoryArea.get(13, 2));
   private static final dD of = new dD(Me, 1L, 0L);
   private static dD os = new dD(LK, 0L, 1L);
   private static dD iu = new dD(DirectEncodedMemoryArea.get(20, 3), 1L, 16L);
   private static final dD fn = new dD(Hk, 16L, 1L, 0L, 0L);
   private static final dD ZU = new dD(DirectEncodedMemoryArea.get(19, 1), 0L, 16L);
   private static final dD Sz = new dD(XD.Uv, 1L, 1L, 1280L, 1L);
   private static final dD fq = new dD(Hk, 1L, 1L, 32L, 1L);
   private static final dD mJ = new dD(Hk, 1L, 80L, 64L, 64L);
   private static final dD Bs = new dD(new EncodedMemoryAreaList(Me, Hk), 1L, 80L, 1L, 1L, 1L, 80L, 64L, 1L);
   private static final dD rV = new dD(Hk, 1L, 256L, 1L, 1L);
   private static final dD WX = new dD(Hk, 1L, 1L, 1024L, 1L);
   private static final dD CB = new dD(PQ, 1L, 16L, 0L, 0L);
   private static final dD C = new dD(XD.Uv, 0L, 0L, 0L, 1L);
   private static final Je[] GC = new Je[]{
      new Qg(0, "MOVI", wF, PY),
      new Qg(1, "ORR", wF, PY),
      new Qg(2, "MOVI", wF, jh),
      new Qg(3, "ORR", wF, jh),
      new Qg(4, "MOVI", wF, jh),
      new Qg(5, "ORR", wF, jh),
      new Qg(6, "MOVI", wF, jh),
      new Qg(7, "ORR", wF, jh),
      new Qg(8, "MOVI", sH, PY),
      new Qg(9, "ORR", sH, PY),
      new Qg(10, "MOVI", sH, Qu),
      new Qg(11, "ORR", sH, Qu),
      new Qg(12, "MOVI", wF, Jf),
      new Qg(13, "MOVI", wF, Jf),
      new Qg(14, "MOVI", xW, PY),
      new Qg(15, "FMOV", wF, eC),
      new Qg(0, "MVNI", wF, PY),
      new Qg(1, "BIC", wF, PY),
      new Qg(2, "MVNI", wF, jh),
      new Qg(3, "BIC", wF, jh),
      new Qg(4, "MVNI", wF, jh),
      new Qg(5, "BIC", wF, jh),
      new Qg(6, "MVNI", wF, jh),
      new Qg(7, "BIC", wF, jh),
      new Qg(8, "MVNI", sH, PY),
      new Qg(9, "BIC", sH, PY),
      new Qg(10, "MVNI", sH, Qu),
      new Qg(11, "BIC", sH, Qu),
      new Qg(12, "MVNI", wF, Jf),
      new Qg(13, "MVNI", wF, Jf),
      new Qg(14, "MOVI", Dz, cR),
      new Qg(15, "FMOV", Dz, cR)
   };
   private static final Je KF = new Qg("MOVI", XD.HF, cR);
   private static final Je rk = new Qg("FMOV", sH, ND).q(MX.za);
   private static final Je[] cy = new Je[]{new Qg("AESE", xW, KT), new Qg("AESD", xW, KT), new Qg("AESMC", xW, KT), new Qg("AESIMC", xW, KT)};
   private static final Je[] jk = new Je[]{
      new Qg(0, "SSHR", Yw, qR, io),
      null,
      new Qg(2, "SSRA", Yw, qR, io),
      null,
      new Qg(4, "SRSHR", Yw, qR, io),
      null,
      new Qg(6, "SRSRA", Yw, qR, io),
      null,
      null,
      null,
      new Qg(10, "SHL", Yw, qR, qa),
      null,
      null,
      null,
      new Qg(14, "SQSHL", Yw, qR, qa),
      null,
      new Qg(16, "SHRN", Yw, YA, io).q(PV).q(os),
      new Qg(17, "RSHRN", Yw, YA, io).q(PV).q(os),
      new Qg(18, "SQSHRN", Yw, YA, io).q(PV).q(os),
      new Qg(19, "SQRSHRN", Yw, YA, io).q(PV).q(os),
      new Qg(20, "SSHLL", IY, qR, qa).q(PV).q(os),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(28, "SCVTF", Yw, qR, io).q(iu),
      null,
      null,
      new Qg(31, "FCVTZS", Yw, qR, io).q(iu),
      new Qg(32, "USHR", Yw, qR, io),
      null,
      new Qg(34, "USRA", Yw, qR, io),
      null,
      new Qg(36, "URSHR", Yw, qR, io),
      null,
      new Qg(38, "URSRA", Yw, qR, io),
      null,
      new Qg(40, "SRI", Yw, qR, io),
      null,
      new Qg(42, "SLI", Yw, qR, qa),
      null,
      new Qg(44, "SQSHLU", Yw, qR, qa),
      null,
      new Qg(46, "UQSHL", Yw, qR, qa),
      null,
      new Qg(48, "SQSHRUN", Yw, YA, io).q(PV).q(os),
      new Qg(49, "SQRSHRUN", Yw, YA, io).q(PV).q(os),
      new Qg(50, "UQSHRN", Yw, YA, io).q(PV).q(os),
      new Qg(51, "UQRSHRN", Yw, YA, io).q(PV).q(os),
      new Qg(52, "USHLL", IY, qR, qa).q(PV).q(os),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(60, "UCVTF", Yw, qR, io).q(iu),
      null,
      null,
      new Qg(63, "FCVTZU", Yw, qR, io).q(iu)
   };
   private static final Je[] Cl = new Je[]{new Qg("SXTL", IY, qR).q(PV).q(os), new Qg("UXTL", IY, qR).q(PV).q(os)};
   private static final Je hM = new Qg("FCMLA", GY, Wx, Fo.gO, vC).q(Bs);
   private static final Je[] kv = new Je[]{
      new Qg(0, "FMLAL", wF, Ef, Fo.Dw).q(fq),
      new Qg(1, "FMLA", Yp, Gu, Fo.nf).q(fn),
      new Qg(2, "SMLAL", Tq, Wx, Fo.Uv).q(PV),
      new Qg(3, "SQDMLAL", Tq, Wx, Fo.Uv).q(PV),
      new Qg(4, "FMLSL", wF, Ef, Fo.Dw).q(fq),
      new Qg(5, "FMLS", Yp, Gu, Fo.nf).q(fn),
      new Qg(6, "SMLSL", Tq, Wx, Fo.Uv).q(PV),
      new Qg(7, "SQDMLSL", Tq, Wx, Fo.Uv).q(PV),
      new Qg(8, "MUL", GY, Wx, Fo.Uv),
      new Qg(9, "FMUL", Yp, Gu, Fo.nf).q(fn),
      new Qg(10, "SMULL", Tq, Wx, Fo.Uv).q(PV),
      new Qg(11, "SQDMULL", Tq, Wx, Fo.Uv).q(PV),
      new Qg(12, "SQDMULH", GY, Wx, Fo.Uv),
      new Qg(13, "SQRDMULH", GY, Wx, Fo.Uv),
      new Qg(14, "SDOT", wF, KT, fw).q(Sz),
      null,
      new Qg(16, "MLA", GY, Wx, Fo.Uv),
      hM,
      new Qg(18, "UMLAL", Tq, Wx, Fo.Uv).q(PV),
      hM,
      new Qg(20, "MLS", GY, Wx, Fo.Uv),
      hM,
      new Qg(22, "UMLSL", Tq, Wx, Fo.Uv).q(PV),
      hM,
      new Qg(24, "FMLAL2", wF, Ef, Fo.Dw).q(fq),
      new Qg(25, "FMULX", Yp, Gu, Fo.nf).q(fn),
      new Qg(26, "UMULL", Tq, Wx, Fo.Uv).q(PV),
      null,
      new Qg(0, "FMLSL2", wF, Ef, Fo.Dw).q(fq),
      new Qg(29, "SQRDMLAH", GY, Wx, Fo.Uv).q(QJ.LL),
      new Qg(30, "UDOT", wF, KT, fw).q(Sz),
      new Qg(31, "SQRDMLSH", GY, Wx, Fo.Uv).q(QJ.LL)
   };
   private static final Je[] oS = new Je[]{
      new Qg("SUDOT", wF, KT, fw).q(QJ.ND),
      new Qg("BFDOT", wF, CE, Wp).q(QJ.eC),
      new Qg("USDOT", wF, KT, fw).q(QJ.ND),
      new Qg("BFMLAL", gO, Uv, Fo.oW).q(oQ).q(QJ.eC)
   };
   private static final Je[] FG = new Je[]{
      new Qg(0, "SADDLV", XD.cC, NX),
      new Qg(1, "SMAXV", XD.Gf, NX),
      new Qg(2, "SMINV", XD.Gf, NX),
      new Qg(3, "ADDV", XD.Gf, NX),
      new Qg(0, "UADDLV", XD.cC, NX),
      new Qg(1, "UMAXV", XD.Gf, NX),
      new Qg(2, "UMINV", XD.Gf, NX),
      null
   };
   private static final Je[] Al = new Je[]{
      new Qg("FMAXNMV", XD.gP, CE).q(os, MX.za),
      new Qg("FMAXV", XD.gP, CE).q(os, MX.za),
      new Qg("FMINNMV", XD.gP, CE).q(os, MX.za),
      new Qg("FMINV", XD.gP, CE).q(os, MX.za),
      new Qg("FMAXNMV", XD.lm, If).q(os, of),
      new Qg("FMAXV", XD.lm, If).q(os, of),
      new Qg("FMINNMV", XD.lm, If).q(os, of),
      new Qg("FMINV", XD.lm, If).q(os, of)
   };
   private static final Je[] Kn = new Je[]{
      new Qg(0, "REV64", Rv, zx),
      new Qg(1, "REV16", Bu, IN),
      new Qg(2, "SADDLP", nY, zx),
      new Qg(3, "SUQADD", ZT, Ri),
      new Qg(4, "CLS", Rv, zx),
      new Qg(5, "CNT", Bu, IN),
      new Qg(6, "SADALP", nY, zx),
      new Qg(7, "SQABS", ZT, Ri),
      new Qg(8, "CMGT", ZT, Ri, go.Ri),
      new Qg(9, "CMEQ", ZT, Ri, go.Ri),
      new Qg(10, "CMLT", ZT, Ri, go.Ri),
      new Qg(11, "ABS", ZT, Ri),
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(18, "XTN", Rv, nq).q(PV),
      null,
      new Qg(20, "SQXTN", Rv, nq).q(PV),
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
      new Qg(32, "REV32", eJ, YN),
      null,
      new Qg(34, "UADDLP", nY, zx),
      new Qg(35, "USQADD", ZT, Ri),
      new Qg(36, "CLZ", Rv, zx),
      null,
      new Qg(38, "UADALP", nY, zx),
      new Qg(39, "SQNEG", ZT, Ri),
      new Qg(40, "CMGE", ZT, Ri, go.Ri),
      new Qg(41, "CMLE", ZT, Ri, go.Ri),
      null,
      new Qg(43, "NEG", ZT, Ri),
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(50, "SQXTUN", PV, Rv, nq),
      new Qg(50, "SHLL", PV, lF, zx, new Mf(var0 -> 8 << XD.Uv.decodeInt(var0))).q(C),
      new Qg(52, "UQXTN", PV, Rv, nq),
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
      null
   };
   private static final Je[] vh = new Je[]{
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
      new Qg(16, "FRINTP", fi, bl),
      new Qg(17, "FRINTZ", fi, bl),
      null,
      null,
      null,
      null,
      new Qg(22, "FCVTN", XD.q(0, br), XD.q(5, LL)).q(PV),
      new Qg(23, "FCVTL", XD.q(0, LL), XD.q(5, br)).q(PV),
      new Qg(24, "FRINTN", jb, pQ).q(ZU),
      new Qg(25, "FRINTM", jb, pQ).q(ZU),
      new Qg(26, "FCVTNS", jb, pQ).q(ZU),
      new Qg(27, "FCVTMS", jb, pQ).q(ZU),
      new Qg(28, "FCVTAS", jb, pQ).q(ZU),
      new Qg(29, "SCVTF", jb, pQ).q(ZU),
      new Qg(30, "FRINT32Z", fi, bl).q(QJ.cR),
      new Qg(31, "FRINT64Z", fi, bl).q(QJ.cR),
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
      new Qg(44, "FCMGT", jb, pQ, kf).q(ZU),
      new Qg(45, "FCMEQ", jb, pQ, kf).q(ZU),
      new Qg(46, "FCMLT", jb, pQ, kf).q(ZU),
      new Qg(47, "FABS", jb, pQ).q(ZU),
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(47, "BFCVTN", sH, nf).q(PV).q(QJ.eC),
      null,
      new Qg(56, "FRINTP", jb, pQ).q(ZU),
      new Qg(57, "FRINTZ", jb, pQ).q(ZU),
      new Qg(58, "FCVTPS", jb, pQ).q(ZU),
      new Qg(59, "FCVTZS", jb, pQ).q(ZU),
      new Qg(60, "URECPE", XD.q(0, Ov), XD.q(5, Ov)),
      new Qg(61, "FRECPE", jb, pQ).q(ZU),
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
      new Qg(86, "FCVTXN", XD.q(0, Lj), XD.q(5, nv)).q(PV),
      null,
      new Qg(88, "FRINTA", jb, pQ).q(ZU),
      new Qg(89, "FRINTX", jb, pQ).q(ZU),
      new Qg(90, "FCVTNU", jb, pQ).q(ZU),
      new Qg(91, "FCVTMU", jb, pQ).q(ZU),
      new Qg(92, "FCVTAU", jb, pQ).q(ZU),
      new Qg(93, "UCVTF", jb, pQ).q(ZU),
      new Qg(30, "FRINT32X", fi, bl).q(QJ.cR),
      new Qg(31, "FRINT64X", fi, bl).q(QJ.cR),
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
      new Qg(108, "FCMGE", jb, pQ, kf).q(ZU),
      new Qg(109, "FCMLE", jb, pQ, kf).q(ZU),
      null,
      new Qg(111, "FNEG", jb, pQ).q(ZU),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(121, "FRINTI", jb, pQ).q(ZU),
      new Qg(122, "FCVTPU", jb, pQ).q(ZU),
      new Qg(123, "FCVTZU", jb, pQ).q(ZU),
      new Qg(124, "URSQRTE", XD.q(0, Ov), XD.q(5, Ov)),
      new Qg(125, "FRSQRTE", jb, pQ).q(ZU),
      null,
      new Qg(127, "FSQRT", jb, pQ).q(ZU)
   };
   private static final Je[] Rd = new Je[]{new Qg("MVN", xW, KT), new Qg("RBIT", xW, KT)};
   private static final Ef[] Eq = new Ef[]{Rv, zx, XD.q(16, kT.Gf)};
   private static final Ef[] hP = new Ef[]{ZT, Ri, XD.q(16, kT.Ef)};
   private static final Ef[] wN = new Ef[]{GY, Wx, XD.q(16, kT.cC)};
   private static final Je[] Uc = new Je[]{
      new Qg(0, "SHADD", Eq),
      new Qg(1, "SQADD", hP),
      new Qg(2, "SRHADD", Eq),
      null,
      new Qg(4, "SHSUB", Eq),
      new Qg(5, "SQSUB", hP),
      new Qg(6, "CMGT", hP),
      new Qg(7, "CMGE", hP),
      new Qg(8, "SSHL", hP),
      new Qg(9, "SQSHL", hP),
      new Qg(10, "SRSHL", hP),
      new Qg(11, "SQRSHL", hP),
      new Qg(12, "SMAX", Eq),
      new Qg(13, "SMIN", Eq),
      new Qg(14, "SABD", Eq),
      new Qg(15, "SABA", Eq),
      new Qg(0, "ADD", hP),
      new Qg(1, "CMTST", hP),
      new Qg(2, "MLA", Eq),
      new Qg(4, "MUL", Eq),
      new Qg(4, "SMAXP", Eq),
      new Qg(5, "SMINP", Eq),
      new Qg(6, "SQDMULH", wN),
      new Qg(7, "ADDP", hP),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new Qg(0, "UHADD", Eq),
      new Qg(1, "UQADD", hP),
      new Qg(2, "URHADD", Eq),
      null,
      new Qg(4, "UHSUB", Eq),
      new Qg(5, "UQSUB", hP),
      new Qg(6, "CMHI", hP),
      new Qg(7, "CMHS", hP),
      new Qg(8, "USHL", hP),
      new Qg(9, "UQSHL", hP),
      new Qg(10, "URSHL", hP),
      new Qg(11, "UQRSHL", hP),
      new Qg(12, "UMAX", Eq),
      new Qg(13, "UMIN", Eq),
      new Qg(14, "UABD", Eq),
      new Qg(15, "UABA", Eq),
      new Qg(0, "SUB", hP),
      new Qg(1, "CMEQ", hP),
      new Qg(2, "MLS", Eq),
      new Qg(4, "PMUL", xW, KT, Gf),
      new Qg(4, "UMAXP", Eq),
      new Qg(5, "UMINP", Eq),
      new Qg(6, "SQRDMULH", wN),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
   };
   private static final go TB = new go(67174400, DirectEncodedMemoryArea.get(11, 2));
   private static final go dg = new go(67174400, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 1), VirtualEncodedMemoryArea._1));
   private static final Je hw = new Qg("FCMLA", AB, CY, WI, TB).q(mJ);
   private static final Je gm = new Qg("FCADD", AB, CY, WI, dg).q(mJ);
   private static final Je[] uY = new Je[]{
      null,
      null,
      new Qg(2, "SDOT", wF, KT, Gf).q(Sz),
      new Qg(2, "USDOT", wF, KT, Gf).q(WX),
      new Qg(4, "SMMLA", wF, KT, Gf).q(WX, of),
      new Qg(4, "USMMLA", wF, KT, Gf).q(WX, of),
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
      new Qg(16, "SQRDMLAH", wN).q(QJ.LL),
      new Qg(17, "SQRDMLSH", wN).q(QJ.LL),
      new Qg(18, "UDOT", wF, KT, Gf).q(Sz),
      null,
      new Qg(20, "UMMLA", wF, KT, Gf).q(WX, of),
      null,
      null,
      null,
      hw,
      hw,
      hw,
      hw,
      gm,
      new Qg("BFMMLA", wF, CY, WI).q(rV, of),
      gm,
      null
   };
   private static final Je[] sc = new Je[]{null, new Qg("BFDOT", wF, CY, WI).q(QJ.eC), null, new Qg("BFMLAL", gO, Uv, oW).q(oQ).q(QJ.eC)};
   private static final Ef[] wQ = new Ef[]{XD.q(0, fQ), XD.q(5, fQ), XD.q(16, fQ)};
   private static final Je[] Oj = new Je[]{
      new Qg(0, "FMAXNM", wQ).q(CB),
      new Qg(1, "FMLA", wQ).q(CB),
      new Qg(2, "FADD", wQ).q(CB),
      new Qg(3, "FMULX", wQ).q(CB),
      new Qg(4, "FCMEQ", wQ).q(CB),
      null,
      new Qg(6, "FMAX", wQ).q(CB),
      new Qg(7, "FRECPS", wQ).q(CB),
      new Qg(8, "FMINNM", wQ).q(CB),
      new Qg(9, "FMLS", wQ).q(CB),
      new Qg(10, "FSUB", wQ).q(CB),
      null,
      null,
      null,
      new Qg(14, "FMIN", wQ).q(CB),
      new Qg(15, "FRSQRTS", wQ).q(CB),
      new Qg(0, "FMAXNMP", wQ).q(CB),
      null,
      new Qg(2, "FADDP", wQ).q(CB),
      new Qg(3, "FMUL", wQ).q(CB),
      new Qg(4, "FCMGE", wQ).q(CB),
      new Qg(5, "FACGE", wQ).q(CB),
      new Qg(6, "FMAXP", wQ).q(CB),
      new Qg(7, "FDIV", wQ).q(CB),
      new Qg(8, "FMINNMP", wQ).q(CB),
      null,
      new Qg(10, "FABD", wQ).q(CB),
      null,
      new Qg(12, "FCMGT", wQ).q(CB),
      new Qg(13, "FACGT", wQ).q(CB),
      new Qg(14, "FMINP", wQ).q(CB),
      null
   };
   private static final Ef[] VW = new Ef[]{xW, KT, Gf};
   private static final Je[] ap = new Je[]{
      new Qg(0, "AND", VW),
      new Qg(1, "BIC", VW),
      new Qg(2, "ORR", VW),
      new Qg(3, "ORN", VW),
      new Qg(4, "EOR", VW),
      new Qg(5, "BSL", VW),
      new Qg(6, "BIT", VW),
      new Qg(7, "BIF", VW)
   };
   private static final Je RL = new Qg("MOV", xW, KT);
   private static final Je[] hy = new Je[]{
      new Qg(0, "FMLAL", wF, Ef, cC).q(MX.lm),
      null,
      new Qg(2, "FMLSL", wF, Ef, cC).q(MX.lm),
      null,
      new Qg(0, "FMLAL2", wF, Ef, cC).q(MX.lm),
      null,
      new Qg(2, "FMLSL2", wF, Ef, cC).q(MX.lm),
      null
   };
   private static final Je[] Xi = new Je[]{
      null, new Qg(1, "UZP1", hP), new Qg(2, "TRN1", hP), new Qg(3, "ZIP1", hP), null, new Qg(5, "UZP2", hP), new Qg(6, "TRN2", hP), new Qg(7, "ZIP2", hP)
   };
   private static final Je Ag = new Qg("MOV", kA.q(0, 17), kA.q(5, 11));
   private static final Je rp = new Qg("MOV", kA.q(0, 17), kA.q);
   private static final Je[] CW = new Je[]{new Qg("DUP", XD.q(0, kT.Dz), kA.q(5, 17)), new Qg("DUP", XD.q(0, kT.Dz), kA.q)};
   private static final Je[] qm = new Je[]{new Qg("SMOV", YH.TX, kA.q(5, 17)), new Qg("UMOV", YH.TX, kA.q(5, 17))};
   private static final Je LR = new Qg("MOV", YH.TX, kA.q(5, 17));
   private static final Ef[] Uz = new Ef[]{lF, zx, XD.q(16, kT.Gf)};
   private static final Ef[] dF = new Ef[]{lF, nq, XD.q(16, kT.Gf)};
   private static final Ef[] kk = new Ef[]{Rv, nq, XD.q(16, TX)};
   private static final Ef[] Rc = new Ef[]{Tq, Wx, XD.q(16, kT.cC)};
   private static final Je[] jz = new Je[]{
      new Qg(0, "SADDL", PV, Uz),
      new Qg(1, "SADDW", PV, dF),
      new Qg(2, "SSUBL", PV, Uz),
      new Qg(3, "SSUBW", PV, dF),
      new Qg(4, "ADDHN", PV, kk),
      new Qg(5, "SABAL", PV, Uz),
      new Qg(6, "SUBHN", PV, kk),
      new Qg(7, "SABDL", PV, Uz),
      new Qg(8, "SMLAL", PV, Uz),
      new Qg(9, "SQDMLAL", PV, Rc),
      new Qg(10, "SMLSL", PV, Uz),
      new Qg(11, "SQDMLSL", PV, Rc),
      new Qg(12, "SMULL", PV, Uz),
      new Qg(13, "SQDMULL", PV, Rc),
      new Qg(14, "PMULL", PV, XD.q(0, ui), XD.q(5, Xo), XD.q(16, Xo)),
      null,
      new Qg(16, "UADDL", PV, Uz),
      new Qg(17, "UADDW", PV, dF),
      new Qg(18, "USUBL", PV, Uz),
      new Qg(19, "USUBW", PV, dF),
      new Qg(20, "RADDHN", PV, kk),
      new Qg(21, "UABAL", PV, Uz),
      new Qg(22, "RSUBHN", PV, kk),
      new Qg(23, "UABDL", PV, Uz),
      new Qg(24, "UMLAL", PV, Uz),
      null,
      new Qg(26, "UMLSL", PV, Uz),
      null,
      new Qg(28, "UMULL", PV, Uz),
      null,
      null,
      null
   };
   private static final Je MT = new Qg("EXT", Bu, IN, rL, new go(DirectEncodedMemoryArea.get(11, 4)));
   private static final Ef[] bY = new Ef[]{xW, hc.q(5, DirectEncodedMemoryArea.get(13, 2), Dm.gO), Gf};
   private static final Je[] LS = new Je[]{new Qg("TBL", bY), new Qg("TBX", bY)};

   private static int RF(byte[] var0) {
      int var1 = (var0[1] & 120) >>> 3;
      if (var1 == 1) {
         return 0;
      } else if ((var1 & 14) == 2) {
         return 1;
      } else if ((var1 & 12) == 4) {
         return 2;
      } else {
         return (var1 & 8) == 8 ? 3 : -1;
      }
   }

   private static int xK(byte[] var0) {
      return 1 << 4 + RF(var0);
   }

   public static Je q(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 240) >>> 4;
      int var2 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var3 = (var0[1] & 120) >>> 3;
      int var4 = (var0[1] & 6) >>> 1;
      int var5 = (var0[2] & 252) >>> 2;
      int var6 = (var0[1] & 7) << 6 | (var0[2] & 252) >>> 2;
      int var7 = (var0[1] & 192) >>> 6;
      int var8 = (var0[0] & 32) >>> 5;
      int var9 = (var0[0] & 64) >>> 6;
      if (var2 >= 2) {
         if ((var5 & 1) == 0) {
            int var29 = (var0[2] & 240) >>> 4;
            return var8 == 0 && var29 == 15
               ? Qg.q(oS, var7, var0, "Advanced SIMD vector x indexed element")
               : Qg.q(kv, var8 << 4 | var29, var0, "Advanced SIMD vector x indexed element");
         } else if (var2 == 2) {
            if (var3 == 0) {
               int var20 = (var0[0] & 32) >>> 5;
               int var28 = (var0[2] & 240) >>> 4;
               int var33 = (var0[2] & 8) >>> 3;
               if (var9 == 0 && var20 != 0 && var28 == 15) {
                  return Qg.q(var0, "Advanced SIMD modified immediate");
               } else if (var33 != 0) {
                  return var28 == 15 ? rk : Qg.q(var0, "Advanced SIMD modified immediate");
               } else {
                  return var9 == 0 && var20 != 0 && var28 == 14 ? KF : GC[var20 << 4 | var28];
               }
            } else {
               int var19 = (var0[2] & 248) >>> 3;
               int var27 = (var0[1] & 120) >>> 3;
               if (var19 == 20) {
                  int var32 = var0[1] & 7;
                  if (k.Dw(var27) == 1 && var32 == 0) {
                     return Cl[var8];
                  }
               }

               return Qg.q(jk, var8 << 5 | var19, var0, "Advanced SIMD shift by immediate");
            }
         } else {
            return Qg.q(var0, "Advanced SIMD vector");
         }
      } else if ((var3 & 4) == 0) {
         if ((var1 & 11) == 0) {
            if ((var6 & 35) == 0) {
               if (var7 == 0) {
                  int var17 = (var0[2] & 16) >>> 4;
                  return LS[var17];
               }

               return Qg.q(var0, "Advanced SIMD table lookup");
            }

            if ((var6 & 35) == 2) {
               int var16 = (var0[2] & 112) >>> 4;
               return Qg.q(Xi, var16, var0, "Advanced SIMD permute");
            }
         } else if ((var6 & 33) == 0) {
            if (var7 != 0 || var9 == 0 && (var0[2] & 64) != 0) {
               return Qg.q(var0, "Advanced SIMD extract");
            }

            return MT;
         }

         if (var2 == 0 && var3 < 4 && (var6 & 33) == 1) {
            int var26 = var0[1] & 31;
            int var31 = (var0[2] & 120) >>> 3;
            if ((var26 & 15) != 0) {
               if (var8 == 0) {
                  if ((var31 & 14) == 0) {
                     return CW[var31];
                  }

                  if (var9 == 1 && var31 == 3) {
                     return rp;
                  }

                  if ((var31 & 13) == 5 && (var31 != 7 || var9 == 0 || (var26 & 15) == 8)) {
                     if (var31 == 7) {
                        if ((var26 & 15) == 8 || (var26 & 7) == 4) {
                           return LR;
                        }

                        if (var9 == 0 && (var26 & 15) == 8) {
                           return Qg.q(var0, "Advanced SIMD copy UMOV");
                        }
                     }

                     return qm[var31 >>> 1 & 1];
                  }
               } else if (var9 == 1) {
                  return Ag;
               }
            }
         } else {
            if (var3 >= 8 && (var6 & 49) == 1) {
               int var18 = DirectEncodedMemoryArea.get(11, 3).decodeInt(var0);
               return Qg.q(Oj, var8 << 4 | (var7 & 2) << 2 | var18, var0, "Advanced SIMD three same (FP16)");
            }

            if ((var6 & 33) == 33) {
               int var25 = (var0[2] & 120) >>> 3;
               if (var8 != 0 && var25 == 15) {
                  return Qg.q(sc, var7, var0, "Advanced SIMD three-register extension");
               }

               return Qg.q(uY, var8 << 4 | var25, var0, "Advanced SIMD three-register extension");
            }
         }

         return Qg.q(var0, "Advanced SIMD copy");
      } else if ((var5 & 1) != 0) {
         int var24 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
         if (var24 == 3) {
            int var30 = var8 << 2 | var7;
            int var13 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
            int var14 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var0);
            return var30 == 2 && var13 == var14 ? RL : ap[var30];
         } else if ((var24 != 29 || var8 != 0) && (var24 != 25 || var8 == 0)) {
            return MathUtil.betweenInclusive(var24, 24L, 31L)
               ? Qg.q(Oj, var8 << 4 | (var7 & 2) << 2 | var24 & 7, var0, "Advanced SIMD three same")
               : Qg.q(Uc, var8 << 5 | var24, var0, "Advanced SIMD three same");
         } else {
            return Qg.q(hy, var8 << 2 | var7, var0, "Advanced SIMD three same");
         }
      } else if ((var5 & 2) == 0) {
         int var15 = (var0[2] & 240) >>> 4;
         return Qg.q(jz, var8 << 4 | var15, var0, "Advanced SIMD three different");
      } else if (var3 == 15 && (var6 & 387) == 2) {
         int var23 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
         int var12 = var8 << 6 | (var7 & 2) << 4 | var23;
         return !MathUtil.betweenInclusive(var12, 24L, 29L)
               && !MathUtil.betweenInclusive(var12, 44L, 47L)
               && !MathUtil.betweenInclusive(var12, 56L, 59L)
               && var12 != 61
               && !MathUtil.betweenInclusive(var12, 88L, 93L)
               && !MathUtil.betweenInclusive(var12, 108L, 109L)
               && var12 != 111
               && !MathUtil.betweenInclusive(var12, 121L, 1983L)
               && var12 != 125
               && var12 != 127
            ? Qg.q(var0, "Advanced SIMD two-register miscellaneous (FP16)")
            : Qg.q(vh, var12, var0, "Advanced SIMD two-register miscellaneous (FP16)");
      } else {
         if (var4 == 0) {
            if ((var3 & 3) == 0) {
               int var22 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
               if (var22 == 5 && var7 < 2 && var8 == 1) {
                  return Rd[var7];
               }

               if (!MathUtil.betweenInclusive(var22, 0L, 11L) && !MathUtil.betweenInclusive(var22, 18L, 20L)) {
                  return Qg.q(vh, var8 << 6 | (var7 & 2) << 4 | var22, var0, "Advanced SIMD two-register miscellaneous");
               }

               return Qg.q(Kn, var8 << 5 | var22, var0, "Advanced SIMD two-register miscellaneous");
            }

            if ((var3 & 3) == 2) {
               int var11 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
               if (var11 != 3 && var11 != 10 && var11 != 26 && var11 != 27) {
                  if (var11 != 12 && var11 != 15) {
                     return Qg.q(var0, "Advanced SIMD across lanes");
                  }

                  return Qg.q(Al, var8 << 2 | var7 & 2 | var11 & 1, var0, "Advanced SIMD across lanes");
               }

               var11 = var11 < 16 ? var11 >>> 3 : var11 & 3;
               return Qg.q(FG, var8 << 2 | var11, var0, "Advanced SIMD across lanes");
            }

            if ((var3 & 3) == 1 && var1 == 4) {
               int var10 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
               if (var7 == 0 && (var10 & 28) == 4) {
                  return cy[var10 & 3];
               }

               return Qg.q(var0, "Cryptographic AES");
            }
         }

         return Qg.q(var0, "Advanced SIMD/Cryptographic AES");
      }
   }

   private static class eo implements Ef {
      private final IEncodedMemoryArea q;
      private final int RF;

      public eo(int var1, IEncodedMemoryArea var2) {
         this.q = var2;
         this.RF = var1;
      }

      @Override
      public CW buildOperand(byte[] var1, int var2) {
         int var3 = (var1[0] & 32) >>> 5;
         int var4 = (var1[2] & 240) >>> 4;
         if (var4 == 15) {
            return new ub(this.RF, this.q).buildOperand(var1, var2);
         } else {
            int var5 = k.RF(var1, this.q);
            return com.pnfsoftware.jebglobal.CW.q(64, k.q(var3, var4, (long)var5));
         }
      }
   }
}
