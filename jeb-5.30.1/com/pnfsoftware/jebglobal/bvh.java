package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.HashMap;

class bvh implements IDVisitor {
   bvh(bvf var1, IDVar var2, HashMap var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDInstanceField var4 && var4.getInstance() == this.q) {
         int var5 = var4.getIndex().getValue();
         IDVar var6 = (IDVar)this.RF.get(var5);
         if (var6 == null) {
            var6 = this.xK.Uv.createVirtualVar(var4.getType());
            this.RF.put(var5, var6);
         }

         if (!var2.replaceSubExpression(var1, var6)) {
            var3.interrupt(false);
         }
      }
   }
}
