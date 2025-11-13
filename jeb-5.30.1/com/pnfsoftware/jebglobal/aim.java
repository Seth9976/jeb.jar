package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aim extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICIfStm && ((ICIfStm)var4).hasDefaultBlock()) {
            ICIfStm var5 = (ICIfStm)var4;
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
