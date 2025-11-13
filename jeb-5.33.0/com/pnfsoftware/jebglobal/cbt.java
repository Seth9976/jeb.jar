package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;

class cbt implements bst {
   @Override
   public boolean pC(bsq var1, bsq.Av var2) {
      IDImm var3 = (IDImm)var2.pC(1, IDImm.class);
      IDImm var4 = (IDImm)var2.pC(2, IDImm.class);
      IDImm var5 = (IDImm)var2.pC(3, IDImm.class);
      if (!var3._and(var4)._not().isOnes()) {
         return false;
      } else if (!var3._or(var4)._and(var5)._not().isOnes()) {
         return false;
      } else {
         var2.kS().put(4, var3._or(var4)._or(var5));
         return true;
      }
   }
}
