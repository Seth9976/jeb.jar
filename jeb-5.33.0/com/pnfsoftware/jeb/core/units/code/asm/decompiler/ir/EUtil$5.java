package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import java.util.List;

class EUtil$5 implements IEVisitor {
   EUtil$5(IEGeneric var1, List var2) {
      this.val$value = var1;
      this.val$parents = var2;
   }

   public void process(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1 == this.val$value) {
         this.val$parents.add(var2);
         var3.skipChildren();
      }
   }
}
