package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class CannotWriteRegisterException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public CannotWriteRegisterException(String var1) {
      super("Cannot rwriteead register name " + var1);
   }

   public CannotWriteRegisterException(int var1) {
      super("Cannot write register index " + var1);
   }

   public CannotWriteRegisterException(RegisterType var1) {
      super("Cannot write register type " + var1);
   }
}
