package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICVisitor;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aiz extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICWhileStm
            && var3 >= 1
            && var1.get(var3 - 1) instanceof ICAssignment
            && !((ICWhileStm)var4).getPredicate().isLitteralTrue()
            && this.q(var1, var3)) {
            var2++;
         }
      }

      return var2;
   }

   private boolean q(ICBlock var1, int var2) {
      ICAssignment var3 = (ICAssignment)var1.get(var2 - 1);
      if (!var3.isSimpleAssignment()) {
         return false;
      } else {
         ICIdentifier var4;
         if (var3.getLeft() instanceof ICDecl) {
            ICDecl var5 = (ICDecl)var3.getLeft();
            var4 = var5.getIdentifier();
         } else {
            if (!(var3.getLeft() instanceof ICIdentifier)) {
               return false;
            }

            var4 = (ICIdentifier)var3.getLeft();
         }

         ICExpression var12 = var3.getRight();
         ICWhileStm var6 = (ICWhileStm)var1.get(var2);
         ICBlock var7 = var6.getBody();
         if (var7.size() == 0) {
            return false;
         } else {
            ICStatement var8 = var7.getLast();
            if (var8 instanceof ICAssignment && ((ICAssignment)var8).getLeft() == var4) {
               ICAssignment var9 = this.ef.createAssignment(var4, var12);
               if (var12 instanceof ICCall && var9.equals(var8)) {
                  return false;
               } else {
                  ICPredicate var10 = this.q(var6.getPredicate(), var4);
                  var7.removeLast();
                  ICForStm var11 = this.ef.createForLoop(var9, var10, var8, var7);
                  var11.setPhysicalOffsets(var6.getPhysicalOffsets());
                  var11.setIntermediateOffset(var6.getIntermediateOffset());
                  var1.set(var2, var11);
                  if (var3.getLeft() instanceof ICDecl) {
                     var1.set(var2 - 1, (ICDecl)var3.getLeft());
                  } else if (var3.getLeft() instanceof ICIdentifier) {
                     var1.remove(var2 - 1);
                  }

                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }

   private ICPredicate q(ICPredicate var1, ICIdentifier var2) {
      if (var1.getExpression() instanceof ICOperation) {
         ICOperation var3 = (ICOperation)var1.getExpression();
         COperatorType var4 = var3.getOperatorType();
         if (var3.getCountOfOperands() != 2 || var3.getFirstOperand().equals(var2) || var4.mirror() == null) {
            return var1;
         }

         if (var3.getSecondOperand().equals(var2)) {
            var3.mirror(this.of);
            return var1;
         }

         aiz.eo var5 = new aiz.eo(var2);
         if (var3.getFirstOperand().visitDepthPost(var5)) {
            if (var3.getSecondOperand().visitDepthPost(var5)) {
               return var1;
            }

            if (!var3.mirror(this.of)) {
               return var1;
            }
         }

         ICPredicate var6 = this.q(var3.getFirstOperand(), var3.getSecondOperand(), var3.getOperator(), var5, false);
         if (var6 != null) {
            return var6;
         }
      }

      return var1;
   }

   private ICPredicate q(ICExpression var1, ICExpression var2, ICOperator var3, ICVisitor var4, boolean var5) {
      if (var1 instanceof ICOperation var6) {
         COperatorType var7 = var6.getOperatorType();
         if (var6.getCountOfOperands() == 2 && (var7 == COperatorType.ADD || var7 == COperatorType.SUB) && !var6.getFirstOperand().visitDepthPost(var4)) {
            ICOperation var8 = this.ef.createOperation(var7 == COperatorType.ADD ? COperatorType.SUB : COperatorType.ADD, var2, var6.getSecondOperand());
            return this.q(var6.getFirstOperand(), var8, var3, var4, true);
         }
      }

      return var5 ? this.ef.createPredicate(this.ef.createOperation(var3, var1, var2)) : null;
   }

   private static class eo implements ICVisitor {
      private ICIdentifier q;

      public eo(ICIdentifier var1) {
         this.q = var1;
      }

      public void q(ICElement var1, ICElement var2, IVisitResults var3) {
         if (var1.equals(this.q)) {
            var3.interrupt(false);
         }
      }
   }
}
