package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class sR implements Callable {
   sR(vV var1, long var2, int var4, int var5) {
      this.wS = var1;
      this.pC = var2;
      this.A = var4;
      this.kS = var5;
   }

   public Integer pC() throws Exception {
      return this.wS.A.check(this.pC, this.A, this.kS);
   }
}
