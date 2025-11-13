package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;

class cbr implements bst {
   @Override
   public boolean pC(bsq var1, bsq.Av var2) {
      IDImm var3 = (IDImm)var2.pC(1, IDImm.class);
      IDImm var4 = (IDImm)var2.pC(2, IDImm.class);
      return var3._or(var4._not()).isOnes();
   }
}
