package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public class cga extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() == 1 && ((IDInstruction)var3.get(0)).isJcond()) {
            IDInstruction var4 = (IDInstruction)var3.get(0);
            if (var4.getBranchTarget() == (int)var4.getOffset()) {
               BasicBlock var5 = this.cfg.get(var2 + 1);
               DUtil.modifyInstructionSize(this.ctx, var4, 2);
               var4.adjustSize(-1);
               BasicBlock var6 = new BasicBlock();
               IDInstruction var7 = this.ctx.createJump((int)var4.getOffset()).withOffset((int)var4.getOffsetEnd());
               var7.setData("KEEP_INSTRUCTION", true);
               var6.add(var7);
               var4.setBranchTarget((int)var5.getBase());
               var4.reverseJcondCondition();
               this.cfg.addBlock(var2 + 1, var6);
               this.cfg.addEdge(var6, var3);
               this.cfg.deleteOutEdges(var3);
               this.cfg.addEdge(var3, var6);
               this.cfg.addEdge(var3, var5);
               var1++;
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }
}
