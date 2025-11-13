package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.HashMap;

class bqz implements IDVisitor {
   bqz(bqx var1, IDVar var2, HashMap var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDInstanceField var4 && var4.getInstance() == this.pC) {
         int var5 = var4.getIndex().getValue();
         IDVar var6 = (IDVar)this.A.get(var5);
         if (var6 == null) {
            var6 = this.kS.wS.createVirtualVar(var4.getType());
            this.A.put(var5, var6);
         }

         if (!var2.replaceSubExpression(var1, var6)) {
            var3.interrupt(false);
         }
      }
   }
}
