package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class asw extends AbstractEExpressionOptimizer {
   public asw() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IECond && ((IECond)var1).getCondition() instanceof IECond) {
         IECond var2 = (IECond)var1;
         IECond var3 = (IECond)var2.getCondition();
         if (var3.getExpressionTrue() instanceof IEImm && var3.getExpressionFalse() instanceof IEImm) {
            IEImm var4 = (IEImm)var3.getExpressionTrue();
            IEImm var5 = (IEImm)var3.getExpressionFalse();
            IEGeneric var6;
            if (var4._signum() == 0) {
               var6 = var2.getExpressionFalse();
            } else {
               var6 = var2.getExpressionTrue();
            }

            IEGeneric var7;
            if (var5._signum() == 0) {
               var7 = var2.getExpressionFalse();
            } else {
               var7 = var2.getExpressionTrue();
            }

            boolean var8;
            Object var9;
            if (!amw.q(var6, var7)) {
               var8 = false;
               var9 = this.ectx.createCond(var3.getCondition(), var6, var7);
            } else {
               var8 = true;
               if (EUtil.containsUndeterminedInvocations(var3.getCondition())) {
                  return null;
               }

               var9 = var6;
            }

            return AbstractEExpressionOptimizer.EOR.create((IEGeneric)var9, var8);
         }
      }

      return null;
   }
}
