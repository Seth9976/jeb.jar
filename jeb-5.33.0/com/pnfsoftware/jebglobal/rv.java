package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.List;

@Ser
public class rv implements INativeCodeAnalyzerExtensionsManager {
   @SerId(1)
   private INativeCodeAnalyzer pC;
   @SerTransient
   private List A;

   public rv(INativeCodeAnalyzer var1, boolean var2) {
      this.pC = var1;
      this.A = new ArrayList();
      if (var2) {
         this.A();
      }
   }

   @SerCustomInit
   private void pC() {
      this.A = new ArrayList();
   }

   private void A() {
      IProcessor var1 = this.pC.getProcessor();
      if (var1 instanceof com.pnfsoftware.jeb.corei.parsers.x86.Or) {
         this.registerExtension(new com.pnfsoftware.jeb.corei.parsers.x86.io());
      } else if (var1 instanceof com.pnfsoftware.jeb.corei.parsers.mips.p) {
         this.registerExtension(new com.pnfsoftware.jeb.corei.parsers.mips.K());
      } else if (var1 instanceof com.pnfsoftware.jeb.corei.parsers.arm.cq) {
         this.registerExtension(new com.pnfsoftware.jeb.corei.parsers.arm.Av());
      } else if (var1 instanceof com.pnfsoftware.jeb.corei.parsers.avr.RC) {
         this.registerExtension(new com.pnfsoftware.jeb.corei.parsers.avr.Av());
      }
   }

   @Override
   public INativeCodeUnit getUnit() {
      return null;
   }

   @Override
   public void registerExtension(INativeCodeAnalyzerExtension var1) {
      this.registerExtension(var1, INativeCodeAnalyzerExtensionsManager.ExtensionPriority.MEDIUM_PRIORITY);
   }

   @Override
   public void registerExtensions(List var1, boolean var2) {
      if (var2) {
         this.A.clear();
      }

      for (INativeCodeAnalyzerExtension var4 : var1) {
         this.registerExtension(var4, INativeCodeAnalyzerExtensionsManager.ExtensionPriority.MEDIUM_PRIORITY);
      }
   }

   @Override
   public void registerExtension(INativeCodeAnalyzerExtension var1, INativeCodeAnalyzerExtensionsManager.ExtensionPriority var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null extension");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Null priority");
      } else {
         rv.Av var3 = new rv.Av(var1, var2);
         var1.initialize(this.pC);
         int var4 = 0;

         while (var4 < this.A.size() && var3.A().compareTo(((rv.Av)this.A.get(var4)).A()) < 0) {
            var4++;
         }

         this.A.add(var4, var3);
      }
   }

   @Override
   public void initialize(INativeCodeAnalyzer var1) {
      for (rv.Av var3 : this.A) {
         var3.pC().initialize(var1);
      }
   }

   @Override
   public IPrimitiveSizes getPrimitiveSizes(SubsystemType var1, CompilerType var2) {
      throw new RuntimeException("This method is not be called by the extensions manager");
   }

   @Override
   public void typeManagerInitialized(ITypeManager var1) {
      throw new RuntimeException("This method is not be called by the extensions manager");
   }

   @Override
   public boolean skipSymbolProcessing() {
      throw new RuntimeException("This method is not be called by the extensions manager");
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_STOP;

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().preprocessImage(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_STOP;

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().postprocessImage(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   @Override
   public ChainedOperationResult sigMatchingPostProcess(int var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_STOP;

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().sigMatchingPostProcess(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   @Override
   public ChainedOperationResult getTrampolineTarget(CFG var1) {
      ChainedOperationResult var2 = ChainedOperationResult.stop();

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().getTrampolineTarget(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   @Override
   public ChainedOperationResult getPrologueLooking(long var1, long var3) {
      ChainedOperationResult var5 = ChainedOperationResult.stop();

      for (rv.Av var7 : this.A) {
         ChainedOperationResult var8 = var7.pC().getPrologueLooking(var1, var3);
         if (var8.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var5 = var8;
         }

         if (var8.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var5;
   }

   @Override
   public ChainedOperationResult verifyGapRoutineCandidate(long var1) {
      ChainedOperationResult var3 = ChainedOperationResult.stop(false);

      for (rv.Av var5 : this.A) {
         ChainedOperationResult var6 = var5.pC().verifyGapRoutineCandidate(var1);
         if (var6.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var3 = var6;
         }

         if (var6.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var3;
   }

   @Override
   public ChainedOperationResult getPossiblePaddingSize(long var1, long var3) {
      ChainedOperationResult var5 = ChainedOperationResult.ZEROL_CONTINUE;

      for (rv.Av var7 : this.A) {
         ChainedOperationResult var8 = var7.pC().getPossiblePaddingSize(var1, var3);
         if (var8.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var5 = var8;
         }

         if (var8.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var5;
   }

   @Override
   public ChainedOperationResult getProbableEntryPoints(long var1, long var3) {
      ArrayList var5 = new ArrayList();
      ChainedOperationResult var6 = new ChainedOperationResult(var5, ChainedOperationResult.ContinuationStatus.STOP);

      for (rv.Av var8 : this.A) {
         ChainedOperationResult var9 = var8.pC().getProbableEntryPoints(var1, var3);
         if (var9.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var6 = var9;
         }

         if (var9.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var6;
   }

   @Override
   public ChainedOperationResult determineRoutineStackPointerDelta(CFG var1) {
      ChainedOperationResult var2 = ChainedOperationResult.stop();

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().determineRoutineStackPointerDelta(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   @Override
   public ChainedOperationResult determinePotentialPointers(long var1, IInstruction var3, List var4) {
      boolean var5 = false;

      for (rv.Av var7 : this.A) {
         ChainedOperationResult var8 = var7.pC().determinePotentialPointers(var1, var3, var4);
         if (var8.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE && Boolean.TRUE.equals(var8.getResult())) {
            var5 = true;
         }

         if (var8.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return new ChainedOperationResult(var5);
   }

   @Override
   public ChainedOperationResult determinePotentialPointersInProtoBlock(IBasicBlockSkeleton var1, List var2) {
      boolean var3 = false;

      for (rv.Av var5 : this.A) {
         ChainedOperationResult var6 = var5.pC().determinePotentialPointersInProtoBlock(var1, var2);
         if (var6.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE && Boolean.TRUE.equals(var6.getResult())) {
            var3 = true;
         }

         if (var6.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return new ChainedOperationResult(var3);
   }

   @Override
   public ChainedOperationResult shouldForceRoutineEnd(long var1, IInstruction var3) {
      ChainedOperationResult var4 = ChainedOperationResult.FALSE_STOP;

      for (rv.Av var6 : this.A) {
         ChainedOperationResult var7 = var6.pC().shouldForceRoutineEnd(var1, var3);
         if (var7.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var4 = var7;
         }

         if (var7.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var4;
   }

   @Override
   public ChainedOperationResult isNonReturningRoutine(INativeMethodItem var1) {
      ChainedOperationResult var2 = ChainedOperationResult.ignore();

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().isNonReturningRoutine(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   @Override
   public ChainedOperationResult isCandidateSwitchDispatcher(long var1, IInstruction var3, IBasicBlockSkeleton var4) {
      ChainedOperationResult var5 = ChainedOperationResult.FALSE_STOP;

      for (rv.Av var7 : this.A) {
         ChainedOperationResult var8 = var7.pC().isCandidateSwitchDispatcher(var1, var3, var4);
         if (var8.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var5 = var8;
         }

         if (var8.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var5;
   }

   @Override
   public ChainedOperationResult determineSwitchInformation(long var1, IBasicBlockSkeleton var3, List var4) {
      ChainedOperationResult var5 = ChainedOperationResult.stop();

      for (rv.Av var7 : this.A) {
         ChainedOperationResult var8 = var7.pC().determineSwitchInformation(var1, var3, var4);
         if (var8.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var5 = var8;
         }

         if (var8.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var5;
   }

   @Override
   public ChainedOperationResult getPreferredAdvancedAnalysisStage(INativeMethodItem var1) {
      ChainedOperationResult var2 = ChainedOperationResult.stop();

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().getPreferredAdvancedAnalysisStage(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   @Override
   public ChainedOperationResult getPreferredBreakingFlow(long var1, IInstruction var3) {
      ChainedOperationResult var4 = ChainedOperationResult.stop();

      for (rv.Av var6 : this.A) {
         ChainedOperationResult var7 = var6.pC().getPreferredBreakingFlow(var1, var3);
         if (var7.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var4 = var7;
         }

         if (var7.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var4;
   }

   @Override
   public ChainedOperationResult customizeInstructionItem(INativeInstructionItem var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_CONTINUE;

      for (rv.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().customizeInstructionItem(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   public class Av {
      private final INativeCodeAnalyzerExtension A;
      private final INativeCodeAnalyzerExtensionsManager.ExtensionPriority kS;

      public Av(INativeCodeAnalyzerExtension var2, INativeCodeAnalyzerExtensionsManager.ExtensionPriority var3) {
         this.A = var2;
         this.kS = var3;
      }

      public INativeCodeAnalyzerExtension pC() {
         return this.A;
      }

      public INativeCodeAnalyzerExtensionsManager.ExtensionPriority A() {
         return this.kS;
      }
   }
}
