package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;

public class cft extends AbstractDOptimizer {
   public cft() {
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      cft.eo var1 = new cft.eo();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstruction(var1, true);
      }

      if (var1.q > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.q;
   }

   class eo implements IDVisitor {
      int q;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDArrayElt var4 && var4.getIndex() instanceof IDImm var5 && var4.getArray() instanceof IDNewArrayInfo var6) {
            int var11 = (int)var5.getRawValue();
            if (var11 >= 0 && var11 < var6.getCountOfInitialValues()) {
               for (IDExpression var9 : var6.getInitialValues()) {
                  if (var9.hasSideEffects(cft.this.ctx, true)) {
                     return;
                  }
               }

               IDExpression var12 = var6.getInitialValue(var11);
               if (var12 != null && var2.replaceSubExpression(var1, var12)) {
                  var3.setReplacedNode(var12);
                  this.q++;
               }
            }
         }
      }
   }
}
