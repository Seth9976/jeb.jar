package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

public class fv {
   private static final String[] ld = new String[]{"p0", "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10", "p11", "p12", "p13", "p14", "p15"};
   public static final Ag pC = new Ag(ld, DirectEncodedMemoryArea.get(8, 4));
   public static final Hu A = new IV(DirectEncodedMemoryArea.get(21, 3));
   public static final Hu kS = new IV(268435456, DirectEncodedMemoryArea.get(5, 3));
   public static final tz[] wS = new tz[]{
      new UC("MCR", pC, A, wT.UT, wT.NS, wT.oT, kS),
      new UC("MRC", pC, A, wT.gp, wT.NS, wT.oT, kS),
      new UC("MCR2", pC, A, wT.UT, wT.NS, wT.oT, kS),
      new UC("MRC2", pC, A, wT.gp, wT.NS, wT.oT, kS)
   };
   public static final tz[] UT = new tz[]{
      new UC("MCRR", pC, IV.E, wT.UT, wT.ys, wT.oT),
      new UC("MRRC", pC, IV.E, wT.UT, wT.ys, wT.oT),
      new UC("MCRR2", pC, IV.E, wT.UT, wT.ys, wT.oT),
      new UC("MRRC2", pC, IV.E, wT.UT, wT.ys, wT.oT)
   };
   private static final LF gp = LF.pC(EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2), LF.A);
   private static final LF oT = LF.pC(EncodedMemoryAreaList.shift(DirectEncodedMemoryArea.get(0, 8), 2), LF.pC, LF.A);
   public static final tz[] E = new tz[]{
      new UC("STC", pC, wT.WR, gp),
      new UC("STCL", pC, wT.WR, gp),
      new UC("LDC", pC, wT.WR, oT),
      new UC("LDCL", pC, wT.WR, oT),
      new UC("STC2", pC, wT.WR, gp),
      new UC("STC2L", pC, wT.WR, gp),
      new UC("LDC2", pC, wT.WR, oT),
      new UC("LDC2L", pC, wT.WR, oT)
   };
   public static final tz sY = new UC(
      "CDP", pC, new IV(DirectEncodedMemoryArea.get(20, 4)), wT.WR, wT.NS, wT.oT, new IV(268435456, DirectEncodedMemoryArea.get(5, 3))
   );
   public static final tz ys = new UC(
      "CDP2", pC, new IV(DirectEncodedMemoryArea.get(20, 4)), wT.WR, wT.NS, wT.oT, new IV(268435456, DirectEncodedMemoryArea.get(5, 3))
   );

   public static int pC(byte[] var0) {
      return ZC.UT(var0, 1) | ZC.wS(var0, 0);
   }

   public static int A(byte[] var0) {
      return ZC.UT(var0, 2) | ZC.wS(var0, 1) | (var0[1] & 64) >>> 6;
   }

   public static int kS(byte[] var0) {
      return ZC.wS(var0, 1) | (var0[1] & 64) >>> 6;
   }
}
