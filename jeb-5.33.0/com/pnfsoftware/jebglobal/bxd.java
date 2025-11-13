package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class bxd extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size() - 1; var2++) {
         BasicBlock var5 = this.cfg.get(var2);
         IDInstruction var3;
         if (var5.size() == 1
            && (var3 = (IDInstruction)var5.get(0)).isJcond()
            && var3.getJcondCondition() instanceof IDOperation var6
            && var6.getOperatorType() == JavaOperatorType.NE
            && var6.getOperand1() instanceof IDVar var7
            && var6.getOperand2() instanceof IDImm var8
            && var7.getType().isObject()
            && var8.isNullRef()) {
            BasicBlock var17 = this.cfg.get(var2 + 1);
            if (var17.size() == 1 && ((IDInstruction)var17.get(0)).isReturn()) {
               BasicBlock var10 = this.cfg.getBlockAt((long)var3.getBranchTarget());
               IDInstruction var4;
               if (var10.size() == 1
                  && (var4 = (IDInstruction)var10.get(0)).isJcond()
                  && var4.getJcondCondition() instanceof IDOperation var11
                  && var11.getOperatorType() == JavaOperatorType.INSTANCEOF
                  && var11.getOperand1() == var7) {
                  BasicBlock var18 = this.cfg.getBlockAt(var10.getEndAddress());
                  if (var18.size() == 1 && ((IDInstruction)var18.get(0)).isReturn()) {
                     IDExpression var13 = ((IDInstruction)var17.get(0)).getReturnExpression();
                     IDExpression var14 = ((IDInstruction)var18.get(0)).getReturnExpression();
                     if (var13 == null && var14 == null || var13 != null && var13.equals(var14)) {
                        var3.transformJcondToJump();
                        this.cfg.deleteEdge(var5, var17);
                        var1++;
                        var2++;
                     }
                  }
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
