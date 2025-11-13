package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.IPlugin;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESimulationResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;

public interface INativeDecompilerExtension extends IPlugin {
   ChainedOperationResult convertInstruction(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3);

   ChainedOperationResult convertToInlinedCall(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3, long var4);

   ChainedOperationResult executePrePipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2);

   ChainedOperationResult executePostPipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2);

   ChainedOperationResult resolveVariableArgumentInformation(IERoutineContext var1, CFG var2, int var3, IWildcardPrototype var4);

   ChainedOperationResult applyAdditionalTypes(IDecompiledMethod var1, CFG var2);

   ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2);

   ChainedOperationResult augmentSimulationContext(
      INativeDecompilerContext var1, IERoutineContext var2, IESimulationResults var3, long var4, IEStatement var6, EState var7
   );

   ChainedOperationResult isMemoryResolutionAllowed(INativeDecompilerContext var1, IERoutineContext var2, IEMem var3);

   ChainedOperationResult collectCandidateMemoryDerefs(IEGeneric var1, EDefUseInfo var2, boolean var3);

   ChainedOperationResult isOpaquePointerType(IWildcardType var1);
}
