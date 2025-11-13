package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cco extends AbstractDOptimizer {
   private int q;

   @Override
   public int perform() {
      this.q = 0;
      this.analyzeChains();

      for (IDInstruction var2 : this.cfg.instructions()) {
         var2.visitInstruction(new ccp(this, var2));
      }

      if (this.q > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return this.q;
   }
}
