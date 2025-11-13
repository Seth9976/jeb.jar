package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

public class cdd extends AbstractDOptimizer {
   private buo q;

   public cdd() {
      this.addTag("deobfuscator");
      this.addTag("inliner");
   }

   @Override
   public int perform() {
      if (bto.q(this.g)) {
         return 0;
      } else {
         if (this.q == null) {
            this.q = new buo(this.g, this.ctx);
         }

         int var1 = 0;
         cdd.eo var2 = new cdd.eo();

         for (IDInstruction var4 : this.cfg.instructions()) {
            var2.q = var4;
            if (!var4.visitInstructionPostOrder(var2, true)) {
               var4.transformToNop();
               var1++;
            }
         }

         var1 += var2.RF;
         if (var1 > 0) {
            this.cfg.invalidateDataFlowAnalysis();
         }

         return var1;
      }
   }

   class eo implements IDVisitor {
      IDInstruction q;
      int RF;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         IDImm var4 = null;
         if (var1 instanceof IDCallInfo var5) {
            var4 = cdd.this.q.q(var5, this.q);
         } else if (var1 instanceof IDOperation var6) {
            var4 = cdd.this.q.q(var6, this.q);
         }

         if (var4 != null) {
            if (var2 instanceof IDInstruction && var2.asInstruction().isInvoke()) {
               var3.interrupt(false);
            } else if (var2 != null && var2.replaceSubExpression(var1, var4)) {
               this.RF++;
            }
         }
      }
   }
}
