package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.concurrent.atomic.AtomicBoolean;

class bxs implements IDVisitor {
   bxs(bxp.DH var1, boolean var2, AtomicBoolean var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var2 != null) {
         if (var1 instanceof IDVar var4) {
            IDExpression var5 = this.kS.pC(var4, this.pC);
            if (var5 == null) {
               var3.interrupt(false);
            } else if (!this.A.get()) {
               var2.replaceVariable(var4, var5);
            }
         } else if (var1 instanceof IDInvokeInfo) {
            var3.interrupt(false);
         }
      }
   }
}
