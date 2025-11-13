package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizer;

public interface IEOptimizer extends IOptimizer {
   DataChainsUpdatePolicy getDataChainsUpdatePolicy();

   IEGeneric performOnExpression(IEGeneric var1, IERoutineContext var2);
}
