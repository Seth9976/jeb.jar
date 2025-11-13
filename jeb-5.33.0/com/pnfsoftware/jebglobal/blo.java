package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaThrow;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class blo extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf) {
            IJavaLabel var6 = JUtil.checkIfGoto(var5);
            if (var6 != null) {
               int var7 = -1;

               for (int var8 = var4 + 1; var8 < var1.size(); var8++) {
                  IJavaStatement var9 = var1.get(var8);
                  if (var9 instanceof IJavaGoto || var9 instanceof IJavaReturn || var9 instanceof IJavaThrow) {
                     var7 = var8;
                     break;
                  }

                  if (var9 instanceof IJavaLabel && (IJavaLabel)var9 == var6) {
                     var7 = var8 - 1;
                     break;
                  }
               }

               if (var7 >= 0) {
                  IJavaStatement var14 = JUtil.getFirstRealStatement(var1, var7 + 1);
                  if (var14 instanceof IJavaLabel && (IJavaLabel)var14 == var6) {
                     ((IJavaIf)var5).getBranchPredicate(0).reverse(this.of);
                     IJavaBlock var10 = ((IJavaIf)var5).getBranchBody(0);
                     var10.remove(0);
                     int var11 = var7 + 1 - (var4 + 1);
                     int var13 = var4 + 1;

                     while (var11-- > 0) {
                        IJavaStatement var12 = var1.remove(var13);
                        var10.add(var12);
                     }

                     var3++;
                  }
               }
            }
         }
      }

      return var3;
   }
}
