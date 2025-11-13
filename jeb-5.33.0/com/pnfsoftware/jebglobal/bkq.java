package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCatchBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bkq extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : this.pC(this.m.getBody());
   }

   int pC(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var4 instanceof IJavaTry && ((IJavaTry)var4).getCatchCount() == 1) {
            IJavaTry var5 = (IJavaTry)var4;
            IJavaCatchBlock var6 = JUtil.isTryCatchall(var5);
            if (var6 != null && var6.getBlock().size() == 1 && JUtil.isThrow(var6.getBlock().get(0), var6.getIdentifier())) {
               if (var5.isTryWithResource()) {
                  var5.removeCatchBlock(0);
               } else {
                  IJavaBlock var7 = var5.getTryBody();
                  var1.remove(var3);
                  var1.insertAll(var3, var7);
               }

               var2++;
            }
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var9 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.pC(var9);
            }
         }
      }

      return var2;
   }
}
