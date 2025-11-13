package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bwu implements IDVisitor {
   bwu(bwt var1, bvv var2, BasicBlock var3, int var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo var4) {
         if (!this.Dw.q(var4)) {
            return;
         }

         if (!this.q.q(this.RF, this.xK, var4)) {
            if (var4.hasSideEffects(this.Dw.q, false)) {
               var3.interrupt(true);
            }

            return;
         }

         var3.interrupt(false);
      }
   }
}
