package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class aqx extends AbstractEExpressionOptimizer {
   public aqx() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IEOperation var2)) {
         return null;
      } else {
         OperationType var3 = var2.getOperationType();
         IEGeneric var4 = var2.getOperand1();
         int var5 = var2.getBitsize();
         if (var3.isAnyOf(OperationType.CAST, OperationType.CAST_S)) {
            if (var3 == OperationType.CAST_S && var5 < var4.getBitsize()) {
               IEOperation var11 = this.ectx.createResizeOperation(var4, var5, false);
               return AbstractEExpressionOptimizer.EOR.create(var11);
            }

            if (var5 == var4.getBitsize()) {
               return AbstractEExpressionOptimizer.EOR.create(var4);
            }

            if (var3 == OperationType.CAST && var4 instanceof IEOperation) {
               OperationType var6 = ((IEOperation)var4).getOperationType();
               IEGeneric var7 = ((IEOperation)var4).getOperand1();
               IEGeneric var8 = ((IEOperation)var4).getOperand2();
               if (var5 < var7.getBitsize()
                  && var6.isAnyOf(OperationType.ADD, OperationType.SUB, OperationType.MUL, OperationType.AND, OperationType.OR, OperationType.XOR)) {
                  IEOperation var16 = this.ectx
                     .createOperation(var6, this.ectx.createResizeOperation(var7, var5, false), this.ectx.createResizeOperation(var8, var5, false));
                  return AbstractEExpressionOptimizer.EOR.create(var16);
               }

               if (var6 == OperationType.CAST && var5 < var4.getBitsize()) {
                  IEOperation var15 = this.ectx.createResizeOperation(var7, var5, false);
                  return AbstractEExpressionOptimizer.EOR.create(var15);
               }

               if (var6 == OperationType.CAST_S && var5 < var4.getBitsize()) {
                  IEOperation var14 = this.ectx.createResizeOperation(var7, var5, true);
                  return AbstractEExpressionOptimizer.EOR.create(var14);
               }

               if (var6 == OperationType.CAST && var5 > var4.getBitsize() && var4.getBitsize() > var7.getBitsize()) {
                  IEOperation var9 = this.ectx.createResizeOperation(var7, var5, false);
                  return AbstractEExpressionOptimizer.EOR.create(var9);
               }
            }

            if (var3 == OperationType.CAST_S && var4 instanceof IEOperation) {
               OperationType var10 = ((IEOperation)var4).getOperationType();
               IEGeneric var12 = ((IEOperation)var4).getOperand1();
               if (var10 == OperationType.CAST_S && var5 > var4.getBitsize() && var4.getBitsize() > var12.getBitsize()) {
                  IEOperation var13 = this.ectx.createResizeOperation(var12, var5, true);
                  return AbstractEExpressionOptimizer.EOR.create(var13);
               }
            }
         }

         return null;
      }
   }

   private static boolean q(IEImm var0, int var1) {
      int var2 = var0.getBitsize();
      if (var2 <= var1) {
         return true;
      } else {
         IEImm var3 = var0._sar(var1);
         return var3.isZero() ? true : var3.equalsEx(EUtil.minusOne(var2), false);
      }
   }
}
