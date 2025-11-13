package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;

class bxr implements IDVisitor {
   bxr(bxp.Av var1, btj var2, btp var3, boolean var4, ccw.Av var5) {
      this.UT = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDInvokeInfo && var1.getPhysicalMethodIndex() == -1) {
         int var4 = var1.getPhysicalOffset();
         Couple var5 = this.pC.pC(var4);
         if (var5 != null) {
            Object var6 = var5.getSecond();
            Object[] var10000 = new Object[]{var1, var6};
            if (this.UT.wS.pC(this.A, this.kS, var6, var1, var2, var3, this.wS, this.UT.pC)) {
               this.UT.kS++;
            }
         }
      }
   }
}
