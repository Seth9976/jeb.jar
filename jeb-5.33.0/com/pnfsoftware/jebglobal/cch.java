package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cch extends AbstractDOptimizer {
   private int pC;

   @Override
   public int perform() {
      this.pC = 0;

      for (int var1 = 0; var1 < this.cfg.size(); var1++) {
         BasicBlock var2 = this.cfg.get(var1);

         for (int var3 = 0; var3 < var2.size(); var3++) {
            IDInstruction var4 = (IDInstruction)var2.get(var3);
            var4.visitDepthPost(new cci(this));
         }
      }

      return this.pC;
   }
}
