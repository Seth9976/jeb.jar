package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aub extends atf {
   private static final StructuredLogger WR = aco.pC(aub.class);

   public aub() {
      super(atg.ED);
   }

   @Override
   protected boolean kS() {
      this.pC(OptimizerMode.AGGRESSIVE, WR, "Aggressive optimizations (reaching-output is disregarded)");
      Object[] var10000 = new Object[0];
      asr var1 = new asr();
      int var2 = var1.performOnTarget(this.ys);
      var10000 = new Object[]{var2};
      if (var2 > 0) {
         var10000 = new Object[]{var2};
      }

      return !var1.pC() || !this.wS();
   }
}
