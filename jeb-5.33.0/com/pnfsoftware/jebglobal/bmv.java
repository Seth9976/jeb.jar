package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class bmv extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf && JUtil.isIfElse(var5)) {
            IJavaIf var6 = (IJavaIf)var5;
            IJavaPredicate var7 = var6.getBranchPredicate(0);
            if (var7.getExpression() instanceof IJavaOperation var8 && var8.getOperator().isAnyOf(JavaOperatorType.NE, JavaOperatorType.LOG_NOT)) {
               var6.getBranchPredicate(0).reverse(this.of);
               IJavaBlock var11 = var6.getBranchBody(0);
               IJavaBlock var10 = var6.setDefaultBlock(var11);
               var6.setBranchBody(0, var10);
               var3++;
            }
         }
      }

      return var3;
   }
}
