package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.base.Couple;

class btl implements IDVisitor {
   int q;

   btl(btk var1, int var2, IDExpression var3, int var4, Couple[] var5) {
      this.oW = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      boolean var4;
      switch (this.RF) {
         case 0:
            var4 = var1 == this.xK;
            break;
         case 1:
            var4 = var1.equals(this.xK);
            break;
         case 2:
            var4 = var1.equalsEx(this.xK, false);
            break;
         default:
            return;
      }

      if (var4) {
         if (this.q >= this.Dw) {
            this.Uv[0] = new Couple(var2, var1);
            var3.interrupt(true);
         }

         this.q++;
      }
   }
}
