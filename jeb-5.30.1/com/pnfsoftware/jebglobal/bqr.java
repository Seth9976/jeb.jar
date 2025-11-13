package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.List;

public class bqr extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         List var2 = this.m.toFlatList();
         int var3 = 0;

         label89:
         while (var3 < var2.size()) {
            IJavaStatement var4 = (IJavaStatement)var2.get(var3);
            if (var4 instanceof IJavaGoto) {
               IJavaLabel var5 = ((IJavaGoto)var4).getLabel();
               boolean var6 = true;
               int var7 = var3 + 1;

               while (var7 < var2.size()) {
                  IJavaStatement var8 = (IJavaStatement)var2.get(var7);
                  if (var6 && var8 instanceof IJavaGoto) {
                     var2.remove(var7);
                     var1++;
                     continue label89;
                  }

                  if (var8 == var5) {
                     var2.remove(var3);
                     var1++;
                     continue label89;
                  }

                  if (!(var8 instanceof brp) && !(var8 instanceof brq)) {
                     if (!(var8 instanceof IJavaLabel)
                        && !(var8 instanceof brr)
                        && (!(var8 instanceof bso) || !((bso)var8).q().isLitteralTrue())
                        && (!(var8 instanceof brx) || !((brx)var8).q().isLitteralTrue())
                        && !(var8 instanceof bsa)
                        && !(var8 instanceof bsj)
                        && !(var8 instanceof bsl)
                        && !(var8 instanceof bsh)
                        && !(var8 instanceof bsi)) {
                        if (!(var8 instanceof bry) && !(var8 instanceof brz) && !(var8 instanceof bsm) && !(var8 instanceof bsk) && !(var8 instanceof bsn)) {
                           break;
                        }

                        var6 = false;
                        var7 = bsq.RF(var2, var7);
                     } else {
                        var6 = false;
                        var7++;
                     }
                  } else {
                     var7++;
                  }
               }
            }

            var3++;
         }

         if (var1 > 0) {
            this.m.fromFlatList(var2);
         }

         return var1;
      }
   }
}
