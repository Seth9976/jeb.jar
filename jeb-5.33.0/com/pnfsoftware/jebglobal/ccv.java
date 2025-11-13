package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Map;

class ccv implements IDVisitor {
   ccv(cct var1, Map var2) {
      this.A = var1;
      this.pC = var2;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar var4) {
         int var5 = var4.getId();
         Integer var6 = (Integer)this.pC.get(var5);
         if (var6 != null) {
            var2.replaceSubExpression(var4, this.A.A.getVar(var6));
         }
      }
   }
}
