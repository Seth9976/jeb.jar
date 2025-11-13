package com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions;

public class InstructionConversionException extends DecompilerException {
   private static final long serialVersionUID = 1L;

   public InstructionConversionException() {
   }

   public InstructionConversionException(String var1) {
      super(var1);
   }

   public InstructionConversionException(Throwable var1) {
      super(var1);
   }

   public InstructionConversionException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
