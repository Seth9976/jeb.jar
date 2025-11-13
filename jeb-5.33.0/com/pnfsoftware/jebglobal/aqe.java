package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.AbstractEOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt.DataChainsUpdatePolicy;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerMode;

public class aqe extends AbstractEOptimizer {
   public aqe() {
      super(DataChainsUpdatePolicy.UPDATE_IF_OPTIMIZED);
      this.setRequiredModeThreshold(OptimizerMode.UNFRIENDLY);
   }

   @Override
   public int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            if (var5 instanceof IEAssign var6 && var6.getDstOperand() instanceof IESlice var8 && var8.getWholeExpression() instanceof IEVar) {
               IEVar var9 = (IEVar)var8.getWholeExpression();
               int var10 = var9.getBitsize();
               int var11 = var8.getBitStart();
               int var12 = var8.getBitEnd();
               IEGeneric var13 = var6.getSrcOperand();
               Object var14;
               if (var11 == 0) {
                  if (var12 == var10) {
                     var14 = var13;
                  } else {
                     var14 = this.ectx.createCompose(var13, var9.slice(var12, var10));
                  }
               } else if (var12 == var10) {
                  var14 = this.ectx.createCompose(var9.slice(0, var11), var13);
               } else {
                  var14 = this.ectx.createCompose(var9.slice(0, var11), var13, var9.slice(var12, var10));
               }

               var3.set(var4, var6.duplicateWithNewOperands(var9, (IEGeneric)var14));
               var1++;
            }
         }
      }

      return var1;
   }
}
