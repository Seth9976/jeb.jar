package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cib extends AbstractDOptimizer {
   @Override
   public int perform() {
      for (BasicBlock var2 : this.cfg) {
         for (IDInstruction var4 : var2) {
            logger.info("%04X: %s", var4.getOffset(), var4);
         }
      }

      return 0;
   }
}
