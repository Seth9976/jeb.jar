package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCatchBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bqz extends AbstractJBlockOptimizer {
   public bqz() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaCatchBlock var5 = JUtil.isTryCatchall(var1, var4);
         if (var5 != null) {
            IJavaTry var6 = (IJavaTry)var1.get(var4);
            IJavaBlock var7 = var6.getTryBody();
            if (var7.size() == 2 && var7.get(0) instanceof IJavaLabel && JUtil.isMonitorExit(var7.get(1))) {
               IJavaLabel var8 = (IJavaLabel)var7.get(0);
               if (var5.getBlock().size() == 1 && JUtil.getGotoLabel(var5.getBlock().get(0)) == var8) {
                  var1.remove(var4);
                  var1.insertAll(var4, var7);
                  var3++;
               }
            }
         }
      }

      return var3;
   }
}
