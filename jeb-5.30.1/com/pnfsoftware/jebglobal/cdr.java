package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cdr implements IDVisitor {
   cdr(cdq var1, IDField var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.equalsEx(this.q, false)) {
         var3.interrupt(false);
      }
   }
}
