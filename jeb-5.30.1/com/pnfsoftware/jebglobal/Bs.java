package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class Bs extends PZ {
   private final IEncodedMemoryArea xW;

   public Bs(int var1, IEncodedMemoryArea var2) {
      super(var1);
      this.xW = var2;
   }

   @Override
   protected int q(byte[] var1) {
      return this.xW.decodeInt(var1);
   }
}
