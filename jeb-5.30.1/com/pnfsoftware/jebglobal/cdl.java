package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cdl extends AbstractDOptimizer {
   int q;

   public cdl() {
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      this.q = 0;

      for (IDInstruction var2 : this.cfg.instructions()) {
         if (!var2.visitInstructionPostOrder(new cdm(this), true)) {
            var2.transformToNop();
            this.q++;
         }
      }

      if (this.q > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return this.q;
   }
}
