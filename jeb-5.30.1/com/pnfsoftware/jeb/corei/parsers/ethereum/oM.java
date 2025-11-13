package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class oM implements IEVisitor {
   oM(ej var1, IEVar var2, IEVar var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      var3.setVisitResult(false);
      if (var1 instanceof IEOperation var4) {
         OperationType var5 = var4.getOperationType();
         if (var5 == OperationType.AND && var4.getOperand1() instanceof IEOperation && var4.getOperand2().equals(EUtil.imm(4294967295L, 256))) {
            var4 = (IEOperation)var4.getOperand1();
            var5 = var4.getOperationType();
         }

         if (var5 == OperationType.DIV_U && var4.getOperand1() == this.q && EUtil.isImmValue(var4.getOperand2(), ej.RF)) {
            IEGeneric var6 = this.RF.zeroExtend(var1.getBitsize());
            var2.replaceSubExpression(var1, var6);
            var3.setReplacedNode(var6);
            var3.interrupt(true);
         }

         if (var5 == OperationType.SHR && var4.getOperand1() == this.q && EUtil.isImmValue(var4.getOperand2(), ej.xK)) {
            IEGeneric var7 = this.RF.zeroExtend(var1.getBitsize());
            var2.replaceSubExpression(var1, var7);
            var3.setReplacedNode(var7);
            var3.interrupt(true);
         }
      }
   }
}
