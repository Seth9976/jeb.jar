package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class jO implements Ef {
   private IEncodedMemoryArea q;
   private int RF;

   public jO(IEncodedMemoryArea var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) {
      int var3 = this.q.decodeInt(var1);
      return CW.q(32, (long)(this.RF - var3));
   }
}
