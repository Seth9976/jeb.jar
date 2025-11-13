package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Set;

class bur implements IDVisitor {
   bur(bup var1, int var2, Set var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.isVar(this.q)) {
         if (var2 instanceof IDInstanceField) {
            if (!var2.asInstanceField().isArrayLength()) {
               var3.interrupt(false);
            }
         } else {
            if (var2 instanceof IDCallInfo) {
               IDCallInfo var4 = var2.asCallInfo();
               String var5 = var4.getMethodSignature();
               if (bup.RF.contains(var5)) {
                  return;
               }

               this.RF.add(this.q);
            } else if (!(var2 instanceof IDArrayElt)) {
               this.RF.add(this.q);
            }
         }
      }
   }
}
