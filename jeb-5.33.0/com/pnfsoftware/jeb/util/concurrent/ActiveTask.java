package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public abstract class ActiveTask implements Runnable {
   private static final ILogger logger = GlobalLog.getLogger(ActiveTask.class);
   @SerTransient
   private volatile Thread thread;
   @SerTransient
   private volatile Runnable completion;
   @SerTransient
   private volatile boolean cancelRequested;
   @SerTransient
   private volatile String name;

   public ActiveTask(String var1) {
      this.name = var1;
   }

   public synchronized boolean start(Runnable var1) {
      if (this.thread != null && this.thread.isAlive()) {
         return false;
      } else {
         this.completion = var1;
         this.thread = ThreadUtil.start(this.name, this);
         return true;
      }
   }

   public synchronized boolean interrupt() {
      if (this.thread != null && this.thread.isAlive()) {
         this.thread.interrupt();
         return true;
      } else {
         return false;
      }
   }

   public synchronized boolean cancel() {
      return this.cancel(true);
   }

   public synchronized boolean cancel(boolean var1) {
      this.cancelRequested = true;
      if (this.thread != null && this.thread.isAlive()) {
         if (var1) {
            this.thread.interrupt();
         }

         return true;
      } else {
         return false;
      }
   }

   public synchronized boolean isDone() {
      return this.thread == null || !this.thread.isAlive();
   }

   public final boolean isCancelled() {
      return this.cancelRequested;
   }

   public synchronized boolean join() throws InterruptedException {
      return this.join(0L);
   }

   public synchronized boolean join(long var1) throws InterruptedException {
      if (this.thread != null && this.thread.isAlive()) {
         this.thread.join(var1);
      }

      return true;
   }

   @Override
   public void run() {
      try {
         this.onPreExecution();
         this.runi();
         this.onPostExecution();
      } catch (Exception var26) {
         if (this.thread == null) {
            throw var26;
         }

         this.onException(var26);
      } finally {
         if (this.completion != null) {
            try {
               this.completion.run();
            } finally {
               this.completion = null;
            }
         }
      }
   }

   public abstract void runi();

   protected void onPreExecution() {
   }

   protected void onPostExecution() {
   }

   protected void onException(Exception var1) {
      logger.catching(var1);
   }
}
