package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

public class byn extends AbstractDOptimizer {
   private bqi pC;

   public byn() {
      this.addTag("deobfuscator");
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bpl.pC(this.g)) {
         return 0;
      } else {
         if (this.pC == null) {
            this.pC = new bqi(this.g, this.ctx);
         }

         int var1 = 0;
         byn.Av var2 = new byn.Av();

         for (IDInstruction var4 : this.cfg.instructions()) {
            var2.pC = var4;
            if (!var4.visitInstructionPostOrder(var2, true)) {
               var4.transformToNop();
               var1++;
            }
         }

         var1 += var2.A;
         if (var1 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }

   class Av implements IDVisitor {
      IDInstruction pC;
      int A;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         IDImm var4 = null;
         if (var1 instanceof IDCallInfo var5) {
            var4 = byn.this.pC.pC(var5, this.pC);
         } else if (var1 instanceof IDOperation var6) {
            var4 = byn.this.pC.pC(var6, this.pC);
         }

         if (var4 != null) {
            if (var2 instanceof IDInstruction && var2.asInstruction().isInvoke()) {
               var3.interrupt(false);
            } else if (var2 != null && var2.replaceSubExpression(var1, var4)) {
               this.A++;
            }
         }
      }
   }
}
