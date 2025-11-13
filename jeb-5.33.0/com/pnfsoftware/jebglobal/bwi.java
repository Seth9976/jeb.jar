package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;

public abstract class bwi extends AbstractDOptimizer {
   protected boolean pC = true;
   protected boolean A = false;
   protected IDInstruction kS = null;

   public bwi() {
   }

   public bwi(DOptimizerType var1) {
      super(var1);
   }

   @Override
   public int perform() {
      boolean[] var1 = new boolean[]{false};
      int var2 = 0;

      for (BasicBlock var4 : this.cfg.getBlocks()) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            this.kS = var6;

            int var7;
            do {
               var7 = 0;
               if (!this.pC) {
                  brn var8 = this.pC(var6);
                  if (var8 != null) {
                     IDExpression var9 = var8.pC();
                     if (!(var9 instanceof IDInstruction)) {
                        throw new RuntimeException("Expected a statement IRE");
                     }

                     var6 = (IDInstruction)var8.pC();
                     var4.set(var5, var6);
                     if (var8.A()) {
                        var1[0] = true;
                     }

                     var7++;
                  }
               }

               if (var7 == 0) {
                  if (this.A && var6.isAssign()) {
                     var7 += this.pC(var6, var6.getAssignSource(), var1, null);
                  } else {
                     for (IDExpression var11 : var6.getSubExpressions()) {
                        var7 += this.pC(var6, var11, var1, null);
                     }
                  }
               }

               var2 += var7;
            } while (var7 > 0);
         }
      }

      this.kS = null;
      if (var1[0]) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var2;
   }

   private int pC(IDExpression var1, IDExpression var2, boolean[] var3, IDExpression[] var4) {
      brn var5 = this.pC(var2);
      if (var5 != null) {
         if (var1 == null) {
            if (var4 != null) {
               var4[0] = var5.pC();
            }
         } else if (!var1.replaceSubExpression(var2, var5.pC())) {
            return 0;
         }

         if (var5.A() && var3 != null) {
            var3[0] = true;
         }

         return 1;
      } else {
         int var6 = 0;

         for (IDExpression var8 : var2.getSubExpressions()) {
            var6 += this.pC(var2, var8, var3, var4);
         }

         return var6;
      }
   }

   protected abstract brn pC(IDExpression var1);
}
