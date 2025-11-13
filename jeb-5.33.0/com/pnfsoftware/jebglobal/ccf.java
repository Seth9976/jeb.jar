package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class ccf implements IDVisitor {
   ccf(ccd var1) {
      this.pC = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo && ((IDCallInfo)var1).getMethodName().equals("toString") && ((IDCallInfo)var1).getCountOfArguments() == 1) {
         IDExpression var4 = this.pC.pC((IDCallInfo)var1);
         if (var4 != null && var2.replaceSubExpression(var1, var4)) {
            var3.setReplacedNode(var4);
            var3.interrupt(false);
         }
      }
   }
}
