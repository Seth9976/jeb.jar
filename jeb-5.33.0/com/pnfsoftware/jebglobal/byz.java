package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;

class byz extends bsh {
   byz(byy var1, IDMethodContext var2) {
      super(var2);
      this.wS = var1;
   }

   @Override
   protected boolean pC(IDCallInfo var1) {
      bqh var2 = bqh.pC(var1.getMethodSignature(), this.wS.g);
      if (var2 == bqh.pC) {
         return false;
      } else if (var1.getInvokeType() != DInvokeType.STATIC) {
         return false;
      } else {
         String var3 = var1.getMethodSignature();
         if (var3.endsWith(byy.pC)) {
            IDExpression var4 = var1.getArgument(1);
            IDExpression var5 = var1.getArgument(2);
            IDExpression var6 = var1.getArgument(3);
            return var4.isConstantImm() && var5.isConstantImm() && !var6.isConstantImm();
         } else {
            return false;
         }
      }
   }
}
