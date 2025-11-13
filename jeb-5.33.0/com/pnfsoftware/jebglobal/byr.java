package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class byr extends AbstractDOptimizer {
   int pC = 0;

   public byr() {
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bpl.pC(this.g)) {
         return 0;
      } else {
         this.pC = 0;

         for (BasicBlock var2 : this.cfg) {
            for (int var3 = 0; var3 < var2.size(); var3++) {
               IDInstruction var4 = (IDInstruction)var2.get(var3);
               if (!var4.visitInstructionPostOrder(new bys(this), true)) {
                  var4.transformToNop();
                  this.pC++;
               }
            }
         }

         if (this.pC > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return this.pC;
      }
   }
}
