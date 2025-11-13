package com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class DecompilerException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public DecompilerException() {
   }

   public DecompilerException(String var1) {
      super(var1);
   }

   public DecompilerException(Throwable var1) {
      super(var1);
   }

   public DecompilerException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
