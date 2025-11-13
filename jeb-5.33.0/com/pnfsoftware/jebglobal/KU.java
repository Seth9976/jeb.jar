package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class KU {
   private static final IEncodedMemoryArea ys = DirectEncodedMemoryArea.get(0, 1);
   private static final IEncodedMemoryArea ld = DirectEncodedMemoryArea.get(3, 1);
   private static final IEncodedMemoryArea gp = DirectEncodedMemoryArea.get(4, 1);
   private static final IEncodedMemoryArea oT = DirectEncodedMemoryArea.get(5, 1);
   private static final IEncodedMemoryArea fI = DirectEncodedMemoryArea.get(5, 3);
   private static final IEncodedMemoryArea WR = DirectEncodedMemoryArea.get(5, 5);
   private static final IEncodedMemoryArea NS = DirectEncodedMemoryArea.get(5, 6);
   private static final IEncodedMemoryArea vP = DirectEncodedMemoryArea.get(8, 1);
   private static final IEncodedMemoryArea xC = DirectEncodedMemoryArea.get(8, 2);
   private static final IEncodedMemoryArea ED = DirectEncodedMemoryArea.get(10, 1);
   private static final IEncodedMemoryArea Sc = DirectEncodedMemoryArea.get(10, 2);
   private static final IEncodedMemoryArea ah = DirectEncodedMemoryArea.get(10, 3);
   private static final IEncodedMemoryArea eP = DirectEncodedMemoryArea.get(10, 4);
   private static final IEncodedMemoryArea UO = new EncodedMemoryAreaList(Sc, ys);
   private static final IEncodedMemoryArea Ab = new EncodedMemoryAreaList(Sc, ld);
   private static final IEncodedMemoryArea rl = new EncodedMemoryAreaList(Sc, gp);
   private static final IEncodedMemoryArea z = DirectEncodedMemoryArea.get(11, 2);
   private static final IEncodedMemoryArea Ek = DirectEncodedMemoryArea.get(11, 6);
   private static final IEncodedMemoryArea hK = DirectEncodedMemoryArea.get(12, 1);
   private static final IEncodedMemoryArea Er = DirectEncodedMemoryArea.get(12, 2);
   static final IEncodedMemoryArea pC = DirectEncodedMemoryArea.get(13, 1);
   static final IEncodedMemoryArea A = DirectEncodedMemoryArea.get(13, 2);
   private static final IEncodedMemoryArea FE = new EncodedMemoryAreaList(pC, gp);
   private static final IEncodedMemoryArea Aj = DirectEncodedMemoryArea.get(15, 1);
   private static final IEncodedMemoryArea EX = new EncodedMemoryAreaList(Aj, pC, gp);
   private static final IEncodedMemoryArea LM = DirectEncodedMemoryArea.get(16, 1);
   private static final IEncodedMemoryArea mv = new EncodedMemoryAreaList(LM, Sc);
   private static final IEncodedMemoryArea sO = DirectEncodedMemoryArea.get(16, 2);
   private static final IEncodedMemoryArea os = new EncodedMemoryAreaList(sO, gp);
   private static final IEncodedMemoryArea Cu = DirectEncodedMemoryArea.get(16, 3);
   static final IEncodedMemoryArea kS = DirectEncodedMemoryArea.get(16, 4);
   static final IEncodedMemoryArea wS = DirectEncodedMemoryArea.get(16, 5);
   private static final IEncodedMemoryArea hZ = DirectEncodedMemoryArea.get(17, 1);
   private static final IEncodedMemoryArea UW = DirectEncodedMemoryArea.get(17, 2);
   private static final IEncodedMemoryArea PR = DirectEncodedMemoryArea.get(19, 2);
   private static final IEncodedMemoryArea cX = new EncodedMemoryAreaList(PR, Sc);
   private static final IEncodedMemoryArea DQ = new EncodedMemoryAreaList(PR, DirectEncodedMemoryArea.get(11, 1));
   private static final IEncodedMemoryArea ZN = DirectEncodedMemoryArea.get(20, 1);
   private static final IEncodedMemoryArea OB = new EncodedMemoryAreaList(ZN, DirectEncodedMemoryArea.get(11, 1));
   static final IEncodedMemoryArea UT = DirectEncodedMemoryArea.get(22, 1);
   static final IEncodedMemoryArea E = DirectEncodedMemoryArea.get(22, 2);
   private static final IEncodedMemoryArea pF = new EncodedMemoryAreaList(UT, PR);
   private static final IEncodedMemoryArea Bc = new EncodedMemoryAreaList(E, hK);
   private static final IEncodedMemoryArea OI = new EncodedMemoryAreaList(E, wS);
   private static final IEncodedMemoryArea Bf = new EncodedMemoryAreaList(E, DirectEncodedMemoryArea.get(18, 3));
   private static final IEncodedMemoryArea Pe = new EncodedMemoryAreaList(UT, UW);
   private static final IEncodedMemoryArea ck = DirectEncodedMemoryArea.get(23, 1);
   private static final String[] RW = new String[]{"#0", "#90", "#180", "#270"};
   private static final ZW e = new ZW(ER.pC, 262144L, 1L, 1L, 1L);
   private static final ZW xM = new ZW(ER.pC, 262144L, 262144L, 1L, 1L);
   private static final ZW kU = new ZW(ER.pC, 131072L, 131072L, 131072L, 1L);
   static final ZW sY = new ZW(ER.pC, 1L, 131072L, 131072L, 131072L);
   private static final ZW Kq = new ZW(ER.pC, 1L, 262144L, 262144L, 262144L);
   private static final ZW go = new ZW(UW, 1L, 262144L, 262144L, 262144L);
   private static final ZW JF = Kq;
   private static final ZW Nq = new ZW(ER.pC, 1L, 1L, 131072L, 1L);
   private static final ZW pg = new ZW(ER.pC, 1L, 1L, 262144L, 1L);
   private static final ZW gj = new ZW(ER.pC, 1L, 1L, 131072L, 131072L);
   private static final ZW ZD = new ZW(ER.pC, 1L, 1L, 262144L, 262144L);
   private static final ZW DL = new ZW(ER.pC, 1L, 1L, 1L, 131072L);
   private static final ZW UH = new ZW(
      new EncodedMemoryAreaList(ER.pC, DirectEncodedMemoryArea.get(13, 1)), 131072L, 1L, 131072L, 131072L, 131072L, 131072L, 131072L, 131072L
   );
   private static final ZW VD = new ZW(DirectEncodedMemoryArea.get(4, 1), 131072L, 1L);
   private static final ZW Xs = new ZW(pF, 1L, 262144L, 262144L, 1L, 262144L, 1L, 1L, 1L);
   private static final Hu[] KV = new Hu[]{ER.gp, aP.QQ, aP.Bc};
   private static final Hu[] FK = new Hu[]{ER.ED, aP.QQ, aP.Bc};
   private static final Hu[] Bi = new Hu[]{ER.ED, aP.QQ, ER.ED, aP.Bc};
   private static final Hu[] wQ = new Hu[]{ER.ED, aP.QQ, aP.Bc};
   private static final Hu[] PZ = new Hu[]{ER.ED, aP.QQ, ER.ED, aP.Bc};
   private static final Hu[] Ip = new Hu[]{com.pnfsoftware.jebglobal.Bf.NS, aP.QQ, aP.Bc};
   private static final Hu[] Fm = new Hu[]{aP.uE, aP.jS};
   private static final Hu[] FM = new Hu[]{aP.uE, aP.jS, aP.rC};
   private static final Hu[] Wn = new Hu[]{aP.uE, aP.AU, aP.uE};
   private static final Hu[] gy = new Hu[]{aP.Um, aP.KK};
   private static final Hu[] pt = new Hu[]{aP.Ta, aP.KK};
   private static final Hu[] uE = new Hu[]{aP.Um, aP.sz};
   private static final Hu[] Um = new Hu[]{aP.Um, aP.fD, aP.KK, aP.be};
   private static final Hu[] Ta = new Hu[]{aP.uE, aP.dM, aP.Bc, aP.ZD};
   private static final Hu[] So = new Hu[]{aP.uE, aP.dM, aP.Bc, bI.pC(bI.UT)};
   private static final Hu[] tH = new Hu[]{aP.uE, aP.dM, aP.Bc, aP.Bi};
   private static final Hu[] Gm = new Hu[]{aP.Um, aP.Gu, aP.KK, aP.be};
   private static final Hu[] Br = new Hu[]{aP.Um, aP.Gu, aP.KK};
   private static final Hu[] IE = new Hu[]{aP.Um, aP.ii, aP.KK};
   private static final Hu[] AU = new Hu[]{aP.Um, aP.Gu, aP.KK, aP.Um};
   private static final Hu[] jS = new Hu[]{aP.Um, aP.hw, aP.KK};
   private static final Hu[] KK = new Hu[]{aP.Um, aP.PR};
   private static final Hu[] oB = new Hu[]{aP.ah, aP.KK};
   private static final Hu[] Rq = new Hu[]{aP.Ek, aP.jS};
   private static final Hu[] LL = new Hu[]{aP.Ek, aP.QQ, aP.Bc};
   private static final Hu[] rC = new Hu[]{aP.Ek, aP.QQ, aP.Ek, aP.Bc};
   private static final Hu[] be = new Hu[]{aP.Ek, aP.fD, aP.Bc, aP.ZD};
   private static final Hu[] Xh = new Hu[]{aP.Ek, aP.eE, aP.Ek, aP.Bc};
   private static final Hu[] sz = new Hu[]{aP.Ek, aP.eE, aP.Ek, aP.OB};
   private static final Hu[] QQ = new Hu[]{aP.Ek, aP.eE, aP.Bc};
   private static final Hu[] eE = new Hu[]{aP.Ek, aP.eE, aP.Bc, aP.ZD};
   private static final Hu[] dM = new Hu[]{aP.Ek, aP.eE, aP.ZD, aP.Bc};
   private static final Hu[] EM = new Hu[]{aP.Ek, aP.ii, aP.Bc};
   private static final Hu[] fD = new Hu[]{aP.UO, aP.ii, aP.UO, aP.DQ};
   private static final Hu[] ii = new Hu[]{aP.UO, aP.eE, aP.DQ, aP.KV};
   private static final Hu[] Gu = new Hu[]{aP.UO, aP.eE, aP.DQ};
   private static final Hu[] hw = new Hu[]{aP.UO, aP.eE, aP.ZN};
   private static final Hu[] qG = new Hu[]{aP.UO, aP.eE, aP.OB};
   private static final Hu[] yi = new Hu[]{aP.Ab, aP.eE, aP.DQ};
   private static final Hu[] zK = new Hu[]{aP.Ab, aP.eE, aP.ZN};
   private static final Hu[] LA = new Hu[]{aP.Ab, aP.eE, aP.OB};
   private static final Hu[] ve = new Hu[]{aP.rl, aP.eE, aP.DQ};
   private static final Hu[] yv = new Hu[]{aP.rl, aP.eE, aP.ZN};
   private static final Hu[] MZ = new Hu[]{aP.rl, aP.eE, aP.OB};
   private static final Hu[] fH = new Hu[]{aP.z, aP.eE, aP.pF};
   private static final Hu[] ET = new Hu[]{aP.Ek, aP.dM, aP.Bc, aP.ZD};
   private static final Hu[] kk = new Hu[]{aP.Er, aP.eE, aP.OI};
   private static final Hu[] Rh = new Hu[]{aP.ah, aP.PR};
   private static final Hu[] vv = new Hu[]{aP.Ek, aP.Bc};
   private static final Hu[] fn = new Hu[]{aP.Ek, aP.Bc, aP.ZD};
   private static final Hu[] AS = new Hu[]{aP.Ek, aP.Bc, aP.Bi};
   private static final Hu[] wF = new Hu[]{aP.UO, aP.DQ, aP.KV};
   private static final Hu[] hF = new Hu[]{aP.UO, aP.cX};
   private static final Hu[] FA = new Hu[]{aP.UO, aP.cX, aP.Xs};
   private static final Hu[] IK = new Hu[]{aP.Ab, aP.cX, aP.Xs};
   private static final Hu[] DM = new Hu[]{aP.Ab, aP.DQ, aP.KV};
   private static final Hu[] IQ = new Hu[]{aP.Ab, aP.ZN, aP.FK};
   private static final Hu[] zR = new Hu[]{aP.rl, aP.OB, aP.Bi};
   private static final Hu[] Ft = new Hu[]{aP.z, aP.pF, aP.wQ};
   private static final Hu[] kt = new Hu[]{aP.rl, aP.OB};
   private static final Hu[] Yw = new Hu[]{aP.Ek, aP.Ek};
   private static final Hu[] uD = new Hu[]{aP.Ek, aP.Ek, aP.Bc};
   private static final Hu[] ZY = new Hu[]{aP.Ek, aP.Ek, aP.Bc, IV.wS};
   private static final Hu[] mK = new Hu[]{aP.Ab, aP.Ab, aP.ZN};
   private static final Hu[] pW = new Hu[]{aP.rl, aP.rl, aP.Bi, aP.OB};
   private static final Hu[] Gg = new Hu[]{aP.Ek, aP.ZD, aP.Bc};
   private static final Hu kQ = new Zs(var0 -> pC(var0, aP.A, fI, Z.Av.kS));
   private static final Hu te = new Zs(var0 -> pC(var0, aP.A, fI, Z.Av.pC));
   private static final Hu B = new Zs(var0 -> pC(var0, aP.pC, Cu, Z.Av.kS));
   private static final Hu RR = new Zs(var0 -> pC(var0, aP.pC, Cu, Z.Av.pC));
   private static final Hu[] CK = new Hu[]{aP.Cu, aP.eE, aP.Cu, kQ};
   private static final Hu[] Eq = new Hu[]{aP.Cu, aP.eE, aP.Cu, te};
   private static final Hu[] y = new Hu[]{aP.sO, aP.sO, aP.RW, B};
   private static final Hu[] JP = new Hu[]{aP.sO, aP.RW, B};
   private static final Hu[] jY = new Hu[]{aP.sO, aP.RW, RR};
   private static final Hu ee = new Yu(aP.xM, TN.E(wS));
   private static final Hu ho = new Yu(aP.PZ, PR);
   private static final Hu VE = new Yu(aP.PZ, cX);
   private static final Hu lt = new Yu(aP.PZ, DQ);
   private static final Hu uw = new Yu(aP.Ip, pF);
   private static final Hu QP = new Yu(aP.Ip, PR);
   private static final Hu hM = new Yu(aP.Ip, DQ);
   private static final Hu MJ = new Yu(aP.FM, ZN);
   private static final Hu OA = new Yu(aP.Fm, PR);
   private static final Hu kT = new Yu(aP.Wn, OB);
   private static final Hu x = new Yu(aP.Wn, ZN);
   private static final Hu un = new Yu(aP.gy, ZN);
   private static final Hu JV = new Yu(aP.gj, ck);
   private static final Hu Iq = new Yu(aP.gj, E);
   private static final Hu mV = new Yu(aP.gj, Bc);
   private static final Hu Gh = new TF(aP.Xh, sQ.Bf, IV.pC(TN.E(Bf)));
   private static final Hu[] HG = new Hu[]{aP.Ab, aP.cX, ho};
   private static final Hu[] yC = new Hu[]{aP.Ab, aP.cX, VE};
   private static final Hu[] uu = new Hu[]{aP.rl, aP.DQ, MJ};
   private static final Hu[] Tq = new Hu[]{aP.UO, aP.cX, VE};
   private static final Hu[] HO = new Hu[]{aP.UO, aP.cX, lt};
   private static final Hu[] Is = new Hu[]{aP.UO, aP.DQ, uw};
   private static final Hu[] BP = new Hu[]{aP.Ab, aP.DQ, QP};
   private static final Hu[] Wm = new Hu[]{aP.Ab, aP.ZN, OA};
   private static final Hu[] TP = new Hu[]{aP.rl, aP.OB, un};
   private static final Hu[] gd = new Hu[]{aP.Ab, aP.DQ, hM};
   private static final Hu[] eI = new Hu[]{aP.rl, aP.ZN, kT};
   private static final Hu lZ = new Yu(aP.ah, hZ);
   private static final Hu AQ = new Yu(aP.ah, UW);
   private static final Hu BX = new Yu(aP.ah, Pe);
   private static final Hu xg = new Yu(aP.PR, hZ);
   private static final Hu NN = new Yu(aP.PR, UW);
   private static final Hu np = new Yu(aP.PR, Pe);
   private static final Hu[] ik = new Hu[]{aP.Ta, xg};
   private static final Hu[] aK = new Hu[]{aP.So, NN};
   private static final Hu[] gR = new Hu[]{aP.tH, np};
   private static final Hu[] Ig = new Hu[]{lZ, aP.oB};
   private static final Hu[] TV = new Hu[]{AQ, aP.Rq};
   private static final Hu[] pY = new Hu[]{BX, aP.LL};
   private static final Hu l = new Fw(64, aP.Bc);
   private static final Hu Tr = new Fw(64, aP.Bc, aP.Kq);
   private static final Hu Op = new Fw(64, aP.cX);
   private static final Hu yB = new Fw(64, aP.cX, aP.go);
   private static final Hu Mi = new Fw(64, aP.DQ);
   private static final Hu TD = new Fw(64, aP.DQ, aP.JF);
   private static final Hu mz = new Fw(64, aP.Nq, aP.pg);
   private static final tz[] UJ = new tz[]{new UC("MLA", eE).pC(Le.UT), new UC("MLS", eE).pC(Le.UT), new UC("MAD", dM).pC(Le.UT), new UC("MSB", dM).pC(Le.UT)};
   private static final tz[] KW = new tz[]{
      new UC("ADD", Xh).pC(Le.UT),
      new UC("SUB", Xh).pC(Le.UT),
      null,
      new UC("SUBR", Xh).pC(Le.UT),
      new UC("ADDPT", Xh).pC(Le.UT, DL, Le.Bf),
      new UC("SUBPT", Xh).pC(Le.UT, DL, Le.Bf),
      null,
      null,
      new UC("SMAX", Xh).pC(Le.UT),
      new UC("UMAX", Xh).pC(Le.UT),
      new UC("SMIN", Xh).pC(Le.UT),
      new UC("UMIN", Xh).pC(Le.UT),
      new UC("SABD", Xh).pC(Le.UT),
      new UC("UABD", Xh).pC(Le.UT),
      null,
      null
   };
   private static final tz[] NB = new tz[]{
      new UC("MUL", Xh).pC(Le.UT),
      null,
      new UC("SMULH", Xh).pC(Le.UT),
      new UC("UMULH", Xh).pC(Le.UT),
      new UC("SDIV", Xh).pC(gj),
      new UC("UDIV", Xh).pC(gj),
      new UC("SDIVR", Xh).pC(gj),
      new UC("UDIVR", Xh).pC(gj),
      new UC("ORR", Xh).pC(Le.UT),
      new UC("EOR", Xh).pC(Le.UT),
      new UC("AND", Xh).pC(Le.UT),
      new UC("BIC", Xh).pC(Le.UT)
   };
   private static final tz[][] ND = new tz[][]{
      {new UC("SADDV", KV).pC(kU), new UC("UADDV", KV).pC(Le.UT)},
      {null, new UC("ADDQV", Ip).pC(Le.sY)},
      {new UC("SMAXV", FK).pC(Le.UT), new UC("UMAXV", FK).pC(Le.UT), new UC("SMINV", FK).pC(Le.UT), new UC("UMINV", FK).pC(Le.UT)},
      {new UC("SMAXQV", Ip).pC(Le.sY), new UC("UMAXQV", Ip).pC(Le.sY), new UC("SMINQV", Ip).pC(Le.sY), new UC("UMINQV", Ip).pC(Le.sY)},
      null,
      null,
      {new UC("ORV", FK).pC(Le.UT), new UC("EORV", FK).pC(Le.UT), new UC("ANDV", FK).pC(Le.UT)},
      {new UC("ORQV", Ip).pC(Le.sY), new UC("EORQV", Ip).pC(Le.sY), new UC("ANDQV", Ip).pC(Le.sY)}
   };
   private static final tz WX = new UC("MOVPRFX", aP.Ek, aP.EM, aP.Bc).pC(Le.UT);
   private static final tz[][] Ck = new tz[][]{
      {
            new UC("ASR", CK).pC(Le.UT),
            new UC("LSR", CK).pC(Le.UT),
            null,
            new UC("LSL", Eq).pC(Le.UT),
            new UC("ASRD", CK).pC(Le.UT),
            null,
            new UC("SQSHL", Eq).pC(Le.E),
            new UC("UQSHL", Eq).pC(Le.E)
      },
      {null, null, null, null, new UC("SRSHR", CK).pC(Le.E), new UC("URSHR", CK).pC(Le.E), null, new UC("SQSHLU", Eq).pC(Le.UT)},
      {
            new UC("ASR", Xh).pC(Le.UT),
            new UC("LSR", Xh).pC(Le.UT),
            null,
            new UC("LSL", Xh).pC(Le.UT),
            new UC("ASRR", Xh).pC(Le.UT),
            new UC("LSRR", Xh).pC(Le.UT),
            null,
            new UC("LSLR", Xh).pC(Le.UT)
      },
      {new UC("ASR", sz).pC(kU), new UC("LSR", sz).pC(kU), null, new UC("LSL", sz).pC(kU)}
   };
   private static final tz[][] vU = new tz[][]{
      null,
      null,
      {
            new UC("SXTB", QQ).pC(sY),
            new UC("UXTB", QQ).pC(sY),
            new UC("SXTH", QQ).pC(gj),
            new UC("UXTH", QQ).pC(gj),
            new UC("SXTW", QQ).pC(DL),
            new UC("UXTW", QQ).pC(DL),
            new UC("ABS", QQ).pC(Le.UT),
            new UC("NEG", QQ).pC(Le.UT)
      },
      {
            new UC("CLS", QQ).pC(Le.UT),
            new UC("CLZ", QQ).pC(Le.UT),
            new UC("CNT", QQ).pC(Le.UT),
            new UC("CNOT", QQ).pC(Le.UT),
            new UC("FABS", QQ).pC(sY),
            new UC("FNEG", QQ).pC(sY),
            new UC("NOT", QQ).pC(Le.UT)
      }
   };
   private static final tz[] KM = new tz[]{
      new UC("ADD", fn).pC(Le.UT),
      new UC("SUB", fn).pC(Le.UT),
      new UC("ADDPT", fn).pC(Le.UT, DL, Le.Bf),
      new UC("SUBPT", fn).pC(Le.UT, DL, Le.Bf),
      new UC("SQADD", fn).pC(Le.UT),
      new UC("UQADD", fn).pC(Le.UT),
      new UC("SQSUB", fn).pC(Le.UT),
      new UC("UQSUB", fn).pC(Le.UT)
   };
   private static final tz[] rI = new tz[]{
      new UC("AND", zR).pC(Le.UT),
      new UC("ORR", zR).pC(Le.UT).pC(new UC("MOV", kt).pC(Le.UT), NH.pC(aP.PR, aP.Bi)),
      new UC("EOR", zR).pC(Le.UT),
      new UC("BIC", zR).pC(Le.UT)
   };
   private static final tz iX = new UC("XAR", y).pC(Le.E);
   private static final tz[] XZ = new tz[]{
      new UC("EOR3", pW).pC(Le.E),
      new UC("BSL", pW).pC(Le.E),
      new UC("BCAX", pW).pC(Le.E),
      new UC("BSL1N", pW).pC(Le.E),
      null,
      new UC("BSL2N", pW).pC(Le.E),
      null,
      new UC("NBSL", pW).pC(Le.E)
   };
   private static final Hu[] jj = new Hu[]{aP.Ek, IV.ah, IV.eP};
   private static final Hu[] jH = new Hu[]{aP.Ek, sQ.Cu, IV.eP};
   private static final Hu[] uJ = new Hu[]{aP.Ek, IV.ah, sQ.cX};
   private static final Hu[] Fx = new Hu[]{aP.Ek, sQ.Cu, sQ.cX};
   private static final tz[] hB = new tz[]{
      new UC("INDEX", jj).pC(Le.UT), new UC("INDEX", jH).pC(Le.UT), new UC("INDEX", uJ).pC(Le.UT), new UC("INDEX", Fx).pC(Le.UT)
   };
   private static final tz[] gW = new tz[]{new UC("ADDVL", sQ.WR, sQ.vP, IV.Ab).pC(Le.UT), new UC("ADDPL", sQ.WR, sQ.vP, IV.Ab).pC(Le.UT)};
   private static final tz pP = new UC("RDVL", sQ.A, IV.Ab).pC(Le.UT);
   private static final tz[] sd = new tz[]{
      new UC("MUL", fn).pC(Le.E),
      new UC("PMUL", fn).pC(e),
      new UC("SMULH", fn).pC(Le.E),
      new UC("UMULH", fn).pC(Le.E),
      new UC("SQDMULH", fn).pC(Le.E),
      new UC("SQRDMULH", fn).pC(Le.E)
   };
   private static final tz[] OD = new tz[]{
      new UC("ASR", AS).pC(kU),
      new UC("LSR", AS).pC(kU),
      null,
      new UC("LSL", AS).pC(kU),
      new UC("ASR", JP).pC(Le.UT),
      new UC("LSR", JP).pC(Le.UT),
      null,
      new UC("LSL", jY).pC(Le.UT)
   };
   private static final tz[] hq = new tz[]{
      new UC("ADR", aP.rl, LF.pC(aP.OB, aP.Bi, VirtualEncodedMemoryArea.get(6, 4), Sc, LF.wS)).pC(Le.UT),
      new UC("ADR", aP.rl, LF.pC(aP.OB, aP.Bi, VirtualEncodedMemoryArea.get(2, 4), Sc, LF.wS)).pC(Le.UT),
      new UC("ADR", aP.Ek, LF.pC(aP.Bc, aP.ZD, VirtualEncodedMemoryArea.get(0, 4), Sc)).pC(Le.UT)
   };
   private static final tz sR = new UC("FTSSEL", fn).pC(sY);
   private static final tz ib = new UC("FEXPA", vv).pC(sY);
   private static final tz zJ = new UC("MOVPRFX", Rh).pC(Le.UT);
   private static final String[] aU = new String[]{
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
   private static final Hu dC = new Ag(Ll.Av.pC, aU, WR, "#%d");
   private static final Hu fN = new Ag(Ll.Av.WR, aU, WR, "#%d");
   private static final Hu dW = (var0, var1) -> {
      int var2 = var0[1] & 15;
      if (var2 == 0) {
         Yg var3 = dC.buildOperand(var0, var1);
         return var3.getAlias(0L).equals("ALL") ? null : var3;
      } else {
         return ZV.pC(dC.buildOperand(var0, var1), Z.Av.xC, ++var2);
      }
   };
   private static final Hu[] dE = new Hu[]{aP.Ek, dW};
   private static final tz[][] xy = new tz[][]{
      null,
      {new UC("SQINCH", dE).pC(Le.UT), new UC("UQINCH", dE).pC(Le.UT), new UC("SQDECH", dE).pC(Le.UT), new UC("UQDECH", dE).pC(Le.UT)},
      {new UC("SQINCW", dE).pC(Le.UT), new UC("UQINCW", dE).pC(Le.UT), new UC("SQDECW", dE).pC(Le.UT), new UC("UQDECW", dE).pC(Le.UT)},
      {new UC("SQINCD", dE).pC(Le.UT), new UC("UQINCD", dE).pC(Le.UT), new UC("SQDECD", dE).pC(Le.UT), new UC("UQDECD", dE).pC(Le.UT)}
   };
   private static final Hu[] tR = new Hu[]{sQ.A, dW};
   private static final tz[] lO = new tz[]{
      new UC("CNTB", tR).pC(Le.UT), new UC("CNTH", tR).pC(Le.UT), new UC("CNTW", tR).pC(Le.UT), new UC("CNTD", tR).pC(Le.UT)
   };
   private static final tz[][] ll = new tz[][]{
      null,
      {new UC("INCH", dE).pC(Le.UT), new UC("DECH", dE).pC(Le.UT)},
      {new UC("INCW", dE).pC(Le.UT), new UC("DECW", dE).pC(Le.UT)},
      {new UC("INCD", dE).pC(Le.UT), new UC("DECD", dE).pC(Le.UT)}
   };
   private static final tz[][] KC = new tz[][]{
      {new UC("INCB", tR).pC(Le.UT), new UC("DECB", tR).pC(Le.UT)},
      {new UC("INCH", tR).pC(Le.UT), new UC("DECH", tR).pC(Le.UT)},
      {new UC("INCW", tR).pC(Le.UT), new UC("DECW", tR).pC(Le.UT)},
      {new UC("INCD", tR).pC(Le.UT), new UC("DECD", tR).pC(Le.UT)}
   };
   private static final Hu[] cH = new Hu[]{sQ.A, sQ.ED, dW};
   private static final Hu[] bm = new Hu[]{sQ.ED, dW};
   private static final tz[][] hE = new tz[][]{
      {
            new UC("SQINCB", cH).pC(Le.UT),
            new UC("UQINCB", bm).pC(Le.UT),
            new UC("SQDECB", cH).pC(Le.UT),
            new UC("UQDECB", bm).pC(Le.UT),
            new UC("SQINCB", tR).pC(Le.UT),
            new UC("UQINCB", tR).pC(Le.UT),
            new UC("SQDECB", tR).pC(Le.UT),
            new UC("UQDECB", tR).pC(Le.UT)
      },
      {
            new UC("SQINCH", cH).pC(Le.UT),
            new UC("UQINCH", bm).pC(Le.UT),
            new UC("SQDECH", cH).pC(Le.UT),
            new UC("UQDECH", bm).pC(Le.UT),
            new UC("SQINCH", tR).pC(Le.UT),
            new UC("UQINCH", tR).pC(Le.UT),
            new UC("SQDECH", tR).pC(Le.UT),
            new UC("UQDECH", tR).pC(Le.UT)
      },
      {
            new UC("SQINCW", cH).pC(Le.UT),
            new UC("UQINCW", bm).pC(Le.UT),
            new UC("SQDECW", cH).pC(Le.UT),
            new UC("UQDECW", bm).pC(Le.UT),
            new UC("SQINCW", tR).pC(Le.UT),
            new UC("UQINCW", tR).pC(Le.UT),
            new UC("SQDECW", tR).pC(Le.UT),
            new UC("UQDECW", tR).pC(Le.UT)
      },
      {
            new UC("SQINCD", cH).pC(Le.UT),
            new UC("UQINCD", bm).pC(Le.UT),
            new UC("SQDECD", cH).pC(Le.UT),
            new UC("UQDECD", bm).pC(Le.UT),
            new UC("SQINCD", tR).pC(Le.UT),
            new UC("UQINCD", tR).pC(Le.UT),
            new UC("SQDECD", tR).pC(Le.UT),
            new UC("UQDECD", tR).pC(Le.UT)
      }
   };
   private static final Hu vr = (var0, var1) -> {
      try {
         int var2 = 8 << aP.sY(var0);
         return Yg.pC(64, u.pC(hZ.decodeInt(var0), NS.decodeInt(var0), Ek.decodeInt(var0), var2));
      } catch (oJ var3) {
         throw new ProcessorException(var3);
      }
   };
   private static final Hu[] LV = new Hu[]{aP.mv, aP.mv, vr};
   private static final tz[] Yi = new tz[]{
      new UC("ORR", LV).pC(Le.UT),
      new UC("EOR", LV).pC(Le.UT),
      new UC("AND", LV).pC(Le.UT),
      new UC("DUPM", aP.mv, vr).pC(Le.UT).pC(new UC("MOV", aP.mv, vr).pC(Le.UT), var0 -> A(var0))
   };
   private static final Hu dF = new Dj(IV.Cu, Dj.pC, DirectEncodedMemoryArea.get(13, 1).shift(3));
   private static final Hu Jq = (var0, var1) -> new eK(8 << ER.pC.decodeInt(var0), DirectEncodedMemoryArea.get(5, 8)).buildOperand(var0, var1);
   private static final Hu[] Fz = new Hu[]{aP.Ek, aP.zK, dF};
   private static final Hu[] mr = new Hu[]{aP.Ek, aP.yi, dF};
   private static final Hu[] ct = new Hu[]{aP.Ek, aP.yi, Jq};
   private static final tz[] wj = new tz[]{new UC("MOV", Fz).pC(UH), new UC("MOV", mr).pC(UH), null, new UC("FMOV", ct).pC(sY)};
   private static final Hu[] kB = new Hu[]{aP.eP, aP.eP, aP.cX, IV.Bc};
   private static final Hu[] rB = new Hu[]{aP.eP, yB, IV.Bc};
   private static final tz[] zL = new tz[]{new UC("EXT", kB).pC(Le.UT), new UC("EXT", rB).pC(Le.E)};
   private static final tz oc = new fr(new String[]{"ZIP1", "ZIP2", "UZP1", "UZP2", null, null, "TRN1", "TRN2"}, ah, Ft).pC(Le.Pe);
   private static final tz mE = new UC("MOV", aP.UW, new Yu(aP.kU, TN.E(OI)))
      .pC(new UC("MOV", aP.UW, VC.gp).pC(Le.UT), var0 -> Gq.wS(OI.decodeInt(var0)) == 1)
      .pC(Le.UT);
   private static final tz[] GM = new tz[]{new UC("DUPQ", aP.hZ, ee).pC(Le.sY), new UC("EXTQ", aP.eP, aP.eP, aP.cX, IV.gp).pC(Le.sY)};
   private static final tz TS = new UC("TBL", aP.Ek, Tr, aP.ZD).pC(Le.E);
   private static final tz LQ = new UC("TBX", fn).pC(Le.E);
   private static final tz dv = new UC("TBXQ", fn).pC(Le.sY);
   private static final tz of = new UC("TBL", aP.Ek, l, aP.ZD).pC(Le.UT);
   private static final tz iG = new UC("MOV", aP.Ek, sQ.pF).pC(Le.UT);
   private static final tz Pr = new UC("INSR", aP.Ek, sQ.Cu).pC(Le.UT);
   private static final tz[] ti = new tz[]{
      new UC("PMOV", KK).pC(Le.sY), new UC("PMOV", ik).pC(Le.sY), new UC("PMOV", aK).pC(Le.sY), new UC("PMOV", gR).pC(Le.sY)
   };
   private static final tz[] nd = new tz[]{
      new UC("PMOV", oB).pC(Le.sY), new UC("PMOV", Ig).pC(Le.sY), new UC("PMOV", TV).pC(Le.sY), new UC("PMOV", pY).pC(Le.sY)
   };
   private static final Hu[] RS = new Hu[]{aP.Ek, aP.ck};
   private static final tz[] Nw = new tz[]{
      new UC("SUNPKLO", RS).pC(sY), new UC("SUNPKHI", RS).pC(sY), new UC("UUNPKLO", RS).pC(sY), new UC("UUNPKHI", RS).pC(sY)
   };
   private static final tz Tl = new UC("INSR", aP.Ek, ER.Sc).pC(Le.UT);
   private static final tz Jl = new UC("REV", vv).pC(Le.UT);
   private static final tz[] gS = new tz[]{new UC("PUNPKLO", pt).pC(Le.UT), new UC("PUNPKHI", pt).pC(Le.UT)};
   private static final tz aX = new fr(new String[]{"ZIP1", "ZIP2", "UZP1", "UZP2", "TRN1", "TRN2"}, ah, FM).pC(Le.UT);
   private static final tz Jy = new UC("REV", Fm).pC(Le.UT);
   private static final tz mx = new fr(new String[]{"ZIP1", "ZIP2", "UZP1", "UZP2", "TRN1", "TRN2"}, ah, fn).pC(Le.UT);
   private static final tz[] Vw = new tz[]{
      new UC("MOV", aP.Ek, aP.eE, ER.Sc).pC(Le.UT),
      new UC("COMPACT", LL).pC(gj),
      new UC("LASTA", sQ.ZN, aP.QQ, aP.Bc).pC(Le.UT),
      new UC("LASTB", sQ.ZN, aP.QQ, aP.Bc).pC(Le.UT)
   };
   private static final tz[] bK = new tz[]{new UC("LASTA", wQ).pC(Le.UT), new UC("LASTB", wQ).pC(Le.UT)};
   private static final tz[] VT = new tz[]{new UC("REVB", QQ).pC(sY), new UC("REVH", QQ).pC(gj), new UC("REVW", QQ).pC(DL), new UC("RBIT", QQ).pC(Le.UT)};
   private static final tz[] Gn = new tz[]{new UC("CLASTA", rC).pC(Le.UT), new UC("CLASTB", rC).pC(Le.UT), new UC("MOV", aP.Ek, aP.eE, sQ.pF).pC(Le.UT), null};
   private static final tz[] Do = new tz[]{new UC("CLASTA", PZ).pC(Le.UT), new UC("CLASTB", PZ).pC(Le.UT)};
   private static final tz[] QA = new tz[]{new UC("SPLICE", rC).pC(Le.UT), new UC("SPLICE", aP.Ek, aP.QQ, Tr).pC(Le.E)};
   private static final tz HZ = new UC("REVD", fH).pC(Le.UT);
   private static final Hu[] wa = new Hu[]{sQ.sO, aP.QQ, sQ.sO, aP.Bc};
   private static final tz[] Tb = new tz[]{new UC("CLASTA", wa).pC(Le.UT), new UC("CLASTB", wa).pC(Le.UT)};
   private static final tz Uf = new UC("SEL", be).pC(Le.UT).pC(new UC("MOV", EM).pC(Le.UT), NH.pC(aP.ah, aP.ZD));
   private static final CharSequence[] sZ = new CharSequence[]{"HS", "HI", null, null, "GE", "GT", "EQ", "NE"};
   private static final CharSequence[] dH = new CharSequence[]{"GE", "GT", "LT", "LE", "EQ", "NE"};
   private static final CharSequence[] yS = new CharSequence[]{"GE", "GT", "LT", "LE", "HS", "HI", "LO", "LS"};
   private static final CharSequence[] UB = new CharSequence[]{"GE", "GT", "LT", "LE", "EQ", null, "NE", null};
   private static final CharSequence[] cq = new CharSequence[]{"HS", "HI", "LO", "LS"};
   private static final CharSequence[] Ol = new CharSequence[]{"GE", "GT", "EQ", "NE", "UO"};
   private static final CharSequence[] nx = new CharSequence[]{"GE", "GT"};
   private static final ji NG = new ji.Ws(EX, sZ);
   private static final tz Sx = new UC("CMP", Ta).pC(NG).pC(Le.UT);
   private static final ji Qa = new ji.Ws(gp, "EQ", "NE");
   private static final ji Fv = new ji.Ws(EX, yS);
   private static final tz QW = new UC("CMP", tH).pC(Qa).pC(kU);
   private static final tz af = new UC("CMP", tH).pC(Fv).pC(kU);
   private static final Hu[] cL = new Hu[]{aP.uE, aP.dM, aP.Bc, IV.Aj};
   private static final ji bW = new ji.Ws(FE, cq);
   private static final tz ZO = new UC("CMP", cL).pC(bW).pC(Le.UT);
   private static final Hu[] hW = new Hu[]{aP.uE, aP.dM, aP.Bc, IV.eP};
   private static final ji PT = new ji.Ws(EX, dH);
   private static final tz Mv = new UC("CMP", hW).pC(PT).pC(Le.UT);
   private static final tz[] Ez = new tz[]{
      new UC("AND", Gm).pC(Le.UT).pC(new UC("MOV", Br).pC(Le.UT), NH.pC(aP.KK, aP.be)),
      new UC("BIC", Gm).pC(Le.UT),
      new UC("EOR", Gm).pC(Le.UT).pC(new UC("NOT", Br).pC(Le.UT), NH.pC(aP.Gu, aP.be)),
      new UC("SEL", Um).pC(Le.UT).pC(new UC("MOV", IE).pC(Le.UT), NH.pC(aP.Um, aP.be)),
      new UC("AND", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT).pC(new UC("MOV", Br).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT), NH.pC(aP.KK, aP.be)),
      new UC("BIC", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT),
      new UC("EOR", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT).pC(new UC("NOT", Br).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT), NH.pC(aP.Gu, aP.be)),
      null,
      new UC("ORR", Gm).pC(Le.UT).pC(new UC("MOV", gy).pC(Le.UT), NH.pC(aP.KK, aP.Gu, aP.be)),
      new UC("ORN", Gm).pC(Le.UT),
      new UC("NOR", Gm).pC(Le.UT),
      new UC("NAND", Gm).pC(Le.UT),
      new UC("ORR", Gm)
         .pC(com.pnfsoftware.jebglobal.ji.hK)
         .pC(Le.UT)
         .pC(new UC("MOV", gy).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT), NH.pC(aP.KK, aP.Gu, aP.be)),
      new UC("ORN", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT),
      new UC("NOR", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT),
      new UC("NAND", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT)
   };
   private static final tz[] FR = new tz[]{
      new UC("BRKPA", Gm).pC(Le.UT),
      new UC("BRKPB", Gm).pC(Le.UT),
      new UC("BRKPA", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT),
      new UC("BRKPB", Gm).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT)
   };
   private static final tz[] qn = new tz[]{new UC("BRKN", AU).pC(Le.UT), new UC("BRKN", AU).pC(com.pnfsoftware.jebglobal.ji.hK).pC(Le.UT)};
   private static final tz[] ko = new tz[]{
      new UC("BRKA", jS).pC(Le.UT),
      new UC("BRKA", Br).pC(com.pnfsoftware.jebglobal.ji.hK).pC(VD),
      new UC("BRKB", jS).pC(Le.UT),
      new UC("BRKB", Br).pC(com.pnfsoftware.jebglobal.ji.hK).pC(VD)
   };
   private static final tz xr = new UC("PTEST", aP.fD, aP.KK).pC(Le.UT);
   private static final tz qP = new UC("PFIRST", aP.Um, aP.AU, aP.Um).pC(Le.UT);
   private static final tz HY = new UC("PFALSE", aP.Um).pC(Le.UT);
   private static final ji.Av EQ = (var0, var1) -> (var0[1] & 64) != 0;
   private static final tz cM = new UC("RDFFR", uE).pC(EQ).pC(Le.UT);
   private static final tz YR = new UC("PNEXT", Wn).pC(Le.UT);
   private static final tz hH = new UC("RDFFR", aP.Um).pC(Le.UT);
   private static final ji.Av iC = (var0, var1) -> (var0[1] & 1) != 0;
   private static final tz YH = new UC("PTRUE", aP.uE, fN).pC(iC).pC(Le.UT);
   private static final ji fd = new ji.Ws(rl, yS);
   private static final tz iY = new UC("WHILE", aP.uE, sQ.os, sQ.hZ).pC(fd).pC(Le.UT);
   private static final ji az = new ji.Ws(gp, "EQ", "NE");
   private static final tz gx = new UC("CTERM", sQ.Cu, sQ.cX).pC(az).pC(Le.UT);
   private static final ji df = new ji.Ws(gp, "WR", "RW");
   private static final tz tQ = new UC("WHILE", aP.uE, sQ.UT, sQ.ld).pC(df).pC(Le.E);
   private static final tz ou = new UC("PSEL", aP.pt, aP.fD, Gh).pC(Le.sY);
   private static final ji el = new ji.Ws(Ab, yS);
   private static final ji OE = new ji.Ws(UO, yS);
   private static final Hu lT = new Ag(new String[]{"VLx2", "VLx4"}, ED);
   private static final Hu xp = new Ag(new String[]{"VLx2", "VLx4"}, pC);
   private static final Hu Wu = new Fw(64, aP.Gm, aP.Br);
   private static final tz FS = new UC("WHILE", aP.ve, sQ.UT, sQ.ld, xp).pC(el).pC(Le.sY);
   private static final tz VA = new UC("WHILE", Wu, sQ.UT, sQ.ld).pC(OE).pC(Le.sY);
   private static final Hu MP = new Fw(64, aP.uE, aP.IE);
   private static final tz is = new UC("PEXT", aP.uE, new Yu(aP.yv, xC)).pC(Le.sY);
   private static final tz tP = new UC("PEXT", MP, new Yu(aP.yv, vP)).pC(Le.sY);
   private static final tz YB = new UC("PTRUE", aP.ve).pC(Le.sY);
   private static final Hu Oy = new Dj(IV.os, Dj.pC, DirectEncodedMemoryArea.get(13, 1).shift(3));
   private static final Hu[] Lv = new Hu[]{aP.Ek, aP.Ek, Oy};
   private static final tz[] YL = new tz[]{
      new UC("ADD", Lv).pC(Le.UT),
      new UC("SUB", Lv).pC(Le.UT),
      null,
      new UC("SUBR", Lv).pC(Le.UT),
      new UC("SQADD", Lv).pC(Le.UT),
      new UC("UQADD", Lv).pC(Le.UT),
      new UC("SQSUB", Lv).pC(Le.UT),
      new UC("UQSUB", Lv).pC(Le.UT)
   };
   private static final Hu[] Te = new Hu[]{aP.Ek, aP.Ek, IV.Cu};
   private static final tz[] yh = new tz[]{
      new UC("SMAX", Te).pC(Le.UT), new UC("UMAX", Lv).pC(Le.UT), new UC("SMIN", Te).pC(Le.UT), new UC("UMIN", Lv).pC(Le.UT)
   };
   private static final tz[] ln = new tz[]{new UC("MUL", Te).pC(Le.UT)};
   private static final tz Tv = new UC("MOV", aP.Ek, dF).pC(UH);
   private static final tz Uy = new UC("FMOV", aP.Ek, Jq).pC(sY);
   private static final tz UV = new UC("CNTP", sQ.A, aP.fD, aP.jS).pC(Le.UT);
   private static final tz Ra = new UC("CNTP", sQ.A, aP.LA, lT).pC(Le.sY);
   private static final Hu[] SG = new Hu[]{sQ.A, aP.jS, sQ.ED};
   private static final Hu[] Xd = new Hu[]{sQ.A, aP.jS};
   private static final Hu[] iU = new Hu[]{sQ.ED, aP.jS};
   private static final tz[][] Pp = new tz[][]{
      {
            new UC("SQINCP", Rq).pC(sY),
            null,
            null,
            null,
            new UC("UQINCP", Rq).pC(sY),
            null,
            null,
            null,
            new UC("SQDECP", Rq).pC(sY),
            null,
            null,
            null,
            new UC("UQDECP", Rq).pC(sY)
      },
      {
            new UC("SQINCP", SG).pC(Le.UT),
            null,
            new UC("SQINCP", Xd).pC(Le.UT),
            null,
            new UC("UQINCP", iU).pC(Le.UT),
            null,
            new UC("UQINCP", Xd).pC(Le.UT),
            null,
            new UC("SQDECP", SG).pC(Le.UT),
            null,
            new UC("SQDECP", Xd).pC(Le.UT),
            null,
            new UC("UQDECP", iU).pC(Le.UT),
            null,
            new UC("UQDECP", Xd).pC(Le.UT)
      },
      {new UC("INCP", Rq).pC(sY), null, null, null, new UC("DECP", Rq).pC(sY)},
      {new UC("INCP", Xd).pC(Le.UT), null, null, null, new UC("DECP", Xd).pC(Le.UT)}
   };
   private static final tz Bt = new UC("WRFFR", aP.jS).pC(Le.UT);
   private static final tz zj = new UC("SETFFR").pC(Le.UT);
   private static final Hu[] jL = new Hu[]{aP.Ek, aP.Bf, aP.DL};
   private static final Hu[] mN = new Hu[]{aP.Ek, aP.ck, aP.VD};
   private static final Hu[] nH = new Hu[]{aP.Ek, aP.Bc, aP.VD};
   private static final Hu wl = new Ag(RW, Sc);
   private static final Hu[] ef = new Hu[]{aP.Ek, aP.Bc, aP.ZD, wl};
   private static final Hu[] qD = new Hu[]{aP.Ek, aP.eE, aP.ck};
   private static final Hu[] Fo = new Hu[]{aP.UO, aP.DQ, QP, wl};
   private static final Hu[] uf = new Hu[]{aP.Ab, aP.ZN, x, wl};
   private static final tz[] On = new tz[]{
      new UC("SDOT", jL).pC(gj), new UC("UDOT", jL).pC(gj), new UC("SQDMLALBT", mN).pC(Kq), new UC("SQDMLSLBT", mN).pC(Kq)
   };
   private static final tz Cq = new UC("CDOT", aP.Ek, aP.Bf, aP.DL, wl).pC(ZD);
   private static final tz[] kI = new tz[]{new UC("CMLA", ef).pC(Le.E), new UC("SQRDCMLAH", ef).pC(Le.E)};
   private static final tz[] IF = new tz[]{
      new UC("SMLALB", mN).pC(Kq),
      new UC("SMLALT", mN).pC(Kq),
      new UC("UMLALB", mN).pC(Kq),
      new UC("UMLALT", mN).pC(Kq),
      new UC("SMLSLB", mN).pC(Kq),
      new UC("SMLSLT", mN).pC(Kq),
      new UC("UMLSLB", mN).pC(Kq),
      new UC("UMLSLT", mN).pC(Kq),
      new UC("SQDMLALB", mN).pC(Kq),
      new UC("SQDMLALT", mN).pC(Kq),
      new UC("SQDMLSLB", mN).pC(Kq),
      new UC("SQDMLSLT", mN).pC(Kq),
      new UC("SQRDMLAH", fn).pC(Le.E),
      new UC("SQRDMLSH", fn).pC(Le.E),
      new UC("USDOT", IK).pC(Nq, Le.mv)
   };
   private static final tz Zu = new fr(
         new String[]{
            null, null, "SRSHL", "URSHL", null, null, "SRSHLR", "URSHLR", "SQSHL", "UQSHL", "SQRSHL", "UQRSHL", "SQSHLR", "UQSHLR", "SQRSHLR", "UQRSHLR"
         },
         kS,
         Xh
      )
      .pC(Le.E);
   private static final tz eg = new fr(new String[]{"SHADD", "UHADD", "SHSUB", "UHSUB", "SRHADD", "URHADD", "SHSUBR", "UHSUBR"}, Cu, Xh).pC(Le.E);
   private static final tz tj = new fr(new String[]{"SQADD", "UQADD", "SQSUB", "UQSUB", "SUQADD", "USQADD", "SQSUBR", "UQSUBR"}, Cu, Xh).pC(Le.E);
   private static final tz[] Yq = new tz[]{new UC("SADALP", qD).pC(Kq), new UC("UADALP", qD).pC(Kq)};
   private static final tz[] zw = new tz[]{
      new UC("URECPE", QQ).pC(pg), new UC("SQABS", QQ).pC(Le.E), new UC("URSQRTE", QQ).pC(pg), new UC("SQNEG", QQ).pC(Le.E)
   };
   private static final tz[] Tu = new tz[]{
      null,
      new UC("ADDP", Xh).pC(Le.E),
      null,
      null,
      new UC("SMAXP", Xh).pC(Le.E),
      new UC("UMAXP", Xh).pC(Le.E),
      new UC("SMINP", Xh).pC(Le.E),
      new UC("UMINP", Xh).pC(Le.E)
   };
   private static final tz[] ok = new tz[]{
      new UC("ZIPQ1", fn).pC(Le.sY),
      new UC("ZIPQ2", fn).pC(Le.sY),
      new UC("UZPQ1", fn).pC(Le.sY),
      new UC("UZPQ2", fn).pC(Le.sY),
      null,
      null,
      new UC("TBLQ", aP.Ek, l, aP.ZD).pC(Le.sY)
   };
   private static final tz[] Gy = new tz[]{new UC("MLAPT", fn).pC(Le.UT, DL, Le.Bf), null, new UC("MADPT", Gg).pC(Le.UT, DL, Le.Bf), null};
   private static final tz[] kc = new tz[]{new UC("SCLAMP", fn).pC(Le.sY), new UC("UCLAMP", fn).pC(Le.sY)};
   private static final tz[] Uz = new tz[]{new UC("SDOT", DM).pC(Le.sY), new UC("UDOT", DM).pC(Le.sY)};
   private static final tz[] At = new tz[]{new UC("SDOT", BP).pC(Le.sY), new UC("UDOT", BP).pC(Le.sY)};
   private static final tz[] XS = new tz[]{
      new UC("SDOT", HG).pC(Le.UT), new UC("UDOT", HG).pC(Le.UT), new UC("SDOT", uu).pC(Le.UT), new UC("UDOT", uu).pC(Le.UT)
   };
   private static final tz[] pU = new tz[]{
      null,
      null,
      new UC("MLA", Is).pC(Le.E),
      new UC("MLS", Is).pC(Le.E),
      new UC("MLA", Wm).pC(Le.E),
      new UC("MLS", Wm).pC(Le.E),
      new UC("MLA", TP).pC(Le.E),
      new UC("MLS", TP).pC(Le.E)
   };
   private static final tz[] Ga = new tz[]{
      null,
      null,
      new UC("SQRDMLAH", Is).pC(Le.E),
      new UC("SQRDMLSH", Is).pC(Le.E),
      new UC("SQRDMLAH", Wm).pC(Le.E),
      new UC("SQRDMLSH", Wm).pC(Le.E),
      new UC("SQRDMLAH", TP).pC(Le.E),
      new UC("SQRDMLSH", TP).pC(Le.E)
   };
   private static final tz[] Kj = new tz[]{new UC("USDOT", HG).pC(Le.E, Le.mv), new UC("SUDOT", HG).pC(Le.E, Le.mv)};
   private static final tz[] vj = new tz[]{
      new UC("SQDMLALB", gd).pC(Le.E),
      new UC("SQDMLALT", gd).pC(Le.E),
      new UC("SQDMLSLB", gd).pC(Le.E),
      new UC("SQDMLSLT", gd).pC(Le.E),
      new UC("SQDMLALB", eI).pC(Le.E),
      new UC("SQDMLALT", eI).pC(Le.E),
      new UC("SQDMLSLB", eI).pC(Le.E),
      new UC("SQDMLSLT", eI).pC(Le.E)
   };
   private static final tz[] DC = new tz[]{new UC("CDOT", aP.Ab, aP.cX, ho, wl).pC(Le.E), new UC("CDOT", aP.rl, aP.DQ, MJ, wl).pC(Le.E)};
   private static final tz[] jT = new tz[]{new UC("CMLA", Fo).pC(Le.E), new UC("CMLA", uf).pC(Le.E)};
   private static final tz[] pT = new tz[]{new UC("SQRDCMLAH", Fo).pC(Le.E), new UC("SQRDCMLAH", uf).pC(Le.E)};
   private static final tz[] uz = new tz[]{
      new UC("SMLALB", gd).pC(Le.E),
      new UC("SMLALT", gd).pC(Le.E),
      new UC("UMLALB", gd).pC(Le.E),
      new UC("UMLALT", gd).pC(Le.E),
      new UC("SMLSLB", gd).pC(Le.E),
      new UC("SMLSLT", gd).pC(Le.E),
      new UC("UMLSLB", gd).pC(Le.E),
      new UC("UMLSLT", gd).pC(Le.E),
      new UC("SMLALB", eI).pC(Le.E),
      new UC("SMLALT", eI).pC(Le.E),
      new UC("UMLALB", eI).pC(Le.E),
      new UC("UMLALT", eI).pC(Le.E),
      new UC("SMLSLB", eI).pC(Le.E),
      new UC("SMLSLT", eI).pC(Le.E),
      new UC("UMLSLB", eI).pC(Le.E),
      new UC("UMLSLT", eI).pC(Le.E)
   };
   private static final tz[] fG = new tz[]{
      new UC("SMULLB", gd).pC(Le.E),
      new UC("SMULLT", gd).pC(Le.E),
      new UC("UMULLB", gd).pC(Le.E),
      new UC("UMULLT", gd).pC(Le.E),
      new UC("SMULLB", eI).pC(Le.E),
      new UC("SMULLT", eI).pC(Le.E),
      new UC("UMULLB", eI).pC(Le.E),
      new UC("UMULLT", eI).pC(Le.E)
   };
   private static final tz[] Oo = new tz[]{
      new UC("SQDMULLB", gd).pC(Le.E), new UC("SQDMULLT", gd).pC(Le.E), new UC("SQDMULLB", eI).pC(Le.E), new UC("SQDMULLT", eI).pC(Le.E)
   };
   private static final tz[] jm = new tz[]{
      null,
      null,
      new UC("SQDMULH", Is).pC(Le.E),
      new UC("SQRDMULH", Is).pC(Le.E),
      new UC("SQDMULH", Wm).pC(Le.E),
      new UC("SQRDMULH", Wm).pC(Le.E),
      new UC("SQDMULH", TP).pC(Le.E),
      new UC("SQRDMULH", TP).pC(Le.E)
   };
   private static final tz[] eT = new tz[]{null, new UC("MUL", Is).pC(Le.E), new UC("MUL", Wm).pC(Le.E), new UC("MUL", TP).pC(Le.E)};
   private static final tz Ew = new fr(
         new String[]{
            "SADDLB", "SADDLT", "UADDLB", "UADDLT", "SSUBLB", "SSUBLT", "USUBLB", "USUBLT", null, null, null, null, "SABDLB", "SABDLT", "UABDLB", "UABDLT"
         },
         eP,
         mN
      )
      .pC(Kq);
   private static final tz rp = new fr(new String[]{"SADDWB", "SADDWT", "UADDWB", "UADDWT", "SSUBWB", "SSUBWT", "USUBWB", "USUBWT"}, ah, nH).pC(Kq);
   private static final tz[] uA = new tz[]{
      new UC("SQDMULLB", mN).pC(Kq),
      new UC("SQDMULLT", mN).pC(Kq),
      new UC("PMULLB", mN).pC(Le.E).pC(new UC("PMULLB", aP.rl, aP.ZN, aP.FK).pC(Le.E), var0 -> kS(var0) == 2),
      new UC("PMULLT", mN).pC(Le.E).pC(new UC("PMULLT", aP.rl, aP.ZN, aP.FK).pC(Le.E), var0 -> kS(var0) == 2),
      new UC("SMULLB", mN).pC(Kq),
      new UC("SMULLT", mN).pC(Kq),
      new UC("UMULLB", mN).pC(Kq),
      new UC("UMULLT", mN).pC(Kq)
   };
   private static final tz[] dK = new tz[]{new UC("PMULLB", aP.z, aP.OB, aP.Bi).pC(Le.E), new UC("PMULLT", aP.z, aP.OB, aP.Bi).pC(Le.E)};
   private static final Hu[] uQ = new Hu[]{aP.os, aP.RW, RR};
   private static final tz[] fr = new tz[]{
      new UC("SADDLBT", mN).pC(Kq),
      null,
      new UC("SSUBLBT", mN).pC(Kq),
      new UC("SSUBLTB", mN).pC(Kq),
      new UC("EORBT", fn).pC(Le.E),
      new UC("EORTB", fn).pC(Le.E),
      null,
      null,
      new UC("SSHLLB", uQ).pC(Le.E),
      new UC("SSHLLT", uQ).pC(Le.E),
      new UC("USHLLB", uQ).pC(Le.E),
      new UC("USHLLT", uQ).pC(Le.E),
      new UC("BEXT", fn).pC(Le.UT, Le.e),
      new UC("BDEP", fn).pC(Le.UT, Le.e),
      new UC("BGRP", fn).pC(Le.UT, Le.e)
   };
   private static final tz[] ym = new tz[]{
      new UC("SMMLA", IK).pC(Le.UT, Le.mv), null, new UC("USMMLA", IK).pC(Le.UT, Le.mv), new UC("UMMLA", IK).pC(Le.UT, Le.mv)
   };
   private static final Hu YM = new Ag(RW, new EncodedMemoryAreaList(ED, VirtualEncodedMemoryArea._1));
   private static final Hu[] LO = new Hu[]{aP.Ek, aP.Ek, aP.Bc, YM};
   private static final tz[] Ms = new tz[]{
      new UC("SABALB", mN).pC(Kq),
      new UC("SABALT", mN).pC(Kq),
      new UC("UABALB", mN).pC(Kq),
      new UC("UABALT", mN).pC(Kq),
      null,
      null,
      new UC("CADD", LO).pC(Le.E),
      new UC("SQCADD", LO).pC(Le.E),
      new UC("SSRA", JP).pC(Le.E),
      new UC("USRA", JP).pC(Le.E),
      new UC("SRSRA", JP).pC(Le.E),
      new UC("URSRA", JP).pC(Le.E),
      new UC("SRI", JP).pC(Le.E),
      new UC("SLI", jY).pC(Le.E),
      new UC("SABA", fn).pC(Le.E),
      new UC("UABA", fn).pC(Le.E)
   };
   private static final Hu[] kE = new Hu[]{aP.EX, aP.Pe, aP.UH};
   private static final tz[] Lr = new tz[]{
      new UC("ADCLB", kE).pC(Le.E), new UC("ADCLT", kE).pC(Le.E), new UC("SBCLB", kE).pC(Le.E), new UC("SBCLT", kE).pC(Le.E)
   };
   private static final Hu[] YN = new Hu[]{aP.sO, aP.e, B};
   private static final tz Pi = new fr(
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
         eP,
         YN
      )
      .pC(Le.E);
   private static final tz Df = new fr(
         new String[]{"SQRSHRUN", null, "SQRSHRN", "UQRSHRN"}, Er, aP.UO, mz, new IV(TN.pC(VirtualEncodedMemoryArea.get(16, 5), kS))
      )
      .pC(Le.sY);
   private static final Hu[] hG = new Hu[]{aP.sO, aP.e};
   private static final tz xS = new fr(new String[]{"SQXTNB", "SQXTNT", "UQXTNB", "UQXTNT", "SQXTUNB", "SQXTUNT"}, ah, hG).pC(Xs);
   private static final tz MC = new fr(new String[]{"SQCVTN", "UQCVTN", "SQCVTUN", null}, z, aP.UO, mz).pC(Le.sY);
   private static final Hu[] Kk = new Hu[]{aP.LM, aP.Bc, aP.ZD};
   private static final tz Jd = new fr(new String[]{"ADDHNB", "ADDHNT", "RADDHNB", "RADDHNT", "SUBHNB", "SUBHNT", "RSUBHNB", "RSUBHNT"}, ah, Kk).pC(Kq);
   private static final tz[] cg = new tz[]{new UC("MATCH", Ta).pC(xM), new UC("NMATCH", Ta).pC(Le.E)};
   private static final tz tK = new UC("LUTI2", aP.UO, Mi, mV).pC(Le.cX);
   private static final tz[] HL = new tz[]{
      new UC("HISTSEG", fn).pC(e),
      new UC("LUTI4", aP.eP, Op, JV).pC(Le.cX),
      tK,
      null,
      new UC("LUTI2", aP.eP, Op, Iq).pC(Le.cX),
      new UC("LUTI4", aP.UO, TD, Iq).pC(Le.cX),
      tK,
      new UC("LUTI4", aP.UO, Mi, Iq).pC(Le.cX)
   };
   private static final tz gn = new UC("HISTCNT", ET).pC(ZD);
   private static final tz[] hd = new tz[]{new UC("AESMC", Yw).pC(Le.Kq), new UC("AESIMC", Yw).pC(Le.Kq)};
   private static final tz[] xQ = new tz[]{new UC("AESE", uD).pC(Le.Kq), new UC("AESD", uD).pC(Le.Kq), new UC("SM4E", mK).pC(Le.JF)};
   private static final tz[] zf = new tz[]{new UC("SM4EKEY", IQ).pC(Le.JF), new UC("RAX1", zR).pC(Le.go)};
   private static final Hu uM = new Ag(RW, A);
   private static final Hu[] Vk = new Hu[]{aP.Ek, aP.eE, aP.Bc, aP.ZD, uM};
   private static final tz rJ = new UC("FCMLA", Vk).pC(sY);
   private static final Hu ug = new Ag(RW, new EncodedMemoryAreaList(LM, VirtualEncodedMemoryArea._1));
   private static final Hu[] Px = new Hu[]{aP.Ek, aP.eE, aP.Ek, aP.Bc, ug};
   private static final tz yk = new UC("FCADD", Px).pC(sY);
   private static final tz DX = new fr(new String[]{"FADDP", null, null, null, "FMAXNMP", "FMINNMP", "FMAXP", "FMINP"}, Cu, Xh).pC(Kq);
   private static final tz[][] jU = new tz[][]{
      {null, null, new UC("FCVTXNT", LA).pC(Le.E)},
      null,
      {new UC("FCVTNT", hw).pC(Le.E), new UC("FCVTLT", yi).pC(Le.E), new UC("BFCVTNT", hw).pC(Le.LM)},
      {null, null, new UC("FCVTNT", LA).pC(Le.E), new UC("FCVTLT", yv).pC(Le.E)}
   };
   private static final tz[] sb = new tz[]{
      new UC("FADDQV", Ip).pC(JF),
      null,
      null,
      null,
      new UC("FMAXNMQV", Ip).pC(JF),
      new UC("FMINNMQV", Ip).pC(JF),
      new UC("FMAXQV", Ip).pC(JF),
      new UC("FMINQV", Ip).pC(JF)
   };
   private static final tz[] OV = new tz[]{
      null,
      null,
      null,
      null,
      new UC("FMLA", Is).pC(Le.UT),
      new UC("FMLS", Is).pC(Le.UT),
      new UC("BFMLA", Is).pC(Le.ck),
      new UC("BFMLS", Is).pC(Le.ck),
      new UC("FMLA", Wm).pC(Le.UT),
      new UC("FMLS", Wm).pC(Le.UT),
      null,
      null,
      new UC("FMLA", TP).pC(Le.UT),
      new UC("FMLS", TP).pC(Le.UT)
   };
   private static final tz[] IS = new tz[]{new UC("FCMLA", aP.UO, aP.DQ, QP, wl).pC(Le.UT), new UC("FCMLA", aP.Ab, aP.ZN, x, wl).pC(Le.UT)};
   private static final tz[] pl = new tz[]{null, new UC("FMUL", Is).pC(Le.UT), new UC("FMUL", Wm).pC(Le.UT), new UC("FMUL", TP).pC(Le.UT)};
   private static final tz zt = new UC("BFMUL", Is).pC(Le.ck);
   private static final tz Sj = new UC("FCLAMP", fn).pC(Le.sY);
   private static final tz Yc = new UC("BFCLAMP", wF).pC(Le.ck);
   private static final tz XY = new UC("FDOT", HO).pC(Le.pg);
   private static final tz[][] ji = new tz[][]{{new UC("FDOT", BP).pC(Le.sY), XY, null, XY}, {new UC("BFDOT", BP).pC(Le.RW), new UC("FDOT", HG).pC(Le.gj)}};
   private static final tz[] jZ = new tz[]{
      new UC("FMLALB", gd).pC(Le.E),
      new UC("FMLALT", gd).pC(Le.E),
      new UC("FMLSLB", gd).pC(Le.E),
      new UC("FMLSLT", gd).pC(Le.E),
      new UC("BFMLALB", gd).pC(Le.RW),
      new UC("BFMLALT", gd).pC(Le.RW),
      new UC("BFMLSLB", gd).pC(Le.sY),
      new UC("BFMLSLT", gd).pC(Le.sY)
   };
   private static final tz[] lB = new tz[]{
      new UC("FDOT", DM).pC(Le.sY), new UC("FDOT", FA).pC(Le.pg), new UC("BFDOT", DM).pC(Le.RW), new UC("FDOT", IK).pC(Le.gj)
   };
   private static final tz[] qk = new tz[]{
      new UC("FMLALB", DM).pC(Le.E),
      new UC("FMLALT", DM).pC(Le.E),
      new UC("FMLSLB", DM).pC(Le.E),
      new UC("FMLSLT", DM).pC(Le.E),
      new UC("BFMLALB", DM).pC(Le.RW),
      new UC("BFMLALT", DM).pC(Le.RW),
      new UC("BFMLSLB", DM).pC(Le.sY),
      new UC("BFMLSLT", DM).pC(Le.sY)
   };
   private static final tz Yl = new fr(new String[]{"FMLALLBB", "FMLALLBT", "FMLALLTB", "FMLALLTT"}, Er, IK).pC(Le.ZD);
   private static final tz kR = new fr(new String[]{"FMLALB", "FMLALT"}, hK, FA).pC(Le.ZD);
   private static final tz LN = new fr(new String[]{"FMLALLBB", "FMLALLBT", "FMLALLTB", "FMLALLTT"}, E, yC).pC(Le.ZD);
   private static final tz[] CI = new tz[]{null, new UC("BFMMLA", DM).pC(Le.RW), new UC("FMMLA", IQ).pC(Le.xM), new UC("FMMLA", zR).pC(Le.kU)};
   private static final tz fZ = new fr(new String[]{"FMLALB", "FMLALT"}, ck, Tq).pC(Le.ZD);
   private static final ji QK = new ji.Ws(EX, Ol);
   private static final tz Dp = new UC("FCM", Ta).pC(QK).pC(sY);
   private static final ji OW = new ji.Ws(pC, nx);
   private static final tz oC = new UC("FAC", Ta).pC(OW).pC(sY);
   private static final ji wI = new ji.Ws(os, UB);
   private static final tz Nv = new UC("FCM", So).pC(wI).pC(sY);
   private static final tz vb = new fr(new String[]{"FADD", "FSUB", "FMUL", "FTSMUL", null, null, "FRECPS", "FRSQRTS"}, ah, fn).pC(sY);
   private static final tz rb = new fr(new String[]{"BFADD", "BFSUB", "BFMUL"}, ah, wF).pC(Le.ck);
   private static final tz tu = new fr(
         new String[]{"FADD", "FSUB", "FMUL", "FSUBR", "FMAXNM", "FMINNM", "FMAX", "FMIN", "FABD", "FSCALE", "FMULX", null, "FDIVR", "FDIV"}, kS, Xh
      )
      .pC(sY);
   private static final tz UR = new fr(new String[]{"BFADD", "BFSUB", "BFMUL", null, "BFMAXNM", "BFMINNM", "BFMAX", "BFMIN"}, kS, fD).pC(Le.ck);
   private static final tz WH = new fr(new String[]{"FAMAX", "FAMIN"}, LM, Xh).pC(Kq, Le.pC);
   private static final tz ta = new UC("FTMAD", ZY).pC(sY);
   private static final Hu bw = new bI(bI.UT, new ZW(oT, 0.5, 1.0));
   private static final Hu UY = new bI(bI.UT, new ZW(oT, 0.5, 2.0));
   private static final Hu DV = new bI(bI.UT, new ZW(oT, 0.0, 1.0));
   private static final Hu[] vN = new Hu[]{aP.Ek, aP.eE, aP.Ek, bw};
   private static final Hu[] uC = new Hu[]{aP.Ek, aP.eE, aP.Ek, UY};
   private static final Hu[] qS = new Hu[]{aP.Ek, aP.eE, aP.Ek, DV};
   private static final tz[] GC = new tz[]{
      new UC("FADD", vN).pC(sY),
      new UC("FSUB", vN).pC(sY),
      new UC("FMUL", uC).pC(sY),
      new UC("FSUBR", vN).pC(sY),
      new UC("FMAXNM", qS).pC(sY),
      new UC("FMINNM", qS).pC(sY),
      new UC("FMAX", qS).pC(sY),
      new UC("FMIN", qS).pC(sY)
   };
   private static final CharSequence[] SO = new CharSequence[]{"N", "P", "M", "Z", "A", null, "X", "I"};
   private static final ji Fb = new ji.Ws(Cu, SO);
   private static final tz kK = new UC("FRINT", QQ).pC(Fb).pC(sY);
   private static final tz[][] EV = new tz[][]{
      {null, null, new UC("FCVTX", LA).pC(Le.E)},
      null,
      {new UC("FCVT", hw).pC(Le.UT), new UC("FCVT", yi).pC(Le.UT), new UC("BFCVT", hw).pC(Le.RW)},
      {new UC("FCVT", qG).pC(Le.UT), new UC("FCVT", ve).pC(Le.UT), new UC("FCVT", LA).pC(Le.UT), new UC("FCVT", yv).pC(Le.UT)}
   };
   private static final tz[] bi = new tz[]{new UC("FRECPX", QQ).pC(sY), new UC("FSQRT", QQ).pC(sY)};
   private static final tz[][] Oa = new tz[][]{
      null,
      {
            null,
            null,
            new UC("SCVTF", Gu).pC(Le.UT),
            new UC("UCVTF", Gu).pC(Le.UT),
            new UC("SCVTF", hw).pC(Le.UT),
            new UC("UCVTF", hw).pC(Le.UT),
            new UC("SCVTF", qG).pC(Le.UT),
            new UC("UCVTF", qG).pC(Le.UT)
      },
      {null, null, null, null, new UC("SCVTF", zK).pC(Le.UT), new UC("UCVTF", zK).pC(Le.UT)},
      {
            new UC("SCVTF", yv).pC(Le.UT),
            new UC("UCVTF", yv).pC(Le.UT),
            null,
            null,
            new UC("SCVTF", LA).pC(Le.UT),
            new UC("UCVTF", LA).pC(Le.UT),
            new UC("SCVTF", MZ).pC(Le.UT),
            new UC("UCVTF", MZ).pC(Le.UT)
      }
   };
   private static final tz[][] eo = new tz[][]{
      {new UC("FLOGB", kk).pC(go)},
      {
            null,
            null,
            new UC("FCVTZS", Gu).pC(Le.UT),
            new UC("FCVTZU", Gu).pC(Le.UT),
            new UC("FCVTZS", yi).pC(Le.UT),
            new UC("FCVTZU", yi).pC(Le.UT),
            new UC("FCVTZS", ve).pC(Le.UT),
            new UC("FCVTZU", ve).pC(Le.UT)
      },
      {null, null, null, null, new UC("FCVTZS", zK).pC(Le.UT), new UC("FCVTZU", zK).pC(Le.UT)},
      {
            new UC("FCVTZS", LA).pC(Le.UT),
            new UC("FCVTZU", LA).pC(Le.UT),
            null,
            null,
            new UC("FCVTZS", yv).pC(Le.UT),
            new UC("FCVTZU", yv).pC(Le.UT),
            new UC("FCVTZS", MZ).pC(Le.UT),
            new UC("FCVTZU", MZ).pC(Le.UT)
      }
   };
   private static final tz ZQ = new fr(new String[]{"FADDV", null, null, null, "FMAXNMV", "FMINNMV", "FMAXV", "FMINV"}, Cu, FK).pC(sY);
   private static final tz NY = new fr(new String[]{"F1CVT", "F2CVT", "BF1CVT", "BF2CVT", "F1CVTLT", "F2CVTLT", "BF1CVTLT", "BF2CVTLT"}, mv, hF).pC(Le.Nq);
   private static final tz[] tr = new tz[]{
      new UC("FCVTN", aP.eP, TD).pC(Le.Nq), new UC("FCVTNB", aP.eP, mz).pC(Le.Nq), new UC("BFCVTN", aP.eP, TD).pC(Le.Nq), new UC("FCVTNT", aP.eP, mz).pC(Le.Nq)
   };
   private static final tz uF = new fr(new String[]{"FRECPE", "FRSQRTE"}, LM, vv).pC(sY);
   private static final tz[] OX = new tz[]{new UC("FADDA", Bi).pC(sY)};
   private static final tz VZ = new fr(new String[]{"FMLA", "FMLS", "FNMLA", "FNMLS"}, A, eE).pC(sY);
   private static final tz Zm = new fr(new String[]{"BFMLA", "BFMLS"}, pC, ii).pC(Le.ck);
   private static final tz Qc = new fr(new String[]{"FMAD", "FMSB", "FNMAD", "FNMSB"}, A, eE).pC(sY);

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static int pC(byte[] var0, IEncodedMemoryArea var1, IEncodedMemoryArea var2, Z.Av var3) {
      int var4 = 8 << Gq.pC(var1.decode(var0));
      int var5 = var1.decodeInt(var0);
      int var6 = var5 << var2.getLength() | var2.decodeInt(var0);
      switch (var3) {
         case pC:
            return var6 - var4;
         case kS:
         case A:
         default:
            return 2 * var4 - var6;
      }
   }

   private static boolean pC(long var0, long var2) {
      return (var0 & var2) == 0L || (var0 & var2) == var2;
   }

   private static boolean pC(long var0, int var2) {
      long var3 = (1L << var2) - 1L;
      return (var0 & var3) == (var0 >>> var2 & var3);
   }

   private static boolean A(byte[] var0) {
      if (var0[2] == -120 && var0[3] == 0) {
         System.out.println();
      }

      long var1;
      try {
         var1 = u.pC(hZ.decodeInt(var0), NS.decodeInt(var0), Ek.decodeInt(var0), 64);
      } catch (ProcessorException var3) {
         return false;
      }

      if ((var1 & 255L) != 0L) {
         if (pC(var1, -256L)) {
            return false;
         }

         if (pC(var1, 32) && pC(var1, 4294967040L)) {
            return false;
         }

         if (pC(var1, 32) && pC(var1, 16) && pC(var1, 65280L)) {
            return false;
         }

         if (pC(var1, 32) && pC(var1, 16) && pC(var1, 8)) {
            return false;
         }
      } else {
         if (pC(var1, -65536L)) {
            return false;
         }

         if (pC(var1, 32) && pC(var1, 4294901760L)) {
            return false;
         }

         if (pC(var1, 32) && pC(var1, 16)) {
            return false;
         }
      }

      return true;
   }

   public static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 224) >>> 5;
      switch (var1) {
         case 0:
            return sY(var0);
         case 1:
            return oT(var0);
         case 2:
            return xC(var0);
         case 3:
            return UO(var0);
         case 4:
            return ky.pC(var0);
         case 5:
            return ky.A(var0);
         case 6:
            return ky.kS(var0);
         case 7:
            return ky.wS(var0);
         default:
            return null;
      }
   }

   private static int kS(byte[] var0) {
      return (var0[1] & 192) >>> 6;
   }

   private static int wS(byte[] var0) {
      return (var0[2] & 4) >>> 2;
   }

   private static int UT(byte[] var0) {
      return (var0[2] & 12) >>> 2;
   }

   private static int E(byte[] var0) {
      return (var0[3] & 16) >>> 4;
   }

   private static tz sY(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      switch (var1) {
         case 0:
         case 1:
            return var2 < 16 ? ys(var0) : ld(var0);
         default:
            if ((var2 & 24) == 0) {
               return (var2 & 6) == 0 ? Yi[kS(var0)] : null;
            } else if ((var2 & 24) == 8) {
               int var3 = (var0[2] & 224) >>> 5;
               return var3 >= 4 && var3 != 6 ? null : wj[var3 >>> 1];
            } else {
               return gp(var0);
            }
      }
   }

   private static tz ys(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 16) == 16) {
         return UJ[(var1 & 32) >>> 4 | (var1 & 8) >>> 3];
      } else if ((var1 & 40) == 0) {
         int var3 = (var0[1] & 28) >>> 2;
         switch (var3) {
            case 0:
            case 1:
            case 2:
            case 3:
               return KW[var0[1] & 15];
            case 4:
            case 5:
            case 6:
               return NB[var0[1] & 15];
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
                     return WX;
                  }
                  break;
               default:
                  return UC.pC(ND, var2, var0[1] & 3, var0, "SVE Integer Reduction");
            }
         } else {
            if ((var1 & 40) == 32) {
               return UC.pC(Ck, (var0[1] & 24) >>> 3, var0[1] & 7, var0, "SVE Bitwise Shift - Predicated");
            }

            if ((var1 & 40) == 40) {
               return UC.pC(vU, (var0[1] & 24) >>> 3, var0[1] & 7, var0, "SVE Integer Unary Arithmetic - Predicated");
            }
         }

         return null;
      }
   }

   private static tz ld(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 56) == 0) {
         int var14 = (var0[2] & 28) >>> 2;
         return KM[var14];
      } else {
         if ((var1 & 56) == 8) {
            int var2 = (var0[2] & 28) >>> 2;
            if (var2 == 4) {
               return rI[kS(var0)];
            }

            if (var2 == 5) {
               return iX;
            }

            if (var2 >= 6) {
               return XZ[kS(var0) << 1 | wS(var0)];
            }
         } else {
            if ((var1 & 60) == 16) {
               return hB[UT(var0)];
            }

            if ((var1 & 60) == 20) {
               int var7 = (var0[1] & 64) >>> 6;
               int var3 = (var0[1] & 128) >>> 7;
               int var4 = (var1 & 2) >>> 1;
               int var5 = var3 << 1 | var4;
               switch (var5) {
                  case 0:
                     return gW[var7];
                  case 1:
                     return KL.pC[var7];
                  case 2:
                     int var16 = var0[1] & 31;
                     return var16 == 31 ? pP : null;
                  case 3:
                     int var6 = var0[1] & 31;
                     return var6 == 31 ? KL.A : null;
               }
            } else {
               if ((var1 & 56) == 24) {
                  int var13 = (var0[2] & 28) >>> 2;
                  return UC.pC(sd, var13, var0, "SVE2 Integer Multiply - Unpredicated");
               }

               if ((var1 & 56) == 32) {
                  int var12 = (var0[2] & 28) >>> 2;
                  return UC.pC(OD, var12, var0, "SVE Bitwise Shift - Unpredicated");
               }

               if ((var1 & 60) == 40) {
                  int var10 = kS(var0);
                  var10 = var10 == 3 ? 2 : var10;
                  return UC.pC(hq, var10, var0, "SVE address generation");
               }

               if ((var1 & 60) == 44) {
                  int var8 = UT(var0);
                  if (var8 == 0) {
                     return sR;
                  }

                  if (var8 == 2) {
                     if ((var0[1] & 31) == 0) {
                        return ib;
                     }
                  } else if (var8 == 3 && var0[1] == 32) {
                     return zJ;
                  }
               } else if ((var1 & 48) == 48) {
                  int var9 = (var0[1] & 16) >>> 4;
                  int var15 = (var0[2] & 56) >>> 3;
                  if (var9 == 0 && (var15 & 6) == 0) {
                     return UC.pC(xy, kS(var0), UT(var0), var0, "SVE saturating inc/dec vector by element count");
                  }

                  if (var9 == 0 && var15 == 4) {
                     if (wS(var0) == 0) {
                        return UC.pC(lO, kS(var0), var0, "SVE element count");
                     }
                  } else {
                     if (var9 == 1 && var15 == 0) {
                        return UC.pC(ll, kS(var0), (var0[2] & 4) >>> 2, var0, "SVE inc/dec vector by element count");
                     }

                     if (var9 == 1 && var15 == 4) {
                        return UC.pC(KC, kS(var0), (var0[2] & 4) >>> 2, var0, "SVE inc/dec register by element count");
                     }

                     if ((var15 & 6) == 6) {
                        return UC.pC(hE, kS(var0), (var0[1] & 16) >>> 2 | UT(var0), var0, "SVE saturating inc/dec register by element count");
                     }
                  }
               }
            }
         }

         return null;
      }
   }

   private static tz gp(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 56) == 0) {
         int var2 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
         if (var2 == 2) {
            int var13 = (var0[1] & 64) >>> 6;
            return zL[var13];
         }

         int var3 = (var0[1] & 64) >>> 6;
         if (var3 == 0) {
            return oc;
         }
      } else if ((var1 & 48) == 0) {
         switch (var1 & 7) {
            case 0:
               return mE;
            case 1:
               int var10 = kS(var0);
               if ((var10 & 2) == 0 && (var10 != 1 || (var0[1] & 16) == 0)) {
                  return GM[var10];
               }

               return null;
            case 2:
               return TS;
            case 3:
               return LQ;
            case 4:
               return of;
            case 5:
               return dv;
            case 6:
               int var9 = (var0[1] & 24) >>> 3;
               int var14 = var0[1] & 7;
               int var4 = (var0[2] & 2) >>> 1;
               int var5 = E(var0);
               if (var9 == 0) {
                  if (var14 == 0) {
                     return iG;
                  }

                  if (var14 == 4) {
                     return Pr;
                  }
               } else if (var9 == 1) {
                  if ((var14 & 1) == 0 && var5 == 0) {
                     int var21 = kS(var0);
                     int var22 = (var0[1] & 6) >>> 1;
                     if (var21 == 0 && var22 == 0) {
                        return null;
                     }

                     int var23 = Gq.pC((long)(var21 << 2 | var22));
                     return ti[var23];
                  }

                  if ((var14 & 1) == 1 && var4 == 0) {
                     int var6 = kS(var0);
                     int var7 = (var0[1] & 6) >>> 1;
                     if (var6 == 0 && var7 == 0) {
                        return null;
                     }

                     int var8 = Gq.pC((long)(var6 << 2 | var7));
                     return nd[var8];
                  }
               } else if (var9 == 2) {
                  if (var14 < 4) {
                     return Nw[var0[1] & 3];
                  }

                  if (var14 == 4) {
                     return Tl;
                  }
               } else if (var9 == 3 && var14 == 0) {
                  return Jl;
               }
               break;
            default:
               return null;
         }
      } else if ((var1 & 56) == 16) {
         int var11 = kS(var0);
         int var15 = var0[1] & 31;
         int var17 = (var0[2] & 14) >>> 1;
         int var19 = E(var0);
         if (var19 == 0) {
            if (var11 == 0 && (var15 & 30) == 16 && var17 == 0) {
               return gS[var0[1] & 3];
            }

            if ((var15 & 16) == 0 && (var17 & 1) == 0) {
               return aX;
            }

            if (var15 == 20 && var17 == 0) {
               return Jy;
            }
         }
      } else {
         if ((var1 & 56) == 24) {
            return mx;
         }

         if ((var1 & 48) == 32) {
            int var12 = (var0[1] & 16) >>> 4;
            int var16 = (var0[1] & 14) >>> 1;
            int var18 = var0[1] & 1;
            int var20 = (var0[2] & 32) >>> 5;
            if (var12 == 0) {
               if (var16 == 0) {
                  return Vw[var20 << 1 | var18];
               }

               if (var16 == 1 && var20 == 0) {
                  return bK[var18];
               }

               if ((var16 & 6) == 2 && var20 == 0) {
                  return VT[var0[1] & 3];
               }

               if (var16 == 4) {
                  return Gn[var20 << 1 | var18];
               }

               if (var16 == 5 && var20 == 0) {
                  return Do[var18];
               }

               if (var16 == 6 && var20 == 0) {
                  return QA[var18];
               }

               if (var16 == 7 && var20 == 0 && var18 == 0 && kS(var0) == 0) {
                  return HZ;
               }
            } else if (var16 == 0 && var20 != 0) {
               return Tb[var18];
            }
         } else if ((var1 & 48) == 48) {
            return Uf;
         }
      }

      return null;
   }

   private static tz oT(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      if ((var1 & 2) == 0) {
         return var2 < 16 ? fI(var0) : WR(var0);
      } else {
         return var2 < 16 ? NS(var0) : vP(var0);
      }
   }

   private static tz fI(byte[] var0) {
      int var1 = (var0[2] & 64) >>> 6;
      if (var1 == 0) {
         int var2 = (var0[2] & 128) >>> 5 | (var0[2] & 32) >>> 4 | E(var0);
         return (var2 & 6) == 2 ? QW : Sx;
      } else {
         return af;
      }
   }

   private static tz WR(byte[] var0) {
      return ZO;
   }

   private static tz NS(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 62) >>> 1;
      int var2 = (var0[2] & 252) >>> 2;
      if ((var2 & 16) == 0) {
         int var3 = (var0[2] & 128) >>> 5 | (var0[2] & 32) >>> 4 | E(var0);
         if (var3 < 6) {
            return Mv;
         }
      } else {
         if ((var1 & 8) == 0 && (var2 & 48) == 16) {
            int var11 = kS(var0) << 2 | var0[2] & 2 | E(var0);
            return Ez[var11];
         }

         if ((var1 & 8) == 0 && (var2 & 48) == 48) {
            if ((var0[2] & 2) == 0) {
               int var10 = kS(var0) << 1 | E(var0);
               return UC.pC(FR, var10, var0, "SVE propagate break from previous partition");
            }
         } else if ((var1 & 8) == 8 && (var2 & 48) == 16) {
            int var9 = (var0[1] & 128) >>> 7;
            int var12 = var0[1] & 15;
            int var13 = (var0[2] & 2) >>> 1;
            int var14 = E(var0);
            if (var9 == 0 && var12 == 8 && var13 == 0 && var14 == 0) {
               return qn[kS(var0) & 1];
            }

            if (var12 == 0 && var13 == 0) {
               return ko[kS(var0)];
            }
         } else if ((var1 & 8) == 8 && (var2 & 48) == 48) {
            int var8 = var0[1] & 15;
            int var4 = (var0[2] & 56) >>> 3;
            int var5 = (var0[2] & 6) >>> 1;
            int var6 = (var0[2] & 1) << 3 | (var0[3] & 224) >>> 5;
            int var7 = E(var0);
            if (var8 == 0 && (var5 & 1) == 0 && var7 == 0) {
               if (kS(var0) == 1 && (var0[3] & 15) == 0) {
                  return xr;
               }
            } else if (var8 == 8 && var4 == 0 && var5 == 0 && var7 == 0) {
               if (kS(var0) == 1) {
                  return qP;
               }
            } else if (var8 == 8 && var4 == 4 && var5 == 2 && var6 == 0 && var7 == 0) {
               if (kS(var0) == 0) {
                  return HY;
               }
            } else if (var8 == 8 && var4 == 6 && var5 == 0 && var7 == 0) {
               if ((kS(var0) & 2) == 0) {
                  return cM;
               }
            } else {
               if (var8 == 9 && var4 == 0 && var5 == 2 && var7 == 0) {
                  return YR;
               }

               if (var8 == 9 && var4 == 6 && var5 == 0 && var6 == 0 && var7 == 0) {
                  if (kS(var0) == 0) {
                     return hH;
                  }
               } else if ((var8 & 14) == 8 && var4 == 4 && var5 < 2 && var7 == 0) {
                  return YH;
               }
            }
         }
      }

      return null;
   }

   private static tz vP(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 48) == 0) {
         int var2 = (var0[2] & 48) >>> 4;
         int var3 = UT(var0);
         int var4 = var0[3] & 15;
         if (var2 < 2) {
            return iY;
         }

         if (var2 == 2 && var3 == 0 && var4 == 0) {
            if ((kS(var0) & 2) != 0) {
               return gx;
            }
         } else if (var2 == 3 && var3 == 0) {
            return tQ;
         }
      } else if ((var1 & 48) == 16) {
         int var7 = E(var0);
         if (var7 != 0) {
            int var10 = var0[1] & 31;
            int var15 = (var0[2] & 56) >>> 3;
            if ((var15 & 2) == 0) {
               return FS;
            }

            if ((var15 & 4) == 0) {
               return VA;
            }

            if (var15 == 6 && var10 == 0) {
               int var5 = var0[2] & 7;
               if (var5 < 4) {
                  return is;
               }

               if (var5 < 6) {
                  return tP;
               }
            } else if (var15 == 7 && var10 == 0 && (var0[2] & 7) == 0 && (var0[3] & 232) == 0) {
               return YB;
            }

            return null;
         }

         if ((var0[2] & 2) == 0) {
            return ou;
         }
      } else if ((var1 & 48) == 48) {
         int var8 = (var0[1] & 24) >>> 3;
         int var11 = var0[1] & 1;
         if (var8 == 0) {
            return YL[var0[1] & 7];
         }

         if (var8 == 1) {
            return (var0[2] & 32) == 0 ? UC.pC(yh, var0[1] & 7, var0, "SVE integer min/max immediate (unpredicated)") : null;
         }

         if (var8 == 2) {
            return (var0[2] & 32) == 0 ? UC.pC(ln, var0[1] & 7, var0, "SVE integer multiply immediate (unpredicated)") : null;
         }

         if (var8 == 3 && var11 == 0) {
            if ((var0[1] & 6) == 0) {
               return Tv;
            }
         } else if (var8 == 3 && var11 == 1 && (var0[1] & 6) == 0 && (var0[2] & 32) == 0) {
            return Uy;
         }
      } else {
         int var9 = (var0[1] & 62) >>> 1;
         if ((var9 & 28) == 16) {
            int var12 = (var0[2] & 56) >>> 3;
            int var16 = (var0[2] & 2) >>> 1;
            int var19 = var0[1] & 7;
            if (var16 == 0) {
               if (var19 == 0) {
                  return UV;
               }
            } else if (var12 == 0 && var19 == 0) {
               return Ra;
            }
         } else {
            if ((var9 & 28) == 20 && (var1 & 60) == 32) {
               int var14 = (var0[1] & 4) >>> 1 | (var0[2] & 8) >>> 3;
               int var18 = (var0[1] & 3) << 2 | (var0[2] & 6) >>> 1;
               return UC.pC(Pp, var14, var18, var0, "SVE Inc/Dec by Predicate Count");
            }

            if ((var9 & 28) == 20 && (var1 & 60) == 36) {
               int var13 = var0[1] & 7;
               int var17 = (var0[2] & 14) >>> 1;
               int var20 = (var0[2] & 1) << 3 | (var0[3] & 224) >>> 5;
               int var6 = var0[3] & 31;
               if (var13 == 0 && var17 == 0 && var6 == 0) {
                  if (kS(var0) == 0) {
                     return Bt;
                  }
               } else if (var13 == 4 && var17 == 0 && var20 == 0 && var6 == 0 && kS(var0) == 0) {
                  return zj;
               }
            }
         }
      }

      return null;
   }

   private static tz xC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      if ((var1 & 2) == 0) {
         return var2 < 16 ? ED(var0) : Sc(var0);
      } else {
         return var2 < 16 ? ah(var0) : eP(var0);
      }
   }

   private static tz ED(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      if ((var1 & 32) == 0) {
         int var6 = (var0[2] & 124) >>> 2;
         if (var6 < 4) {
            return On[var6];
         } else if (var6 < 8) {
            return Cq;
         } else {
            return var6 < 16 ? kI[(var6 & 4) >>> 2] : UC.pC(IF, var6 & 15, var0, "SVE Integer Multiply-Add - Unpredicated");
         }
      } else {
         if ((var1 & 16) == 0) {
            int var2 = (var0[1] & 30) >>> 1;
            int var3 = (var0[2] & 32) >>> 5;
            if (var3 == 0) {
               if (var2 < 8) {
                  return Zu;
               }

               if (var2 < 12) {
                  return eg;
               }

               return tj;
            }

            if (var2 == 2) {
               return Yq[var0[1] & 1];
            }

            if ((var2 & 10) == 0) {
               return UC.pC(zw, (var0[1] & 3) << 1 | (var0[1] & 8) >>> 3, var0, "SVE2 integer unary operations (predicated)");
            }

            if ((var2 & 12) == 8) {
               return UC.pC(Tu, var0[1] & 7, var0, "SVE2 integer unary operations (predicated)");
            }
         } else {
            if ((var1 & 8) != 0) {
               int var5 = (var0[2] & 28) >>> 2;
               return UC.pC(ok, var5, var0, "SVE permute vector elements (quadwords)");
            }

            if ((var1 & 4) != 0) {
               return Gy[UT(var0)];
            }

            if ((var1 & 2) == 0) {
               return kc[wS(var0)];
            }

            int var4 = kS(var0);
            if (var4 == 0) {
               return Uz[wS(var0)];
            }

            if (var4 == 2) {
               return At[wS(var0)];
            }
         }

         return null;
      }
   }

   private static tz Sc(byte[] var0) {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 1) {
         case 0:
            int var14 = kS(var0);
            if (var14 < 2) {
               return null;
            }

            return XS[(var14 & 1) << 1 | wS(var0)];
         case 1:
            int var13 = kS(var0) << 1 | wS(var0);
            return pU[var13 < 2 ? var13 + 2 : var13];
         case 2:
            int var12 = kS(var0) << 1 | wS(var0);
            return Ga[var12 < 2 ? var12 + 2 : var12];
         case 3:
            int var11 = kS(var0);
            if (var11 >= 2 && var11 != 3) {
               return Kj[wS(var0)];
            }

            return null;
         case 4:
         case 5:
         case 6:
         case 7:
            int var10 = kS(var0);
            if (var10 < 2) {
               return null;
            }

            return vj[(var10 & 1) << 2 | (var0[2] & 16) >>> 3 | wS(var0)];
         case 8:
         case 9:
            int var9 = kS(var0);
            if (var9 < 2) {
               return null;
            }

            return DC[var9 & 1];
         case 10:
         case 11:
         default:
            return null;
         case 12:
         case 13:
            int var8 = kS(var0);
            if (var8 < 2) {
               return null;
            }

            return jT[var8 & 1];
         case 14:
         case 15:
            int var7 = kS(var0);
            if (var7 < 2) {
               return null;
            }

            return pT[var7 & 1];
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
            int var6 = kS(var0);
            if (var6 < 2) {
               return null;
            }

            return uz[(var6 & 1) << 3 | (var0[2] & 48) >>> 3 | wS(var0)];
         case 24:
         case 25:
         case 26:
         case 27:
            int var5 = kS(var0);
            if (var5 < 2) {
               return null;
            }

            return fG[(var5 & 1) << 2 | (var0[2] & 16) >>> 3 | wS(var0)];
         case 28:
         case 29:
            int var4 = kS(var0);
            if (var4 < 2) {
               return null;
            }

            return Oo[(var4 & 1) << 1 | wS(var0)];
         case 30:
            int var3 = kS(var0) << 1 | wS(var0);
            return jm[var3 < 2 ? var3 + 2 : var3];
         case 31:
            if ((var1 & 1) == 0) {
               int var2 = kS(var0);
               return eT[var2 == 0 ? 1 : var2];
            } else {
               return null;
            }
      }
   }

   private static tz ah(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 4) {
         case 2:
            int var6 = (var0[2] & 60) >>> 2;
            if (var6 == 6) {
               return UC.pC(ym, kS(var0), var0, "SVE integer matrix multiply accumulate");
            } else {
               if ((var6 & 12) == 8 && (kS(var0) & 2) != 0) {
                  return null;
               }

               return UC.pC(fr, var6, var0, "SVE Misc");
            }
         case 3:
            int var5 = (var0[2] & 60) >>> 2;
            int var4 = var5 >>> 1;
            if (var4 == 2) {
               return UC.pC(Lr, kS(var0) & 2 | wS(var0), var0, "SVE2 integer add/subtract long with carry");
            } else {
               if (var4 == 3) {
                  if ((var0[1] & 30) != 0) {
                     return null;
                  }

                  var5 = var5 & 14 | var0[1] & 1;
               }

               return UC.pC(Ms, var5, var0, "SVE Misc");
            }
         default:
            int var2 = (var0[2] & 96) >>> 5;
            if (var2 < 2) {
               return Ew;
            } else if (var2 == 2) {
               return rp;
            } else if (var2 == 3) {
               int var3 = (var0[2] & 28) >>> 2;
               return (var3 & 6) == 2 && kS(var0) == 0 ? dK[var3 & 1] : UC.pC(uA, var3, var0, "SVE2 integer multiply long");
            } else {
               return null;
            }
      }
   }

   private static tz eP(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 3) {
         case 4:
            return cg[E(var0)];
         case 5:
            int var8 = (var0[2] & 28) >>> 2;
            if (var8 == 1 && (var0[1] & 64) == 0) {
               return null;
            }

            return UC.pC(HL, var8, var0, "SVE2 Histogram Computation (Segment) and Lookup Table");
         case 6:
            return gn;
         case 7:
            int var7 = var0[1] & 31;
            int var9 = (var0[2] & 24) >>> 3;
            int var10 = (var0[2] & 3) << 3 | (var0[3] & 224) >>> 5;
            if (var9 == 0) {
               if (var7 == 0 && var10 == 0) {
                  if (kS(var0) == 0) {
                     return hd[wS(var0)];
                  }
               } else if ((var7 & 30) == 2 && kS(var0) == 0) {
                  return UC.pC(xQ, (var0[1] & 1) << 1 | wS(var0), var0, "SVE2 crypto destructive binary operations");
               }
            } else if (var9 == 2 && kS(var0) == 0) {
               return zf[wS(var0)];
            }

            return null;
         default:
            int var2 = (var0[1] & 128) >>> 7;
            int var3 = var0[1] & 7;
            int var4 = (var0[2] & 96) >>> 5;
            int var5 = wS(var0);
            int var6 = (var0[3] & 32) >>> 5;
            if (var4 < 2) {
               if (var2 == 0) {
                  return Pi;
               }

               if (var5 == 0 && var6 == 0 && (var0[1] & 80) == 16 && (var0[2] & 8) == 8) {
                  return Df;
               }
            } else if (var4 == 2) {
               if (var2 == 0 && var3 == 0) {
                  return xS;
               }

               if (var2 == 0 && var3 == 1 && var5 == 0 && var6 == 0 && (var0[1] & 88) == 16) {
                  return MC;
               }
            } else if (var4 == 3) {
               return Jd;
            }

            return null;
      }
   }

   private static tz UO(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 62) >>> 1;
      if ((var1 & 2) == 0) {
         return var2 < 16 ? Ab(var0) : rl(var0);
      } else {
         return var2 < 16 ? z(var0) : Ek(var0);
      }
   }

   private static tz Ab(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      int var2 = (var0[1] & 30) >>> 1;
      if ((var1 & 32) == 0) {
         return rJ;
      } else {
         if ((var1 & 24) == 0) {
            if (var2 == 0) {
               return yk;
            }

            if ((var2 & 12) == 8) {
               return DX;
            }
         } else if ((var1 & 24) == 8) {
            if ((var2 & 14) == 4) {
               return UC.pC(jU, kS(var0), var0[1] & 3, var0, "SVE floating-point convert precision odd elements");
            }

            if ((var2 & 12) == 8) {
               return UC.pC(sb, var0[1] & 7, var0, "SVE floating-point recursive reduction (quadwords)");
            }
         }

         return null;
      }
   }

   private static tz rl(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      int var2 = kS(var0);
      switch (var1 >>> 2) {
         case 0:
            int var3 = var2 << 2 | UT(var0);
            return UC.pC(OV, var3 < 4 ? var3 + 4 : var3, var0, "SVE floating-point multiply-add (indexed)");
         case 1:
            if (var2 < 2) {
               return null;
            }

            return IS[var2 & 1];
         case 2:
            if ((var1 & 1) == 0) {
               if ((var0[2] & 8) != 0) {
                  return zt;
               }

               return pl[var2 == 0 ? 1 : var2];
            } else {
               if ((var1 & 3) == 1) {
                  return var2 == 0 ? Yc : Sj;
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
               return UC.pC(jZ, (var2 & 1) << 2 | (var0[2] & 32) >>> 4 | (var0[2] & 4) >>> 2, var0, "SVE floating-point multiply-add long (indexed)");
            } else {
               if ((var0[2] & 32) == 0) {
                  return UC.pC(ji, var2 & 1, (var0[2] & 12) >>> 2, var0, "SVE BFloat16 floating-point dot product (indexed)");
               }

               return null;
            }
         case 5:
            if ((kS(var0) & 1) == 0) {
               return fZ;
            }

            return null;
         case 8:
         case 9:
         case 10:
         case 11:
            if ((var1 & 6) == 0) {
               if ((var2 & 2) != 0) {
                  return UC.pC(qk, (var2 & 1) << 2 | (var0[2] & 32) >>> 4 | (var0[2] & 4) >>> 2, var0, "SVE floating-point multiply-add long");
               }

               if ((var0[2] & 32) == 0) {
                  return UC.pC(lB, (var2 & 1) << 1 | (var0[2] & 4) >>> 2, var0, "SVE BFloat16 floating-point dot product");
               }
            }

            if ((var1 & 3) == 2 && (kS(var0) & 1) == 0) {
               if ((var2 & 2) == 0) {
                  return Yl;
               }

               if ((var0[2] & 32) == 0) {
                  return kR;
               }
            }

            return null;
         case 12:
            return LN;
         case 14:
            return (var1 & 3) == 1 ? CI[var2] : null;
      }
   }

   private static tz z(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      int var2 = kS(var0);
      switch (var1 >>> 3) {
         case 0:
            int var9 = (var0[2] & 28) >>> 2;
            if (kS(var0) == 0 && var9 <= 2) {
               return rb;
            }

            return vb;
         case 1:
            int var8 = (var0[1] & 24) >>> 3;
            switch (var8) {
               case 0:
                  return ZQ;
               case 1:
                  if ((var1 & 4) != 0) {
                     int var11 = var0[1] & 7;
                     if (var11 < 2 && var2 == 0) {
                        return NY;
                     }

                     if (var11 == 2 && var2 == 0 && (var0[3] & 32) == 0) {
                        return tr[(var0[2] & 12) >>> 2];
                     }

                     if (var11 >= 6 && (var0[2] & 12) == 0) {
                        return uF;
                     }
                  }

                  return null;
               case 2:
                  if ((var0[1] & 4) == 0) {
                     long var10 = os.decode(var0);
                     if (var10 <= 4L || (var10 & 1L) == 0L) {
                        return Nv;
                     }
                  }

                  return null;
               case 3:
                  if ((var0[1] & 4) == 0) {
                     return UC.pC(OX, var0[1] & 3, var0, "SVE floating-point serial reduction (predicated)");
                  }

                  return null;
               default:
                  return null;
            }
         case 2:
         case 3:
         case 6:
         case 7:
            long var7 = EX.decode(var0);
            if (var7 <= 4L) {
               return Dp;
            } else {
               if ((var7 & 1L) != 0L) {
                  return oC;
               }

               return null;
            }
         case 4:
            int var6 = (var0[1] & 24) >>> 3;
            if (var6 < 2) {
               int var4 = var0[1] & 15;
               if (kS(var0) == 0 && var4 <= 8) {
                  return UR;
               } else {
                  if (var4 >= 14) {
                     return WH;
                  }

                  return tu;
               }
            } else if (var6 == 2 && (var0[2] & 28) == 0) {
               return ta;
            } else {
               if (var6 == 3 && DirectEncodedMemoryArea.get(6, 4).decodeInt(var0) == 0) {
                  return GC[var0[1] & 7];
               }

               return null;
            }
         case 5:
            switch ((var0[1] & 28) >>> 2) {
               case 0:
               case 1:
                  if (Cu.decode(var0) != 5L) {
                     return kK;
                  }

                  return null;
               case 2:
                  return UC.pC(EV, var2, var0[1] & 3, var0, "SVE floating-point convert precision");
               case 3:
                  return UC.pC(bi, var0[1] & 3, var0, "SVE floating-point unary operations");
               case 4:
               case 5:
                  return UC.pC(Oa, var2, var0[1] & 7, var0, "SVE integer convert to floating-point");
               case 6:
               case 7:
                  int var3 = var0[1] & 7;
                  if (var2 == 0 && (var3 & 1) == 0) {
                     var3 = 0;
                  }

                  return UC.pC(eo, var2, var3, var0, "SVE floating-point convert to integer");
               default:
                  return null;
            }
         default:
            return null;
      }
   }

   private static tz Ek(byte[] var0) {
      int var1 = (var0[2] & 128) >>> 7;
      if (var1 == 0) {
         return kS(var0) == 0 && (var0[2] & 64) == 0 ? Zm : VZ;
      } else {
         return Qc;
      }
   }
}
