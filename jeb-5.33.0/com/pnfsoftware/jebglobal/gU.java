package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class gU {
   private static final ji vP = new ji.rQ(ji.DH.pC, ji.DH.A);
   public static final tz[] pC = new tz[]{
      new UC("REV", wT.UT, wT.pC).pC(cT.wS),
      new UC("REV16", wT.UT, wT.pC).pC(cT.wS),
      new UC("RBIT", wT.UT, wT.pC).pC(cT.wS),
      new UC("REVSH", wT.UT, wT.pC).pC(cT.wS)
   };
   public static final tz[] A = new tz[]{
      new UC("REV", LY.pC, LY.A), new UC("REV16", LY.pC, LY.A), new UC("HLT", IV.UO).pC().pC(bb.E), new UC("REVSH", LY.pC, LY.A)
   };
   private static final jp xC = new PW(4, 0, 16);
   public static final tz[][] kS = new tz[][]{
      qf.wS,
      {
            new UC("REV", wT.wS, wT.pC).pC(cT.wS).pC(vP).pC(xC),
            new UC("REV16", wT.wS, wT.pC).pC(cT.wS).pC(vP).pC(xC),
            new UC("RBIT", wT.wS, wT.pC).pC(cT.wS).pC(xC),
            new UC("REVSH", wT.wS, wT.pC).pC(cT.wS).pC(vP).pC(xC)
      },
      {new UC("SEL", wT.wS, wT.ys, wT.pC).pC(cT.UT)},
      {new UC("CLZ", wT.wS, wT.pC).pC(cT.wS).pC(xC)},
      fA.A,
      fA.kS
   };
   public static final tz wS = new UC("MOVT", wT.UT, IV.pF).ys();
   private static final IEncodedMemoryArea ED = DirectEncodedMemoryArea.get(7, 5);
   private static final IV Sc = new IV(ED);
   private static final IV ah = new gU.Av(1048576, DirectEncodedMemoryArea.get(16, 5), ED);
   public static final tz UT = new UC("BFI", wT.UT, wT.pC, Sc, ah).ys();
   public static final tz E = new UC("BFC", wT.UT, Sc, ah).ys();
   public static final tz sY = new UC("SBFX", wT.UT, wT.pC, Sc, IV.xC).pC(cT.wS);
   public static final tz ys = new UC("UBFX", wT.UT, wT.pC, Sc, IV.xC).pC(cT.wS);
   public static final tz ld = new UC("USAD8", wT.ys, wT.pC, wT.wS).pC(cT.UT);
   public static final tz gp = new UC("USADA8", wT.ys, wT.pC, wT.wS, wT.UT);
   private static final IEncodedMemoryArea eP = new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(12, 3), DirectEncodedMemoryArea.get(6, 2));
   private static final IV UO = new IV(eP);
   private static final IV Ab = new gU.Av(1048576, DirectEncodedMemoryArea.get(0, 5), eP);
   public static final tz oT = new UC("BFC", wT.wS, UO, Ab).ys();
   public static final tz[] fI = new tz[]{
      new UC("SBFX", wT.wS, wT.ys, UO, IV.NS).pC(cT.wS), new UC("BFI", wT.wS, wT.ys, UO, Ab).ys(), new UC("UBFX", wT.wS, wT.ys, UO, IV.NS).pC(cT.wS), null
   };
   public static final tz WR = new UC("SEL", wT.UT, wT.ys, wT.pC).pC(cT.UT);
   public static final tz NS = new UC("CLZ", wT.UT, wT.pC).pC(cT.wS);

   private static class Av extends IV {
      private IEncodedMemoryArea OI;

      public Av(int var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
         super(var1, var2);
         this.OI = var3;
      }

      @Override
      protected long getValue(byte[] var1, int var2) throws ProcessorException {
         long var3 = Gq.A(var1, this.getMemoryArea());
         long var5 = Gq.A(var1, this.OI);
         if (var3 >= var5) {
            return var3 - var5;
         } else {
            throw new ProcessorException("Unpredictable BFC Instruction: Illegal width");
         }
      }
   }
}
