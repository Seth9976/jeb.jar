package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class chx extends AbstractDOptimizer {
   private int q = 0;

   @Override
   public int perform() {
      this.q = 0;

      for (BasicBlock var2 : this.cfg) {
         for (IDInstruction var4 : var2) {
            var4.visitInstruction(new chy(this), true);
         }
      }

      if (this.q > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return this.q;
   }
}
