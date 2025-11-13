package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class auo extends AbstractEExpressionOptimizer {
   public auo() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (!(var1 instanceof IESlice)) {
         return null;
      } else {
         int var2 = ((IESlice)var1).getBitStart();
         int var3 = ((IESlice)var1).getBitEnd();
         IEGeneric var4 = ((IESlice)var1).getWholeExpression();
         if (var2 == 0
            && EUtil.isOperation(var4, OperationType.ADD, OperationType.SUB, OperationType.MUL, OperationType.AND, OperationType.OR, OperationType.XOR)) {
            IEOperation var5 = var4.asOperation();
            IEGeneric var6 = var5.getOperand1();
            IEGeneric var7 = var5.getOperand2();
            if (var7.isImm()) {
               var6 = var6.part(var3);
               var7 = var7.part(var3);
               IEOperation var18 = this.ectx.createOperation(var5.getOperationType(), var6, var7);
               return AbstractEExpressionOptimizer.EOR.create(var18);
            }
         }

         if (var4.getBitsize() == var3
            && var2 == var3 / 2
            && EUtil.isOperation(var4, OperationType.ADD, OperationType.SUB, OperationType.AND, OperationType.OR, OperationType.XOR)) {
            IEOperation var13 = (IEOperation)var4;
            IEGeneric var14 = var13.getOperand1();
            IEGeneric var16 = var13.getOperand2();
            if (var16 instanceof IECompose || var14 instanceof IECompose) {
               int var8 = var4.getBitsize() / 2;
               OperationType var9 = var13.getOperationType();
               boolean var10 = var9.isAnyOf(OperationType.ADD, OperationType.SUB);
               if (var16 instanceof IECompose var11) {
                  if (var11.getPartsCount() == 2 && var11.getLowPart().getBitsize() == var8 && (!var10 || EUtil.isImmZero(var11.getLowPart()))) {
                     IEOperation var12 = this.ectx.createOperation(var9, var14.slice(var8), var11.getHighPart());
                     return AbstractEExpressionOptimizer.EOR.create(var12);
                  }
               } else if (var9 != OperationType.SUB) {
                  IECompose var19 = (IECompose)var14;
                  if (var19.getPartsCount() == 2 && var19.getLowPart().getBitsize() == var8 && (!var10 || EUtil.isImmZero(var19.getLowPart()))) {
                     IEOperation var20 = this.ectx.createOperation(var9, var19.getHighPart(), var16.slice(var8));
                     return AbstractEExpressionOptimizer.EOR.create(var20);
                  }
               }
            }
         }

         return null;
      }
   }
}
