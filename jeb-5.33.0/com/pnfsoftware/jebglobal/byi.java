package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class byi extends AbstractDOptimizer {
   private int pC;

   @Override
   public int perform() {
      this.pC = 0;

      for (IDInstruction var2 : this.cfg.instructions()) {
         var2.visitInstruction(new byj(this), true);
      }

      return this.pC;
   }
}
