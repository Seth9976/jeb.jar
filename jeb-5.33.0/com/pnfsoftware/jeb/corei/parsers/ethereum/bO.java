package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class bO implements IEVisitor {
   bO(Ws var1, IEVar var2, IEVar var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      var3.setVisitResult(false);
      if (var1 instanceof IEOperation var4) {
         OperationType var5 = var4.getOperationType();
         if (var5 == OperationType.AND && var4.getOperand1() instanceof IEOperation && var4.getOperand2().equals(EUtil.imm(4294967295L, 256))) {
            var4 = (IEOperation)var4.getOperand1();
            var5 = var4.getOperationType();
         }

         if (var5 == OperationType.DIV_U && var4.getOperand1() == this.pC && EUtil.isImmValue(var4.getOperand2(), Ws.A)) {
            IEGeneric var6 = this.A.zeroExtend(var1.getBitsize());
            var2.replaceSubExpression(var1, var6);
            var3.setReplacedNode(var6);
            var3.interrupt(true);
         }

         if (var5 == OperationType.SHR && var4.getOperand1() == this.pC && EUtil.isImmValue(var4.getOperand2(), Ws.kS)) {
            IEGeneric var7 = this.A.zeroExtend(var1.getBitsize());
            var2.replaceSubExpression(var1, var7);
            var3.setReplacedNode(var7);
            var3.interrupt(true);
         }
      }
   }
}
