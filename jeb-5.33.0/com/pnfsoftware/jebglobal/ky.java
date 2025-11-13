package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractImmediateOperandBuilder;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class ky {
   private static final IEncodedMemoryArea A = DirectEncodedMemoryArea.get(0, 5);
   private static final IEncodedMemoryArea kS = KU.pC;
   private static final IEncodedMemoryArea wS = KU.A;
   private static final IEncodedMemoryArea UT = DirectEncodedMemoryArea.get(14, 1);
   private static final IEncodedMemoryArea E = KU.kS;
   private static final IEncodedMemoryArea sY = KU.wS;
   private static final IEncodedMemoryArea ys = DirectEncodedMemoryArea.get(16, 6);
   private static final IEncodedMemoryArea ld = DirectEncodedMemoryArea.get(21, 2);
   private static final IEncodedMemoryArea gp = DirectEncodedMemoryArea.get(21, 4);
   private static final IEncodedMemoryArea oT = KU.UT;
   private static final IEncodedMemoryArea fI = KU.E;
   private static final IEncodedMemoryArea WR = DirectEncodedMemoryArea.get(23, 2);
   private static final IEncodedMemoryArea NS = new EncodedMemoryAreaList(WR, kS);
   private static final IEncodedMemoryArea vP = new EncodedMemoryAreaList(WR, wS);
   private static final IEncodedMemoryArea xC = new EncodedMemoryAreaList(WR, UT);
   private static final ZW ED = KU.sY;
   private static final Hu Sc = LF.pC(sQ.NS, sQ.ld);
   private static final Hu ah = LF.pC(sQ.NS, sQ.ld, Dj.pC, VirtualEncodedMemoryArea._1);
   private static final Hu eP = LF.pC(sQ.NS, sQ.ld, Dj.pC, VirtualEncodedMemoryArea._2);
   private static final Hu UO = LF.pC(sQ.NS, sQ.ld, Dj.pC, VirtualEncodedMemoryArea._4);
   private static final Hu Ab = LF.pC(sQ.NS, sQ.ld, Dj.pC, WR);
   private static final Hu rl = LF.pC(sQ.NS, sQ.fI);
   private static final Hu z = LF.pC(sQ.NS, sQ.fI, Dj.pC, VirtualEncodedMemoryArea._1);
   private static final Hu Ek = LF.pC(sQ.NS, sQ.fI, Dj.pC, VirtualEncodedMemoryArea._2);
   private static final Hu hK = LF.pC(sQ.NS, sQ.fI, Dj.pC, WR);
   private static final Hu Er = LF.pC(sQ.NS, aP.FK, new EncodedMemoryAreaList(UT, VirtualEncodedMemoryArea.get(2, 2)), null, LF.wS);
   private static final Hu FE = LF.pC(sQ.NS, aP.FK, new EncodedMemoryAreaList(UT, VirtualEncodedMemoryArea.get(2, 2)), WR, LF.wS);
   private static final Hu Aj = LF.pC(sQ.NS, aP.FK, new EncodedMemoryAreaList(oT, VirtualEncodedMemoryArea.get(2, 2)), null, LF.wS);
   private static final Hu EX = LF.pC(sQ.NS, aP.FK, new EncodedMemoryAreaList(oT, VirtualEncodedMemoryArea.get(2, 2)), VirtualEncodedMemoryArea._1, LF.wS);
   private static final Hu LM = LF.pC(sQ.NS, aP.FK, new EncodedMemoryAreaList(oT, VirtualEncodedMemoryArea.get(2, 2)), VirtualEncodedMemoryArea._2, LF.wS);
   private static final Hu mv = LF.pC(sQ.NS, aP.FK, new EncodedMemoryAreaList(oT, VirtualEncodedMemoryArea.get(2, 2)), wS, LF.wS);
   private static final Hu sO = LF.pC(sQ.NS, aP.Bi);
   private static final Hu os = LF.pC(sQ.NS, aP.Bi, new EncodedMemoryAreaList(UT, VirtualEncodedMemoryArea.get(2, 2)), null, LF.wS);
   private static final Hu Cu = LF.pC(sQ.NS, aP.Bi, new EncodedMemoryAreaList(UT, VirtualEncodedMemoryArea.get(2, 2)), WR, LF.wS);
   private static final Hu hZ = LF.pC(sQ.NS, aP.Bi, new EncodedMemoryAreaList(oT, VirtualEncodedMemoryArea.get(2, 2)), null, LF.wS);
   private static final Hu UW = LF.pC(sQ.NS, aP.Bi, new EncodedMemoryAreaList(oT, VirtualEncodedMemoryArea.get(2, 2)), wS, LF.wS);
   private static final Hu PR = LF.pC(sQ.NS, aP.Bi, new EncodedMemoryAreaList(oT, VirtualEncodedMemoryArea.get(2, 2)), WR, LF.wS);
   private static final Hu cX = LF.pC(sQ.NS, aP.Bi, Dj.pC, wS);
   private static final Hu DQ = LF.pC(sQ.NS, aP.Bi, Dj.pC, WR);
   private static final Hu ZN = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(E, 4), LF.kS);
   private static final Hu OB = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(E, 5), LF.kS);
   private static final Hu pF = pC(sQ.NS, IV.fI);
   private static final Hu Bc = pC(sQ.NS, new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, TN.pC(E, true, TN.pC(ld), false)));
   private static final Hu OI = pC(sQ.NS, new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, TN.pC(E, true, TN.pC(fI), false)));
   private static final Hu Bf = pC(sQ.NS, new IV(AbstractImmediateOperandBuilder.ImmediateType.SignExtend32, 65536, TN.pC(E, true, TN.pC(WR), false)));
   private static final Hu Pe = LF.pC(aP.ZN, TN.kS(sY, WR));
   private static final Hu ck = LF.pC(aP.OB, TN.kS(sY, WR));
   private static final Hu RW = LF.pC(sQ.NS, ys);
   private static final Hu e = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(ys, 1));
   private static final Hu xM = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(ys, 2));
   private static final Hu kU = LF.pC(sQ.NS, TN.kS(ys, WR));
   private static final Hu Kq = pC(sQ.NS, IV.hK);
   private static final Hu go = pC(sQ.NS, IV.ZN);
   private static final Hu JF = LF.pC(aP.ZN, sQ.fI);
   private static final Hu Nq = LF.pC(aP.OB, sQ.fI);
   private static final Hu pg = new Fw(64, aP.UO);
   private static final Hu gj = new Fw(64, aP.Ab);
   private static final Hu ZD = new Fw(64, aP.rl);
   private static final Hu DL = new Fw(64, aP.z);
   private static final Hu UH = new Fw(64, aP.hK);
   private static final Hu VD = new Fw(64, aP.FE);
   private static final Hu Xs = new Fw(64, aP.Aj);
   private static final Hu KV = new Yq(A, ld, IX.eP);
   private static final Hu FK = new Yq(A, fI, IX.UT);
   private static final Hu Bi = new Yq(A, WR, IX.UT);
   private static final String[] wQ = new String[]{
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
   public static final Ag pC = new Ag(Ll.Av.pC, wQ, DirectEncodedMemoryArea.get(0, 5), "#%d");
   private static final Hu[] PZ = new Hu[]{pg, aP.dM, Sc};
   private static final Hu[] Ip = new Hu[]{gj, aP.dM, Sc};
   private static final Hu[] Fm = new Hu[]{ZD, aP.dM, Sc};
   private static final Hu[] FM = new Hu[]{gj, aP.dM, ah};
   private static final Hu[] Wn = new Hu[]{ZD, aP.dM, ah};
   private static final Hu[] gy = new Hu[]{ZD, aP.dM, eP};
   private static final Hu[] pt = new Hu[]{pg, aP.dM, rl};
   private static final Hu[] uE = new Hu[]{gj, aP.dM, rl};
   private static final Hu[] Um = new Hu[]{ZD, aP.dM, rl};
   private static final Hu[] Ta = new Hu[]{gj, aP.dM, z};
   private static final Hu[] So = new Hu[]{ZD, aP.dM, z};
   private static final Hu[] tH = new Hu[]{ZD, aP.dM, Ek};
   private static final Hu[] Gm = new Hu[]{gj, aP.dM, Aj};
   private static final Hu[] Br = new Hu[]{gj, aP.dM, EX};
   private static final Hu[] IE = new Hu[]{gj, aP.dM, LM};
   private static final Hu[] AU = new Hu[]{ZD, aP.dM, sO};
   private static final Hu[] jS = new Hu[]{ZD, aP.dM, hZ};
   private static final Hu[] KK = new Hu[]{ZD, aP.dM, PR};
   private static final Hu[] oB = new Hu[]{ZD, aP.dM, DQ};
   private static final Hu[] Rq = new Hu[]{DL, aP.dM, Ab};
   private static final Hu[] LL = new Hu[]{VD, aP.dM, Ab};
   private static final Hu[] rC = new Hu[]{VD, aP.dM, hK};
   private static final Hu[] be = new Hu[]{Xs, aP.dM, Ab};
   private static final Hu[] Xh = new Hu[]{pg, aP.dM, pF};
   private static final Hu[] sz = new Hu[]{gj, aP.dM, pF};
   private static final Hu[] QQ = new Hu[]{ZD, aP.dM, pF};
   private static final Hu[] eE = new Hu[]{DL, aP.dM, pF};
   private static final Hu[] dM = new Hu[]{VD, aP.dM, pF};
   private static final Hu[] EM = new Hu[]{Xs, aP.dM, pF};
   private static final Hu[] fD = new Hu[]{Xs, aP.dM, ZN};
   private static final Hu[] ii = new Hu[]{Xs, aP.dM, OB};
   private static final Hu[] Gu = new Hu[]{ZD, aP.dM, ck};
   private static final Hu[] hw = new Hu[]{pg, aP.dM, RW};
   private static final Hu[] qG = new Hu[]{gj, aP.dM, RW};
   private static final Hu[] yi = new Hu[]{gj, aP.dM, e};
   private static final Hu[] zK = new Hu[]{ZD, aP.dM, e};
   private static final Hu[] LA = new Hu[]{ZD, aP.dM, RW};
   private static final Hu[] ve = new Hu[]{ZD, aP.dM, xM};
   private static final Hu[] yv = new Hu[]{UH, aP.dM, kU};
   private static final Hu[] MZ = new Hu[]{gj, aP.dM, JF};
   private static final Hu[] fH = new Hu[]{ZD, aP.dM, Nq};
   private static final Hu[] ET = new Hu[]{DL, aP.dM, Nq};
   private static final Hu[] kk = new Hu[]{gj, aP.dM, Pe};
   private static final Hu[] Rh = new Hu[]{ZD, aP.QQ, sO};
   private static final Hu[] vv = new Hu[]{ZD, aP.QQ, DQ};
   private static final Hu[] fn = new Hu[]{gj, aP.QQ, Er};
   private static final Hu[] AS = new Hu[]{ZD, aP.QQ, os};
   private static final Hu[] wF = new Hu[]{gj, aP.QQ, FE};
   private static final Hu[] hF = new Hu[]{ZD, aP.QQ, Cu};
   private static final Hu[] FA = new Hu[]{DL, aP.QQ, Ab};
   private static final Hu[] IK = new Hu[]{VD, aP.QQ, Ab};
   private static final Hu[] DM = new Hu[]{Xs, aP.QQ, Ab};
   private static final Hu[] IQ = new Hu[]{VD, aP.QQ, pF};
   private static final Hu[] zR = new Hu[]{DL, aP.QQ, pF};
   private static final Hu[] Ft = new Hu[]{Xs, aP.QQ, pF};
   private static final Hu[] kt = new Hu[]{gj, aP.QQ, Pe};
   private static final Hu[] Yw = new Hu[]{ZD, aP.QQ, ck};
   private static final Hu[] uD = new Hu[]{gj, aP.QQ, JF};
   private static final Hu[] ZY = new Hu[]{ZD, aP.QQ, Nq};
   private static final Hu[] mK = new Hu[]{DL, aP.QQ, Nq};
   private static final Hu[] pW = new Hu[]{KV, aP.dM, Ab};
   private static final Hu[] Gg = new Hu[]{KV, aP.QQ, Ab};
   private static final Hu[] kQ = new Hu[]{KV, aP.dM, Bc};
   private static final Hu[] te = new Hu[]{KV, aP.QQ, Bc};
   private static final Hu[] B = new Hu[]{Bi, aP.dM, UO};
   private static final Hu[] RR = new Hu[]{FK, aP.QQ, UO};
   private static final Hu[] CK = new Hu[]{Bi, aP.dM, Bf};
   private static final Hu[] Eq = new Hu[]{FK, aP.QQ, OI};
   private static final Hu[] y = new Hu[]{aP.ah, go};
   private static final Hu[] JP = new Hu[]{aP.pt, go};
   private static final tz jY = new UC("LDR", aP.pt, go).pC(Le.UT);
   private static final tz ee = new UC("LDR", aP.ah, go).pC(Le.UT);
   private static final tz ho = new fr(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, wS, pC, aP.QQ, Kq).pC(Le.UT);
   private static final tz VE = new fr(
         new String[]{"LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", null, null, "LD1W", "LDFF1W"}, vP, Gm
      )
      .pC(Le.UT);
   private static final tz lt = new fr(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, wS, pC, aP.QQ, mv).pC(Le.UT);
   private static final tz uw = new fr(new String[]{"LD1SH", "LDFF1SH", "LD1H", "LDFF1H"}, wS, Br).pC(Le.UT);
   private static final tz QP = new fr(new String[]{null, null, "LD1W", "LDFF1W"}, wS, IE).pC(Le.UT);
   private static final tz hM = new fr(
         new String[]{"LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", null, null, "LD1W", "LDFF1W"}, vP, kk
      )
      .pC(Le.UT);
   private static final tz MJ = new UC("LD1RB", yv).pC(Le.UT);
   private static final tz OA = new UC("LD1RH", yv).pC(Le.UT);
   private static final tz kT = new UC("LD1RW", yv).pC(Le.UT);
   private static final tz[] x = new tz[]{
      MJ,
      MJ,
      MJ,
      MJ,
      new UC("LD1RSW", ve).pC(Le.UT),
      OA,
      OA,
      OA,
      new UC("LD1RSH", zK).pC(Le.UT),
      new UC("LD1RSH", yi).pC(Le.UT),
      kT,
      kT,
      new UC("LD1RSB", LA).pC(Le.UT),
      new UC("LD1RSB", qG).pC(Le.UT),
      new UC("LD1RSB", hw).pC(Le.UT),
      new UC("LD1RD", yv).pC(Le.UT)
   };
   private static final tz un = new fr(new String[]{"LDNT1SB", "LDNT1B", "LDNT1SH", "LDNT1H", null, "LDNT1W"}, NS, MZ).pC(Le.E);
   private static final tz JV = new fr(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, WR, pC, aP.QQ, Sc).pC(Le.UT);
   private static final tz Iq = new fr(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, WR, pC, aP.QQ, Pe).pC(Le.UT);
   private static final tz[][] mV = new tz[][]{
      {new UC("LD1RQB", be).pC(Le.UT), new UC("LD1ROB", be).pC(Le.kU)},
      {new UC("LD1RQH", be).pC(Le.UT), new UC("LD1ROH", be).pC(Le.kU)},
      {new UC("LD1RQW", be).pC(Le.UT), new UC("LD1ROW", be).pC(Le.kU)},
      {new UC("LD1RQD", be).pC(Le.UT), new UC("LD1ROD", be).pC(Le.kU)}
   };
   private static final tz[][] Gh = new tz[][]{
      {new UC("LD1RQB", fD).pC(Le.UT), new UC("LD1ROB", ii).pC(Le.kU)},
      {new UC("LD1RQH", fD).pC(Le.UT), new UC("LD1ROH", ii).pC(Le.kU)},
      {new UC("LD1RQW", fD).pC(Le.UT), new UC("LD1ROW", ii).pC(Le.kU)},
      {new UC("LD1RQD", fD).pC(Le.UT), new UC("LD1ROD", ii).pC(Le.kU)}
   };
   private static final tz HG = new fr(new String[]{null, null, "LD1W", "LD1D"}, WR, eE).pC(Le.sY);
   private static final tz yC = new UC("LD1B", LL).pC(Le.UT);
   private static final tz uu = new UC("LD1H", LL).pC(Le.UT);
   private static final tz Tq = new UC("LD1W", LL).pC(Le.UT);
   private static final tz[] HO = new tz[]{
      yC,
      yC,
      yC,
      yC,
      new UC("LD1SW", gy).pC(Le.UT),
      uu,
      uu,
      uu,
      new UC("LD1SH", Wn).pC(Le.UT),
      new UC("LD1SH", FM).pC(Le.UT),
      Tq,
      Tq,
      new UC("LD1SB", Fm).pC(Le.UT),
      new UC("LD1SB", Ip).pC(Le.UT),
      new UC("LD1SB", PZ).pC(Le.UT),
      new UC("LD1D", LL).pC(Le.UT)
   };
   private static final tz Is = new UC("LDFF1B", rC).pC(Le.UT);
   private static final tz BP = new UC("LDFF1H", rC).pC(Le.UT);
   private static final tz Wm = new UC("LDFF1W", rC).pC(Le.UT);
   private static final tz[] TP = new tz[]{
      Is,
      Is,
      Is,
      Is,
      new UC("LDFF1SW", tH).pC(Le.UT),
      BP,
      BP,
      BP,
      new UC("LDFF1SH", So).pC(Le.UT),
      new UC("LDFF1SH", Ta).pC(Le.UT),
      Wm,
      Wm,
      new UC("LDFF1SB", Um).pC(Le.UT),
      new UC("LDFF1SB", uE).pC(Le.UT),
      new UC("LDFF1SB", pt).pC(Le.UT),
      new UC("LDFF1D", rC).pC(Le.UT)
   };
   private static final tz gd = new fr(new String[]{null, null, "LD1W", "LD1D"}, WR, Rq).pC(Le.sY);
   private static final tz eI = new fr(new String[]{null, "LD2Q", "LD3Q", "LD4Q"}, WR, B).pC(Le.sY);
   private static final tz lZ = new UC("LD1B", dM).pC(Le.UT);
   private static final tz AQ = new UC("LD1H", dM).pC(Le.UT);
   private static final tz BX = new UC("LD1W", dM).pC(Le.UT);
   private static final tz[] xg = new tz[]{
      lZ,
      lZ,
      lZ,
      lZ,
      new UC("LD1SW", QQ).pC(Le.UT),
      AQ,
      AQ,
      AQ,
      new UC("LD1SH", QQ).pC(Le.UT),
      new UC("LD1SH", sz).pC(Le.UT),
      BX,
      BX,
      new UC("LD1SB", QQ).pC(Le.UT),
      new UC("LD1SB", sz).pC(Le.UT),
      new UC("LD1SB", Xh).pC(Le.UT),
      new UC("LD1D", dM).pC(Le.UT)
   };
   private static final tz NN = new UC("LDNF1B", dM).pC(Le.UT);
   private static final tz np = new UC("LDNF1H", dM).pC(Le.UT);
   private static final tz ik = new UC("LDNF1W", dM).pC(Le.UT);
   private static final tz[] aK = new tz[]{
      NN,
      NN,
      NN,
      NN,
      new UC("LDNF1SW", QQ).pC(Le.UT),
      np,
      np,
      np,
      new UC("LDNF1SH", QQ).pC(Le.UT),
      new UC("LDNF1SH", sz).pC(Le.UT),
      ik,
      ik,
      new UC("LDNF1SB", QQ).pC(Le.UT),
      new UC("LDNF1SB", sz).pC(Le.UT),
      new UC("LDNF1SB", Xh).pC(Le.UT),
      new UC("LDNF1D", QQ).pC(Le.UT)
   };
   private static final tz gR = new fr(new String[]{"LDNT1B", "LDNT1H", "LDNT1W", "LDNT1D"}, WR, be).pC(Le.UT);
   private static final tz Ig = new fr(
         new String[]{null, "LD2B", "LD3B", "LD4B", null, "LD2H", "LD3H", "LD4H", null, "LD2W", "LD3W", "LD4W", null, "LD2D", "LD3D", "LD4D"}, gp, pW
      )
      .pC(Le.UT);
   private static final tz TV = new fr(new String[]{"LDNT1B", "LDNT1H", "LDNT1W", "LDNT1D"}, WR, EM).pC(Le.UT);
   private static final tz pY = new fr(new String[]{null, "LD2Q", "LD3Q", "LD4Q"}, WR, CK).pC(Le.sY);
   private static final tz l = new fr(
         new String[]{null, "LD2B", "LD3B", "LD4B", null, "LD2H", "LD3H", "LD4H", null, "LD2W", "LD3W", "LD4W", null, "LD2D", "LD3D", "LD4D"}, gp, kQ
      )
      .pC(Le.UT);
   private static final tz Tr = new fr(
         new String[]{
            "LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"
         },
         vP,
         jS
      )
      .pC(Le.UT);
   private static final tz Op = new fr(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, wS, pC, aP.QQ, UW).pC(Le.UT);
   private static final tz yB = new fr(
         new String[]{null, null, null, null, "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"}, vP, KK
      )
      .pC(Le.UT);
   private static final tz Mi = new fr(new String[]{"LDNT1SB", "LDNT1B", "LDNT1SH", "LDNT1H", "LDNT1SW", "LDNT1W", null, "LDNT1D"}, xC, fH).pC(Le.E);
   private static final tz TD = new UC("LD1Q", ET).pC(Le.sY);
   private static final tz mz = new fr(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, WR, pC, aP.QQ, ck).pC(Le.UT);
   private static final tz UJ = new fr(
         new String[]{
            "LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"
         },
         vP,
         Gu
      )
      .pC(Le.UT);
   private static final tz KW = new fr(
         new String[]{
            "LD1SB", "LDFF1SB", "LD1B", "LDFF1B", "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"
         },
         vP,
         AU
      )
      .pC(Le.UT);
   private static final tz NB = new fr(
         new String[]{null, null, null, null, "LD1SH", "LDFF1SH", "LD1H", "LDFF1H", "LD1SW", "LDFF1SW", "LD1W", "LDFF1W", null, null, "LD1D", "LDFF1D"}, vP, oB
      )
      .pC(Le.UT);
   private static final tz ND = new fr(new String[]{"PRFB", "PRFH", "PRFW", "PRFD"}, wS, pC, aP.QQ, cX).pC(Le.UT);
   private static final tz WX = new UC("STR", y).pC(Le.UT);
   private static final tz Ck = new UC("ST1B", IK).pC(Le.UT);
   private static final tz vU = new UC("ST1H", IK).pC(ED);
   private static final tz KM = new UC("ST1W", IK).pC(Le.UT);
   private static final tz[] rI = new tz[]{
      Ck, Ck, Ck, Ck, null, vU, vU, vU, new UC("ST1W", FA).pC(Le.sY), null, KM, KM, null, null, new UC("ST1D", FA).pC(Le.sY), new UC("ST1D", IK).pC(Le.UT)
   };
   private static final tz iX = new UC("STR", JP).pC(Le.UT);
   private static final tz XZ = new fr(new String[]{null, "ST2Q", "ST3Q", "ST4Q"}, fI, Eq).pC(Le.sY);
   private static final tz jj = new fr(new String[]{null, "ST2Q", "ST3Q", "ST4Q"}, fI, RR).pC(Le.sY);
   private static final tz jH = new fr(new String[]{"STNT1B", "STNT1H", "STNT1W", "STNT1D"}, WR, ZY).pC(Le.E);
   private static final tz uJ = new fr(new String[]{"STNT1B", "STNT1H", "STNT1W", null}, WR, uD).pC(Le.E);
   private static final tz Fx = new UC("ST1Q", mK).pC(Le.sY);
   private static final tz hB = new fr(new String[]{"STNT1B", "STNT1H", "STNT1W", "STNT1D"}, WR, DM).pC(Le.UT);
   private static final tz gW = new fr(
         new String[]{null, "ST2B", "ST3B", "ST4B", null, "ST2H", "ST3H", "ST4H", null, "ST2W", "ST3W", "ST4W", null, "ST2D", "ST3D", "ST4D"}, gp, Gg
      )
      .pC(Le.UT);
   private static final tz[] pP = new tz[]{
      new fr(new String[]{"ST1B", "ST1H", "ST1W", "ST1D"}, WR, AS).pC(Le.UT),
      new fr(new String[]{null, "ST1H", "ST1W", "ST1D"}, WR, hF).pC(Le.UT),
      new fr(new String[]{"ST1B", "ST1H", "ST1W", null}, WR, fn).pC(Le.UT),
      new fr(new String[]{null, "ST1H", "ST1W", null}, WR, wF).pC(Le.UT)
   };
   private static final tz[] sd = new tz[]{
      new fr(new String[]{"ST1B", "ST1H", "ST1W", "ST1D"}, WR, Rh).pC(Le.UT),
      new fr(new String[]{null, "ST1H", "ST1W", "ST1D"}, WR, vv).pC(Le.UT),
      new fr(new String[]{"ST1B", "ST1H", "ST1W", "ST1D"}, WR, Yw).pC(Le.UT),
      new fr(new String[]{"ST1B", "ST1H", "ST1W", null}, WR, kt).pC(Le.UT)
   };
   private static final tz OD = new UC("ST1B", IQ).pC(Le.UT);
   private static final tz hq = new UC("ST1H", IQ).pC(ED);
   private static final tz sR = new UC("ST1W", IQ).pC(Le.UT);
   private static final tz[] ib = new tz[]{
      OD, OD, OD, OD, null, hq, hq, hq, new UC("ST1W", zR).pC(Le.sY), null, sR, sR, null, null, new UC("ST1D", zR).pC(Le.sY), new UC("ST1D", IQ).pC(Le.UT)
   };
   private static final tz zJ = new fr(new String[]{"STNT1B", "STNT1H", "STNT1W", "STNT1D"}, WR, Ft).pC(Le.UT);
   private static final tz aU = new fr(
         new String[]{null, "ST2B", "ST3B", "ST4B", null, "ST2H", "ST3H", "ST4H", null, "ST2W", "ST3W", "ST4W", null, "ST2D", "ST3D", "ST4D"}, gp, te
      )
      .pC(Le.UT);

   static final Hu pC(Hu var0, Hu var1) {
      return (var2, var3) -> {
         Yg var4 = var1.buildOperand(var2, var3);
         ZV var5 = null;
         if (var4.getOperandValue() != 0L) {
            var5 = ZV.pC(var4, Z.Av.vP);
         }

         return new KH(var0.buildOperand(var2, var3), var5, false, true, var3);
      };
   }

   static tz pC(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 96) >>> 5;
      int var3 = (var0[2] & 224) >>> 5;
      int var4 = (var0[3] & 16) >>> 4;
      if (var3 < 4) {
         if (var1 == 3) {
            if ((var2 & 2) == 0) {
               if (var3 == 0 && var4 == 0) {
                  return jY;
               }

               if (var3 == 2) {
                  return ee;
               }
            } else if (var4 == 0) {
               return ho;
            }
         } else {
            if ((var2 & 1) == 0) {
               return VE;
            }

            if (var1 == 0 && var4 == 0) {
               return lt;
            }

            if (var1 == 1) {
               return uw;
            }

            if (var1 == 2) {
               return QP;
            }
         }
      } else {
         if (var2 == 1) {
            return hM;
         }

         if ((var2 & 2) != 0) {
            return x[var1 << 2 | (var0[2] & 96) >>> 5];
         }

         if (var3 < 6) {
            return un;
         }

         if (var3 == 6 && var4 == 0) {
            if ((var0[1] & 31) == 31) {
               return null;
            }

            return JV;
         }

         if (var3 == 7 && var4 == 0) {
            return Iq;
         }
      }

      return null;
   }

   static tz A(byte[] var0) throws ProcessorException {
      int var1 = (var0[1] & 96) >>> 5;
      int var2 = (var0[1] & 16) >>> 4;
      int var3 = (var0[2] & 224) >>> 5;
      switch (var3) {
         case 0:
            if ((var0[1] & 31) == 31) {
               return null;
            }

            return UC.pC(mV, WR.decodeInt(var0), var1, var0, "SVE load and broadcast quadword (scalar plus scalar)");
         case 1:
            if (var2 == 0) {
               return UC.pC(Gh, WR.decodeInt(var0), var1, var0, "SVE load and broadcast quadword (scalar plus immediate)");
            } else {
               if (var1 == 0) {
                  return HG;
               }

               return null;
            }
         case 2:
            if ((var0[1] & 31) == 31) {
               return null;
            }

            return HO[gp.decodeInt(var0)];
         case 3:
            return TP[gp.decodeInt(var0)];
         case 4:
            if (var1 == 0) {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return gd;
            } else {
               if (var1 == 1) {
                  if ((var0[1] & 31) == 31) {
                     return null;
                  }

                  return eI;
               }

               return null;
            }
         case 5:
            if (var2 == 0) {
               return xg[gp.decodeInt(var0)];
            }

            return aK[gp.decodeInt(var0)];
         case 6:
            if (var1 == 0) {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return gR;
            } else {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return Ig;
            }
         case 7:
            if (var1 == 0) {
               if (var2 == 0) {
                  return TV;
               }

               return pY;
            } else {
               if (var2 == 0) {
                  return l;
               }

               return null;
            }
         default:
            return null;
      }
   }

   static tz kS(byte[] var0) throws ProcessorException {
      int var1 = (var0[0] & 1) << 1 | (var0[1] & 128) >>> 7;
      int var2 = (var0[1] & 96) >>> 5;
      int var3 = (var0[2] & 224) >>> 5;
      int var4 = (var0[3] & 16) >>> 4;
      if (var3 < 4) {
         if ((var2 & 1) == 0) {
            return Tr;
         } else {
            return var1 == 0 ? Op : yB;
         }
      } else {
         if (var2 == 0) {
            if (var3 == 4 || var3 == 6) {
               return Mi;
            }

            if (var3 == 5 && var2 == 0) {
               return TD;
            }

            if (var3 == 7 && var4 == 0) {
               return mz;
            }
         } else {
            if (var2 == 1) {
               return UJ;
            }

            if (var2 == 2) {
               return KW;
            }

            if (var1 != 0) {
               return NB;
            }

            if (var4 == 0) {
               return ND;
            }
         }

         return null;
      }
   }

   static tz wS(byte[] var0) throws ProcessorException {
      int var1 = (var0[2] & 252) >>> 2;
      switch (var1 >>> 3) {
         case 0:
            int var7 = (var0[0] & 1) << 2 | (var0[1] & 192) >>> 6;
            int var3 = (var0[3] & 16) >>> 4;
            if (var7 == 6 && var3 == 0) {
               return iX;
            } else {
               if (var7 < 4) {
                  int var4 = (var0[1] & 48) >>> 4;
                  if (var4 == 0) {
                     return XZ;
                  }

                  if (var4 >= 2) {
                     if ((var0[1] & 31) == 31) {
                        return null;
                     }

                     return jj;
                  }
               }

               return null;
            }
         case 1:
            int var6 = (var0[0] & 1) << 2 | (var0[1] & 192) >>> 6;
            if ((var0[1] & 32) == 0) {
               if ((var6 & 1) == 0) {
                  return jH;
               }

               return uJ;
            } else {
               if (var6 == 0) {
                  return Fx;
               }

               return null;
            }
         case 2:
            int var5 = (var0[0] & 1) << 2 | (var0[1] & 192) >>> 6;
            if (var5 == 6) {
               return WX;
            } else {
               if ((var0[1] & 31) == 31) {
                  return null;
               }

               return rI[gp.decodeInt(var0)];
            }
         case 3:
            if ((var0[1] & 31) == 31) {
               return null;
            } else {
               if ((var0[1] & 96) == 0) {
                  return hB;
               }

               return gW;
            }
         case 4:
         case 6:
            return pP[(var0[1] & 96) >>> 5];
         case 5:
            return sd[(var0[1] & 96) >>> 5];
         case 7:
            int var2 = (var0[1] & 16) >>> 4;
            if (var2 == 0) {
               return ib[gp.decodeInt(var0)];
            } else {
               if ((var0[1] & 96) == 0) {
                  return zJ;
               }

               return aU;
            }
         default:
            return null;
      }
   }
}
