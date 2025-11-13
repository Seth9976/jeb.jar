package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodExecutionHelper;

public abstract class bxr implements IDMethodExecutionHelper {
   private String q;

   public bxr(String var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Missing method signature");
      } else {
         this.q = var1;
      }
   }

   @Override
   public final String getMethodSignature() {
      return this.q;
   }
}
