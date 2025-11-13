package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.AbstractEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class xs {
   private static final String[] cX = new String[]{
      "PLDL1KEEP",
      "PLDL1STRM",
      "PLDL2KEEP",
      "PLDL2STRM",
      "PLDL3KEEP",
      "PLDL3STRM",
      "PLDSLCKEEP",
      "PLDSLCSTRM",
      "PLIL1KEEP",
      "PLIL1STRM",
      "PLIL2KEEP",
      "PLIL2STRM",
      "PLIL3KEEP",
      "PLIL3STRM",
      "PLISLCKEEP",
      "PLISLCSTRM",
      "PSTL1KEEP",
      "PSTL1STRM",
      "PSTL2KEEP",
      "PSTL2STRM",
      "PSTL3KEEP",
      "PSTL3STRM",
      "PSTSLCKEEP",
      "PSTSLCSTRM"
   };
   private static final String[] DQ = new String[]{"PLDKEEP", "PSTKEEP", null, null, "PLDSTRM", "PSTSTRM"};
   public static final Ag pC = new Ag(Ll.Av.pC, cX, DirectEncodedMemoryArea.get(0, 5), "#%d");
   public static final Ag A = new Ag(
      Ll.Av.pC, DQ, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(15, 1), DirectEncodedMemoryArea.get(12, 2), DirectEncodedMemoryArea.get(0, 3)), "#%d"
   );
   private static final LF ZN = LF.pC(sQ.NS, DirectEncodedMemoryArea.get(10, 12));
   private static final LF OB = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 1));
   private static final LF pF = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 2));
   private static final LF Bc = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 3));
   private static final LF OI = LF.pC(sQ.NS, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 4));
   public static final tz[][] kS = new tz[][]{
      {
            new UC("STRB", sQ.ED, ZN),
            new UC("LDRB", sQ.ED, ZN),
            new UC("LDRSB", sQ.A, ZN),
            new UC("LDRSB", sQ.ED, ZN),
            new UC("STR", ER.wS, ZN),
            new UC("LDR", ER.wS, ZN),
            new UC("STR", ER.Cu, OI),
            new UC("LDR", ER.Cu, OI)
      },
      {
            new UC("STRH", sQ.ED, OB),
            new UC("LDRH", sQ.ED, OB),
            new UC("LDRSH", sQ.A, OB),
            new UC("LDRSH", sQ.ED, OB),
            new UC("STR", ER.UT, OB),
            new UC("LDR", ER.UT, OB)
      },
      {new UC("STR", sQ.ED, pF), new UC("LDR", sQ.ED, pF), new UC("LDRSW", sQ.A, pF), null, new UC("STR", ER.sY, pF), new UC("LDR", ER.sY, pF)},
      {new UC("STR", sQ.A, Bc), new UC("LDR", sQ.A, Bc), new UC("PRFM", pC, Bc), null, new UC("STR", ER.gp, Bc), new UC("LDR", ER.gp, Bc)}
   };
   private static final LF Bf = LF.pC(
      sQ.NS,
      DirectEncodedMemoryArea.get(24, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      DirectEncodedMemoryArea.get(15, 7).shift(2),
      LF.kS
   );
   private static final LF Pe = LF.pC(
      sQ.NS,
      DirectEncodedMemoryArea.get(24, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      DirectEncodedMemoryArea.get(15, 7).shift(3),
      LF.kS
   );
   private static final LF ck = LF.pC(
      sQ.NS,
      DirectEncodedMemoryArea.get(24, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      DirectEncodedMemoryArea.get(15, 7).shift(4),
      LF.kS
   );
   public static final tz[] wS = new tz[]{
      new UC("STP", sQ.ED, sQ.UO, Bf),
      new UC("LDP", sQ.ED, sQ.UO, Bf),
      new UC("STP", ER.sY, ER.ld, Bf),
      new UC("LDP", ER.sY, ER.ld, Bf),
      new UC("STGP", sQ.A, sQ.ys, ck),
      new UC("LDPSW", sQ.A, sQ.ys, Bf),
      new UC("STP", ER.gp, ER.fI, Pe),
      new UC("LDP", ER.gp, ER.fI, Pe),
      new UC("STP", sQ.A, sQ.ys, Pe),
      new UC("LDP", sQ.A, sQ.ys, Pe),
      new UC("STP", ER.Cu, ER.UW, ck),
      new UC("LDP", ER.Cu, ER.UW, ck)
   };
   private static final LF RW = pC(sQ.NS, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(15, 7), 2));
   private static final LF e = pC(sQ.NS, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(15, 7), 3));
   private static final LF xM = pC(sQ.NS, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(15, 7), 4));
   public static final tz[] UT = new tz[]{
      new UC("STNP", sQ.ED, sQ.UO, RW),
      new UC("LDNP", sQ.ED, sQ.UO, RW),
      new UC("STNP", ER.sY, ER.ld, RW),
      new UC("LDNP", ER.sY, ER.ld, RW),
      null,
      null,
      new UC("STNP", ER.gp, ER.fI, e),
      new UC("LDNP", ER.gp, ER.fI, e),
      new UC("STNP", sQ.A, sQ.ys, e),
      new UC("LDNP", sQ.A, sQ.ys, e),
      new UC("STNP", ER.Cu, ER.UW, xM),
      new UC("LDNP", ER.Cu, ER.UW, xM)
   };
   public static final tz[][] E = new tz[][]{UT, wS, wS, wS};
   private static final Hu kU = LF.pC(sQ.NS);
   private static final Hu[] Kq = new Hu[]{sQ.Ab, sQ.ED, kU};
   private static final Hu[] go = new Hu[]{sQ.Ab, sQ.A, kU};
   private static final Hu[] JF = new Hu[]{sQ.rl, sQ.z, sQ.Sc, sQ.ah, kU};
   private static final Hu[] Nq = new Hu[]{sQ.ld, sQ.A, kU};
   private static final Hu[] pg = new Hu[]{sQ.gp, sQ.oT, sQ.kS, sQ.wS, kU};
   private static final Hu[] gj = new Hu[]{sQ.A, sQ.ld, kU};
   private static final Hu[] ZD = new Hu[]{sQ.Ab, sQ.ED, sQ.UO, kU};
   private static final Hu[] DL = new Hu[]{sQ.Ab, sQ.A, sQ.ys, kU};
   private static final Hu[] UH = new Hu[]{sQ.ED, kU};
   private static final Hu[] VD = new Hu[]{sQ.A, kU};
   private static final Hu[] Xs = new Hu[]{sQ.ED, sQ.UO, kU};
   private static final Hu[] KV = new Hu[]{sQ.A, sQ.ys, kU};
   public static final tz[] sY = new tz[]{
      new UC(0, "STXRB", Kq),
      new UC(1, "STLXRB", Kq),
      new UC(2, "CASP", JF).pC(Le.gp),
      new UC(3, "CASPL", JF).pC(Le.gp),
      new UC(4, "LDXRB", UH),
      new UC(5, "LDAXRB", UH),
      new UC(6, "CASPA", JF).pC(Le.gp),
      new UC(7, "CASPAL", JF).pC(Le.gp),
      new UC(9, "STLLRB", UH).pC(Le.oT),
      new UC(9, "STLRB", UH),
      new UC(10, "CASB", Kq).pC(Le.gp),
      new UC(11, "CASLB", Kq).pC(Le.gp),
      new UC(12, "LDLARB", UH).pC(Le.oT),
      new UC(13, "LDARB", UH),
      new UC(14, "CASAB", Kq).pC(Le.gp),
      new UC(15, "CASALB", Kq).pC(Le.gp),
      new UC(16, "STXRH", Kq),
      new UC(17, "STLXRH", Kq),
      new UC(18, "CASP", pg).pC(Le.gp),
      new UC(19, "CASPL", pg).pC(Le.gp),
      new UC(20, "LDXRH", UH),
      new UC(21, "LDAXRH", UH),
      new UC(22, "CASPA", pg).pC(Le.gp),
      new UC(23, "CASPAL", pg).pC(Le.gp),
      new UC(25, "STLLRH", UH).pC(Le.oT),
      new UC(25, "STLRH", UH),
      new UC(26, "CASH", Kq).pC(Le.gp),
      new UC(27, "CASLH", Kq).pC(Le.gp),
      new UC(29, "LDLARH", UH).pC(Le.oT),
      new UC(29, "LDARH", UH),
      new UC(30, "CASAH", Kq).pC(Le.gp),
      new UC(31, "CASALH", Kq).pC(Le.gp),
      new UC(32, "STXR", Kq),
      new UC(33, "STLXR", Kq),
      new UC(34, "STXP", ZD),
      new UC(35, "STLXP", ZD),
      new UC(36, "LDXR", UH),
      new UC(37, "LDAXR", UH),
      new UC(38, "LDXP", Xs),
      new UC(39, "LDAXP", Xs),
      new UC(41, "STLLR", UH).pC(Le.oT),
      new UC(41, "STLR", UH),
      new UC(42, "CAS", Kq).pC(Le.gp),
      new UC(43, "CASL", Kq).pC(Le.gp),
      new UC(45, "LDLAR", UH).pC(Le.oT),
      new UC(45, "LDAR", UH),
      new UC(46, "CASA", Kq).pC(Le.gp),
      new UC(47, "CASAL", Kq).pC(Le.gp),
      new UC(48, "STXR", go),
      new UC(49, "STLXR", go),
      new UC(50, "STXP", DL),
      new UC(51, "STLXP", DL),
      new UC(52, "LDXR", VD),
      new UC(53, "LDAXR", VD),
      new UC(54, "LDXP", KV),
      new UC(55, "LDAXP", KV),
      new UC(57, "STLLR", VD).pC(Le.oT),
      new UC(57, "STLR", VD),
      new UC(58, "CAS", Nq).pC(Le.gp),
      new UC(59, "CASL", Nq).pC(Le.gp),
      new UC(61, "LDLAR", VD).pC(Le.oT),
      new UC(61, "LDAR", VD),
      new UC(62, "CASA", Nq),
      new UC(63, "CASAL", Nq)
   };
   private static final LF FK = pC(sQ.NS, DirectEncodedMemoryArea.get(12, 9));
   public static final tz[] ys = new tz[]{
      new UC("STURB", sQ.ED, FK),
      new UC("LDURB", sQ.ED, FK),
      new UC("LDURSB", sQ.A, FK),
      new UC("LDURSB", sQ.ED, FK),
      new UC("STUR", ER.wS, FK),
      new UC("LDUR", ER.wS, FK),
      new UC("STUR", ER.Cu, FK),
      new UC("LDUR", ER.Cu, FK),
      new UC("STURH", sQ.ED, FK),
      new UC("LDURH", sQ.ED, FK),
      new UC("LDURSH", sQ.A, FK),
      new UC("LDURSH", sQ.ED, FK),
      new UC("STUR", ER.UT, FK),
      new UC("LDUR", ER.UT, FK),
      null,
      null,
      new UC("STUR", sQ.ED, FK),
      new UC("LDUR", sQ.ED, FK),
      new UC("LDURSW", sQ.A, FK),
      null,
      new UC("STUR", ER.sY, FK),
      new UC("LDUR", ER.sY, FK),
      null,
      null,
      new UC("STUR", sQ.A, FK),
      new UC("LDUR", sQ.A, FK),
      new UC("PRFUM", pC, FK),
      null,
      new UC("STUR", ER.gp, FK),
      new UC("LDUR", ER.gp, FK),
      null,
      null
   };
   public static final tz[] ld = new tz[]{
      new UC(0, "STLURB", sQ.ED, FK).pC(Le.FE),
      new UC(1, "LDAPURB", sQ.ED, FK).pC(Le.FE),
      new UC(2, "LDAPURSB", sQ.A, FK).pC(Le.FE),
      new UC(3, "LDAPURSB", sQ.ED, FK).pC(Le.FE),
      new UC(4, "STLURH", sQ.ED, FK).pC(Le.FE),
      new UC(5, "LDAPURH", sQ.ED, FK).pC(Le.FE),
      new UC(6, "LDAPURSH", sQ.A, FK).pC(Le.FE),
      new UC(7, "LDAPURSH", sQ.ED, FK).pC(Le.FE),
      new UC(8, "STLUR", sQ.ED, FK).pC(Le.FE),
      new UC(9, "LDAPUR", sQ.ED, FK).pC(Le.FE),
      new UC(10, "LDAPURSW", sQ.A, FK).pC(Le.FE),
      null,
      new UC(8, "STLUR", sQ.A, FK).pC(Le.FE),
      new UC(9, "LDAPUR", sQ.A, FK).pC(Le.FE)
   };
   public static final tz[] gp = new tz[]{
      new UC("STTRB", sQ.ED, FK),
      new UC("LDTRB", sQ.ED, FK),
      new UC("LDTRSB", sQ.A, FK),
      new UC("LDTRSB", sQ.ED, FK),
      new UC("STTRH", sQ.ED, FK),
      new UC("LDTRH", sQ.ED, FK),
      new UC("LDTRSH", sQ.A, FK),
      new UC("LDTRSH", sQ.ED, FK),
      new UC("STTR", sQ.ED, FK),
      new UC("LDTR", sQ.ED, FK),
      new UC("LDTRSW", sQ.A, FK),
      null,
      new UC("STTR", sQ.A, FK),
      new UC("LDTR", sQ.A, FK),
      null,
      null
   };
   private static final LF Bi = LF.pC(
      sQ.NS, DirectEncodedMemoryArea.get(11, 1), VirtualEncodedMemoryArea._1, DirectEncodedMemoryArea.get(10, 1), DirectEncodedMemoryArea.get(12, 9), LF.kS
   );
   public static final tz[] oT = new tz[]{
      new UC("STRB", sQ.ED, Bi),
      new UC("LDRB", sQ.ED, Bi),
      new UC("LDRSB", sQ.A, Bi),
      new UC("LDRSB", sQ.ED, Bi),
      new UC("STR", ER.wS, Bi),
      new UC("LDR", ER.wS, Bi),
      new UC("STR", ER.Cu, Bi),
      new UC("LDR", ER.Cu, Bi),
      new UC("STRH", sQ.ED, Bi),
      new UC("LDRH", sQ.ED, Bi),
      new UC("LDRSH", sQ.A, Bi),
      new UC("LDRSH", sQ.ED, Bi),
      new UC("STR", ER.UT, Bi),
      new UC("LDR", ER.UT, Bi),
      null,
      null,
      new UC("STR", sQ.ED, Bi),
      new UC("LDR", sQ.ED, Bi),
      new UC("LDRSW", sQ.A, Bi),
      null,
      new UC("STR", ER.sY, Bi),
      new UC("LDR", ER.sY, Bi),
      null,
      null,
      new UC("STR", sQ.A, Bi),
      new UC("LDR", sQ.A, Bi),
      null,
      null,
      new UC("STR", ER.gp, Bi),
      new UC("LDR", ER.gp, Bi),
      null,
      null
   };
   private static final LF wQ = LF.pC(
      sQ.NS, sQ.UW, DirectEncodedMemoryArea.get(13, 3), new xs.Av(DirectEncodedMemoryArea.get(30, 2)), DirectEncodedMemoryArea.get(12, 1), LF.wS
   );
   private static final LF PZ = LF.pC(
      sQ.NS, sQ.UW, DirectEncodedMemoryArea.get(13, 3), new xs.Av(VirtualEncodedMemoryArea.get(4, 4)), DirectEncodedMemoryArea.get(12, 1), LF.wS
   );
   public static final tz[] fI = new tz[]{
      new UC("STRB", sQ.ED, wQ),
      new UC("LDRB", sQ.ED, wQ),
      new UC("LDRSB", sQ.A, wQ),
      new UC("LDRSB", sQ.ED, wQ),
      new UC("STR", ER.wS, wQ),
      new UC("LDR", ER.wS, wQ),
      new UC("STR", ER.Cu, PZ),
      new UC("LDR", ER.Cu, PZ),
      new UC("STRH", sQ.ED, wQ),
      new UC("LDRH", sQ.ED, wQ),
      new UC("LDRSH", sQ.A, wQ),
      new UC("LDRSH", sQ.ED, wQ),
      new UC("STR", ER.UT, wQ),
      new UC("LDR", ER.UT, wQ),
      null,
      null,
      new UC("STR", sQ.ED, wQ),
      new UC("LDR", sQ.ED, wQ),
      new UC("LDRSW", sQ.A, wQ),
      null,
      new UC("STR", ER.sY, wQ),
      new UC("LDR", ER.sY, wQ),
      null,
      null,
      new UC("STR", sQ.A, wQ),
      new UC("LDR", sQ.A, wQ),
      new UC("PRFM", pC, wQ),
      null,
      new UC("STR", ER.gp, wQ),
      new UC("LDR", ER.gp, wQ),
      null,
      null
   };
   public static final tz WR = new UC("RPRFM", A, sQ.ld, sQ.xC).pC(Le.A);
   private static final LF Ip = LF.pC(sQ.Pe, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(5, 19), 2), LF.pC, LF.kS);
   public static final tz[] NS = new tz[]{
      new UC("LDR", sQ.ED, Ip),
      new UC("LDR", ER.sY, Ip),
      new UC("LDR", sQ.A, Ip),
      new UC("LDR", ER.gp, Ip),
      new UC("LDRSW", sQ.A, Ip),
      new UC("LDR", ER.Cu, Ip),
      new UC("PRFM", pC, Ip),
      null
   };
   private static final LF Fm = LF.pC(
      sQ.NS,
      VirtualEncodedMemoryArea._1,
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(11, 1),
      new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(12, 9), VirtualEncodedMemoryArea._000),
      LF.kS
   );
   public static final tz[] vP = new tz[]{new UC("LDRAA", sQ.A, Fm).pC(Le.rl), new UC("LDRAB", sQ.A, Fm).pC(Le.rl)};
   private static final Hu[] FM = new Hu[]{sQ.Ab, sQ.ED, kU};
   private static final tz[] Wn = new tz[]{
      new UC(0, "LDADDB", FM).pC(Le.gp),
      new UC(1, "LDCLRB", FM).pC(Le.gp),
      new UC(2, "LDEORB", FM).pC(Le.gp),
      new UC(3, "LDSETB", FM).pC(Le.gp),
      new UC(4, "LDSMAXB", FM).pC(Le.gp),
      new UC(5, "LDSMINB", FM).pC(Le.gp),
      new UC(6, "LDUMAXB", FM).pC(Le.gp),
      new UC(7, "LDUMINB", FM).pC(Le.gp),
      new UC(8, "LDADDLB", FM).pC(Le.gp),
      new UC(9, "LDCLRLB", FM).pC(Le.gp),
      new UC(10, "LDEORLB", FM).pC(Le.gp),
      new UC(11, "LDSETLB", FM).pC(Le.gp),
      new UC(12, "LDSMAXLB", FM).pC(Le.gp),
      new UC(13, "LDSMINLB", FM).pC(Le.gp),
      new UC(14, "LDUMAXLB", FM).pC(Le.gp),
      new UC(15, "LDUMINLB", FM).pC(Le.gp),
      new UC(16, "LDADDAB", FM).pC(Le.gp),
      new UC(17, "LDCLRAB", FM).pC(Le.gp),
      new UC(18, "LDEORAB", FM).pC(Le.gp),
      new UC(19, "LDSETAB", FM).pC(Le.gp),
      new UC(20, "LDSMAXAB", FM).pC(Le.gp),
      new UC(21, "LDSMINAB", FM).pC(Le.gp),
      new UC(22, "LDUMAXAB", FM).pC(Le.gp),
      new UC(23, "LDUMINAB", FM).pC(Le.gp),
      new UC(24, "LDADDALB", FM).pC(Le.gp),
      new UC(25, "LDCLRALB", FM).pC(Le.gp),
      new UC(26, "LDEORALB", FM).pC(Le.gp),
      new UC(27, "LDSETALB", FM).pC(Le.gp),
      new UC(28, "LDSMAXALB", FM).pC(Le.gp),
      new UC(29, "LDSMINALB", FM).pC(Le.gp),
      new UC(30, "LDUMAXALB", FM).pC(Le.gp),
      new UC(31, "LDUMINALB", FM).pC(Le.gp)
   };
   private static final tz[] gy = new tz[]{
      new UC(0, "LDADDH", FM).pC(Le.gp),
      new UC(1, "LDCLRH", FM).pC(Le.gp),
      new UC(2, "LDEORH", FM).pC(Le.gp),
      new UC(3, "LDSETH", FM).pC(Le.gp),
      new UC(4, "LDSMAXH", FM).pC(Le.gp),
      new UC(5, "LDSMINH", FM).pC(Le.gp),
      new UC(6, "LDUMAXH", FM).pC(Le.gp),
      new UC(7, "LDUMINH", FM).pC(Le.gp),
      new UC(8, "LDADDLH", FM).pC(Le.gp),
      new UC(9, "LDCLRLH", FM).pC(Le.gp),
      new UC(10, "LDEORLH", FM).pC(Le.gp),
      new UC(11, "LDSETLH", FM).pC(Le.gp),
      new UC(12, "LDSMAXLH", FM).pC(Le.gp),
      new UC(13, "LDSMINLH", FM).pC(Le.gp),
      new UC(14, "LDUMAXLH", FM).pC(Le.gp),
      new UC(15, "LDUMINLH", FM).pC(Le.gp),
      new UC(16, "LDADDAH", FM).pC(Le.gp),
      new UC(17, "LDCLRAH", FM).pC(Le.gp),
      new UC(18, "LDEORAH", FM).pC(Le.gp),
      new UC(19, "LDSETAH", FM).pC(Le.gp),
      new UC(20, "LDSMAXAH", FM).pC(Le.gp),
      new UC(21, "LDSMINAH", FM).pC(Le.gp),
      new UC(22, "LDUMAXAH", FM).pC(Le.gp),
      new UC(23, "LDUMINAH", FM).pC(Le.gp),
      new UC(24, "LDADDALH", FM).pC(Le.gp),
      new UC(25, "LDCLRALH", FM).pC(Le.gp),
      new UC(26, "LDEORALH", FM).pC(Le.gp),
      new UC(27, "LDSETALH", FM).pC(Le.gp),
      new UC(28, "LDSMAXALH", FM).pC(Le.gp),
      new UC(29, "LDSMINALH", FM).pC(Le.gp),
      new UC(30, "LDUMAXALH", FM).pC(Le.gp),
      new UC(31, "LDUMINALH", FM).pC(Le.gp)
   };
   private static final tz[] pt = new tz[]{
      new UC(0, "LDADD", FM).pC(Le.gp),
      new UC(1, "LDCLR", FM).pC(Le.gp),
      new UC(2, "LDEOR", FM).pC(Le.gp),
      new UC(3, "LDSET", FM).pC(Le.gp),
      new UC(4, "LDSMAX", FM).pC(Le.gp),
      new UC(5, "LDSMIN", FM).pC(Le.gp),
      new UC(6, "LDUMAX", FM).pC(Le.gp),
      new UC(7, "LDUMIN", FM).pC(Le.gp),
      new UC(8, "LDADDL", FM).pC(Le.gp),
      new UC(9, "LDCLRL", FM).pC(Le.gp),
      new UC(10, "LDEORL", FM).pC(Le.gp),
      new UC(11, "LDSETL", FM).pC(Le.gp),
      new UC(12, "LDSMAXL", FM).pC(Le.gp),
      new UC(13, "LDSMINL", FM).pC(Le.gp),
      new UC(14, "LDUMAXL", FM).pC(Le.gp),
      new UC(15, "LDUMINL", FM).pC(Le.gp),
      new UC(16, "LDADDA", FM).pC(Le.gp),
      new UC(17, "LDCLRA", FM).pC(Le.gp),
      new UC(18, "LDEORA", FM).pC(Le.gp),
      new UC(19, "LDSETA", FM).pC(Le.gp),
      new UC(20, "LDSMAXA", FM).pC(Le.gp),
      new UC(21, "LDSMINA", FM).pC(Le.gp),
      new UC(22, "LDUMAXA", FM).pC(Le.gp),
      new UC(23, "LDUMINA", FM).pC(Le.gp),
      new UC(24, "LDADDAL", FM).pC(Le.gp),
      new UC(25, "LDCLRAL", FM).pC(Le.gp),
      new UC(26, "LDEORAL", FM).pC(Le.gp),
      new UC(27, "LDSETAL", FM).pC(Le.gp),
      new UC(28, "LDSMAXAL", FM).pC(Le.gp),
      new UC(29, "LDSMINAL", FM).pC(Le.gp),
      new UC(30, "LDUMAXAL", FM).pC(Le.gp),
      new UC(31, "LDUMINAL", FM).pC(Le.gp)
   };
   private static final Hu[] uE = new Hu[]{sQ.ld, sQ.A, kU};
   private static final tz[] Um = new tz[]{
      new UC(0, "LDADD", uE).pC(Le.gp),
      new UC(1, "LDCLR", uE).pC(Le.gp),
      new UC(2, "LDEOR", uE).pC(Le.gp),
      new UC(3, "LDSET", uE).pC(Le.gp),
      new UC(4, "LDSMAX", uE).pC(Le.gp),
      new UC(5, "LDSMIN", uE).pC(Le.gp),
      new UC(6, "LDUMAX", uE).pC(Le.gp),
      new UC(7, "LDUMIN", uE).pC(Le.gp),
      new UC(8, "LDADDL", uE).pC(Le.gp),
      new UC(9, "LDCLRL", uE).pC(Le.gp),
      new UC(10, "LDEORL", uE).pC(Le.gp),
      new UC(11, "LDSETL", uE).pC(Le.gp),
      new UC(12, "LDSMAXL", uE).pC(Le.gp),
      new UC(13, "LDSMINL", uE).pC(Le.gp),
      new UC(14, "LDUMAXL", uE).pC(Le.gp),
      new UC(15, "LDUMINL", uE).pC(Le.gp),
      new UC(16, "LDADDA", uE).pC(Le.gp),
      new UC(17, "LDCLRA", uE).pC(Le.gp),
      new UC(18, "LDEORA", uE).pC(Le.gp),
      new UC(19, "LDSETA", uE).pC(Le.gp),
      new UC(20, "LDSMAXA", uE).pC(Le.gp),
      new UC(21, "LDSMINA", uE).pC(Le.gp),
      new UC(22, "LDUMAXA", uE).pC(Le.gp),
      new UC(23, "LDUMINA", uE).pC(Le.gp),
      new UC(24, "LDADDAL", uE).pC(Le.gp),
      new UC(25, "LDCLRAL", uE).pC(Le.gp),
      new UC(26, "LDEORAL", uE).pC(Le.gp),
      new UC(27, "LDSETAL", uE).pC(Le.gp),
      new UC(28, "LDSMAXAL", uE).pC(Le.gp),
      new UC(29, "LDSMINAL", uE).pC(Le.gp),
      new UC(30, "LDUMAXAL", uE).pC(Le.gp),
      new UC(31, "LDUMINAL", uE).pC(Le.gp)
   };
   public static final tz[][] xC = new tz[][]{Wn, gy, pt, Um};
   private static final Hu[] Ta = new Hu[]{sQ.Ab, kU};
   private static final tz[] So = new tz[]{
      new UC(0, "STADDB", Ta).pC(Le.gp),
      new UC(1, "STCLRB", Ta).pC(Le.gp),
      new UC(2, "STEORB", Ta).pC(Le.gp),
      new UC(3, "STSETB", Ta).pC(Le.gp),
      new UC(4, "STSMAXB", Ta).pC(Le.gp),
      new UC(5, "STSMINB", Ta).pC(Le.gp),
      new UC(6, "STUMAXB", Ta).pC(Le.gp),
      new UC(7, "STUMINB", Ta).pC(Le.gp),
      new UC(8, "STADDLB", Ta).pC(Le.gp),
      new UC(9, "STCLRLB", Ta).pC(Le.gp),
      new UC(10, "STEORLB", Ta).pC(Le.gp),
      new UC(11, "STSETLB", Ta).pC(Le.gp),
      new UC(12, "STSMAXLB", Ta).pC(Le.gp),
      new UC(13, "STSMINLB", Ta).pC(Le.gp),
      new UC(14, "STUMAXLB", Ta).pC(Le.gp),
      new UC(15, "STUMINLB", Ta).pC(Le.gp)
   };
   private static final tz[] tH = new tz[]{
      new UC(0, "STADDH", Ta).pC(Le.gp),
      new UC(1, "STCLRH", Ta).pC(Le.gp),
      new UC(2, "STEORH", Ta).pC(Le.gp),
      new UC(3, "STSETH", Ta).pC(Le.gp),
      new UC(4, "STSMAXH", Ta).pC(Le.gp),
      new UC(5, "STSMINH", Ta).pC(Le.gp),
      new UC(6, "STUMAXH", Ta).pC(Le.gp),
      new UC(7, "STUMINH", Ta).pC(Le.gp),
      new UC(8, "STADDLH", Ta).pC(Le.gp),
      new UC(9, "STCLRLH", Ta).pC(Le.gp),
      new UC(10, "STEORLH", Ta).pC(Le.gp),
      new UC(11, "STSETLH", Ta).pC(Le.gp),
      new UC(12, "STSMAXLH", Ta).pC(Le.gp),
      new UC(13, "STSMINLH", Ta).pC(Le.gp),
      new UC(14, "STUMAXLH", Ta).pC(Le.gp),
      new UC(15, "STUMINLH", Ta).pC(Le.gp)
   };
   private static final tz[] Gm = new tz[]{
      new UC(0, "STADD", Ta).pC(Le.gp),
      new UC(1, "STCLR", Ta).pC(Le.gp),
      new UC(2, "STEOR", Ta).pC(Le.gp),
      new UC(3, "STSET", Ta).pC(Le.gp),
      new UC(4, "STSMAX", Ta).pC(Le.gp),
      new UC(5, "STSMIN", Ta).pC(Le.gp),
      new UC(6, "STUMAX", Ta).pC(Le.gp),
      new UC(7, "STUMIN", Ta).pC(Le.gp),
      new UC(8, "STADDL", Ta).pC(Le.gp),
      new UC(9, "STCLRL", Ta).pC(Le.gp),
      new UC(10, "STEORL", Ta).pC(Le.gp),
      new UC(11, "STSETL", Ta).pC(Le.gp),
      new UC(12, "STSMAXL", Ta).pC(Le.gp),
      new UC(13, "STSMINL", Ta).pC(Le.gp),
      new UC(14, "STUMAXL", Ta).pC(Le.gp),
      new UC(15, "STUMINL", Ta).pC(Le.gp)
   };
   private static final Hu[] Br = new Hu[]{sQ.ld, kU};
   private static final tz[] IE = new tz[]{
      new UC(0, "STADD", Br).pC(Le.gp),
      new UC(1, "STCLR", Br).pC(Le.gp),
      new UC(2, "STEOR", Br).pC(Le.gp),
      new UC(3, "STSET", Br).pC(Le.gp),
      new UC(4, "STSMAX", Br).pC(Le.gp),
      new UC(5, "STSMIN", Br).pC(Le.gp),
      new UC(6, "STUMAX", Br).pC(Le.gp),
      new UC(7, "STUMIN", Br).pC(Le.gp),
      new UC(8, "STADDL", Br).pC(Le.gp),
      new UC(9, "STCLRL", Br).pC(Le.gp),
      new UC(10, "STEORL", Br).pC(Le.gp),
      new UC(11, "STSETL", Br).pC(Le.gp),
      new UC(12, "STSMAXL", Br).pC(Le.gp),
      new UC(13, "STSMINL", Br).pC(Le.gp),
      new UC(14, "STUMAXL", Br).pC(Le.gp),
      new UC(15, "STUMINL", Br).pC(Le.gp)
   };
   public static final tz[][] ED = new tz[][]{So, tH, Gm, IE};
   public static final tz[][] Sc = new tz[][]{
      {
            new UC(0, "SWPB", Kq).pC(Le.gp),
            new UC(1, "SWPLB", Kq).pC(Le.gp),
            new UC(2, "SWPAB", Kq).pC(Le.gp),
            new UC(3, "SWPALB", Kq).pC(Le.gp),
            new UC(4, "SWPH", Kq).pC(Le.gp),
            new UC(5, "SWPLH", Kq).pC(Le.gp),
            new UC(6, "SWPAH", Kq).pC(Le.gp),
            new UC(7, "SWPALH", Kq).pC(Le.gp),
            new UC(8, "SWP", Kq).pC(Le.gp),
            new UC(9, "SWPL", Kq).pC(Le.gp),
            new UC(10, "SWPA", Kq).pC(Le.gp),
            new UC(11, "SWPAL", Kq).pC(Le.gp),
            new UC(12, "SWP", Nq).pC(Le.gp),
            new UC(13, "SWPL", Nq).pC(Le.gp),
            new UC(14, "SWPA", Nq).pC(Le.gp),
            new UC(15, "SWPAL", Nq).pC(Le.gp)
      },
      {
            new UC("RCWCLR", Nq).pC(Le.DL),
            new UC("RCWCLRL", Nq).pC(Le.DL),
            new UC("RCWCLRA", Nq).pC(Le.DL),
            new UC("RCWCLRAL", Nq).pC(Le.DL),
            new UC("RCWSCLR", Nq).pC(Le.DL),
            new UC("RCWSCLRL", Nq).pC(Le.DL),
            new UC("RCWSCLRA", Nq).pC(Le.DL),
            new UC("RCWSCLRAL", Nq).pC(Le.DL),
            null,
            null,
            null,
            null,
            new UC("ST64B", sQ.A, kU).pC(Le.sO)
      },
      {
            new UC("RCWSWP", Nq).pC(Le.DL),
            new UC("RCWSWPL", Nq).pC(Le.DL),
            new UC("RCWSWPA", Nq).pC(Le.DL),
            new UC("RCWSWPAL", Nq).pC(Le.DL),
            new UC("RCWSSWP", Nq).pC(Le.DL),
            new UC("RCWSSWPL", Nq).pC(Le.DL),
            new UC("RCWSSWPA", Nq).pC(Le.DL),
            new UC("RCWSSWPAL", Nq).pC(Le.DL),
            null,
            null,
            null,
            null,
            new UC("ST64BV0", sQ.ld, sQ.A, kU).pC(Le.sO)
      },
      {
            new UC("RCWSET", Nq).pC(Le.DL),
            new UC("RCWSETL", Nq).pC(Le.DL),
            new UC("RCWSETA", Nq).pC(Le.DL),
            new UC("RCWSETAL", Nq).pC(Le.DL),
            new UC("RCWSSET", Nq).pC(Le.DL),
            new UC("RCWSSETL", Nq).pC(Le.DL),
            new UC("RCWSSETA", Nq).pC(Le.DL),
            new UC("RCWSSETAL", Nq).pC(Le.DL),
            null,
            null,
            null,
            null,
            new UC("ST64BV", sQ.ld, sQ.A, kU).pC(Le.sO)
      },
      {
            null,
            null,
            new UC("LDAPRB", sQ.ED, kU).pC(Le.z),
            null,
            null,
            null,
            new UC("LDAPRH", sQ.ED, kU).pC(Le.z),
            null,
            null,
            null,
            new UC("LDAPR", sQ.ED, kU).pC(Le.z),
            null,
            null,
            null,
            new UC("LDAPR", sQ.A, kU).pC(Le.z)
      },
      {null, null, null, null, null, null, null, null, null, null, null, null, new UC("LD64B", sQ.A, kU).pC(Le.sO)}
   };
   private static final LF AU = LF.pC(
      sQ.NS,
      DirectEncodedMemoryArea.get(11, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(10, 1),
      DirectEncodedMemoryArea.get(12, 9).shift(4),
      LF.kS
   );
   private static final LF jS = pC(sQ.NS, DirectEncodedMemoryArea.get(12, 9).shift(4));
   public static final tz[] ah = new tz[]{
      new UC("STG", sQ.WR, AU).pC(Le.ED), new UC("STZG", sQ.WR, AU).pC(Le.ED), new UC("ST2G", sQ.WR, AU).pC(Le.ED), new UC("STZ2G", sQ.WR, AU).pC(Le.ED)
   };
   public static final tz[] eP = new tz[]{
      new UC("STZGM", sQ.A, jS).pC(Le.Sc), new UC("LDG", sQ.A, jS).pC(Le.ED), new UC("STGM", sQ.A, jS).pC(Le.Sc), new UC("LDGM", sQ.A, jS).pC(Le.Sc)
   };
   public static final tz[] UO = new tz[]{
      new UC("RCWCAS", Nq).pC(Le.DL),
      new UC("RCWCASL", Nq).pC(Le.DL),
      new UC("RCWCASA", Nq).pC(Le.DL),
      new UC("RCWCASAL", Nq).pC(Le.DL),
      new UC("RCWSCAS", Nq).pC(Le.DL),
      new UC("RCWSCASL", Nq).pC(Le.DL),
      new UC("RCWSCASA", Nq).pC(Le.DL),
      new UC("RCWSCASAL", Nq).pC(Le.DL)
   };
   public static final tz[] Ab = new tz[]{
      new UC("RCWCASP", pg).pC(Le.DL, Le.UW),
      new UC("RCWCASPL", pg).pC(Le.DL, Le.UW),
      new UC("RCWCASPA", pg).pC(Le.DL, Le.UW),
      new UC("RCWCASPAL", pg).pC(Le.DL, Le.UW),
      new UC("RCWSCASP", pg).pC(Le.DL, Le.UW),
      new UC("RCWSCASPL", pg).pC(Le.DL, Le.UW),
      new UC("RCWSCASPA", pg).pC(Le.DL, Le.UW),
      new UC("RCWSCASPAL", pg).pC(Le.DL, Le.UW)
   };
   public static final tz[][] rl = new tz[][]{
      null,
      {new UC("LDCLRP", gj).pC(Le.PR), new UC("LDCLRPL", gj).pC(Le.PR), new UC("LDCLRPA", gj).pC(Le.PR), new UC("LDCLRPAL", gj).pC(Le.PR)},
      null,
      {new UC("LDSETP", gj).pC(Le.PR), new UC("LDSETPL", gj).pC(Le.PR), new UC("LDSETPA", gj).pC(Le.PR), new UC("LDSETPAL", gj).pC(Le.PR)}
   };
   public static final tz[][] z = new tz[][]{
      {new UC("SWPP", gj).pC(Le.PR), new UC("SWPPL", gj).pC(Le.PR), new UC("SWPPA", gj).pC(Le.PR), new UC("SWPPAL", gj).pC(Le.PR)},
      {
            new UC("RCWCLRP", gj).pC(Le.DL, Le.UW),
            new UC("RCWCLRPL", gj).pC(Le.DL, Le.UW),
            new UC("RCWCLRPA", gj).pC(Le.DL, Le.UW),
            new UC("RCWCLRPAL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSCLRP", gj).pC(Le.DL, Le.UW),
            new UC("RCWSCLRPL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSCLRPA", gj).pC(Le.DL, Le.UW),
            new UC("RCWSCLRPAL", gj).pC(Le.DL, Le.UW)
      },
      {
            new UC("RCWSWPP", gj).pC(Le.DL, Le.UW),
            new UC("RCWSWPPL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSWPPA", gj).pC(Le.DL, Le.UW),
            new UC("RCWSWPPAL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSWPP", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSWPPL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSWPPA", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSWPPAL", gj).pC(Le.DL, Le.UW)
      },
      {
            new UC("RCWSETP", gj).pC(Le.DL, Le.UW),
            new UC("RCWSETPL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSETPA", gj).pC(Le.DL, Le.UW),
            new UC("RCWSETPAL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSETP", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSETPL", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSETPA", gj).pC(Le.DL, Le.UW),
            new UC("RCWSSETPAL", gj).pC(Le.DL, Le.UW)
      }
   };
   public static final Hu Ek = LF.A(sQ.A);
   public static final Hu hK = LF.A(sQ.ld);
   private static final Hu[] KK = new Hu[]{Ek, hK, sQ.sY};
   private static final Hu[] oB = new Hu[]{Ek, sQ.sY, sQ.ld};
   public static final tz[][] Er = new tz[][]{
      {
            new UC("CPYFP", KK).pC(Le.OB),
            new UC("CPYFPWT", KK).pC(Le.OB),
            new UC("CPYFPRT", KK).pC(Le.OB),
            new UC("CPYFPT", KK).pC(Le.OB),
            new UC("CPYFPWN", KK).pC(Le.OB),
            new UC("CPYFPWTWN", KK).pC(Le.OB),
            new UC("CPYFPRTWN", KK).pC(Le.OB),
            new UC("CPYFPTWN", KK).pC(Le.OB),
            new UC("CPYFPRN", KK).pC(Le.OB),
            new UC("CPYFPWTRN", KK).pC(Le.OB),
            new UC("CPYFPRTRN", KK).pC(Le.OB),
            new UC("CPYFPTRN", KK).pC(Le.OB),
            new UC("CPYFPN", KK).pC(Le.OB),
            new UC("CPYFPWTN", KK).pC(Le.OB),
            new UC("CPYFPRTN", KK).pC(Le.OB),
            new UC("CPYFPTN", KK).pC(Le.OB)
      },
      {
            new UC("CPYFM", KK).pC(Le.OB),
            new UC("CPYFMWT", KK).pC(Le.OB),
            new UC("CPYFMRT", KK).pC(Le.OB),
            new UC("CPYFMT", KK).pC(Le.OB),
            new UC("CPYFMWN", KK).pC(Le.OB),
            new UC("CPYFMWTWN", KK).pC(Le.OB),
            new UC("CPYFMRTWN", KK).pC(Le.OB),
            new UC("CPYFMTWN", KK).pC(Le.OB),
            new UC("CPYFMRN", KK).pC(Le.OB),
            new UC("CPYFMWTRN", KK).pC(Le.OB),
            new UC("CPYFMRTRN", KK).pC(Le.OB),
            new UC("CPYFMTRN", KK).pC(Le.OB),
            new UC("CPYFMN", KK).pC(Le.OB),
            new UC("CPYFMWTN", KK).pC(Le.OB),
            new UC("CPYFMRTN", KK).pC(Le.OB),
            new UC("CPYFMTN", KK).pC(Le.OB)
      },
      {
            new UC("CPYFE", KK).pC(Le.OB),
            new UC("CPYFEWT", KK).pC(Le.OB),
            new UC("CPYFERT", KK).pC(Le.OB),
            new UC("CPYFET", KK).pC(Le.OB),
            new UC("CPYFEWN", KK).pC(Le.OB),
            new UC("CPYFEWTWN", KK).pC(Le.OB),
            new UC("CPYFERTWN", KK).pC(Le.OB),
            new UC("CPYFETWN", KK).pC(Le.OB),
            new UC("CPYFERN", KK).pC(Le.OB),
            new UC("CPYFEWTRN", KK).pC(Le.OB),
            new UC("CPYFERTRN", KK).pC(Le.OB),
            new UC("CPYFETRN", KK).pC(Le.OB),
            new UC("CPYFEN", KK).pC(Le.OB),
            new UC("CPYFEWTN", KK).pC(Le.OB),
            new UC("CPYFERTN", KK).pC(Le.OB),
            new UC("CPYFETN", KK).pC(Le.OB)
      },
      {
            new UC("SETP", oB).pC(Le.OB),
            new UC("SETPT", oB).pC(Le.OB),
            new UC("SETPN", oB).pC(Le.OB),
            new UC("SETPTN", oB).pC(Le.OB),
            new UC("SETM", oB).pC(Le.OB),
            new UC("SETMT", oB).pC(Le.OB),
            new UC("SETMN", oB).pC(Le.OB),
            new UC("SETMTN", oB).pC(Le.OB),
            new UC("SETE", oB).pC(Le.OB),
            new UC("SETET", oB).pC(Le.OB),
            new UC("SETEN", oB).pC(Le.OB),
            new UC("SETETN", oB).pC(Le.OB)
      },
      {
            new UC("CPYP", KK).pC(Le.OB),
            new UC("CPYPWT", KK).pC(Le.OB),
            new UC("CPYPRT", KK).pC(Le.OB),
            new UC("CPYPT", KK).pC(Le.OB),
            new UC("CPYPWN", KK).pC(Le.OB),
            new UC("CPYPWTWN", KK).pC(Le.OB),
            new UC("CPYPRTWN", KK).pC(Le.OB),
            new UC("CPYPTWN", KK).pC(Le.OB),
            new UC("CPYPRN", KK).pC(Le.OB),
            new UC("CPYPWTRN", KK).pC(Le.OB),
            new UC("CPYPRTRN", KK).pC(Le.OB),
            new UC("CPYPTRN", KK).pC(Le.OB),
            new UC("CPYPN", KK).pC(Le.OB),
            new UC("CPYPWTN", KK).pC(Le.OB),
            new UC("CPYPRTN", KK).pC(Le.OB),
            new UC("CPYPTN", KK).pC(Le.OB)
      },
      {
            new UC("CPYM", KK).pC(Le.OB),
            new UC("CPYMWT", KK).pC(Le.OB),
            new UC("CPYMRT", KK).pC(Le.OB),
            new UC("CPYMT", KK).pC(Le.OB),
            new UC("CPYMWN", KK).pC(Le.OB),
            new UC("CPYMWTWN", KK).pC(Le.OB),
            new UC("CPYMRTWN", KK).pC(Le.OB),
            new UC("CPYMTWN", KK).pC(Le.OB),
            new UC("CPYMRN", KK).pC(Le.OB),
            new UC("CPYMWTRN", KK).pC(Le.OB),
            new UC("CPYMRTRN", KK).pC(Le.OB),
            new UC("CPYMTRN", KK).pC(Le.OB),
            new UC("CPYMN", KK).pC(Le.OB),
            new UC("CPYMWTN", KK).pC(Le.OB),
            new UC("CPYMRTN", KK).pC(Le.OB),
            new UC("CPYMTN", KK).pC(Le.OB)
      },
      {
            new UC("CPYE", KK).pC(Le.OB),
            new UC("CPYEWT", KK).pC(Le.OB),
            new UC("CPYERT", KK).pC(Le.OB),
            new UC("CPYET", KK).pC(Le.OB),
            new UC("CPYEWN", KK).pC(Le.OB),
            new UC("CPYEWTWN", KK).pC(Le.OB),
            new UC("CPYERTWN", KK).pC(Le.OB),
            new UC("CPYETWN", KK).pC(Le.OB),
            new UC("CPYERN", KK).pC(Le.OB),
            new UC("CPYEWTRN", KK).pC(Le.OB),
            new UC("CPYERTRN", KK).pC(Le.OB),
            new UC("CPYETRN", KK).pC(Le.OB),
            new UC("CPYEN", KK).pC(Le.OB),
            new UC("CPYEWTN", KK).pC(Le.OB),
            new UC("CPYERTN", KK).pC(Le.OB),
            new UC("CPYETN", KK).pC(Le.OB)
      },
      {
            new UC("SETGP", oB).pC(Le.OB),
            new UC("SETGPT", oB).pC(Le.OB),
            new UC("SETGPN", oB).pC(Le.OB),
            new UC("SETGPTN", oB).pC(Le.OB),
            new UC("SETGM", oB).pC(Le.OB),
            new UC("SETGMT", oB).pC(Le.OB),
            new UC("SETGMN", oB).pC(Le.OB),
            new UC("SETGMTN", oB).pC(Le.OB),
            new UC("SETGE", oB).pC(Le.OB),
            new UC("SETGET", oB).pC(Le.OB),
            new UC("SETGEN", oB).pC(Le.OB),
            new UC("SETGETN", oB).pC(Le.OB)
      }
   };
   public static final Hu FE = LF.kS(sQ.NS, VirtualEncodedMemoryArea.get(-4, 8), LF.kS);
   public static final Hu Aj = LF.kS(sQ.NS, VirtualEncodedMemoryArea.get(-8, 8), LF.kS);
   public static final Hu EX = LF.kS(sQ.NS, VirtualEncodedMemoryArea.get(-16, 8), LF.kS);
   public static final Hu LM = LF.A(sQ.NS, VirtualEncodedMemoryArea.get(4, 8));
   public static final Hu mv = LF.A(sQ.NS, VirtualEncodedMemoryArea.get(8, 8));
   public static final Hu sO = LF.A(sQ.NS, VirtualEncodedMemoryArea.get(16, 8));
   public static final tz[][] os = new tz[][]{
      null,
      null,
      {new UC("LDIAPP", sQ.mv, sQ.PR, mv).pC(Le.Bc), new UC("LDIAPP", sQ.mv, sQ.PR, kU).pC(Le.Bc)},
      {new UC("LDIAPP", sQ.mv, sQ.PR, sO).pC(Le.Bc), new UC("LDIAPP", sQ.mv, sQ.PR, kU).pC(Le.Bc)}
   };
   public static final tz[][] Cu = new tz[][]{
      null,
      null,
      {new UC("STILP", sQ.mv, sQ.PR, Aj).pC(Le.Bc), new UC("STILP", sQ.mv, sQ.PR, kU).pC(Le.Bc)},
      {new UC("STILP", sQ.mv, sQ.PR, EX).pC(Le.Bc), new UC("STILP", sQ.mv, sQ.PR, kU).pC(Le.Bc)}
   };
   public static final tz[][] hZ = new tz[][]{
      null,
      null,
      {new UC("STLR", sQ.mv, FE).pC(Le.Bc), new UC("LDAPR", sQ.mv, LM).pC(Le.Bc)},
      {new UC("STLR", sQ.mv, Aj).pC(Le.Bc), new UC("LDAPR", sQ.mv, mv).pC(Le.Bc)}
   };
   public static final tz[][] UW = new tz[][]{
      {new UC("STLUR", ER.wS, FK).pC(Le.Bc), new UC("LDAPUR", ER.wS, FK).pC(Le.Bc), new UC("STLUR", ER.Cu, FK).pC(Le.Bc), new UC("LDAPUR", ER.Cu, FK).pC(Le.Bc)},
      {new UC("STLUR", ER.UT, FK).pC(Le.Bc), new UC("LDAPUR", ER.UT, FK).pC(Le.Bc)},
      {new UC("STLUR", ER.sY, FK).pC(Le.Bc), new UC("LDAPUR", ER.sY, FK).pC(Le.Bc)},
      {new UC("STLUR", ER.gp, FK).pC(Le.Bc), new UC("LDAPUR", ER.gp, FK).pC(Le.Bc)}
   };
   public static final tz[] PR = new tz[]{new UC("GCSSTR", sQ.A, kU).pC(Le.OI), new UC("GCSSTTR", sQ.A, kU).pC(Le.OI)};

   public static LF pC(gZ var0, IEncodedMemoryArea var1) {
      return LF.pC(var0, var1, LF.kS);
   }

   private static class Av extends AbstractEncodedMemoryArea {
      private final IEncodedMemoryArea pC;

      public Av(IEncodedMemoryArea var1) {
         this.pC = var1;
      }

      @Override
      public int getLength() {
         return 4;
      }

      @Override
      public long decode(byte[] var1) {
         if (DirectEncodedMemoryArea.get(12, 1).decode(var1) == 0L) {
            return 0L;
         } else {
            int var2 = this.pC.decodeInt(var1);
            return var2;
         }
      }
   }
}
