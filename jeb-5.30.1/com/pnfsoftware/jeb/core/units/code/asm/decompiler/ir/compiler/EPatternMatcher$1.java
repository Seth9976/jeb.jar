package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVisitor;

class EPatternMatcher$1 implements IEVisitor {
   EPatternMatcher$1(EPatternMatcher var1, EExpressionMatcher var2, IEGeneric[] var3) {
      this.this$0 = var1;
      this.val$m = var2;
      this.val$matched = var3;
   }

   public void process(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      this.val$m.reset();
      if (this.val$m.isMatch(var1)) {
         this.val$matched[0] = var1;
         this.val$matched[1] = var2;
         var3.interrupt(true);
      }
   }
}
