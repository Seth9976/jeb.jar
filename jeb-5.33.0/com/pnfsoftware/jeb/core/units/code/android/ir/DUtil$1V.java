package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class DUtil$1V implements IDVisitor {
   int cnt;

   DUtil$1V(IDVar var1) {
      this.val$var = var1;
   }

   public void process(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 == this.val$var) {
         this.cnt++;
      }
   }
}
