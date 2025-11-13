package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bwm implements IDVisitor {
   int pC;

   bwm(bwk var1) {
      this.A = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDStaticField) {
         boolean var4 = false;
         if (var1.equalsEx(this.A.fI, false)) {
            var4 = var2.replaceSubExpression(var1, this.A.wS.createInt(0));
         } else if (var1.equalsEx(this.A.WR, false)) {
            var4 = var2.replaceSubExpression(var1, this.A.wS.createInt(1));
         }

         if (var4) {
            this.pC++;
         }
      }
   }
}
