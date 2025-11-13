package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;

public class bvx {
   IDExpression q;
   boolean RF;

   public static bvx q(IDExpression var0, boolean var1) {
      return var0 == null ? null : new bvx(var0, var1);
   }

   public static bvx q(IDExpression var0) {
      return q(var0, false);
   }

   private bvx(IDExpression var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("An expression was expected");
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   public IDExpression q() {
      return this.q;
   }

   public boolean RF() {
      return this.RF;
   }
}
