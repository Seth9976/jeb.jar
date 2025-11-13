package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import java.util.function.Predicate;

class IEGeneric$1 implements IEVisitor {
   IEGeneric$1(IEGeneric var1, Predicate var2) {
      this.this$0 = var1;
      this.val$tester = var2;
   }

   public void process(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (this.val$tester.test(var1)) {
         var3.interrupt(false);
      }
   }
}
