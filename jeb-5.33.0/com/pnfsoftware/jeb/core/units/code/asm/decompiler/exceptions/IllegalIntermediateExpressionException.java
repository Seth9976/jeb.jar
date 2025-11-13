package com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions;

import com.pnfsoftware.jeb.util.format.Strings;

public class IllegalIntermediateExpressionException extends DecompilerException {
   private static final long serialVersionUID = 1L;

   public IllegalIntermediateExpressionException(String var1) {
      super(var1);
   }

   public IllegalIntermediateExpressionException(String var1, Object... var2) {
      super(Strings.ff(var1, var2));
   }
}
