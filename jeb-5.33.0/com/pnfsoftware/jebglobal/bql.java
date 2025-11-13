package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import java.util.Set;

class bql implements IDVisitor {
   bql(bqj var1, int var2, Set var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      if (var1.isVar(this.pC)) {
         if (var2 instanceof IDInstanceField) {
            if (!var2.asInstanceField().isArrayLength()) {
               var3.interrupt(false);
            }
         } else {
            if (var2 instanceof IDCallInfo) {
               IDCallInfo var4 = var2.asCallInfo();
               String var5 = var4.getMethodSignature();
               if (bqj.A.contains(var5)) {
                  return;
               }

               this.A.add(this.pC);
            } else if (!(var2 instanceof IDArrayElt)) {
               this.A.add(this.pC);
            }
         }
      }
   }
}
