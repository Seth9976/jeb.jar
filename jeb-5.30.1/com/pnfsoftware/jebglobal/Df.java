package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class Df implements Ef {
   protected final int gP;
   private Dm q;
   private IEncodedMemoryArea RF;

   public Df(int var1, Dm var2, IEncodedMemoryArea var3) {
      this.gP = var1;
      this.q = var2;
      this.RF = var3;
   }

   protected XD RF(byte[] var1) {
      return XD.q(this.gP, this.q);
   }

   protected IEncodedMemoryArea Dw(byte[] var1) {
      return this.RF;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new VP(this.RF(var1), this.Dw(var1)).buildOperand(var1, var2);
   }
}
