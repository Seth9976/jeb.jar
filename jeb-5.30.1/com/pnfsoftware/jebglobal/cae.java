package com.pnfsoftware.jebglobal;

import java.util.concurrent.ThreadFactory;

class cae implements ThreadFactory {
   cae(cad var1, ThreadGroup var2) {
      this.RF = var1;
      this.q = var2;
   }

   @Override
   public Thread newThread(Runnable var1) {
      Thread var2 = this.q != null ? new Thread(this.q, var1) : new Thread(var1);
      var2.setDaemon(true);
      var2.setName(cad.HF + cad.Hk.getAndIncrement());
      var2.setContextClassLoader(this.RF.KT);
      return var2;
   }
}
