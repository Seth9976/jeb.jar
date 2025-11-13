package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class cdj extends AbstractDOptimizer {
   private static final ILogger q = GlobalLog.getLogger(cdj.class);

   public cdj() {
      super(DOptimizerType.UNSAFE);
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bto.q(this.g)) {
         return 0;
      } else {
         cdk var1 = new cdk(this, this.ctx);
         return var1.q();
      }
   }
}
