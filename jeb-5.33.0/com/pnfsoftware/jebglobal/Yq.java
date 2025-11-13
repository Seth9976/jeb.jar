package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class Yq extends h {
   private final IEncodedMemoryArea pC;
   private final IX A;

   public Yq(IEncodedMemoryArea var1, IEncodedMemoryArea var2, IX var3) {
      super(VirtualEncodedMemoryArea._0, var2, Ll.Av.UT);
      this.pC = var1;
      this.A = var3;
   }

   @Override
   protected Yg pC(int var1, byte[] var2, int var3) throws ProcessorException {
      return aP.pC(TN.pC(this.pC, var1), this.A).buildOperand(var2, var3);
   }
}
