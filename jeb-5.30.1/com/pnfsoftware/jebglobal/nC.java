package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;

class nC implements IEVisitor {
   nC(KV var1, IEGeneric var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var2 != null && var1.equals(this.q) && var2.isOperation(OperationType.SHL) && var2.asOperation().getOperand2().isImm()) {
         var3.interrupt(false);
      }
   }
}
