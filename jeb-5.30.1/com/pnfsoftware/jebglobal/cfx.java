package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.ArrayList;

class cfx implements IDVisitor {
   int q;

   cfx(cfv var1, IDVar var2, ArrayList var3) {
      this.Dw = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDArrayElt var4 && var4.getArray().equals(this.RF) && var4.getIndex() instanceof IDImm var5) {
         int var8 = (int)var5.getRawValue();
         if (var8 >= 0 && var8 < this.xK.size()) {
            IDImm var7 = this.Dw.g.createConstant(((Integer)this.xK.get(var8)).intValue(), this.Dw.tf.getInt());
            if (var2.replaceSubExpression(var1, var7)) {
               this.q++;
            }
         }
      }
   }
}
