package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaForEach;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bkp extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : this.pC(this.m.getBody());
   }

   int pC(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var4 instanceof IJavaCompound
            && (var4 instanceof IJavaFor || var4 instanceof IJavaForEach || var4 instanceof IJavaWhile || var4 instanceof IJavaDoWhile)) {
            IJavaBlock var5 = (IJavaBlock)((IJavaCompound)var4).getBlocks().get(0);
            if (!var5.isEmpty()) {
               if (this.A(var5)) {
                  var2++;
               } else if (this.kS(var5)) {
                  var2++;
               } else if (this.wS(var5)) {
                  var2++;
               } else if (this.UT(var5)) {
                  var2++;
               } else if (this.E(var5)) {
                  var2++;
               }
            }
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.pC(var6);
            }
         }
      }

      return var2;
   }

   private boolean A(IJavaBlock var1) {
      if (var1.size() < 2) {
         return false;
      } else {
         int var2 = var1.size() - 1;
         IJavaStatement var3 = var1.get(var2);
         if (!JUtil.isSimpleBreak(var3)) {
            return false;
         } else {
            var2--;

            for (; var2 >= 0; var2--) {
               var3 = var1.get(var2);
               if (JUtil.isIf(var3)) {
                  var3 = ((IJavaIf)var3).getBranchBody(0).getLast();
                  if (JUtil.isSimpleContinue(var3)) {
                     break;
                  }
               }
            }

            if (var2 < 0) {
               return false;
            } else {
               IJavaIf var4 = (IJavaIf)var1.get(var2);
               var2++;
               IJavaBlock var5 = var4.getBranchBody(0);
               int var6 = var5.size();
               var4.getBranchPredicate(0).reverse(this.of);
               int var7 = var1.size() - var2;
               JUtil.moveStatements(var1, var2, var1.size(), var5, 0);
               JUtil.moveStatements(var5, var7, var7 + var6, var1, var1.size());
               var1.removeLast();
               return true;
            }
         }
      }
   }

   private boolean kS(IJavaBlock var1) {
      if (var1.size() < 2) {
         return false;
      } else {
         int var2 = var1.size() - 1;
         IJavaStatement var3 = var1.get(var2);
         if (JUtil.isFlowBreaker(var3)) {
            return false;
         } else {
            var2--;

            for (; var2 >= 0; var2--) {
               var3 = var1.get(var2);
               if (JUtil.isIf(var3)) {
                  IJavaBlock var4 = ((IJavaIf)var3).getBranchBody(0);
                  if (!var4.isEmpty()) {
                     var3 = var4.getLast();
                     if (JUtil.isSimpleContinue(var3)) {
                        break;
                     }
                  }
               }
            }

            if (var2 < 0) {
               return false;
            } else {
               IJavaIf var10 = (IJavaIf)var1.get(var2);
               var2++;
               IJavaBlock var5 = this.jctx.createBlock();
               JUtil.moveStatements(var1, var2, var1.size(), var5, 0);
               var10.setDefaultBlock(var5);
               var10.getBranchBody(0).removeLast();
               return true;
            }
         }
      }
   }

   private boolean wS(IJavaBlock var1) {
      if (var1.size() < 2) {
         return false;
      } else {
         IJavaStatement var2 = null;

         while (!var1.isEmpty()) {
            int var3 = var1.size() - 1;
            var2 = var1.get(var3);
            if (var2 instanceof IJavaTry && !((IJavaTry)var2).hasFinallyBlock()) {
               var1 = ((IJavaTry)var2).getTryBody();
            } else {
               if (!(var2 instanceof IJavaIf) || ((IJavaIf)var2).size() != 1) {
                  break;
               }

               var1 = ((IJavaIf)var2).getBranchBody(0);
            }
         }

         if (var2 == null || var1.size() < 2) {
            return false;
         } else if (!(var2 instanceof IJavaIf var7)) {
            return false;
         } else if (!var7.hasDefaultBlock()) {
            return false;
         } else {
            IJavaBlock var4 = var7.getBranchBody(var7.sizeWithoutDefault() - 1);
            if (var4.size() < 2) {
               return false;
            } else if (!JUtil.isIfContinue(var4.get(0))) {
               return false;
            } else {
               IJavaBlock var5 = this.jctx.createBlock();
               JUtil.moveStatements(var4, 1, var4.size(), var5, 0);
               var7.getBranchPredicate(var7.sizeWithoutDefault() - 1).reverse(this.of);
               IJavaPredicate var6 = ((IJavaIf)var4.get(0)).getBranchPredicate(0);
               var6.reverse(this.of);
               var7.addBranch(var6, var5);
               var4.remove(0);
               JUtil.moveStatements(var7.getDefaultBlock(), 0, var7.getDefaultBlock().size(), var4, 0);
               var7.setDefaultBlock(null);
               return true;
            }
         }
      }
   }

   private boolean UT(IJavaBlock var1) {
      if (var1.size() < 2) {
         return false;
      } else {
         int var2 = var1.size() - 1;
         IJavaStatement var3 = var1.get(var2);
         if (!JUtil.isSimpleBreak(var3)) {
            return false;
         } else {
            var3 = var1.get(var2 - 1);
            if (!JUtil.isIfElse(var3)) {
               return false;
            } else {
               IJavaIf var4 = (IJavaIf)var3;
               IJavaBlock var5 = var4.getDefaultBlock();
               if (!var5.isEmpty() && JUtil.isSimpleContinue(var5.getLast())) {
                  IJavaBlock var6 = var4.getBranchBody(0);
                  var6.add(var1.remove(var2));
                  var4.setDefaultBlock(null);
                  var5.removeLast();
                  JUtil.moveStatements(var5, 0, var5.size(), var1, var1.size());
                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   private boolean E(IJavaBlock var1) {
      if (!JUtil.isSimpleContinue(var1.getLast())) {
         return false;
      } else {
         var1.removeLast();
         return true;
      }
   }
}
