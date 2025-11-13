package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class bqw extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         HashMap var2 = new HashMap();
         ArrayList var3 = new ArrayList();
         List var4 = this.m.toFlatList();

         for (int var5 = 0; var5 < var4.size(); var5++) {
            IJavaStatement var6 = (IJavaStatement)var4.get(var5);
            bqw.eo var7 = new bqw.eo();
            var2.put(var6, var7);
            if (var6 instanceof IJavaAssignment var8 && var8.isSimpleAssignment() && var8.getLeft() instanceof IJavaDefinition) {
               IJavaIdentifier var9 = ((IJavaDefinition)var8.getLeft()).getIdentifier();
               var7.q = var9;
               var3.add(var9);
            }

            if (!(var6 instanceof bsq)) {
               var7.RF = this.q(var6);
            }
         }

         int var19 = 1;
         if (this.m.isConstructor()) {
            IJavaStatement var20 = (IJavaStatement)var4.get(1);
            if (var20 instanceof IJavaCall) {
               var19++;
            }
         }

         for (IJavaIdentifier var22 : var3) {
            int var23 = -1;
            boolean var10 = false;
            ArrayDeque var11 = new ArrayDeque();
            int var12 = -1;
            int var13 = -1;
            byte var14 = -1;

            for (int var18 = 0; var18 < var4.size(); var18++) {
               IJavaStatement var15 = (IJavaStatement)var4.get(var18);
               if (var23 < 0) {
                  if (var15 instanceof brp) {
                     var11.push((brp)var15);
                  } else if (var15 instanceof brq) {
                     var11.pop();
                  }
               } else if (var15 instanceof brp) {
                  var12++;
               } else if (var15 instanceof brq) {
                  if (--var12 < var13 && var10) {
                     var10 = false;
                  }
               }

               bqw.eo var16 = (bqw.eo)var2.get(var15);
               if (var16 != null) {
                  if (var16.q == var22) {
                     var23 = var18;
                     var10 = true;
                     var12 = var11.size();
                     var13 = var12;
                  }

                  if (var16.RF != null && !var10 && var16.RF.contains(var22)) {
                     var14 = 0;
                  }
               }
            }

            if (var14 >= 0 && var23 >= 0) {
               IJavaStatement var24 = (IJavaStatement)var4.get(var23);
               IJavaAssignment var25 = (IJavaAssignment)var24;
               IJavaDefinition var17 = (IJavaDefinition)var25.getLeft();
               var25.setLeft(var17.getIdentifier());
               var4.add(var19, var17);
               var1++;
            }
         }

         if (var1 > 0) {
            this.m.fromFlatList(var4);
         }

         return var1;
      }
   }

   private Set q(IJavaStatement var1) {
      HashSet var2 = new HashSet();
      this.q(var1, var2);
      return var2;
   }

   private void q(IJavaElement var1, Set var2) {
      for (IJavaElement var4 : var1.getSubElements()) {
         if (!JUtil.isClassMethodField(var4)) {
            if (var4 instanceof IJavaIdentifier) {
               var2.add((IJavaIdentifier)var4);
            }

            this.q(var4, var2);
         }
      }
   }

   static class eo {
      IJavaIdentifier q;
      Set RF;
   }
}
