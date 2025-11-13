package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cef implements IDVisitor {
   cef(IDVar var1, IDVar var2) {
      this.q = var1;
      this.RF = var2;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar && (var1.equals(this.q) || var1.equals(this.RF))) {
         var3.interrupt(false);
      }
   }
}
