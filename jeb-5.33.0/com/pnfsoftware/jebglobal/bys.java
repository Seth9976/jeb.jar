package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bys implements IDVisitor {
   bys(byr var1) {
      this.pC = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo var4 && !bqh.pC(var4.getMethodSignature(), this.pC.g).pC()) {
         IDExpression var5 = bpl.pC(this.pC.ctx, var4, 30, 5, false);
         if (var5 != null) {
            if (var2 instanceof IDInstruction && var2.asInstruction().isInvoke()) {
               var3.interrupt(false);
            } else if (var2 != null && var2.replaceSubExpression(var4, var5)) {
               this.pC.pC++;
            }
         }
      }
   }
}
