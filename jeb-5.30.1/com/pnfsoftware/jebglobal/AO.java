package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.AbstractEncodedMemoryArea;

class AO extends AbstractEncodedMemoryArea {
   @Override
   public int getLength() {
      return 2;
   }

   @Override
   public long decode(byte[] var1) {
      return zF.xK(var1);
   }
}
