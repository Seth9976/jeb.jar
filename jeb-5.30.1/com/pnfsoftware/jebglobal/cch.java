package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.concurrent.atomic.AtomicBoolean;

class cch implements IDVisitor {
   cch(cce.iZ var1, boolean var2, AtomicBoolean var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var2 != null) {
         if (var1 instanceof IDVar var4) {
            IDExpression var5 = this.xK.q(var4, this.q);
            if (var5 == null) {
               var3.interrupt(false);
            } else if (!this.RF.get()) {
               var2.replaceVariable(var4, var5);
            }
         } else if (var1 instanceof IDInvokeInfo) {
            var3.interrupt(false);
         }
      }
   }
}
