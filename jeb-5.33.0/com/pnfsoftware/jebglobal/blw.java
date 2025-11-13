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

public class blw extends AbstractJBlockOptimizer {
   public blw() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size() - 1; var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaMonitor && ((IJavaMonitor)var5).isEnter()) {
            IJavaExpression var6 = ((IJavaMonitor)var5).getLock();
            if (var6 != null && new blw.Av(var6, var1, var4).pC()) {
               var3++;
            }
         }
      }

      return var3;
   }

   private class Av {
      IJavaExpression pC;
      IJavaBlock A;
      int kS;
      List wS = new ArrayList();

      Av(IJavaExpression var2, IJavaBlock var3, int var4) {
         this.pC = var2;
         this.A = var3;
         this.kS = var4;
      }

      boolean pC() {
         if (!this.pC(this.A, this.kS)) {
            return false;
         } else if (this.wS.isEmpty()) {
            return false;
         } else {
            IJavaBlock var1 = blw.this.jctx.createBlock();
            IJavaSynchronizedBlock var2 = blw.this.jctx.createSynchronizedBlock(this.pC, var1);
            var2.setPhysicalOffset(this.A.get(this.kS).getPhysicalOffset());
            bne var3 = (bne)this.wS.get(this.wS.size() - 1);
            if (var3.pC != this.A) {
               return false;
            } else {
               JUtil.moveStatements(this.A, this.kS + 1, var3.A + 1, var1, 0);
               this.A.remove(this.kS);
               this.A.insert(this.kS, var2);

               for (bne var5 : this.wS) {
                  if (var5.pC == this.A) {
                     var5.pC = var1;
                  }

                  var5.pC.remove(var5.kS);
               }

               return true;
            }
         }
      }

      private boolean pC(IJavaBlock var1, int var2) {
         while (var2 < var1.size()) {
            IJavaStatement var3 = var1.get(var2);
            if (var3 instanceof IJavaAssignment && ((IJavaAssignment)var3).getLeft() == this.pC) {
               if (var1 == this.A) {
                  return true;
               }

               return false;
            }

            if (var3 instanceof IJavaTry) {
               if (var1 == this.A) {
                  return true;
               }

               return false;
            }

            if (var3 instanceof IJavaMonitor && ((IJavaMonitor)var3).isExit() && this.pC.equals(((IJavaMonitor)var3).getLock())) {
               this.wS.add(new bne(var1, var2, var3));
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                  if (!this.pC(var5, 0)) {
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
