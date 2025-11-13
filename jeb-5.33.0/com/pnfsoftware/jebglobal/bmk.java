package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bmk extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         if (var1.get(var4) instanceof IJavaTry var6 && !var6.isTryWithResource() && !var6.hasFinallyBlock() && var6.getCatchCount() == 1) {
            IJavaBlock var7 = var6.getCatchBlock(0).getBlock();
            if (var7.size() == 1 && var7.get(0) instanceof IJavaGoto) {
               IJavaLabel var8 = ((IJavaGoto)var7.get(0)).getLabel();
               IJavaBlock var9 = var6.getTryBody();
               if (var9.size() == 1 && var9.get(0) instanceof IJavaWhile) {
                  IJavaWhile var10 = (IJavaWhile)var9.get(0);
                  if (var10.getPredicate().isLitteralTrue()) {
                     IJavaBlock var11 = var10.getBody();
                     if (!var11.isEmpty() && var11.get(0) == var8) {
                        var1.remove(var4);
                        var9.remove(0);
                        JUtil.moveStatements(var11, 0, var11.size(), var9, 0);
                        var11.add(var6);
                        var1.insert(var4, var10);
                        var3++;
                     }
                  }
               }
            }
         }
      }

      return var3;
   }
}
