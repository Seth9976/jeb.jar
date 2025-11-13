package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum cmb {
   q,
   RF,
   xK,
   Dw,
   Uv,
   oW,
   gO,
   nf,
   gP,
   za,
   lm,
   zz,
   JY,
   HF,
   LK,
   io,
   qa,
   Hk,
   Me,
   PV;

   public boolean q(IMachineContext var1, clv[] var2, int var3) throws ProcessorException {
      if (this == q) {
         return true;
      } else if (var2 != null && var2.length > 1) {
         clv var4 = var2[0];
         clv var5;
         if (var2.length == 2) {
            var5 = clv.RF(0, 0, var3);
         } else {
            var5 = var2[1];
         }

         if (var4.getOperandValue() == var5.getOperandValue()) {
            return this.q(1L, 1L, this);
         } else if (var1 == null) {
            throw new ProcessorException("Can not determine if Jump condition is satisfied: context is required");
         } else {
            long var6 = var1.getRegisters().getProgramCounter();
            int var8 = var4.q(var6, var3, var1).intValue();
            int var9 = var5.q(var6, var3, var1).intValue();
            return this.q(var8, var9, this);
         }
      } else {
         throw new JebRuntimeException("Inner parsing errer: not enough operands to perform a comparison for condition " + this);
      }
   }

   public boolean q(long var1, long var3, cmb var5) throws ProcessorException {
      switch (var5) {
         case RF:
            return var1 == var3;
         case xK:
            return var1 != var3;
         case Dw:
            return var1 < var3;
         case Uv:
            return var1 <= var3;
         case oW:
            return var1 > var3;
         case gO:
            return var1 >= var3;
         case nf:
         case lm:
         case gP:
         case za:
            return this.q(var1 & 4294967295L, var3 & 4294967295L, this.q(var5));
         case zz:
            return this.q(var1, var3);
         case JY:
            return !this.q(var1, var3);
         default:
            throw new ProcessorException("Mips Condition " + var5 + " is not implemented");
      }
   }

   private boolean q(long var1, long var3) {
      long var5 = var1 + var3;
      return var5 < -2147483648L || var5 > 2147483647L;
   }

   private cmb q(cmb var1) {
      switch (var1) {
         case nf:
            return Dw;
         case lm:
            return gO;
         case gP:
            return Uv;
         case za:
            return oW;
         default:
            return var1;
      }
   }
}
