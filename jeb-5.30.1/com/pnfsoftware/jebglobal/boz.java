package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCatchBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.ArrayList;
import java.util.List;

public class boz extends AbstractJBlockOptimizer {
   public boz() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         if (var1.get(var4) instanceof IJavaTry var6 && var6.getCatchCount() >= 1 && var6.getFinallyBlock() == null) {
            int var7 = var6.getCatchByType("Ljava/lang/Throwable;");
            if (var7 == var6.getCatchCount() - 1) {
               IJavaIdentifier var8 = var6.getCatchIdentifier(var7);
               if (var8 != null && var6.getCatchBody(var7).size() >= 1 && this.q(var1, var4, var6, var7)) {
                  var3++;
                  continue;
               }
            }
         }

         var4++;
      }

      return var3;
   }

   private boolean q(IJavaBlock var1, int var2, IJavaTry var3, int var4) {
      IJavaBlock var5 = var3.getCatchBody(var4);
      if (var5.isEmpty()) {
         return false;
      } else {
         List var6 = this.m.toFlatList();
         IJavaExpression var7 = JUtil.isThrowLike(var5.getLast(), var6);
         if (var7 != null && var7 == var3.getCatchIdentifier(var4)) {
            IJavaLabel var8 = null;
            IJavaStatement var9 = var5.get(0);
            if (var9 instanceof IJavaLabel) {
               var8 = (IJavaLabel)var9;
            }

            if (var8 == null) {
               return false;
            } else {
               ArrayList var10 = new ArrayList();

               for (int var11 = var8 == null ? 0 : 1; var11 < var5.size() - 1; var11++) {
                  var10.add(var5.get(var11));
               }

               int var20 = var3.getCatchCount() - 1;
               ArrayList var12 = new ArrayList(var20);

               for (int var13 = 0; var13 < var20; var13++) {
                  IJavaBlock var14 = var3.getCatchBody(var13);
                  if (var14.isEmpty()) {
                     return false;
                  }

                  int var15;
                  for (var15 = 0; var15 < var14.size(); var15++) {
                     IJavaStatement var16 = var14.get(var15);
                     if (!(var16 instanceof IJavaAssignment)) {
                        break;
                     }

                     IJavaLeftExpression var17 = ((IJavaAssignment)var16).getLeft();
                     IJavaExpression var18 = ((IJavaAssignment)var16).getRight();
                     if (!(var17 instanceof IJavaDefinition) && !(var17 instanceof IJavaIdentifier) || !(var18 instanceof IJavaIdentifier)) {
                        break;
                     }
                  }

                  if (var15 >= var14.size()) {
                     return false;
                  }

                  IJavaCatchBlock var24 = JUtil.isTryCatchall(var14, var15);
                  if (var24 == null) {
                     return false;
                  }

                  IJavaBlock var26 = var24.getBlock();
                  if (var26.size() != 1 || !JUtil.isGotoTo(var26.get(0), var8)) {
                     return false;
                  }

                  IJavaStatement var28 = var14.getLast();
                  if (!(var28 instanceof IJavaReturn) && !(var28 instanceof IJavaThrow) && !(var28 instanceof IJavaGoto)) {
                     return false;
                  }

                  if (!this.q(var14, var15 + 1, var14.size() - 1, var10)) {
                     return false;
                  }

                  var12.add(var15);
               }

               if (var2 + 1 + var10.size() > var1.size()) {
                  return false;
               } else if (!this.q(var1, var2 + 1, var2 + 1 + var10.size(), var10)) {
                  return false;
               } else {
                  List var21 = JUtil.getGotos(var8, var6);
                  if (var21.size() != var20) {
                     return false;
                  } else {
                     var1.removeMultiple(var2 + 1, var10.size());
                     IJavaBlock var22 = var3.removeCatchBlock(var3.getCatchCount() - 1).getBlock();
                     var22.remove(var22.size() - 1);
                     var22.remove(0);
                     var3.setFinallyBlock(var22);

                     for (int var23 = 0; var23 < var3.getCatchCount(); var23++) {
                        IJavaBlock var25 = var3.getCatchBody(var23);
                        int var27 = (Integer)var12.get(var23);
                        IJavaBlock var29 = ((IJavaTry)var25.get(var27)).getTryBody();
                        IJavaStatement var19 = var25.getLast();
                        var25.removeRange(var27, var25.size());
                        var25.insertAll(var27, var29);
                        var25.add(var19);
                     }

                     return true;
                  }
               }
            }
         } else {
            return false;
         }
      }
   }

   private boolean q(IJavaBlock var1, int var2, int var3, List var4) {
      if (var4.size() != var3 - var2) {
         return false;
      } else {
         int var5 = var2;

         for (int var6 = 0; var6 < var4.size(); var6++) {
            if (var1.get(var5).getElementType() != ((IJavaStatement)var4.get(var6)).getElementType()) {
               return false;
            }

            var5++;
         }

         return true;
      }
   }
}
