package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public abstract class h extends Ll {
   private final IEncodedMemoryArea pC;
   private final int A;
   private final IEncodedMemoryArea kS;

   public h(IEncodedMemoryArea var1, int var2) {
      this.pC = var1;
      this.A = var2;
      this.kS = null;
   }

   public h(IEncodedMemoryArea var1, IEncodedMemoryArea var2) {
      this.pC = var1;
      this.A = -1;
      this.kS = var2;
   }

   public h(IEncodedMemoryArea var1, IEncodedMemoryArea var2, Ll.Av var3) {
      super(var3);
      this.pC = var1;
      this.A = -1;
      this.kS = var2;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = this.kS != null ? this.kS.decodeInt(var1) + 1 : this.A;
      Yg[] var4 = new Yg[var3];
      int var5 = this.pC.decodeInt(var1) + 1;

      for (int var6 = 0; var6 < var3; var6++) {
         var4[var6] = this.pC(var5 * var6, var1, var2);
      }

      return new Uw(0, this.pC(), var4);
   }

   protected abstract Yg pC(int var1, byte[] var2, int var3) throws ProcessorException;
}
