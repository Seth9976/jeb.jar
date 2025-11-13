package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMonitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import java.util.ArrayList;
import java.util.List;

public class bpv extends AbstractJBlockOptimizer {
   public bpv() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size() - 1; var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaMonitor && ((IJavaMonitor)var5).isEnter()) {
            IJavaExpression var6 = ((IJavaMonitor)var5).getLock();
            if (var6 != null && new bpv.eo(var6, var1, var4).q()) {
               var3++;
            }
         }
      }

      return var3;
   }

   private class eo {
      IJavaExpression q;
      IJavaBlock RF;
      int xK;
      List Dw = new ArrayList();

      eo(IJavaExpression var2, IJavaBlock var3, int var4) {
         this.q = var2;
         this.RF = var3;
         this.xK = var4;
      }

      boolean q() {
         if (!this.q(this.RF, this.xK)) {
            return false;
         } else if (this.Dw.isEmpty()) {
            return false;
         } else {
            IJavaBlock var1 = bpv.this.jctx.createBlock();
            IJavaSynchronizedBlock var2 = bpv.this.jctx.createSynchronizedBlock(this.q, var1);
            var2.setPhysicalOffset(this.RF.get(this.xK).getPhysicalOffset());
            brf var3 = (brf)this.Dw.get(this.Dw.size() - 1);
            if (var3.q != this.RF) {
               return false;
            } else {
               JUtil.moveStatements(this.RF, this.xK + 1, var3.RF + 1, var1, 0);
               this.RF.remove(this.xK);
               this.RF.insert(this.xK, var2);

               for (brf var5 : this.Dw) {
                  if (var5.q == this.RF) {
                     var5.q = var1;
                  }

                  var5.q.remove(var5.xK);
               }

               return true;
            }
         }
      }

      private boolean q(IJavaBlock var1, int var2) {
         while (var2 < var1.size()) {
            IJavaStatement var3 = var1.get(var2);
            if (var3 instanceof IJavaAssignment && ((IJavaAssignment)var3).getLeft() == this.q) {
               if (var1 == this.RF) {
                  return true;
               }

               return false;
            }

            if (var3 instanceof IJavaTry) {
               if (var1 == this.RF) {
                  return true;
               }

               return false;
            }

            if (var3 instanceof IJavaMonitor && ((IJavaMonitor)var3).isExit() && this.q.equals(((IJavaMonitor)var3).getLock())) {
               this.Dw.add(new brf(var1, var2, var3));
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                  if (!this.q(var5, 0)) {
                     return false;
                  }
               }
            }

            var2++;
         }

         return true;
      }
   }
}
