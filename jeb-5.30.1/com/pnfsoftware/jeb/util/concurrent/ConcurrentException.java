package com.pnfsoftware.jeb.util.concurrent;

public class ConcurrentException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public ConcurrentException() {
   }

   public ConcurrentException(String var1) {
      super(var1);
   }

   public ConcurrentException(Throwable var1) {
      super(var1);
   }

   public ConcurrentException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
