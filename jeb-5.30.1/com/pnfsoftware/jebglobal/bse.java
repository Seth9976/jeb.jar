package com.pnfsoftware.jebglobal;

import java.util.List;

public class bse extends bsq {
   List oW;
   List gO;

   public bse(List var1, List var2) {
      super(bsq.eo.gO, bsq.CU.RF);
      this.oW = var1;
      if (var2 != null && var2.size() != var1.size()) {
         throw new RuntimeException();
      } else {
         this.gO = var2;
      }
   }

   public List Dw() {
      return this.oW;
   }

   public List Uv() {
      return this.gO;
   }

   @Override
   public String toString() {
      return "switch-case: " + this.oW;
   }
}
