package com.pnfsoftware.jebglobal;

import java.util.Comparator;

class bhm implements Comparator {
   bhm(bhl var1) {
      this.pC = var1;
   }

   public int pC(bow var1, bow var2) {
      return -(var1.pC() - var2.pC());
   }
}
