package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class EUtil$4 implements IEVisitor {
   EUtil$4(IEGeneric var1) {
      this.val$token = var1;
   }

   public void process(IEGeneric var1, IEGeneric var2, IVisitResults var3) {
      if (var1.equals(this.val$token)) {
         var3.interrupt(false);
      }
   }
}
