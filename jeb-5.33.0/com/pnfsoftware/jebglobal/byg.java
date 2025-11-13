package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.ir.AbstractDOptimizer;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOptimizerType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class byg extends AbstractDOptimizer {
   static boolean pC = false;

   public byg() {
      super(DOptimizerType.UNSAFE);
      this.addTag("deobfuscator");
      bpl.kS(this);
   }

   @Override
   public int perform() {
      byg.Av var1 = new byg.Av();

      for (IDInstruction var3 : this.cfg.instructions()) {
         var1.pC = var3;
         var3.visitInstructionPostOrder(var1, true);
      }

      int var4 = var1.A;
      if (var4 > 0) {
         this.cfg.invalidateDataFlowAnalysis();
      }

      return var4;
   }

   IDExpression pC(IDOperation var1, IDInstruction var2) {
      if (var1.getLeft() instanceof IDOperation var3) {
         if (!var3.getOperator().is(JavaOperatorType.REM)) {
            return null;
         } else {
            Integer var16 = this.pC(var3.getRight(), var2, true);
            if (var16 != null && var16 == 2) {
               var16 = this.pC(var1.getRight(), var2, true);
               if (var16 == null) {
                  return null;
               } else if (var16 != 0 && var16 != 1) {
                  return null;
               } else {
                  int var5 = var16;
                  if (var3.getLeft() instanceof IDOperation var6) {
                     if (!var6.getOperator().is(JavaOperatorType.MUL)) {
                        return null;
                     } else {
                        IDExpression var8;
                        IDOperation var19;
                        if (var6.getLeft() instanceof IDOperation) {
                           var19 = (IDOperation)var6.getLeft();
                           var8 = var6.getRight();
                        } else {
                           if (!(var6.getRight() instanceof IDOperation)) {
                              return null;
                           }

                           var19 = (IDOperation)var6.getRight();
                           var8 = var6.getLeft();
                        }

                        if (!var19.getOperator().is(JavaOperatorType.ADD)) {
                           return null;
                        } else {
                           IDExpression var9 = var19.getLeft();
                           IDExpression var10 = var19.getRight();
                           IDExpression var12;
                           if (var9.equalsEx(var8, false)) {
                              var12 = var10;
                           } else if (var10.equalsEx(var8, false)) {
                              var12 = var9;
                           } else {
                              Integer var13 = this.pC(var9, var2, true);
                              if (var13 == null) {
                                 return null;
                              }

                              Integer var14 = this.pC(var8, var2, true);
                              if (var14 == null) {
                                 return null;
                              }

                              if (var13.equals(var14)) {
                                 var12 = var10;
                              } else {
                                 Integer var15 = this.pC(var10, var2, true);
                                 if (var15 == null) {
                                    return null;
                                 }

                                 if (!var15.equals(var14)) {
                                    return null;
                                 }

                                 var12 = var9;
                              }
                           }

                           var16 = this.pC(var12, var2, true);
                           if (var16 == null || var16 % 2 != 1) {
                              return null;
                           } else if (var1.getOperator().is(JavaOperatorType.EQ)) {
                              return this.g.createBoolean(var5 == 0);
                           } else {
                              return var1.getOperator().is(JavaOperatorType.NE) ? this.g.createBoolean(var5 != 0) : null;
                           }
                        }
                     }
                  } else {
                     return null;
                  }
               }
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   Integer pC(IDExpression var1, IDInstruction var2, boolean var3) {
      return this.pC(var1, var2, var3, 2);
   }

   Integer pC(IDExpression var1, IDInstruction var2, boolean var3, int var4) {
      if (var4 < 0 || var1.getType() != this.tf.getInt()) {
         return null;
      } else if (var1 instanceof IDImm var12) {
         try {
            return (int)var12.toLong();
         } catch (DexDecEvaluationException var9) {
            return null;
         }
      } else if (var1 instanceof IDStaticField var5 && var5.getIndex() != null) {
         int var14 = var5.getIndex().getValue();
         if (!"I".equals(this.dex.getField(var14).getFieldTypeSignature(false))) {
            return null;
         } else {
            if (pC) {
               for (IDexAddress var8 : this.dex.getReferenceManager().getReferences(DexPoolType.FIELD, var14)) {
                  if (var8.getReferenceType() != DexReferenceType.GET) {
                     return null;
                  }
               }
            }

            IDexValue var16 = this.dex.getStaticFieldInitializer(var14);
            if (var16 == null) {
               return 0;
            } else {
               return var16.getType() == 4 ? var16.getInt() : null;
            }
         }
      } else if (var1 instanceof IDVar var10 && var2 != null) {
         this.analyzeChains(false);
         Long var13 = this.dfa.checkSingleDef(var2.getOffset(), var10.getId());
         if (var13 != null) {
            IDInstruction var7 = (IDInstruction)this.cfg.getInstruction(var13);
            if (var7 != null && var7.isAssign()) {
               return this.pC(var7.getAssignSource(), var2, var3, var4 - 1);
            }
         }

         return null;
      } else {
         if (var1 instanceof IDCallInfo var11 && var3) {
            IDExpression var6 = bpl.pC(this.ctx, var11);
            if (var6 != null) {
               return this.pC(var6, null, var3, var4 - 1);
            }
         }

         return null;
      }
   }

   class Av implements IDVisitor {
      IDInstruction pC;
      int A;

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDOperation var4 && var4.getOperatorType().isAnyOf(JavaOperatorType.EQ, JavaOperatorType.NE)) {
            IDExpression var5 = byg.this.pC(var4, this.pC);
            if (var5 != null && var2.replaceSubExpression(var1, var5)) {
               this.A++;
            }
         }
      }
   }
}
