package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import java.util.ArrayList;
import java.util.List;

public class bmn extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : new bmn.Sv().pC(this.m);
   }

   private static class Av {
      IJavaBlock pC;
      IJavaStatement A;
      IJavaSynchronizedBlock kS;
      IJavaIdentifier wS;
      IJavaExpression UT;

      Av(IJavaBlock var1, IJavaStatement var2, IJavaSynchronizedBlock var3, IJavaIdentifier var4, IJavaExpression var5) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
      }
   }

   private static class Sv {
      private List pC = new ArrayList();

      int pC(IJavaMethod var1) {
         int var2 = 0;
         this.pC(var1.getBody());

         for (bmn.Av var4 : this.pC) {
            if (this.pC(var1.getBody(), var4) && this.pC(var4)) {
               var2++;
            }
         }

         return var2;
      }

      private void pC(IJavaBlock var1) {
         for (int var2 = 1; var2 < var1.size(); var2++) {
            IJavaStatement var3 = var1.get(var2);
            if (var3 instanceof IJavaSynchronizedBlock && ((IJavaSynchronizedBlock)var3).getLock() instanceof IJavaIdentifier) {
               IJavaIdentifier var4 = (IJavaIdentifier)((IJavaSynchronizedBlock)var3).getLock();
               IJavaStatement var5 = var1.get(var2 - 1);
               if (var5 instanceof IJavaAssignment && ((IJavaAssignment)var5).getLeft() instanceof IJavaDefinition) {
                  IJavaDefinition var6 = (IJavaDefinition)((IJavaAssignment)var5).getLeft();
                  if (var4 == var6.getIdentifier()) {
                     IJavaExpression var7 = ((IJavaAssignment)var5).getRight();
                     this.pC.add(new bmn.Av(var1, var5, (IJavaSynchronizedBlock)var3, var4, var7));
                  }
               }
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var9 : ((IJavaCompound)var3).getBlocks()) {
                  this.pC(var9);
               }
            }
         }
      }

      private boolean pC(IJavaBlock var1, bmn.Av var2) {
         int var3 = 0;

         while (var3 < var1.size()) {
            IJavaStatement var4 = var1.get(var3);
            if (var4 == var2.A) {
               var3++;
            } else {
               if (var4 != var2.kS && !var4.visitDepthPost(new bmo(this, var2), null, null, true)) {
                  return false;
               }

               if (var4 instanceof IJavaCompound) {
                  for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
                     if (!this.pC(var6, var2)) {
                        return false;
                     }
                  }
               }

               var3++;
            }
         }

         return true;
      }

      private boolean pC(bmn.Av var1) {
         IJavaBlock var2 = var1.pC;

         for (int var3 = 0; var3 < var2.size() - 1; var3++) {
            IJavaStatement var4 = var2.get(var3);
            if (var4 == var1.A && var2.get(var3 + 1) == var1.kS) {
               var2.remove(var3);
               var1.kS.setLock(var1.UT);
               return true;
            }
         }

         return false;
      }
   }
}
