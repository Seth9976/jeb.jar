package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class blb extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf && ((IJavaIf)var5).hasDefaultBlock()) {
            IJavaIf var6 = (IJavaIf)var5;
            if (var4 + 1 < var1.size() && var1.get(var4 + 1) instanceof IJavaLabel) {
               IJavaLabel var7 = (IJavaLabel)var1.get(var4 + 1);
               if (var6.getDefaultBlock().size() >= 1) {
                  IJavaStatement var8 = var6.getDefaultBlock().get(0);
                  IJavaLabel var9 = JUtil.isIfGoto(var8);
                  if (var9 == var7) {
                     IJavaBlock var10 = var6.setDefaultBlock(null);
                     IJavaIf var11 = (IJavaIf)var10.remove(0);
                     IJavaPredicate var12 = var11.getBranchPredicate(0);
                     var12.reverse(this.of);
                     var6.addBranch(var12, var10);
                     var3++;
                  }
               }
            }
         }
      }

      return var3;
   }
}
