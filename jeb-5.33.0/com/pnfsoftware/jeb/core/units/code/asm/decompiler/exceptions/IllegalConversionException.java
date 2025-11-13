package com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions;

public class IllegalConversionException extends InstructionConversionException {
   private static final long serialVersionUID = 1L;

   public IllegalConversionException() {
   }

   public IllegalConversionException(String var1) {
      super(var1);
   }

   public IllegalConversionException(Throwable var1) {
      super(var1);
   }

   public IllegalConversionException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
