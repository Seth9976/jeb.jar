package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class CannotReadRegisterException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public CannotReadRegisterException(String var1) {
      super("Cannot read register name " + var1);
   }

   public CannotReadRegisterException(int var1) {
      super("Cannot read register index " + var1);
   }

   public CannotReadRegisterException(RegisterType var1) {
      super("Cannot read register type " + var1);
   }
}
