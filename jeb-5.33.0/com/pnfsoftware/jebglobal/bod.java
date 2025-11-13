package com.pnfsoftware.jebglobal;

import java.util.List;

public class bod extends bop {
   List wS;
   List UT;

   public bod(List var1, List var2) {
      super(bop.Av.sY, bop.Sv.A);
      this.wS = var1;
      if (var2 != null && var2.size() != var1.size()) {
         throw new RuntimeException();
      } else {
         this.UT = var2;
      }
   }

   public List A() {
      return this.wS;
   }

   public List kS() {
      return this.UT;
   }

   @Override
   public String toString() {
      return "switch-case: " + this.wS;
   }
}
