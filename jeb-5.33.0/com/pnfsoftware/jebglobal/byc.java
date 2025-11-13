package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;

public class byc extends bwi {
   public byc() {
      this.A = true;
   }

   @Override
   protected brn pC(IDExpression var1) {
      if (var1 instanceof IDOperation && bqr.pC(var1)) {
         bqr var2 = new bqr(this.ctx);
         if (!var2.A(var1)) {
            return null;
         } else {
            IDExpression var3 = var2.A();
            if (var3 == null) {
               return null;
            } else {
               boolean var4 = false;

               for (IDExpression var6 : var2.pC()) {
                  if (DUtil.hasVariables(var6)) {
                     var4 = true;
                     break;
                  }
               }

               return brn.pC(var3, var4);
            }
         }
      } else {
         return null;
      }
   }
}
