package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class byt extends AbstractDOptimizer {
   private static final ILogger pC = GlobalLog.getLogger(byt.class);

   public byt() {
      super(DOptimizerType.UNSAFE);
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bpl.pC(this.g)) {
         return 0;
      } else {
         byu var1 = new byu(this, this.ctx);
         return var1.pC();
      }
   }
}
