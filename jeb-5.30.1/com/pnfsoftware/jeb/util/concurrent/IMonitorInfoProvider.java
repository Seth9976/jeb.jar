package com.pnfsoftware.jeb.util.concurrent;

public interface IMonitorInfoProvider {
   void setTimeout(long var1);

   long getTimeout();

   void setProbingPeriod(long var1);

   long getProbingPeriod();

   void onInterrupt();
}
