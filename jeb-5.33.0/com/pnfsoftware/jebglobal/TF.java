package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class TF implements Hu {
   private Hu pC;
   private Hu[] A;

   public TF(Hu var1, Hu... var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      Yg[] var3 = new Yg[this.A.length];

      for (int var4 = 0; var4 < this.A.length; var4++) {
         var3[var4] = this.A[var4].buildOperand(var1, var2);
      }

      return new rw(this.pC.buildOperand(var1, var2), var3);
   }
}
