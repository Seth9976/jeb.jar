package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMonitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSynchronizedBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;

public class blv extends AbstractJBlockOptimizer {
   public blv() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size() - 1; var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaMonitor && ((IJavaMonitor)var5).isEnter()) {
            IJavaExpression var6 = ((IJavaMonitor)var5).getLock();
            if (var6 != null) {
               IJavaStatement var7 = var1.get(var4 + 1);
               if (var7 instanceof IJavaTry && ((IJavaTry)var7).hasFinallyBlock()) {
                  IJavaTry var8 = (IJavaTry)var7;
                  IJavaBlock var9 = var8.getFinallyBlock();
                  if (var9.size() == 1) {
                     IJavaStatement var10 = var9.get(0);
                     if (var10 instanceof IJavaMonitor && ((IJavaMonitor)var10).isExit() && var6.equals(((IJavaMonitor)var10).getLock())) {
                        if (var8.getCatchCount() == 0) {
                           IJavaBlock var11 = var8.getTryBody();
                           var1.remove(var4);
                           var1.remove(var4);
                           IJavaSynchronizedBlock var12 = this.jctx.createSynchronizedBlock(var6, var11);
                           var12.setPhysicalOffset(var5.getPhysicalOffset());
                           var1.insert(var4, var12);
                           var3++;
                        } else {
                           var8.setFinallyBlock(null);
                           IJavaBlock var13 = this.jctx.createBlock(var8);
                           var1.remove(var4);
                           var1.remove(var4);
                           IJavaSynchronizedBlock var14 = this.jctx.createSynchronizedBlock(var6, var13);
                           var14.setPhysicalOffset(var5.getPhysicalOffset());
                           var1.insert(var4, var14);
                           var3++;
                        }
                     }
                  }
               }
            }
         }
      }

      return var3;
   }
}
