package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class cly implements clw {
   private clw q;
   private clw RF;

   public cly(clw var1, clw var2) {
      this.q = var1;
      this.RF = var2;
   }

   public clv q(byte[] var1, int var2) throws ProcessorException {
      clv var3 = (clv)this.q.buildOperand(var1, var2);
      clv var4 = new clv(0L, 4, (clv)this.RF.buildOperand(var1, var2));
      return new clv(0L, 0, var3, var4);
   }
}
