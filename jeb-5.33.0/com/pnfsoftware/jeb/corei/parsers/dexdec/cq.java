package com.pnfsoftware.jeb.corei.parsers.dexdec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class cq extends RC {
   AtomicInteger pC;

   cq(Ws var1, int var2, String var3, int var4) {
      super(var2);
      this.wS = var1;
      this.A = var3;
      this.kS = var4;
      this.pC = new AtomicInteger();
   }

   public ExecutorService pC() {
      String var1 = this.A + this.pC.getAndIncrement() + "-";
      return Executors.newFixedThreadPool(this.kS, new DH(this, var1));
   }

   public void pC(ExecutorService var1) {
      var1.shutdownNow();
   }
}
