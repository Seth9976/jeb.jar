package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import java.util.Collection;

public class apb extends AbstractEOptimizer {
   public apb() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.AGGRESSIVE);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (AddressableInstruction var3 : this.cfg.addressableInstructions()) {
         IEStatement var4 = (IEStatement)var3.getInstruction();
         if (var4 instanceof IECall) {
            IECall var5 = var4.asCall();

            for (int var6 = 0; var6 < var5.getCountOfArguments(); var6++) {
               if (!var5.getHintArgumentPointsToExternalMemory(var6)) {
                  IEGeneric var7 = var5.getArgument(var6);
                  IWildcardType var8 = var7.getType();
                  if (var8 != null && var8.isPointer()) {
                     boolean var9 = false;
                     if (EUtil.isLikeImmediate(var7)) {
                        var9 = true;
                     } else if (pC(var7)) {
                        var9 = true;
                     } else if (var7 instanceof IEMem) {
                        if (var7.hasFlags(256)) {
                           var9 = true;
                        }
                     } else {
                        alj var10 = new alj(var7);
                        if (var10.pC() && var10.UT()) {
                           IEVar var11 = var10.kS();
                           if (var11 != null && !var11.isReference()) {
                              IDFA var12 = this.cfg.doDataFlowAnalysis();
                              Collection var13 = var12.getUseDefs(var3.getOffset(), var11.getId());
                              if (var13.size() == 1 && (Long)var13.iterator().next() == -1L) {
                                 var9 = true;
                              }
                           }
                        }
                     }

                     if (var9 && var5.setHintArgumentPointsToExternalMemory(var6, true)) {
                        var1++;
                     }
                  }
               }
            }
         }
      }

      return var1;
   }

   public static boolean pC(IEGeneric var0) {
      if (var0 == null) {
         return false;
      } else if (var0.isVar() && var0.asVar().hasFlags(64)) {
         return true;
      } else {
         if (var0.isOperation()) {
            IEOperation var1 = var0.asOperation();
            if (var1.getOperationType().isAnyOf(OperationType.ADD, OperationType.SUB)) {
               IEGeneric var2 = var1.getOperand1();
               IEGeneric var3 = var1.getOperand2();
               IEGeneric var4 = null;
               if (var2.isVar() && var2.asVar().hasFlags(64)) {
                  var4 = var3;
               } else if (var3.isVar() && var3.asVar().hasFlags(64)) {
                  var4 = var2;
               }

               if (var4 != null) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
