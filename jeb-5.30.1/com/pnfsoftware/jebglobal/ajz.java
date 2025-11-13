package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;

public class ajz extends AbstractCOptimizer {
   @Override
   protected int perform() {
      if (this.m.isEmpty()) {
         return 0;
      } else if (this.m.getLastStatement() instanceof ICReturn var2 && var2.returnsVoid()) {
         this.m.deleteStatement(var2);
         return 1;
      } else {
         return 0;
      }
   }
}
