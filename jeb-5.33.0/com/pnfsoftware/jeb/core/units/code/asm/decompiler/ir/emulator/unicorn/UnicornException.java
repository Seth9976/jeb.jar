package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.unicorn;

public class UnicornException extends RuntimeException {
   private static final long serialVersionUID = -6938281450570075359L;
   private int err;

   public UnicornException(String var1) {
      super(var1);
      this.err = -1;
   }

   public UnicornException(String var1, int var2) {
      super(var1 + ": " + UnicornLibrary.INSTANCE.uc_strerror(var2));
      this.err = var2;
   }

   public int getErrorCode() {
      return this.err;
   }
}
