package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cbt extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         IDInstruction var4 = (IDInstruction)var3.getLast();
         if (var4.isJcond() && !Boolean.TRUE.equals(var4.getData("KEEP_INSTRUCTION"))) {
            IDExpression var5 = var4.getJcondCondition();
            if (!var5.hasSideEffects(this.ctx, true)) {
               long var6 = var4.getBranchTarget();
               if (var6 == var3.getEndAddress()) {
                  var4.transformToNop();
                  BasicBlock var8 = this.cfg.getBlockAt(var6);
                  this.cfg.deleteOutEdges(var3);
                  this.cfg.addEdge(var3, var8);
                  var1++;
               } else if (bto.xK(var5)) {
                  var4.transformJcondToJump();
                  BasicBlock var9 = this.cfg.getBlockAt(var3.getEndAddress());
                  this.cfg.deleteEdge(var3, var9);
                  var1++;
               } else if (bto.Dw(var5)) {
                  var4.transformToNop();
                  BasicBlock var10 = this.cfg.getBlockAt(var6);
                  this.cfg.deleteEdge(var3, var10);
                  var1++;
               }
            }
         }
      }

      if (var1 > 0) {
         this.cleanGraph();
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
