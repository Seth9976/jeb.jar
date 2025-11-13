package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class btp implements IDVisitor {
   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof btk) {
         ((btk)var1).verify();
      }
   }
}
