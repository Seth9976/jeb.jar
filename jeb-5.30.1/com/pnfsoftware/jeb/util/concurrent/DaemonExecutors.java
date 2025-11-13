package com.pnfsoftware.jeb.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DaemonExecutors {
   public static ExecutorService newCachedThreadPool() {
      return Executors.newCachedThreadPool(new DaemonExecutors.DaemonThreadFactory());
   }

   public static ExecutorService newFixedThreadPool(int var0) {
      return Executors.newFixedThreadPool(var0, new DaemonExecutors.DaemonThreadFactory());
   }

   public static ExecutorService newSingleThreadExecutor() {
      return Executors.newSingleThreadExecutor(new DaemonExecutors.DaemonThreadFactory());
   }

   static class DaemonThreadFactory implements ThreadFactory {
      private static final AtomicInteger poolNumber = new AtomicInteger(1);
      private final AtomicInteger threadNumber = new AtomicInteger(1);
      private final String namePrefix = "jeb-daemonpool-" + poolNumber.getAndIncrement() + "-thread-";

      @Override
      public Thread newThread(Runnable var1) {
         return ThreadUtil.create(this.namePrefix + this.threadNumber.getAndIncrement(), var1);
      }
   }
}
