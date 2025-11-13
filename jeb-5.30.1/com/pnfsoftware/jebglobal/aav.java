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
public class aav implements INativeCodeAnalyzerExtensionsManager {
   @SerId(1)
   private INativeCodeAnalyzer q;
   @SerTransient
   private List RF;

   public aav(INativeCodeAnalyzer var1, boolean var2) {
      this.q = var1;
      this.RF = new ArrayList();
      if (var2) {
         this.Dw();
      }
   }

   @SerCustomInit
   private void xK() {
      this.RF = new ArrayList();
   }

   private void Dw() {
      IProcessor var1 = this.q.getProcessor();
      if (var1 instanceof cti) {
         this.registerExtension(new crk());
      } else if (var1 instanceof clc) {
         this.registerExtension(new ckl());
      } else if (var1 instanceof vh) {
         this.registerExtension(new FS());
      } else if (var1 instanceof bdf) {
         this.registerExtension(new bcs());
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
         this.RF.clear();
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
         aav.eo var3 = new aav.eo(var1, var2);
         var1.initialize(this.q);
         int var4 = 0;

         while (var4 < this.RF.size() && var3.RF().compareTo(((aav.eo)this.RF.get(var4)).RF()) < 0) {
            var4++;
         }

         this.RF.add(var4, var3);
      }
   }

   public List q() {
      return this.RF;
   }

   public void RF() {
      this.RF.clear();
   }

   @Override
   public void initialize(INativeCodeAnalyzer var1) {
      for (aav.eo var3 : this.RF) {
         var3.q().initialize(var1);
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
   public ChainedOperationResult preprocessImage(int var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_STOP;

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().preprocessImage(var1);
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

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().postprocessImage(var1);
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

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().sigMatchingPostProcess(var1);
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

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().getTrampolineTarget(var1);
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

      for (aav.eo var7 : this.RF) {
         ChainedOperationResult var8 = var7.q().getPrologueLooking(var1, var3);
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

      for (aav.eo var5 : this.RF) {
         ChainedOperationResult var6 = var5.q().verifyGapRoutineCandidate(var1);
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

      for (aav.eo var7 : this.RF) {
         ChainedOperationResult var8 = var7.q().getPossiblePaddingSize(var1, var3);
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

      for (aav.eo var8 : this.RF) {
         ChainedOperationResult var9 = var8.q().getProbableEntryPoints(var1, var3);
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

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().determineRoutineStackPointerDelta(var1);
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

      for (aav.eo var7 : this.RF) {
         ChainedOperationResult var8 = var7.q().determinePotentialPointers(var1, var3, var4);
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

      for (aav.eo var5 : this.RF) {
         ChainedOperationResult var6 = var5.q().determinePotentialPointersInProtoBlock(var1, var2);
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

      for (aav.eo var6 : this.RF) {
         ChainedOperationResult var7 = var6.q().shouldForceRoutineEnd(var1, var3);
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

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().isNonReturningRoutine(var1);
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

      for (aav.eo var7 : this.RF) {
         ChainedOperationResult var8 = var7.q().isCandidateSwitchDispatcher(var1, var3, var4);
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

      for (aav.eo var7 : this.RF) {
         ChainedOperationResult var8 = var7.q().determineSwitchInformation(var1, var3, var4);
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

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().getPreferredAdvancedAnalysisStage(var1);
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

      for (aav.eo var6 : this.RF) {
         ChainedOperationResult var7 = var6.q().getPreferredBreakingFlow(var1, var3);
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

      for (aav.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().customizeInstructionItem(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   public class eo {
      private final INativeCodeAnalyzerExtension RF;
      private final INativeCodeAnalyzerExtensionsManager.ExtensionPriority xK;

      public eo(INativeCodeAnalyzerExtension var2, INativeCodeAnalyzerExtensionsManager.ExtensionPriority var3) {
         this.RF = var2;
         this.xK = var3;
      }

      public INativeCodeAnalyzerExtension q() {
         return this.RF;
      }

      public INativeCodeAnalyzerExtensionsManager.ExtensionPriority RF() {
         return this.xK;
      }
   }
}
