package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bxg implements IDVisitor {
   bxg(bxe var1, bxa var2, IDExpression[] var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      this.q.Dw();
      if (this.q.q(var1)) {
         this.RF[0] = var1;
         this.RF[1] = var2;
         var3.interrupt(true);
      }
   }
}
