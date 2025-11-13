package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import java.util.HashSet;
import java.util.Set;

public class aqr extends AbstractEExpressionOptimizer {
   private Set pC = new HashSet();

   public aqr() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.skipStatementProcessing = false;
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      IEGeneric var2 = null;
      if (var1 instanceof IEJump) {
         var2 = ((IEJump)var1).getCondition();
      }

      if (var1 instanceof IECond) {
         var2 = ((IECond)var1).getCondition();
      }

      if (var2 != null) {
         String var3 = var2.toString();
         if (this.pC.add(var3)) {
            Set var4 = EUtil.collectVars(var2);
            if (var4.size() == 1) {
               IEVar var5 = (IEVar)var4.iterator().next();
               amo var6 = new amo(var2, var5);
               if (var6.pC()) {
                  IEGeneric var7 = var6.wS();
                  if (var7 != null
                     && var7 != var2
                     && EUtil.calculateComplexity(var7) < EUtil.calculateComplexity(var2)
                     && var1.replaceSubExpression(var2, var7)) {
                     Set var8 = EUtil.collectVars(var7);
                     return AbstractEExpressionOptimizer.EOR.create(var1, !var8.equals(var4));
                  }
               }
            }
         }
      }

      return null;
   }
}
