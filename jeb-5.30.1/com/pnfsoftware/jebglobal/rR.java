package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public abstract class rR extends jD {
   public static final int q = 0;
   public static final int RF = 15;
   public static final int xK = 33;
   private final int Dw;
   private final int Uv;
   private final IEncodedMemoryArea oW;

   protected rR(int var1, jD.eo var2, IEncodedMemoryArea var3, int var4) {
      super(var2);
      this.Dw = var1;
      this.oW = var3;
      this.Uv = var4;
   }

   public int q(byte[] var1) {
      return k.RF(var1, this.oW);
   }

   public Integer RF(byte[] var1) {
      int var2 = k.RF(var1, this.oW);
      return this.q(var2) ? null : var2;
   }

   public boolean Dw(byte[] var1) {
      Integer var2 = this.RF(var1);
      return var2 == null ? true : this.Uv < 0 || var2 <= this.Uv;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      if (!this.Dw(var1)) {
         throw new ProcessorException(Strings.f("Invalid operands for instruction %s", Formatter.byteArrayToHex(var1)));
      } else {
         Integer var3 = this.RF(var1);
         return var3 == null ? null : GC.q(this.Uv(var1), var3, var2, this.q());
      }
   }

   public int Uv(byte[] var1) {
      return this.Dw;
   }

   public abstract boolean oW(byte[] var1);
}
