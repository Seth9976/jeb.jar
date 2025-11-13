package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class bph extends AbstractJStatementOptimizer {
   @Override
   public int optimizeStatement(IJavaStatement var1) {
      if (var1 instanceof IJavaIf var2 && var2.getDefaultBlock() == null) {
         int var3 = var2.size() - 1;
         if (var2.getBranchBody(var3).size() == 1 && var2.getBranchBody(var3).get(0) instanceof IJavaIf var5 && var5.size() == 1) {
            IJavaPredicate var6 = this.jctx
               .createPredicate(
                  this.jctx.createOperation(var2.getBranchPredicate(var3), this.of.getStandardOperator(JavaOperatorType.LOG_AND), var5.getBranchPredicate(0))
               );
            var2.setBranchPredicate(var3, var6);
            var2.setBranchBody(var3, var5.getBranchBody(0));
            return 1;
         }
      }

      return 0;
   }
}
