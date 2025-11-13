package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cdh extends AbstractDOptimizer {
   int q = 0;

   public cdh() {
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bto.q(this.g)) {
         return 0;
      } else {
         this.q = 0;

         for (BasicBlock var2 : this.cfg) {
            for (int var3 = 0; var3 < var2.size(); var3++) {
               IDInstruction var4 = (IDInstruction)var2.get(var3);
               if (!var4.visitInstructionPostOrder(new cdi(this), true)) {
                  var4.transformToNop();
                  this.q++;
               }
            }
         }

         if (this.q > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return this.q;
      }
   }
}
