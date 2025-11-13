package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cbp extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 1; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() == 1 && var3.insize() == 1 && var3.irrinsize() == 0 && ((IDInstruction)var3.get(0)).isJcond()) {
            BasicBlock var4 = this.cfg.get(var2 - 1);
            if (((IDInstruction)var4.getLast()).isJcond() && var4.getOutputBlock(0) == var3) {
               IDInstruction var5 = (IDInstruction)var3.get(0);
               IDExpression var6 = var5.getJcondCondition();
               IDExpression var7 = ((IDInstruction)var4.getLast()).getJcondCondition();
               if (!var6.hasSideEffects(this.ctx, true) && var6.equalsEx(var7, false)) {
                  this.cfg.deleteEdge(var3, this.cfg.getBlockAt((long)var5.getBranchTarget()));
                  var5.transformToNop();
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
         this.cleanGraph();
      }

      return var1;
   }
}
