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

public class cbd extends AbstractDOptimizer {
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
                     cbd.Av var10 = new cbd.Av(var2);
                     if (var10.pC() && var10.A()) {
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

   private class Av {
      int pC;
      int A = -1;
      int kS = -1;
      int wS = -1;
      int UT = -1;
      int[] E;

      Av(int var2) {
         if (var2 < 2) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var2;
         }
      }

      boolean pC() {
         BasicBlock var1 = cbd.this.cfg.get(this.pC - 1);
         if (var1.insize() != 2) {
            return false;
         } else if (var1.size() != 1) {
            return false;
         } else {
            IDInstruction var2 = (IDInstruction)var1.get(0);
            if (!this.pC(var2)) {
               return false;
            } else {
               BasicBlock var3 = cbd.this.cfg.get(this.pC);
               if (var3.insize() != 1) {
                  return false;
               } else if (var3.size() != 3) {
                  return false;
               } else {
                  var2 = (IDInstruction)var3.get(1);
                  if (!this.A(var2)) {
                     return false;
                  } else {
                     var2 = (IDInstruction)var3.get(2);
                     if (!var2.isJump()) {
                        return false;
                     } else if (var2.getBranchTarget() != var1.getAddress()) {
                        return false;
                     } else {
                        BasicBlock var4 = cbd.this.cfg.get(this.pC - 2);
                        if (var4.size() < 2) {
                           return false;
                        } else {
                           var2 = (IDInstruction)var4.get(var4.size() - 1);
                           if (var2.isAssignToVar(this.A) && var2.getAssignSource().isConstantImm() && (int)var2.getAssignSource().asImm().getRawValue() == 0) {
                              var2 = (IDInstruction)var4.get(var4.size() - 2);
                              if (!this.kS(var2)) {
                                 return false;
                              } else {
                                 if (var4.size() >= 3) {
                                    var2 = (IDInstruction)var4.get(var4.size() - 3);
                                    this.wS(var2);
                                 }

                                 var2 = (IDInstruction)var3.get(0);
                                 return this.UT(var2);
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

      boolean A() {
         BasicBlock var1 = cbd.this.cfg.get(this.pC - 1);
         BasicBlock var2 = cbd.this.cfg.get(this.pC);
         if (cbd.this.cfg.deleteEdge(var1, var2, 0) != 1) {
            return false;
         } else {
            IDInstruction var3 = (IDInstruction)var1.get(0);
            var3.transformJcondToJump();
            BasicBlock var4 = cbd.this.cfg.get(this.pC - 2);
            var3 = (IDInstruction)var4.get(var4.size() - 2);
            var3.setAssignSource(cbd.this.g.createIntArray(this.E));
            return true;
         }
      }

      private boolean pC(IDInstruction var1) {
         if (var1.isJcond()) {
            IDExpression var2 = var1.getJcondCondition();
            if (var2.isOperation(JavaOperatorType.GE, JavaOperatorType.EQ)) {
               IDOperation var3 = var2.asOperation();
               IDExpression var4 = var3.getOperand1();
               IDExpression var5 = var3.getOperand2();
               if (var4 instanceof IDVar var6 && var5 instanceof IDImm var7) {
                  this.A = var6.getId();
                  this.kS = (int)var7.getRawValue();
                  return true;
               }
            }
         }

         return false;
      }

      private boolean A(IDInstruction var1) {
         if (var1.isAssignToVar(this.A)) {
            IDExpression var2 = var1.getAssignSource();
            if (var2.isOperation(JavaOperatorType.ADD)) {
               IDExpression var3 = var2.asOperation().getOperand1();
               IDExpression var4 = var2.asOperation().getOperand2();
               if (var3.isVar(this.A) && var4.isConstantImm() && (int)var4.asImm().getRawValue() == 1) {
                  return true;
               }
            }
         }

         return false;
      }

      private boolean kS(IDInstruction var1) {
         if (var1.isAssignToVar()
            && var1.getAssignSource() instanceof IDNewArrayInfo var3
            && var3.getCountOfInitialValues() == 0
            && var3.getType().getArrayTypeDimensionBelow() == cbd.this.tf.getInt()) {
            IDExpression var4 = var3.getSize();
            if (var4.isConstantImm()) {
               int var5 = (int)var4.asImm().getRawValue();
               if (var5 == this.kS) {
                  this.wS = var1.getAssignDestination().asVar().getId();
                  return true;
               }
            }
         }

         return false;
      }

      private boolean wS(IDInstruction var1) {
         if (var1.isAssignToVar()) {
            IDExpression var2 = var1.getAssignSource();
            if (this.pC(var2)) {
               this.UT = var1.getAssignDestination().asVar().getId();
               return true;
            }
         }

         return false;
      }

      private boolean pC(IDExpression var1) {
         if (var1.isCastOperation()) {
            var1 = var1.asOperation().getOperand2();
         }

         if (var1 instanceof IDNewArrayInfo var2 && var2.getType().getArrayTypeDimensionBelow().isObject() && var2.getCountOfInitialValues() == this.kS) {
            int var3 = 0;
            this.E = new int[this.kS];

            for (IDExpression var5 : var2.getInitialValues()) {
               if (!var5.isConstantImm() || var5.getType() != cbd.this.tf.getJavaLangString()) {
                  return false;
               }

               String var6 = var5.asImm().getStringValue(cbd.this.g);

               try {
                  this.E[var3] = Integer.parseInt(var6);
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

      private boolean UT(IDInstruction var1) {
         if (var1.isAssign() && var1.getAssignDestination() instanceof IDArrayElt var3 && var3.getArray().isVar(this.wS) && var3.getIndex().isVar(this.A)) {
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
               if (var4.isCastOperation(cbd.this.tf.getJavaLangString())) {
                  var4 = var4.asOperation().getOperand2();
               }

               if (!(var4 instanceof IDArrayElt var9)) {
                  return false;
               }

               IDExpression var7 = var9.getArray();
               IDExpression var8 = var9.getIndex();
               if (this.E != null) {
                  if (var7.isVar(this.UT) && var8.isVar(this.A)) {
                     return true;
                  }
               } else if (this.pC(var7) && var8.isVar(this.A)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
