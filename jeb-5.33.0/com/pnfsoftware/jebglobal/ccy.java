package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class ccy implements IDVisitor {
   int pC;
   int A;

   ccy(ccx var1, IDVar var2, ContextAccessType var3) {
      this.UT = var1;
      this.kS = var2;
      this.wS = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar && var1.equals(this.kS)) {
         this.pC++;
         if (this.A > 0) {
            var3.interrupt(false);
         }
      } else if (this.pC(var1) || !this.wS.isNoneLike() && DUtil.usesReferences(var1)) {
         this.A++;
         if (this.pC == 0) {
            var3.interrupt(false);
         }
      }
   }

   private boolean pC(IDExpression var1) {
      return !var1.hasSideEffects(this.UT.pC, false)
         ? false
         : !(var1 instanceof IDCallInfo var2 && var2.getMethodSignature().startsWith("Ljava/lang/Class;->"));
   }
}
