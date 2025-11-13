package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class VP implements Ef {
   private rR q;
   private IEncodedMemoryArea RF;

   public VP(rR var1, IEncodedMemoryArea var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new BY(this.q.buildOperand(var1, var2), this.RF(var1));
   }

   protected Integer RF(byte[] var1) {
      return this.RF != null ? this.RF.decodeInt(var1) : null;
   }
}
