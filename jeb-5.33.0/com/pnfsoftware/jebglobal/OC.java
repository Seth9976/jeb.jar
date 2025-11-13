package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class OC implements Hu {
   private IEncodedMemoryArea pC;
   private int A;

   public OC(IEncodedMemoryArea var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) {
      int var3 = this.pC.decodeInt(var1);
      return Yg.pC(32, (long)(this.A - var3));
   }
}
