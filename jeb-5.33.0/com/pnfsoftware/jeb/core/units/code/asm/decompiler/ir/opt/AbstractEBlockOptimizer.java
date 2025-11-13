package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;

public abstract class AbstractEBlockOptimizer extends AbstractEOptimizer {
   public AbstractEBlockOptimizer(DataChainsUpdatePolicy var1) {
      super(var1);
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg.getBlocks()) {
         int var4 = this.optimizeBlock(var3);
         if (var4 < 0) {
            var1 += -var4;
            break;
         }

         var1 += var4;
      }

      return this.postPerform(var1);
   }

   protected abstract int optimizeBlock(BasicBlock var1);
}
