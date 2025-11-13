package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class sG extends Yu {
   private final IEncodedMemoryArea pC;
   private final sG.Av[] A;

   public sG(IEncodedMemoryArea var1) {
      super(null, (IEncodedMemoryArea)null);
      this.pC = var1;
      this.A = new sG.Av[1 << var1.getLength()];
   }

   public sG pC(int var1, gZ var2, IEncodedMemoryArea var3) {
      this.A[var1] = new sG.Av(var2, var3);
      return this;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new rw(this.pC(var1).buildOperand(var1, var2), this.A(var1));
   }

   private sG.Av wS(byte[] var1) {
      int var2 = this.pC.decodeInt(var1);
      return this.A[var2];
   }

   protected gZ pC(byte[] var1) {
      sG.Av var2 = this.wS(var1);
      return var2 != null ? var2.pC : null;
   }

   @Override
   protected Integer A(byte[] var1) {
      sG.Av var2 = this.wS(var1);
      return var2 != null ? Gq.A(var1, var2.A) : null;
   }

   private static class Av {
      private gZ pC;
      private IEncodedMemoryArea A;

      public Av(gZ var1, IEncodedMemoryArea var2) {
         this.pC = var1;
         this.A = var2;
      }
   }
}
