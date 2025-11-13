package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;

public class aom extends AbstractEOptimizer {
   public aom() {
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         IEStatement var4 = (IEStatement)var3.getLast();
         if (var4 instanceof IECall && var3.outsize() == 1 && Boolean.TRUE.equals(var4.asCall().getNonReturning())) {
            this.cfg.deleteOutEdges(var3);
            var1++;
         }
      }

      if (var1 > 0) {
         this.ectx.invalidateDataFlowAnalysis();
         this.cleanCfg();
      }

      return var1;
   }
}
