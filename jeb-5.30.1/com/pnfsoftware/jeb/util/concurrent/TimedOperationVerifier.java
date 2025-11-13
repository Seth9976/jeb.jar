package com.pnfsoftware.jeb.util.concurrent;

import com.pnfsoftware.jeb.util.format.Strings;

public class TimedOperationVerifier {
   public static final TimedOperationVerifier DEFAULT = new TimedOperationVerifier();
   private long t0;
   private long maxExecTime;

   public TimedOperationVerifier(long var1, long var3) {
      this.t0 = var1;
      this.maxExecTime = var3;
   }

   public TimedOperationVerifier(long var1) {
      this(System.currentTimeMillis(), var1);
   }

   public TimedOperationVerifier() {
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
      } else {
         long var1;
         if (this.maxExecTime > 0L && (var1 = System.currentTimeMillis() - this.t0) > this.maxExecTime) {
            throw new OperationTimedOutException(Strings.ff("Self interruption: exectime= %d ms; max= %d ms", var1, this.maxExecTime));
         }
      }
   }

   public static void verify(TimedOperationVerifier var0) {
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
