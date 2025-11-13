package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import java.util.ArrayList;

public abstract class FB implements Hu {
   private final IEncodedMemoryArea pC;

   public FB(IEncodedMemoryArea var1) {
      this.pC = var1;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      long var3 = this.pC.decode(var1);
      return new Uw((int)var3, this.pC(var1, var2), this.pC(var3, var1, var2));
   }

   protected int pC(byte[] var1, int var2) {
      return 0;
   }

   protected Yg[] pC(long var1, byte[] var3, int var4) throws ProcessorException {
      ArrayList var5 = new ArrayList();

      for (int var6 = 0; var6 < this.pC.getLength(); var6++) {
         if ((var1 & 1 << var6) != 0L) {
            var5.add(this.pC(var6, var4));
         }
      }

      return (Yg[])var5.toArray(new Yg[var5.size()]);
   }

   protected abstract Yg pC(int var1, int var2) throws ProcessorException;
}
