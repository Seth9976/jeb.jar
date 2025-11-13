package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;

public class bnx extends bop implements bnn {
   IJavaPredicate wS;

   public bnx(IJavaPredicate var1) {
      super(bop.Av.A, bop.Sv.A);
      this.wS = var1;
   }

   @Override
   public IJavaPredicate pC() {
      return this.wS;
   }

   @Override
   public String toString() {
      return "else-if: " + this.wS;
   }
}
