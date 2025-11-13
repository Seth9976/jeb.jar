package com.pnfsoftware.jeb.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtil {
   private static final AtomicInteger anonThreadCounter = new AtomicInteger();

   public static Thread start(Runnable var0) {
      String var1 = "jeb-anonymous-thread-" + anonThreadCounter.getAndIncrement();
      return start(var1, var0);
   }

   public static Thread start(String var0, Runnable var1) {
      Thread var2 = create(var0, var1);
      var2.start();
      return var2;
   }

   public static Thread create(Runnable var0) {
      String var1 = "jeb-anonymous-thread-" + anonThreadCounter.getAndIncrement();
      return create(var1, var0);
   }

   public static Thread create(String var0, Runnable var1) {
      Thread var2 = new Thread(var1);
      if (var0 == null) {
         var0 = "jeb-anonymous-thread-" + anonThreadCounter.getAndIncrement();
      }

      var2.setName(var0);
      var2.setDaemon(true);
      return var2;
   }

   public static boolean stop(Thread var0) {
      if (var0 == null) {
         return false;
      } else if (!var0.isAlive()) {
         return true;
      } else {
         var0.interrupt();

         try {
            var0.join();
         } catch (InterruptedException var1) {
         }

         return !var0.isAlive();
      }
   }

   public static boolean monitor(Thread var0, IMonitorInfoProvider var1) {
      long var2 = System.currentTimeMillis();

      while (true) {
         try {
            long var4 = var1.getProbingPeriod();
            if (var4 <= 0L) {
               var4 = 100L;
            }

            var0.join(var4);
         } catch (InterruptedException var9) {
            break;
         }

         if (!var0.isAlive()) {
            break;
         }

         long var10 = var1.getTimeout();
         if (var10 > 0L) {
            long var6 = System.currentTimeMillis() - var2;
            if (var6 >= var10) {
               var1.onInterrupt();
               if (var0.isAlive()) {
                  var0.interrupt();

                  try {
                     var0.join();
                  } catch (InterruptedException var8) {
                  }
               }
               break;
            }
         }
      }

      return !var0.isAlive();
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static void joinU(Thread var0) {
      boolean var1 = false;

      while (true) {
         boolean var5 = false /* VF: Semaphore variable */;

         try {
            var5 = true;
            var0.join();
            var5 = false;
            break;
         } catch (InterruptedException var6) {
            var5 = false;
         } finally {
            if (var5) {
               if (var1) {
                  Thread.currentThread().interrupt();
               }
            }
         }

         var1 = true;
      }

      if (var1) {
         Thread.currentThread().interrupt();
      }
   }

   public static void interruptAndJoinUninterruptibly(Thread var0) {
      var0.interrupt();
      joinU(var0);
   }

   public static String formatStackTrace() {
      return formatStackTrace(0);
   }

   public static String formatStackTrace(int var0) {
      return formatStackTrace(var0, null, false, false);
   }

   public static String formatStackTrace(int var0, String var1, boolean var2, boolean var3) {
      StringBuilder var4 = new StringBuilder();
      StackTraceElement[] var5 = Thread.currentThread().getStackTrace();
      int var6 = 2 + var0;
      if (var1 == null) {
         var1 = " < ";
      }

      int var7 = 0;

      for (int var8 = var6; var8 < var5.length; var8++) {
         StackTraceElement var9 = var5[var8];
         if (var7 > 0) {
            var4.append(var1);
         }

         String var10 = var9.getClassName();
         if (!var2) {
            int var11 = var10.lastIndexOf(46);
            if (var11 >= 0) {
               var10 = var10.substring(var11 + 1);
            }
         }

         var4.append(var10).append('.').append(var9.getMethodName());
         if (var3) {
            var4.append(':').append(var9.getLineNumber());
         }

         var7++;
      }

      return var4.toString();
   }
}
