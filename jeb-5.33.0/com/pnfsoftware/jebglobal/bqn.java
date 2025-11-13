package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Set;

class bqn implements IDVisitor {
   bqn(Set var1) {
      this.pC = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar var4 && this.pC.contains(var4.getId())) {
         var3.interrupt(false);
      }
   }
}
