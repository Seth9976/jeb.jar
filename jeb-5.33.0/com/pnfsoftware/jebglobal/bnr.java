package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;

public class bnr extends bop implements bnm, bnn {
   IJavaPredicate wS;

   public bnr(IJavaPredicate var1) {
      super(bop.Av.kS, bop.Sv.kS);
      this.wS = var1;
   }

   @Override
   public IJavaPredicate pC() {
      return this.wS;
   }

   @Override
   public String toString() {
      return "do-while-end: " + this.wS;
   }
}
