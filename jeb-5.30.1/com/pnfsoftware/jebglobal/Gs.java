package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.DirectEncodedMemoryArea;

class Gs implements YB {
   private final int q;
   private final int RF;

   public Gs(int var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String q(Je var1, byte[] var2) {
      int var3 = DirectEncodedMemoryArea.decodeInt(this.q, this.RF, var2);
      return var3 == 0 ? null : "Should be zero";
   }
}
