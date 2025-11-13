package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;

public class NativeAnalyzerException extends JebRuntimeException {
   private static final long serialVersionUID = 1L;

   public NativeAnalyzerException() {
   }

   public NativeAnalyzerException(String var1) {
      super(var1);
   }

   public NativeAnalyzerException(Throwable var1) {
      super(var1);
   }

   public NativeAnalyzerException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
