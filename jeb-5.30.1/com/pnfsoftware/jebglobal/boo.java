package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class boo extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaCompound && (var5 instanceof IJavaIf || var5 instanceof IJavaTry && !((IJavaTry)var5).hasFinallyBlock())) {
            IJavaStatement var6 = JUtil.getFirstRealStatement(var1, var4 + 1);
            if (var6 instanceof IJavaLabel) {
               for (IJavaBlock var8 : ((IJavaCompound)var5).getBlocks()) {
                  var3 += this.q(var8, (IJavaLabel)var6);
               }
            }
         }
      }

      return var3;
   }

   private int q(IJavaBlock var1, IJavaLabel var2) {
      if (var1.size() == 0) {
         return 0;
      } else {
         int var3 = 0;
         IJavaStatement var4 = var1.getLast();
         if (var4 instanceof IJavaGoto) {
            if (((IJavaGoto)var4).getLabel() != var2) {
               return var3;
            }

            var1.removeLast();
            var3++;
            if (var1.size() == 0) {
               return var3;
            }

            var4 = var1.getLast();
         }

         if (var4 instanceof IJavaIf || var4 instanceof IJavaTry && !((IJavaTry)var4).hasFinallyBlock()) {
            for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
               var3 += this.q(var6, var2);
            }
         }

         return var3;
      }
   }
}
