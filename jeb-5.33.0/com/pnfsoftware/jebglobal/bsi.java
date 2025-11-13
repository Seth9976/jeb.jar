package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class bsi implements IDVisitor {
   bsi(bsh var1, brm var2, BasicBlock var3, int var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo var4) {
         if (!this.wS.pC(var4)) {
            return;
         }

         if (!this.pC.pC(this.A, this.kS, var4)) {
            if (var4.hasSideEffects(this.wS.pC, false)) {
               var3.interrupt(true);
            }

            return;
         }

         var3.interrupt(false);
      }
   }
}
