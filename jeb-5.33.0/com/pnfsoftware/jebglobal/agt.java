package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class agt extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5 && var5.size() >= 2 && var5.hasDefaultBlock()) {
            int var6 = var5.sizeWithoutDefault() - 1;
            if (var5.getBranchBody(var6).size() == 1
               && var5.getBranchBody(var6).get(0) instanceof ICIfStm
               && (var5.getDefaultBlock().size() != 1 || !(var5.getDefaultBlock().get(0) instanceof ICIfStm))) {
               ICBlock var7 = var5.getBranchBody(var6);
               ICBlock var8 = var5.getDefaultBlock();
               var5.getBranchPredicate(var6).reverse(this.of);
               var5.setBranchBody(var6, var8);
               var5.setDefaultBlock(var7);
               var2++;
            }

            if (var5.getDefaultBlock().size() == 1 && var5.getDefaultBlock().get(0) instanceof ICIfStm) {
               ICIfStm var11 = (ICIfStm)var5.getDefaultBlock().get(0);
               boolean var12 = var11.getDefaultBlock() != null;
               int var9 = var11.size() - (var12 ? 1 : 0);

               for (int var10 = 0; var10 < var9; var10++) {
                  var5.addBranch(var11.getBranchPredicate(var10), var11.getBranchBody(var10));
               }

               if (var12) {
                  var5.setDefaultBlock(var11.getDefaultBlock());
               } else {
                  var5.setDefaultBlock(null);
               }

               var2++;
            }
         }
      }

      return var2;
   }
}
