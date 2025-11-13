package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;

public class bry extends bsq implements bro {
   IJavaPredicate oW;

   public bry(IJavaPredicate var1) {
      super(bsq.eo.RF, bsq.CU.RF);
      this.oW = var1;
   }

   @Override
   public IJavaPredicate q() {
      return this.oW;
   }

   @Override
   public String toString() {
      return "else-if: " + this.oW;
   }
}
