package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.format.Strings;

public class Watchdog {
   private static final Watchdog DEFAULT = new Watchdog();
   private long t0;
   private long maxExecTime;

   public Watchdog(long var1, long var3) {
      this.t0 = var1;
      this.maxExecTime = var3;
   }

   public Watchdog(long var1) {
      this(System.currentTimeMillis(), var1);
   }

   public Watchdog() {
      this(0L);
   }

   public long getInitTime() {
      return this.t0;
   }

   public long getMaxExecTime() {
      return this.maxExecTime;
   }

   public void verify() {
      if (Thread.interrupted()) {
         throw new OperationTimedOutException("External interruption");
      } else if (this.maxExecTime > 0L && System.currentTimeMillis() - this.t0 > this.maxExecTime) {
         String var1 = Strings.ff("Self interruption (operation timed-out, maximum duration allowed was %d ms)", this.maxExecTime);
         throw new OperationTimedOutException(var1);
      }
   }

   public static void verify(Watchdog var0) {
      if (var0 == null) {
         var0 = DEFAULT;
      }

      var0.verify();
   }

   public static void verifyInterruptions() {
      verify(null);
   }

   public static void reportInterruption(InterruptedException var0) {
      throw new OperationTimedOutException(var0);
   }
}
