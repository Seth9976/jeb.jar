package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConditionalExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;

public class bov extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf var6 && var6.size() == 2 && var6.hasDefaultBlock()) {
            IJavaBlock var7 = var6.getBranchBody(0);
            IJavaBlock var8 = var6.getDefaultBlock();
            if (var7.size() == 1 && var8.size() == 1) {
               IJavaStatement var9 = var7.get(0);
               IJavaStatement var10 = var8.get(0);
               if (var9 instanceof IJavaAssignment && var10 instanceof IJavaAssignment) {
                  IJavaAssignment var11 = (IJavaAssignment)var9;
                  IJavaAssignment var12 = (IJavaAssignment)var10;
                  if (var11.isSimpleAssignment() && var12.isSimpleAssignment()
                     || var11.isCombinedOperatorAssignment()
                        && var12.isCombinedOperatorAssignment()
                        && var11.getCombinedOperator().equals(var12.getCombinedOperator())) {
                     IJavaExpression var13 = var11.getRight();
                     IJavaExpression var14 = var12.getRight();
                     if (!(var13 instanceof IJavaConditionalExpression) && !(var14 instanceof IJavaConditionalExpression)) {
                        boolean var15 = false;
                        IJavaPredicate var16 = var6.getBranchPredicate(0);
                        if (!var11.getLeft().equals(var12.getLeft())
                           && (!(var11.getLeft() instanceof IJavaDefinition) || !((IJavaDefinition)var11.getLeft()).getIdentifier().equals(var12.getLeft()))) {
                           if (var12.getLeft() instanceof IJavaDefinition && ((IJavaDefinition)var12.getLeft()).getIdentifier().equals(var11.getLeft())) {
                              IJavaConditionalExpression var27 = this.jctx.createConditionalExpression(var16, var13, var14);
                              var12.setRight(var27);
                              var1.set(var4, var12);
                              var15 = true;
                           }
                        } else {
                           IJavaConditionalExpression var17 = this.jctx.createConditionalExpression(var16, var13, var14);
                           var11.setRight(var17);
                           var1.set(var4, var11);
                           var15 = true;
                        }

                        if (var15) {
                           var3++;
                           continue;
                        }
                     }
                  }
               }
            }
         }

         if (var5 instanceof IJavaIf var18 && var18.size() == 1 && !var18.hasDefaultBlock()) {
            IJavaBlock var19 = var18.getBranchBody(0);
            if (var19.size() == 1
               && var19.get(0) instanceof IJavaReturn var21
               && var21.getExpression() != null
               && var4 + 1 < var1.size()
               && var1.get(var4 + 1) instanceof IJavaReturn var23
               && var23.getExpression() != null) {
               IJavaExpression var24 = var21.getExpression();
               IJavaExpression var25 = var23.getExpression();
               if (!(var24 instanceof IJavaConditionalExpression) && !(var25 instanceof IJavaConditionalExpression)) {
                  IJavaConditionalExpression var26 = this.jctx.createConditionalExpression(var18.getBranchPredicate(0), var24, var25);
                  var23.setExpression(var26);
                  var1.remove(var4);
                  var3++;
                  continue;
               }
            }
         }

         var4++;
      }

      return var3;
   }
}
