package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;

class bpi implements IDVisitor {
   int pC;

   bpi(bph var1, int var2, IDExpression var3, int var4, Couple[] var5) {
      this.E = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      boolean var4;
      switch (this.A) {
         case 0:
            var4 = var1 == this.kS;
            break;
         case 1:
            var4 = var1.equals(this.kS);
            break;
         case 2:
            var4 = var1.equalsEx(this.kS, false);
            break;
         default:
            return;
      }

      if (var4) {
         if (this.pC >= this.wS) {
            this.UT[0] = new Couple(var2, var1);
            var3.interrupt(true);
         }

         this.pC++;
      }
   }
}
