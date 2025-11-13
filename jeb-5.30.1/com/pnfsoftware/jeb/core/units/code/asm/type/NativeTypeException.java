package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class NativeTypeException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public NativeTypeException() {
   }

   public NativeTypeException(String var1) {
      super(var1);
   }

   public NativeTypeException(Throwable var1) {
      super(var1);
   }

   public NativeTypeException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
