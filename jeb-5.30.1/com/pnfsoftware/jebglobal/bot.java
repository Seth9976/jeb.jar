package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bot extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size() - 1; var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (JUtil.isNonCompoundFlowBreaker(var5)) {
            int var6 = var4 + 1;
            int var7;
            if (!q(var1, var6, var1.size())) {
               var7 = var1.size();
            } else {
               int var8;
               for (var8 = var6; var8 < var1.size(); var8++) {
                  IJavaStatement var9 = var1.get(var8);
                  if (var9 instanceof IJavaLabel || var9 instanceof IJavaCompound) {
                     break;
                  }
               }

               var7 = var8;
            }

            if (var7 > var6) {
               int var10 = var7 - var6;

               while (var10-- > 0) {
                  var1.remove(var6);
               }

               var3++;
            }
         }
      }

      return var3;
   }

   static boolean q(IJavaBlock var0, int var1, int var2) {
      for (int var3 = var1; var3 < var2; var3++) {
         IJavaStatement var4 = var0.get(var3);
         if (JUtil.isOrContainsLabel(var4)) {
            return true;
         }
      }

      return false;
   }
}
