package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

class che implements IDVisitor {
   che(chd var1) {
      this.q = var1;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo && "Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;".equals(((IDCallInfo)var1).getMethodSignature())) {
         IDExpression var4 = ((IDCallInfo)var1).getArgument(0);
         boolean var5 = false;
         if (var4 instanceof IDImm && ((IDImm)var4).isString()) {
            var5 = true;
         } else if (var4 instanceof IDOperation && ((IDOperation)var4).getOperator().is(JavaOperatorType.CONCAT)) {
            var5 = true;
         }

         if (var4 instanceof IDCallInfo && ((IDCallInfo)var4).getMethodSignature().endsWith(")Ljava/lang/String;")) {
            var5 = true;
         }

         if (var5 && var2.replaceSubExpression(var1, var4)) {
            this.q.RF++;
         }
      }
   }
}
