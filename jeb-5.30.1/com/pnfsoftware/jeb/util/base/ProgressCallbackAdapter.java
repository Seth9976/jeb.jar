package com.pnfsoftware.jeb.util.base;

public class ProgressCallbackAdapter implements IProgressCallback {
   private volatile long total;
   private volatile long current;

   @Override
   public void setTotal(long var1) {
      this.total = var1;
   }

   @Override
   public final long getTotal() {
      return this.total;
   }

   @Override
   public void updateTotal(long var1) {
      if (this.isInitialized()) {
         this.total += var1;
      } else {
         this.total = var1;
      }
   }

   @Override
   public void setCurrent(long var1) {
      this.current = var1;
   }

   @Override
   public final long getCurrent() {
      return this.current;
   }

   @Override
   public long increment() {
      synchronized (this) {
         return ++this.current;
      }
   }

   @Override
   public void message(int var1, String var2) {
   }
}
