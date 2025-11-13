package com.pnfsoftware.jeb.util.logging;

public class StatusLogDrip {
   ILogger logger;
   long period;
   long lastSendTs;

   public StatusLogDrip(long var1, ILogger var3) {
      if (var1 <= 0L) {
         throw new IllegalArgumentException();
      } else {
         this.period = var1;
         if (var3 == null) {
            var3 = GlobalLog.getLogger();
         }

         this.logger = var3;
      }
   }

   public boolean canSend() {
      long var1 = System.currentTimeMillis();
      return this.lastSendTs == 0L || var1 - this.lastSendTs >= this.period;
   }

   public boolean log(String var1, Object... var2) {
      long var3 = System.currentTimeMillis();
      if (this.lastSendTs != 0L && var3 - this.lastSendTs < this.period) {
         return false;
      } else {
         GlobalLog.status(var1, var2);
         this.lastSendTs = var3;
         return true;
      }
   }

   public void forceLog(String var1, Object... var2) {
      long var3 = System.currentTimeMillis();
      GlobalLog.status(var1, var2);
      this.lastSendTs = var3;
   }
}
