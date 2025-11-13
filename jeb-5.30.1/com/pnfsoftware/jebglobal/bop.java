package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bop extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaIf && ((IJavaIf)var5).hasDefaultBlock() || var5 instanceof IJavaTry && !((IJavaTry)var5).hasFinallyBlock()) {
            IJavaStatement var6 = null;

            for (IJavaBlock var8 : ((IJavaCompound)var5).getBlocks()) {
               if (var8.size() == 0) {
                  var6 = null;
                  break;
               }

               IJavaStatement var9 = var8.getLast();
               if (var6 == null) {
                  if (var9 instanceof IJavaCompound) {
                     var6 = null;
                     break;
                  }

                  if (var5 instanceof IJavaTry && JUtil.canThrow(var9)) {
                     var6 = null;
                     break;
                  }

                  var6 = var9;
               } else if (!var6.equals(var9)) {
                  var6 = null;
                  break;
               }
            }

            if (var6 != null) {
               for (IJavaBlock var11 : ((IJavaCompound)var5).getBlocks()) {
                  var11.removeLast();
               }

               var1.insert(var4 + 1, var6);
               var3++;
            }
         }
      }

      return var3;
   }
}
