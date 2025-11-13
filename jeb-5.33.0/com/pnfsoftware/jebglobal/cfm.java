package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class cfm implements cfk {
   private cfk pC;
   private cfk A;

   public cfm(cfk var1, cfk var2) {
      this.pC = var1;
      this.A = var2;
   }

   public cfj pC(byte[] var1, int var2) throws ProcessorException {
      cfj var3 = (cfj)this.pC.buildOperand(var1, var2);
      cfj var4 = new cfj(0L, 4, (cfj)this.A.buildOperand(var1, var2));
      return new cfj(0L, 0, var3, var4);
   }
}
