package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ahr extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5) {
            int var6 = 0;
            ICPredicate var7 = null;

            while (var6 < var5.sizeWithoutDefault()) {
               ICBlock var8 = (ICBlock)var5.getBlocks().get(var6);
               ICPredicate var9 = var5.getBranchPredicate(var6);
               if (var8.size() == 0 && CUtil.hasNoSideEffects(var9)) {
                  var5.removeBranch(var6);
                  var2++;
                  if (var5.size() == 0) {
                     var1.remove(var3);
                  }

                  var9.reverse(this.of);
                  var7 = var7 == null ? var9 : this.ef.createPredicate(CUtil.andL(this.m, var7.getExpression().duplicate(), var9.getExpression()));
               } else {
                  if (var7 != null) {
                     var5.setBranchPredicate(var6, this.ef.createPredicate(CUtil.andL(this.m, var7.getExpression().duplicate(), var9.getExpression())));
                  }

                  var6++;
               }
            }

            if (var5.hasDefaultBlock() && var7 != null) {
               var5.addBranch(var7.duplicate(), var5.getDefaultBlock());
               var5.setDefaultBlock(null);
            }

            if (var5.hasDefaultBlock() && var5.getDefaultBlock().size() == 0) {
               var5.setDefaultBlock(null);
               var2++;
            }
         }
      }

      return var2;
   }
}
