package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

public class cbk extends AbstractDOptimizer {
   @Override
   public int perform() {
      cbk.Av var1 = new cbk.Av();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstructionPostOrder(var1, true);
      }

      if (var1.pC > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.pC;
   }

   class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDOperation var4 && var4.isConditional()) {
            IDExpression var5 = var4.getCondPredicate();
            IDExpression var6 = var4.getCondTrueExpression();
            IDExpression var7 = var4.getCondFalseExpression();
            if (!var6.equalsEx(var7, false) || var5.hasSideEffects(cbk.this.ctx, true)) {
               if (var6.getType() == cbk.this.tf.getBoolean() && var7.getType() == var6.getType() && var6.isConstantImm() && var7.isConstantImm()) {
                  if (var6.asImm().getRawValue() != 0L && var7.asImm().getRawValue() == 0L && var2.replaceSubExpression(var1, var5)) {
                     this.pC++;
                  }

                  if (var6.asImm().getRawValue() == 0L && var7.asImm().getRawValue() != 0L) {
                     IDExpression var8 = bpl.pC(var5, cbk.this.g);
                     if (var2.replaceSubExpression(var1, var8)) {
                        this.pC++;
                     }
                  }
               }
            } else if (var2.replaceSubExpression(var1, var6)) {
               this.pC++;
            }
         }
      }
   }
}
