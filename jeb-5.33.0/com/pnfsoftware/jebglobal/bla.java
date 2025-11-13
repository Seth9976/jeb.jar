package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;

public class bla extends AbstractJStatementOptimizer {
   @Override
   public int optimizeStatement(IJavaStatement var1) {
      if (var1 instanceof IJavaIf var2 && var2.size() >= 2 && var2.getDefaultBlock() != null) {
         int var3 = var2.size() - 2;
         if (var2.getBranchBody(var3).size() == 1
            && var2.getBranchBody(var3).get(0) instanceof IJavaIf
            && (var2.getDefaultBlock().size() != 1 || !(var2.getDefaultBlock().get(0) instanceof IJavaIf))) {
            IJavaBlock var8 = var2.getBranchBody(var3);
            IJavaBlock var9 = var2.getDefaultBlock();
            var2.getBranchPredicate(var3).reverse(this.of);
            var2.setBranchBody(var3, var9);
            var2.setDefaultBlock(var8);
            return 1;
         }

         if (var2.getDefaultBlock().size() == 1 && var2.getDefaultBlock().get(0) instanceof IJavaIf) {
            IJavaIf var4 = (IJavaIf)var2.getDefaultBlock().get(0);
            boolean var5 = var4.getDefaultBlock() != null;
            int var6 = var4.size() - (var5 ? 1 : 0);

            for (int var7 = 0; var7 < var6; var7++) {
               var2.addBranch(var4.getBranchPredicate(var7), var4.getBranchBody(var7));
            }

            if (var5) {
               var2.setDefaultBlock(var4.getDefaultBlock());
            } else {
               var2.setDefaultBlock(null);
            }

            return 1;
         }
      }

      return 0;
   }
}
