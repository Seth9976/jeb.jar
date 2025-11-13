package com.pnfsoftware.jeb.util.concurrent;

public class MonitorInfoAdapter implements IMonitorInfoProvider {
   private long timeout;
   private long probingPeriod;

   public MonitorInfoAdapter(long var1, long var3) {
      this.timeout = var1;
      this.probingPeriod = var3;
   }

   @Override
   public void setTimeout(long var1) {
      this.timeout = var1;
   }

   @Override
   public long getTimeout() {
      return this.timeout;
   }

   @Override
   public void setProbingPeriod(long var1) {
      this.probingPeriod = var1;
   }

   @Override
   public long getProbingPeriod() {
      return this.probingPeriod;
   }

   @Override
   public void onInterrupt() {
   }
}
