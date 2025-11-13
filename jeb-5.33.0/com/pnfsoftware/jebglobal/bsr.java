package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bsr implements IDVisitor {
   bsr(bsq var1, bsm var2, IDExpression[] var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      this.pC.pC();
      if (this.pC.pC(var1)) {
         this.A[0] = var1;
         this.A[1] = var2;
         var3.interrupt(true);
      }
   }
}
