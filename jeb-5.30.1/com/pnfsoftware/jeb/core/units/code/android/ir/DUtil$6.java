package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class DUtil$6 implements IDVisitor {
   public void process(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar) {
         var3.interrupt(false);
      }
   }
}
