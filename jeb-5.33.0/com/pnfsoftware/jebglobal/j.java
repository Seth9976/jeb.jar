package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

class j extends nl {
   private final IEncodedMemoryArea Ab;

   public j(int var1, IEncodedMemoryArea var2) {
      super(var1);
      this.Ab = var2;
   }

   @Override
   protected int pC(byte[] var1) {
      return this.Ab.decodeInt(var1);
   }
}
