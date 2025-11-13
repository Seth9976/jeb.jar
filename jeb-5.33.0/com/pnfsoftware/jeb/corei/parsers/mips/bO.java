package com.pnfsoftware.jeb.corei.parsers.mips;

import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.INativeContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractNativeDecompilerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.DecompilationStatus;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDecompiledMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.NativeDecompilationStage;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESimulationResults;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.IEMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.cfb;
import com.pnfsoftware.jebglobal.cfc;
import com.pnfsoftware.jebglobal.cfd;
import com.pnfsoftware.jebglobal.cfe;
import java.util.ArrayList;

public class bO extends AbstractNativeDecompilerExtension {
   private static final ILogger pC = GlobalLog.getLogger(bO.class);

   @Override
   public ChainedOperationResult customizeIntermediateOptimizer(INativeDecompilerContext var1, IEMasterOptimizer var2) {
      var2.registerOptimizer(new cfe());
      var2.registerOptimizer(new cfc());
      if (!var1.getNativeContext().getProcessor().getType().is64Bit()) {
         var2.registerOptimizer(new cfd());
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult executePostPipelineStage(NativeDecompilationStage var1, IDecompiledMethod var2) {
      if (var1 == NativeDecompilationStage.IR_CONVERSION) {
         ArrayList var3 = new ArrayList();
         ArrayList var4 = new ArrayList();

         for (IEStatement var6 : var2.getIRContext().getCfg().getEntryBlock()) {
            var3.clear();
            var4.clear();
            var6.getDefUse(var3, var4, null);
            if (var3.contains(1600)) {
               break;
            }

            if (var4.contains(1600)) {
               IEVar var7 = var2.getConverter().getGlobalContext().getVar(1600);
               if (var7 != null) {
                  long var8 = var2.getMethodItem().getMemoryAddress();
                  int var10 = var6.replaceVar(var7, EUtil.imm(var8, var7.getBitsize()));
                  Object[] var10000 = new Object[]{var10};
                  break;
               }
            }
         }
      }

      return new ChainedOperationResult(DecompilationStatus.IN_PROCESS, ChainedOperationResult.ContinuationStatus.CONTINUE);
   }

   @Override
   public ChainedOperationResult augmentSimulationContext(
      INativeDecompilerContext var1, IERoutineContext var2, IESimulationResults var3, long var4, IEStatement var6, EState var7
   ) {
      INativeContext var8 = var1.getNativeContext();
      if (var6 instanceof IEUntranslatedInstruction && Strings.equalsIgnoreCase(((IEUntranslatedInstruction)var6).getNativeMnemonic(), "syscall")) {
         Long var9 = var7.getValueAsLongSafe(cfb.pC(2));
         if (var9 == null) {
            return ChainedOperationResult.FALSE_CONTINUE;
         } else {
            int var10 = var9.intValue();
            Mh var11 = Mh.pC();
            String var12 = var11.pC(var10);
            if (var12 == null) {
               return ChainedOperationResult.FALSE_CONTINUE;
            } else {
               var3.recordComment(var4, "syscall:" + var12);
               IPrototypeItem var13 = var11.pC(var10, var8.getTypeManager(), true);
               if (var13 == null) {
                  return ChainedOperationResult.FALSE_CONTINUE;
               } else {
                  String var14 = "sys_" + var12;
                  INativeMethodItem var15 = var8.getRoutineByName(var14);
                  if (var15 == null) {
                     var8.createMethodReference(var14, var13, null);
                  }

                  INativeMethodItem var16 = var8.getRoutineByName(var14);
                  var3.recordSupportRoutineCall(var4, var16);
                  return ChainedOperationResult.TRUE_STOP;
               }
            }
         }
      } else {
         return ChainedOperationResult.FALSE_CONTINUE;
      }
   }
}
