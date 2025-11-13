package com.pnfsoftware.jeb.util.events;

class AggregatorDispatcher$1 implements Runnable {
   AggregatorDispatcher$1(AggregatorDispatcher var1) {
      this.this$0 = var1;
   }

   @Override
   public void run() {
      while (true) {
         try {
            synchronized (this.this$0.list) {
               while (this.this$0.list.isEmpty()) {
                  this.this$0.list.wait();
               }
            }

            Thread.sleep(this.this$0.resolution);
            this.this$0.trigger();
         } catch (InterruptedException var4) {
         }
      }
   }
}
