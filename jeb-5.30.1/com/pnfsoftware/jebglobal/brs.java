package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;

public class brs extends bsq implements brn, bro {
   IJavaPredicate oW;

   public brs(IJavaPredicate var1) {
      super(bsq.eo.xK, bsq.CU.xK);
      this.oW = var1;
   }

   @Override
   public IJavaPredicate q() {
      return this.oW;
   }

   @Override
   public String toString() {
      return "do-while-end: " + this.oW;
   }
}
