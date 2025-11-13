package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class uh extends jD {
   private final String[][] q;
   private final String RF = null;
   private final IEncodedMemoryArea xK;
   private final IEncodedMemoryArea Dw;

   public uh(String[][] var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3) {
      super(jD.eo.q);
      this.q = var1;
      this.xK = var2;
      this.Dw = var3;
   }

   @Override
   public CW buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = k.RF(var1, this.xK);
      if (!this.q(var3) && this.q != null) {
         String var4 = this.q(var3, var1);
         if (var4 == null) {
            if (this.RF == null) {
               throw new ProcessorException("Unknown value");
            }

            var4 = String.format(this.RF, var3);
         }

         return new sJ(var3, this.q(), var4);
      } else {
         return null;
      }
   }

   private String q(int var1, byte[] var2) {
      int var3 = k.RF(var2, this.Dw);
      return this.q != null ? (String)ArrayUtil.getSafe2(this.q, var1, var3, null) : null;
   }
}
