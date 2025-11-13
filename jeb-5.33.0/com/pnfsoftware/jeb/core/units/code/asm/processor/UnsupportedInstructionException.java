package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class UnsupportedInstructionException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public UnsupportedInstructionException() {
   }

   public UnsupportedInstructionException(String var1) {
      super(var1);
   }

   public UnsupportedInstructionException(Throwable var1) {
      super(var1);
   }

   public UnsupportedInstructionException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
