package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;

public class brn {
   IDExpression pC;
   boolean A;

   public static brn pC(IDExpression var0, boolean var1) {
      return var0 == null ? null : new brn(var0, var1);
   }

   public static brn pC(IDExpression var0) {
      return pC(var0, false);
   }

   private brn(IDExpression var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("An expression was expected");
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   public IDExpression pC() {
      return this.pC;
   }

   public boolean A() {
      return this.A;
   }
}
