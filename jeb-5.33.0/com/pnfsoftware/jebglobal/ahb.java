package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ahb extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICDoWhileStm var5) {
            ICPredicate var6 = var5.getPredicate();
            if (var6.isLitteralTrue()) {
               ICWhileStm var7 = this.ef.createWhileStm(var6, var5.getBody());
               var1.set(var3, var7);
               var2++;
            }
         }
      }

      return var2;
   }
}
