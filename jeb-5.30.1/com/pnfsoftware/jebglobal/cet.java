package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cet implements IDVisitor {
   int q;

   cet(ces var1, int var2, IDVar var3) {
      this.Dw = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDOperation var4 && var4.isConditional() && this.q < this.RF) {
         var3.interrupt(false);
      } else {
         if (var1 == this.xK) {
            this.q++;
         }
      }
   }
}
