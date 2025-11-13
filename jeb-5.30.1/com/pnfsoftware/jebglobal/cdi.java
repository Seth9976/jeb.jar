package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cdi implements IDVisitor {
   cdi(cdh var1) {
      this.q = var1;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo var4 && !bun.q(var4.getMethodSignature(), this.q.g).q()) {
         IDExpression var5 = bto.q(this.q.ctx, var4, 30, 5, false);
         if (var5 != null) {
            if (var2 instanceof IDInstruction && var2.asInstruction().isInvoke()) {
               var3.interrupt(false);
            } else if (var2 != null && var2.replaceSubExpression(var4, var5)) {
               this.q.q++;
            }
         }
      }
   }
}
