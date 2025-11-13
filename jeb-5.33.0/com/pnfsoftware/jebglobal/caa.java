package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class caa implements IDVisitor {
   int pC;

   caa(bzz var1, int var2, IDVar var3) {
      this.wS = var1;
      this.A = var2;
      this.kS = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDOperation var4 && var4.isConditional() && this.pC < this.A) {
         var3.interrupt(false);
      } else {
         if (var1 == this.kS) {
            this.pC++;
         }
      }
   }
}
