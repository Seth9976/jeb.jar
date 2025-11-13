package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.ArrayList;

class cbb implements IDVisitor {
   int pC;

   cbb(caz var1, IDVar var2, ArrayList var3) {
      this.wS = var1;
      this.A = var2;
      this.kS = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDArrayElt var4 && var4.getArray().equals(this.A) && var4.getIndex() instanceof IDImm var5) {
         int var8 = (int)var5.getRawValue();
         if (var8 >= 0 && var8 < this.kS.size()) {
            IDImm var7 = this.wS.g.createConstant(((Integer)this.kS.get(var8)).intValue(), this.wS.tf.getInt());
            if (var2.replaceSubExpression(var1, var7)) {
               this.pC++;
            }
         }
      }
   }
}
