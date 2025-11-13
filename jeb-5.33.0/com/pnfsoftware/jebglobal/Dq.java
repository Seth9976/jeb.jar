package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class Dq implements Callable {
   Dq(vV var1, long var2, long var4, byte[] var6) {
      this.wS = var1;
      this.pC = var2;
      this.A = var4;
      this.kS = var6;
   }

   public Long pC() throws Exception {
      return this.wS.A.findBytes(this.pC, this.A, this.kS);
   }
}
