package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class ccm extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 0; var2 < this.cfg.size() - 3; var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() == 1) {
            IDInstruction var4 = (IDInstruction)var3.get(0);
            if (var4.isJcond()) {
               int var5 = ((IDInstruction)var3.get(0)).getBranchTarget();
               BasicBlock var6 = this.cfg.get(var2 + 1);
               if (var6.size() == 2 && ((IDInstruction)var6.get(0)).isAssignToVar() && ((IDInstruction)var6.get(1)).isJump() && var5 == var6.getEndAddress()) {
                  IDVar var7 = ((IDInstruction)var6.get(0)).getAssignDestination().asVar();
                  int var8 = ((IDInstruction)var6.get(1)).getBranchTarget();
                  BasicBlock var9 = this.cfg.get(var2 + 2);
                  if (var9.size() == 1 && ((IDInstruction)var9.get(0)).isAssignToVar(var7.getId()) && var8 == var9.getEndAddress()) {
                     BasicBlock var10 = this.cfg.get(var2 + 3);
                     if (((IDInstruction)var10.getLast()).isJumpOrJcond() && ((IDInstruction)var10.getLast()).getBranchTarget() == var5) {
                        IDInstruction var11 = (IDInstruction)var6.get(0);
                        Long var12 = this.pC(var7, var11.getAssignSource());
                        if (var12 != null) {
                           IDInstruction var13 = (IDInstruction)var9.get(0);
                           Long var14 = this.pC(var7, var13.getAssignSource());
                           if (var14 != null) {
                              IDOperation var15 = var11.getAssignSource().asOperation();
                              IDImm var16 = this.g.createImm(var12 - var14, var15.getOperand2().getType());
                              var15.setOperand2(var16);
                              var15.setOperator(JavaOperatorType.ADD, this.of);
                              ((IDInstruction)var6.get(1)).transformToNop();
                              this.cfg.reconnectEdge(var6, var10, var9);
                              var1++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1;
   }

   private Long pC(IDVar var1, IDExpression var2) {
      if (var2.isOperation(JavaOperatorType.ADD, JavaOperatorType.SUB)) {
         IDOperation var3 = var2.asOperation();
         if (var3.getOperand1() == var1 && var3.getOperand2().isConstantImm()) {
            IDImm var4 = var3.getOperand2().asImm();
            IJavaType var5 = var4.getType();
            if (var5 != null && (var5.isSmallInt() || var5.isLong())) {
               try {
                  long var6 = var4.toLong();
                  return var3.getOperatorType() == JavaOperatorType.ADD ? var6 : -var6;
               } catch (DexDecEvaluationException var8) {
               }
            }
         }
      }

      return null;
   }
}
