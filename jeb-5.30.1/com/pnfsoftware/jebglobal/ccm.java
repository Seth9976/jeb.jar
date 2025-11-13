package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Triple;
import java.util.List;

class ccm implements IDVisitor {
   ccm(ccl var1, IDVar var2, List var3, IJavaType var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 == this.q) {
         if (var2 instanceof IDOperation var4
            && var4.getOperatorType().isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)
            && var4.getOperand1() == this.q
            && var4.getOperand2().isConstantImm()) {
            IDImm var5 = var4.getOperand2().asImm();
            int var6 = (int)var5.getRawValue();
            if (var6 == 0 || var6 == 1) {
               this.RF.add(new Triple(var4, var5, var5.duplicateWithDifferentType(this.xK)));
               return;
            }
         }

         var3.interrupt(false);
      }
   }
}
