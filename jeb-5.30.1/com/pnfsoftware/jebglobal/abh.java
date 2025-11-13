package com.pnfsoftware.jebglobal;

import java.util.Comparator;

class abh implements Comparator {
   abh(abg var1) {
      this.q = var1;
   }

   public int q(axo var1, axo var2) {
      return Integer.compare(var1.getCFG().getEffectiveSize(), var2.getCFG().getEffectiveSize());
   }
}
