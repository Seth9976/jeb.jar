package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class yc extends LN {
   private final IEncodedMemoryArea q;
   private final Dm RF;

   public yc(IEncodedMemoryArea var1, IEncodedMemoryArea var2, Dm var3) {
      super(VirtualEncodedMemoryArea._0, var2, jD.eo.Uv);
      this.q = var1;
      this.RF = var3;
   }

   @Override
   protected CW q(int var1, byte[] var2, int var3) throws ProcessorException {
      return Bq.q(dX.q(this.q, var1), this.RF).buildOperand(var2, var3);
   }
}
