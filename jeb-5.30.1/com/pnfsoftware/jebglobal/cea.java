package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cea extends AbstractDOptimizer {
   private static final boolean q = true;

   @Override
   public int perform() {
      bvb var1 = new bvb(this.ctx, true);
      int var2 = 0;

      for (BasicBlock var4 : this.cfg) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            IDInstruction var7 = (IDInstruction)var1.q(var6);
            if (var7 != null) {
               var4.set(var5, var7);
               var2++;
            }
         }
      }

      if (var2 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var2;
   }
}
