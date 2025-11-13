package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;

public class bmx extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         if (var1.get(var4) instanceof IJavaTry var6) {
            IJavaBlock var7 = var6.getTryBody();
            int var8 = var7.size();
            if (var8 >= 1 && var6.getCatchCount() == 1 && var6.getFinallyBlock() == null && var7.get(var8 - 1) instanceof IJavaGoto) {
               IJavaLabel var9 = ((IJavaGoto)var7.get(var8 - 1)).getLabel();
               boolean var10 = false;
               int var11 = Math.min(var4 + 10, var1.size());
               int var12 = var4 + 1;

               for (int var13 = 0; var12 < var11; var12++) {
                  IJavaStatement var14 = var1.get(var12);
                  if (var14 instanceof IJavaCompound) {
                     break;
                  }

                  if (var14 instanceof IJavaLabel) {
                     var10 = var14 == var9 && var13 > 0;
                     break;
                  }

                  var13++;
               }

               if (var10) {
                  var6.getCatchBody(0).addMultiple(var1, var4 + 1, var12, true);
                  var3++;
                  continue;
               }
            }
         }

         var4++;
      }

      return var3;
   }
}
