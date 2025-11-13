package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;

public class Ws extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      var2.registerOptimizer(new cq());
      var2.registerOptimizer(new rQ());
      var2.registerOptimizer(new DH());
      var2.registerOptimizer(new zp());
      var2.addDefaultInput(1088, EUtil.imm(0L, 32));
      var2.addDefaultInput(128, EUtil.imm(0L, 1));
      var2.addDisregardedOutputFilter(new bO(this, var2));
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult collectCandidateMemoryDerefs(IEGeneric var1, EDefUseInfo var2, boolean var3) {
      return ChainedOperationResult.stop();
   }
}
