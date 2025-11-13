package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

public class ZH {
   private static final String[] gP = new String[]{"p0", "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10", "p11", "p12", "p13", "p14", "p15"};
   public static final wJ q = new wJ(gP, DirectEncodedMemoryArea.get(8, 4));
   public static final Ef RF = new go(DirectEncodedMemoryArea.get(21, 3));
   public static final Ef xK = new go(268435456, DirectEncodedMemoryArea.get(5, 3));
   public static final Je[] Dw = new Je[]{
      new Qg("MCR", q, RF, Pc.nf, Pc.qa, Pc.HF, xK),
      new Qg("MRC", q, RF, Pc.JY, Pc.qa, Pc.HF, xK),
      new Qg("MCR2", q, RF, Pc.nf, Pc.qa, Pc.HF, xK),
      new Qg("MRC2", q, RF, Pc.JY, Pc.qa, Pc.HF, xK)
   };
   public static final Je[] Uv = new Je[]{
      new Qg("MCRR", q, go.JY, Pc.nf, Pc.lm, Pc.HF),
      new Qg("MRRC", q, go.JY, Pc.nf, Pc.lm, Pc.HF),
      new Qg("MCRR2", q, go.JY, Pc.nf, Pc.lm, Pc.HF),
      new Qg("MRRC2", q, go.JY, Pc.nf, Pc.lm, Pc.HF)
   };
   private static final Bf za = Bf.q(EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2), Bf.xK);
   private static final Bf lm = Bf.q(EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2), Bf.RF, Bf.xK);
   public static final Je[] oW = new Je[]{
      new Qg("STC", q, Pc.io, za),
      new Qg("STCL", q, Pc.io, za),
      new Qg("LDC", q, Pc.io, lm),
      new Qg("LDCL", q, Pc.io, lm),
      new Qg("STC2", q, Pc.io, za),
      new Qg("STC2L", q, Pc.io, za),
      new Qg("LDC2", q, Pc.io, lm),
      new Qg("LDC2L", q, Pc.io, lm)
   };
   public static final Je gO = new Qg(
      "CDP", q, new go(DirectEncodedMemoryArea.get(20, 4)), Pc.io, Pc.qa, Pc.HF, new go(268435456, DirectEncodedMemoryArea.get(5, 3))
   );
   public static final Je nf = new Qg(
      "CDP2", q, new go(DirectEncodedMemoryArea.get(20, 4)), Pc.io, Pc.qa, Pc.HF, new go(268435456, DirectEncodedMemoryArea.get(5, 3))
   );

   public static int q(byte[] var0) {
      return HS.Uv(var0, 1) | HS.Dw(var0, 0);
   }

   public static int RF(byte[] var0) {
      return HS.Uv(var0, 2) | HS.Dw(var0, 1) | (var0[1] & 64) >>> 6;
   }

   public static int xK(byte[] var0) {
      return HS.Dw(var0, 1) | (var0[1] & 64) >>> 6;
   }
}
