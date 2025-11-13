package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEExpressionOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class gJ extends AbstractEExpressionOptimizer {
   public gJ() {
      super(DataChainsUpdatePolicy.UPDATE_IF_REQUIRED);
   }

   @Override
   protected AbstractEExpressionOptimizer.EOR optimizeExpression(IEGeneric var1) {
      return this.pC(var1) ? AbstractEExpressionOptimizer.EOR.create(this.ectx.createImm(0L, var1.getBitsize()), true) : null;
   }

   private boolean pC(IEGeneric var1) {
      return var1 instanceof IEVar && ((IEVar)var1).getId() == 2880;
   }
}
