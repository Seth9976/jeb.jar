package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.util.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class bms extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         boa var2 = new boa(this.m);
         ArrayList var3 = new ArrayList();
         HashMap var4 = new HashMap();
         HashMap var5 = new HashMap();
         List var6 = var2.A();

         for (int var7 = 0; var7 < var6.size(); var7++) {
            IJavaStatement var8 = (IJavaStatement)var6.get(var7);
            if (var8 instanceof IJavaGoto) {
               var3.add(var7);
            } else if (var8 instanceof IJavaLabel) {
               var4.put((IJavaLabel)var8, var7);
            }
         }

         HashSet var17 = new HashSet();
         var17.add(IJavaLabel.class);
         ArrayList var18 = new ArrayList();

         for (int var10 : var3) {
            IJavaGoto var11 = (IJavaGoto)var6.get(var10);
            IJavaLabel var12 = var11.getLabel();
            Integer var13 = (Integer)var4.get(var12);
            if (var13 != null) {
               Object var14 = (List)var5.get(var12);
               if (var14 == null) {
                  var2.pC(var13, true, false, 2, null);
                  var14 = new ArrayList(var2.wS());
                  var5.put(var12, var14);
               }

               int var15 = var2.pC(var10, true, true, 2, null);
               if (var15 > 0 && var15 != var10) {
                  IJavaStatement var16 = var2.A(var15);
                  if (var15 == var13 || var14.contains(var15) || var16 instanceof IJavaGoto && ((IJavaGoto)var16).getLabel() == var12) {
                     var18.add(var10);
                     var1++;
                  }
               }
            }
         }

         for (int var20 : Lists.reverse(var18)) {
            var6.remove(var20);
         }

         if (var1 > 0) {
            this.m.fromFlatList(var6);
         }

         return var1;
      }
   }
}
