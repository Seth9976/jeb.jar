package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class chv implements IDVisitor {
   int q;
   int RF;

   chv(chu var1, IDVar var2, ContextAccessType var3) {
      this.Uv = var1;
      this.xK = var2;
      this.Dw = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDVar && var1.equals(this.xK)) {
         this.q++;
         if (this.RF > 0) {
            var3.interrupt(false);
         }
      } else if (this.q(var1) || !this.Dw.isNoneLike() && DUtil.usesReferences(var1)) {
         this.RF++;
         if (this.q == 0) {
            var3.interrupt(false);
         }
      }
   }

   private boolean q(IDExpression var1) {
      return !var1.hasSideEffects(this.Uv.q, false) ? false : !(var1 instanceof IDCallInfo var2 && var2.getMethodSignature().startsWith("Ljava/lang/Class;->"));
   }
}
