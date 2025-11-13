package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class asf extends AbstractEExpressionOptimizer {
   public asf() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IECond && !EUtil.containsUndeterminedInvocations(((IECond)var1).getCondition())) {
         IEGeneric var2 = ((IECond)var1).getExpressionTrue();
         IEGeneric var3 = ((IECond)var1).getExpressionFalse();
         if (akt.pC(var2, var3)) {
            IEGeneric var8 = ((IECond)var1).getCondition();
            return AbstractEExpressionOptimizer.EOR.create(var2, EUtil.countVariableUse(var8) > 0);
         }
      }

      if (var1 instanceof IECond) {
         IEGeneric var6 = ((IECond)var1).getCondition();
         IEGeneric var7 = ((IECond)var1).getExpressionTrue();
         IEGeneric var4 = ((IECond)var1).getExpressionFalse();
         if (var7 instanceof IECond) {
            IEGeneric var5 = ((IECond)var7).getCondition();
            if (akt.pC(var6, var5)) {
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var6, ((IECond)var7).getExpressionTrue(), var4), true);
            }

            if (EUtil.isNotPredicate(var6, var5)) {
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var6, ((IECond)var7).getExpressionFalse(), var4), true);
            }
         }

         if (var4 instanceof IECond) {
            IEGeneric var9 = ((IECond)var4).getCondition();
            if (akt.pC(var6, var9)) {
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var6, var7, ((IECond)var4).getExpressionFalse()), true);
            }

            if (EUtil.isNotPredicate(var6, var9)) {
               return AbstractEExpressionOptimizer.EOR.create(this.ectx.createCond(var6, var7, ((IECond)var4).getExpressionTrue()), true);
            }
         }
      }

      return null;
   }
}
