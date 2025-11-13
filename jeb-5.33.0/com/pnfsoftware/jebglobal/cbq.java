package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;

class cbq implements bst {
   @Override
   public boolean pC(bsq var1, bsq.Av var2) {
      IDImm var3 = (IDImm)var2.pC(1, IDImm.class);
      IDImm var4 = (IDImm)var2.pC(2, IDImm.class);
      IDImm var5 = (IDImm)var2.pC(3, IDImm.class);
      return !var4._or(var5._not()).isOnes() ? false : var3._not()._or(var4._xor(var5)._not()).isOnes();
   }
}
