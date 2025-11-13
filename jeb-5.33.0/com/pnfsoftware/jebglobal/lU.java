package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class lU implements jp {
   private final int pC;
   private final int A;

   public lU(int var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String pC(tz var1, byte[] var2) {
      int var3 = DirectEncodedMemoryArea.decodeInt(this.pC, this.A, var2);
      return var3 == 0 ? null : "Should be zero";
   }
}
