package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.util.collect.Sets;

public class bcy extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      var2.registerOptimizer(new bda());
      var2.registerOptimizer(new bdb());
      var2.addDisregardedOutputBits(Sets.createNonNulls(65600, 65601, 65602, 65603, 65604, 65605, 65606, 65607));
      return ChainedOperationResult.TRUE_CONTINUE;
   }
}
