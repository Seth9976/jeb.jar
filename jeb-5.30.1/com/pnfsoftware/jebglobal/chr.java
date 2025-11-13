package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Map;

class chr implements IDVisitor {
   chr(chp var1, Map var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar var4) {
         int var5 = var4.getId();
         Integer var6 = (Integer)this.q.get(var5);
         if (var6 != null) {
            var2.replaceSubExpression(var4, this.RF.RF.getVar(var6));
         }
      }
   }
}
