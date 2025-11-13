package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class xT implements Ef {
   private final int q;
   private final Ef[] RF;

   public xT(Ef... var1) {
      this(0, var1);
   }

   public xT(int var1, Ef... var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      CW[] var3 = new CW[this.RF.length];

      for (int var4 = 0; var4 < this.RF.length; var4++) {
         var3[var4] = this.RF[var4].buildOperand(var1, var2);
      }

      return new eL(0, this.q, var3);
   }
}
