package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bpy extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : this.q(this.m.getBody());
   }

   int q(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var4 instanceof IJavaWhile && ((IJavaWhile)var4).getPredicate().isLitteralTrue() && this.q((IJavaWhile)var4, var1, var3)) {
            var2++;
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.q(var6);
            }
         }
      }

      return var2;
   }

   private boolean q(IJavaWhile var1, IJavaBlock var2, int var3) {
      IJavaBlock var4 = var1.getBody();
      if (var4.size() < 2) {
         return false;
      } else if (!(var4.get(0) instanceof IJavaAssignment var6)) {
         return false;
      } else if (!var6.isSimpleAssignment()) {
         return false;
      } else if (!(var6.getLeft() instanceof IJavaDefinition)) {
         return false;
      } else {
         IJavaExpression var7 = var6.getRight();
         IJavaDefinition var8 = (IJavaDefinition)var6.getLeft();
         IJavaStatement var14 = var4.get(1);
         if (!JUtil.isIf(var14)) {
            return false;
         } else {
            IJavaIf var9 = (IJavaIf)var14;
            IJavaBlock var10 = var9.getBranchBody(0);
            if (var10.size() != 1) {
               return false;
            } else if (!JUtil.isSimpleBreak(var10.get(0))) {
               return false;
            } else {
               IJavaPredicate var11 = var9.getBranchPredicate(0);
               if (var11.getExpression() instanceof IJavaOperation var12) {
                  if (var12.getLeft() != var8.getIdentifier()) {
                     return false;
                  } else {
                     IJavaAssignment var15 = this.jctx.createAssignment(var8.getIdentifier(), var7);
                     var12.setLeft(var15);
                     var11.reverse(this.of);
                     var1.setPredicate(var11);
                     var4.remove(0);
                     var4.remove(0);
                     var2.insert(var3, var8);
                     return true;
                  }
               } else {
                  return false;
               }
            }
         }
      }
   }
}
