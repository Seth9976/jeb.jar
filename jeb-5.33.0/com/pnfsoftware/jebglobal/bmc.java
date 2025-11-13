package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bmc extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf && JUtil.isIfElse(var5)) {
            IJavaIf var6 = (IJavaIf)var5;
            if (var6.getBranchBody(0).isEmpty()) {
               var6.getBranchPredicate(0).reverse(this.of);
               var6.getBranchBody(0).insertAll(0, var6.getDefaultBlock());
               var6.getDefaultBlock().clear();
               var6.setDefaultBlock(null);
               var3++;
            }
         }
      }

      return var3;
   }
}
