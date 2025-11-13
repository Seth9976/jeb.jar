package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemoryShim;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.ArrayList;

@Ser
public class Av extends AbstractAnalyzerExtension {
   private static final ILogger pC = GlobalLog.getLogger(Av.class);

   @Override
   public ChainedOperationResult getTrampolineTarget(CFG var1) {
      if (var1.size() > 2) {
         return ChainedOperationResult.stop();
      } else {
         BasicBlock var2 = var1.getEntryBlock();
         if (var2.size() >= 3 && var2.size() <= 4) {
            yt var3 = (yt)var2.get(0);
            if (!var3.getMnemonic().equalsIgnoreCase("auipc")) {
               return ChainedOperationResult.stop();
            } else {
               var3 = (yt)var2.get(1);
               if (!var3.getMnemonic().equalsIgnoreCase("ld") && !var3.getMnemonic().equalsIgnoreCase("lw")) {
                  return ChainedOperationResult.stop();
               } else {
                  var3 = (yt)var2.get(2);
                  if (!var3.getMnemonic().equalsIgnoreCase("jalr")) {
                     return ChainedOperationResult.stop();
                  } else {
                     try {
                        Sv var4 = new Sv((sy)this.gca.getProcessor());
                        IERoutineContext var5 = var4.getGlobalContext().createRoutineContext();
                        var4.setCurrentContext(var5);
                        ArrayList var6 = new ArrayList();
                        var4.convertBlock(var2, var6);
                        EState var7 = var4.getGlobalContext().buildEmptyState();
                        var7.setMaxEvaluationCount(10);
                        var7.setExecuteSubRoutines(true);
                        IVirtualMemoryShim var8 = VirtualMemoryUtil.getCopyOnWriteShim(this.gca.getMemory());
                        var7.setMemory(var8);

                        for (IEStatement var10 : var6) {
                           if (var10.isAssign() && var10.asAssign().getRightOperand().isMem()) {
                              IEMem var11 = var10.asAssign().getRightOperand().asMem();
                              IEGeneric var12 = var11.getReference();
                              long var13 = var12.evaluate(var7).getValueAsAddress();
                              return new ChainedOperationResult(new Pointer(var13, var11.getBitsize() / 8, 5));
                           }

                           var10.evaluate(var7);
                        }
                     } catch (Exception var15) {
                     }

                     return ChainedOperationResult.stop();
                  }
               }
            }
         } else {
            return ChainedOperationResult.stop();
         }
      }
   }
}
