package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodExecutionHelper;

public abstract class btc implements IDMethodExecutionHelper {
   private String pC;

   public btc(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Missing method signature");
      } else {
         this.pC = var1;
      }
   }

   @Override
   public final String getMethodSignature() {
      return this.pC;
   }
}
