package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class le extends LN {
   private IEncodedMemoryArea q;

   public le(IEncodedMemoryArea var1, IEncodedMemoryArea var2, int var3) {
      super(var2, var3);
      this.q = var1;
   }

   public le(IEncodedMemoryArea var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
      super(var2, var3);
      this.q = var1;
   }

   @Override
   protected CW q(int var1, byte[] var2, int var3) throws ProcessorException {
      return new VP(Y.RF(var1), this.q).buildOperand(var2, var3);
   }
}
