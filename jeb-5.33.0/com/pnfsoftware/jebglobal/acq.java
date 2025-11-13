package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AbstractPlugin;
import com.pnfsoftware.jeb.core.IPluginInformation;
import com.pnfsoftware.jeb.core.PluginInformation;
import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerExtensionsManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESimulationResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import java.util.ArrayList;
import java.util.List;

public class acq extends AbstractPlugin implements INativeDecompilerExtensionsManager {
   private INativeDecompilerContext pC;
   private List A = new ArrayList();

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation("NativeDecompilerExtensionsManager", "Manager for native decompiler extensions", "PNF Software", Version.create(1, 0));
   }

   public acq(INativeDecompilerContext var1) {
      this.pC = var1;
   }

   @Override
   public boolean registerExtension(INativeDecompilerExtension var1) {
      return this.registerExtension(var1, INativeDecompilerExtensionsManager.ExtensionPriority.MEDIUM_PRIORITY);
   }

   @Override
   public boolean registerExtension(INativeDecompilerExtension var1, INativeDecompilerExtensionsManager.ExtensionPriority var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null extension");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Null priority");
      } else {
         for (acq.Av var4 : this.A) {
            if (var1 == var4.pC()) {
               return false;
            }
         }

         acq.Av var5 = new acq.Av(var1, var2);
         int var6 = 0;

         while (var6 < this.A.size() && var5.A().compareTo(((acq.Av)this.A.get(var6)).A()) < 0) {
            var6++;
         }

         this.A.add(var6, var5);
         return true;
      }
   }

   @Override
   public int registerExtensions(List var1, boolean var2) {
      if (var2) {
         this.A.clear();
      }

      int var3 = 0;

      for (INativeDecompilerExtension var5 : var1) {
         if (this.registerExtension(var5, INativeDecompilerExtensionsManager.ExtensionPriority.MEDIUM_PRIORITY)) {
            var3++;
         }
      }

      return var3;
   }

   @Override
   public ChainedOperationResult convertInstruction(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3) {
      ChainedOperationResult var4 = ChainedOperationResult.FALSE_STOP;

      for (acq.Av var6 : this.A) {
         ChainedOperationResult var7 = var6.pC().convertInstruction(var1, var2, var3);
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
   public ChainedOperationResult convertToInlinedCall(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3, long var4) {
      ChainedOperationResult var6 = ChainedOperationResult.FALSE_STOP;

      for (acq.Av var8 : this.A) {
         ChainedOperationResult var9 = var8.pC().convertToInlinedCall(var1, var2, var3, var4);
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
   public ChainedOperationResult executePrePipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      ChainedOperationResult var3 = new ChainedOperationResult(DecompilationStatus.IN_PROCESS, ChainedOperationResult.ContinuationStatus.STOP);

      for (acq.Av var5 : this.A) {
         ChainedOperationResult var6 = var5.pC().executePrePipelineStage(var1, var2);
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
   public ChainedOperationResult executePostPipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      ChainedOperationResult var3 = new ChainedOperationResult(DecompilationStatus.IN_PROCESS, ChainedOperationResult.ContinuationStatus.STOP);

      for (acq.Av var5 : this.A) {
         ChainedOperationResult var6 = var5.pC().executePostPipelineStage(var1, var2);
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
   public ChainedOperationResult resolveVariableArgumentInformation(IERoutineContext var1, CFG var2, int var3, IWildcardPrototype var4) {
      ChainedOperationResult var5 = ChainedOperationResult.continue_();

      for (acq.Av var7 : this.A) {
         ChainedOperationResult var8 = var7.pC().resolveVariableArgumentInformation(var1, var2, var3, var4);
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
   public ChainedOperationResult augmentSimulationContext(
      INativeDecompilerContext var1, IERoutineContext var2, IESimulationResults var3, long var4, IEStatement var6, EState var7
   ) {
      ChainedOperationResult var8 = ChainedOperationResult.FALSE_STOP;

      for (acq.Av var10 : this.A) {
         ChainedOperationResult var11 = var10.pC().augmentSimulationContext(var1, var2, var3, var4, var6, var7);
         if (var11.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var8 = var11;
         }

         if (var11.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var8;
   }

   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      ChainedOperationResult var3 = ChainedOperationResult.FALSE_STOP;

      for (acq.Av var5 : this.A) {
         ChainedOperationResult var6 = var5.pC().customizeIntermediateOptimizer(var1, var2);
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
   public ChainedOperationResult applyAdditionalTypes(IDecompiledMethod var1, CFG var2) {
      ChainedOperationResult var3 = ChainedOperationResult.FALSE_STOP;

      for (acq.Av var5 : this.A) {
         ChainedOperationResult var6 = var5.pC().applyAdditionalTypes(var1, var2);
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
   public ChainedOperationResult isMemoryResolutionAllowed(INativeDecompilerContext var1, IERoutineContext var2, IEMem var3) {
      ChainedOperationResult var4 = ChainedOperationResult.TRUE_CONTINUE;

      for (acq.Av var6 : this.A) {
         ChainedOperationResult var7 = var6.pC().isMemoryResolutionAllowed(var1, var2, var3);
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
   public ChainedOperationResult collectCandidateMemoryDerefs(IEGeneric var1, EDefUseInfo var2, boolean var3) {
      ChainedOperationResult var4 = ChainedOperationResult.TRUE_CONTINUE;

      for (acq.Av var6 : this.A) {
         ChainedOperationResult var7 = var6.pC().collectCandidateMemoryDerefs(var1, var2, var3);
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
   public ChainedOperationResult isOpaquePointerType(IWildcardType var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_CONTINUE;

      for (acq.Av var4 : this.A) {
         ChainedOperationResult var5 = var4.pC().isOpaquePointerType(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   public static class Av {
      private final INativeDecompilerExtension pC;
      private final INativeDecompilerExtensionsManager.ExtensionPriority A;

      public Av(INativeDecompilerExtension var1, INativeDecompilerExtensionsManager.ExtensionPriority var2) {
         this.pC = var1;
         this.A = var2;
      }

      public INativeDecompilerExtension pC() {
         return this.pC;
      }

      public INativeDecompilerExtensionsManager.ExtensionPriority A() {
         return this.A;
      }
   }
}
