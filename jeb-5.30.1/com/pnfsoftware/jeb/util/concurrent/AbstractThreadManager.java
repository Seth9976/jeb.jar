package com.pnfsoftware.jeb.util.concurrent;

public abstract class AbstractThreadManager {
   public abstract Thread create(Runnable var1);

   public Thread start(Runnable var1) {
      return this.start(null, var1);
   }

   public Thread start(String var1, Runnable var2) {
      Thread var3 = this.create(var2);
      if (var1 != null) {
         var3.setName(var1);
      }

      var3.start();
      return var3;
   }

   public boolean stop(Thread var1) {
      if (var1 == null) {
         return false;
      } else if (!var1.isAlive()) {
         return true;
      } else {
         var1.interrupt();

         try {
            var1.join();
         } catch (InterruptedException var2) {
         }

         return !var1.isAlive();
      }
   }

   public boolean monitor(Thread var1, IMonitorInfoProvider var2) {
      long var3 = System.currentTimeMillis();

      while (true) {
         try {
            long var5 = var2.getProbingPeriod();
            if (var5 <= 0L) {
               var5 = 100L;
            }

            var1.join(var5);
         } catch (InterruptedException var10) {
            break;
         }

         if (!var1.isAlive()) {
            break;
         }

         long var11 = var2.getTimeout();
         if (var11 > 0L) {
            long var7 = System.currentTimeMillis() - var3;
            if (var7 >= var11) {
               var2.onInterrupt();
               var1.interrupt();

               try {
                  var1.join();
               } catch (InterruptedException var9) {
               }
               break;
            }
         }
      }

      return !var1.isAlive();
   }
}
