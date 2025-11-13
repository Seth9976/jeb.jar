package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJOptimizer;
import java.util.Comparator;

class blq implements Comparator {
   blq(blp var1) {
      this.q = var1;
   }

   public int q(IJOptimizer var1, IJOptimizer var2) {
      return -Double.compare(var1.getPriority(), var2.getPriority());
   }
}
