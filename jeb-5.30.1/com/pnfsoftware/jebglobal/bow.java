package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bow extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         if (var1.get(var4) instanceof IJavaDoWhile var6 && JUtil.getFirstRealStatement(var1, var4 + 1) instanceof IJavaLabel var8 && this.q(var6, var8)) {
            var3++;
         }
      }

      return var3;
   }

   private boolean q(IJavaDoWhile var1, IJavaLabel var2) {
      if (!var1.getPredicate().isLitteralTrue()) {
         return false;
      } else {
         IJavaBlock var3 = var1.getBody();
         if (var3.size() < 3) {
            return false;
         } else if (!(JUtil.getFirstRealStatement(var3, 0) instanceof IJavaLabel var5)) {
            return false;
         } else if (!(var3.getLast() instanceof IJavaGoto)) {
            return false;
         } else {
            IJavaGoto var6 = (IJavaGoto)var3.getLast();
            if (var6.getLabel() != var2) {
               return false;
            } else if (!(var3.get(var3.size() - 2) instanceof IJavaIf)) {
               return false;
            } else {
               IJavaIf var7 = (IJavaIf)var3.get(var3.size() - 2);
               if (var7.size() != 1) {
                  return false;
               } else {
                  IJavaBlock var8 = var7.getBranchBody(0);
                  if (var8.size() == 1 && var8.get(0) instanceof IJavaGoto) {
                     IJavaGoto var9 = (IJavaGoto)var8.get(0);
                     if (var9.getLabel() == var5) {
                        var3.removeLast();
                        var1.setPredicate(var7.getBranchPredicate(0));
                        var3.removeLast();
                        return true;
                     } else {
                        return false;
                     }
                  } else {
                     var3.insertAll(var3.size() - 1, var8);
                     var7.getBranchPredicate(0).reverse(this.of);
                     var7.setBranchBody(0, this.jctx.createBlock(this.jctx.createGoto(var6.getLabel())));
                     return true;
                  }
               }
            }
         }
      }
   }
}
