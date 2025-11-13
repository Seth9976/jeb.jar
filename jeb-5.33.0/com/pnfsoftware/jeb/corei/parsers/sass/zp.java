package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;

public class zp extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult executePrePipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      return var1 != NativeDecompilationStage.SIMULATION && var1 != NativeDecompilationStage.STACK_ANALYSIS
         ? super.executePrePipelineStage(var1, var2)
         : new ChainedOperationResult(DecompilationStatus.COMPLETED, ChainedOperationResult.ContinuationStatus.STOP);
   }

   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      rQ var3 = (rQ)var1.getConverter();
      var2.registerOptimizer(new yt(var3.ld.kS()));
      var2.addDisregardedOutputFilter(new KD(this, var2));
      return ChainedOperationResult.TRUE_CONTINUE;
   }
}
