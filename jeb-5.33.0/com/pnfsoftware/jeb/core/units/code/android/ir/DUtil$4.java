package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import java.util.Set;

class DUtil$4 implements IDVisitor {
   DUtil$4(Set var1) {
      this.val$r = var1;
   }

   public void process(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar) {
         this.val$r.add(((IDVar)var1).getId());
      }
   }
}
