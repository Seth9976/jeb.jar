package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;

public class aqg extends AbstractEOptimizer {
   private static final StructuredLogger pC = aco.pC(aqg.class);

   public aqg() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setPriority(Double.MAX_VALUE);
   }

   @Override
   protected int perform() {
      int var1 = this.cfg.simplify(false, true, true);
      if (var1 > 0) {
         Object[] var10000 = new Object[0];

         for (BasicBlock var3 : this.cfg.getBlocks()) {
            long var4 = var3.getFirstAddress();
            int var6 = 0;

            while (var6 < var3.size() - 1) {
               IEStatement var7 = (IEStatement)var3.get(var6);
               if (!var7.getBreakingFlow(var4).isBroken()) {
                  var4 += var7.getSize();
                  var6++;
               } else {
                  Object var8;
                  if (var7 instanceof IEJump && ((IEJump)var7).getCondition() != null) {
                     IEGeneric var9 = ((IEJump)var7).getCondition();
                     var8 = this.ectx
                        .createAssign(this.ectx.getGlobalContext().createVirtualRegister("oldJumpCondition" + var9.getBitsize(), var9.getBitsize()), var9);
                     ((IEStatement)var8).copyProperties(var7);
                  } else {
                     var8 = this.ectx.createNop(var7);
                  }

                  var3.set(var6, (IInstruction)var8);
                  var4 += ((IEStatement)var8).getSize();
                  var6++;
               }
            }
         }
      }

      return this.postPerform(var1);
   }
}
