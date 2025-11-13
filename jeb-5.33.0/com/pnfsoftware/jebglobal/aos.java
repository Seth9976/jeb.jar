package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class aos implements IEVisitor {
   aos(aoq.Av var1, int var2, IEVar var3, IEVar var4, IEVar var5) {
      this.UT = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEImm) {
         IEImm var4 = var1.asImm()._shr(this.pC - 1);
         if (var2.replaceSubExpression(var1, var4)) {
            var3.setReplacedNode(var4);
         } else {
            var3.interrupt(false);
         }
      } else if (!(var1 instanceof IEVar) && !(var1 instanceof IEMem)) {
         if (var1 instanceof IEOperation && ((IEOperation)var1).getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB)) {
            if (var2.replaceSubExpression(var1, this.wS)) {
               var3.setReplacedNode(this.wS);
            } else {
               var3.interrupt(false);
            }
         }
      } else if (var1.equals(this.UT.A)) {
         if (var2.replaceSubExpression(var1, this.A)) {
            var3.setReplacedNode(this.A);
         } else {
            var3.interrupt(false);
         }
      } else if (var1.equals(this.UT.kS)) {
         if (var2.replaceSubExpression(var1, this.kS)) {
            var3.setReplacedNode(this.kS);
         } else {
            var3.interrupt(false);
         }
      }
   }
}
