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

public class aei extends AbstractPlugin implements INativeDecompilerExtensionsManager {
   private INativeDecompilerContext q;
   private List RF = new ArrayList();

   @Override
   public IPluginInformation getPluginInformation() {
      return new PluginInformation("NativeDecompilerExtensionsManager", "Manager for native decompiler extensions", "PNF Software", Version.create(1, 0));
   }

   public aei(INativeDecompilerContext var1) {
      this.q = var1;
   }

   public INativeDecompilerContext q() {
      return this.q;
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
         for (aei.eo var4 : this.RF) {
            if (var1 == var4.q()) {
               return false;
            }
         }

         aei.eo var5 = new aei.eo(var1, var2);
         int var6 = 0;

         while (var6 < this.RF.size() && var5.RF().compareTo(((aei.eo)this.RF.get(var6)).RF()) < 0) {
            var6++;
         }

         this.RF.add(var6, var5);
         return true;
      }
   }

   @Override
   public int registerExtensions(List var1, boolean var2) {
      if (var2) {
         this.RF.clear();
      }

      int var3 = 0;

      for (INativeDecompilerExtension var5 : var1) {
         if (this.registerExtension(var5, INativeDecompilerExtensionsManager.ExtensionPriority.MEDIUM_PRIORITY)) {
            var3++;
         }
      }

      return var3;
   }

   public List RF() {
      return this.RF;
   }

   public void xK() {
      this.RF.clear();
   }

   @Override
   public ChainedOperationResult convertInstruction(IEConverter var1, IERoutineContext var2, ConverterInstructionEntry var3) {
      ChainedOperationResult var4 = ChainedOperationResult.FALSE_STOP;

      for (aei.eo var6 : this.RF) {
         ChainedOperationResult var7 = var6.q().convertInstruction(var1, var2, var3);
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

      for (aei.eo var8 : this.RF) {
         ChainedOperationResult var9 = var8.q().convertToInlinedCall(var1, var2, var3, var4);
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

      for (aei.eo var5 : this.RF) {
         ChainedOperationResult var6 = var5.q().executePrePipelineStage(var1, var2);
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

      for (aei.eo var5 : this.RF) {
         ChainedOperationResult var6 = var5.q().executePostPipelineStage(var1, var2);
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

      for (aei.eo var7 : this.RF) {
         ChainedOperationResult var8 = var7.q().resolveVariableArgumentInformation(var1, var2, var3, var4);
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

      for (aei.eo var10 : this.RF) {
         ChainedOperationResult var11 = var10.q().augmentSimulationContext(var1, var2, var3, var4, var6, var7);
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

      for (aei.eo var5 : this.RF) {
         ChainedOperationResult var6 = var5.q().customizeIntermediateOptimizer(var1, var2);
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

      for (aei.eo var5 : this.RF) {
         ChainedOperationResult var6 = var5.q().applyAdditionalTypes(var1, var2);
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

      for (aei.eo var6 : this.RF) {
         ChainedOperationResult var7 = var6.q().isMemoryResolutionAllowed(var1, var2, var3);
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

      for (aei.eo var6 : this.RF) {
         ChainedOperationResult var7 = var6.q().collectCandidateMemoryDerefs(var1, var2, var3);
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

      for (aei.eo var4 : this.RF) {
         ChainedOperationResult var5 = var4.q().isOpaquePointerType(var1);
         if (var5.getContinuationStatus() != ChainedOperationResult.ContinuationStatus.IGNORE) {
            var2 = var5;
         }

         if (var5.getContinuationStatus() == ChainedOperationResult.ContinuationStatus.STOP) {
            break;
         }
      }

      return var2;
   }

   public static class eo {
      private final INativeDecompilerExtension q;
      private final INativeDecompilerExtensionsManager.ExtensionPriority RF;

      public eo(INativeDecompilerExtension var1, INativeDecompilerExtensionsManager.ExtensionPriority var2) {
         this.q = var1;
         this.RF = var2;
      }

      public INativeDecompilerExtension q() {
         return this.q;
      }

      public INativeDecompilerExtensionsManager.ExtensionPriority RF() {
         return this.RF;
      }
   }
}
