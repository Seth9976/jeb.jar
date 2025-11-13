package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class bln extends AbstractJStatementOptimizer {
   @Override
   public int optimizeStatement(IJavaStatement var1) {
      if (var1 instanceof IJavaIf var2 && var2.size() == 3 && var2.hasDefaultBlock() && var2.getBranchBody(0).size() >= 1 && var2.getDefaultBlock().size() == 1
         )
       {
         IJavaStatement var3 = JUtil.getFirstRealStatement(var2.getBranchBody(0), 0);
         if (var3 instanceof IJavaLabel && JUtil.isGotoTo(var2.getDefaultBlock().get(0), (IJavaLabel)var3)) {
            var2.getBranchPredicate(1).reverse(this.of);
            IJavaPredicate var4 = this.jctx
               .createPredicate(
                  this.jctx.createOperation(var2.getBranchPredicate(0), this.of.getStandardOperator(JavaOperatorType.LOG_OR), var2.getBranchPredicate(1))
               );
            var2.setBranchPredicate(0, var4);
            var2.setDefaultBlock(var2.getBranchBody(1));
            var2.removeBranch(1);
            return 1;
         }
      }

      return 0;
   }
}
