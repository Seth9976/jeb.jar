package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class apl extends AbstractEExpressionOptimizer {
   public apl() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected int perform() {
      int var1 = super.perform();
      if (var1 > 0) {
         akt.pC(this.ectx);
      }

      return var1;
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IECond var2) {
         if (var2.getCondition() instanceof IEImm var22) {
            IEGeneric var23 = var2.getExpressionTrue();
            IEGeneric var24 = var2.getExpressionFalse();
            if (var22._signum() != 0) {
               return AbstractEExpressionOptimizer.EOR.create(var23, EUtil.countVariableUse(var24) > 0);
            }

            return AbstractEExpressionOptimizer.EOR.create(var24, EUtil.countVariableUse(var23) > 0);
         }
      } else if (var1 instanceof IESlice var3) {
         IEGeneric var19 = var3.getWholeExpression();
         if (var19 instanceof IEImm) {
            return AbstractEExpressionOptimizer.EOR.create(EUtil.evaluate_preVerified(var1));
         }
      } else {
         if (var1 instanceof IECompose) {
            for (IEGeneric var21 : (IECompose)var1) {
               if (!(var21 instanceof IEImm)) {
                  return null;
               }
            }

            return AbstractEExpressionOptimizer.EOR.create(EUtil.evaluate_preVerified(var1));
         }

         if (var1 instanceof IEOperation var5) {
            OperationType var6 = var5.getOperationType();
            IEGeneric var7 = var5.getOperand1();
            IEGeneric var8 = var5.getOperand2();
            if ((var7 == null || var7 instanceof IEImm) && (var8 == null || var8 instanceof IEImm)) {
               try {
                  return AbstractEExpressionOptimizer.EOR.create(var1.evaluate(null));
               } catch (Exception var16) {
                  return null;
               }
            }

            if (var6.isLogical() && EUtil.hasNoSideEffect(var7) && var7.equalsEx(var8, false)) {
               switch (var6) {
                  case LOG_EQ:
                  case GE_S:
                  case GE_U:
                  case LE_S:
                  case LE_U:
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.one(1));
                  case LOG_NEQ:
                  case GT_S:
                  case GT_U:
                  case LT_S:
                  case LT_U:
                     return AbstractEExpressionOptimizer.EOR.create(EUtil.zero(1));
               }
            }

            if (var8 instanceof IEImm
               && var7 instanceof IECond var9
               && !(var9.getCondition() instanceof IEImm)
               && var9.getExpressionTrue() instanceof IEImm
               && var9.getExpressionFalse() instanceof IEImm
               && EUtil.hasNoSideEffect(var9.getCondition())) {
               try {
                  IEGeneric var10 = var9.getExpressionTrue();
                  IEOperation var11 = this.ectx.createOperation(var6, var10, var8);
                  IEImm var12 = var11.evaluate(null);
                  IEGeneric var13 = var9.getExpressionFalse();
                  IEOperation var14 = this.ectx.createOperation(var6, var13, var8);
                  IEImm var15 = var14.evaluate(null);
                  if (var12.equals(var15)) {
                     return AbstractEExpressionOptimizer.EOR.create(var12);
                  }
               } catch (Exception var17) {
                  return null;
               }
            }
         }
      }

      return null;
   }
}
