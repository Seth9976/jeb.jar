package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.UnmanglerService;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeCodeAnalyzer {
   int PERMISSION_GENTLE = 0;
   int PERMISSION_FORCEFUL = 1;
   int PERMISSION_DIRTY = 2;
   int PERMISSION_GOD_MODE = 3;
   int FLAG_NO_ROUTINE = 1;
   int FLAG_CODE_CONTIGUOUS = 2;
   int FLAG_CALLEE_GENTLE_ANALYSIS = 4;
   int FLAG_CALLEE_FORCEFUL_ANALYSIS = 8;
   int FLAG_CALLEE_DIRTY_ANALYSIS = 16;
   int FLAG_NO_CACHES_CHECK = 32;
   int FLAG_NO_MERGE = 64;
   int FLAG_TRANSIENT = 4096;

   IProcessor getProcessor();

   IVirtualMemory getMemory();

   ICodeObjectUnit getContainer();

   MemoryRanges getAnalysisRanges();

   ITypeManager getTypeManager();

   INativeCodeModel getModel();

   INativeCodeAdvancedAnalyzer getAdvancedAnalyzer();

   INativeCodeAnalyzerExtension getAnalyzerExtensionsManager();

   UnmanglerService getUnmanglerService();

   ICompiler getDetectedCompiler();

   DebugInformationPolicy getDebugInformationPolicy();

   boolean enqueuePointerForAnalysis(Pointer var1);

   boolean enqueuePointerForAnalysis(Pointer var1, int var2);

   boolean enqueuePointerForAnalysis(Pointer var1, int var2, int var3);

   boolean enqueueRoutineForReanalysis(INativeMethodItem var1);

   boolean clearAnalysisQueue();

   boolean needsAnalysis();

   boolean isAnalyzing();

   int getAnalysisCount();

   void analyze();

   void analyze(boolean var1, boolean var2);

   void requestAnalysisInterruption();

   INativeDataItem defineData(long var1, INativeType var3);

   INativeDataItem defineData(long var1, INativeType var3, int var4);

   boolean recordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4, boolean var5);

   boolean unrecordDynamicBranchTarget(long var1, boolean var3, IBranchTarget var4);

   boolean recordDynamicRegisterValue(long var1, boolean var3, long var4, long var6);

   boolean recordAnalysisComment(long var1, String var3);
}
