package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;

public class arw extends AbstractEExpressionOptimizer {
   public arw() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.setRequiredModeThreshold(OptimizerMode.AGGRESSIVE);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEOperation) {
         OperationType var2 = ((IEOperation)var1).getOperationType();
         IEGeneric var3 = ((IEOperation)var1).getOperand1();
         IEGeneric var4 = ((IEOperation)var1).getOperand2();
         if (var2 == OperationType.CARRY) {
            IEImm var5 = null;
            IEGeneric var6 = null;
            if (var3 instanceof IEImm) {
               var5 = (IEImm)var3;
               var6 = var4;
            } else if (var4 instanceof IEImm) {
               var5 = (IEImm)var4;
               var6 = var3;
            }

            if (var5 != null) {
               int var19 = var5.getBitsize();
               IEImm var21 = alu.q(0L, var19)._sub(var5);
               IEOperation var23 = this.ectx.createOperation(OperationType.GE_U, var6, var21);
               return AbstractEExpressionOptimizer.EOR.create(var23);
            }
         }

         if (var2.isAnyOf(OperationType.LOG_EQ, OperationType.LOG_NEQ)
            && EUtil.isZero(var4)
            && var3 instanceof IEOperation
            && ((IEOperation)var3).getOperationType() == OperationType.ADD) {
            IEGeneric var14 = ((IEOperation)var3).getOperand1();
            if (((IEOperation)var3).getOperand2() instanceof IEImm var18) {
               int var20 = var18.getBitsize();
               IEImm var22 = alu.q(0L, var20)._sub(var18);
               IEOperation var25 = this.ectx.createOperation(var2, var14, var22);
               return AbstractEExpressionOptimizer.EOR.create(var25);
            }
         }
      }

      if (var1 instanceof IECompose var11) {
         boolean var12 = var11.getPartsCount() != 2 || !var11.getHighPart().isImm() || !var11.getHighPart().asImm().isZero();
         if (var12) {
            int var13 = var1.getBitsize();
            IEImm var15 = EUtil.imm(-1L, var13);
            IEGeneric var17 = null;
            int var7 = 0;

            for (IEGeneric var9 : var11) {
               if (!EUtil.isZero(var9)) {
                  if (!(var9 instanceof IESlice)) {
                     var12 = false;
                     break;
                  }

                  IEGeneric var24 = ((IESlice)var9).getWholeExpression();
                  if (var17 == null) {
                     if (var24.getBitsize() != var13 || !EUtil.hasNoSideEffect(var24)) {
                        var12 = false;
                        break;
                     }

                     var17 = var24;
                  } else if (!var17.equals(var24)) {
                     var12 = false;
                     break;
                  }

                  if (((IESlice)var9).getBitStart() != var7) {
                     var12 = false;
                     break;
                  }

                  var7 = ((IESlice)var9).getBitEnd();
               } else {
                  int var10 = var7 + var9.getBitsize();
                  var15 = var15._and(EUtil.mask(var1.getBitsize(), var7, var10)._not());
                  var7 = var10;
               }
            }

            if (var12 && var7 == var13) {
               if (EUtil.isZero(var15)) {
                  return AbstractEExpressionOptimizer.EOR.create(EUtil.zero(var13), true);
               }

               if (EUtil.isMinusOne(var15)) {
                  return AbstractEExpressionOptimizer.EOR.create(var17);
               }

               return AbstractEExpressionOptimizer.EOR.create(EUtil.andB(var17, var15));
            }
         }
      }

      return null;
   }
}
