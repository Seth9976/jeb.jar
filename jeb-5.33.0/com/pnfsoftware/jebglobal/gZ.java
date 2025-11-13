package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public abstract class gZ extends Ll {
   private final int pC;
   private final int A;
   private final IEncodedMemoryArea kS;

   protected gZ(int var1, Ll.Av var2, IEncodedMemoryArea var3, int var4) {
      super(var2);
      this.pC = var1;
      this.kS = var3;
      this.A = var4;
   }

   public int pC(byte[] var1) {
      return Gq.A(var1, this.kS);
   }

   public Integer A(byte[] var1) {
      int var2 = Gq.A(var1, this.kS);
      return this.pC(var2) ? null : var2;
   }

   public boolean wS(byte[] var1) {
      Integer var2 = this.A(var1);
      return var2 == null ? true : this.A < 0 || var2 <= this.A;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      if (!this.wS(var1)) {
         throw new ProcessorException(Strings.f("Invalid operands for instruction %s", Formatter.byteArrayToHex(var1)));
      } else {
         Integer var3 = this.A(var1);
         return var3 == null ? null : LC.pC(this.UT(var1), var3, var2, this.pC());
      }
   }

   public int UT(byte[] var1) {
      return this.pC;
   }

   public abstract boolean E(byte[] var1);
}
