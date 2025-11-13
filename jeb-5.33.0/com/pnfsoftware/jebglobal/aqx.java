package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class aqx extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(aqx.class);

   public aqx() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.AGGRESSIVE);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      IDFA var2 = this.cfg.doDataFlowAnalysis();
      ams var3 = new ams(this.ectx);
      ams.Av var4 = var3.pC();

      for (BasicBlock var6 : this.cfg) {
         Map var7 = var4.pC(var6.getBase()).pC();
         if (!var7.isEmpty()) {
            HashSet var8 = new HashSet(var7.keySet());
            long var9 = var6.getBase();

            for (int var11 = 0; var11 < var6.size(); var11++) {
               IEStatement var12 = (IEStatement)var6.get(var11);

               for (Integer var15 : var2.getInstructionUses(var9)) {
                  if (var8.contains(var15)) {
                     IEImm var16 = (IEImm)var7.get(var15);
                     var1 += var12.replaceUsedVar(this.ectx.getVariableById(var15), var16);
                  }
               }

               Collection var17 = var2.getInstructionAllDefs(var9);
               var8.removeAll(var17);
               var9 += var12.getSize();
            }
         }
      }

      return this.postPerform(var1);
   }
}
