package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class aD {
   private static final OQ YN = OQ.q(525570);
   private static final de Rv = new de.qV(de.EE.q, de.EE.xK, de.EE.Dw).RF(DirectEncodedMemoryArea.get(4, 2));
   private static final de zx = new de.qV(de.EE.xK, de.EE.Dw).RF(DirectEncodedMemoryArea.get(5, 7));
   private static final de ZT = new de.qV(de.EE.xK).q(DirectEncodedMemoryArea.getThumb2(7, 1, 1)).RF(DirectEncodedMemoryArea.get(8, 4));
   private static final Bf Ri = Bf.q(DirectEncodedMemoryArea.get(0, 12), Bf.RF);
   private static final Bf GY = Bf.q(DirectEncodedMemoryArea.get(0, 12));
   public static final Je[] q = new Je[]{
      new Qg("STR", Pc.nf, GY), new Qg("STRB", Pc.nf, GY), new Qg("LDR", Pc.nf, Ri).q(QI.io).q(YN), new Qg("LDRB", Pc.nf, Ri).nf().q(QI.io)
   };
   public static final Je[] RF = new Je[]{
      new Qg("STRT", Pc.nf, GY),
      new Qg("STRBT", Pc.nf, GY),
      new Qg("LDRT", Pc.nf, Ri).nf().q(QI.io).q(QI.HF),
      new Qg("LDRBT", Pc.nf, Ri).nf().q(QI.io).q(QI.HF)
   };
   public static final Je xK = new Qg("POP", Pc.za).q(YN);
   public static final Je Dw = new Qg("PUSH", Pc.za);
   private static final Bf Wx = Bf.q(
      Pc.lm, VirtualEncodedMemoryArea._1, DirectEncodedMemoryArea.get(23, 1), VirtualEncodedMemoryArea._0, DirectEncodedMemoryArea.get(0, 12), Bf.RF
   );
   public static final Je Uv = new Qg("PLD", Wx);
   public static final Je oW = new Qg("PLDW", Wx);
   public static final Je gO = new Qg("PLI", Wx);
   private static final Bf AB = Bf.q(Pc.Dw, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5), Bf.RF);
   private static final Bf CY = Bf.q(Pc.Dw, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5));
   public static final Je[] nf = new Je[]{
      new Qg("STR", Pc.nf, CY), new Qg("STRB", Pc.nf, CY), new Qg("LDR", Pc.nf, AB).q(QI.io).q(QI.qa).q(YN), new Qg("LDRB", Pc.nf, AB).nf().q(QI.io).q(QI.qa)
   };
   public static final Je[] gP = new Je[]{
      new Qg("STRT", Pc.nf, CY),
      new Qg("STRBT", Pc.nf, CY),
      new Qg("LDRT", Pc.nf, AB).nf().q(QI.io).q(QI.HF).q(QI.qa),
      new Qg("LDRBT", Pc.nf, AB).nf().q(QI.io).q(QI.HF).q(QI.qa)
   };
   private static final Bf WI = new Bf(
      Pc.lm,
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      VirtualEncodedMemoryArea._0,
      Pc.Dw,
      DirectEncodedMemoryArea.get(5, 2),
      DirectEncodedMemoryArea.get(7, 5),
      VirtualEncodedMemoryArea._0,
      Bf.RF
   );
   public static final Je za = new Qg("PLD", WI);
   public static final Je lm = new Qg("PLDW", WI);
   public static final Je zz = new Qg("PLI", WI);
   private static final Bf Tq = Bf.q(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 4), DirectEncodedMemoryArea.get(0, 4)), Bf.RF);
   private static final Bf Yp = Bf.q(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 4), DirectEncodedMemoryArea.get(0, 4)));
   public static final Je[] JY = new Je[]{
      null,
      new Qg("STRH", Pc.nf, Yp),
      new Qg("LDRD", Pc.nf, Pc.gP, Tq).q(QI.Me).q(QI.io).q(QI.xK),
      new Qg("STRD", Pc.nf, Pc.gP, Yp),
      null,
      new Qg("LDRH", Pc.nf, Tq).nf().q(QI.io),
      new Qg("LDRSB", Pc.nf, Tq).nf().q(QI.io),
      new Qg("LDRSH", Pc.nf, Tq).nf().q(QI.io)
   };
   public static final Je[] HF = new Je[]{
      null,
      new Qg("STRHT", Pc.nf, Yp),
      new Qg("LDRD UNPREDICTABLE", Pc.nf, Pc.gP, Tq).q("P=0 && W=1"),
      new Qg("STRD UNPREDICTABLE", Pc.nf, Pc.gP, Yp).q("P=0 && W=1"),
      null,
      new Qg("LDRHT", Pc.nf, Tq).nf().q(QI.io).q(QI.HF),
      new Qg("LDRSBT", Pc.nf, Tq).nf().q(QI.io).q(QI.HF),
      new Qg("LDRSHT", Pc.nf, Tq).nf().q(QI.io).q(QI.HF)
   };
   private static final Bf Gu = Bf.q(Pc.Dw, null, null, Bf.RF);
   private static final Bf nY = Bf.q(Pc.Dw, null, null);
   public static final Je[] LK = new Je[]{
      null,
      new Qg("STRH", Pc.nf, nY),
      new Qg("LDRD", Pc.nf, Pc.gP, Gu).q(QI.Me).q(QI.io).q(QI.xK).q(QI.qa).q(QI.Hk),
      new Qg("STRD", Pc.nf, Pc.gP, nY),
      null,
      new Qg("LDRH", Pc.nf, Gu).nf().q(QI.io).q(QI.qa),
      new Qg("LDRSB", Pc.nf, Gu).nf().q(QI.io).q(QI.qa),
      new Qg("LDRSH", Pc.nf, Gu).nf().q(QI.io).q(QI.qa)
   };
   public static final Je[] io = new Je[]{
      null,
      new Qg("STRHT", Pc.nf, nY),
      new Qg("LDRD UNPREDICTABLE", Pc.nf, Pc.gP, Gu).q("P=0 && W=1"),
      new Qg("STRD UNPREDICTABLE", Pc.nf, Pc.gP, nY).q("P=0 && W=1"),
      null,
      new Qg("LDRHT", Pc.nf, Gu).nf().q(QI.io).q(QI.HF).q(QI.qa),
      new Qg("LDRSBT", Pc.nf, Gu).nf().q(QI.io).q(QI.HF).q(QI.qa),
      new Qg("LDRSHT", Pc.nf, Gu).nf().q(QI.io).q(QI.HF).q(QI.qa)
   };
   private static final Bf lF = Bf.q(Pc.lm, Pc.Dw, VirtualEncodedMemoryArea._00, DirectEncodedMemoryArea.get(4, 2));
   public static final Je[] qa = new Je[]{
      new Qg("STRB", Pc.nf, lF).q(Rv),
      new Qg("LDRB", Pc.nf, lF).q(Rv).q(QI.qa),
      new Qg("STRH", Pc.nf, lF).q(Rv),
      new Qg("LDRH", Pc.nf, lF).q(Rv).q(QI.qa),
      new Qg("STR", Pc.nf, lF).q(Rv),
      new Qg("LDR", Pc.nf, lF).q(Rv).q(QI.qa).q(YN),
      null,
      null,
      null,
      new Qg("LDRSB", Pc.nf, lF).q(Rv).q(QI.qa),
      null,
      new Qg("LDRSH", Pc.nf, lF).q(Rv).q(QI.qa)
   };
   public static final Je Hk = new Qg("PLD", lF);
   public static final Je Me = new Qg("PLDW", lF);
   public static final Je PV = new Qg("PLI", lF);
   private static final Bf nq = Bf.q(
      Pc.lm, DirectEncodedMemoryArea.get(10, 1), DirectEncodedMemoryArea.get(9, 1), DirectEncodedMemoryArea.get(8, 1), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final Je[] oQ = new Je[]{
      new Qg("STRB", Pc.nf, nq),
      new Qg("LDRB", Pc.nf, nq).nf().q(QI.io),
      new Qg("STRH", Pc.nf, nq),
      new Qg("LDRH", Pc.nf, nq).nf().q(QI.io),
      new Qg("STR", Pc.nf, nq),
      new Qg("LDR", Pc.nf, nq).q(YN).q(QI.io),
      null,
      null,
      null,
      new Qg("LDRSB", Pc.nf, nq).nf().q(QI.io),
      null,
      new Qg("LDRSH", Pc.nf, nq).nf().q(QI.io)
   };
   public static final Je[] xW = new Je[]{
      new Qg("STRBT", Pc.nf, nq),
      new Qg("LDRBT", Pc.nf, nq).nf(),
      new Qg("STRHT", Pc.nf, nq),
      new Qg("LDRHT", Pc.nf, nq).nf(),
      new Qg("STRT", Pc.nf, nq),
      new Qg("LDRT", Pc.nf, nq).nf(),
      null,
      null,
      null,
      new Qg("LDRSBT", Pc.nf, nq).nf(),
      null,
      new Qg("LDRSHT", Pc.nf, nq).nf()
   };
   public static final Je KT = new Qg("PLD", nq);
   public static final Je Gf = new Qg("PLDW", nq);
   public static final Je Ef = new Qg("PLI", nq);
   private static final Bf NX = Bf.q(Pc.lm, DirectEncodedMemoryArea.get(0, 12));
   public static final Je[] cC = new Je[]{
      new Qg("STRB", Pc.nf, NX).q(zx),
      new Qg("LDRB", Pc.nf, NX).q(zx),
      new Qg("STRH", Pc.nf, NX).q(zx),
      new Qg("LDRH", Pc.nf, NX).q(zx),
      new Qg("STR", Pc.nf, NX).q(zx),
      new Qg("LDR", Pc.nf, NX).q(zx).q(YN),
      null,
      null,
      null,
      new Qg("LDRSB", Pc.nf, NX).q(zx),
      null,
      new Qg("LDRSH", Pc.nf, NX).q(zx)
   };
   private static final Bf br = Bf.q(
      Pc.lm, VirtualEncodedMemoryArea._1, DirectEncodedMemoryArea.get(23, 1), VirtualEncodedMemoryArea._0, DirectEncodedMemoryArea.get(0, 12), Bf.RF
   );
   public static final Je[] sH = new Je[]{
      new Qg("LDRB", Pc.nf, br),
      new Qg("LDRH", Pc.nf, br),
      new Qg("LDR", Pc.nf, br).q(YN).q(ZT),
      null,
      new Qg("LDRSB", Pc.nf, br),
      new Qg("LDRSH", Pc.nf, br),
      null,
      null
   };
   public static final Je CE = new Qg("PLD", br);
   public static final Je wF = new Qg("PLDW", br);
   public static final Je If = new Qg("PLI", br);
   public static final Je[] Dz = new Je[]{new Qg("STRD", Pc.nf, Pc.gO, Bf.oW), new Qg("LDRD", Pc.nf, Pc.gO, Bf.gO).q(QI.io).q(QI.Dw).q(QI.lm)};
   public static final Je mI = new Qg("LDRD", Pc.nf, Pc.gO, Bf.gO).q(QI.Dw).q(QI.lm);
   private static final Bf tW = Bf.q(Pc.PV, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2), Bf.RF);
   public static final Je jq = new Qg("LDR", iv.gO, tW);
   private static final Bf ZA = Bf.q(iv.Uv, iv.oW);
   public static final Je[] ui = new Je[]{
      new Qg("STR", iv.Dw, ZA),
      new Qg("STRH", iv.Dw, ZA),
      new Qg("STRB", iv.Dw, ZA),
      new Qg("LDRSB", iv.Dw, ZA),
      new Qg("LDR", iv.Dw, ZA),
      new Qg("LDRH", iv.Dw, ZA),
      new Qg("LDRB", iv.Dw, ZA),
      new Qg("LDRSH", iv.Dw, ZA)
   };
   private static final Bf Ov = Bf.q(iv.Uv, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(6, 5), 2));
   private static final Bf Lj = Bf.q(iv.Uv, DirectEncodedMemoryArea.get(6, 5));
   public static final Je[] TX = new Je[]{new Qg("STR", iv.Dw, Ov), new Qg("LDR", iv.Dw, Ov), new Qg("STRB", iv.Dw, Lj), new Qg("LDRB", iv.Dw, Lj)};
   private static final Bf nv = Bf.q(iv.Uv, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(6, 5), 1));
   private static final Bf LL = Bf.q(Pc.Hk, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2));
   public static final Je[] Rr = new Je[]{new Qg("STRH", iv.Dw, nv), new Qg("LDRH", iv.Dw, nv), new Qg("STR", iv.gO, LL), new Qg("LDR", iv.gO, LL)};
   public static final Ef EB = Bf.q(Pc.lm);
   public static final Je[][] Xo = new Je[][]{
      {
            new Qg("STL", Pc.Dw, EB).RF(12, 4),
            null,
            new Qg("STLEX", Pc.nf, Pc.Dw, EB),
            new Qg("STREX", Pc.nf, Pc.Dw, EB),
            new Qg("LDA", Pc.nf, EB).nf().q(QI.HF).RF(0, 4),
            null,
            new Qg("LDAEX", Pc.nf, EB).nf().q(QI.HF).RF(0, 4),
            new Qg("LDREX", Pc.nf, EB).nf().q(QI.HF).RF(0, 4)
      },
      {
            null,
            null,
            new Qg("STLEXD", Pc.nf, Pc.Dw, Pc.Uv, EB).q(QI.Uv, QI.HF, QI.lm, QI.zz, new x(4, 12, 16), QI.za),
            new Qg("STREXD", Pc.nf, Pc.Dw, Pc.Uv, EB).q(QI.Uv, QI.HF, QI.lm, QI.zz, new x(4, 12, 16), QI.za),
            null,
            null,
            new Qg("LDAEXD", Pc.nf, Pc.gP, EB).q(QI.xK, QI.HF, QI.gP).RF(0, 4),
            new Qg("LDREXD", Pc.nf, Pc.gP, EB).q(QI.xK, QI.HF, QI.gP).RF(0, 4)
      },
      {
            new Qg("STLB", Pc.Dw, EB).RF(12, 4),
            null,
            new Qg("STLEXB", Pc.nf, Pc.Dw, EB),
            new Qg("STREXB", Pc.nf, Pc.Dw, EB),
            new Qg("LDAB", Pc.nf, EB).nf().q(QI.HF).RF(0, 4),
            null,
            new Qg("LDAEXB", Pc.nf, EB).nf().q(QI.HF).RF(0, 4),
            new Qg("LDREXB", Pc.nf, EB).nf().q(QI.HF).RF(0, 4)
      },
      {
            new Qg("STLH", Pc.Dw, EB).RF(12, 4),
            null,
            new Qg("STLEXH", Pc.nf, Pc.Dw, EB),
            new Qg("STREXH", Pc.nf, Pc.Dw, EB),
            new Qg("LDAH", Pc.nf, EB).nf().q(QI.HF).RF(0, 4),
            null,
            new Qg("LDAEXH", Pc.nf, EB).nf().q(QI.HF).RF(0, 4),
            new Qg("LDREXH", Pc.nf, EB).nf().q(QI.HF).RF(0, 4)
      }
   };
   public static final Je[] Bu = new Je[]{
      new Qg("STLB", Pc.nf, EB),
      new Qg("STLH", Pc.nf, EB),
      new Qg("STL", Pc.nf, EB),
      null,
      new Qg("LDAB", Pc.nf, EB).nf().q(QI.HF),
      new Qg("LDAH", Pc.nf, EB).nf().q(QI.HF),
      new Qg("LDA", Pc.nf, EB).nf().q(QI.HF),
      null,
      new Qg("STLEXB", Pc.Dw, Pc.nf, EB),
      new Qg("STLEXH", Pc.Dw, Pc.nf, EB),
      new Qg("STLEX", Pc.Dw, Pc.nf, EB),
      new Qg("STLEXD", Pc.Dw, Pc.nf, Pc.gO, EB),
      new Qg("LDAEXB", Pc.nf, EB).nf().q(QI.HF),
      new Qg("LDAEXH", Pc.nf, EB).nf().q(QI.HF),
      new Qg("LDAEX", Pc.nf, EB).nf().q(QI.HF),
      new Qg("LDAEXD", Pc.nf, Pc.gO, EB).q(QI.Dw).q(QI.HF).q(QI.lm)
   };
   private static final Ef PQ = Bf.q(Pc.lm, DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final Je[] IN = new Je[]{new Qg("STREX", Pc.gO, Pc.nf, PQ), new Qg("LDREX", Pc.nf, PQ).nf().q(QI.HF)};
   public static final Je[] rL = new Je[]{
      new Qg("STREXB", Pc.Dw, Pc.nf, EB),
      new Qg("STREXH", Pc.Dw, Pc.nf, EB),
      null,
      new Qg("STREXD", Pc.Dw, Pc.nf, Pc.gO, EB),
      new Qg("LDREXB", Pc.nf, EB).nf().q(QI.HF),
      new Qg("LDREXH", Pc.nf, EB).nf().q(QI.HF),
      null,
      new Qg("LDREXD", Pc.nf, Pc.gO, EB).q(QI.Dw).q(QI.HF).q(QI.lm)
   };
   public static final Je[] eJ = new Je[]{new Qg("SWP", Pc.nf, Pc.Dw, EB).q(QI.Dw).q(QI.HF), new Qg("SWPB", Pc.nf, Pc.Dw, EB).q(QI.Dw).q(QI.HF)};
}
