package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class agr extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5 && var5.hasDefaultBlock()) {
            boolean var6 = true;

            for (ICBlock var8 : var5.getConditionalBlocks()) {
               if (var8.isEmpty() || !(var8.getLast() instanceof ICReturn)) {
                  var6 = false;
                  break;
               }
            }

            if (var6) {
               ICBlock var9 = var5.getDefaultBlock();
               var5.setDefaultBlock(null);
               var1.insertAll(var3 + 1, var9);
               var2++;
            }
         }
      }

      return var2;
   }
}
