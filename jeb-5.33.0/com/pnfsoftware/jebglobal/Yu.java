package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Yu implements Hu {
   private gZ pC;
   private IEncodedMemoryArea A;

   public Yu(gZ var1, IEncodedMemoryArea var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new rw(this.pC.buildOperand(var1, var2), this.A(var1));
   }

   protected Integer A(byte[] var1) {
      return this.A != null ? this.A.decodeInt(var1) : null;
   }
}
