package com.pnfsoftware.jeb.util.concurrent;

class ProcessWrapper$2 implements Runnable {
   ProcessWrapper$2(ProcessWrapper var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      try {
         Thread.sleep(this.this$0.timeout);
      } catch (InterruptedException var1) {
      }

      this.this$0.kill();
   }
}
