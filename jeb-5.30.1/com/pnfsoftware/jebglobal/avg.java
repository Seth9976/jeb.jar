package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class avg extends AbstractEExpressionOptimizer {
   public avg() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IEMem)) {
         return null;
      } else {
         IEGeneric var2 = var1.asMem().getReference();
         if (!var2.isOperation(OperationType.ADD, OperationType.SUB)) {
            return null;
         } else {
            IEGeneric var3 = this.q(var2);
            if (!(var3 instanceof IEImm)) {
               return null;
            } else {
               ano var4 = new ano(var2);
               if (!var4.q()) {
                  return null;
               } else {
                  long var5 = var4.oW();
                  if (var5 == 0L) {
                     return null;
                  } else if (!var4.Uv()) {
                     return null;
                  } else {
                     IEVar var7 = var4.xK();
                     if (!var7.isStackReference()) {
                        return null;
                     } else {
                        long var8 = var7.getAddress() + var5;
                        IEVar var10 = this.ectx.getStackVariable((int)var8);
                        if (var10 == null) {
                           this.ectx.getStackManager().createPureStackItem(var8, 8);
                        }

                        IEVar var11 = this.ectx.createStackReference(var8, null);
                        var4.q(var11, 0L);
                        IEGeneric var12 = var4.q(this.ectx);
                        return !var1.replaceSubExpression(var2, var12) ? null : AbstractEExpressionOptimizer.EOR.create(var1, true);
                     }
                  }
               }
            }
         }
      }
   }

   IEGeneric q(IEGeneric var1) {
      while (var1 instanceof IEOperation) {
         IEOperation var2 = var1.asOperation();
         if (var2.getOperationType() != OperationType.ADD && var2.getOperationType() != OperationType.SUB) {
            return var1;
         }

         var1 = var2.getOperand2();
      }

      return var1;
   }
}
