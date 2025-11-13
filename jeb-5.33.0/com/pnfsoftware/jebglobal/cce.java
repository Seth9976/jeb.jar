package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.List;

class cce implements IDVisitor {
   cce(IDExpression var1, boolean var2, List var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (this.pC == var1 || this.A && this.pC.equals(var1)) {
         var3.parentsIterator().forEachRemaining(var1x -> var0.add(var1x));
         var3.interrupt(true);
      }
   }
}
