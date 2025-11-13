package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Set;

class ccr implements IDVisitor {
   ccr(ccq var1, Set var2) {
      this.RF = var1;
      this.q = var2;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1 instanceof IDCallInfo var4 && var4.getInvokeType() == DInvokeType.STATIC && var4.getCountOfArguments() == 0) {
         String var5 = var4.getMethodSignature();
         if (this.RF.xK.contains(var5)) {
            return;
         }

         String var6 = var5.substring(0, var5.indexOf("->"));
         if (this.q.contains(var6)) {
            IDImm var7 = this.RF.q(var4);
            if (var7 != null && !var7.isRef() && var2.replaceSubExpression(var1, var7.duplicate())) {
               this.RF.RF++;
            }
         }
      }
   }
}
