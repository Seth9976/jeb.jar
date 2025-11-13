package com.pnfsoftware.jeb.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public final class ThreadEx extends Thread {
   private Runnable runnable;
   private Callable callable;
   private Exception ex;
   private Object result;

   public ThreadEx(Runnable var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide a non-null Runnable object");
      } else {
         this.runnable = var1;
      }
   }

   public ThreadEx(Callable var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide a non-null Callable object");
      } else {
         this.callable = var1;
      }
   }

   @Override
   public final void run() {
      try {
         if (this.runnable != null) {
            this.runnable.run();
         } else {
            this.result = this.callable.call();
         }
      } catch (Exception var2) {
         this.ex = var2;
      }
   }

   public Object get() throws ExecutionException, InterruptedException {
      try {
         return this.get(0L);
      } catch (TimeoutException var2) {
         throw new RuntimeException("Should not happen", var2);
      }
   }

   public Object get(long var1) throws ExecutionException, InterruptedException, TimeoutException {
      this.join(var1);
      if (this.ex != null) {
         throw new ExecutionException(this.ex);
      } else if (var1 != 0L && this.isAlive()) {
         throw new TimeoutException();
      } else {
         return this.result;
      }
   }
}
