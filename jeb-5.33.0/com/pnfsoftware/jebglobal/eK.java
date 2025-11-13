package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class eK implements Hu {
   private int pC;
   private IEncodedMemoryArea A;

   public eK(int var1, IEncodedMemoryArea var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) {
      int var3 = Gq.A(var1, this.A);
      double var4 = Gq.pC(var3);
      int var6 = Gq.A(var3);
      return Yg.pC(this.pC, var6, var4);
   }
}
