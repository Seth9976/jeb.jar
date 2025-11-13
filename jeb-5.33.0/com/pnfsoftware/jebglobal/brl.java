package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import java.util.Comparator;

class brl implements Comparator {
   brl(brk var1) {
      this.pC = var1;
   }

   public int pC(IDOptimizer var1, IDOptimizer var2) {
      return -Double.compare(var1.getPriority(), var2.getPriority());
   }
}
