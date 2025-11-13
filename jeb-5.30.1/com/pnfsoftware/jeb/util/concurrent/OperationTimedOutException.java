package com.pnfsoftware.jeb.util.concurrent;

public class OperationTimedOutException extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public OperationTimedOutException() {
   }

   public OperationTimedOutException(String var1) {
      super(var1);
   }

   public OperationTimedOutException(Throwable var1) {
      super(var1);
   }

   public OperationTimedOutException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
