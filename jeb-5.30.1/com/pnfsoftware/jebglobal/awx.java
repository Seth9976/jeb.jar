package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class awx extends avx {
   private static final StructuredLogger JY = aeg.q(awx.class);

   public awx() {
      super(avy.Me);
   }

   @Override
   protected boolean Dw() {
      this.q(OptimizerMode.UNFRIENDLY, JY, "Unfriendly optimizations (before performing casts)");

      int var1;
      try {
         JY.beginSection("Inserting casts");
         ans var2 = new ans();
         var1 = var2.performOnTarget(this.nf);
      } finally {
         JY.closeSection();
      }

      Object[] var10000 = new Object[]{var1};
      return true;
   }
}
