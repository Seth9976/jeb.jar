package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class ccu extends AbstractDOptimizer {
   public ccu() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      ccu.eo var1 = new ccu.eo();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstructionPostOrder(var1, true);
      }

      if (var1.RF) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.q;
   }

   private class eo implements IDVisitor {
      int q;
      boolean RF;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         IDExpression var4 = null;
         if (var4 == null) {
            var4 = this.q(var1);
         }

         if (var4 != null && var2.replaceSubExpression(var1, var4)) {
            this.q++;
         }
      }

      IDExpression q(IDExpression var1) {
         if (var1 instanceof IDOperation var2 && var2.getOperator().isAnyOf(JavaOperatorType.SHL, JavaOperatorType.SHR, JavaOperatorType.USHR)) {
            IDExpression var3 = var2.getLeft();
            if (var3 instanceof IDImm var4 && var4.isZero()) {
               IDExpression var5 = var2.getRight();
               this.RF(var5);
               var3.transferMetadataFrom(var1);
               return var3;
            }
         }

         return null;
      }

      void RF(IDExpression var1) {
         if (!this.RF && DUtil.hasVariables(var1)) {
            this.RF = true;
         }
      }
   }
}
