package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.util.base.Assert;

public class bmy extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaReturn && !((IJavaReturn)var5).returnsVoid()) {
            IJavaReturn var6 = (IJavaReturn)var5;
            if (this.pC(var1, var4, var6) || this.A(var1, var4, var6)) {
               var3++;
               continue;
            }
         }

         var4++;
      }

      return var3;
   }

   boolean pC(IJavaBlock var1, int var2, IJavaReturn var3) {
      IJavaIdentifier var4 = JUtil.getVarLike(var3.getExpression());
      if (var4 == null) {
         return false;
      } else {
         IJavaExpression var5 = null;
         int var6 = var2 - 1;

         for (int var7 = Math.max(0, var6 - 2); var6 >= var7; var6--) {
            IJavaStatement var8 = var1.get(var6);
            if (var8 instanceof IJavaLabel || var8 instanceof IJavaCompound) {
               break;
            }

            if (var8 instanceof IJavaAssignment
               && ((IJavaAssignment)var8).isSimpleAssignment()
               && JUtil.isIdentOrDefinition(((IJavaAssignment)var8).getLeft(), var4)) {
               IJavaExpression var9 = ((IJavaAssignment)var8).getRight();
               if (var6 == var2 - 1 || JUtil.isImmOrVarLike(var9)) {
                  var5 = var9;
               }
               break;
            }

            if (JUtil.getIdentifiers(var8).contains(var4)) {
               break;
            }
         }

         if (var5 == null) {
            return false;
         } else {
            var3.setExpression(var5);
            var1.remove(var6);
            return true;
         }
      }
   }

   boolean A(IJavaBlock var1, int var2, IJavaReturn var3) {
      if (var2 >= 1 && var1.get(var2 - 1) instanceof IJavaTry) {
         IJavaTry var4 = (IJavaTry)var1.get(var2 - 1);
         int var5 = 0;

         while (true) {
            if (var5 >= var4.getCatchCount()) {
               if (JUtil.canThrow(var3)) {
                  return false;
               }

               IJavaStatement var8 = var1.remove(var2);
               Assert.a(var8 == var3);
               var4.getTryBody().add(var8);
               return true;
            }

            IJavaBlock var6 = var4.getCatchBody(var5);
            if (var6.isEmpty()) {
               break;
            }

            IJavaStatement var7 = var6.getLast();
            if (!(var7 instanceof IJavaReturn) && !(var7 instanceof IJavaThrow)) {
               break;
            }

            var5++;
         }

         return false;
      } else {
         return false;
      }
   }
}
