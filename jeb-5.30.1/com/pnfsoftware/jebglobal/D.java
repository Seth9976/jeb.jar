package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.AbstractEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class D {
   private static final String[] Bu = new String[]{
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
   private static final String[] IN = new String[]{"PLDKEEP", "PSTKEEP", null, null, "PLDSTRM", "PSTSTRM"};
   public static final wJ q = new wJ(jD.eo.q, Bu, DirectEncodedMemoryArea.get(0, 5), "#%d");
   public static final wJ RF = new wJ(
      jD.eo.q, IN, new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(15, 1), DirectEncodedMemoryArea.get(12, 2), DirectEncodedMemoryArea.get(0, 3)), "#%d"
   );
   private static final Bf rL = Bf.q(YH.qa, DirectEncodedMemoryArea.get(10, 12));
   private static final Bf eJ = Bf.q(YH.qa, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 1));
   private static final Bf YN = Bf.q(YH.qa, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 2));
   private static final Bf Rv = Bf.q(YH.qa, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 3));
   private static final Bf zx = Bf.q(YH.qa, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(10, 12), 4));
   public static final Je[][] xK = new Je[][]{
      {
            new Qg("STRB", YH.PV, rL),
            new Qg("LDRB", YH.PV, rL),
            new Qg("LDRSB", YH.Uv, rL),
            new Qg("LDRSB", YH.PV, rL),
            new Qg("STR", XD.nf, rL),
            new Qg("LDR", XD.nf, rL),
            new Qg("STR", XD.Bu, zx),
            new Qg("LDR", XD.Bu, zx)
      },
      {
            new Qg("STRH", YH.PV, eJ),
            new Qg("LDRH", YH.PV, eJ),
            new Qg("LDRSH", YH.Uv, eJ),
            new Qg("LDRSH", YH.PV, eJ),
            new Qg("STR", XD.gP, eJ),
            new Qg("LDR", XD.gP, eJ)
      },
      {new Qg("STR", YH.PV, YN), new Qg("LDR", YH.PV, YN), new Qg("LDRSW", YH.Uv, YN), null, new Qg("STR", XD.lm, YN), new Qg("LDR", XD.lm, YN)},
      {new Qg("STR", YH.Uv, Rv), new Qg("LDR", YH.Uv, Rv), new Qg("PRFM", q, Rv), null, new Qg("STR", XD.HF, Rv), new Qg("LDR", XD.HF, Rv)}
   };
   private static final Bf ZT = Bf.q(
      YH.qa,
      DirectEncodedMemoryArea.get(24, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      DirectEncodedMemoryArea.get(15, 7).shift(2),
      Bf.Dw
   );
   private static final Bf Ri = Bf.q(
      YH.qa,
      DirectEncodedMemoryArea.get(24, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      DirectEncodedMemoryArea.get(15, 7).shift(3),
      Bf.Dw
   );
   private static final Bf GY = Bf.q(
      YH.qa,
      DirectEncodedMemoryArea.get(24, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      DirectEncodedMemoryArea.get(15, 7).shift(4),
      Bf.Dw
   );
   public static final Je[] Dw = new Je[]{
      new Qg("STP", YH.PV, YH.Gf, ZT),
      new Qg("LDP", YH.PV, YH.Gf, ZT),
      new Qg("STP", XD.lm, XD.JY, ZT),
      new Qg("LDP", XD.lm, XD.JY, ZT),
      new Qg("STGP", YH.Uv, YH.lm, GY),
      new Qg("LDPSW", YH.Uv, YH.lm, ZT),
      new Qg("STP", XD.HF, XD.io, Ri),
      new Qg("LDP", XD.HF, XD.io, Ri),
      new Qg("STP", YH.Uv, YH.lm, Ri),
      new Qg("LDP", YH.Uv, YH.lm, Ri),
      new Qg("STP", XD.Bu, XD.rL, GY),
      new Qg("LDP", XD.Bu, XD.rL, GY)
   };
   private static final Bf Wx = q(YH.qa, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(15, 7), 2));
   private static final Bf AB = q(YH.qa, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(15, 7), 3));
   private static final Bf CY = q(YH.qa, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(15, 7), 4));
   public static final Je[] Uv = new Je[]{
      new Qg("STNP", YH.PV, YH.Gf, Wx),
      new Qg("LDNP", YH.PV, YH.Gf, Wx),
      new Qg("STNP", XD.lm, XD.JY, Wx),
      new Qg("LDNP", XD.lm, XD.JY, Wx),
      null,
      null,
      new Qg("STNP", XD.HF, XD.io, AB),
      new Qg("LDNP", XD.HF, XD.io, AB),
      new Qg("STNP", YH.Uv, YH.lm, AB),
      new Qg("LDNP", YH.Uv, YH.lm, AB),
      new Qg("STNP", XD.Bu, XD.rL, CY),
      new Qg("LDNP", XD.Bu, XD.rL, CY)
   };
   public static final Je[][] oW = new Je[][]{Uv, Dw, Dw, Dw};
   private static final Ef WI = Bf.q(YH.qa);
   private static final Ef[] Tq = new Ef[]{YH.Ef, YH.PV, WI};
   private static final Ef[] Yp = new Ef[]{YH.Ef, YH.Uv, WI};
   private static final Ef[] Gu = new Ef[]{YH.cC, YH.sH, YH.oQ, YH.xW, WI};
   private static final Ef[] nY = new Ef[]{YH.zz, YH.Uv, WI};
   private static final Ef[] lF = new Ef[]{YH.JY, YH.HF, YH.oW, YH.gO, WI};
   private static final Ef[] nq = new Ef[]{YH.Uv, YH.zz, WI};
   private static final Ef[] NX = new Ef[]{YH.Ef, YH.PV, YH.Gf, WI};
   private static final Ef[] br = new Ef[]{YH.Ef, YH.Uv, YH.lm, WI};
   private static final Ef[] tW = new Ef[]{YH.PV, WI};
   private static final Ef[] ZA = new Ef[]{YH.Uv, WI};
   private static final Ef[] Ov = new Ef[]{YH.PV, YH.Gf, WI};
   private static final Ef[] Lj = new Ef[]{YH.Uv, YH.lm, WI};
   public static final Je[] gO = new Je[]{
      new Qg(0, "STXRB", Tq),
      new Qg(1, "STLXRB", Tq),
      new Qg(2, "CASP", Gu).q(QJ.Lj),
      new Qg(3, "CASPL", Gu).q(QJ.Lj),
      new Qg(4, "LDXRB", tW),
      new Qg(5, "LDAXRB", tW),
      new Qg(6, "CASPA", Gu).q(QJ.Lj),
      new Qg(7, "CASPAL", Gu).q(QJ.Lj),
      new Qg(9, "STLLRB", tW).q(QJ.nv),
      new Qg(9, "STLRB", tW),
      new Qg(10, "CASB", Tq).q(QJ.Lj),
      new Qg(11, "CASLB", Tq).q(QJ.Lj),
      new Qg(12, "LDLARB", tW).q(QJ.nv),
      new Qg(13, "LDARB", tW),
      new Qg(14, "CASAB", Tq).q(QJ.Lj),
      new Qg(15, "CASALB", Tq).q(QJ.Lj),
      new Qg(16, "STXRH", Tq),
      new Qg(17, "STLXRH", Tq),
      new Qg(18, "CASP", lF).q(QJ.Lj),
      new Qg(19, "CASPL", lF).q(QJ.Lj),
      new Qg(20, "LDXRH", tW),
      new Qg(21, "LDAXRH", tW),
      new Qg(22, "CASPA", lF).q(QJ.Lj),
      new Qg(23, "CASPAL", lF).q(QJ.Lj),
      new Qg(25, "STLLRH", tW).q(QJ.nv),
      new Qg(25, "STLRH", tW),
      new Qg(26, "CASH", Tq).q(QJ.Lj),
      new Qg(27, "CASLH", Tq).q(QJ.Lj),
      new Qg(29, "LDLARH", tW).q(QJ.nv),
      new Qg(29, "LDARH", tW),
      new Qg(30, "CASAH", Tq).q(QJ.Lj),
      new Qg(31, "CASALH", Tq).q(QJ.Lj),
      new Qg(32, "STXR", Tq),
      new Qg(33, "STLXR", Tq),
      new Qg(34, "STXP", NX),
      new Qg(35, "STLXP", NX),
      new Qg(36, "LDXR", tW),
      new Qg(37, "LDAXR", tW),
      new Qg(38, "LDXP", Ov),
      new Qg(39, "LDAXP", Ov),
      new Qg(41, "STLLR", tW).q(QJ.nv),
      new Qg(41, "STLR", tW),
      new Qg(42, "CAS", Tq).q(QJ.Lj),
      new Qg(43, "CASL", Tq).q(QJ.Lj),
      new Qg(45, "LDLAR", tW).q(QJ.nv),
      new Qg(45, "LDAR", tW),
      new Qg(46, "CASA", Tq).q(QJ.Lj),
      new Qg(47, "CASAL", Tq).q(QJ.Lj),
      new Qg(48, "STXR", Yp),
      new Qg(49, "STLXR", Yp),
      new Qg(50, "STXP", br),
      new Qg(51, "STLXP", br),
      new Qg(52, "LDXR", ZA),
      new Qg(53, "LDAXR", ZA),
      new Qg(54, "LDXP", Lj),
      new Qg(55, "LDAXP", Lj),
      new Qg(57, "STLLR", ZA).q(QJ.nv),
      new Qg(57, "STLR", ZA),
      new Qg(58, "CAS", nY).q(QJ.Lj),
      new Qg(59, "CASL", nY).q(QJ.Lj),
      new Qg(61, "LDLAR", ZA).q(QJ.nv),
      new Qg(61, "LDAR", ZA),
      new Qg(62, "CASA", nY),
      new Qg(63, "CASAL", nY)
   };
   private static final Bf nv = q(YH.qa, DirectEncodedMemoryArea.get(12, 9));
   public static final Je[] nf = new Je[]{
      new Qg("STURB", YH.PV, nv),
      new Qg("LDURB", YH.PV, nv),
      new Qg("LDURSB", YH.Uv, nv),
      new Qg("LDURSB", YH.PV, nv),
      new Qg("STUR", XD.nf, nv),
      new Qg("LDUR", XD.nf, nv),
      new Qg("STUR", XD.Bu, nv),
      new Qg("LDUR", XD.Bu, nv),
      new Qg("STURH", YH.PV, nv),
      new Qg("LDURH", YH.PV, nv),
      new Qg("LDURSH", YH.Uv, nv),
      new Qg("LDURSH", YH.PV, nv),
      new Qg("STUR", XD.gP, nv),
      new Qg("LDUR", XD.gP, nv),
      null,
      null,
      new Qg("STUR", YH.PV, nv),
      new Qg("LDUR", YH.PV, nv),
      new Qg("LDURSW", YH.Uv, nv),
      null,
      new Qg("STUR", XD.lm, nv),
      new Qg("LDUR", XD.lm, nv),
      null,
      null,
      new Qg("STUR", YH.Uv, nv),
      new Qg("LDUR", YH.Uv, nv),
      new Qg("PRFUM", q, nv),
      null,
      new Qg("STUR", XD.HF, nv),
      new Qg("LDUR", XD.HF, nv),
      null,
      null
   };
   public static final Je[] gP = new Je[]{
      new Qg(0, "STLURB", YH.PV, nv).q(QJ.cY),
      new Qg(1, "LDAPURB", YH.PV, nv).q(QJ.cY),
      new Qg(2, "LDAPURSB", YH.Uv, nv).q(QJ.cY),
      new Qg(3, "LDAPURSB", YH.PV, nv).q(QJ.cY),
      new Qg(4, "STLURH", YH.PV, nv).q(QJ.cY),
      new Qg(5, "LDAPURH", YH.PV, nv).q(QJ.cY),
      new Qg(6, "LDAPURSH", YH.Uv, nv).q(QJ.cY),
      new Qg(7, "LDAPURSH", YH.PV, nv).q(QJ.cY),
      new Qg(8, "STLUR", YH.PV, nv).q(QJ.cY),
      new Qg(9, "LDAPUR", YH.PV, nv).q(QJ.cY),
      new Qg(10, "LDAPURSW", YH.Uv, nv).q(QJ.cY),
      null,
      new Qg(8, "STLUR", YH.Uv, nv).q(QJ.cY),
      new Qg(9, "LDAPUR", YH.Uv, nv).q(QJ.cY)
   };
   public static final Je[] za = new Je[]{
      new Qg("STTRB", YH.PV, nv),
      new Qg("LDTRB", YH.PV, nv),
      new Qg("LDTRSB", YH.Uv, nv),
      new Qg("LDTRSB", YH.PV, nv),
      new Qg("STTRH", YH.PV, nv),
      new Qg("LDTRH", YH.PV, nv),
      new Qg("LDTRSH", YH.Uv, nv),
      new Qg("LDTRSH", YH.PV, nv),
      new Qg("STTR", YH.PV, nv),
      new Qg("LDTR", YH.PV, nv),
      new Qg("LDTRSW", YH.Uv, nv),
      null,
      new Qg("STTR", YH.Uv, nv),
      new Qg("LDTR", YH.Uv, nv),
      null,
      null
   };
   private static final Bf LL = Bf.q(
      YH.qa, DirectEncodedMemoryArea.get(11, 1), VirtualEncodedMemoryArea._1, DirectEncodedMemoryArea.get(10, 1), DirectEncodedMemoryArea.get(12, 9), Bf.Dw
   );
   public static final Je[] lm = new Je[]{
      new Qg("STRB", YH.PV, LL),
      new Qg("LDRB", YH.PV, LL),
      new Qg("LDRSB", YH.Uv, LL),
      new Qg("LDRSB", YH.PV, LL),
      new Qg("STR", XD.nf, LL),
      new Qg("LDR", XD.nf, LL),
      new Qg("STR", XD.Bu, LL),
      new Qg("LDR", XD.Bu, LL),
      new Qg("STRH", YH.PV, LL),
      new Qg("LDRH", YH.PV, LL),
      new Qg("LDRSH", YH.Uv, LL),
      new Qg("LDRSH", YH.PV, LL),
      new Qg("STR", XD.gP, LL),
      new Qg("LDR", XD.gP, LL),
      null,
      null,
      new Qg("STR", YH.PV, LL),
      new Qg("LDR", YH.PV, LL),
      new Qg("LDRSW", YH.Uv, LL),
      null,
      new Qg("STR", XD.lm, LL),
      new Qg("LDR", XD.lm, LL),
      null,
      null,
      new Qg("STR", YH.Uv, LL),
      new Qg("LDR", YH.Uv, LL),
      null,
      null,
      new Qg("STR", XD.HF, LL),
      new Qg("LDR", XD.HF, LL),
      null,
      null
   };
   private static final Bf PQ = Bf.q(
      YH.qa, YH.IN, DirectEncodedMemoryArea.get(13, 3), new D.eo(DirectEncodedMemoryArea.get(30, 2)), DirectEncodedMemoryArea.get(12, 1), Bf.Uv
   );
   private static final Bf fQ = Bf.q(
      YH.qa, YH.IN, DirectEncodedMemoryArea.get(13, 3), new D.eo(VirtualEncodedMemoryArea.get(4, 4)), DirectEncodedMemoryArea.get(12, 1), Bf.Uv
   );
   public static final Je[] zz = new Je[]{
      new Qg("STRB", YH.PV, PQ),
      new Qg("LDRB", YH.PV, PQ),
      new Qg("LDRSB", YH.Uv, PQ),
      new Qg("LDRSB", YH.PV, PQ),
      new Qg("STR", XD.nf, PQ),
      new Qg("LDR", XD.nf, PQ),
      new Qg("STR", XD.Bu, fQ),
      new Qg("LDR", XD.Bu, fQ),
      new Qg("STRH", YH.PV, PQ),
      new Qg("LDRH", YH.PV, PQ),
      new Qg("LDRSH", YH.Uv, PQ),
      new Qg("LDRSH", YH.PV, PQ),
      new Qg("STR", XD.gP, PQ),
      new Qg("LDR", XD.gP, PQ),
      null,
      null,
      new Qg("STR", YH.PV, PQ),
      new Qg("LDR", YH.PV, PQ),
      new Qg("LDRSW", YH.Uv, PQ),
      null,
      new Qg("STR", XD.lm, PQ),
      new Qg("LDR", XD.lm, PQ),
      null,
      null,
      new Qg("STR", YH.Uv, PQ),
      new Qg("LDR", YH.Uv, PQ),
      new Qg("PRFM", q, PQ),
      null,
      new Qg("STR", XD.HF, PQ),
      new Qg("LDR", XD.HF, PQ),
      null,
      null
   };
   public static final Je JY = new Qg("RPRFM", RF, YH.zz, YH.Me).q(QJ.nY);
   private static final Bf fi = Bf.q(YH.AB, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(5, 19), 2), Bf.RF, Bf.Dw);
   public static final Je[] HF = new Je[]{
      new Qg("LDR", YH.PV, fi),
      new Qg("LDR", XD.lm, fi),
      new Qg("LDR", YH.Uv, fi),
      new Qg("LDR", XD.HF, fi),
      new Qg("LDRSW", YH.Uv, fi),
      new Qg("LDR", XD.Bu, fi),
      new Qg("PRFM", q, fi),
      null
   };
   private static final Bf bl = Bf.q(
      YH.qa,
      VirtualEncodedMemoryArea._1,
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(11, 1),
      new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(22, 1), DirectEncodedMemoryArea.get(12, 9), VirtualEncodedMemoryArea._000),
      Bf.Dw
   );
   public static final Je[] LK = new Je[]{new Qg("LDRAA", YH.Uv, bl).q(QJ.IY), new Qg("LDRAB", YH.Uv, bl).q(QJ.IY)};
   private static final Ef[] jb = new Ef[]{YH.Ef, YH.PV, WI};
   private static final Je[] pQ = new Je[]{
      new Qg(0, "LDADDB", jb).q(QJ.Lj),
      new Qg(1, "LDCLRB", jb).q(QJ.Lj),
      new Qg(2, "LDEORB", jb).q(QJ.Lj),
      new Qg(3, "LDSETB", jb).q(QJ.Lj),
      new Qg(4, "LDSMAXB", jb).q(QJ.Lj),
      new Qg(5, "LDSMINB", jb).q(QJ.Lj),
      new Qg(6, "LDUMAXB", jb).q(QJ.Lj),
      new Qg(7, "LDUMINB", jb).q(QJ.Lj),
      new Qg(8, "LDADDLB", jb).q(QJ.Lj),
      new Qg(9, "LDCLRLB", jb).q(QJ.Lj),
      new Qg(10, "LDEORLB", jb).q(QJ.Lj),
      new Qg(11, "LDSETLB", jb).q(QJ.Lj),
      new Qg(12, "LDSMAXLB", jb).q(QJ.Lj),
      new Qg(13, "LDSMINLB", jb).q(QJ.Lj),
      new Qg(14, "LDUMAXLB", jb).q(QJ.Lj),
      new Qg(15, "LDUMINLB", jb).q(QJ.Lj),
      new Qg(16, "LDADDAB", jb).q(QJ.Lj),
      new Qg(17, "LDCLRAB", jb).q(QJ.Lj),
      new Qg(18, "LDEORAB", jb).q(QJ.Lj),
      new Qg(19, "LDSETAB", jb).q(QJ.Lj),
      new Qg(20, "LDSMAXAB", jb).q(QJ.Lj),
      new Qg(21, "LDSMINAB", jb).q(QJ.Lj),
      new Qg(22, "LDUMAXAB", jb).q(QJ.Lj),
      new Qg(23, "LDUMINAB", jb).q(QJ.Lj),
      new Qg(24, "LDADDALB", jb).q(QJ.Lj),
      new Qg(25, "LDCLRALB", jb).q(QJ.Lj),
      new Qg(26, "LDEORALB", jb).q(QJ.Lj),
      new Qg(27, "LDSETALB", jb).q(QJ.Lj),
      new Qg(28, "LDSMAXALB", jb).q(QJ.Lj),
      new Qg(29, "LDSMINALB", jb).q(QJ.Lj),
      new Qg(30, "LDUMAXALB", jb).q(QJ.Lj),
      new Qg(31, "LDUMINALB", jb).q(QJ.Lj)
   };
   private static final Je[] kf = new Je[]{
      new Qg(0, "LDADDH", jb).q(QJ.Lj),
      new Qg(1, "LDCLRH", jb).q(QJ.Lj),
      new Qg(2, "LDEORH", jb).q(QJ.Lj),
      new Qg(3, "LDSETH", jb).q(QJ.Lj),
      new Qg(4, "LDSMAXH", jb).q(QJ.Lj),
      new Qg(5, "LDSMINH", jb).q(QJ.Lj),
      new Qg(6, "LDUMAXH", jb).q(QJ.Lj),
      new Qg(7, "LDUMINH", jb).q(QJ.Lj),
      new Qg(8, "LDADDLH", jb).q(QJ.Lj),
      new Qg(9, "LDCLRLH", jb).q(QJ.Lj),
      new Qg(10, "LDEORLH", jb).q(QJ.Lj),
      new Qg(11, "LDSETLH", jb).q(QJ.Lj),
      new Qg(12, "LDSMAXLH", jb).q(QJ.Lj),
      new Qg(13, "LDSMINLH", jb).q(QJ.Lj),
      new Qg(14, "LDUMAXLH", jb).q(QJ.Lj),
      new Qg(15, "LDUMINLH", jb).q(QJ.Lj),
      new Qg(16, "LDADDAH", jb).q(QJ.Lj),
      new Qg(17, "LDCLRAH", jb).q(QJ.Lj),
      new Qg(18, "LDEORAH", jb).q(QJ.Lj),
      new Qg(19, "LDSETAH", jb).q(QJ.Lj),
      new Qg(20, "LDSMAXAH", jb).q(QJ.Lj),
      new Qg(21, "LDSMINAH", jb).q(QJ.Lj),
      new Qg(22, "LDUMAXAH", jb).q(QJ.Lj),
      new Qg(23, "LDUMINAH", jb).q(QJ.Lj),
      new Qg(24, "LDADDALH", jb).q(QJ.Lj),
      new Qg(25, "LDCLRALH", jb).q(QJ.Lj),
      new Qg(26, "LDEORALH", jb).q(QJ.Lj),
      new Qg(27, "LDSETALH", jb).q(QJ.Lj),
      new Qg(28, "LDSMAXALH", jb).q(QJ.Lj),
      new Qg(29, "LDSMINALH", jb).q(QJ.Lj),
      new Qg(30, "LDUMAXALH", jb).q(QJ.Lj),
      new Qg(31, "LDUMINALH", jb).q(QJ.Lj)
   };
   private static final Je[] GM = new Je[]{
      new Qg(0, "LDADD", jb).q(QJ.Lj),
      new Qg(1, "LDCLR", jb).q(QJ.Lj),
      new Qg(2, "LDEOR", jb).q(QJ.Lj),
      new Qg(3, "LDSET", jb).q(QJ.Lj),
      new Qg(4, "LDSMAX", jb).q(QJ.Lj),
      new Qg(5, "LDSMIN", jb).q(QJ.Lj),
      new Qg(6, "LDUMAX", jb).q(QJ.Lj),
      new Qg(7, "LDUMIN", jb).q(QJ.Lj),
      new Qg(8, "LDADDL", jb).q(QJ.Lj),
      new Qg(9, "LDCLRL", jb).q(QJ.Lj),
      new Qg(10, "LDEORL", jb).q(QJ.Lj),
      new Qg(11, "LDSETL", jb).q(QJ.Lj),
      new Qg(12, "LDSMAXL", jb).q(QJ.Lj),
      new Qg(13, "LDSMINL", jb).q(QJ.Lj),
      new Qg(14, "LDUMAXL", jb).q(QJ.Lj),
      new Qg(15, "LDUMINL", jb).q(QJ.Lj),
      new Qg(16, "LDADDA", jb).q(QJ.Lj),
      new Qg(17, "LDCLRA", jb).q(QJ.Lj),
      new Qg(18, "LDEORA", jb).q(QJ.Lj),
      new Qg(19, "LDSETA", jb).q(QJ.Lj),
      new Qg(20, "LDSMAXA", jb).q(QJ.Lj),
      new Qg(21, "LDSMINA", jb).q(QJ.Lj),
      new Qg(22, "LDUMAXA", jb).q(QJ.Lj),
      new Qg(23, "LDUMINA", jb).q(QJ.Lj),
      new Qg(24, "LDADDAL", jb).q(QJ.Lj),
      new Qg(25, "LDCLRAL", jb).q(QJ.Lj),
      new Qg(26, "LDEORAL", jb).q(QJ.Lj),
      new Qg(27, "LDSETAL", jb).q(QJ.Lj),
      new Qg(28, "LDSMAXAL", jb).q(QJ.Lj),
      new Qg(29, "LDSMINAL", jb).q(QJ.Lj),
      new Qg(30, "LDUMAXAL", jb).q(QJ.Lj),
      new Qg(31, "LDUMINAL", jb).q(QJ.Lj)
   };
   private static final Ef[] TQ = new Ef[]{YH.zz, YH.Uv, WI};
   private static final Je[] Yw = new Je[]{
      new Qg(0, "LDADD", TQ).q(QJ.Lj),
      new Qg(1, "LDCLR", TQ).q(QJ.Lj),
      new Qg(2, "LDEOR", TQ).q(QJ.Lj),
      new Qg(3, "LDSET", TQ).q(QJ.Lj),
      new Qg(4, "LDSMAX", TQ).q(QJ.Lj),
      new Qg(5, "LDSMIN", TQ).q(QJ.Lj),
      new Qg(6, "LDUMAX", TQ).q(QJ.Lj),
      new Qg(7, "LDUMIN", TQ).q(QJ.Lj),
      new Qg(8, "LDADDL", TQ).q(QJ.Lj),
      new Qg(9, "LDCLRL", TQ).q(QJ.Lj),
      new Qg(10, "LDEORL", TQ).q(QJ.Lj),
      new Qg(11, "LDSETL", TQ).q(QJ.Lj),
      new Qg(12, "LDSMAXL", TQ).q(QJ.Lj),
      new Qg(13, "LDSMINL", TQ).q(QJ.Lj),
      new Qg(14, "LDUMAXL", TQ).q(QJ.Lj),
      new Qg(15, "LDUMINL", TQ).q(QJ.Lj),
      new Qg(16, "LDADDA", TQ).q(QJ.Lj),
      new Qg(17, "LDCLRA", TQ).q(QJ.Lj),
      new Qg(18, "LDEORA", TQ).q(QJ.Lj),
      new Qg(19, "LDSETA", TQ).q(QJ.Lj),
      new Qg(20, "LDSMAXA", TQ).q(QJ.Lj),
      new Qg(21, "LDSMINA", TQ).q(QJ.Lj),
      new Qg(22, "LDUMAXA", TQ).q(QJ.Lj),
      new Qg(23, "LDUMINA", TQ).q(QJ.Lj),
      new Qg(24, "LDADDAL", TQ).q(QJ.Lj),
      new Qg(25, "LDCLRAL", TQ).q(QJ.Lj),
      new Qg(26, "LDEORAL", TQ).q(QJ.Lj),
      new Qg(27, "LDSETAL", TQ).q(QJ.Lj),
      new Qg(28, "LDSMAXAL", TQ).q(QJ.Lj),
      new Qg(29, "LDSMINAL", TQ).q(QJ.Lj),
      new Qg(30, "LDUMAXAL", TQ).q(QJ.Lj),
      new Qg(31, "LDUMINAL", TQ).q(QJ.Lj)
   };
   public static final Je[][] io = new Je[][]{pQ, kf, GM, Yw};
   private static final Ef[] IY = new Ef[]{YH.Ef, WI};
   private static final Je[] qR = new Je[]{
      new Qg(0, "STADDB", IY).q(QJ.Lj),
      new Qg(1, "STCLRB", IY).q(QJ.Lj),
      new Qg(2, "STEORB", IY).q(QJ.Lj),
      new Qg(3, "STSETB", IY).q(QJ.Lj),
      new Qg(4, "STSMAXB", IY).q(QJ.Lj),
      new Qg(5, "STSMINB", IY).q(QJ.Lj),
      new Qg(6, "STUMAXB", IY).q(QJ.Lj),
      new Qg(7, "STUMINB", IY).q(QJ.Lj),
      new Qg(8, "STADDLB", IY).q(QJ.Lj),
      new Qg(9, "STCLRLB", IY).q(QJ.Lj),
      new Qg(10, "STEORLB", IY).q(QJ.Lj),
      new Qg(11, "STSETLB", IY).q(QJ.Lj),
      new Qg(12, "STSMAXLB", IY).q(QJ.Lj),
      new Qg(13, "STSMINLB", IY).q(QJ.Lj),
      new Qg(14, "STUMAXLB", IY).q(QJ.Lj),
      new Qg(15, "STUMINLB", IY).q(QJ.Lj)
   };
   private static final Je[] YA = new Je[]{
      new Qg(0, "STADDH", IY).q(QJ.Lj),
      new Qg(1, "STCLRH", IY).q(QJ.Lj),
      new Qg(2, "STEORH", IY).q(QJ.Lj),
      new Qg(3, "STSETH", IY).q(QJ.Lj),
      new Qg(4, "STSMAXH", IY).q(QJ.Lj),
      new Qg(5, "STSMINH", IY).q(QJ.Lj),
      new Qg(6, "STUMAXH", IY).q(QJ.Lj),
      new Qg(7, "STUMINH", IY).q(QJ.Lj),
      new Qg(8, "STADDLH", IY).q(QJ.Lj),
      new Qg(9, "STCLRLH", IY).q(QJ.Lj),
      new Qg(10, "STEORLH", IY).q(QJ.Lj),
      new Qg(11, "STSETLH", IY).q(QJ.Lj),
      new Qg(12, "STSMAXLH", IY).q(QJ.Lj),
      new Qg(13, "STSMINLH", IY).q(QJ.Lj),
      new Qg(14, "STUMAXLH", IY).q(QJ.Lj),
      new Qg(15, "STUMINLH", IY).q(QJ.Lj)
   };
   private static final Je[] fw = new Je[]{
      new Qg(0, "STADD", IY).q(QJ.Lj),
      new Qg(1, "STCLR", IY).q(QJ.Lj),
      new Qg(2, "STEOR", IY).q(QJ.Lj),
      new Qg(3, "STSET", IY).q(QJ.Lj),
      new Qg(4, "STSMAX", IY).q(QJ.Lj),
      new Qg(5, "STSMIN", IY).q(QJ.Lj),
      new Qg(6, "STUMAX", IY).q(QJ.Lj),
      new Qg(7, "STUMIN", IY).q(QJ.Lj),
      new Qg(8, "STADDL", IY).q(QJ.Lj),
      new Qg(9, "STCLRL", IY).q(QJ.Lj),
      new Qg(10, "STEORL", IY).q(QJ.Lj),
      new Qg(11, "STSETL", IY).q(QJ.Lj),
      new Qg(12, "STSMAXL", IY).q(QJ.Lj),
      new Qg(13, "STSMINL", IY).q(QJ.Lj),
      new Qg(14, "STUMAXL", IY).q(QJ.Lj),
      new Qg(15, "STUMINL", IY).q(QJ.Lj)
   };
   private static final Ef[] Wp = new Ef[]{YH.zz, WI};
   private static final Je[] cY = new Je[]{
      new Qg(0, "STADD", Wp).q(QJ.Lj),
      new Qg(1, "STCLR", Wp).q(QJ.Lj),
      new Qg(2, "STEOR", Wp).q(QJ.Lj),
      new Qg(3, "STSET", Wp).q(QJ.Lj),
      new Qg(4, "STSMAX", Wp).q(QJ.Lj),
      new Qg(5, "STSMIN", Wp).q(QJ.Lj),
      new Qg(6, "STUMAX", Wp).q(QJ.Lj),
      new Qg(7, "STUMIN", Wp).q(QJ.Lj),
      new Qg(8, "STADDL", Wp).q(QJ.Lj),
      new Qg(9, "STCLRL", Wp).q(QJ.Lj),
      new Qg(10, "STEORL", Wp).q(QJ.Lj),
      new Qg(11, "STSETL", Wp).q(QJ.Lj),
      new Qg(12, "STSMAXL", Wp).q(QJ.Lj),
      new Qg(13, "STSMINL", Wp).q(QJ.Lj),
      new Qg(14, "STUMAXL", Wp).q(QJ.Lj),
      new Qg(15, "STUMINL", Wp).q(QJ.Lj)
   };
   public static final Je[][] qa = new Je[][]{qR, YA, fw, cY};
   public static final Je[][] Hk = new Je[][]{
      {
            new Qg(0, "SWPB", Tq).q(QJ.Lj),
            new Qg(1, "SWPLB", Tq).q(QJ.Lj),
            new Qg(2, "SWPAB", Tq).q(QJ.Lj),
            new Qg(3, "SWPALB", Tq).q(QJ.Lj),
            new Qg(4, "SWPH", Tq).q(QJ.Lj),
            new Qg(5, "SWPLH", Tq).q(QJ.Lj),
            new Qg(6, "SWPAH", Tq).q(QJ.Lj),
            new Qg(7, "SWPALH", Tq).q(QJ.Lj),
            new Qg(8, "SWP", Tq).q(QJ.Lj),
            new Qg(9, "SWPL", Tq).q(QJ.Lj),
            new Qg(10, "SWPA", Tq).q(QJ.Lj),
            new Qg(11, "SWPAL", Tq).q(QJ.Lj),
            new Qg(12, "SWP", nY).q(QJ.Lj),
            new Qg(13, "SWPL", nY).q(QJ.Lj),
            new Qg(14, "SWPA", nY).q(QJ.Lj),
            new Qg(15, "SWPAL", nY).q(QJ.Lj)
      },
      {
            new Qg("RCWCLR", nY).q(QJ.Al),
            new Qg("RCWCLRL", nY).q(QJ.Al),
            new Qg("RCWCLRA", nY).q(QJ.Al),
            new Qg("RCWCLRAL", nY).q(QJ.Al),
            new Qg("RCWSCLR", nY).q(QJ.Al),
            new Qg("RCWSCLRL", nY).q(QJ.Al),
            new Qg("RCWSCLRA", nY).q(QJ.Al),
            new Qg("RCWSCLRAL", nY).q(QJ.Al),
            null,
            null,
            null,
            null,
            new Qg("ST64B", YH.Uv, WI).q(QJ.Qu)
      },
      {
            new Qg("RCWSWP", nY).q(QJ.Al),
            new Qg("RCWSWPL", nY).q(QJ.Al),
            new Qg("RCWSWPA", nY).q(QJ.Al),
            new Qg("RCWSWPAL", nY).q(QJ.Al),
            new Qg("RCWSSWP", nY).q(QJ.Al),
            new Qg("RCWSSWPL", nY).q(QJ.Al),
            new Qg("RCWSSWPA", nY).q(QJ.Al),
            new Qg("RCWSSWPAL", nY).q(QJ.Al),
            null,
            null,
            null,
            null,
            new Qg("ST64BV0", YH.zz, YH.Uv, WI).q(QJ.Qu)
      },
      {
            new Qg("RCWSET", nY).q(QJ.Al),
            new Qg("RCWSETL", nY).q(QJ.Al),
            new Qg("RCWSETA", nY).q(QJ.Al),
            new Qg("RCWSETAL", nY).q(QJ.Al),
            new Qg("RCWSSET", nY).q(QJ.Al),
            new Qg("RCWSSETL", nY).q(QJ.Al),
            new Qg("RCWSSETA", nY).q(QJ.Al),
            new Qg("RCWSSETAL", nY).q(QJ.Al),
            null,
            null,
            null,
            null,
            new Qg("ST64BV", YH.zz, YH.Uv, WI).q(QJ.Qu)
      },
      {
            null,
            null,
            new Qg("LDAPRB", YH.PV, WI).q(QJ.qR),
            null,
            null,
            null,
            new Qg("LDAPRH", YH.PV, WI).q(QJ.qR),
            null,
            null,
            null,
            new Qg("LDAPR", YH.PV, WI).q(QJ.qR),
            null,
            null,
            null,
            new Qg("LDAPR", YH.Uv, WI).q(QJ.qR)
      },
      {null, null, null, null, null, null, null, null, null, null, null, null, new Qg("LD64B", YH.Uv, WI).q(QJ.Qu)}
   };
   private static final Bf PY = Bf.q(
      YH.qa,
      DirectEncodedMemoryArea.get(11, 1),
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(10, 1),
      DirectEncodedMemoryArea.get(12, 9).shift(4),
      Bf.Dw
   );
   private static final Bf cR = q(YH.qa, DirectEncodedMemoryArea.get(12, 9).shift(4));
   public static final Je[] Me = new Je[]{
      new Qg("STG", YH.io, PY).q(QJ.jb), new Qg("STZG", YH.io, PY).q(QJ.jb), new Qg("ST2G", YH.io, PY).q(QJ.jb), new Qg("STZ2G", YH.io, PY).q(QJ.jb)
   };
   public static final Je[] PV = new Je[]{
      new Qg("STZGM", YH.Uv, cR).q(QJ.pQ), new Qg("LDG", YH.Uv, cR).q(QJ.jb), new Qg("STGM", YH.Uv, cR).q(QJ.pQ), new Qg("LDGM", YH.Uv, cR).q(QJ.pQ)
   };
   public static final Je[] oQ = new Je[]{
      new Qg("RCWCAS", nY).q(QJ.Al),
      new Qg("RCWCASL", nY).q(QJ.Al),
      new Qg("RCWCASA", nY).q(QJ.Al),
      new Qg("RCWCASAL", nY).q(QJ.Al),
      new Qg("RCWSCAS", nY).q(QJ.Al),
      new Qg("RCWSCASL", nY).q(QJ.Al),
      new Qg("RCWSCASA", nY).q(QJ.Al),
      new Qg("RCWSCASAL", nY).q(QJ.Al)
   };
   public static final Je[] xW = new Je[]{
      new Qg("RCWCASP", lF).q(QJ.Al, QJ.of),
      new Qg("RCWCASPL", lF).q(QJ.Al, QJ.of),
      new Qg("RCWCASPA", lF).q(QJ.Al, QJ.of),
      new Qg("RCWCASPAL", lF).q(QJ.Al, QJ.of),
      new Qg("RCWSCASP", lF).q(QJ.Al, QJ.of),
      new Qg("RCWSCASPL", lF).q(QJ.Al, QJ.of),
      new Qg("RCWSCASPA", lF).q(QJ.Al, QJ.of),
      new Qg("RCWSCASPAL", lF).q(QJ.Al, QJ.of)
   };
   public static final Je[][] KT = new Je[][]{
      null,
      {new Qg("LDCLRP", nq).q(QJ.os), new Qg("LDCLRPL", nq).q(QJ.os), new Qg("LDCLRPA", nq).q(QJ.os), new Qg("LDCLRPAL", nq).q(QJ.os)},
      null,
      {new Qg("LDSETP", nq).q(QJ.os), new Qg("LDSETPL", nq).q(QJ.os), new Qg("LDSETPA", nq).q(QJ.os), new Qg("LDSETPAL", nq).q(QJ.os)}
   };
   public static final Je[][] Gf = new Je[][]{
      {new Qg("SWPP", nq).q(QJ.os), new Qg("SWPPL", nq).q(QJ.os), new Qg("SWPPA", nq).q(QJ.os), new Qg("SWPPAL", nq).q(QJ.os)},
      {
            new Qg("RCWCLRP", nq).q(QJ.Al, QJ.of),
            new Qg("RCWCLRPL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWCLRPA", nq).q(QJ.Al, QJ.of),
            new Qg("RCWCLRPAL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSCLRP", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSCLRPL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSCLRPA", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSCLRPAL", nq).q(QJ.Al, QJ.of)
      },
      {
            new Qg("RCWSWPP", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSWPPL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSWPPA", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSWPPAL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSWPP", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSWPPL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSWPPA", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSWPPAL", nq).q(QJ.Al, QJ.of)
      },
      {
            new Qg("RCWSETP", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSETPL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSETPA", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSETPAL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSETP", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSETPL", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSETPA", nq).q(QJ.Al, QJ.of),
            new Qg("RCWSSETPAL", nq).q(QJ.Al, QJ.of)
      }
   };
   public static final Ef Ef = Bf.RF(YH.Uv);
   public static final Ef cC = Bf.RF(YH.zz);
   private static final Ef[] eC = new Ef[]{Ef, cC, YH.za};
   private static final Ef[] ND = new Ef[]{Ef, YH.za, YH.zz};
   public static final Je[][] sH = new Je[][]{
      {
            new Qg("CPYFP", eC).q(QJ.Sz),
            new Qg("CPYFPWT", eC).q(QJ.Sz),
            new Qg("CPYFPRT", eC).q(QJ.Sz),
            new Qg("CPYFPT", eC).q(QJ.Sz),
            new Qg("CPYFPWN", eC).q(QJ.Sz),
            new Qg("CPYFPWTWN", eC).q(QJ.Sz),
            new Qg("CPYFPRTWN", eC).q(QJ.Sz),
            new Qg("CPYFPTWN", eC).q(QJ.Sz),
            new Qg("CPYFPRN", eC).q(QJ.Sz),
            new Qg("CPYFPWTRN", eC).q(QJ.Sz),
            new Qg("CPYFPRTRN", eC).q(QJ.Sz),
            new Qg("CPYFPTRN", eC).q(QJ.Sz),
            new Qg("CPYFPN", eC).q(QJ.Sz),
            new Qg("CPYFPWTN", eC).q(QJ.Sz),
            new Qg("CPYFPRTN", eC).q(QJ.Sz),
            new Qg("CPYFPTN", eC).q(QJ.Sz)
      },
      {
            new Qg("CPYFM", eC).q(QJ.Sz),
            new Qg("CPYFMWT", eC).q(QJ.Sz),
            new Qg("CPYFMRT", eC).q(QJ.Sz),
            new Qg("CPYFMT", eC).q(QJ.Sz),
            new Qg("CPYFMWN", eC).q(QJ.Sz),
            new Qg("CPYFMWTWN", eC).q(QJ.Sz),
            new Qg("CPYFMRTWN", eC).q(QJ.Sz),
            new Qg("CPYFMTWN", eC).q(QJ.Sz),
            new Qg("CPYFMRN", eC).q(QJ.Sz),
            new Qg("CPYFMWTRN", eC).q(QJ.Sz),
            new Qg("CPYFMRTRN", eC).q(QJ.Sz),
            new Qg("CPYFMTRN", eC).q(QJ.Sz),
            new Qg("CPYFMN", eC).q(QJ.Sz),
            new Qg("CPYFMWTN", eC).q(QJ.Sz),
            new Qg("CPYFMRTN", eC).q(QJ.Sz),
            new Qg("CPYFMTN", eC).q(QJ.Sz)
      },
      {
            new Qg("CPYFE", eC).q(QJ.Sz),
            new Qg("CPYFEWT", eC).q(QJ.Sz),
            new Qg("CPYFERT", eC).q(QJ.Sz),
            new Qg("CPYFET", eC).q(QJ.Sz),
            new Qg("CPYFEWN", eC).q(QJ.Sz),
            new Qg("CPYFEWTWN", eC).q(QJ.Sz),
            new Qg("CPYFERTWN", eC).q(QJ.Sz),
            new Qg("CPYFETWN", eC).q(QJ.Sz),
            new Qg("CPYFERN", eC).q(QJ.Sz),
            new Qg("CPYFEWTRN", eC).q(QJ.Sz),
            new Qg("CPYFERTRN", eC).q(QJ.Sz),
            new Qg("CPYFETRN", eC).q(QJ.Sz),
            new Qg("CPYFEN", eC).q(QJ.Sz),
            new Qg("CPYFEWTN", eC).q(QJ.Sz),
            new Qg("CPYFERTN", eC).q(QJ.Sz),
            new Qg("CPYFETN", eC).q(QJ.Sz)
      },
      {
            new Qg("SETP", ND).q(QJ.Sz),
            new Qg("SETPT", ND).q(QJ.Sz),
            new Qg("SETPN", ND).q(QJ.Sz),
            new Qg("SETPTN", ND).q(QJ.Sz),
            new Qg("SETM", ND).q(QJ.Sz),
            new Qg("SETMT", ND).q(QJ.Sz),
            new Qg("SETMN", ND).q(QJ.Sz),
            new Qg("SETMTN", ND).q(QJ.Sz),
            new Qg("SETE", ND).q(QJ.Sz),
            new Qg("SETET", ND).q(QJ.Sz),
            new Qg("SETEN", ND).q(QJ.Sz),
            new Qg("SETETN", ND).q(QJ.Sz)
      },
      {
            new Qg("CPYP", eC).q(QJ.Sz),
            new Qg("CPYPWT", eC).q(QJ.Sz),
            new Qg("CPYPRT", eC).q(QJ.Sz),
            new Qg("CPYPT", eC).q(QJ.Sz),
            new Qg("CPYPWN", eC).q(QJ.Sz),
            new Qg("CPYPWTWN", eC).q(QJ.Sz),
            new Qg("CPYPRTWN", eC).q(QJ.Sz),
            new Qg("CPYPTWN", eC).q(QJ.Sz),
            new Qg("CPYPRN", eC).q(QJ.Sz),
            new Qg("CPYPWTRN", eC).q(QJ.Sz),
            new Qg("CPYPRTRN", eC).q(QJ.Sz),
            new Qg("CPYPTRN", eC).q(QJ.Sz),
            new Qg("CPYPN", eC).q(QJ.Sz),
            new Qg("CPYPWTN", eC).q(QJ.Sz),
            new Qg("CPYPRTN", eC).q(QJ.Sz),
            new Qg("CPYPTN", eC).q(QJ.Sz)
      },
      {
            new Qg("CPYM", eC).q(QJ.Sz),
            new Qg("CPYMWT", eC).q(QJ.Sz),
            new Qg("CPYMRT", eC).q(QJ.Sz),
            new Qg("CPYMT", eC).q(QJ.Sz),
            new Qg("CPYMWN", eC).q(QJ.Sz),
            new Qg("CPYMWTWN", eC).q(QJ.Sz),
            new Qg("CPYMRTWN", eC).q(QJ.Sz),
            new Qg("CPYMTWN", eC).q(QJ.Sz),
            new Qg("CPYMRN", eC).q(QJ.Sz),
            new Qg("CPYMWTRN", eC).q(QJ.Sz),
            new Qg("CPYMRTRN", eC).q(QJ.Sz),
            new Qg("CPYMTRN", eC).q(QJ.Sz),
            new Qg("CPYMN", eC).q(QJ.Sz),
            new Qg("CPYMWTN", eC).q(QJ.Sz),
            new Qg("CPYMRTN", eC).q(QJ.Sz),
            new Qg("CPYMTN", eC).q(QJ.Sz)
      },
      {
            new Qg("CPYE", eC).q(QJ.Sz),
            new Qg("CPYEWT", eC).q(QJ.Sz),
            new Qg("CPYERT", eC).q(QJ.Sz),
            new Qg("CPYET", eC).q(QJ.Sz),
            new Qg("CPYEWN", eC).q(QJ.Sz),
            new Qg("CPYEWTWN", eC).q(QJ.Sz),
            new Qg("CPYERTWN", eC).q(QJ.Sz),
            new Qg("CPYETWN", eC).q(QJ.Sz),
            new Qg("CPYERN", eC).q(QJ.Sz),
            new Qg("CPYEWTRN", eC).q(QJ.Sz),
            new Qg("CPYERTRN", eC).q(QJ.Sz),
            new Qg("CPYETRN", eC).q(QJ.Sz),
            new Qg("CPYEN", eC).q(QJ.Sz),
            new Qg("CPYEWTN", eC).q(QJ.Sz),
            new Qg("CPYERTN", eC).q(QJ.Sz),
            new Qg("CPYETN", eC).q(QJ.Sz)
      },
      {
            new Qg("SETGP", ND).q(QJ.Sz),
            new Qg("SETGPT", ND).q(QJ.Sz),
            new Qg("SETGPN", ND).q(QJ.Sz),
            new Qg("SETGPTN", ND).q(QJ.Sz),
            new Qg("SETGM", ND).q(QJ.Sz),
            new Qg("SETGMT", ND).q(QJ.Sz),
            new Qg("SETGMN", ND).q(QJ.Sz),
            new Qg("SETGMTN", ND).q(QJ.Sz),
            new Qg("SETGE", ND).q(QJ.Sz),
            new Qg("SETGET", ND).q(QJ.Sz),
            new Qg("SETGEN", ND).q(QJ.Sz),
            new Qg("SETGETN", ND).q(QJ.Sz)
      }
   };
   public static final Ef CE = Bf.xK(YH.qa, VirtualEncodedMemoryArea.get(-4, 8), Bf.Dw);
   public static final Ef wF = Bf.xK(YH.qa, VirtualEncodedMemoryArea.get(-8, 8), Bf.Dw);
   public static final Ef If = Bf.xK(YH.qa, VirtualEncodedMemoryArea.get(-16, 8), Bf.Dw);
   public static final Ef Dz = Bf.RF(YH.qa, VirtualEncodedMemoryArea.get(4, 8));
   public static final Ef mI = Bf.RF(YH.qa, VirtualEncodedMemoryArea.get(8, 8));
   public static final Ef jq = Bf.RF(YH.qa, VirtualEncodedMemoryArea.get(16, 8));
   public static final Je[][] ui = new Je[][]{
      null,
      null,
      {new Qg("LDIAPP", YH.TX, YH.rL, mI).q(QJ.mJ), new Qg("LDIAPP", YH.TX, YH.rL, WI).q(QJ.mJ)},
      {new Qg("LDIAPP", YH.TX, YH.rL, jq).q(QJ.mJ), new Qg("LDIAPP", YH.TX, YH.rL, WI).q(QJ.mJ)}
   };
   public static final Je[][] TX = new Je[][]{
      null,
      null,
      {new Qg("STILP", YH.TX, YH.rL, wF).q(QJ.mJ), new Qg("STILP", YH.TX, YH.rL, WI).q(QJ.mJ)},
      {new Qg("STILP", YH.TX, YH.rL, If).q(QJ.mJ), new Qg("STILP", YH.TX, YH.rL, WI).q(QJ.mJ)}
   };
   public static final Je[][] Rr = new Je[][]{
      null,
      null,
      {new Qg("STLR", YH.TX, CE).q(QJ.mJ), new Qg("LDAPR", YH.TX, Dz).q(QJ.mJ)},
      {new Qg("STLR", YH.TX, wF).q(QJ.mJ), new Qg("LDAPR", YH.TX, mI).q(QJ.mJ)}
   };
   public static final Je[][] EB = new Je[][]{
      {new Qg("STLUR", XD.nf, nv).q(QJ.mJ), new Qg("LDAPUR", XD.nf, nv).q(QJ.mJ), new Qg("STLUR", XD.Bu, nv).q(QJ.mJ), new Qg("LDAPUR", XD.Bu, nv).q(QJ.mJ)},
      {new Qg("STLUR", XD.gP, nv).q(QJ.mJ), new Qg("LDAPUR", XD.gP, nv).q(QJ.mJ)},
      {new Qg("STLUR", XD.lm, nv).q(QJ.mJ), new Qg("LDAPUR", XD.lm, nv).q(QJ.mJ)},
      {new Qg("STLUR", XD.HF, nv).q(QJ.mJ), new Qg("LDAPUR", XD.HF, nv).q(QJ.mJ)}
   };
   public static final Je[] Xo = new Je[]{new Qg("GCSSTR", YH.Uv, WI).q(QJ.Bs), new Qg("GCSSTTR", YH.Uv, WI).q(QJ.Bs)};

   public static Bf q(rR var0, IEncodedMemoryArea var1) {
      return Bf.q(var0, var1, Bf.Dw);
   }

   private static class eo extends AbstractEncodedMemoryArea {
      private final IEncodedMemoryArea q;

      public eo(IEncodedMemoryArea var1) {
         this.q = var1;
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
            int var2 = this.q.decodeInt(var1);
            return var2;
         }
      }
   }
}
