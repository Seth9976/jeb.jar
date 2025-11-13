package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cbf extends AbstractDOptimizer {
   int pC;

   @Override
   public int perform() {
      this.pC = 0;

      for (IDInstruction var2 : this.cfg.instructions()) {
         var2.visitInstruction(new cbg(this));
      }

      return this.pC;
   }
}
