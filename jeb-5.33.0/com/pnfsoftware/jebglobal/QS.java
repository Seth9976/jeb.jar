package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class QS implements Callable {
   QS(ia var1, long var2, int var4, byte[] var5, int var6) {
      this.UT = var1;
      this.pC = var2;
      this.A = var4;
      this.kS = var5;
      this.wS = var6;
   }

   public Integer pC() throws Exception {
      return this.UT.A(this.pC, this.A, this.kS, this.wS);
   }
}
