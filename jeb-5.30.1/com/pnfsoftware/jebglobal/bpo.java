package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCatchBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.Maps;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public class bpo extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         if (var1.get(var4) instanceof IJavaTry var6) {
            IdentityHashMap var7 = new IdentityHashMap();
            IdentityHashMap var8 = new IdentityHashMap();

            for (int var9 = 0; var9 < var6.getCatchCount(); var9++) {
               IJavaBlock var10 = var6.getCatchBody(var9);
               if (var10.size() == 1 && var10.get(0) instanceof IJavaGoto) {
                  IJavaLabel var20 = ((IJavaGoto)var10.get(0)).getLabel();
                  Maps.putMulti(var8, var20, var9);
               } else if (var10.size() >= 1 && var10.get(0) instanceof IJavaLabel) {
                  IJavaLabel var11 = (IJavaLabel)var10.get(0);
                  var7.put(var11, var9);
               }
            }

            int var18 = 0;
            ArrayList var19 = new ArrayList();

            for (IJavaLabel var12 : var8.keySet()) {
               if (var7.containsKey(var12)) {
                  List var13 = (List)var8.get(var12);

                  for (int var15 : var13) {
                     List var16 = var6.getCatchBlock(var15).getCaughtTypes();
                     IJavaCatchBlock var17 = var6.getCatchBlock((Integer)var7.get(var12));
                     var17.addTypes(var16);
                     var18++;
                  }

                  var19.addAll(var13);
               }
            }

            var19.sort(null);
            Lists.reverse(var19);

            for (int var23 : var19) {
               var6.removeCatchBlock(var23);
            }

            if (var18 > 0) {
               var3++;
               continue;
            }
         }

         var4++;
      }

      return var3;
   }
}
