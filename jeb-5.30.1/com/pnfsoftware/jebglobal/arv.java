package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;

public class arv extends AbstractEExpressionOptimizer {
   public arv() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
      this.skipLeftSideOfAssignment = true;
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEOperation && anx.q(var1)) {
         anx var2 = new anx(this.ectx);
         if (!var2.RF(var1)) {
            return null;
         } else {
            IEGeneric var3 = var2.Dw();
            if (var3 == null) {
               return null;
            } else {
               boolean var4 = false;

               for (IEGeneric var6 : var2.xK()) {
                  if (var6.examine(var0 -> var0 instanceof IEVar)) {
                     var4 = true;
                     break;
                  }
               }

               return AbstractEExpressionOptimizer.EOR.create(var3, var4);
            }
         }
      } else {
         return null;
      }
   }
}
