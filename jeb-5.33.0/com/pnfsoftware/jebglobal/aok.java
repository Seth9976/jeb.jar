package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class aok extends AbstractEExpressionOptimizer {
   public aok() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEOperation var2) {
         OperationType var3 = var2.getOperationType();
         IEGeneric var4 = var2.getOperand1();
         int var5 = var2.getBitsize();
         if (var3.isAnyOf(OperationType.CAST, OperationType.CAST_S)) {
            if (var3 == OperationType.CAST_S && var5 < var4.getBitsize()) {
               IEOperation var12 = this.ectx.createResizeOperation(var4, var5, false);
               return AbstractEExpressionOptimizer.EOR.create(var12);
            }

            if (var5 == var4.getBitsize()) {
               return AbstractEExpressionOptimizer.EOR.create(var4);
            }

            if (var3 == OperationType.CAST && var4 instanceof IEOperation var6) {
               OperationType var7 = var6.getOperationType();
               IEGeneric var8 = var6.getOperand1();
               IEGeneric var9 = var6.getOperand2();
               if (var5 < var8.getBitsize()
                  && var7.isAnyOf(OperationType.ADD, OperationType.SUB, OperationType.MUL, OperationType.AND, OperationType.OR, OperationType.XOR)) {
                  IEOperation var18 = this.ectx
                     .createOperation(var7, this.ectx.createResizeOperation(var8, var5, false), this.ectx.createResizeOperation(var9, var5, false));
                  return AbstractEExpressionOptimizer.EOR.create(var18);
               }

               if (var7 == OperationType.CAST && var5 < var4.getBitsize()) {
                  IEOperation var17 = this.ectx.createResizeOperation(var8, var5, false);
                  return AbstractEExpressionOptimizer.EOR.create(var17);
               }

               if (var7 == OperationType.CAST_S && var5 < var4.getBitsize()) {
                  IEOperation var16 = this.ectx.createResizeOperation(var8, var5, true);
                  return AbstractEExpressionOptimizer.EOR.create(var16);
               }

               if (var7 == OperationType.CAST && var5 > var4.getBitsize() && var4.getBitsize() > var8.getBitsize()) {
                  IEOperation var10 = this.ectx.createResizeOperation(var8, var5, false);
                  return AbstractEExpressionOptimizer.EOR.create(var10);
               }
            }

            if (var3 == OperationType.CAST_S && var4 instanceof IEOperation var11) {
               OperationType var13 = var11.getOperationType();
               IEGeneric var14 = var11.getOperand1();
               if (var13 == OperationType.CAST_S && var5 > var4.getBitsize() && var4.getBitsize() > var14.getBitsize()) {
                  IEOperation var15 = this.ectx.createResizeOperation(var14, var5, true);
                  return AbstractEExpressionOptimizer.EOR.create(var15);
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }
}
