package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum cfo {
   pC,
   A,
   kS,
   wS,
   UT,
   E,
   sY,
   ys,
   ld,
   gp,
   oT,
   fI,
   WR,
   NS,
   vP,
   xC,
   ED,
   Sc,
   ah,
   eP;

   public boolean pC(IMachineContext var1, cfj[] var2, int var3) throws ProcessorException {
      if (this == pC) {
         return true;
      } else if (var2 != null && var2.length > 1) {
         cfj var4 = var2[0];
         cfj var5;
         if (var2.length == 2) {
            var5 = cfj.A(0, 0, var3);
         } else {
            var5 = var2[1];
         }

         if (var4.getOperandValue() == var5.getOperandValue()) {
            return this.pC(1L, 1L, this);
         } else if (var1 == null) {
            throw new ProcessorException("Can not determine if Jump condition is satisfied: context is required");
         } else {
            long var6 = var1.getRegisters().getProgramCounter();
            int var8 = var4.pC(var6, var3, var1).intValue();
            int var9 = var5.pC(var6, var3, var1).intValue();
            return this.pC(var8, var9, this);
         }
      } else {
         throw new JebRuntimeException("Inner parsing errer: not enough operands to perform a comparison for condition " + this);
      }
   }

   public boolean pC(long var1, long var3, cfo var5) throws ProcessorException {
      switch (var5) {
         case A:
            return var1 == var3;
         case kS:
            return var1 != var3;
         case wS:
            return var1 < var3;
         case UT:
            return var1 <= var3;
         case E:
            return var1 > var3;
         case sY:
            return var1 >= var3;
         case ys:
         case oT:
         case ld:
         case gp:
            return this.pC(var1 & 4294967295L, var3 & 4294967295L, this.pC(var5));
         case fI:
            return this.pC(var1, var3);
         case WR:
            return !this.pC(var1, var3);
         default:
            throw new ProcessorException("Mips Condition " + var5 + " is not implemented");
      }
   }

   private boolean pC(long var1, long var3) {
      long var5 = var1 + var3;
      return var5 < -2147483648L || var5 > 2147483647L;
   }

   private cfo pC(cfo var1) {
      switch (var1) {
         case ys:
            return wS;
         case oT:
            return sY;
         case ld:
            return UT;
         case gp:
            return E;
         default:
            return var1;
      }
   }
}
