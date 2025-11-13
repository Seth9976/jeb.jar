package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLeftExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNew;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.ArrayList;

public class brc extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         if (var1.get(var4) instanceof IJavaAssignment var6 && var6.isSimpleAssignment() && var6.getRight() instanceof IJavaNew) {
            IJavaNew var7 = (IJavaNew)var6.getRight();
            if (var7.getType().toString().equals("java.lang.StringBuilder") && this.q(var1, var4)) {
               var3++;
               var4++;
            }
         }
      }

      return var3;
   }

   private boolean q(IJavaBlock var1, int var2) {
      IJavaAssignment var3 = (IJavaAssignment)var1.get(var2);
      IJavaLeftExpression var5 = var3.getLeft();
      if (var5 instanceof IJavaDefinition) {
         var4 = ((IJavaDefinition)var5).getIdentifier();
      } else if (!(var5 instanceof IJavaIdentifier var4)) {
         return false;
      }

      boolean var6 = false;
      ArrayList var7 = new ArrayList();
      IJavaNew var8 = (IJavaNew)var3.getRight();
      if (!var8.getArguments().isEmpty()) {
         if (var8.getArguments().size() != 1) {
            return false;
         }

         IJavaExpression var9 = null;
         IJavaExpression var10 = (IJavaExpression)var8.getArguments().get(0);
         if (var10 instanceof IJavaIdentifier) {
            IJavaDefinition var11 = this.m.getIdentifierManager().getDefinition((IJavaIdentifier)var10);
            if (var11 != null) {
               String var12 = var11.getType().toString();
               if (var12.equals("java.lang.String")) {
                  var9 = var10;
               }
            }
         } else if (var10 instanceof IJavaConstant) {
            if (((IJavaConstant)var10).getType() == this.tf.getInt()) {
               var6 = true;
            } else if (((IJavaConstant)var10).getString() != null) {
               var9 = var10;
            }
         }

         if (!var6) {
            if (var9 == null) {
               return false;
            }

            var7.add(var9);
         }
      }

      var2++;
      int var18 = 0;

      IJavaStatement var19;
      for (var19 = null; var2 < var1.size(); var18++) {
         var19 = var1.get(var2);
         IJavaIdentifier var20 = null;
         IJavaCall var22 = null;
         if (var19 instanceof IJavaAssignment var13) {
            if (!var13.isSimpleAssignment() || var13.getLeft() != var4 || !(var13.getRight() instanceof IJavaCall)) {
               break;
            }

            var20 = (IJavaIdentifier)var13.getLeft();
            var22 = (IJavaCall)var13.getRight();
         } else if (var19 instanceof IJavaCall) {
            var22 = (IJavaCall)var19;
         }

         if (var22 == null
            || !var22.getMethodName().equals("append")
            || var22.getArgumentCount() != 2
            || var22.getArguments().get(0) != var4
            || var20 != null && var20 != var4) {
            break;
         }

         IJavaExpression var25 = (IJavaExpression)var22.getArguments().get(1);
         var7.add(var25);
         var2++;
      }

      if (var7.isEmpty()) {
         return false;
      } else {
         Object var21 = (IJavaExpression)var7.get(0);
         if (var7.size() >= 2) {
            for (int var23 = 1; var23 < var7.size(); var23++) {
               var21 = this.jctx
                  .createOperation((IJavaExpression)var21, this.of.getStandardOperator(JavaOperatorType.CONCAT), (IJavaExpression)var7.get(var23));
            }
         }

         if (var19 != null && !(var19 instanceof IJavaCompound)) {
            IJavaCall var24 = null;

            for (IJavaElement var14 : var19.getSubElements()) {
               if (var14 instanceof IJavaCall) {
                  var24 = (IJavaCall)var14;
                  break;
               }
            }

            if (var24 != null && var24.getMethodName().equals("toString") && var24.getArguments().size() == 1 && var24.getArguments().get(0) == var4) {
               try {
                  if (!var19.replaceSubElement(var24, (IJavaElement)var21)) {
                     return false;
                  }
               } catch (ClassCastException var15) {
                  return false;
               }

               var2 -= var18;

               while (var18-- > 0) {
                  var1.remove(var2);
               }

               var1.remove(var2 - 1);
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }
}
