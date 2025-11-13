package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;

public class aot extends AbstractEOptimizer {
   private boolean pC = true;
   private boolean A = false;

   public aot() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
   }

   @Override
   protected int perform() {
      int var1 = 0;
      int var2 = 0;

      do {
         var1 += var2;
         var2 = 0;

         for (BasicBlock var4 : this.cfg.getBlocks()) {
            IEStatement var5 = (IEStatement)var4.getLast();
            IEGeneric var6;
            if (this.pC && var5.isConditionalJump()) {
               IEJump var8 = ((IEStatement)var4.getLast()).asJump();
               var6 = var8.getCondition();
            } else {
               if (!this.A || !var5.isConditionalJumpFar()) {
                  continue;
               }

               IEJumpFar var7 = var5.asJumpFar();
               var6 = var7.getCondition();
            }

            if (var6 instanceof IEImm) {
               boolean var9 = ((IEImm)var6)._signum() != 0;
               aoe.pC(this.ectx, var4, var9);
               var2++;
               break;
            }
         }
      } while (var2 > 0);

      return this.postPerform(var1);
   }
}
