package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;

class ccg implements IDVisitor {
   ccg(cce.eo var1, bxy var2, bye var3, boolean var4, chs.eo var5) {
      this.Uv = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDInvokeInfo && var1.getPhysicalMethodIndex() == -1) {
         int var4 = var1.getPhysicalOffset();
         Couple var5 = this.q.q(var4);
         if (var5 != null) {
            Object var6 = var5.getSecond();
            Object[] var10000 = new Object[]{var1, var6};
            if (this.Uv.Dw.q(this.RF, this.xK, var6, var1, var2, var3, this.Dw, this.Uv.q)) {
               this.Uv.xK++;
            }
         }
      }
   }
}
