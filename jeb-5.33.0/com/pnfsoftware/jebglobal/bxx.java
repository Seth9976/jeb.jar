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

class bxx implements IDVisitor {
   bxx(bxw var1, IDVar var2, List var3, IJavaType var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 == this.pC) {
         if (var2 instanceof IDOperation var4
            && var4.getOperatorType().isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)
            && var4.getOperand1() == this.pC
            && var4.getOperand2().isConstantImm()) {
            IDImm var5 = var4.getOperand2().asImm();
            int var6 = (int)var5.getRawValue();
            if (var6 == 0 || var6 == 1) {
               this.A.add(new Triple(var4, var5, var5.duplicateWithDifferentType(this.kS)));
               return;
            }
         }

         var3.interrupt(false);
      }
   }
}
