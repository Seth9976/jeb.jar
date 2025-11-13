package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class bxy extends AbstractDOptimizer {
   private int pC;

   @Override
   public int perform() {
      this.pC = 0;
      this.analyzeChains();

      for (IDInstruction var2 : this.cfg.instructions()) {
         var2.visitInstruction(new bxz(this, var2));
      }

      if (this.pC > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return this.pC;
   }
}
