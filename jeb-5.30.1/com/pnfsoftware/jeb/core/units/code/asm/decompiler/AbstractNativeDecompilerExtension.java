package com.pnfsoftware.jeb.core.units.code.asm.decompiler;

import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.EditablePluginInformation;
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
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public abstract class AbstractNativeDecompilerExtension extends AbstractPlugin implements INativeDecompilerExtension {
   protected static final ILogger logger = GlobalLog.getLogger(AbstractNativeDecompilerExtension.class);
   private EditablePluginInformation info = new EditablePluginInformation();

   public AbstractNativeDecompilerExtension() {
      this(null);
   }

   public AbstractNativeDecompilerExtension(String var1) {
      this.setName(var1);
   }

   protected void setName(String var1) {
      if (var1 == null) {
         var1 = this.getClass().getSimpleName();
      }

      this.info.setName(var1);
   }

   public String getName() {
      return this.info.getName();
   }

   public EditablePluginInformation getPluginInformation() {
      return this.info;
   }

   @Override
   public ChainedOperationResult convertInstruction(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult convertToInlinedCall(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3, long var4) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult resolveVariableArgumentInformation(IERoutineContext var1, CFG var2, int var3, IWildcardPrototype var4) {
      return ChainedOperationResult.continue_();
   }

   @Override
   public ChainedOperationResult executePrePipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      return new ChainedOperationResult(DecompilationStatus.IN_PROCESS, ChainedOperationResult.ContinuationStatus.CONTINUE);
   }

   @Override
   public ChainedOperationResult executePostPipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      return new ChainedOperationResult(DecompilationStatus.IN_PROCESS, ChainedOperationResult.ContinuationStatus.CONTINUE);
   }

   @Override
   public ChainedOperationResult applyAdditionalTypes(IDecompiledMethod var1, CFG var2) {
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult augmentSimulationContext(
      INativeDecompilerContext var1, IERoutineContext var2, IESimulationResults var3, long var4, IEStatement var6, EState var7
   ) {
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult isMemoryResolutionAllowed(INativeDecompilerContext var1, IERoutineContext var2, IEMem var3) {
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult collectCandidateMemoryDerefs(IEGeneric var1, EDefUseInfo var2, boolean var3) {
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult isOpaquePointerType(IWildcardType var1) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }
}
