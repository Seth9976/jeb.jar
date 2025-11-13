package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class iK implements IEVisitor {
   iK(Ei var1) {
      this.pC = var1;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var2 != null && var1.equals(this.pC.kS) && !this.pC(var2)) {
         var3.interrupt(false);
      }
   }

   private boolean pC(IEGeneric var1) {
      if (var1.isOperation()) {
         OperationType var2 = var1.asOperation().getOperationType();
         if (var2.isAnyOf(OperationType.ADD, OperationType.SUB, OperationType.SHL)) {
            return true;
         }

         if (var2 == OperationType.MUL) {
            return var1.asOperation().getOperand2().isImm() && var1.asOperation().getOperand2().asImm().getValueAsUnsignedLong() < 8L;
         }

         if (var2 == OperationType.NOT) {
            return true;
         }

         if (var2 == OperationType.AND) {
            return true;
         }
      } else if (var1.isSlice()) {
         return true;
      }

      return false;
   }
}
