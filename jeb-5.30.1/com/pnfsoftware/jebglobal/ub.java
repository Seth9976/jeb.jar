package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class ub implements Ef {
   private int q;
   private IEncodedMemoryArea RF;

   public ub(int var1, IEncodedMemoryArea var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) {
      int var3 = k.RF(var1, this.RF);
      double var4 = k.q(var3);
      int var6 = k.RF(var3);
      return CW.q(this.q, var6, var4);
   }
}
