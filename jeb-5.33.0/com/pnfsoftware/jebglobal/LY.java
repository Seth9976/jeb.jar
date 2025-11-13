package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class LY extends gZ {
   private static final IEncodedMemoryArea ld = DirectEncodedMemoryArea.get(0, 3);
   private static final IEncodedMemoryArea gp = DirectEncodedMemoryArea.get(3, 3);
   private static final IEncodedMemoryArea oT = DirectEncodedMemoryArea.get(6, 3);
   private static final IEncodedMemoryArea fI = DirectEncodedMemoryArea.get(8, 3);
   private final LY.Av WR;
   public static final LY pC = new LY(ld);
   public static final LY A = new LY(gp);
   public static final LY kS = new LY(oT);
   public static final LY wS = new LY(fI);
   public static final LY UT = new LY(fI, Ll.Av.A);
   public static final LY E = new LY(fI, LY.Av.A);
   public static final gZ sY = new LY(DirectEncodedMemoryArea.get(3, 4));
   public static final gZ ys = new LY(new EncodedMemoryAreaList(DirectEncodedMemoryArea.get(7, 1), DirectEncodedMemoryArea.get(0, 3)));

   public LY(IEncodedMemoryArea var1) {
      this(0, var1, Ll.Av.pC, LY.Av.pC);
   }

   public LY(IEncodedMemoryArea var1, Ll.Av var2) {
      this(0, var1, var2, LY.Av.pC);
   }

   public LY(IEncodedMemoryArea var1, LY.Av var2) {
      this(0, var1, Ll.Av.pC, var2);
   }

   public LY(int var1, IEncodedMemoryArea var2, Ll.Av var3, LY.Av var4) {
      super(var1, var3, var2, 15);
      this.WR = var4;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      if (this.WR == LY.Av.A) {
         int var3 = this.A(var1);
         int var4 = var1[1] & 255;
         boolean var5 = (var4 & 1 << var3) == 0;
         return LC.pC(this.UT(var1), var3, var2, var5 ? 131072 : 0);
      } else {
         return super.buildOperand(var1, var2);
      }
   }

   @Override
   public boolean E(byte[] var1) {
      return this.UT(var1) == 0 && this.A(var1) == 15;
   }

   private static enum Av {
      pC,
      A;
   }
}
