package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import java.util.concurrent.Callable;

class ym implements Callable {
   ym(ia var1, String var2, int var3, ICodeUnit var4, boolean var5) {
      this.UT = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
      this.wS = var5;
   }

   public xa pC() throws Exception {
      return this.UT.A(this.pC, this.A, this.kS, this.wS);
   }
}
