package com.pnfsoftware.jebglobal;

class EE implements Runnable {
   EE(tw var1) {
      this.q = var1;
   }

   @Override
   public void run() {
      while (true) {
         this.q.xK.setLastModified(System.currentTimeMillis());

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var1) {
            return;
         }
      }
   }
}
