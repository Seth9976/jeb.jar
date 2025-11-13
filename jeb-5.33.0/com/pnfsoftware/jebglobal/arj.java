package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class arj extends AbstractEExpressionOptimizer {
   public arj() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IEOperation var2)) {
         return null;
      } else {
         OperationType var3 = var2.getOperationType();
         if (var3.isFloatConversion()) {
            IEGeneric var4 = var2.getOperand1();
            if (var4 instanceof IEOperation && ((IEOperation)var4).getOperationType().isFloatConversion()) {
               IEOperation var5 = (IEOperation)var4;
               OperationType var6 = var5.getOperationType();
               if (var3 == OperationType.FP2FP) {
                  if (var6 == OperationType.FP2FP) {
                     IEOperation var12 = this.ectx.createConversionOperation(OperationType.FP2FP, var5.getOperand1(), var2.getBitsize());
                     return AbstractEExpressionOptimizer.EOR.create(var12);
                  }

                  if (var6 == OperationType.INT2FP || var6 == OperationType.UINT2FP) {
                     IEOperation var7 = this.ectx.createConversionOperation(var6, var5.getOperand1(), var2.getBitsize());
                     return AbstractEExpressionOptimizer.EOR.create(var7);
                  }
               } else if (var3 == OperationType.FP2INT) {
                  if (var6 == OperationType.FP2FP) {
                     IEOperation var14 = this.ectx.createConversionOperation(OperationType.FP2INT, var5.getOperand1(), var2.getBitsize());
                     return AbstractEExpressionOptimizer.EOR.create(var14);
                  }

                  if (var6 == OperationType.INT2FP) {
                     Object var13;
                     if (var5.getOperand1().getBitsize() == var2.getBitsize()) {
                        var13 = var5.getOperand1();
                     } else {
                        var13 = this.ectx.createConversionOperation(OperationType.CAST_S, var5.getOperand1(), var2.getBitsize());
                     }

                     return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var13);
                  }
               } else if (var3 == OperationType.INT2FP && var6 == OperationType.FP2INT) {
                  Object var15;
                  if (var5.getOperand1().getBitsize() == var2.getBitsize()) {
                     var15 = var5.getOperand1();
                  } else {
                     var15 = this.ectx.createConversionOperation(OperationType.FP2FP, var5.getOperand1(), var2.getBitsize());
                  }

                  return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var15);
               }
            }
         } else if (var3.isFloatOperation() || var3.isFloatComparison()) {
            IEGeneric var9 = var2.getOperand1();
            IEGeneric var10 = var2.getOperand2();
            if (var9 instanceof IEOperation
               && ((IEOperation)var9).getOperationType() == OperationType.FP2FP
               && var10 instanceof IEOperation
               && ((IEOperation)var10).getOperationType() == OperationType.FP2FP) {
               IEGeneric var11 = ((IEOperation)var9).getOperand1();
               IEGeneric var16 = ((IEOperation)var10).getOperand1();
               if (var11.getBitsize() == var16.getBitsize()) {
                  IEOperation var8 = this.ectx.createOperation(var3, var11, var16);
                  var8 = this.ectx.createConversionOperation(OperationType.FP2FP, var8, var1.getBitsize());
                  return AbstractEExpressionOptimizer.EOR.create(var8);
               }
            }
         }

         return null;
      }
   }
}
