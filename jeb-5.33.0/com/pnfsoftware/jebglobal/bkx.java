package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaPredicate;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bkx extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (JUtil.isIf(var5)) {
            IJavaIf var6 = (IJavaIf)var5;
            IJavaPredicate var7 = var6.getBranchPredicate(0);
            if (var7.isLitteralTrue()) {
               var1.remove(var4);
               IJavaBlock var8 = var6.getBranchBody(0);
               JUtil.moveStatements(var8, 0, var8.size(), var1, var4);
               var3++;
            } else if (var7.isLitteralFalse()) {
               IJavaBlock var9 = var6.getBranchBody(0);
               if (this.pC(var9)) {
                  var1.remove(var4);
                  var3++;
               }
            }
         }
      }

      return var3;
   }

   boolean pC(IJavaBlock var1) {
      return new bhu.Av(this.m, var1).pC();
   }
}
