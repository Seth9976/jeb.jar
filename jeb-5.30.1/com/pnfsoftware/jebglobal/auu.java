package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.List;

public class auu extends AbstractEOptimizer {
   private static final StructuredLogger q = aeg.q(auu.class);

   public auu() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (AddressableInstruction var3 : this.cfg.addressableInstructions()) {
         if (var3.getInstruction() instanceof IECall) {
            IECall var4 = (IECall)var3.getInstruction();
            if (!var4.isReturnExpressionUnused()) {
               List var5 = var4.getReturnExpressions();
               if (var5.size() == 1) {
                  IEGeneric var6 = (IEGeneric)var5.get(0);
                  if (var6 instanceof IEVar var7) {
                     IDFA var8 = this.cfg.doDataFlowAnalysis();
                     if (var8.checkNoUse(var3.getOffset(), var7.getId())) {
                        var4.setReturnExpressionUnused(true);
                        var1++;
                     }
                  }
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
