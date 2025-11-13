package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class cbb implements IDVisitor {
   int q;

   cbb(caz var1) {
      this.RF = var1;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDStaticField) {
         boolean var4 = false;
         if (var1.equalsEx(this.RF.JY, false)) {
            var4 = var2.replaceSubExpression(var1, this.RF.Uv.createInt(0));
         } else if (var1.equalsEx(this.RF.HF, false)) {
            var4 = var2.replaceSubExpression(var1, this.RF.Uv.createInt(1));
         }

         if (var4) {
            this.q++;
         }
      }
   }
}
