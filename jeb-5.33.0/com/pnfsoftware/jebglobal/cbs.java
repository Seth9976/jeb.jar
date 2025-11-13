package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;

class cbs implements bst {
   @Override
   public boolean pC(bsq var1, bsq.Av var2) {
      IDImm var3 = (IDImm)var2.pC(1, IDImm.class);
      IDImm var4 = (IDImm)var2.pC(2, IDImm.class);
      IDImm var5 = (IDImm)var2.pC(3, IDImm.class);
      if (!var4._and(var5)._not().isOnes()) {
         return false;
      } else if (!var3._not()._or(var4._not()._and(var5)).isOnes()) {
         return false;
      } else {
         var2.kS().put(4, var4._add(var5));
         return true;
      }
   }
}
