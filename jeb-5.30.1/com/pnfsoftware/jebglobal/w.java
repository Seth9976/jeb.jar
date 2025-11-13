package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;

class w implements YB {
   private int q = 0;
   private int RF = 0;

   public w(int var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String q(Je var1, byte[] var2) {
      long var3 = EncodedMemoryAreaUtil.zeroExtend(var2, this.q, 1);
      return var3 != this.RF ? "Invalid bit value at position " + this.q : null;
   }
}
