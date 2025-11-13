package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.HashMap;
import java.util.Map;

public class bkv extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         int var1 = 0;
         HashMap var2 = new HashMap();
         var1 += this.pC(this.m.getBody(), var2);
         return var1 + this.A(this.m.getBody(), var2);
      }
   }

   private int pC(IJavaBlock var1, Map var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaCompound) {
            for (IJavaBlock var7 : ((IJavaCompound)var5).getBlocks()) {
               var3 += this.pC(var7, var2);
            }
         }

         if (var5 instanceof IJavaLabel) {
            var10 = null;

            int var11;
            for (var11 = var4 + 1; var11 < var1.size(); var11++) {
               if (!(var1.get(var11) instanceof IJavaLabel var10)) {
                  break;
               }
            }

            if (var10 != null) {
               int var12 = var11 - var4 - 1;

               for (int var9 = 0; var9 < var12; var9++) {
                  var2.put((IJavaLabel)var1.get(var4), var10);
                  var1.remove(var4);
               }

               var3 += var12;
            }
         }
      }

      return var3;
   }

   private int A(IJavaBlock var1, Map var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaCompound) {
            for (IJavaBlock var7 : ((IJavaCompound)var5).getBlocks()) {
               var3 += this.A(var7, var2);
            }
         }

         if (var5 instanceof IJavaGoto var9) {
            IJavaLabel var10 = var9.getLabel();
            if (var2.containsKey(var10)) {
               IJavaLabel var8 = (IJavaLabel)var2.get(var10);
               var9.setLabel(var8);
               var3++;
            }
         }
      }

      return var3;
   }
}
