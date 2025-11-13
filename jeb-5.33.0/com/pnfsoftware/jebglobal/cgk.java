package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class cgk implements cfk {
   public cfj pC(byte[] var1, int var2) throws ProcessorException {
      int var3 = DirectEncodedMemoryArea.get(11, 5).decodeInt(var1);
      return cfj.pC(5, var3 + 1 + 32, false);
   }
}
