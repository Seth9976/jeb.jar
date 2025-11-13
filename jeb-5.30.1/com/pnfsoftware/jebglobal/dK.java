package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

public class dK {
   public static final lH q = OQ.q(257);
   public static final lH RF = OQ.q(65793);
   public static final lH xK = OQ.q(1);
   private static final Ef zz = Ne.q(2097152, DirectEncodedMemoryArea.get(0, 26).shift(2));
   public static final Je Dw = new Qg("B", q, zz);
   public static final Je Uv = new Qg("BL", RF, zz);
   private static final dD JY = new dD(DirectEncodedMemoryArea.get(0, 5), dD.xK);
   private static final dD HF = new dD(DirectEncodedMemoryArea.get(0, 5), dD.RF);
   private static final dD LK = new dD(DirectEncodedMemoryArea.get(5, 5), dD.RF);
   public static final Je[][] oW = new Je[][]{
      {new Qg("BR", q, YH.nf).q(JY), null, new Qg("BRAAZ", q, YH.nf).q(QJ.IY, HF), new Qg("BRABZ", q, YH.nf).q(QJ.IY, HF)},
      {new Qg("BLR", RF, YH.nf).q(JY), null, new Qg("BLRAAZ", RF, YH.nf).q(QJ.IY, HF), new Qg("BLRABZ", RF, YH.nf).q(QJ.IY, HF)},
      {new Qg("RET", q, YH.gP).q(JY), null, new Qg("RETAA", q).q(QJ.IY, HF, LK), new Qg("RETAB", q).q(QJ.IY, HF, LK)},
      null,
      {new Qg("ERET", xK).q(LK, JY), null, new Qg("ERETAA", xK).q(QJ.IY, HF, LK), new Qg("ERETAB", xK).q(QJ.IY, HF, LK)},
      {new Qg("DRPS", xK).q(LK, JY)},
      null,
      null,
      {null, null, new Qg("BRAA", q, YH.nf, YH.io).q(QJ.IY), new Qg("BRAB", q, YH.nf, YH.io).q(QJ.IY)},
      {null, null, new Qg("BLRAA", RF, YH.nf, YH.io).q(QJ.IY), new Qg("BLRAB", RF, YH.nf, YH.io).q(QJ.IY)}
   };
   private static final Ef io = Ne.q(2097152, DirectEncodedMemoryArea.get(5, 19).shift(2));
   public static final Je gO = new Qg("B", q, io).q(new Qg.eo(DirectEncodedMemoryArea.get(0, 4), 33554432));
   public static final Je nf = new Qg("CBNZ", q, YH.Dz, io).q(new Qg.eo(2097152));
   public static final Je gP = new Qg("CBZ", q, YH.Dz, io).q(new Qg.eo(1048576));
   private static final Ef qa = Ne.q(2097152, DirectEncodedMemoryArea.get(5, 14).shift(2));
   private static final Ef Hk = Ne.q(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(31, 1), DirectEncodedMemoryArea.get(19, 5)));
   public static final Je za = new Qg("TBNZ", q, YH.Dz, Hk, qa).q(new Qg.eo(4194304));
   public static final Je lm = new Qg("TBZ", q, YH.Dz, Hk, qa).q(new Qg.eo(3145728));
}
