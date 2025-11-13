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

public class bqm extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : new bqm.CU().q(this.m);
   }

   private static class CU {
      private List q = new ArrayList();

      int q(IJavaMethod var1) {
         int var2 = 0;
         this.q(var1.getBody());

         for (bqm.eo var4 : this.q) {
            if (this.q(var1.getBody(), var4) && this.q(var4)) {
               var2++;
            }
         }

         return var2;
      }

      private void q(IJavaBlock var1) {
         for (int var2 = 1; var2 < var1.size(); var2++) {
            IJavaStatement var3 = var1.get(var2);
            if (var3 instanceof IJavaSynchronizedBlock && ((IJavaSynchronizedBlock)var3).getLock() instanceof IJavaIdentifier) {
               IJavaIdentifier var4 = (IJavaIdentifier)((IJavaSynchronizedBlock)var3).getLock();
               IJavaStatement var5 = var1.get(var2 - 1);
               if (var5 instanceof IJavaAssignment && ((IJavaAssignment)var5).getLeft() instanceof IJavaDefinition) {
                  IJavaDefinition var6 = (IJavaDefinition)((IJavaAssignment)var5).getLeft();
                  if (var4 == var6.getIdentifier()) {
                     IJavaExpression var7 = ((IJavaAssignment)var5).getRight();
                     this.q.add(new bqm.eo(var1, var5, (IJavaSynchronizedBlock)var3, var4, var7));
                  }
               }
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var9 : ((IJavaCompound)var3).getBlocks()) {
                  this.q(var9);
               }
            }
         }
      }

      private boolean q(IJavaBlock var1, bqm.eo var2) {
         int var3 = 0;

         while (var3 < var1.size()) {
            IJavaStatement var4 = var1.get(var3);
            if (var4 == var2.RF) {
               var3++;
            } else {
               if (var4 != var2.xK && !var4.visitDepthPost(new bqn(this, var2), null, null, true)) {
                  return false;
               }

               if (var4 instanceof IJavaCompound) {
                  for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
                     if (!this.q(var6, var2)) {
                        return false;
                     }
                  }
               }

               var3++;
            }
         }

         return true;
      }

      private boolean q(bqm.eo var1) {
         IJavaBlock var2 = var1.q;

         for (int var3 = 0; var3 < var2.size() - 1; var3++) {
            IJavaStatement var4 = var2.get(var3);
            if (var4 == var1.RF && var2.get(var3 + 1) == var1.xK) {
               var2.remove(var3);
               var1.xK.setLock(var1.Uv);
               return true;
            }
         }

         return false;
      }
   }

   private static class eo {
      IJavaBlock q;
      IJavaStatement RF;
      IJavaSynchronizedBlock xK;
      IJavaIdentifier Dw;
      IJavaExpression Uv;

      eo(IJavaBlock var1, IJavaStatement var2, IJavaSynchronizedBlock var3, IJavaIdentifier var4, IJavaExpression var5) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.Uv = var5;
      }
   }
}
