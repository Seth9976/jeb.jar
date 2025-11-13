package com.pnfsoftware.jeb.corei.parsers.dexdec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

class Nt extends vb {
   AtomicInteger q;

   Nt(ej var1, int var2, String var3, int var4) {
      super(var2);
      this.Dw = var1;
      this.RF = var3;
      this.xK = var4;
      this.q = new AtomicInteger();
   }

   public ExecutorService q() {
      String var1 = this.RF + this.q.getAndIncrement() + "-";
      return Executors.newFixedThreadPool(this.xK, new iZ(this, var1));
   }

   public void q(ExecutorService var1) {
      var1.shutdownNow();
   }
}
