package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.opt;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;

public abstract class AbstractEStatementOptimizer extends AbstractEOptimizer {
   public AbstractEStatementOptimizer(DataChainsUpdatePolicy var1) {
      super(var1);
      if (var1 == DataChainsUpdatePolicy.UPDATE_IF_REQUIRED) {
         throw new IllegalArgumentException();
      }
   }

   @Override
   protected int perform() {
      int var1 = 0;

      for (BasicBlock var3 : this.cfg.getBlocks()) {
         for (int var4 = 0; var4 < var3.size(); var4++) {
            IEStatement var5 = (IEStatement)var3.get(var4);
            IEStatement var6 = this.optimizeStatement(var5);
            if (var6 != null) {
               var3.set(var4, var6);
               var1++;
            }
         }
      }

      return this.postPerform(var1);
   }

   protected abstract IEStatement optimizeStatement(IEStatement var1);
}
