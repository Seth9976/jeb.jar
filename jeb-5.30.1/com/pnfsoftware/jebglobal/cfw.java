package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cfw implements IDVisitor {
   cfw(cfv var1, IDVar var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDInvokeInfo var4) {
         for (IDExpression var6 : var4.getArguments()) {
            if (var6.equals(this.q)) {
               var3.interrupt(false);
               return;
            }
         }
      }
   }
}
