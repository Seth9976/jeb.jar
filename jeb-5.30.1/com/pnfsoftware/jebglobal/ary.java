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

public class ary extends AbstractEExpressionOptimizer {
   public ary() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IECond) {
         IEGeneric var15 = ((IECond)var1).getCondition();
         if (var15 instanceof IEImm) {
            IEGeneric var17 = ((IECond)var1).getExpressionTrue();
            IEGeneric var18 = ((IECond)var1).getExpressionFalse();
            if (((IEImm)var15)._signum() != 0) {
               return AbstractEExpressionOptimizer.EOR.create(var17, EUtil.countVariableUse(var18) > 0);
            }

            return AbstractEExpressionOptimizer.EOR.create(var18, EUtil.countVariableUse(var17) > 0);
         }
      } else if (var1 instanceof IESlice) {
         IEGeneric var14 = ((IESlice)var1).getWholeExpression();
         if (var14 instanceof IEImm) {
            return AbstractEExpressionOptimizer.EOR.create(EUtil.evaluate_preVerified(var1));
         }
      } else {
         if (var1 instanceof IECompose) {
            for (IEGeneric var16 : var1.asCompose()) {
               if (!(var16 instanceof IEImm)) {
                  return null;
               }
            }

            return AbstractEExpressionOptimizer.EOR.create(EUtil.evaluate_preVerified(var1));
         }

         if (var1 instanceof IEOperation) {
            OperationType var2 = ((IEOperation)var1).getOperationType();
            IEGeneric var3 = ((IEOperation)var1).getOperand1();
            IEGeneric var4 = ((IEOperation)var1).getOperand2();
            if ((var3 == null || var3 instanceof IEImm) && (var4 == null || var4 instanceof IEImm)) {
               try {
                  return AbstractEExpressionOptimizer.EOR.create(var1.evaluate(null));
               } catch (Exception var11) {
                  return null;
               }
            }

            if (var2.isLogical() && EUtil.hasNoSideEffect(var3) && var3.equalsEx(var4, false)) {
               switch (var2) {
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

            if (var4 instanceof IEImm
               && var3 instanceof IECond
               && !(((IECond)var3).getCondition() instanceof IEImm)
               && ((IECond)var3).getExpressionTrue() instanceof IEImm
               && ((IECond)var3).getExpressionFalse() instanceof IEImm
               && EUtil.hasNoSideEffect(((IECond)var3).getCondition())) {
               try {
                  IEGeneric var5 = ((IECond)var3).getExpressionTrue();
                  IEOperation var6 = this.ectx.createOperation(var2, var5, var4);
                  IEImm var7 = var6.evaluate(null);
                  IEGeneric var8 = ((IECond)var3).getExpressionFalse();
                  IEOperation var9 = this.ectx.createOperation(var2, var8, var4);
                  IEImm var10 = var9.evaluate(null);
                  if (var7.equals(var10)) {
                     return AbstractEExpressionOptimizer.EOR.create(var7);
                  }
               } catch (Exception var12) {
                  return null;
               }
            }
         }
      }

      return null;
   }
}
