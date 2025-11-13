package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;

public class InstructionUtil {
   public static IInstructionOperand getOperandByGlobalIndex(IInstruction var0, int var1) {
      IInstructionOperand[] var2 = var0.getOperands();

      while (var2.length > 0) {
         int var3 = var1 & 15;
         if (var3 < var2.length) {
            IInstructionOperand var4 = var2[var3];
            if (!(var4 instanceof IInstructionOperandGeneric)) {
               return var4;
            }

            if (((IInstructionOperandGeneric)var4).getOperandType() == 7 && var4 instanceof IInstructionOperandList) {
               var2 = ((IInstructionOperandList)var4).getOperands();
               var1 >>= 4;
               continue;
            }

            return var4;
         }
         break;
      }

      return null;
   }

   public static boolean isAddressOperand(IInstructionOperandGeneric var0) {
      return var0.getOperandType() == 3 || var0.getOperandType() == 2;
   }
}
