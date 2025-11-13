package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class xO extends kP {
   private final IEncodedMemoryArea xW;

   public xO(IEncodedMemoryArea var1) {
      this.xW = var1;
   }

   @Override
   protected int q(byte[] var1) {
      return this.xW.decodeInt(var1);
   }
}
