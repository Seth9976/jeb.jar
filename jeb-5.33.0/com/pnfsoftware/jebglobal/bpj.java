package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bpj implements IDVisitor {
   int pC;

   bpj(bph var1, Class var2, int var3, IDExpression[] var4) {
      this.UT = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (this.A.isInstance(var1)) {
         if (this.pC >= this.kS) {
            this.wS[0] = var1;
            var3.interrupt(true);
         }

         this.pC++;
      }
   }
}
