package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDoWhile;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaWhile;

public class bly extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         if (var1.get(var4) instanceof IJavaDoWhile var6 && var6.getPredicate().isLitteralTrue()) {
            IJavaWhile var7 = this.jctx.createWhile(var6.getPredicate(), var6.getBody());
            var1.set(var4, var7);
            var3++;
         }
      }

      return var3;
   }
}
