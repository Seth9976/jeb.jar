package com.pnfsoftware.jeb.corei.parsers.dexdec;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

class iZ implements ThreadFactory {
   AtomicInteger q;

   iZ(Nt var1, String var2) {
      this.xK = var1;
      this.RF = var2;
      this.q = new AtomicInteger();
   }

   @Override
   public Thread newThread(Runnable var1) {
      Thread var2 = new Thread(var1);
      var2.setDaemon(true);
      var2.setName(this.RF + this.q.getAndIncrement());
      return var2;
   }
}
