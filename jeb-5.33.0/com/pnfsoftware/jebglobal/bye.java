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

public class bye extends AbstractDOptimizer {
   public bye() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      bye.Av var1 = new bye.Av();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstructionPostOrder(var1, true);
      }

      if (var1.A) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.pC;
   }

   private class Av implements IDVisitor {
      int pC;
      boolean A;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         IDExpression var4 = null;
         if (var4 == null) {
            var4 = this.pC(var1);
         }

         if (var4 != null && var2.replaceSubExpression(var1, var4)) {
            this.pC++;
         }
      }

      IDExpression pC(IDExpression var1) {
         if (var1 instanceof IDOperation var2 && var2.getOperator().isAnyOf(JavaOperatorType.SHL, JavaOperatorType.SHR, JavaOperatorType.USHR)) {
            IDExpression var3 = var2.getLeft();
            if (var3 instanceof IDImm var4 && var4.isZero()) {
               IDExpression var5 = var2.getRight();
               this.A(var5);
               var3.transferMetadataFrom(var1);
               return var3;
            }
         }

         return null;
      }

      void A(IDExpression var1) {
         if (!this.A && DUtil.hasVariables(var1)) {
            this.A = true;
         }
      }
   }
}
