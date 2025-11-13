package com.pnfsoftware.jeb.core.units.code.android.ir;

public class DexDecEvalStubException extends DexDecEvaluationException {
   private static final long serialVersionUID = 1L;
   private String msig;

   public DexDecEvalStubException(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.msig = var1;
      }
   }

   public String getStubMethodSignature() {
      return this.msig;
   }

   @Override
   public String getMessage() {
      return "Only have a stub for method: " + this.msig;
   }
}
