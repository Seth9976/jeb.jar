package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.Collection;

public class ccn extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 1; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() == 1) {
            IDInstruction var4 = (IDInstruction)var3.get(0);
            if (var4.isJcond()) {
               IDExpression var5 = var4.getJcondCondition();
               if (var5.isOperation(
                  JavaOperatorType.EQ, JavaOperatorType.NE, JavaOperatorType.GT, JavaOperatorType.GE, JavaOperatorType.LT, JavaOperatorType.LE
               )) {
                  IDOperation var6 = var5.asOperation();
                  IDExpression var7 = var6.getOperand1();
                  if (var7.isVar() && var6.getOperand2() instanceof IDImm) {
                     int var8 = var7.asVar().getId();
                     int var9 = -1;
                     BasicBlock var10 = this.cfg.get(var2 - 1);
                     if (!((IDInstruction)var10.getLast()).getBreakingFlow().isBroken()) {
                        this.analyzeChains();

                        for (int var11 = var10.size() - 1; var11 >= 0; var11--) {
                           IDInstruction var12 = (IDInstruction)var10.get(var11);
                           Collection var13 = this.dfa.getInstructionUses(var12.getOffset());
                           Collection var14 = this.dfa.getInstructionDefs(var12.getOffset());
                           if (var13.contains(var8) || var14.contains(var8)) {
                              if (var11 != var10.size() - 1 && var12.isAssignToVar(var8) && var12.getAssignSource().isConstantImm()) {
                                 var9 = var11;
                              }
                              break;
                           }
                        }

                        if (var9 != -1) {
                           long var18 = var10.getBase();
                           long var19 = var10.getEndAddress();
                           IDInstruction var15 = (IDInstruction)var10.get(var9);

                           for (int var16 = var9 + 1; var16 < var10.size(); var16++) {
                              IDInstruction var17 = (IDInstruction)var10.get(var16);
                              var10.set(var16 - 1, var17, false, false);
                           }

                           var10.set(var10.size() - 1, var15, false, false);
                           bpl.pC(var10, var18, var19);
                           this.cfg.invalidateDataFlowAnalysis();
                           var1++;
                        }
                     }
                  }
               }
            }
         }
      }

      return var1;
   }
}
