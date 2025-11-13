package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.VirtualEncodedMemoryArea;

public class cB extends h {
   public cB(IEncodedMemoryArea var1) {
      super(VirtualEncodedMemoryArea._0, var1);
   }

   @Override
   protected Yg pC(int var1, byte[] var2, int var3) throws ProcessorException {
      return BS.pC(var1).buildOperand(var2, var3);
   }
}
