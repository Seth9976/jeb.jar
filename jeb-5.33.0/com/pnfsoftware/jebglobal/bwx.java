package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bwx implements IDVisitor {
   bwx(IDArrayElt[] var1) {
      this.pC = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDArrayElt && var1.getType().isPrimitive()) {
         this.pC[0] = var1.asArrayElt();
         var3.interrupt(true);
      }
   }
}
