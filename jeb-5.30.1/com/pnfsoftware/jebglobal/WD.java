package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class WD extends VP {
   private final IEncodedMemoryArea q;
   private final WD.eo[] RF;

   public WD(IEncodedMemoryArea var1) {
      super(null, (IEncodedMemoryArea)null);
      this.q = var1;
      this.RF = new WD.eo[1 << var1.getLength()];
   }

   public WD q(int var1, rR var2, IEncodedMemoryArea var3) {
      this.RF[var1] = new WD.eo(var2, var3);
      return this;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      return new BY(this.q(var1).buildOperand(var1, var2), this.RF(var1));
   }

   private WD.eo Dw(byte[] var1) {
      int var2 = this.q.decodeInt(var1);
      return this.RF[var2];
   }

   protected rR q(byte[] var1) {
      WD.eo var2 = this.Dw(var1);
      return var2 != null ? var2.q : null;
   }

   @Override
   protected Integer RF(byte[] var1) {
      WD.eo var2 = this.Dw(var1);
      return var2 != null ? k.RF(var1, var2.RF) : null;
   }

   private static class eo {
      private rR q;
      private IEncodedMemoryArea RF;

      public eo(rR var1, IEncodedMemoryArea var2) {
         this.q = var1;
         this.RF = var2;
      }
   }
}
