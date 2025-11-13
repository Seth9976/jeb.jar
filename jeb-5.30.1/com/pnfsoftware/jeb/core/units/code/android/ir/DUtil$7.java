package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class DUtil$7 implements IDVisitor {
   DUtil$7(int var1) {
      this.val$varid = var1;
   }

   public void process(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.isVar(this.val$varid)) {
         var3.interrupt(false);
      }
   }
}
