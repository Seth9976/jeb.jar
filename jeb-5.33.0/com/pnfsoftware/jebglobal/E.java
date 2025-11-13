package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class E implements Callable {
   E(vV var1, long var2, int var4, byte[] var5, int var6, boolean var7) {
      this.E = var1;
      this.pC = var2;
      this.A = var4;
      this.kS = var5;
      this.wS = var6;
      this.UT = var7;
   }

   public Integer pC() throws Exception {
      return this.E.A.write(this.pC, this.A, this.kS, this.wS, this.UT);
   }
}
