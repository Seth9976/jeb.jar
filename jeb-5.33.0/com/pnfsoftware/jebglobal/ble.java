package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.util.collect.Sets;
import java.util.Collection;

public class ble extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaWhile) {
            if (this.A(var1, var4)) {
               var3++;
            } else if (this.pC(var1, var4)) {
               var3++;
            }
         }
      }

      return var3;
   }

   private boolean pC(IJavaBlock var1, int var2) {
      if (var2 == 0) {
         return false;
      } else if (!(var1.get(var2 - 1) instanceof IJavaAssignment var4)) {
         return false;
      } else if (!var4.isSimpleAssignment()) {
         return false;
      } else {
         IJavaIdentifier var5;
         if (var4.getLeft() instanceof IJavaDefinition) {
            IJavaDefinition var6 = (IJavaDefinition)var4.getLeft();
            var5 = var6.getIdentifier();
         } else {
            if (!(var4.getLeft() instanceof IJavaIdentifier)) {
               return false;
            }

            var5 = (IJavaIdentifier)var4.getLeft();
         }

         IJavaExpression var13 = var4.getRight();
         IJavaWhile var7 = (IJavaWhile)var1.get(var2);
         IJavaBlock var8 = var7.getBody();
         if (var8.isEmpty()) {
            return false;
         } else {
            IJavaStatement var9 = var8.getLast();
            if (var9 instanceof IJavaTry && !((IJavaTry)var9).hasFinallyBlock()) {
               var8 = ((IJavaTry)var9).getTryBody();
               if (!var8.isEmpty()) {
                  var9 = var8.getLast();
               }
            }

            if (var9 instanceof IJavaAssignment && ((IJavaAssignment)var9).getLeft() == var5) {
               IJavaLabel var10 = null;
               if (var8.size() >= 2 && var8.get(var8.size() - 2) instanceof IJavaLabel) {
                  var10 = (IJavaLabel)var8.get(var8.size() - 2);
               }

               if (!this.pC(var7.getBody(), var10)) {
                  return false;
               } else {
                  var8.removeLast();
                  IJavaAssignment var11 = this.jctx.createAssignment(var5, var13);
                  IJavaFor var12 = this.jctx.createFor(var11, var7.getPredicate(), var9, var7.getBody());
                  var12.setPhysicalOffset(var7.getPhysicalOffset());
                  var1.set(var2, var12);
                  if (var4.getLeft() instanceof IJavaDefinition) {
                     var1.set(var2 - 1, (IJavaDefinition)var4.getLeft());
                  } else if (var4.getLeft() instanceof IJavaIdentifier) {
                     var1.remove(var2 - 1);
                  }

                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }

   private boolean A(IJavaBlock var1, int var2) {
      IJavaWhile var3 = (IJavaWhile)var1.get(var2);
      IJavaBlock var4 = var3.getBody();
      if (var4.isEmpty()) {
         return false;
      } else {
         IJavaStatement var5 = var4.getLast();
         if (var5 instanceof IJavaTry && !((IJavaTry)var5).hasFinallyBlock()) {
            var4 = ((IJavaTry)var5).getTryBody();
            if (!var4.isEmpty()) {
               var5 = var4.getLast();
            }
         }

         if (var5 instanceof IJavaAssignment && ((IJavaAssignment)var5).getLeft() instanceof IJavaIdentifier) {
            IJavaLabel var6 = null;
            if (var4.size() >= 2 && var4.get(var4.size() - 2) instanceof IJavaLabel) {
               var6 = (IJavaLabel)var4.get(var4.size() - 2);
            }

            IJavaIdentifier var7 = (IJavaIdentifier)((IJavaAssignment)var5).getLeft();
            if (!this.pC(var3.getBody(), var6)) {
               return false;
            } else {
               IJavaStatement var8 = this.pC(var7, Sets.newHashSet(var3));
               if (var8 == null) {
                  return false;
               } else if (!this.A(var7, Sets.newHashSet(var3, var8))) {
                  return false;
               } else {
                  var4.removeLast();
                  IJavaFor var10 = this.jctx.createFor(var8, var3.getPredicate(), var5, var3.getBody());
                  var10.setPhysicalOffset(var3.getPhysicalOffset());
                  var1.set(var2, var10);
                  this.m.deleteStatement(var8);
                  return true;
               }
            }
         } else {
            return false;
         }
      }
   }

   private boolean pC(IJavaBlock var1, IJavaLabel var2) {
      return var1.visitDepthPost(new blf(this, var2));
   }

   private IJavaStatement pC(IJavaIdentifier var1, Collection var2) {
      IJavaStatement[] var3 = new IJavaStatement[1];
      this.m.getBody().visitDepthPre(new blg(this, var2, var1, var3));
      return var3[0];
   }

   private boolean A(IJavaIdentifier var1, Collection var2) {
      return this.m.getBody().visitDepthPre(new blh(this, var2, var1));
   }
}
