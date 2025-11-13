package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaForEach;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperation;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;

public class bpa extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaWhile && var4 >= 1 && var1.get(var4 - 1) instanceof IJavaAssignment) {
            IJavaAssignment var6 = (IJavaAssignment)var1.get(var4 - 1);
            if (var6.getLeft() instanceof IJavaDefinition) {
               IJavaDefinition var7 = (IJavaDefinition)var6.getLeft();
               if (var6.getRight() instanceof IJavaCall) {
                  IJavaCall var9 = (IJavaCall)var6.getRight();
                  String var10 = var9.getMethodName();
                  if (var10.equals("iterator")) {
                     IJavaExpression var8 = var9.getArgument(0);
                     if (this.q(var1, var4, (IJavaWhile)var5, var7, var8)) {
                        var3++;
                     }
                  }
               }
            }
         }
      }

      return var3;
   }

   private boolean q(IJavaBlock var1, int var2, IJavaWhile var3, IJavaDefinition var4, IJavaExpression var5) {
      IJavaPredicate var6 = var3.getPredicate();
      IJavaExpression var7 = var6.getExpression();
      if (var7 instanceof IJavaOperation) {
         return false;
      } else {
         IJavaExpression var8 = null;
         if (var7 instanceof IJavaCall var9) {
            String var10 = var9.getMethodName();
            if (var10.equals("hasNext")) {
               var8 = var9.getArgument(0);
            }
         }

         if (var8 != var4.getIdentifier()) {
            return false;
         } else {
            IJavaBlock var14 = var3.getBody();
            if (!var14.isEmpty() && var14.get(0) instanceof IJavaAssignment) {
               IJavaAssignment var15 = (IJavaAssignment)var14.get(0);
               IJavaDefinition var11 = null;
               if (var15.getLeft() instanceof IJavaDefinition && var15.getRight() instanceof IJavaCall) {
                  IJavaCall var12 = (IJavaCall)var15.getRight();
                  String var13 = var12.getMethodName();
                  if (var13.equals("next") && var12.getArgument(0) == var8) {
                     var11 = (IJavaDefinition)var15.getLeft();
                  }
               }

               if (var11 == null) {
                  return false;
               } else {
                  for (int var16 = 1; var16 < var14.size(); var16++) {
                     if (!var14.get(var16).visitDepthPost(new bpb(this, var4))) {
                        return false;
                     }
                  }

                  var14.remove(0);
                  IJavaForEach var17 = this.jctx.createForEach(var11, var5, var14);
                  var1.remove(var2);
                  var1.insert(var2, var17);
                  var1.remove(var2 - 1);
                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }
}
