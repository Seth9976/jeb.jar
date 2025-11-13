package com.pnfsoftware.jeb.core.exceptions;

public class InterruptionException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public InterruptionException() {
   }

   public InterruptionException(String var1) {
      super(var1);
   }

   public InterruptionException(Throwable var1) {
      super(var1);
   }

   public InterruptionException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
