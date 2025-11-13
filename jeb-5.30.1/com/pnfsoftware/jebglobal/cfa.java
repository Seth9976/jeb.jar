package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;

public class cfa extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size() - 1; var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() >= 2 && ((IDInstruction)var3.getLast()).isSwitchOnInt()) {
            IDInstruction var4 = (IDInstruction)var3.getLast();
            if (var4.getSwitchExpression() instanceof IDVar var6) {
               IDInstruction var7 = (IDInstruction)var3.get(var3.size() - 2);
               if (var7.isAssignToVar(var6.getId()) && var7.getAssignSource() instanceof IDCallInfo var8) {
                  IDVar var12 = cez.q(var8);
                  BasicBlock var10 = this.cfg.get(var2 + 1);
                  if (var10.insize() == 1) {
                     IDInstruction var11 = (IDInstruction)var10.get(0);
                     if (var11.isJcond() && this.q(var3, var10, var6, var12)) {
                        var1++;
                     }
                  }
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

   boolean q(BasicBlock var1, BasicBlock var2, IDVar var3, IDVar var4) {
      IDInstruction var5 = (IDInstruction)var1.getLast();
      Assert.a(var5.isSwitchOnInt());
      IDInstruction var6 = (IDInstruction)var2.get(0);
      Assert.a(var6.isJcond());
      if (var6.getJcondCondition() instanceof IDOperation var8
         && var8.getOperatorType() == JavaOperatorType.LOG_OR
         && var8.getOperand1() instanceof IDOperation var9
         && var8.getOperand2() instanceof IDOperation var10) {
         Integer var18 = cez.q(var9, var3);
         if (var18 != null) {
            Couple var12 = cez.q(var10, var4, var18, this.g);
            if (var12 != null && var5.getSwitchData().getTargetForCase(var18) == null) {
               long var13 = var6.getOffset();
               DUtil.modifyInstructionSize(this.ctx, var6, 2);
               var6.adjustSize(-1);
               var6.setOffset(var13 + 1L);
               this.ctx.getExceptionData().moveProtectedBlock((int)var13, (int)(var13 + 1L));
               IDInstruction var15 = this.ctx.createJump(var6.getBranchTarget()).withOffset(var13);
               BasicBlock var16 = new BasicBlock();
               var16.add(var15);
               this.cfg.addBlock(this.cfg.indexOf(var1) + 1, var16);
               this.cfg.reconnectEdge(var1, var2, var16);
               this.cfg.addEdge(var16, this.cfg.getBlockAt((long)var6.getBranchTarget()));
               this.cfg.addEdge(var1, var2);
               var5.getSwitchData().addCase(var18, this.ctx.createTarget((int)var2.getBase()), false);
               var6.setJcondCondition(var10);
               return true;
            }
         }
      }

      return false;
   }
}
