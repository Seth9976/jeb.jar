package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import java.util.ArrayList;

public abstract class rB implements Ef {
   private final IEncodedMemoryArea q;

   public rB(IEncodedMemoryArea var1) {
      this.q = var1;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      long var3 = this.q.decode(var1);
      return new eL((int)var3, this.q(var1, var2), this.q(var3, var1, var2));
   }

   protected int q(byte[] var1, int var2) {
      return 0;
   }

   protected CW[] q(long var1, byte[] var3, int var4) throws ProcessorException {
      ArrayList var5 = new ArrayList();

      for (int var6 = 0; var6 < this.q.getLength(); var6++) {
         if ((var1 & 1 << var6) != 0L) {
            var5.add(this.q(var6, var4));
         }
      }

      return (CW[])var5.toArray(new CW[var5.size()]);
   }

   protected abstract CW q(int var1, int var2) throws ProcessorException;
}
