package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.List;

public class bmj extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         List var2 = this.m.toFlatList();

         for (int var3 = 0; var3 < var2.size(); var3++) {
            IJavaStatement var4 = (IJavaStatement)var2.get(var3);
            if (var4 instanceof IJavaGoto var5) {
               IJavaLabel var6 = var5.getLabel();

               for (int var7 = 0; var7 < var2.size() - 1; var7++) {
                  IJavaStatement var8 = (IJavaStatement)var2.get(var7);
                  if (var8 == var6) {
                     IJavaStatement var9 = (IJavaStatement)var2.get(var7 + 1);
                     if (var9 instanceof IJavaReturn var10) {
                        IJavaExpression var13 = var10.getExpression();
                        if (var13 == null || JUtil.isImmOrVarLike(var13)) {
                           var2.set(var3, this.jctx.createReturn(var13 == null ? null : var13.duplicate()));
                           var1++;
                        }
                        break;
                     }

                     if (var9 instanceof IJavaThrow var11) {
                        IJavaThrow var12 = var11.duplicate();
                        var2.set(var3, var12);
                        var1++;
                     } else if (var9 instanceof bnp && var7 + 2 == var2.size()) {
                        var2.set(var3, this.jctx.createReturn());
                        var1++;
                     }
                     break;
                  }
               }
            }
         }

         if (var1 > 0) {
            this.m.fromFlatList(var2);
         }

         return var1;
      }
   }
}
