package com.pnfsoftware.jeb.core.units.code;

import java.util.List;

public class InstructionUtil {
   public static IInstructionOperand getOperand(IInstruction var0, int var1) {
      return var1 >= 0 && var1 < var0.getOperands().length ? var0.getOperands()[var1] : null;
   }

   public static IInstructionOperand getOperand(IInstruction var0, int var1, Class var2) {
      if (var1 >= 0 && var1 < var0.getOperands().length) {
         IInstructionOperand var3 = var0.getOperands()[var1];
         return !var2.isInstance(var3) ? null : var3;
      } else {
         return null;
      }
   }

   public static int getSizeOf(List var0) {
      return getSizeUntil(var0, var0.size());
   }

   public static int getSizeUntil(List var0, int var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1; var3++) {
         var2 += ((IInstruction)var0.get(var3)).getSize();
      }

      return var2;
   }
}
