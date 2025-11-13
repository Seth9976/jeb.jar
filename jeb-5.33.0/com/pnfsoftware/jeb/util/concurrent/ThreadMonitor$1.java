package com.pnfsoftware.jeb.util.concurrent;

class ThreadMonitor$1 implements Runnable {
   ThreadMonitor$1(ThreadMonitor var1, boolean var2) {
      this.this$0 = var1;
      this.val$blocking = var2;
   }

   @Override
   public void run() {
      if (this.this$0.before != null) {
         this.this$0.before.run();
      }

      if (!this.this$0.started) {
         this.this$0.thread.start();
      }

      Object[] var10000 = new Object[0];
      this.this$0.success = ThreadUtil.monitor(this.this$0.thread, this.this$0.moninfo);
      var10000 = new Object[]{this.this$0.success};
      if (this.this$0.after != null) {
         this.this$0.after.run();
      }

      if (!this.val$blocking) {
         this.this$0.setState(this.this$0.success ? ThreadMonitor.State.DONE : ThreadMonitor.State.ERROR);
      }
   }
}
