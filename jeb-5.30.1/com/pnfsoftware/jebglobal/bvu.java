package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDOptimizer;
import java.util.Comparator;

class bvu implements Comparator {
   bvu(bvt var1) {
      this.q = var1;
   }

   public int q(IDOptimizer var1, IDOptimizer var2) {
      return -Double.compare(var1.getPriority(), var2.getPriority());
   }
}
