package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class ccy extends AbstractDOptimizer {
   private int q;

   @Override
   public int perform() {
      this.q = 0;

      for (IDInstruction var2 : this.cfg.instructions()) {
         var2.visitInstruction(new ccz(this), true);
      }

      return this.q;
   }
}
