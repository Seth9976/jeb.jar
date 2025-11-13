package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class PP implements Hu {
   protected final int ld;
   private IX pC;
   private IEncodedMemoryArea A;

   public PP(int var1, IX var2, IEncodedMemoryArea var3) {
      this.ld = var1;
      this.pC = var2;
      this.A = var3;
   }

   protected ER A(byte[] var1) {
      return ER.pC(this.ld, this.pC);
   }

   protected IEncodedMemoryArea wS(byte[] var1) {
      return this.A;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new Yu(this.A(var1), this.wS(var1)).buildOperand(var1, var2);
   }
}
