package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.collect.ISegmentMap;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.a;
import com.pnfsoftware.jebglobal.dt;
import java.util.List;

@Ser
public abstract class AbstractAnalyzerExtension implements INativeCodeAnalyzerExtension {
   @SerId(1)
   protected INativeCodeAnalyzer gca;
   @SerTransient
   protected BinaryPatternVerifier prologueVerifier;
   @SerTransient
   protected BinaryPatternVerifier paddingVerifier;

   @Override
   public void initialize(INativeCodeAnalyzer var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (this.gca != null && this.gca != var1) {
         throw new IllegalStateException();
      } else {
         this.gca = var1;
         this.prologueVerifier = new BinaryPatternVerifier();
         this.initializeProloguePatterns(this.prologueVerifier);
         this.paddingVerifier = new BinaryPatternVerifier();
         this.initializePaddingPatterns(this.paddingVerifier);
      }
   }

   @Override
   public INativeCodeUnit getUnit() {
      return ((a)this.gca).wS().A();
   }

   protected void initializeProloguePatterns(BinaryPatternVerifier var1) {
   }

   @Override
   public IPrimitiveSizes getPrimitiveSizes(SubsystemType var1, CompilerType var2) {
      return null;
   }

   @Override
   public void typeManagerInitialized(ITypeManager var1) {
   }

   @Override
   public boolean skipSymbolProcessing() {
      return false;
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult sigMatchingPostProcess(int var1) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult getPrologueLooking(long var1, long var3) {
      IBinaryPattern var5 = CodeAnalyzerUtil.checkBinaryPattern(this.gca, this.prologueVerifier, var1, var3);
      return var5 != null ? new ChainedOperationResult(new CodePointer(var1, var5.getProcessorMode())) : ChainedOperationResult.ignore();
   }

   @Override
   public ChainedOperationResult verifyGapRoutineCandidate(long var1) {
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
   }

   @Override
   public ChainedOperationResult getPossiblePaddingSize(long var1, long var3) {
      IBinaryPattern var5 = CodeAnalyzerUtil.checkBinaryPattern(this.gca, this.paddingVerifier, var1, var3);
      if (var5 == null) {
         return ChainedOperationResult.ZEROL_CONTINUE;
      } else {
         ISegmentMap var6 = ((a)this.gca).A();
         boolean var7 = false;
         if (var6 != null) {
            dt var8 = (dt)var6.getSegmentContaining(var1);
            if (var8 != null) {
               long var9 = var8.pC();
               if (var9 != 0L && var9 != 1L) {
                  var7 = true;
                  if (var1 % var9 != 0L) {
                     return new ChainedOperationResult(var5.getBinary().length & 4294967295L);
                  }
               }
            }
         }

         return !var7 ? new ChainedOperationResult(var5.getBinary().length & 4294967295L) : ChainedOperationResult.ZEROL_CONTINUE;
      }
   }

   @Override
   public ChainedOperationResult getProbableEntryPoints(long var1, long var3) {
      return ChainedOperationResult.ignore();
   }

   @Override
   public ChainedOperationResult getTrampolineTarget(CFG var1) {
      return ChainedOperationResult.continue_();
   }

   @Override
   public ChainedOperationResult determineRoutineStackPointerDelta(CFG var1) {
      return ChainedOperationResult.continue_();
   }

   @Override
   public ChainedOperationResult determinePotentialPointers(long var1, IInstruction var3, List var4) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult determinePotentialPointersInProtoBlock(IBasicBlockSkeleton var1, List var2) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult shouldForceRoutineEnd(long var1, IInstruction var3) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult isNonReturningRoutine(INativeMethodItem var1) {
      return ChainedOperationResult.ignore();
   }

   @Override
   public ChainedOperationResult isCandidateSwitchDispatcher(long var1, IInstruction var3, IBasicBlockSkeleton var4) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult determineSwitchInformation(long var1, IBasicBlockSkeleton var3, List var4) {
      return ChainedOperationResult.continue_();
   }

   @Override
   public ChainedOperationResult getPreferredBreakingFlow(long var1, IInstruction var3) {
      return ChainedOperationResult.continue_();
   }

   @Override
   public ChainedOperationResult getPreferredAdvancedAnalysisStage(INativeMethodItem var1) {
      return new ChainedOperationResult(NativeDecompilationStage.SIMULATION, ChainedOperationResult.ContinuationStatus.CONTINUE);
   }

   @Override
   public ChainedOperationResult customizeInstructionItem(INativeInstructionItem var1) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }
}
