package com.pnfsoftware.jebglobal;

import java.util.concurrent.ThreadFactory;

class bvp implements ThreadFactory {
   bvp(bvo var1, ThreadGroup var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public Thread newThread(Runnable var1) {
      Thread var2 = this.pC != null ? new Thread(this.pC, var1) : new Thread(var1);
      var2.setDaemon(true);
      var2.setName(bvo.NS + bvo.Sc.getAndIncrement());
      var2.setContextClassLoader(this.A.rl);
      return var2;
   }
}
