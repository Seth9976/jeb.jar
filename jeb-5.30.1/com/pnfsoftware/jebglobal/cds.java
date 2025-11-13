package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cds implements IDVisitor {
   cds(cdq var1, IDField var2, int var3, IDExpression var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.equalsEx(this.q, false)) {
         if (this.RF == 2 && !(var2 instanceof IDArrayElt)) {
            return;
         }

         IDExpression var4 = this.xK.duplicate();
         if (var2.replaceSubExpression(var1, var4)) {
            var3.setReplacedNode(var4);
            this.Dw.q++;
         }
      }
   }
}
