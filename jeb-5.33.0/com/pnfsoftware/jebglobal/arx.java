package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class arx extends AbstractEExpressionOptimizer {
   public arx() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IESlice var2)) {
         return null;
      } else {
         int var3 = var2.getBitStart();
         int var4 = var2.getBitEnd();
         IEGeneric var5 = var2.getWholeExpression();
         boolean var6 = false;
         if (var3 == 0
            && var5 instanceof IEOperation var7
            && var7.getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB, OperationType.MUL, OperationType.AND, OperationType.OR, OperationType.XOR)
            )
          {
            IEGeneric var8 = var7.getOperand1();
            IEGeneric var9 = var7.getOperand2();
            if (!var6 || var9.isImm()) {
               var8 = var8.part(var4);
               var9 = var9.part(var4);
               IEOperation var21 = this.ectx.createOperation(var7.getOperationType(), var8, var9);
               return AbstractEExpressionOptimizer.EOR.create(var21);
            }
         }

         if (var5.getBitsize() == var4
            && var3 == var4 / 2
            && var5 instanceof IEOperation var16
            && var16.getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB, OperationType.AND, OperationType.OR, OperationType.XOR)) {
            IEGeneric var17 = var16.getOperand1();
            IEGeneric var19 = var16.getOperand2();
            if (var19 instanceof IECompose || var17 instanceof IECompose) {
               int var10 = var5.getBitsize() / 2;
               OperationType var11 = var16.getOperationType();
               boolean var12 = var11.isAnyOf(OperationType.ADD, OperationType.SUB);
               if (var19 instanceof IECompose var13) {
                  if (var13.getPartsCount() == 2 && var13.getLowPart().getBitsize() == var10 && (!var12 || EUtil.isImmZero(var13.getLowPart()))) {
                     IEOperation var14 = this.ectx.createOperation(var11, var17.slice(var10), var13.getHighPart());
                     return AbstractEExpressionOptimizer.EOR.create(var14);
                  }
               } else if (var11 != OperationType.SUB) {
                  IECompose var22 = (IECompose)var17;
                  if (var22.getPartsCount() == 2 && var22.getLowPart().getBitsize() == var10 && (!var12 || EUtil.isImmZero(var22.getLowPart()))) {
                     IEOperation var15 = this.ectx.createOperation(var11, var22.getHighPart(), var19.slice(var10));
                     return AbstractEExpressionOptimizer.EOR.create(var15);
                  }
               }
            }
         }

         return null;
      }
   }
}
