package com.pnfsoftware.jebglobal;

class zp implements Runnable {
   zp(rQ var1) {
      this.pC = var1;
   }

   @Override
   public void run() {
      while (true) {
         this.pC.kS.setLastModified(System.currentTimeMillis());

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var1) {
            return;
         }
      }
   }
}
