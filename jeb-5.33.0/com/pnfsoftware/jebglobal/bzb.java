package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bzb implements IDVisitor {
   bzb(bza var1, IDField var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.equalsEx(this.pC, false)) {
         var3.interrupt(false);
      }
   }
}
