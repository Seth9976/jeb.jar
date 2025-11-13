package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;

public abstract class cax extends AbstractDOptimizer {
   protected boolean q = true;
   protected boolean RF = false;
   protected IDInstruction xK = null;

   public cax() {
   }

   public cax(DOptimizerType var1) {
      super(var1);
   }

   public cax(DOptimizerType var1, String var2) {
      super(var1, var2);
   }

   @Override
   public int perform() {
      boolean[] var1 = new boolean[]{false};
      int var2 = 0;

      for (BasicBlock var4 : this.cfg.getBlocks()) {
         for (int var5 = 0; var5 < var4.size(); var5++) {
            IDInstruction var6 = (IDInstruction)var4.get(var5);
            this.xK = var6;

            int var7;
            do {
               var7 = 0;
               if (!this.q) {
                  bvx var8 = this.q(var6);
                  if (var8 != null) {
                     IDExpression var9 = var8.q();
                     if (!(var9 instanceof IDInstruction)) {
                        throw new RuntimeException("Expected a statement IRE");
                     }

                     var6 = (IDInstruction)var8.q();
                     var4.set(var5, var6);
                     if (var8.RF()) {
                        var1[0] = true;
                     }

                     var7++;
                  }
               }

               if (var7 == 0) {
                  if (this.RF && var6.isAssign()) {
                     var7 += this.q(var6, var6.getAssignSource(), var1, null);
                  } else {
                     for (IDExpression var11 : var6.getSubExpressions()) {
                        var7 += this.q(var6, var11, var1, null);
                     }
                  }
               }

               var2 += var7;
            } while (var7 > 0);
         }
      }

      this.xK = null;
      if (var1[0]) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var2;
   }

   public IDExpression q(IDExpression var1, IDMethodContext var2) {
      this.ctx = var2;
      IDExpression[] var3 = new IDExpression[1];
      int var4 = this.q(null, var1, null, var3);
      if (var4 <= 0) {
         return null;
      } else {
         return var3[0] != null ? var3[0] : var1;
      }
   }

   private int q(IDExpression var1, IDExpression var2, boolean[] var3, IDExpression[] var4) {
      bvx var5 = this.q(var2);
      if (var5 != null) {
         if (var1 == null) {
            if (var4 != null) {
               var4[0] = var5.q();
            }
         } else if (!var1.replaceSubExpression(var2, var5.q())) {
            return 0;
         }

         if (var5.RF() && var3 != null) {
            var3[0] = true;
         }

         return 1;
      } else {
         int var6 = 0;

         for (IDExpression var8 : var2.getSubExpressions()) {
            var6 += this.q(var2, var8, var3, var4);
         }

         return var6;
      }
   }

   protected abstract bvx q(IDExpression var1);

   protected IDExpression q(IDExpression var1, bxp... var2) {
      for (bxp var6 : var2) {
         bxa var7 = new bxa(this.ctx, var6.q());
         if (var7.q(var1)) {
            return new bwy(this.ctx, var6.RF()).q(var7.xK());
         }
      }

      return null;
   }
}
