package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class arf implements IEVisitor {
   arf(ard.eo var1, int var2, IEVar var3, IEVar var4, IEVar var5) {
      this.Uv = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 instanceof IEImm) {
         IEImm var4 = var1.asImm()._shr(this.q - 1);
         if (var2.replaceSubExpression(var1, var4)) {
            var3.setReplacedNode(var4);
         } else {
            var3.interrupt(false);
         }
      } else if (!(var1 instanceof IEVar) && !(var1 instanceof IEMem)) {
         if (var1 instanceof IEOperation && ((IEOperation)var1).getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB)) {
            if (var2.replaceSubExpression(var1, this.Dw)) {
               var3.setReplacedNode(this.Dw);
            } else {
               var3.interrupt(false);
            }
         }
      } else if (var1.equals(this.Uv.RF)) {
         if (var2.replaceSubExpression(var1, this.RF)) {
            var3.setReplacedNode(this.RF);
         } else {
            var3.interrupt(false);
         }
      } else if (var1.equals(this.Uv.xK)) {
         if (var2.replaceSubExpression(var1, this.xK)) {
            var3.setReplacedNode(this.xK);
         } else {
            var3.interrupt(false);
         }
      }
   }
}
