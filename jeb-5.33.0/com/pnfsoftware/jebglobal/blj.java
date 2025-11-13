package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class blj extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf && var4 + 1 < var1.size() && var1.get(var4 + 1) instanceof IJavaLabel) {
            IJavaIf var6 = (IJavaIf)var5;
            IJavaLabel var7 = (IJavaLabel)var1.get(var4 + 1);
            if (var6.size() == 1
               && var6.getBranchBody(0).size() >= 1
               && var6.getBranchBody(0).get(0) instanceof IJavaIf var9
               && var9.size() == 1
               && var9.getBranchBody(0).size() == 1) {
               IJavaStatement var10 = var9.getBranchBody(0).get(0);
               if (JUtil.isGotoTo(var10, var7)) {
                  var9.getBranchPredicate(0).reverse(this.of);
                  IJavaPredicate var11 = this.jctx
                     .createPredicate(
                        this.jctx
                           .createOperation(var6.getBranchPredicate(0), this.of.getStandardOperator(JavaOperatorType.LOG_AND), var9.getBranchPredicate(0))
                     );
                  var6.setBranchPredicate(0, var11);
                  var6.getBranchBody(0).remove(0);
                  var3++;
               }
            }
         }
      }

      return var3;
   }
}
