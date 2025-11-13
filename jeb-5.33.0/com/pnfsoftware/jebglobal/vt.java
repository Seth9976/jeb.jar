package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;

public class vt extends Ll {
   private final String[][][] pC;
   private final String A = null;
   private final IEncodedMemoryArea kS;
   private final IEncodedMemoryArea wS;
   private final IEncodedMemoryArea UT;

   public vt(String[][][] var1, IEncodedMemoryArea var2, IEncodedMemoryArea var3, IEncodedMemoryArea var4) {
      super(Ll.Av.pC);
      this.pC = var1;
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
   }

   @Override
   public Yg buildOperand(byte[] var1, int var2) throws ProcessorException {
      int var3 = Gq.A(var1, this.kS);
      if (!this.pC(var3) && this.pC != null) {
         String var4 = this.pC(var3, var1);
         if (var4 == null) {
            if (this.A == null) {
               throw new ProcessorException("Unknown value");
            }

            var4 = String.format(this.A, var3);
         }

         return new fj(var3, this.pC(), var4);
      } else {
         return null;
      }
   }

   private String pC(int var1, byte[] var2) {
      int var3 = Gq.A(var2, this.wS);
      int var4 = Gq.A(var2, this.UT);
      return this.pC != null ? (String)ArrayUtil.getSafe3(this.pC, var1, var3, var4, null) : null;
   }
}
