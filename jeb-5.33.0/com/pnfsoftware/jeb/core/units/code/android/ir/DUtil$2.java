package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;

class DUtil$2 implements IDVisitor {
   public void process(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDField || var1 instanceof IDArrayElt || var1 instanceof IDNewArrayInfo) {
         var3.interrupt(false);
      }
   }
}
