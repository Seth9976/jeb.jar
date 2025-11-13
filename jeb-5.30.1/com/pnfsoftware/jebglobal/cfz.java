package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class cfz extends AbstractDOptimizer {
   @Override
   public int perform() {
      int var1 = 0;

      for (int var2 = 2; var2 < this.cfg.size(); var2++) {
         BasicBlock var3 = this.cfg.get(var2);
         if (var3.size() == 3) {
            IDInstruction var4 = (IDInstruction)var3.get(2);
            if (var4.isJump()) {
               var4 = (IDInstruction)var3.get(1);
               if (var4.isAssignToVar()) {
                  IDVar var5 = var4.getAssignDestination().asVar();
                  var4 = (IDInstruction)var3.get(0);
                  if (var4.isAssign() && var4.getAssignDestination() instanceof IDArrayElt var6 && var6.getIndex().isVar(var5.getId())) {
                     cfz.eo var10 = new cfz.eo(var2);
                     if (var10.q() && var10.RF()) {
                        var2 += 2;
                        var1++;
                     }
                  }
               }
            }
         }
      }

      if (var1 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
         this.cleanGraph();
      }

      return var1;
   }

   private class eo {
      int q;
      int RF = -1;
      int xK = -1;
      int Dw = -1;
      int Uv = -1;
      int[] oW;

      eo(int var2) {
         if (var2 < 2) {
            throw new IllegalArgumentException();
         } else {
            this.q = var2;
         }
      }

      boolean q() {
         BasicBlock var1 = cfz.this.cfg.get(this.q - 1);
         if (var1.insize() != 2) {
            return false;
         } else if (var1.size() != 1) {
            return false;
         } else {
            IDInstruction var2 = (IDInstruction)var1.get(0);
            if (!this.q(var2)) {
               return false;
            } else {
               BasicBlock var3 = cfz.this.cfg.get(this.q);
               if (var3.insize() != 1) {
                  return false;
               } else if (var3.size() != 3) {
                  return false;
               } else {
                  var2 = (IDInstruction)var3.get(1);
                  if (!this.RF(var2)) {
                     return false;
                  } else {
                     var2 = (IDInstruction)var3.get(2);
                     if (!var2.isJump()) {
                        return false;
                     } else if (var2.getBranchTarget() != var1.getAddress()) {
                        return false;
                     } else {
                        BasicBlock var4 = cfz.this.cfg.get(this.q - 2);
                        if (var4.size() < 2) {
                           return false;
                        } else {
                           var2 = (IDInstruction)var4.get(var4.size() - 1);
                           if (var2.isAssignToVar(this.RF) && var2.getAssignSource().isConstantImm() && (int)var2.getAssignSource().asImm().getRawValue() == 0) {
                              var2 = (IDInstruction)var4.get(var4.size() - 2);
                              if (!this.xK(var2)) {
                                 return false;
                              } else {
                                 if (var4.size() >= 3) {
                                    var2 = (IDInstruction)var4.get(var4.size() - 3);
                                    this.Dw(var2);
                                 }

                                 var2 = (IDInstruction)var3.get(0);
                                 return this.Uv(var2);
                              }
                           } else {
                              return false;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      boolean RF() {
         BasicBlock var1 = cfz.this.cfg.get(this.q - 1);
         BasicBlock var2 = cfz.this.cfg.get(this.q);
         if (cfz.this.cfg.deleteEdge(var1, var2, 0) != 1) {
            return false;
         } else {
            IDInstruction var3 = (IDInstruction)var1.get(0);
            var3.transformJcondToJump();
            BasicBlock var4 = cfz.this.cfg.get(this.q - 2);
            var3 = (IDInstruction)var4.get(var4.size() - 2);
            var3.setAssignSource(cfz.this.g.createIntArray(this.oW));
            return true;
         }
      }

      private boolean q(IDInstruction var1) {
         if (var1.isJcond()) {
            IDExpression var2 = var1.getJcondCondition();
            if (var2.isOperation(JavaOperatorType.GE, JavaOperatorType.EQ)) {
               IDOperation var3 = var2.asOperation();
               IDExpression var4 = var3.getOperand1();
               IDExpression var5 = var3.getOperand2();
               if (var4 instanceof IDVar var6 && var5 instanceof IDImm var7) {
                  this.RF = var6.getId();
                  this.xK = (int)var7.getRawValue();
                  return true;
               }
            }
         }

         return false;
      }

      private boolean RF(IDInstruction var1) {
         if (var1.isAssignToVar(this.RF)) {
            IDExpression var2 = var1.getAssignSource();
            if (var2.isOperation(JavaOperatorType.ADD)) {
               IDExpression var3 = var2.asOperation().getOperand1();
               IDExpression var4 = var2.asOperation().getOperand2();
               if (var3.isVar(this.RF) && var4.isConstantImm() && (int)var4.asImm().getRawValue() == 1) {
                  return true;
               }
            }
         }

         return false;
      }

      private boolean xK(IDInstruction var1) {
         if (var1.isAssignToVar()
            && var1.getAssignSource() instanceof IDNewArrayInfo var3
            && var3.getCountOfInitialValues() == 0
            && var3.getType().getArrayTypeDimensionBelow() == cfz.this.tf.getInt()) {
            IDExpression var4 = var3.getSize();
            if (var4.isConstantImm()) {
               int var5 = (int)var4.asImm().getRawValue();
               if (var5 == this.xK) {
                  this.Dw = var1.getAssignDestination().asVar().getId();
                  return true;
               }
            }
         }

         return false;
      }

      private boolean Dw(IDInstruction var1) {
         if (var1.isAssignToVar()) {
            IDExpression var2 = var1.getAssignSource();
            if (this.q(var2)) {
               this.Uv = var1.getAssignDestination().asVar().getId();
               return true;
            }
         }

         return false;
      }

      private boolean q(IDExpression var1) {
         if (var1.isCastOperation()) {
            var1 = var1.asOperation().getOperand2();
         }

         if (var1 instanceof IDNewArrayInfo var2 && var2.getType().getArrayTypeDimensionBelow().isObject() && var2.getCountOfInitialValues() == this.xK) {
            int var3 = 0;
            this.oW = new int[this.xK];

            for (IDExpression var5 : var2.getInitialValues()) {
               if (!var5.isConstantImm() || var5.getType() != cfz.this.tf.getJavaLangString()) {
                  return false;
               }

               String var6 = var5.asImm().getStringValue(cfz.this.g);

               try {
                  this.oW[var3] = Integer.parseInt(var6);
               } catch (NumberFormatException var7) {
                  return false;
               }

               var3++;
            }

            return true;
         } else {
            return false;
         }
      }

      private boolean Uv(IDInstruction var1) {
         if (var1.isAssign() && var1.getAssignDestination() instanceof IDArrayElt var3 && var3.getArray().isVar(this.Dw) && var3.getIndex().isVar(this.RF)) {
            IDExpression var4 = null;
            IDExpression var5 = var1.getAssignSource();
            if (var5.isCallInfo("Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I")) {
               var4 = var5.asCallInfo().getArgument(0);
            } else if (var5.isCallInfo("Ljava/lang/Integer;->intValue()I")) {
               IDExpression var6 = var5.asCallInfo().getArgument(0);
               if (var6.isCallInfo("Ljava/lang/Integer;->valueOf(Ljava/lang/String;)Ljava/lang/Integer;")) {
                  var4 = var6.asCallInfo().getArgument(0);
               }
            }

            if (var4 != null) {
               if (var4.isCastOperation(cfz.this.tf.getJavaLangString())) {
                  var4 = var4.asOperation().getOperand2();
               }

               if (!(var4 instanceof IDArrayElt var9)) {
                  return false;
               }

               IDExpression var7 = var9.getArray();
               IDExpression var8 = var9.getIndex();
               if (this.oW != null) {
                  if (var7.isVar(this.Uv) && var8.isVar(this.RF)) {
                     return true;
                  }
               } else if (this.q(var7) && var8.isVar(this.RF)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
