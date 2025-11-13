package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.exceptions.JebException;

public class DexDecEvaluationException extends JebException {
   private static final long serialVersionUID = 1L;

   public DexDecEvaluationException() {
   }

   public DexDecEvaluationException(String var1) {
      super(var1);
   }

   public DexDecEvaluationException(Throwable var1) {
      super(var1);
   }

   public DexDecEvaluationException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public DexDecEvaluationException(IDExpression var1) {
      this("Cannot evaluate: " + var1);
   }
}
