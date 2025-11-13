package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ajm extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5 && var5.size() == 2 && var5.hasDefaultBlock()) {
            ICBlock var6 = var5.getBranchBody(0);
            ICBlock var7 = var5.getDefaultBlock();
            boolean var8 = !var6.isEmpty() && var6.getLast() instanceof ICCall && ((ICCall)var6.getLast()).isNonReturningCall();
            boolean var9 = !var7.isEmpty() && var7.getLast() instanceof ICCall && ((ICCall)var7.getLast()).isNonReturningCall();
            if (var8 && !var9) {
               var5.setDefaultBlock(null);
               var1.insertAll(var3 + 1, var7);
               var2++;
            } else if (!var8 && var9) {
               var5.setDefaultBlock(null);
               var1.insertAll(var3 + 1, var6);
               var6.clear();
               var6.insertAll(0, var7);
               var5.getBranchPredicate(0).reverse(this.of);
               var2++;
            }
         }
      }

      return var2;
   }
}
