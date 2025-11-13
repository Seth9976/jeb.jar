package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.ArrayList;
import java.util.List;

public class aqz extends AbstractEExpressionOptimizer {
   public aqz() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   public AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1.isOperation(OperationType.AND, OperationType.OR, OperationType.XOR)) {
         IEOperation var2 = var1.asOperation();
         IEGeneric var3 = var2.getOperand1();
         IEGeneric var4 = var2.getOperand2();
         if ((var3 instanceof IEOperation || var4 instanceof IEOperation) && new aqz.Av(var2.getOperationType()).pC(var1)) {
            return AbstractEExpressionOptimizer.EOR.create(var1);
         }
      }

      return null;
   }

   static class Av {
      OperationType pC;

      Av(OperationType var1) {
         this.pC = var1;
      }

      boolean pC(IEGeneric var1) {
         if (!var1.isOperation(this.pC)) {
            return false;
         } else {
            IEOperation var2 = var1.asOperation();
            IEGeneric var3 = var2.getOperand1();
            IEGeneric var4 = var2.getOperand2();
            if (!(var3 instanceof IEOperation) && !(var4 instanceof IEOperation)) {
               return false;
            } else {
               aqz.Av.Av var5 = new aqz.Av.Av();
               if (var5.pC(var3)) {
                  aqz.Av.Av var6 = new aqz.Av.Av();
                  if (var6.pC(var4)) {
                     IEImm var7 = var5.pC(var2);
                     IEImm var8 = var6.pC(var2);
                     IEImm var9;
                     switch (this.pC) {
                        case AND:
                           var9 = var7._and(var8);
                           break;
                        case OR:
                           var9 = var7._or(var8);
                           break;
                        case XOR:
                           var9 = var7._xor(var8);
                           break;
                        default:
                           return false;
                     }

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

      class Av {
         List pC = new ArrayList();
         IEImm A;

         boolean pC(IEGeneric var1) {
            if (var1 instanceof IEOperation) {
               IEOperation var2 = var1.asOperation();
               OperationType var3 = var2.getOperationType();
               if (var3 == Av.this.pC) {
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
            if (this.A == null) {
               throw new IllegalArgumentException();
            } else {
               this.pC.add(0, var1);
               return this.A;
            }
         }

         boolean pC() {
            return this.pC.size() >= 2;
         }

         boolean A() {
            int var1 = this.pC.size() - 1;
            IEOperation var2 = (IEOperation)this.pC.get(var1);
            IEOperation var3 = (IEOperation)this.pC.get(var1 - 1);
            IEGeneric var4;
            if (this.A == var2.getOperand1()) {
               var4 = var2.getOperand2();
            } else {
               var4 = var2.getOperand1();
            }

            return var3.replaceSubExpression(var2, var4);
         }

         boolean pC(IEImm var1) {
            int var2 = this.pC.size() - 1;
            IEOperation var3 = (IEOperation)this.pC.get(var2);
            return var3.replaceSubExpression(this.A, var1);
         }
      }
   }
}
