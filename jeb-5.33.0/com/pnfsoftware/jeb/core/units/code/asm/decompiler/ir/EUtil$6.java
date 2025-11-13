package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class EUtil$6 implements IEVisitor {
   EUtil$6(IEGeneric var1, IEGeneric var2) {
      this.val$src = var1;
      this.val$dst = var2;
   }

   public void process(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (this.val$src.equals(var1)) {
         var2.replaceSubExpression(var1, this.val$dst.duplicate());
         var3.setVisitResult(false);
      }
   }
}
