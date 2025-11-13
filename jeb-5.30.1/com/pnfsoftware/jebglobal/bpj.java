package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class bpj extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      return this.RF(var1, var2);
   }

   int q(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf && JUtil.isIfNoElse(var5)) {
            IJavaIf var6 = (IJavaIf)var5;
            IJavaLabel var7 = null;
            boolean var8 = true;

            for (IJavaBlock var10 : var6.getBlocks()) {
               if (var10.size() != 0 && var10.getLast() instanceof IJavaGoto) {
                  IJavaLabel var11 = ((IJavaGoto)var10.getLast()).getLabel();
                  if (var7 == null) {
                     var7 = var11;
                     continue;
                  }

                  if (var11 == var7) {
                     continue;
                  }

                  var8 = false;
                  break;
               }

               var8 = false;
               break;
            }

            if (var8 && var7 != null) {
               int var14 = -1;

               for (int var15 = var4 + 1; var15 < var1.size(); var15++) {
                  IJavaStatement var17 = var1.get(var15);
                  if (var17 instanceof IJavaLabel && var17 == var7) {
                     var14 = var15;
                     break;
                  }
               }

               if (var14 >= 0) {
                  for (IJavaBlock var12 : var6.getBlocks()) {
                     var12.removeLast();
                  }

                  IJavaBlock var19 = this.jctx.createBlock();
                  var6.setDefaultBlock(var19);
                  int var20 = var14 - (var4 + 1);
                  int var16 = var4 + 1;

                  while (var20-- > 0) {
                     IJavaStatement var13 = var1.remove(var16);
                     var19.add(var13);
                  }

                  var3++;
               }
            }
         }
      }

      return var3;
   }

   int RF(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf && JUtil.isIfNoElse(var5)) {
            ArrayList var6 = new ArrayList();

            for (int var7 = var4 + 2; var7 < var1.size(); var7++) {
               IJavaStatement var8 = var1.get(var7);
               if (var8 instanceof IJavaLabel) {
                  var6.add((IJavaLabel)var8);
               }
            }

            if (!var6.isEmpty()) {
               IJavaIf var14 = (IJavaIf)var5;
               boolean var9 = true;
               HashSet var10 = new HashSet();

               for (IJavaBlock var12 : var14.getBlocks()) {
                  if (!this.q(var12, var10)) {
                     var9 = false;
                     break;
                  }
               }

               if (var9 && !var10.isEmpty()) {
                  var6.retainAll(var10);
                  if (!var6.isEmpty()) {
                     IJavaLabel var15 = (IJavaLabel)var6.get(0);
                     int var16 = blr.q(var15, var1, var4 + 1, var1.size());
                     IJavaBlock var13 = this.jctx.createBlock();
                     var14.setDefaultBlock(var13);
                     JUtil.moveStatements(var1, var4 + 1, var16, var13, 0);
                     var3++;
                  }
               }
            }
         }
      }

      return var3;
   }

   private boolean q(IJavaBlock var1, Set var2) {
      if (var1.isEmpty()) {
         return false;
      } else {
         IJavaStatement var3 = var1.getLast();
         if (var3 instanceof IJavaGoto) {
            return !var2.add(((IJavaGoto)var3).getLabel()) || var2.size() < 3;
         } else if (JUtil.isFlowBreaker(var3)) {
            return true;
         } else if (var3 instanceof IJavaCompound) {
            if (!(var3 instanceof IJavaIf)
               && !(var3 instanceof IJavaSwitch)
               && !(var3 instanceof IJavaTry)
               && !(var3 instanceof IJavaSynchronizedBlock)
               && !(var3 instanceof IJavaBlock)) {
               return false;
            } else {
               for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                  if (!this.q(var5, var2)) {
                     return false;
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      }
   }
}
