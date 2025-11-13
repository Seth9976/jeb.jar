package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class qW extends AbstractEExpressionOptimizer {
   public qW() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      return this.q(var1) ? AbstractEExpressionOptimizer.EOR.create(this.ectx.createImm(0L, var1.getBitsize()), true) : null;
   }

   private boolean q(IEGeneric var1) {
      return var1 instanceof IEVar && ((IEVar)var1).getId() == 2880;
   }
}
