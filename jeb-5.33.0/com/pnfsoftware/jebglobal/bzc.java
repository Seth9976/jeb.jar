package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bzc implements IDVisitor {
   bzc(bza var1, IDField var2, int var3, IDExpression var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.equalsEx(this.pC, false)) {
         if (this.A == 2 && !(var2 instanceof IDArrayElt)) {
            return;
         }

         IDExpression var4 = this.kS.duplicate();
         if (var2.replaceSubExpression(var1, var4)) {
            var3.setReplacedNode(var4);
            this.wS.pC++;
         }
      }
   }
}
