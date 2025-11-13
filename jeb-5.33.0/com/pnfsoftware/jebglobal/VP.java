package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class VP extends Ll {
   private final String pC;

   public VP(String var1) {
      this(Ll.Av.pC, var1);
   }

   public VP(Ll.Av var1, String var2) {
      super(var1);
      this.pC = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new fj(0L, this.pC(), this.pC);
   }
}
