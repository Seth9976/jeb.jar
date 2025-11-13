package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class chy implements IDVisitor {
   chy(chx var1) {
      this.q = var1;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDOperation) {
         try {
            IDImm var4 = var1.evaluate(this.q.ctx);
            if (var4 != null && !var4.isRef() && var2.replaceSubExpression(var1, var4)) {
               var3.setReplacedNode(var4);
               this.q.q++;
            }
         } catch (Exception var5) {
         }
      }
   }
}
