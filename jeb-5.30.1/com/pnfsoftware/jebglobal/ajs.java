package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ajs extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size() - 1) {
         ICStatement var4 = var1.get(var3);
         ICStatement var5 = var1.get(var3 + 1);
         if (!CUtil.canFallthrough(var4, var5, false)) {
            var1.remove(var3 + 1);
            var2++;
         } else {
            var3++;
         }
      }

      return var2;
   }
}
