package com.pnfsoftware.jeb.core.units.code.android.ir;

public class DexDecEvalCodeThrownException extends DexDecEvaluationException {
   private static final long serialVersionUID = 1L;
   private IDImm ref;

   public DexDecEvalCodeThrownException(IDImm var1) {
      this.ref = var1;
   }

   public IDImm getThrownObjectRef() {
      return this.ref;
   }
}
