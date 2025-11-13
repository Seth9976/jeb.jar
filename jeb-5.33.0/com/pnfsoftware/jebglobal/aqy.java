package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.ArrayList;
import java.util.List;

public class aqy extends AbstractEExpressionOptimizer {
   public aqy() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   public AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (EUtil.isOperation(var1, OperationType.ADD, OperationType.SUB)) {
         IEOperation var2 = var1.asOperation();
         IEGeneric var3 = var2.getOperand1();
         IEGeneric var4 = var2.getOperand2();
         if ((var3 instanceof IEOperation || var4 instanceof IEOperation) && new aqy.Av().pC(var1)) {
            return AbstractEExpressionOptimizer.EOR.create(var1);
         }
      }

      return null;
   }

   static class Av {
      public Av() {
      }

      public boolean pC(IEGeneric var1) {
         if (!EUtil.isOperation(var1, OperationType.ADD, OperationType.SUB)) {
            return false;
         } else {
            IEOperation var2 = var1.asOperation();
            IEGeneric var3 = var2.getOperand1();
            IEGeneric var4 = var2.getOperand2();
            if (!(var3 instanceof IEOperation) && !(var4 instanceof IEOperation)) {
               return false;
            } else {
               aqy.Av.Av var5 = new aqy.Av.Av();
               if (var5.pC(var3)) {
                  aqy.Av.Av var6 = new aqy.Av.Av();
                  if (var6.pC(var4)) {
                     IEImm var7 = var5.pC(var2);
                     IEImm var8 = var6.pC(var2);
                     IEImm var9 = var7._add(var8);
                     byte var10;
                     if (var5.pC()) {
                        var10 = 1;
                     } else {
                        if (!var6.pC()) {
                           return false;
                        }

                        var10 = 2;
                     }

                     if (var10 == 1) {
                        var5.A();
                        var6.pC(var9);
                     } else {
                        var6.A();
                        var5.pC(var9);
                     }

                     return true;
                  }
               }

               return false;
            }
         }
      }

      private static class Av {
         private List pC = new ArrayList();
         private IEImm A;
         private int kS = -1;

         private boolean pC(IEGeneric var1) {
            if (var1 instanceof IEOperation) {
               IEOperation var2 = var1.asOperation();
               OperationType var3 = var2.getOperationType();
               if (var3 == OperationType.ADD || var3 == OperationType.SUB) {
                  this.pC.add(var2);
                  IEGeneric var4 = var2.getOperand1();
                  if (this.pC(var4)) {
                     return true;
                  }

                  IEGeneric var5 = var2.getOperand2();
                  if (this.pC(var5)) {
                     return true;
                  }

                  this.pC.remove(this.pC.size() - 1);
               }
            } else if (var1 instanceof IEImm) {
               this.A = var1.asImm();
               return true;
            }

            return false;
         }

         private IEImm pC(IEOperation var1) {
            if (this.A != null && this.kS < 0) {
               this.kS = 0;
               this.pC.add(0, var1);
               Object var2 = this.A;

               for (int var3 = this.pC.size() - 1; var3 >= 0; var3--) {
                  IEOperation var4 = (IEOperation)this.pC.get(var3);
                  if (var4.getOperationType() != OperationType.ADD && var4.getOperand1() != var2) {
                     this.kS++;
                  }

                  var2 = var4;
               }

               return (this.kS & 1) == 1 ? this.A._neg() : this.A;
            } else {
               throw new IllegalArgumentException();
            }
         }

         private boolean pC() {
            if (this.pC.size() < 2) {
               return false;
            } else {
               int var1 = this.pC.size() - 1;
               IEOperation var2 = (IEOperation)this.pC.get(var1);
               if (this.A == var2.getOperand2()) {
                  return true;
               } else {
                  if (var2.getOperationType() == OperationType.SUB) {
                     IEOperation var3 = (IEOperation)this.pC.get(var1 - 1);
                     if (var3.getOperand1() == var2) {
                        return false;
                     }
                  }

                  return true;
               }
            }
         }

         private boolean A() {
            int var1 = this.pC.size() - 1;
            IEOperation var2 = (IEOperation)this.pC.get(var1);
            IEOperation var3 = (IEOperation)this.pC.get(var1 - 1);
            if (this.A == var2.getOperand2()) {
               IEGeneric var5 = var2.getOperand1();
               return var3.replaceSubExpression(var2, var5);
            } else {
               IEGeneric var4 = var2.getOperand2();
               if (var2.getOperationType() == OperationType.SUB) {
                  if (var3.getOperand1() == var2) {
                     return false;
                  } else if (!var3.replaceSubExpression(var2, var4)) {
                     return false;
                  } else {
                     ((akc)var3).pC(var3.getOperationType() == OperationType.ADD ? OperationType.SUB : OperationType.ADD);
                     return true;
                  }
               } else {
                  return var3.replaceSubExpression(var2, var4);
               }
            }
         }

         private boolean pC(IEImm var1) {
            int var2 = this.pC.size() - 1;
            IEOperation var3 = (IEOperation)this.pC.get(var2);
            boolean var4;
            if ((this.kS & 1) == 0) {
               var4 = var3.replaceSubExpression(this.A, var1);
            } else {
               var4 = var3.replaceSubExpression(this.A, var1._neg());
            }

            Assert.a(var4);
            return true;
         }
      }
   }
}
