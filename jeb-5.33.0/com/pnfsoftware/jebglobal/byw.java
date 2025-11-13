package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class byw implements IDVisitor {
   byw(byv var1) {
      this.pC = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      IDExpression var4 = null;
      if (var1 instanceof IDCallInfo var5) {
         String var6 = var5.getMethodSignature();
         if (var6.equals("Ljava/lang/Integer;->hashCode(I)I")) {
            var4 = var5.getArgument(0);
         }
      }

      if (var4 != null) {
         if (var2 instanceof IDInstruction && var2.asInstruction().isInvoke()) {
            var3.interrupt(false);
         } else if (var2 != null && var2.replaceSubExpression(var1, var4)) {
            this.pC.pC++;
         }
      }
   }
}
