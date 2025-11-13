package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.Set;

class cac implements IDVisitor {
   int pC;
   Set A;

   cac(cab var1) {
      this.kS = var1;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.checkType(this.kS.tf.getBoolean()) && var1 instanceof IDOperation var4) {
         JavaOperatorType var5 = var4.getOperatorType();
         if (var5.isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)
            && var4.getOperand1() instanceof IDVar var6
            && this.A.contains(var6.getId())
            && var4.getOperand2() instanceof IDImm var9
            && var9.isZero()) {
            IDImm var10 = this.kS.ctx.createBoolean(var5 == JavaOperatorType.NE);
            if (var2.replaceSubExpression(var1, var10)) {
               this.pC++;
            }
         }
      }
   }
}
