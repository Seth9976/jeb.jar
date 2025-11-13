package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class btm implements IDVisitor {
   int q;

   btm(btk var1, Class var2, int var3, IDExpression[] var4) {
      this.Uv = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (this.RF.isInstance(var1)) {
         if (this.q >= this.xK) {
            this.Dw[0] = var1;
            var3.interrupt(true);
         }

         this.q++;
      }
   }
}
