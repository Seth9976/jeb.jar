package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface INativeCodeAnalyzerExtension {
   INativeCodeUnit getUnit();

   IPrimitiveSizes getPrimitiveSizes(SubsystemType var1, CompilerType var2);

   void typeManagerInitialized(ITypeManager var1);

   void initialize(INativeCodeAnalyzer var1);

   ChainedOperationResult preprocessImage(int var1);

   ChainedOperationResult postprocessImage(int var1);

   ChainedOperationResult sigMatchingPostProcess(int var1);

   ChainedOperationResult getTrampolineTarget(CFG var1);

   ChainedOperationResult getPrologueLooking(long var1, long var3);

   ChainedOperationResult verifyGapRoutineCandidate(long var1);

   ChainedOperationResult getPossiblePaddingSize(long var1, long var3);

   ChainedOperationResult getProbableEntryPoints(long var1, long var3);

   ChainedOperationResult determineRoutineStackPointerDelta(CFG var1);

   ChainedOperationResult determinePotentialPointers(long var1, IInstruction var3, List var4);

   ChainedOperationResult determinePotentialPointersInProtoBlock(IBasicBlockSkeleton var1, List var2);

   ChainedOperationResult shouldForceRoutineEnd(long var1, IInstruction var3);

   ChainedOperationResult isNonReturningRoutine(INativeMethodItem var1);

   ChainedOperationResult getPreferredAdvancedAnalysisStage(INativeMethodItem var1);

   ChainedOperationResult isCandidateSwitchDispatcher(long var1, IInstruction var3, IBasicBlockSkeleton var4);

   ChainedOperationResult determineSwitchInformation(long var1, IBasicBlockSkeleton var3, List var4);

   ChainedOperationResult getPreferredBreakingFlow(long var1, IInstruction var3);

   ChainedOperationResult customizeInstructionItem(INativeInstructionItem var1);
}
