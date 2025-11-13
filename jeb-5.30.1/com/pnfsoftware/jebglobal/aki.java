package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aki extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 <= var1.size() - 3; var3++) {
         ICStatement var4 = var1.get(var3);
         ICLabel var5 = CUtil.getIfGotoTarget(var4);
         if (var5 != null) {
            ICStatement var6 = var1.get(var3 + 1);
            ICLabel var7 = CUtil.getGotoLabel(var6);
            if (var7 != null && var1.get(var3 + 2) == var5) {
               ICStatement var8 = var1.remove(var3 + 1);
               ((ICIfStm)var4).getBranchPredicate(0).reverse(this.of);
               ((ICIfStm)var4).getBranchBody(0).set(0, var8);
               var2++;
            }
         }
      }

      return var2;
   }
}
