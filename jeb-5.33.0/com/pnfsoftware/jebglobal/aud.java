package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aud extends atf {
   private static final StructuredLogger WR = aco.pC(aud.class);

   public aud() {
      super(atg.ah);
   }

   @Override
   protected boolean kS() {
      this.pC(OptimizerMode.UNFRIENDLY, WR, "Unfriendly optimizations (before performing casts)");

      int var1;
      try {
         WR.beginSection("Inserting casts");
         aln var2 = new aln();
         var1 = var2.performOnTarget(this.ys);
      } finally {
         WR.closeSection();
      }

      Object[] var10000 = new Object[]{var1};
      return true;
   }
}
