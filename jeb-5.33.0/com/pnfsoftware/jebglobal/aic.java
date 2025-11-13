package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCOptimizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class aic extends AbstractCOptimizer {
   @Override
   protected int perform() {
      int var1 = 0;
      HashMap var2 = new HashMap();
      ArrayList var3 = new ArrayList();
      List var4 = this.m.toFlatList();

      for (int var5 = 0; var5 < var4.size(); var5++) {
         ICStatement var6 = (ICStatement)var4.get(var5);
         aic.Av var7 = new aic.Av();
         var2.put(var6, var7);
         ICIdentifier var8 = adj.pC(var6);
         if (var8 != null) {
            var7.pC = var8;
            var3.add(var8);
         }

         var7.A = adj.A(var6);
      }

      for (ICIdentifier var19 : var3) {
         int var20 = -1;
         boolean var9 = false;
         ArrayDeque var10 = new ArrayDeque();
         int var11 = -1;
         int var12 = -1;
         byte var13 = -1;

         for (int var17 = 0; var17 < var4.size(); var17++) {
            ICStatement var14 = (ICStatement)var4.get(var17);
            if (var20 < 0) {
               if (var14 instanceof afs) {
                  var10.push((afs)var14);
               } else if (var14 instanceof aft) {
                  var10.pop();
               }
            } else if (var14 instanceof afs) {
               var11++;
            } else if (var14 instanceof aft) {
               if (--var11 < var12 && var9) {
                  var9 = false;
               }
            }

            aic.Av var15 = (aic.Av)var2.get(var14);
            if (var15 != null) {
               if (var15.pC == var19) {
                  var20 = var17;
                  var9 = true;
                  var11 = var10.size();
                  var12 = var11;
               }

               if (var15.A != null && !var9 && var15.A.contains(var19)) {
                  var13 = 0;
               }
            }
         }

         if (var13 >= 0 && var20 >= 0) {
            ICStatement var21 = (ICStatement)var4.get(var20);
            ICDecl var22 = null;
            ICAssignment var16 = null;
            if (var21 instanceof ICAssignment) {
               var22 = CUtil.getDefinition(var21);
               var16 = (ICAssignment)var21;
            } else if (var21 instanceof ICDecl) {
               var22 = CUtil.getDefinition(var21);
            } else if (var21 instanceof afw) {
               var22 = CUtil.getDefinition(((afw)var21).A());
               var16 = (ICAssignment)((afw)var21).A();
            }

            if (var22 != null) {
               if (var16 != null) {
                  var16.setLeft(var22.getIdentifier());
               } else {
                  var4.remove(var20);
               }

               var4.add(1, var22);
            }

            var1++;
         }
      }

      if (var1 > 0) {
         this.m.fromFlatList(var4);
      }

      return var1;
   }

   private static class Av {
      ICIdentifier pC;
      Set A;
   }
}
