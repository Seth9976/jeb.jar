package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class H extends Fo {
   H(dD var1) {
      super(var1);
   }

   @Override
   int q(byte[] var1) {
      int var2 = super.q(var1);
      return var2 == 3 && DirectEncodedMemoryArea.get(21, 1).decode(var1) != 0L ? -1 : var2;
   }
}
