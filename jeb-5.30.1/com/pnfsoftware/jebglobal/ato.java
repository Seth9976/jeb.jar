package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import java.util.ArrayList;
import java.util.List;

public class ato extends AbstractEExpressionOptimizer {
   public ato() {
      super(DataChainsUpdatePolicy.UPDATE_NOT_NECESSARY);
   }

   @Override
   public AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1.isOperation(OperationType.AND, OperationType.OR, OperationType.XOR)) {
         IEOperation var2 = var1.asOperation();
         IEGeneric var3 = var2.getOperand1();
         IEGeneric var4 = var2.getOperand2();
         if ((var3 instanceof IEOperation || var4 instanceof IEOperation) && new ato.eo(var2.getOperationType()).q(var1)) {
            return AbstractEExpressionOptimizer.EOR.create(var1);
         }
      }

      return null;
   }

   static class eo {
      OperationType q;

      eo(OperationType var1) {
         this.q = var1;
      }

      boolean q(IEGeneric var1) {
         if (!var1.isOperation(this.q)) {
            return false;
         } else {
            IEOperation var2 = var1.asOperation();
            IEGeneric var3 = var2.getOperand1();
            IEGeneric var4 = var2.getOperand2();
            if (!(var3 instanceof IEOperation) && !(var4 instanceof IEOperation)) {
               return false;
            } else {
               ato.eo.eo var5 = new ato.eo.eo();
               if (var5.q(var3)) {
                  ato.eo.eo var6 = new ato.eo.eo();
                  if (var6.q(var4)) {
                     IEImm var7 = var5.q(var2);
                     IEImm var8 = var6.q(var2);
                     IEImm var9;
                     switch (this.q) {
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
                     if (var5.q()) {
                        var10 = 1;
                     } else {
                        if (!var6.q()) {
                           return false;
                        }

                        var10 = 2;
                     }

                     if (var10 == 1) {
                        var5.RF();
                        var6.q(var9);
                     } else {
                        var6.RF();
                        var5.q(var9);
                     }

                     return true;
                  }
               }

               return false;
            }
         }
      }

      class eo {
         List q = new ArrayList();
         IEImm RF;

         boolean q(IEGeneric var1) {
            if (var1 instanceof IEOperation) {
               IEOperation var2 = var1.asOperation();
               OperationType var3 = var2.getOperationType();
               if (var3 == eo.this.q) {
                  this.q.add(var2);
                  IEGeneric var4 = var2.getOperand1();
                  if (this.q(var4)) {
                     return true;
                  }

                  IEGeneric var5 = var2.getOperand2();
                  if (this.q(var5)) {
                     return true;
                  }

                  this.q.remove(this.q.size() - 1);
               }
            } else if (var1 instanceof IEImm) {
               this.RF = var1.asImm();
               return true;
            }

            return false;
         }

         private IEImm q(IEOperation var1) {
            if (this.RF == null) {
               throw new IllegalArgumentException();
            } else {
               this.q.add(0, var1);
               return this.RF;
            }
         }

         boolean q() {
            return this.q.size() >= 2;
         }

         boolean RF() {
            int var1 = this.q.size() - 1;
            IEOperation var2 = (IEOperation)this.q.get(var1);
            IEOperation var3 = (IEOperation)this.q.get(var1 - 1);
            IEGeneric var4;
            if (this.RF == var2.getOperand1()) {
               var4 = var2.getOperand2();
            } else {
               var4 = var2.getOperand1();
            }

            return var3.replaceSubExpression(var2, var4);
         }

         boolean q(IEImm var1) {
            int var2 = this.q.size() - 1;
            IEOperation var3 = (IEOperation)this.q.get(var2);
            return var3.replaceSubExpression(this.RF, var1);
         }
      }
   }
}
