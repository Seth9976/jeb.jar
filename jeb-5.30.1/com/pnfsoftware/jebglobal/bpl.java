package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.HashMap;

public class bpl extends AbstractJStatementOptimizer {
   @Override
   public int optimizeStatement(IJavaStatement var1) {
      if (var1 instanceof IJavaIf var2) {
         int var3 = var2.sizeWithoutDefault();
         if (var3 >= 2) {
            int var4 = -1;
            int var5 = -1;
            HashMap var6 = new HashMap();
            HashMap var7 = new HashMap();
            boolean var8 = false;

            for (int var9 = 0; var9 < var3; var9++) {
               IJavaBlock var10 = var2.getBranchBody(var9);
               if (!var10.isEmpty()) {
                  IJavaStatement var11 = var10.get(0);
                  if (var11 instanceof IJavaLabel var12) {
                     var6.put(var12, var9);
                     if (var7.keySet().contains(var12)) {
                        var4 = (Integer)var7.get(var12);
                        var5 = var9;
                        var8 = true;
                        break;
                     }
                  } else if (var11 instanceof IJavaGoto && var10.size() == 1) {
                     IJavaLabel var14 = ((IJavaGoto)var11).getLabel();
                     var7.put(var14, var9);
                     if (var6.keySet().contains(var14)) {
                        var4 = (Integer)var6.get(var14);
                        var5 = var9;
                        var8 = false;
                        break;
                     }
                  }
               }
            }

            if (var4 >= 0 && var5 >= 0 && var4 != var5) {
               IJavaPredicate var13 = this.jctx
                  .createPredicate(
                     this.jctx
                        .createOperation(var2.getBranchPredicate(var4), this.of.getStandardOperator(JavaOperatorType.LOG_OR), var2.getBranchPredicate(var5))
                  );
               var2.setBranchPredicate(var4, var13);
               if (var8) {
                  var2.setBranchBody(var4, var2.getBranchBody(var5));
               }

               var2.removeBranch(var5);
               return 1;
            }
         }
      }

      return 0;
   }
}
