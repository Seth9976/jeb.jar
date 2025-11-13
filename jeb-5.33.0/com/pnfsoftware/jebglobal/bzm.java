package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bzm implements IDVisitor {
   bzm(IDVar var1, IDVar var2) {
      this.pC = var1;
      this.A = var2;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar && (var1.equals(this.pC) || var1.equals(this.A))) {
         var3.interrupt(false);
      }
   }
}
