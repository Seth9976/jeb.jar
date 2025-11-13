package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;
import com.pnfsoftware.jeb.util.math.MathUtil;

class Gh implements Bf {
   private static final IEncodedMemoryArea Sc = DirectEncodedMemoryArea.get(22, 2);
   private static final IEncodedMemoryArea ah = DirectEncodedMemoryArea.get(30, 1);
   public static final IEncodedMemoryArea vP = DirectEncodedMemoryArea.get(22, 1);
   private static final ji eP = new ji.Ws(ah, "", "2");
   private static final ji UO = new ji.Ws(ah, "B", "T");
   private static final Hu Ab = ER.pC(0, XW.UT);
   private static final Hu rl = ER.pC(5, XW.UT);
   private static final Hu z = ER.pC(16, XW.UT);
   private static final Hu Ek = ER.pC(5, XW.kS);
   private static final Hu hK = ER.pC(16, XW.kS);
   private static final Hu Er = ER.pC(0, XW.wS);
   private static final Hu FE = ER.pC(5, XW.wS);
   private static final Hu Aj = ER.pC(0, XW.A);
   private static final Hu EX = ER.pC(5, XW.A);
   private static final Hu LM = ER.pC(0, XW.E);
   private static final IX mv = new vS(new ZW(ER.pC, 1, null, 2, 3));
   private static final IX sO = new vS(1, new ZW(ER.pC, null, 2, 3, null));
   private static final IX os = new vS(1, new ZW(ER.pC, 1, null, null, 4));
   private static final IX Cu = new vS(1, new ZW(ER.pC, 1, 2, 3, null));
   private static final IX hZ = new uM(new ZW(ER.pC, 0, 1, 2, null));
   private static final IX UW = new LA(new ZW(ER.pC, 1, 2, 3, null));
   private static final IX PR = new LA(new ZW(ER.pC, 0, null, null, 3));
   private static final Hu cX = ER.pC(0, vS.Ab);
   private static final Hu DQ = ER.pC(5, vS.Ab);
   private static final Hu ZN = ER.pC(16, vS.Ab);
   private static final Hu OB = ER.pC(0, vS.rl);
   private static final Hu pF = ER.pC(5, vS.rl);
   private static final Hu Bc = ER.pC(0, vS.z);
   private static final Hu OI = ER.pC(5, vS.z);
   private static final Hu Bf = ER.pC(0, vS.Ek);
   private static final Hu Pe = ER.pC(5, vS.Ek);
   private static final Hu ck = ER.pC(0, vS.hK);
   private static final Hu RW = ER.pC(5, vS.hK);
   private static final Hu e = ER.pC(0, vS.Er);
   private static final Hu xM = ER.pC(5, vS.Er);
   private static final Hu kU = ER.pC(16, vS.Er);
   private static final Hu Kq = ER.pC(0, sO);
   private static final Hu go = ER.pC(0, mv);
   private static final Hu JF = ER.pC(5, mv);
   private static final Hu Nq = ER.pC(0, UW);
   private static final Hu pg = ER.pC(0, Cu);
   private static final Hu gj = ER.pC(5, Cu);
   private static final Hu ZD = ER.pC(5, hZ);
   private static final IX DL = vS.Aj;
   private static final IX UH = vS.EX;
   private static final IX VD = new vS(new ZW(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(19, 1), vP), 2, 3, null, 1));
   private static final IX Xs = new vS(new ZW(vP, 2, null));
   private static final IX KV = new vS(new ZW(vP, null, 2));
   private static final IX FK = new vS(1, new ZW(vP, null, 3));
   private static final IX Bi = new vS(1, new ZW(vP, 2, 3));
   private static final IEncodedMemoryArea wQ = EncodedMemoryAreaList.fromBits(21, 22);
   private static final IX PZ = new vS(new ZW(wQ, null, 1, 2, 3));
   private static final Hu Ip = ER.pC(0, UH);
   private static final Hu Fm = ER.pC(5, UH);
   private static final Hu FM = ER.pC(0, VD);
   private static final Hu Wn = ER.pC(5, VD);
   private static final Hu gy = bI.pC(new ZW(ah, 64, 128));
   private static final IX pt = new pg();
   private static final IX uE = new Uy(1, null);
   private static final Hu Um = ER.pC(0, pt);
   private static final Hu Ta = ER.pC(0, uE);
   private static final Hu So = ER.pC(5, pt);
   private static final Hu tH = ER.pC(5, uE);
   private static final Hu Gm = new PP(16, IX.E, GS.A);
   private static final Hu Br = new PP(16, IX.ys, GS.A);
   private static final IEncodedMemoryArea IE = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(16, 3), DirectEncodedMemoryArea.get(5, 5));
   private static final Hu AU = new IV(IE);
   private static final Hu jS = new Gh.Av(64, IE);
   private static final Hu KK = new Gh.Av(32, IE);
   private static final Hu oB = new Gh.Av(16, IE);
   private static final Hu Rq = new Dj(AU, Dj.pC, DirectEncodedMemoryArea.get(13, 1).shift(3));
   private static final Hu LL = new Dj(AU, Dj.pC, DirectEncodedMemoryArea.get(13, 2).shift(3));
   private static final Hu rC = new Dj(AU, VirtualEncodedMemoryArea.get(5, 32), EncodedMemoryAreaList.shift(TN.pC(DirectEncodedMemoryArea.get(12, 1)), 3));
   static final Hu xC = new Ry();
   static final Hu ED = new Zs(var0 -> {
      int var1 = kS(var0) >>> 1;
      if (var1 == 0) {
         return null;
      } else {
         int var2 = DirectEncodedMemoryArea.get(16, 7).decodeInt(var0);
         return var2 - var1;
      }
   });
   private static final IV be = new IV(67174400, DirectEncodedMemoryArea.get(13, 2));
   private static final ZW Xh = new ZW(ah, 1L, 0L);
   private static ZW sz = new ZW(vP, 0L, 1L);
   private static ZW QQ = new ZW(DirectEncodedMemoryArea.get(20, 3), 1L, 16L);
   private static final ZW eE = new ZW(Sc, 16L, 1L, 0L, 0L);
   private static final ZW dM = new ZW(DirectEncodedMemoryArea.get(19, 1), 0L, 16L);
   private static final ZW EM = new ZW(ER.pC, 1L, 1L, 1280L, 1L);
   private static final ZW fD = new ZW(Sc, 1L, 1L, 32L, 1L);
   private static final ZW ii = new ZW(Sc, 1L, 80L, 64L, 64L);
   private static final ZW Gu = new ZW(new EncodedMemoryAreaList(ah, Sc), 1L, 80L, 1L, 1L, 1L, 80L, 64L, 1L);
   private static final ZW hw = new ZW(Sc, 1L, 256L, 1L, 1L);
   private static final ZW qG = new ZW(Sc, 1L, 1L, 1024L, 1L);
   private static final ZW yi = new ZW(wQ, 1L, 16L, 0L, 0L);
   private static final ZW zK = new ZW(ER.pC, 0L, 0L, 0L, 1L);
   private static final tz[] LA = new tz[]{
      new UC(0, "MOVI", Aj, AU),
      new UC(1, "ORR", Aj, AU),
      new UC(2, "MOVI", Aj, LL),
      new UC(3, "ORR", Aj, LL),
      new UC(4, "MOVI", Aj, LL),
      new UC(5, "ORR", Aj, LL),
      new UC(6, "MOVI", Aj, LL),
      new UC(7, "ORR", Aj, LL),
      new UC(8, "MOVI", Er, AU),
      new UC(9, "ORR", Er, AU),
      new UC(10, "MOVI", Er, Rq),
      new UC(11, "ORR", Er, Rq),
      new UC(12, "MOVI", Aj, rC),
      new UC(13, "MOVI", Aj, rC),
      new UC(14, "MOVI", Ab, AU),
      new UC(15, "FMOV", Aj, KK),
      new UC(0, "MVNI", Aj, AU),
      new UC(1, "BIC", Aj, AU),
      new UC(2, "MVNI", Aj, LL),
      new UC(3, "BIC", Aj, LL),
      new UC(4, "MVNI", Aj, LL),
      new UC(5, "BIC", Aj, LL),
      new UC(6, "MVNI", Aj, LL),
      new UC(7, "BIC", Aj, LL),
      new UC(8, "MVNI", Er, AU),
      new UC(9, "BIC", Er, AU),
      new UC(10, "MVNI", Er, Rq),
      new UC(11, "BIC", Er, Rq),
      new UC(12, "MVNI", Aj, rC),
      new UC(13, "MVNI", Aj, rC),
      new UC(14, "MOVI", LM, jS),
      new UC(15, "FMOV", LM, jS)
   };
   private static final tz ve = new UC("MOVI", ER.gp, jS);
   private static final tz yv = new UC("FMOV", Er, oB).pC(ze.pC);
   private static final tz[] MZ = new tz[]{new UC("AESE", Ab, rl), new UC("AESD", Ab, rl), new UC("AESMC", Ab, rl), new UC("AESIMC", Ab, rl)};
   private static final tz[] fH = new tz[]{
      new UC(0, "SSHR", Um, So, xC),
      null,
      new UC(2, "SSRA", Um, So, xC),
      null,
      new UC(4, "SRSHR", Um, So, xC),
      null,
      new UC(6, "SRSRA", Um, So, xC),
      null,
      null,
      null,
      new UC(10, "SHL", Um, So, ED),
      null,
      null,
      null,
      new UC(14, "SQSHL", Um, So, ED),
      null,
      new UC(16, "SHRN", Um, tH, xC).pC(eP).pC(sz),
      new UC(17, "RSHRN", Um, tH, xC).pC(eP).pC(sz),
      new UC(18, "SQSHRN", Um, tH, xC).pC(eP).pC(sz),
      new UC(19, "SQRSHRN", Um, tH, xC).pC(eP).pC(sz),
      new UC(20, "SSHLL", Ta, So, ED).pC(eP).pC(sz),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(28, "SCVTF", Um, So, xC).pC(QQ),
      null,
      null,
      new UC(31, "FCVTZS", Um, So, xC).pC(QQ),
      new UC(32, "USHR", Um, So, xC),
      null,
      new UC(34, "USRA", Um, So, xC),
      null,
      new UC(36, "URSHR", Um, So, xC),
      null,
      new UC(38, "URSRA", Um, So, xC),
      null,
      new UC(40, "SRI", Um, So, xC),
      null,
      new UC(42, "SLI", Um, So, ED),
      null,
      new UC(44, "SQSHLU", Um, So, ED),
      null,
      new UC(46, "UQSHL", Um, So, ED),
      null,
      new UC(48, "SQSHRUN", Um, tH, xC).pC(eP).pC(sz),
      new UC(49, "SQRSHRUN", Um, tH, xC).pC(eP).pC(sz),
      new UC(50, "UQSHRN", Um, tH, xC).pC(eP).pC(sz),
      new UC(51, "UQRSHRN", Um, tH, xC).pC(eP).pC(sz),
      new UC(52, "USHLL", Ta, So, ED).pC(eP).pC(sz),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(60, "UCVTF", Um, So, xC).pC(QQ),
      null,
      null,
      new UC(63, "FCVTZU", Um, So, xC).pC(QQ)
   };
   private static final tz[] ET = new tz[]{new UC("SXTL", Ta, So).pC(eP).pC(sz), new UC("UXTL", Ta, So).pC(eP).pC(sz)};
   private static final tz kk = new UC("FCMLA", ck, RW, GS.sY, be).pC(Gu);
   private static final tz[] Rh = new tz[]{
      new UC(0, "FMLAL", Aj, Ek, GS.wS).pC(fD),
      new UC(1, "FMLA", go, JF, GS.ys).pC(eE),
      new UC(2, "SMLAL", Kq, RW, GS.UT).pC(eP),
      new UC(3, "SQDMLAL", Kq, RW, GS.UT).pC(eP),
      new UC(4, "FMLSL", Aj, Ek, GS.wS).pC(fD),
      new UC(5, "FMLS", go, JF, GS.ys).pC(eE),
      new UC(6, "SMLSL", Kq, RW, GS.UT).pC(eP),
      new UC(7, "SQDMLSL", Kq, RW, GS.UT).pC(eP),
      new UC(8, "MUL", ck, RW, GS.UT),
      new UC(9, "FMUL", go, JF, GS.ys).pC(eE),
      new UC(10, "SMULL", Kq, RW, GS.UT).pC(eP),
      new UC(11, "SQDMULL", Kq, RW, GS.UT).pC(eP),
      new UC(12, "SQDMULH", ck, RW, GS.UT),
      new UC(13, "SQRDMULH", ck, RW, GS.UT),
      new UC(14, "SDOT", Aj, rl, Gm).pC(EM),
      null,
      new UC(16, "MLA", ck, RW, GS.UT),
      kk,
      new UC(18, "UMLAL", Kq, RW, GS.UT).pC(eP),
      kk,
      new UC(20, "MLS", ck, RW, GS.UT),
      kk,
      new UC(22, "UMLSL", Kq, RW, GS.UT).pC(eP),
      kk,
      new UC(24, "FMLAL2", Aj, Ek, GS.wS).pC(fD),
      new UC(25, "FMULX", go, JF, GS.ys).pC(eE),
      new UC(26, "UMULL", Kq, RW, GS.UT).pC(eP),
      null,
      new UC(0, "FMLSL2", Aj, Ek, GS.wS).pC(fD),
      new UC(29, "SQRDMLAH", ck, RW, GS.UT).pC(Le.fI),
      new UC(30, "UDOT", Aj, rl, Gm).pC(EM),
      new UC(31, "SQRDMLSH", ck, RW, GS.UT).pC(Le.fI)
   };
   private static final tz[] vv = new tz[]{
      new UC("SUDOT", Aj, rl, Gm).pC(Le.mv),
      new UC("BFDOT", Aj, FE, Br).pC(Le.LM),
      new UC("USDOT", Aj, rl, Gm).pC(Le.mv),
      new UC("BFMLAL", sY, UT, GS.E).pC(UO).pC(Le.LM)
   };
   private static final tz[] fn = new tz[]{
      new UC(0, "SADDLV", ER.Ab, ZD),
      new UC(1, "SMAXV", ER.eP, ZD),
      new UC(2, "SMINV", ER.eP, ZD),
      new UC(3, "ADDV", ER.eP, ZD),
      new UC(0, "UADDLV", ER.Ab, ZD),
      new UC(1, "UMAXV", ER.eP, ZD),
      new UC(2, "UMINV", ER.eP, ZD),
      null
   };
   private static final tz[] AS = new tz[]{
      new UC("FMAXNMV", ER.UT, FE).pC(sz, ze.pC),
      new UC("FMAXV", ER.UT, FE).pC(sz, ze.pC),
      new UC("FMINNMV", ER.UT, FE).pC(sz, ze.pC),
      new UC("FMINV", ER.UT, FE).pC(sz, ze.pC),
      new UC("FMAXNMV", ER.sY, EX).pC(sz, Xh),
      new UC("FMAXV", ER.sY, EX).pC(sz, Xh),
      new UC("FMINNMV", ER.sY, EX).pC(sz, Xh),
      new UC("FMINV", ER.sY, EX).pC(sz, Xh)
   };
   private static final tz[] wF = new tz[]{
      new UC(0, "REV64", Bc, OI),
      new UC(1, "REV16", cX, DQ),
      new UC(2, "SADDLP", Nq, OI),
      new UC(3, "SUQADD", Bf, Pe),
      new UC(4, "CLS", Bc, OI),
      new UC(5, "CNT", cX, DQ),
      new UC(6, "SADALP", Nq, OI),
      new UC(7, "SQABS", Bf, Pe),
      new UC(8, "CMGT", Bf, Pe, IV.DQ),
      new UC(9, "CMEQ", Bf, Pe, IV.DQ),
      new UC(10, "CMLT", Bf, Pe, IV.DQ),
      new UC(11, "ABS", Bf, Pe),
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(18, "XTN", Bc, gj).pC(eP),
      null,
      new UC(20, "SQXTN", Bc, gj).pC(eP),
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
      new UC(32, "REV32", OB, pF),
      null,
      new UC(34, "UADDLP", Nq, OI),
      new UC(35, "USQADD", Bf, Pe),
      new UC(36, "CLZ", Bc, OI),
      null,
      new UC(38, "UADALP", Nq, OI),
      new UC(39, "SQNEG", Bf, Pe),
      new UC(40, "CMGE", Bf, Pe, IV.DQ),
      new UC(41, "CMLE", Bf, Pe, IV.DQ),
      null,
      new UC(43, "NEG", Bf, Pe),
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(50, "SQXTUN", eP, Bc, gj),
      new UC(50, "SHLL", eP, pg, OI, new Zs(var0 -> 8 << ER.pC.decodeInt(var0))).pC(zK),
      new UC(52, "UQXTN", eP, Bc, gj),
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
   private static final tz[] hF = new tz[]{
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
      new UC(16, "FRINTP", Ip, Fm),
      new UC(17, "FRINTZ", Ip, Fm),
      null,
      null,
      null,
      null,
      new UC(22, "FCVTN", ER.pC(0, DL), ER.pC(5, Bi)).pC(eP),
      new UC(23, "FCVTL", ER.pC(0, Bi), ER.pC(5, DL)).pC(eP),
      new UC(24, "FRINTN", FM, Wn).pC(dM),
      new UC(25, "FRINTM", FM, Wn).pC(dM),
      new UC(26, "FCVTNS", FM, Wn).pC(dM),
      new UC(27, "FCVTMS", FM, Wn).pC(dM),
      new UC(28, "FCVTAS", FM, Wn).pC(dM),
      new UC(29, "SCVTF", FM, Wn).pC(dM),
      new UC(30, "FRINT32Z", Ip, Fm).pC(Le.EX),
      new UC(31, "FRINT64Z", Ip, Fm).pC(Le.EX),
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
      new UC(44, "FCMGT", FM, Wn, gy).pC(dM),
      new UC(45, "FCMEQ", FM, Wn, gy).pC(dM),
      new UC(46, "FCMLT", FM, Wn, gy).pC(dM),
      new UC(47, "FABS", FM, Wn).pC(dM),
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(47, "BFCVTN", Er, ys).pC(eP).pC(Le.LM),
      null,
      new UC(56, "FRINTP", FM, Wn).pC(dM),
      new UC(57, "FRINTZ", FM, Wn).pC(dM),
      new UC(58, "FCVTPS", FM, Wn).pC(dM),
      new UC(59, "FCVTZS", FM, Wn).pC(dM),
      new UC(60, "URECPE", ER.pC(0, Xs), ER.pC(5, Xs)),
      new UC(61, "FRECPE", FM, Wn).pC(dM),
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
      new UC(86, "FCVTXN", ER.pC(0, KV), ER.pC(5, FK)).pC(eP),
      null,
      new UC(88, "FRINTA", FM, Wn).pC(dM),
      new UC(89, "FRINTX", FM, Wn).pC(dM),
      new UC(90, "FCVTNU", FM, Wn).pC(dM),
      new UC(91, "FCVTMU", FM, Wn).pC(dM),
      new UC(92, "FCVTAU", FM, Wn).pC(dM),
      new UC(93, "UCVTF", FM, Wn).pC(dM),
      new UC(30, "FRINT32X", Ip, Fm).pC(Le.EX),
      new UC(31, "FRINT64X", Ip, Fm).pC(Le.EX),
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
      new UC(108, "FCMGE", FM, Wn, gy).pC(dM),
      new UC(109, "FCMLE", FM, Wn, gy).pC(dM),
      null,
      new UC(111, "FNEG", FM, Wn).pC(dM),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(121, "FRINTI", FM, Wn).pC(dM),
      new UC(122, "FCVTPU", FM, Wn).pC(dM),
      new UC(123, "FCVTZU", FM, Wn).pC(dM),
      new UC(124, "URSQRTE", ER.pC(0, Xs), ER.pC(5, Xs)),
      new UC(125, "FRSQRTE", FM, Wn).pC(dM),
      null,
      new UC(127, "FSQRT", FM, Wn).pC(dM)
   };
   private static final tz[] FA = new tz[]{new UC("MVN", Ab, rl), new UC("RBIT", Ab, rl)};
   private static final Hu[] IK = new Hu[]{Bc, OI, ER.pC(16, vS.z)};
   private static final Hu[] DM = new Hu[]{Bf, Pe, ER.pC(16, vS.Ek)};
   private static final Hu[] IQ = new Hu[]{ck, RW, ER.pC(16, vS.hK)};
   private static final tz[] zR = new tz[]{
      new UC(0, "SHADD", IK),
      new UC(1, "SQADD", DM),
      new UC(2, "SRHADD", IK),
      null,
      new UC(4, "SHSUB", IK),
      new UC(5, "SQSUB", DM),
      new UC(6, "CMGT", DM),
      new UC(7, "CMGE", DM),
      new UC(8, "SSHL", DM),
      new UC(9, "SQSHL", DM),
      new UC(10, "SRSHL", DM),
      new UC(11, "SQRSHL", DM),
      new UC(12, "SMAX", IK),
      new UC(13, "SMIN", IK),
      new UC(14, "SABD", IK),
      new UC(15, "SABA", IK),
      new UC(0, "ADD", DM),
      new UC(1, "CMTST", DM),
      new UC(2, "MLA", IK),
      new UC(4, "MUL", IK),
      new UC(4, "SMAXP", IK),
      new UC(5, "SMINP", IK),
      new UC(6, "SQDMULH", IQ),
      new UC(7, "ADDP", DM),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new UC(0, "UHADD", IK),
      new UC(1, "UQADD", DM),
      new UC(2, "URHADD", IK),
      null,
      new UC(4, "UHSUB", IK),
      new UC(5, "UQSUB", DM),
      new UC(6, "CMHI", DM),
      new UC(7, "CMHS", DM),
      new UC(8, "USHL", DM),
      new UC(9, "UQSHL", DM),
      new UC(10, "URSHL", DM),
      new UC(11, "UQRSHL", DM),
      new UC(12, "UMAX", IK),
      new UC(13, "UMIN", IK),
      new UC(14, "UABD", IK),
      new UC(15, "UABA", IK),
      new UC(0, "SUB", DM),
      new UC(1, "CMEQ", DM),
      new UC(2, "MLS", IK),
      new UC(4, "PMUL", Ab, rl, z),
      new UC(4, "UMAXP", IK),
      new UC(5, "UMINP", IK),
      new UC(6, "SQRDMULH", IQ),
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
   private static final IV Ft = new IV(67174400, DirectEncodedMemoryArea.get(11, 2));
   private static final IV kt = new IV(67174400, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 1), VirtualEncodedMemoryArea._1));
   private static final tz Yw = new UC("FCMLA", e, xM, kU, Ft).pC(ii);
   private static final tz uD = new UC("FCADD", e, xM, kU, kt).pC(ii);
   private static final tz[] ZY = new tz[]{
      null,
      null,
      new UC(2, "SDOT", Aj, rl, z).pC(EM),
      new UC(2, "USDOT", Aj, rl, z).pC(qG),
      new UC(4, "SMMLA", Aj, rl, z).pC(qG, Xh),
      new UC(4, "USMMLA", Aj, rl, z).pC(qG, Xh),
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
      new UC(16, "SQRDMLAH", IQ).pC(Le.fI),
      new UC(17, "SQRDMLSH", IQ).pC(Le.fI),
      new UC(18, "UDOT", Aj, rl, z).pC(EM),
      null,
      new UC(20, "UMMLA", Aj, rl, z).pC(qG, Xh),
      null,
      null,
      null,
      Yw,
      Yw,
      Yw,
      Yw,
      uD,
      new UC("BFMMLA", Aj, xM, kU).pC(hw, Xh),
      uD,
      null
   };
   private static final tz[] mK = new tz[]{null, new UC("BFDOT", Aj, xM, kU).pC(Le.LM), null, new UC("BFMLAL", sY, UT, E).pC(UO).pC(Le.LM)};
   private static final Hu[] pW = new Hu[]{ER.pC(0, PZ), ER.pC(5, PZ), ER.pC(16, PZ)};
   private static final tz[] Gg = new tz[]{
      new UC(0, "FMAXNM", pW).pC(yi),
      new UC(1, "FMLA", pW).pC(yi),
      new UC(2, "FADD", pW).pC(yi),
      new UC(3, "FMULX", pW).pC(yi),
      new UC(4, "FCMEQ", pW).pC(yi),
      null,
      new UC(6, "FMAX", pW).pC(yi),
      new UC(7, "FRECPS", pW).pC(yi),
      new UC(8, "FMINNM", pW).pC(yi),
      new UC(9, "FMLS", pW).pC(yi),
      new UC(10, "FSUB", pW).pC(yi),
      null,
      null,
      null,
      new UC(14, "FMIN", pW).pC(yi),
      new UC(15, "FRSQRTS", pW).pC(yi),
      new UC(0, "FMAXNMP", pW).pC(yi),
      null,
      new UC(2, "FADDP", pW).pC(yi),
      new UC(3, "FMUL", pW).pC(yi),
      new UC(4, "FCMGE", pW).pC(yi),
      new UC(5, "FACGE", pW).pC(yi),
      new UC(6, "FMAXP", pW).pC(yi),
      new UC(7, "FDIV", pW).pC(yi),
      new UC(8, "FMINNMP", pW).pC(yi),
      null,
      new UC(10, "FABD", pW).pC(yi),
      null,
      new UC(12, "FCMGT", pW).pC(yi),
      new UC(13, "FACGT", pW).pC(yi),
      new UC(14, "FMINP", pW).pC(yi),
      null
   };
   private static final Hu[] kQ = new Hu[]{Ab, rl, z};
   private static final tz[] te = new tz[]{
      new UC(0, "AND", kQ),
      new UC(1, "BIC", kQ),
      new UC(2, "ORR", kQ),
      new UC(3, "ORN", kQ),
      new UC(4, "EOR", kQ),
      new UC(5, "BSL", kQ),
      new UC(6, "BIT", kQ),
      new UC(7, "BIF", kQ)
   };
   private static final tz B = new UC("MOV", Ab, rl);
   private static final tz[] RR = new tz[]{
      new UC(0, "FMLAL", Aj, Ek, hK).pC(ze.A),
      null,
      new UC(2, "FMLSL", Aj, Ek, hK).pC(ze.A),
      null,
      new UC(0, "FMLAL2", Aj, Ek, hK).pC(ze.A),
      null,
      new UC(2, "FMLSL2", Aj, Ek, hK).pC(ze.A),
      null
   };
   private static final tz[] CK = new tz[]{
      null, new UC(1, "UZP1", DM), new UC(2, "TRN1", DM), new UC(3, "ZIP1", DM), null, new UC(5, "UZP2", DM), new UC(6, "TRN2", DM), new UC(7, "ZIP2", DM)
   };
   private static final tz Eq = new UC("MOV", com.pnfsoftware.jebglobal.MJ.pC(0, 17), com.pnfsoftware.jebglobal.MJ.pC(5, 11));
   private static final tz y = new UC("MOV", com.pnfsoftware.jebglobal.MJ.pC(0, 17), com.pnfsoftware.jebglobal.MJ.pC);
   private static final tz[] JP = new tz[]{
      new UC("DUP", ER.pC(0, vS.LM), com.pnfsoftware.jebglobal.MJ.pC(5, 17)), new UC("DUP", ER.pC(0, vS.LM), com.pnfsoftware.jebglobal.MJ.pC)
   };
   private static final tz[] jY = new tz[]{
      new UC("SMOV", sQ.mv, com.pnfsoftware.jebglobal.MJ.pC(5, 17)), new UC("UMOV", sQ.mv, com.pnfsoftware.jebglobal.MJ.pC(5, 17))
   };
   private static final tz ee = new UC("MOV", sQ.mv, com.pnfsoftware.jebglobal.MJ.pC(5, 17));
   private static final Hu[] ho = new Hu[]{pg, OI, ER.pC(16, vS.z)};
   private static final Hu[] VE = new Hu[]{pg, gj, ER.pC(16, vS.z)};
   private static final Hu[] lt = new Hu[]{Bc, gj, ER.pC(16, Cu)};
   private static final Hu[] uw = new Hu[]{Kq, RW, ER.pC(16, vS.hK)};
   private static final tz[] QP = new tz[]{
      new UC(0, "SADDL", eP, ho),
      new UC(1, "SADDW", eP, VE),
      new UC(2, "SSUBL", eP, ho),
      new UC(3, "SSUBW", eP, VE),
      new UC(4, "ADDHN", eP, lt),
      new UC(5, "SABAL", eP, ho),
      new UC(6, "SUBHN", eP, lt),
      new UC(7, "SABDL", eP, ho),
      new UC(8, "SMLAL", eP, ho),
      new UC(9, "SQDMLAL", eP, uw),
      new UC(10, "SMLSL", eP, ho),
      new UC(11, "SQDMLSL", eP, uw),
      new UC(12, "SMULL", eP, ho),
      new UC(13, "SQDMULL", eP, uw),
      new UC(14, "PMULL", eP, ER.pC(0, os), ER.pC(5, PR), ER.pC(16, PR)),
      null,
      new UC(16, "UADDL", eP, ho),
      new UC(17, "UADDW", eP, VE),
      new UC(18, "USUBL", eP, ho),
      new UC(19, "USUBW", eP, VE),
      new UC(20, "RADDHN", eP, lt),
      new UC(21, "UABAL", eP, ho),
      new UC(22, "RSUBHN", eP, lt),
      new UC(23, "UABDL", eP, ho),
      new UC(24, "UMLAL", eP, ho),
      null,
      new UC(26, "UMLSL", eP, ho),
      null,
      new UC(28, "UMULL", eP, ho),
      null,
      null,
      null
   };
   private static final tz hM = new UC("EXT", cX, DQ, ZN, new IV(DirectEncodedMemoryArea.get(11, 4)));
   private static final Hu[] MJ = new Hu[]{Ab, com.pnfsoftware.jebglobal.kk.pC(5, DirectEncodedMemoryArea.get(13, 2), IX.sY), z};
   private static final tz[] OA = new tz[]{new UC("TBL", MJ), new UC("TBX", MJ)};

   private static int A(byte[] var0) {
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

   private static int kS(byte[] var0) {
      return 1 << 4 + A(var0);
   }

   public static tz pC(byte[] var0) throws ProcessorException {
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
               ? UC.pC(vv, var7, var0, "Advanced SIMD vector x indexed element")
               : UC.pC(Rh, var8 << 4 | var29, var0, "Advanced SIMD vector x indexed element");
         } else if (var2 == 2) {
            if (var3 == 0) {
               int var20 = (var0[0] & 32) >>> 5;
               int var28 = (var0[2] & 240) >>> 4;
               int var33 = (var0[2] & 8) >>> 3;
               if (var9 == 0 && var20 != 0 && var28 == 15) {
                  return UC.pC(var0, "Advanced SIMD modified immediate");
               } else if (var33 != 0) {
                  return var28 == 15 ? yv : UC.pC(var0, "Advanced SIMD modified immediate");
               } else {
                  return var9 == 0 && var20 != 0 && var28 == 14 ? ve : LA[var20 << 4 | var28];
               }
            } else {
               int var19 = (var0[2] & 248) >>> 3;
               int var27 = (var0[1] & 120) >>> 3;
               if (var19 == 20) {
                  int var32 = var0[1] & 7;
                  if (Gq.wS(var27) == 1 && var32 == 0) {
                     return ET[var8];
                  }
               }

               return UC.pC(fH, var8 << 5 | var19, var0, "Advanced SIMD shift by immediate");
            }
         } else {
            return UC.pC(var0, "Advanced SIMD vector");
         }
      } else if ((var3 & 4) == 0) {
         if ((var1 & 11) == 0) {
            if ((var6 & 35) == 0) {
               if (var7 == 0) {
                  int var17 = (var0[2] & 16) >>> 4;
                  return OA[var17];
               }

               return UC.pC(var0, "Advanced SIMD table lookup");
            }

            if ((var6 & 35) == 2) {
               int var16 = (var0[2] & 112) >>> 4;
               return UC.pC(CK, var16, var0, "Advanced SIMD permute");
            }
         } else if ((var6 & 33) == 0) {
            if (var7 != 0 || var9 == 0 && (var0[2] & 64) != 0) {
               return UC.pC(var0, "Advanced SIMD extract");
            }

            return hM;
         }

         if (var2 == 0 && var3 < 4 && (var6 & 33) == 1) {
            int var26 = var0[1] & 31;
            int var31 = (var0[2] & 120) >>> 3;
            if ((var26 & 15) != 0) {
               if (var8 == 0) {
                  if ((var31 & 14) == 0) {
                     return JP[var31];
                  }

                  if (var9 == 1 && var31 == 3) {
                     return y;
                  }

                  if ((var31 & 13) == 5 && (var31 != 7 || var9 == 0 || (var26 & 15) == 8)) {
                     if (var31 == 7) {
                        if ((var26 & 15) == 8 || (var26 & 7) == 4) {
                           return ee;
                        }

                        if (var9 == 0 && (var26 & 15) == 8) {
                           return UC.pC(var0, "Advanced SIMD copy UMOV");
                        }
                     }

                     return jY[var31 >>> 1 & 1];
                  }
               } else if (var9 == 1) {
                  return Eq;
               }
            }
         } else {
            if (var3 >= 8 && (var6 & 49) == 1) {
               int var18 = DirectEncodedMemoryArea.get(11, 3).decodeInt(var0);
               return UC.pC(Gg, var8 << 4 | (var7 & 2) << 2 | var18, var0, "Advanced SIMD three same (FP16)");
            }

            if ((var6 & 33) == 33) {
               int var25 = (var0[2] & 120) >>> 3;
               if (var8 != 0 && var25 == 15) {
                  return UC.pC(mK, var7, var0, "Advanced SIMD three-register extension");
               }

               return UC.pC(ZY, var8 << 4 | var25, var0, "Advanced SIMD three-register extension");
            }
         }

         return UC.pC(var0, "Advanced SIMD copy");
      } else if ((var5 & 1) != 0) {
         int var24 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var0);
         if (var24 == 3) {
            int var30 = var8 << 2 | var7;
            int var13 = DirectEncodedMemoryArea.get(16, 5).decodeInt(var0);
            int var14 = DirectEncodedMemoryArea.get(5, 5).decodeInt(var0);
            return var30 == 2 && var13 == var14 ? B : te[var30];
         } else if ((var24 != 29 || var8 != 0) && (var24 != 25 || var8 == 0)) {
            return MathUtil.betweenInclusive(var24, 24L, 31L)
               ? UC.pC(Gg, var8 << 4 | (var7 & 2) << 2 | var24 & 7, var0, "Advanced SIMD three same")
               : UC.pC(zR, var8 << 5 | var24, var0, "Advanced SIMD three same");
         } else {
            return UC.pC(RR, var8 << 2 | var7, var0, "Advanced SIMD three same");
         }
      } else if ((var5 & 2) == 0) {
         int var15 = (var0[2] & 240) >>> 4;
         return UC.pC(QP, var8 << 4 | var15, var0, "Advanced SIMD three different");
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
            ? UC.pC(var0, "Advanced SIMD two-register miscellaneous (FP16)")
            : UC.pC(hF, var12, var0, "Advanced SIMD two-register miscellaneous (FP16)");
      } else {
         if (var4 == 0) {
            if ((var3 & 3) == 0) {
               int var22 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
               if (var22 == 5 && var7 < 2 && var8 == 1) {
                  return FA[var7];
               }

               if (!MathUtil.betweenInclusive(var22, 0L, 11L) && !MathUtil.betweenInclusive(var22, 18L, 20L)) {
                  return UC.pC(hF, var8 << 6 | (var7 & 2) << 4 | var22, var0, "Advanced SIMD two-register miscellaneous");
               }

               return UC.pC(wF, var8 << 5 | var22, var0, "Advanced SIMD two-register miscellaneous");
            }

            if ((var3 & 3) == 2) {
               int var11 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
               if (var11 != 3 && var11 != 10 && var11 != 26 && var11 != 27) {
                  if (var11 != 12 && var11 != 15) {
                     return UC.pC(var0, "Advanced SIMD across lanes");
                  }

                  return UC.pC(AS, var8 << 2 | var7 & 2 | var11 & 1, var0, "Advanced SIMD across lanes");
               }

               var11 = var11 < 16 ? var11 >>> 3 : var11 & 3;
               return UC.pC(fn, var8 << 2 | var11, var0, "Advanced SIMD across lanes");
            }

            if ((var3 & 3) == 1 && var1 == 4) {
               int var10 = DirectEncodedMemoryArea.get(12, 5).decodeInt(var0);
               if (var7 == 0 && (var10 & 28) == 4) {
                  return MZ[var10 & 3];
               }

               return UC.pC(var0, "Cryptographic AES");
            }
         }

         return UC.pC(var0, "Advanced SIMD/Cryptographic AES");
      }
   }

   private static class Av implements Hu {
      private final IEncodedMemoryArea pC;
      private final int A;

      public Av(int var1, IEncodedMemoryArea var2) {
         this.pC = var2;
         this.A = var1;
      }

      @Override
      public Yg buildOperand(byte[] var1, int var2) {
         int var3 = (var1[0] & 32) >>> 5;
         int var4 = (var1[2] & 240) >>> 4;
         if (var4 == 15) {
            return new eK(this.A, this.pC).buildOperand(var1, var2);
         } else {
            int var5 = Gq.A(var1, this.pC);
            return Yg.pC(64, Gq.pC(var3, var4, (long)var5));
         }
      }
   }
}
