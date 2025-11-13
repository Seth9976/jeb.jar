package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;

public class zl {
   public static final dW pC = hg.pC(257);
   public static final dW A = hg.pC(65793);
   public static final dW kS = hg.pC(1);
   private static final Hu fI = aX.pC(2097152, DirectEncodedMemoryArea.get(0, 26).shift(2));
   public static final tz wS = new UC("B", pC, fI);
   public static final tz UT = new UC("BL", A, fI);
   private static final ZW WR = new ZW(DirectEncodedMemoryArea.get(0, 5), ZW.kS);
   private static final ZW NS = new ZW(DirectEncodedMemoryArea.get(0, 5), ZW.A);
   private static final ZW vP = new ZW(DirectEncodedMemoryArea.get(5, 5), ZW.A);
   public static final tz[][] E = new tz[][]{
      {new UC("BR", pC, sQ.UT).pC(WR), null, new UC("BRAAZ", pC, sQ.UT).pC(Le.rl, NS), new UC("BRABZ", pC, sQ.UT).pC(Le.rl, NS)},
      {new UC("BLR", A, sQ.UT).pC(WR), null, new UC("BLRAAZ", A, sQ.UT).pC(Le.rl, NS), new UC("BLRABZ", A, sQ.UT).pC(Le.rl, NS)},
      {new UC("RET", pC, sQ.E).pC(WR), null, new UC("RETAA", pC).pC(Le.rl, NS, vP), new UC("RETAB", pC).pC(Le.rl, NS, vP)},
      null,
      {new UC("ERET", kS).pC(vP, WR), null, new UC("ERETAA", kS).pC(Le.rl, NS, vP), new UC("ERETAB", kS).pC(Le.rl, NS, vP)},
      {new UC("DRPS", kS).pC(vP, WR)},
      null,
      null,
      {null, null, new UC("BRAA", pC, sQ.UT, sQ.WR).pC(Le.rl), new UC("BRAB", pC, sQ.UT, sQ.WR).pC(Le.rl)},
      {null, null, new UC("BLRAA", A, sQ.UT, sQ.WR).pC(Le.rl), new UC("BLRAB", A, sQ.UT, sQ.WR).pC(Le.rl)}
   };
   private static final Hu xC = aX.pC(2097152, DirectEncodedMemoryArea.get(5, 19).shift(2));
   public static final tz sY = new UC("B", pC, xC).pC(new UC.Av(DirectEncodedMemoryArea.get(0, 4), 33554432));
   public static final tz ys = new UC("CBNZ", pC, sQ.FE, xC).pC(new UC.Av(2097152));
   public static final tz ld = new UC("CBZ", pC, sQ.FE, xC).pC(new UC.Av(1048576));
   private static final Hu ED = aX.pC(2097152, DirectEncodedMemoryArea.get(5, 14).shift(2));
   private static final Hu Sc = aX.pC(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(31, 1), DirectEncodedMemoryArea.get(19, 5)));
   public static final tz gp = new UC("TBNZ", pC, sQ.FE, Sc, ED).pC(new UC.Av(4194304));
   public static final tz oT = new UC("TBZ", pC, sQ.FE, Sc, ED).pC(new UC.Av(3145728));
}
