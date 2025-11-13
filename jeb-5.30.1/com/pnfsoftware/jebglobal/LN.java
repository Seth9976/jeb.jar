package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public abstract class LN extends jD {
   private final IEncodedMemoryArea q;
   private final int RF;
   private final IEncodedMemoryArea xK;

   public LN(IEncodedMemoryArea var1, int var2) {
      this.q = var1;
      this.RF = var2;
      this.xK = null;
   }

   public LN(IEncodedMemoryArea var1, IEncodedMemoryArea var2) {
      this.q = var1;
      this.RF = -1;
      this.xK = var2;
   }

   public LN(IEncodedMemoryArea var1, IEncodedMemoryArea var2, jD.eo var3) {
      super(var3);
      this.q = var1;
      this.RF = -1;
      this.xK = var2;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = this.xK != null ? this.xK.decodeInt(var1) + 1 : this.RF;
      CW[] var4 = new CW[var3];
      int var5 = this.q.decodeInt(var1) + 1;

      for (int var6 = 0; var6 < var3; var6++) {
         var4[var6] = this.q(var5 * var6, var1, var2);
      }

      return new eL(0, this.q(), var4);
   }

   protected abstract CW q(int var1, byte[] var2, int var3) throws ProcessorException;
}
