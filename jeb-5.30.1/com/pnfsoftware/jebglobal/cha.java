package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.List;

class cha implements IDVisitor {
   cha(IDExpression var1, boolean var2, List var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (this.q == var1 || this.RF && this.q.equals(var1)) {
         var3.parentsIterator().forEachRemaining(var1x -> var0.add(var1x));
         var3.interrupt(true);
      }
   }
}
