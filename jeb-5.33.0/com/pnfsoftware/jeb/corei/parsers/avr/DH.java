package com.pnfsoftware.jeb.corei.parsers.avr;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.util.collect.Sets;

public class DH extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      var2.registerOptimizer(new rQ());
      var2.registerOptimizer(new zp());
      var2.addDisregardedOutputBits(Sets.createNonNulls(65600, 65601, 65602, 65603, 65604, 65605, 65606, 65607));
      return ChainedOperationResult.TRUE_CONTINUE;
   }
}
