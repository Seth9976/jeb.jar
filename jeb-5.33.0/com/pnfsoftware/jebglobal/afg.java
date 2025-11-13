package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.BigIntegers;
import java.util.Comparator;

class afg implements Comparator {
   afg(aff var1) {
      this.pC = var1;
   }

   public int pC(aff.Av var1, aff.Av var2) {
      return BigIntegers.max(var1.kS).compareTo(BigIntegers.max(var2.kS));
   }
}
