package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;

class cgn implements bxh {
   @Override
   public boolean q(bxe var1, bxe.eo var2) {
      IDImm var3 = (IDImm)var2.q(1, IDImm.class);
      IDImm var4 = (IDImm)var2.q(2, IDImm.class);
      return var3._or(var4._not()).isOnes();
   }
}
