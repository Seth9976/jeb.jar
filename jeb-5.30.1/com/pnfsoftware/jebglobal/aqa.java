package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;

public class aqa {
   private IEVar q;

   public aqa(IEVar var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
      }
   }

   public int q(IEGeneric var1) {
      int var2 = EUtil.countVariablePresence(var1, this.q);
      return this.q(var1, var2);
   }

   private int q(IEGeneric var1, int var2) {
      if (var1 instanceof IEStatement) {
         if (var1 instanceof IEAssign && ((IEAssign)var1).getLeftOperand().equals(this.q)) {
            var2--;
         }

         if (var1 instanceof IECall) {
            if (((IECall)var1).getReturnExpressions().contains(this.q)) {
               var2--;
            }

            if (((IECall)var1).getSpoiledExpressions().contains(this.q)) {
               var2--;
            }
         }

         if (var1 instanceof IEUntranslatedInstruction) {
            if (((IEUntranslatedInstruction)var1).getReturnExpressions().contains(this.q)) {
               var2--;
            }

            if (((IEUntranslatedInstruction)var1).getSideEffectDefinedVariables().contains(this.q)) {
               var2--;
            }
         }
      }

      return var2;
   }
}
