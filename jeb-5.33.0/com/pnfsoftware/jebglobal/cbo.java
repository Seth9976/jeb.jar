package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class cbo extends AbstractDOptimizer {
   public cbo() {
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      int var1 = 0;
      cbo.Av var2 = new cbo.Av();

      for (BasicBlock var4 : this.cfg) {
         for (IDInstruction var6 : var4) {
            var6.visitInstructionPostOrder(var2, true);
         }
      }

      var1 += var2.pC;
      if (var1 > 0) {
         this.invalidateChains();
      }

      return var1;
   }

   private class Av implements IDVisitor {
      int pC;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         IDExpression var4 = null;
         if (var4 == null) {
            var4 = this.pC(var1);
         }

         if (var4 == null) {
            var4 = this.A(var1);
         }

         if (var4 == null) {
            var4 = this.kS(var1);
         }

         if (var4 != null && var2.replaceSubExpression(var1, var4)) {
            this.pC++;
         }
      }

      IDExpression pC(IDExpression var1) {
         if (var1 instanceof IDOperation && ((IDOperation)var1).getOperator().is(JavaOperatorType.ADD)) {
            IDExpression var2 = ((IDOperation)var1).getLeft();
            IDExpression var3 = ((IDOperation)var1).getRight();
            if (var2 instanceof IDOperation && var3 instanceof IDOperation) {
               IJavaOperator var4 = ((IDOperation)var2).getOperator();
               IJavaOperator var5 = ((IDOperation)var3).getOperator();
               IDOperation var6;
               IDOperation var7;
               if (var4.is(JavaOperatorType.XOR) && var5.is(JavaOperatorType.SHL)) {
                  var6 = (IDOperation)var2;
                  var7 = (IDOperation)var3;
               } else {
                  if (!var4.is(JavaOperatorType.SHL) || !var5.is(JavaOperatorType.XOR)) {
                     return null;
                  }

                  var6 = (IDOperation)var3;
                  var7 = (IDOperation)var2;
               }

               IDExpression var8 = var6.getLeft();
               IDExpression var9 = var6.getRight();
               if (var8.hasSideEffects(cbo.this.ctx, false) || var9.hasSideEffects(cbo.this.ctx, false)) {
                  return null;
               }

               IDExpression var10 = var7.getLeft();
               IDExpression var11 = var7.getRight();
               if (var11 instanceof IDImm
                  && ((IDImm)var11).getRawValue() == 1L
                  && var10 instanceof IDOperation
                  && ((IDOperation)var10).getOperator().is(JavaOperatorType.AND)) {
                  IDExpression var12 = ((IDOperation)var10).getLeft();
                  IDExpression var13 = ((IDOperation)var10).getRight();
                  if (var12.equalsEx(var8, false) && var13.equalsEx(var9, false) || var12.equalsEx(var9, false) && var13.equalsEx(var8, false)) {
                     IDOperation var14 = cbo.this.g.createOperation(null, JavaOperatorType.ADD, var8, var9);
                     var14.transferMetadataFrom(var1);
                     return var14;
                  }
               }
            }
         }

         return null;
      }

      IDExpression A(IDExpression var1) {
         if (var1 instanceof IDOperation && ((IDOperation)var1).getOperator().is(JavaOperatorType.SUB)) {
            IDExpression var2 = ((IDOperation)var1).getLeft();
            IDExpression var3 = ((IDOperation)var1).getRight();
            if (var2 instanceof IDOperation && var3 instanceof IDOperation) {
               IJavaOperator var4 = ((IDOperation)var2).getOperator();
               IJavaOperator var5 = ((IDOperation)var3).getOperator();
               if (!var4.is(JavaOperatorType.SHL) || !var5.is(JavaOperatorType.XOR)) {
                  return null;
               }

               IDOperation var6 = (IDOperation)var3;
               IDOperation var7 = (IDOperation)var2;
               IDExpression var8 = var6.getLeft();
               IDExpression var9 = var6.getRight();
               if (var8.hasSideEffects(cbo.this.ctx, false) || var9.hasSideEffects(cbo.this.ctx, false)) {
                  return null;
               }

               IDExpression var10 = var7.getLeft();
               IDExpression var11 = var7.getRight();
               if (var11 instanceof IDImm
                  && ((IDImm)var11).getRawValue() == 1L
                  && var10 instanceof IDOperation
                  && ((IDOperation)var10).getOperator().is(JavaOperatorType.OR)) {
                  IDExpression var12 = ((IDOperation)var10).getLeft();
                  IDExpression var13 = ((IDOperation)var10).getRight();
                  if (var12.equalsEx(var8, false) && var13.equalsEx(var9, false) || var12.equalsEx(var9, false) && var13.equalsEx(var8, false)) {
                     IDOperation var14 = cbo.this.g.createOperation(null, JavaOperatorType.ADD, var8, var9);
                     var14.transferMetadataFrom(var1);
                     return var14;
                  }
               }
            }
         }

         return null;
      }

      IDExpression kS(IDExpression var1) {
         if (var1 instanceof IDOperation && ((IDOperation)var1).getOperator().is(JavaOperatorType.ADD)) {
            IDExpression var2 = ((IDOperation)var1).getLeft();
            IDExpression var3 = ((IDOperation)var1).getRight();
            if (var2 instanceof IDOperation && var3 instanceof IDOperation) {
               IJavaOperator var4 = ((IDOperation)var2).getOperator();
               IJavaOperator var5 = ((IDOperation)var3).getOperator();
               IDOperation var6;
               IDOperation var7;
               if (var4.is(JavaOperatorType.OR) && var5.is(JavaOperatorType.AND)) {
                  var6 = (IDOperation)var2;
                  var7 = (IDOperation)var3;
               } else {
                  if (!var4.is(JavaOperatorType.AND) || !var5.is(JavaOperatorType.OR)) {
                     return null;
                  }

                  var6 = (IDOperation)var3;
                  var7 = (IDOperation)var2;
               }

               IDExpression var8 = var6.getLeft();
               IDExpression var9 = var6.getRight();
               if (var8.hasSideEffects(cbo.this.ctx, false) || var9.hasSideEffects(cbo.this.ctx, false)) {
                  return null;
               }

               IDExpression var10 = var7.getLeft();
               IDExpression var11 = var7.getRight();
               if (var10.equalsEx(var8, false) && var11.equalsEx(var9, false) || var10.equalsEx(var9, false) && var11.equalsEx(var8, false)) {
                  IDOperation var12 = cbo.this.g.createOperation(null, JavaOperatorType.ADD, var8, var9);
                  var12.transferMetadataFrom(var1);
                  return var12;
               }
            }
         }

         return null;
      }
   }
}
