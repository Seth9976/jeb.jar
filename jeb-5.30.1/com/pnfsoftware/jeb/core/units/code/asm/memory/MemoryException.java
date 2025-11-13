package com.pnfsoftware.jeb.core.units.code.asm.memory;

import java.io.IOException;

public class MemoryException extends IOException {
   private static final long serialVersionUID = 1L;

   public MemoryException() {
   }

   public MemoryException(String var1) {
      super(var1);
   }

   public MemoryException(Throwable var1) {
      super(var1);
   }

   public MemoryException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
