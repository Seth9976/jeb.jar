package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aiv extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5
            && var5.size() == 3
            && var5.hasDefaultBlock()
            && var5.getBranchBody(0).size() >= 1
            && var5.getDefaultBlock().size() == 1) {
            ICStatement var6 = CUtil.getFirstRealStatement(var5.getBranchBody(0), 0);
            if (var6 instanceof ICLabel && CUtil.isGotoTo(var5.getDefaultBlock().get(0), (ICLabel)var6)) {
               var5.getBranchPredicate(1).reverse(this.of);
               ICOperation var7 = CUtil.orL(this.m, var5.getBranchPredicate(0).getExpression(), var5.getBranchPredicate(1).getExpression());
               ICPredicate var8 = this.ef.createPredicate(var7);
               var5.setBranchPredicate(0, var8);
               if (var5.getBranchBody(1).size() == 0) {
                  var5.setDefaultBlock(null);
               } else {
                  var5.setDefaultBlock(var5.getBranchBody(1));
               }

               var5.removeBranch(1);
               var2++;
            }
         }
      }

      return var2;
   }
}
