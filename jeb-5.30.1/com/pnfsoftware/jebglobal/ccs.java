package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;

public class ccs extends cax {
   public ccs() {
      this.RF = true;
   }

   @Override
   protected bvx q(IDExpression var1) {
      if (var1 instanceof IDOperation && buy.q(var1)) {
         buy var2 = new buy(this.ctx);
         if (!var2.RF(var1)) {
            return null;
         } else {
            IDExpression var3 = var2.Dw();
            if (var3 == null) {
               return null;
            } else {
               boolean var4 = false;

               for (IDExpression var6 : var2.xK()) {
                  if (DUtil.hasVariables(var6)) {
                     var4 = true;
                     break;
                  }
               }

               return bvx.q(var3, var4);
            }
         }
      } else {
         return null;
      }
   }
}
