package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

class ccz implements IDVisitor {
   ccz(ccy var1) {
      this.q = var1;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo var4 && var4.getCountOfArguments() == 1 && var4.getArgument(0) instanceof IDImm) {
         IDImm var5 = null;
         String var6 = var4.getMethodSignature();
         if (var6 != null) {
            if (var6.equals("Ljava/lang/Double;->longBitsToDouble(J)D")) {
               IDImm var7 = (IDImm)var4.getArgument(0);
               var5 = this.q.g.createConstant(var7.getRawValue(), this.q.tf.getDouble());
            } else if (var6.equals("Ljava/lang/Float;->intBitsToFloat(I)F")) {
               IDImm var8 = (IDImm)var4.getArgument(0);
               var5 = this.q.g.createConstant(var8.getRawValue() & 4294967295L, this.q.tf.getFloat());
            }
         }

         if (var5 != null && var2.replaceSubExpression(var1, var5)) {
            var3.setReplacedNode(var5);
            this.q.q++;
         }
      }
   }
}
