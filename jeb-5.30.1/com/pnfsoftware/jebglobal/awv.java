package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awv extends avx {
   private static final StructuredLogger JY = aeg.q(awv.class);

   public awv() {
      super(avy.qa);
   }

   @Override
   protected boolean Dw() {
      this.q(OptimizerMode.AGGRESSIVE, JY, "Aggressive optimizations (reaching-output is disregarded)");
      Object[] var10000 = new Object[0];
      avj var1 = new avj();
      int var2 = var1.performOnTarget(this.nf);
      var10000 = new Object[]{var2};
      if (var2 > 0) {
         var10000 = new Object[]{var2};
      }

      return !var1.q() || !this.oW();
   }
}
