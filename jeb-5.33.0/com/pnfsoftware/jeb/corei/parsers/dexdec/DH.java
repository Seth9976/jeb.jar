package com.pnfsoftware.jeb.corei.parsers.dexdec;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class DH implements ThreadFactory {
   AtomicInteger pC;

   DH(cq var1, String var2) {
      this.kS = var1;
      this.A = var2;
      this.pC = new AtomicInteger();
   }

   @Override
   public Thread newThread(Runnable var1) {
      Thread var2 = new Thread(var1);
      var2.setDaemon(true);
      var2.setName(this.A + this.pC.getAndIncrement());
      return var2;
   }
}
