package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Wp extends h {
   private IEncodedMemoryArea pC;

   public Wp(IEncodedMemoryArea var1, IEncodedMemoryArea var2, int var3) {
      super(var2, var3);
      this.pC = var1;
   }

   public Wp(IEncodedMemoryArea var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
      super(var2, var3);
      this.pC = var1;
   }

   @Override
   protected Yg pC(int var1, byte[] var2, int var3) throws ProcessorException {
      return new Yu(BS.A(var1), this.pC).buildOperand(var2, var3);
   }
}
