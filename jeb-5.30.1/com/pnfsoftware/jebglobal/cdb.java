package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import java.util.Collection;

public class cdb extends AbstractDOptimizer {
   public static final String q = "DO_NOT_HOIST";

   @Override
   public int perform() {
      if (this.ctx.getExceptionData().isEmpty()) {
         return 0;
      } else {
         int var1 = 0;

         for (BasicBlock var3 : this.cfg) {
            if (var3.irrinsize() != 0) {
               boolean var4 = false;
               if (var3.irrinsize() == 1 && var3.insize() == 0) {
                  var4 = this.q(var3);
               } else if (var3.insize() == 1) {
                  var4 = this.RF(var3);
               }

               if (var4) {
                  this.cfg.invalidateDataFlowAnalysis();
                  var1++;
               }
            }
         }

         return var1;
      }
   }

   boolean q(BasicBlock var1) {
      byte var2 = 0;
      if (((IDInstruction)var1.get(var2)).isStoreException()) {
         var2 = 1;
      }

      if (var2 >= var1.size()) {
         return false;
      } else {
         IDInstruction var3 = (IDInstruction)var1.get(var2);
         if (Boolean.TRUE.equals(var3.getData("DO_NOT_HOIST"))) {
            return false;
         } else if (!var3.isAssignToVar()) {
            return false;
         } else {
            IDVar var4 = var3.getAssignDestination().asVar();
            IDExpression var6 = var3.getAssignSource();
            IDVar var5;
            if (var6 instanceof IDImm) {
               var5 = null;
            } else {
               if (!var6.isVar()) {
                  return false;
               }

               var5 = var6.asVar();
            }

            boolean var7 = false;
            BasicBlock var8 = var1.getIrregularInputBlock(0);

            int var9;
            for (var9 = var8.size() - 1; var9 >= 0; var9--) {
               IDInstruction var10 = (IDInstruction)var8.get(var9);
               IDVar var11 = var10.getDefinedVariable();
               if (var11 != null) {
                  if (var5 != null && var11 == var5) {
                     return false;
                  }

                  if (var11 == var4) {
                     var7 = true;
                  }
               }

               if (var10.getUsedVariables().contains(var4)) {
                  var7 = false;
               }

               if (var10.canThrow()) {
                  if (!var7) {
                     return false;
                  }
                  break;
               }
            }

            if (var9 < 0) {
               return false;
            } else {
               this.analyzeChains();

               for (BasicBlock var16 : var8.getIrregularOutputs()) {
                  if (var16 != var1 && this.dfa.isAlive(var16, 0, var4.getId())) {
                     return false;
                  }
               }

               IDInstruction var15 = (IDInstruction)var8.get(var9);
               DUtil.modifyInstructionSize(this.ctx, var15, 2);
               long var17 = var15.getOffset();
               var15.setOffset(var17 + 1L);
               var15.adjustSize(-1);
               IDInstruction var13 = var3.duplicate();
               var13.setOffset(var17);
               var13.setSize(1);
               var8.add(var9, var13);
               var3.transformToNop();
               return true;
            }
         }
      }
   }

   boolean RF(BasicBlock var1) {
      IDInstruction var2 = (IDInstruction)var1.get(0);
      if (Boolean.TRUE.equals(var2.getData("DO_NOT_HOIST"))) {
         return false;
      } else if (!var2.isAssignToVar()) {
         return false;
      } else {
         IDVar var3 = var2.getAssignDestination().asVar();
         IDExpression var4 = var2.getAssignSource();
         if (!(var4 instanceof IDImm)) {
            return false;
         } else {
            BasicBlock var5 = var1.getInputBlock(0);
            Collection var6 = DUtil.determineInterval(var5);

            for (BasicBlock var8 : var1.getAllInputs()) {
               if (!var6.contains(var8)) {
                  return false;
               }
            }

            int var12 = var5.size();
            IDInstruction var13 = (IDInstruction)var5.getLast();
            if (var13.getBreakingFlow().isBroken()) {
               var12--;
            }

            this.analyzeChains();
            if (this.dfa.isAlive(var5, var12, var3.getId())) {
               return false;
            } else {
               for (BasicBlock var10 : var1.getIrregularInputs()) {
                  int var11 = var10.size() - 1;

                  while (var11 >= 0 && !((IDInstruction)var10.get(var11)).canThrow()) {
                     var11--;
                  }

                  if (var11 < 0) {
                     var11 = 0;
                  }

                  if (!this.dfa.isVarReachingFromTo(var3.getId(), var5, var5.size() - 1, var10, var11)) {
                     return false;
                  }
               }

               DUtil.modifyInstructionSize(this.ctx, var13, 2);
               var13.adjustSize(-1);
               long var15;
               if (var12 == var5.size()) {
                  var15 = var13.getOffsetEnd();
               } else {
                  var15 = var13.getOffset();
                  var13.setOffset(var15 + 1L);
               }

               IDInstruction var14 = var2.duplicateWithOffsetAndSize(var15, 1);
               var5.add(var12, var14);
               var2.transformToNop();
               return true;
            }
         }
      }
   }
}
