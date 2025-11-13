package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class Fw implements Hu {
   private final int pC;
   private final Hu[] A;

   public Fw(Hu... var1) {
      this(0, var1);
   }

   public Fw(int var1, Hu... var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      Yg[] var3 = new Yg[this.A.length];

      for (int var4 = 0; var4 < this.A.length; var4++) {
         var3[var4] = this.A[var4].buildOperand(var1, var2);
      }

      return new Uw(0, this.pC, var3);
   }
}
