package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import java.util.concurrent.Callable;

class rI implements Callable {
   rI(ia var1, ICodeUnit var2, String var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public xa pC() throws Exception {
      long var1 = this.kS.UT.pC(this.pC, this.A);
      return this.kS.ys.pC(var1);
   }
}
