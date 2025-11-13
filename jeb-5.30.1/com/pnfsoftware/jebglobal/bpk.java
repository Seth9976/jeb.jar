package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;

public class bpk extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         if (var1.get(var4) instanceof IJavaIf var6 && var6.size() == 2 && var6.hasDefaultBlock() && var6.getBranchBody(0).size() == 1) {
            IJavaStatement var7 = var6.getBranchBody(0).get(0);
            if (var7 instanceof IJavaGoto) {
               IJavaLabel var8 = ((IJavaGoto)var7).getLabel();
               if (var6.getDefaultBlock().size() >= 2) {
                  IJavaStatement var9 = var6.getDefaultBlock().get(0);
                  IJavaStatement var10 = var6.getDefaultBlock().get(1);
                  if (var9 instanceof IJavaGoto && var10 == var8) {
                     var6.getDefaultBlock().remove(0);
                     var1.insertAll(var4 + 1, var6.getDefaultBlock());
                     var6.setDefaultBlock(null);
                     var6.getBranchPredicate(0).reverse(this.of);
                     ((IJavaGoto)var7).setLabel(((IJavaGoto)var9).getLabel());
                     var3++;
                  }
               }
            }
         }
      }

      return var3;
   }
}
