package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.LinuxSyscallResolver;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESimulationResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.format.Strings;

public class Re extends AbstractNativeDecompilerExtension {
   @Override
   public ChainedOperationResult executePostPipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      if (var1 == NativeDecompilationStage.IR_CONVERSION) {
         IERoutineContext var3 = var2.getIRContext();
         this.q(var3);
      }

      return super.executePostPipelineStage(var1, var2);
   }

   int q(IERoutineContext var1) {
      IEVar var2 = var1.getVariableByName("TPIDR_EL0");
      if (var2 != null && var2.getBitsize() == 64) {
         int var3 = 0;

         for (BasicBlock var5 : var1.getCfg()) {
            for (int var6 = 0; var6 < var5.size(); var6++) {
               IEStatement var7 = (IEStatement)var5.get(var6);
               if (var7.isAssign() && var7.asAssign().getRightOperand() == var2) {
                  IEGeneric var8 = var7.asAssign().getLeftOperand();
                  long var9 = var7.getPrimaryLowerLevelAddress();
                  IEUntranslatedInstruction var11 = var1.createUntranslatedInstruction(var9, "read_TPIDR_EL0");
                  var11.setReturnExpression(var8);
                  var11.copyProperties(var7);
                  var5.set(var6, var11);
                  var3++;
               }
            }
         }

         if (var3 > 0) {
            var1.getCfg().invalidateDataFlowAnalysis();
         }

         return var3;
      } else {
         return 0;
      }
   }

   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      if (var1.getNativeContext().getProcessor().getType().is64Bit()) {
         var2.registerOptimizer(new qW());
      } else {
         var2.registerOptimizer(new Wb());
         var2.registerOptimizer(new np());
         var2.registerOptimizer(new wv());
      }

      var2.addDisregardedOutputBits(Sets.createNonNulls(543, 540, 542, 541, 528, 536, 512, 539, 520, 519, 518, 517));
      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult augmentSimulationContext(
      INativeDecompilerContext var1, IERoutineContext var2, IESimulationResults var3, long var4, IEStatement var6, EState var7
   ) {
      INativeContext var8 = var1.getNativeContext();
      if (var6 instanceof IEUntranslatedInstruction && Strings.equalsIgnoreCase(((IEUntranslatedInstruction)var6).getNativeMnemonic(), "svc")) {
         int var11 = 0;
         if (var7 != null && var7.hasValue(517)) {
            var11 = var1.getConverter().getStateProcessorMode(var7);
         } else {
            Long var12 = var6.getPrimaryLowerLevelAddress();
            if (var12 != null && var8.getNativeItemAt(var12) instanceof INativeInstructionItem) {
               INativeInstructionItem var13 = (INativeInstructionItem)var8.getNativeItemAt(var12);
               var11 = var13.getInstruction().getProcessorMode();
            }
         }

         if (var11 == 0) {
            var11 = var8.getProcessor().getMode();
         }

         ProcessorType var9;
         Long var10;
         if (var11 != 16 && var11 != 32) {
            if (var11 != 64) {
               return ChainedOperationResult.FALSE_CONTINUE;
            }

            var9 = ProcessorType.ARM64;
            var10 = var7.getValueAsLongSafe(uq.xK(8));
         } else {
            var9 = ProcessorType.ARM;
            var10 = var7.getValueAsLongSafe(uq.RF(7));
         }

         if (var10 == null) {
            return ChainedOperationResult.FALSE_CONTINUE;
         } else {
            LinuxSyscallResolver var19 = LinuxSyscallResolver.getInstance(var9);
            if (var19 == null) {
               return ChainedOperationResult.FALSE_CONTINUE;
            } else {
               int var20 = var10.intValue();
               String var14 = var19.getName(var20);
               if (var14 == null) {
                  return ChainedOperationResult.FALSE_CONTINUE;
               } else {
                  var3.recordComment(var4, "syscall:" + var14);
                  IPrototypeItem var15 = var19.getPrototype(var20, var8.getTypeManager());
                  if (var15 == null) {
                     return ChainedOperationResult.FALSE_CONTINUE;
                  } else {
                     String var16 = "sys_" + var14;
                     INativeMethodItem var17 = var8.getRoutineByName(var16);
                     if (var17 == null) {
                        var8.createMethodReference(var16, var15, null);
                     }

                     INativeMethodItem var18 = var8.getRoutineByName(var16);
                     var3.recordSupportRoutineCall(var4, var18);
                     return ChainedOperationResult.TRUE_STOP;
                  }
               }
            }
         }
      } else {
         return ChainedOperationResult.FALSE_CONTINUE;
      }
   }
}
