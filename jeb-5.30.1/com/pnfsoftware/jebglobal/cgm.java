package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;

class cgm implements bxh {
   @Override
   public boolean q(bxe var1, bxe.eo var2) {
      IDImm var3 = (IDImm)var2.q(1, IDImm.class);
      IDImm var4 = (IDImm)var2.q(2, IDImm.class);
      IDImm var5 = (IDImm)var2.q(3, IDImm.class);
      return !var4._or(var5._not()).isOnes() ? false : var3._not()._or(var4._xor(var5)._not()).isOnes();
   }
}
