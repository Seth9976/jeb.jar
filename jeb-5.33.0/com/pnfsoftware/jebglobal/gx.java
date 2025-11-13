package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class gx {
   private static final hg pF = hg.pC(525570);
   private static final ji Bc = new ji.rQ(ji.DH.pC, ji.DH.kS, ji.DH.wS).A(DirectEncodedMemoryArea.get(4, 2));
   private static final ji OI = new ji.rQ(ji.DH.kS, ji.DH.wS).A(DirectEncodedMemoryArea.get(5, 7));
   private static final ji Bf = new ji.rQ(ji.DH.kS).pC(DirectEncodedMemoryArea.getThumb2(7, 1, 1)).A(DirectEncodedMemoryArea.get(8, 4));
   private static final LF Pe = LF.pC(DirectEncodedMemoryArea.get(0, 12), LF.pC);
   private static final LF ck = LF.pC(DirectEncodedMemoryArea.get(0, 12));
   public static final tz[] pC = new tz[]{
      new UC("STR", wT.UT, ck), new UC("STRB", wT.UT, ck), new UC("LDR", wT.UT, Pe).pC(cT.xC).pC(pF), new UC("LDRB", wT.UT, Pe).ys().pC(cT.xC)
   };
   public static final tz[] A = new tz[]{
      new UC("STRT", wT.UT, ck),
      new UC("STRBT", wT.UT, ck),
      new UC("LDRT", wT.UT, Pe).ys().pC(cT.xC).pC(cT.NS),
      new UC("LDRBT", wT.UT, Pe).ys().pC(cT.xC).pC(cT.NS)
   };
   public static final tz kS = new UC("POP", wT.sY).pC(pF);
   public static final tz wS = new UC("PUSH", wT.sY);
   private static final LF RW = LF.pC(
      wT.ys, VirtualEncodedMemoryArea._1, DirectEncodedMemoryArea.get(23, 1), VirtualEncodedMemoryArea._0, DirectEncodedMemoryArea.get(0, 12), LF.pC
   );
   public static final tz UT = new UC("PLD", RW);
   public static final tz E = new UC("PLDW", RW);
   public static final tz sY = new UC("PLI", RW);
   private static final LF e = LF.pC(wT.pC, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5), LF.pC);
   private static final LF xM = LF.pC(wT.pC, DirectEncodedMemoryArea.get(5, 2), DirectEncodedMemoryArea.get(7, 5));
   public static final tz[] ys = new tz[]{
      new UC("STR", wT.UT, xM),
      new UC("STRB", wT.UT, xM),
      new UC("LDR", wT.UT, e).pC(cT.xC).pC(cT.ED).pC(pF),
      new UC("LDRB", wT.UT, e).ys().pC(cT.xC).pC(cT.ED)
   };
   public static final tz[] ld = new tz[]{
      new UC("STRT", wT.UT, xM),
      new UC("STRBT", wT.UT, xM),
      new UC("LDRT", wT.UT, e).ys().pC(cT.xC).pC(cT.NS).pC(cT.ED),
      new UC("LDRBT", wT.UT, e).ys().pC(cT.xC).pC(cT.NS).pC(cT.ED)
   };
   private static final LF kU = new LF(
      wT.ys,
      VirtualEncodedMemoryArea._1,
      DirectEncodedMemoryArea.get(23, 1),
      VirtualEncodedMemoryArea._0,
      wT.pC,
      DirectEncodedMemoryArea.get(5, 2),
      DirectEncodedMemoryArea.get(7, 5),
      VirtualEncodedMemoryArea._0,
      LF.pC
   );
   public static final tz gp = new UC("PLD", kU);
   public static final tz oT = new UC("PLDW", kU);
   public static final tz fI = new UC("PLI", kU);
   private static final LF Kq = LF.pC(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 4), DirectEncodedMemoryArea.get(0, 4)), LF.pC);
   private static final LF go = LF.pC(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(8, 4), DirectEncodedMemoryArea.get(0, 4)));
   public static final tz[] WR = new tz[]{
      null,
      new UC("STRH", wT.UT, go),
      new UC("LDRD", wT.UT, wT.E, Kq).pC(cT.ah).pC(cT.xC).pC(cT.kS),
      new UC("STRD", wT.UT, wT.E, go),
      null,
      new UC("LDRH", wT.UT, Kq).ys().pC(cT.xC),
      new UC("LDRSB", wT.UT, Kq).ys().pC(cT.xC),
      new UC("LDRSH", wT.UT, Kq).ys().pC(cT.xC)
   };
   public static final tz[] NS = new tz[]{
      null,
      new UC("STRHT", wT.UT, go),
      new UC("LDRD UNPREDICTABLE", wT.UT, wT.E, Kq).pC("P=0 && W=1"),
      new UC("STRD UNPREDICTABLE", wT.UT, wT.E, go).pC("P=0 && W=1"),
      null,
      new UC("LDRHT", wT.UT, Kq).ys().pC(cT.xC).pC(cT.NS),
      new UC("LDRSBT", wT.UT, Kq).ys().pC(cT.xC).pC(cT.NS),
      new UC("LDRSHT", wT.UT, Kq).ys().pC(cT.xC).pC(cT.NS)
   };
   private static final LF JF = LF.pC(wT.pC, null, null, LF.pC);
   private static final LF Nq = LF.pC(wT.pC, null, null);
   public static final tz[] vP = new tz[]{
      null,
      new UC("STRH", wT.UT, Nq),
      new UC("LDRD", wT.UT, wT.E, JF).pC(cT.ah).pC(cT.xC).pC(cT.kS).pC(cT.ED).pC(cT.Sc),
      new UC("STRD", wT.UT, wT.E, Nq),
      null,
      new UC("LDRH", wT.UT, JF).ys().pC(cT.xC).pC(cT.ED),
      new UC("LDRSB", wT.UT, JF).ys().pC(cT.xC).pC(cT.ED),
      new UC("LDRSH", wT.UT, JF).ys().pC(cT.xC).pC(cT.ED)
   };
   public static final tz[] xC = new tz[]{
      null,
      new UC("STRHT", wT.UT, Nq),
      new UC("LDRD UNPREDICTABLE", wT.UT, wT.E, JF).pC("P=0 && W=1"),
      new UC("STRD UNPREDICTABLE", wT.UT, wT.E, Nq).pC("P=0 && W=1"),
      null,
      new UC("LDRHT", wT.UT, JF).ys().pC(cT.xC).pC(cT.NS).pC(cT.ED),
      new UC("LDRSBT", wT.UT, JF).ys().pC(cT.xC).pC(cT.NS).pC(cT.ED),
      new UC("LDRSHT", wT.UT, JF).ys().pC(cT.xC).pC(cT.NS).pC(cT.ED)
   };
   private static final LF pg = LF.pC(wT.ys, wT.pC, VirtualEncodedMemoryArea._00, DirectEncodedMemoryArea.get(4, 2));
   public static final tz[] ED = new tz[]{
      new UC("STRB", wT.UT, pg).pC(Bc),
      new UC("LDRB", wT.UT, pg).pC(Bc).pC(cT.ED),
      new UC("STRH", wT.UT, pg).pC(Bc),
      new UC("LDRH", wT.UT, pg).pC(Bc).pC(cT.ED),
      new UC("STR", wT.UT, pg).pC(Bc),
      new UC("LDR", wT.UT, pg).pC(Bc).pC(cT.ED).pC(pF),
      null,
      null,
      null,
      new UC("LDRSB", wT.UT, pg).pC(Bc).pC(cT.ED),
      null,
      new UC("LDRSH", wT.UT, pg).pC(Bc).pC(cT.ED)
   };
   public static final tz Sc = new UC("PLD", pg);
   public static final tz ah = new UC("PLDW", pg);
   public static final tz eP = new UC("PLI", pg);
   private static final LF gj = LF.pC(
      wT.ys, DirectEncodedMemoryArea.get(10, 1), DirectEncodedMemoryArea.get(9, 1), DirectEncodedMemoryArea.get(8, 1), DirectEncodedMemoryArea.get(0, 8)
   );
   public static final tz[] UO = new tz[]{
      new UC("STRB", wT.UT, gj),
      new UC("LDRB", wT.UT, gj).ys().pC(cT.xC),
      new UC("STRH", wT.UT, gj),
      new UC("LDRH", wT.UT, gj).ys().pC(cT.xC),
      new UC("STR", wT.UT, gj),
      new UC("LDR", wT.UT, gj).pC(pF).pC(cT.xC),
      null,
      null,
      null,
      new UC("LDRSB", wT.UT, gj).ys().pC(cT.xC),
      null,
      new UC("LDRSH", wT.UT, gj).ys().pC(cT.xC)
   };
   public static final tz[] Ab = new tz[]{
      new UC("STRBT", wT.UT, gj),
      new UC("LDRBT", wT.UT, gj).ys(),
      new UC("STRHT", wT.UT, gj),
      new UC("LDRHT", wT.UT, gj).ys(),
      new UC("STRT", wT.UT, gj),
      new UC("LDRT", wT.UT, gj).ys(),
      null,
      null,
      null,
      new UC("LDRSBT", wT.UT, gj).ys(),
      null,
      new UC("LDRSHT", wT.UT, gj).ys()
   };
   public static final tz rl = new UC("PLD", gj);
   public static final tz z = new UC("PLDW", gj);
   public static final tz Ek = new UC("PLI", gj);
   private static final LF ZD = LF.pC(wT.ys, DirectEncodedMemoryArea.get(0, 12));
   public static final tz[] hK = new tz[]{
      new UC("STRB", wT.UT, ZD).pC(OI),
      new UC("LDRB", wT.UT, ZD).pC(OI),
      new UC("STRH", wT.UT, ZD).pC(OI),
      new UC("LDRH", wT.UT, ZD).pC(OI),
      new UC("STR", wT.UT, ZD).pC(OI),
      new UC("LDR", wT.UT, ZD).pC(OI).pC(pF),
      null,
      null,
      null,
      new UC("LDRSB", wT.UT, ZD).pC(OI),
      null,
      new UC("LDRSH", wT.UT, ZD).pC(OI)
   };
   private static final LF DL = LF.pC(
      wT.ys, VirtualEncodedMemoryArea._1, DirectEncodedMemoryArea.get(23, 1), VirtualEncodedMemoryArea._0, DirectEncodedMemoryArea.get(0, 12), LF.pC
   );
   public static final tz[] Er = new tz[]{
      new UC("LDRB", wT.UT, DL),
      new UC("LDRH", wT.UT, DL),
      new UC("LDR", wT.UT, DL).pC(pF).pC(Bf),
      null,
      new UC("LDRSB", wT.UT, DL),
      new UC("LDRSH", wT.UT, DL),
      null,
      null
   };
   public static final tz FE = new UC("PLD", DL);
   public static final tz Aj = new UC("PLDW", DL);
   public static final tz EX = new UC("PLI", DL);
   public static final tz[] LM = new tz[]{new UC("STRD", wT.UT, wT.wS, LF.UT), new UC("LDRD", wT.UT, wT.wS, LF.E).pC(cT.xC).pC(cT.wS).pC(cT.oT)};
   public static final tz mv = new UC("LDRD", wT.UT, wT.wS, LF.E).pC(cT.wS).pC(cT.oT);
   private static final LF UH = LF.pC(wT.ED, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2), LF.pC);
   public static final tz sO = new UC("LDR", LY.wS, UH);
   private static final LF VD = LF.pC(LY.A, LY.kS);
   public static final tz[] os = new tz[]{
      new UC("STR", LY.pC, VD),
      new UC("STRH", LY.pC, VD),
      new UC("STRB", LY.pC, VD),
      new UC("LDRSB", LY.pC, VD),
      new UC("LDR", LY.pC, VD),
      new UC("LDRH", LY.pC, VD),
      new UC("LDRB", LY.pC, VD),
      new UC("LDRSH", LY.pC, VD)
   };
   private static final LF Xs = LF.pC(LY.A, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(6, 5), 2));
   private static final LF KV = LF.pC(LY.A, DirectEncodedMemoryArea.get(6, 5));
   public static final tz[] Cu = new tz[]{new UC("STR", LY.pC, Xs), new UC("LDR", LY.pC, Xs), new UC("STRB", LY.pC, KV), new UC("LDRB", LY.pC, KV)};
   private static final LF FK = LF.pC(LY.A, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(6, 5), 1));
   private static final LF Bi = LF.pC(wT.vP, EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2));
   public static final tz[] hZ = new tz[]{new UC("STRH", LY.pC, FK), new UC("LDRH", LY.pC, FK), new UC("STR", LY.wS, Bi), new UC("LDR", LY.wS, Bi)};
   public static final Hu UW = LF.pC(wT.ys);
   public static final tz[][] PR = new tz[][]{
      {
            new UC("STL", wT.pC, UW).A(12, 4),
            null,
            new UC("STLEX", wT.UT, wT.pC, UW),
            new UC("STREX", wT.UT, wT.pC, UW),
            new UC("LDA", wT.UT, UW).ys().pC(cT.NS).A(0, 4),
            null,
            new UC("LDAEX", wT.UT, UW).ys().pC(cT.NS).A(0, 4),
            new UC("LDREX", wT.UT, UW).ys().pC(cT.NS).A(0, 4)
      },
      {
            null,
            null,
            new UC("STLEXD", wT.UT, wT.pC, wT.A, UW).pC(cT.UT, cT.NS, cT.oT, cT.fI, new mM(4, 12, 16), cT.gp),
            new UC("STREXD", wT.UT, wT.pC, wT.A, UW).pC(cT.UT, cT.NS, cT.oT, cT.fI, new mM(4, 12, 16), cT.gp),
            null,
            null,
            new UC("LDAEXD", wT.UT, wT.E, UW).pC(cT.kS, cT.NS, cT.ld).A(0, 4),
            new UC("LDREXD", wT.UT, wT.E, UW).pC(cT.kS, cT.NS, cT.ld).A(0, 4)
      },
      {
            new UC("STLB", wT.pC, UW).A(12, 4),
            null,
            new UC("STLEXB", wT.UT, wT.pC, UW),
            new UC("STREXB", wT.UT, wT.pC, UW),
            new UC("LDAB", wT.UT, UW).ys().pC(cT.NS).A(0, 4),
            null,
            new UC("LDAEXB", wT.UT, UW).ys().pC(cT.NS).A(0, 4),
            new UC("LDREXB", wT.UT, UW).ys().pC(cT.NS).A(0, 4)
      },
      {
            new UC("STLH", wT.pC, UW).A(12, 4),
            null,
            new UC("STLEXH", wT.UT, wT.pC, UW),
            new UC("STREXH", wT.UT, wT.pC, UW),
            new UC("LDAH", wT.UT, UW).ys().pC(cT.NS).A(0, 4),
            null,
            new UC("LDAEXH", wT.UT, UW).ys().pC(cT.NS).A(0, 4),
            new UC("LDREXH", wT.UT, UW).ys().pC(cT.NS).A(0, 4)
      }
   };
   public static final tz[] cX = new tz[]{
      new UC("STLB", wT.UT, UW),
      new UC("STLH", wT.UT, UW),
      new UC("STL", wT.UT, UW),
      null,
      new UC("LDAB", wT.UT, UW).ys().pC(cT.NS),
      new UC("LDAH", wT.UT, UW).ys().pC(cT.NS),
      new UC("LDA", wT.UT, UW).ys().pC(cT.NS),
      null,
      new UC("STLEXB", wT.pC, wT.UT, UW),
      new UC("STLEXH", wT.pC, wT.UT, UW),
      new UC("STLEX", wT.pC, wT.UT, UW),
      new UC("STLEXD", wT.pC, wT.UT, wT.wS, UW),
      new UC("LDAEXB", wT.UT, UW).ys().pC(cT.NS),
      new UC("LDAEXH", wT.UT, UW).ys().pC(cT.NS),
      new UC("LDAEX", wT.UT, UW).ys().pC(cT.NS),
      new UC("LDAEXD", wT.UT, wT.wS, UW).pC(cT.wS).pC(cT.NS).pC(cT.oT)
   };
   private static final Hu wQ = LF.pC(wT.ys, DirectEncodedMemoryArea.get(0, 8).shift(2));
   public static final tz[] DQ = new tz[]{new UC("STREX", wT.wS, wT.UT, wQ), new UC("LDREX", wT.UT, wQ).ys().pC(cT.NS)};
   public static final tz[] ZN = new tz[]{
      new UC("STREXB", wT.pC, wT.UT, UW),
      new UC("STREXH", wT.pC, wT.UT, UW),
      null,
      new UC("STREXD", wT.pC, wT.UT, wT.wS, UW),
      new UC("LDREXB", wT.UT, UW).ys().pC(cT.NS),
      new UC("LDREXH", wT.UT, UW).ys().pC(cT.NS),
      null,
      new UC("LDREXD", wT.UT, wT.wS, UW).pC(cT.wS).pC(cT.NS).pC(cT.oT)
   };
   public static final tz[] OB = new tz[]{new UC("SWP", wT.UT, wT.pC, UW).pC(cT.wS).pC(cT.NS), new UC("SWPB", wT.UT, wT.pC, UW).pC(cT.wS).pC(cT.NS)};
}
