package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class ZF extends Hs {
   private final IEncodedMemoryArea Ab;

   public ZF(IEncodedMemoryArea var1) {
      this.Ab = var1;
   }

   @Override
   protected int pC(byte[] var1) {
      return this.Ab.decodeInt(var1);
   }
}
