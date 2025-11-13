package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.exceptions.JebException;

public class ProcessorException extends JebException {
   private static final long serialVersionUID = 1L;

   public ProcessorException() {
   }

   public ProcessorException(String var1) {
      super(var1);
   }

   public ProcessorException(Throwable var1) {
      super(var1);
   }

   public ProcessorException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
