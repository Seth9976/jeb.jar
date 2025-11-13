package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.Collection;
import java.util.Map;

public class auq extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(auq.class);

   public auq() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.AGGRESSIVE);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      IDFA var2 = this.cfg.doDataFlowAnalysis();
      Map var3 = var2.getInputMap();

      for (int var5 : var3.keySet()) {
         Integer var6 = this.ectx.getUnderlyingRegisterId(var5);
         if (var6 != null) {
            IEImm var7 = this.getMasterOptimizerSafe().getDefaultInput(var6);
            if (var7 != null) {
               IEVar var8 = this.ectx.getVariableById(var5);
               if (var8 != null) {
                  for (long var11 : (Collection)var3.get(var5)) {
                     IEStatement var13 = (IEStatement)this.cfg.getInstruction(var11);
                     if (var2.checkSingleDef(var11, var5, -1L)) {
                        var1 += var13.replaceVar(var8, var7, true);
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
