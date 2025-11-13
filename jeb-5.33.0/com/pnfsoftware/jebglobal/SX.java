package com.pnfsoftware.jebglobal;

import java.util.Comparator;

class SX implements Comparator {
   SX(C var1) {
      this.pC = var1;
   }

   public int pC(aut var1, aut var2) {
      return Integer.compare(var1.getCFG().getEffectiveSize(), var2.getCFG().getEffectiveSize());
   }
}
