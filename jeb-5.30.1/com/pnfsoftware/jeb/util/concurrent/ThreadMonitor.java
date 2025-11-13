package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class ThreadMonitor {
   private static final ILogger logger = GlobalLog.getLogger(ThreadMonitor.class);
   private Thread thread;
   private boolean started;
   private IMonitorInfoProvider moninfo;
   private Runnable before;
   private Runnable after;
   private ThreadMonitor.State state;
   private boolean success;

   public ThreadMonitor(Thread var1, boolean var2, IMonitorInfoProvider var3, Runnable var4, Runnable var5) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.thread = var1;
         this.started = var2;
         this.moninfo = var3;
         this.before = var4;
         this.after = var5;
         this.state = ThreadMonitor.State.READY;
      }
   }

   public Thread getMonitoredThread() {
      return this.thread;
   }

   public synchronized ThreadMonitor.State getState() {
      return this.state;
   }

   private synchronized void setState(ThreadMonitor.State var1) {
      this.state = var1;
   }

   public synchronized boolean isReady() {
      return this.state == ThreadMonitor.State.READY;
   }

   public synchronized boolean isMonitoring() {
      return this.state == ThreadMonitor.State.MONITORING;
   }

   public synchronized boolean isTerminated() {
      return this.state == ThreadMonitor.State.DONE || this.state == ThreadMonitor.State.ERROR;
   }

   public synchronized Thread start(boolean var1) {
      if (this.state != ThreadMonitor.State.READY) {
         throw new RuntimeException();
      } else {
         this.setState(ThreadMonitor.State.MONITORING);
         Thread var2 = ThreadUtil.start(new ThreadMonitor$1(this, var1));
         if (var1) {
            try {
               var2.join();
               this.setState(this.success ? ThreadMonitor.State.DONE : ThreadMonitor.State.ERROR);
            } catch (InterruptedException var3) {
               this.setState(ThreadMonitor.State.ERROR);
            }
         }

         return var2;
      }
   }

   public static enum State {
      READY,
      MONITORING,
      DONE,
      ERROR;
   }
}
