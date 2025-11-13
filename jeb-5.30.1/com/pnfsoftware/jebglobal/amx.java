package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import java.util.Comparator;

class amx implements Comparator {
   public int q(OptimizerEntry var1, OptimizerEntry var2) {
      return Long.compare(var2.getStatistics().getExecutionTimeMillis(), var1.getStatistics().getExecutionTimeMillis());
   }
}
