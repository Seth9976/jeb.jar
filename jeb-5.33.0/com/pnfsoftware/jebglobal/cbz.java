package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class cbz extends AbstractDOptimizer {
   public cbz() {
      this.addTag("deobfuscator");
   }

   @Override
   public int perform() {
      cbz.Av var1 = new cbz.Av();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var3.visitInstruction(var1, true);
      }

      if (var1.pC > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var1.pC;
   }

   class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDOperation var4 && var4.checkType(cbz.this.tf.getBoolean())) {
            IDExpression var5 = this.pC(var4, var2);
            if (var5 != null) {
               if (var5 == var1) {
                  this.pC++;
               } else if (var2.replaceSubExpression(var1, var5)) {
                  var3.setReplacedNode(var5);
                  this.pC++;
               }
            }
         }
      }

      private IDExpression pC(IDOperation var1, IDExpression var2) {
         if (var2 instanceof IDOperation var3 && var3.isConditional() && var3.getCondPredicate() == var1) {
            if (var1.getOperatorType() == JavaOperatorType.NE) {
               var1.setOperator(JavaOperatorType.EQ, cbz.this.of);
               IDExpression var4 = var3.getLeft();
               var3.setLeft(var3.getRight());
               var3.setRight(var4);
               this.pC++;
            } else if (var1.getOperatorType() == JavaOperatorType.LOG_NOT) {
               IDExpression var18 = var1.getOperand2();
               var3.setCondPredicate(var18);
               IDExpression var5 = var3.getLeft();
               var3.setLeft(var3.getRight());
               var3.setRight(var5);
               this.pC++;
            }
         }

         JavaOperatorType var17 = var1.getOperatorType();
         IDExpression var19 = var1.getLeft();
         IDExpression var20 = var1.getRight();
         if (var17 == JavaOperatorType.EQ) {
            if (bpl.kS(var20)) {
               return var19;
            }

            if (bpl.kS(var19)) {
               return var20;
            }

            if (var20.getType().isBoolean() && var20 instanceof IDImm var6 && var6.isZero()) {
               var1.setOperator(JavaOperatorType.LOG_NOT, cbz.this.of);
               var1.setRight(var19);
               var1.setLeft(null);
               return var1;
            }

            if (var19.getType().isBoolean() && var19 instanceof IDImm var21 && var21.isZero()) {
               var1.setOperator(JavaOperatorType.LOG_NOT, cbz.this.of);
               var1.setLeft(null);
               return var1;
            }
         }

         if (var17 == JavaOperatorType.NE) {
            if (bpl.wS(var20)) {
               return var19;
            }

            if (bpl.wS(var19)) {
               return var20;
            }

            if (var20.getType().isBoolean() && var20 instanceof IDImm var22 && !var22.isZero()) {
               var1.setOperator(JavaOperatorType.LOG_NOT, cbz.this.of);
               var1.setRight(var19);
               var1.setLeft(null);
               return var1;
            }

            if (var19.getType().isBoolean() && var19 instanceof IDImm var23 && !var23.isZero()) {
               var1.setOperator(JavaOperatorType.LOG_NOT, cbz.this.of);
               var1.setLeft(null);
               return var1;
            }
         }

         if (var17 == JavaOperatorType.LOG_IDENT) {
            return var20;
         } else if (var17 == JavaOperatorType.LOG_NOT && var20 instanceof IDOperation var24 && var24.canReverse()) {
            var24.reverse();
            return var24;
         } else {
            if (var19 instanceof IDOperation var25
               && var20 instanceof IDImm var7
               && var25.isConditional()
               && var25.getLeft() instanceof IDImm var8
               && var25.getRight() instanceof IDImm var9) {
               try {
                  IDImm var27 = cbz.this.g.createOperation(null, var17, var8, var7).evaluate(cbz.this.ctx);
                  IDImm var11 = cbz.this.g.createOperation(null, var17, var9, var7).evaluate(cbz.this.ctx);
                  boolean var12 = var27.isZero();
                  boolean var13 = var11.isZero();
                  if (!var12 && var13) {
                     return var25.getCondPredicate();
                  }

                  if (var12 && !var13 && var25.getCondPredicate() instanceof IDOperation var14 && var14.canReverse()) {
                     var14.reverse();
                     return var14;
                  }
               } catch (DexDecEvaluationException var16) {
               }
            }

            return null;
         }
      }
   }
}
