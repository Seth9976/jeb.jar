package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class JM extends OZ {
   JM(String[][][] var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, IEncodedMemoryArea var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      CW var3 = super.buildOperand(var1, var2);
      if (DirectEncodedMemoryArea.decodeInt(12, 1, var1) == 0) {
         return var3;
      } else {
         return Fh.q(var1) ? null : new sJ(var3.getOperandValue(), var3.getFlags(), var3.getAlias(0L) + "NXS");
      }
   }
}
