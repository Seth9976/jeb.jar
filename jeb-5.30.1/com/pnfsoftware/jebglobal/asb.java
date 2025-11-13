package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroupElt;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class asb extends AbstractEExpressionOptimizer {
   public asb() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      if (var1 instanceof IEGroupElt var2 && var2.getIndex() instanceof IEImm) {
         int var3 = EUtil.evalAsSaturatedPositiveInt((IEImm)var2.getIndex());
         IEGeneric var4 = var2.getGroup().getElement(var3);
         return AbstractEExpressionOptimizer.EOR.create(var4, true);
      } else {
         return null;
      }
   }
}
