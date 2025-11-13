package com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt;

import java.util.Comparator;

class AbstractMasterOptimizer$1 implements Comparator {
   AbstractMasterOptimizer$1(AbstractMasterOptimizer var1) {
      this.this$0 = var1;
   }

   public int compare(OptimizerEntry var1, OptimizerEntry var2) {
      return Double.compare(var2.getOptimizer().getPriority(), var1.getOptimizer().getPriority());
   }
}
