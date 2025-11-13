package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJOptimizer;
import java.util.Comparator;

class bht implements Comparator {
   bht(bhs var1) {
      this.pC = var1;
   }

   public int pC(IJOptimizer var1, IJOptimizer var2) {
      return -Double.compare(var1.getPriority(), var2.getPriority());
   }
}
