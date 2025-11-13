package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;

public abstract class AbstractCStatementOptimizer extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         ICStatement var5 = this.optimizeStatement(var4);
         if (var5 != null) {
            var1.set(var3, var5);
            var2++;
         }
      }

      return var2;
   }

   protected abstract ICStatement optimizeStatement(ICStatement var1);
}
