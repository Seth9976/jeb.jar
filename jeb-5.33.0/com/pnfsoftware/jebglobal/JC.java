package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;

class JC implements jp {
   private int pC = 0;
   private int A = 0;

   public JC(int var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String pC(tz var1, byte[] var2) {
      long var3 = EncodedMemoryAreaUtil.zeroExtend(var2, this.pC, 1);
      return var3 != this.A ? "Invalid bit value at position " + this.pC : null;
   }
}
