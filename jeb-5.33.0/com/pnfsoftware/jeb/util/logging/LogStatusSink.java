package com.pnfsoftware.jeb.util.logging;

public class LogStatusSink {
   boolean fresh;
   CharSequence status;

   public synchronized void update(CharSequence var1) {
      this.status = var1;
      this.fresh = true;
   }

   public synchronized String retrieve() {
      if (!this.fresh) {
         return null;
      } else {
         this.fresh = false;
         return this.status.toString();
      }
   }
}
