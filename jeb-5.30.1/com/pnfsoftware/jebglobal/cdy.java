package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;

public class cdy extends AbstractDOptimizer {
   @Override
   public int perform() {
      if (this.ctx.getExceptionData().isEmpty()) {
         return 0;
      } else {
         int var1 = 0;

         for (BasicBlock var3 : this.cfg) {
            IDInstruction var4;
            if (var3.irrinsize() > 0 && (var4 = (IDInstruction)var3.get(0)).isStoreException()) {
               IDVar var5 = var4.getStoredExceptionVariable();
               this.analyzeChains();

               for (long var8 : this.dfa.getDefUses(var3.getBase(), var5.getId())) {
                  IDInstruction var10 = (IDInstruction)this.cfg.getInstruction(var8);
                  if (var10 != null && var10.isJcond()) {
                     int var11 = this.q(var10.getJcondCondition(), var5);
                     if (var11 != 0 && this.dfa.checkSingleDef(var8, var5.getId(), var3.getBase())) {
                        Assert.a(var11 == 1 || var11 == 2);
                        this.cfg.deleteOutEdges(var3);
                        var10.transformJcondToJump();
                        if (var11 == 1) {
                           var10.setBranchTarget((int)var10.getOffsetEnd());
                        }

                        this.cfg.addEdge(var3, this.cfg.getBlockAt((long)var10.getBranchTarget()));
                        var1++;
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
   }

   private int q(IDExpression var1, IDVar var2) {
      if (var1 instanceof IDOperation var3) {
         JavaOperatorType var4 = var3.getOperatorType();
         if (var4.isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE) && var3.getOperand1() == var2 && var3.getOperand2() instanceof IDImm) {
            IDImm var5 = var3.getOperand2().asImm();
            if (var5.isZero()) {
               return var4 == JavaOperatorType.EQ ? 1 : 2;
            }
         }
      }

      return 0;
   }
}
