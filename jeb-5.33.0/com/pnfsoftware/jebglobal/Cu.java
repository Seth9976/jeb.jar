package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.AbstractEncodedMemoryArea;

class Cu extends AbstractEncodedMemoryArea {
   @Override
   public int getLength() {
      return 2;
   }

   @Override
   public long decode(byte[] var1) {
      return Ir.wS(var1);
   }
}
