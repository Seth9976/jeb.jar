package com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions;

public class UnsupportedConversionException extends InstructionConversionException {
   private static final long serialVersionUID = 1L;

   public UnsupportedConversionException() {
   }

   public UnsupportedConversionException(String var1) {
      super(var1);
   }

   public UnsupportedConversionException(Throwable var1) {
      super(var1);
   }

   public UnsupportedConversionException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
