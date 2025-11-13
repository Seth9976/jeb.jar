package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;

public class Cx extends jD {
   private final String q;

   public Cx(String var1) {
      this(jD.eo.q, var1);
   }

   public Cx(jD.eo var1, String var2) {
      super(var1);
      this.q = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new sJ(0L, this.q(), this.q);
   }
}
