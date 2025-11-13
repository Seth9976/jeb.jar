package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class byv extends AbstractDOptimizer {
   int pC;

   public byv() {
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      this.pC = 0;

      for (IDInstruction var2 : this.cfg.instructions()) {
         if (!var2.visitInstructionPostOrder(new byw(this), true)) {
            var2.transformToNop();
            this.pC++;
         }
      }

      if (this.pC > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return this.pC;
   }
}
