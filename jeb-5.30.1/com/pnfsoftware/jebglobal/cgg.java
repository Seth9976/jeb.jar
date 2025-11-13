package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

public class cgg extends AbstractDOptimizer {
   @Override
   public int perform() {
      cgg.eo var1 = new cgg.eo();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstructionPostOrder(var1, true);
      }

      if (var1.q > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.q;
   }

   class eo implements IDVisitor {
      int q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDOperation var4 && var4.isConditional()) {
            IDExpression var5 = var4.getCondPredicate();
            IDExpression var6 = var4.getCondTrueExpression();
            IDExpression var7 = var4.getCondFalseExpression();
            if (!var6.equalsEx(var7, false) || var5.hasSideEffects(cgg.this.ctx, true)) {
               if (var6.getType() == cgg.this.tf.getBoolean() && var7.getType() == var6.getType() && var6.isConstantImm() && var7.isConstantImm()) {
                  if (var6.asImm().getRawValue() != 0L && var7.asImm().getRawValue() == 0L && var2.replaceSubExpression(var1, var5)) {
                     this.q++;
                  }

                  if (var6.asImm().getRawValue() == 0L && var7.asImm().getRawValue() != 0L) {
                     IDExpression var8 = bto.q(var5, cgg.this.g);
                     if (var2.replaceSubExpression(var1, var8)) {
                        this.q++;
                     }
                  }
               }
            } else if (var2.replaceSubExpression(var1, var6)) {
               this.q++;
            }
         }
      }
   }
}
