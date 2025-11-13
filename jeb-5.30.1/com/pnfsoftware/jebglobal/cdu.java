package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTryData;

public class cdu extends AbstractDOptimizer {
   @Override
   public int perform() {
      IDTryData var1 = this.ctx.getExceptionData();
      if (var1 != null && !var1.isEmpty()) {
         int var2 = 0;
         int var3 = 0;

         while (var3 < this.cfg.size() - 1) {
            BasicBlock var4 = this.cfg.get(var3);
            BasicBlock var5;
            IDInstruction var6;
            if (var4.outsize() == 1
               && !(var6 = (IDInstruction)var4.getLast()).getBreakingFlow().isBroken()
               && !Boolean.TRUE.equals(var6.getData("KEEP_LAST"))
               && (var5 = var4.getOutputBlock(0)) == this.cfg.get(var3 + 1)
               && var5.insize() == 1
               && var5.irrinsize() == 0
               && !Boolean.TRUE.equals(((IDInstruction)var5.get(0)).getData("KEEP_FIRST"))
               && DUtil.mergeBlocks(this.ctx, var4)) {
               var2++;
            } else {
               var3++;
            }
         }

         if (var2 > 0) {
            this.cleanGraph();
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var2;
      } else {
         return 0;
      }
   }
}
