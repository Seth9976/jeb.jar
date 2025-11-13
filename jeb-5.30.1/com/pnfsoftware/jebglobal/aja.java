package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aja extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICDoWhileStm && ((ICDoWhileStm)var4).getPredicate().isLitteralTrue()) {
            ICDoWhileStm var14 = (ICDoWhileStm)var4;
            if (var3 + 1 < var1.size() && var1.get(var3 + 1) instanceof ICLabel) {
               ICLabel var15 = (ICLabel)var1.get(var3 + 1);
               ICBlock var16 = var14.getBody();
               if (var16.size() >= 1) {
                  byte var17 = 0;
                  ICPredicate var18 = null;
                  ICStatement var19 = var16.get(-1);
                  ICLabel var11 = CUtil.getIfGotoTarget(var19);
                  if (var11 == var15) {
                     var18 = ((ICIfStm)var19).getBranchPredicate(0);
                     var17 = 1;
                  } else if (var16.size() >= 2 && var3 >= 1 && var1.get(var3 - 1) instanceof ICLabel) {
                     ICLabel var12 = (ICLabel)var1.get(var3 - 1);
                     var11 = CUtil.getGotoLabel(var19);
                     if (var11 == var15) {
                        ICStatement var13 = var16.get(-2);
                        var11 = CUtil.getIfGotoTarget(var13);
                        if (var11 == var12) {
                           var18 = ((ICIfStm)var13).getBranchPredicate(0);
                           var17 = 2;
                        }
                     }
                  }

                  if (var17 > 0) {
                     if (var17 == 1) {
                        var18.reverse(this.of);
                        var14.setPredicate(var18);
                        var16.removeLast();
                     } else {
                        if (var17 != 2) {
                           throw new RuntimeException();
                        }

                        var14.setPredicate(var18);
                        var16.removeLast();
                        var16.removeLast();
                     }

                     var2++;
                  }
               }
            }
         } else if (var4 instanceof ICWhileStm && ((ICWhileStm)var4).getPredicate().isLitteralTrue()) {
            ICWhileStm var5 = (ICWhileStm)var4;
            if (var3 + 1 < var1.size() && var1.get(var3 + 1) instanceof ICLabel) {
               ICLabel var6 = (ICLabel)var1.get(var3 + 1);
               ICBlock var7 = var5.getBody();
               if (var7.size() >= 1) {
                  ICStatement var8 = var7.get(0);
                  ICLabel var9 = CUtil.getIfGotoTarget(var8);
                  if (var9 == var6) {
                     ICPredicate var10 = ((ICIfStm)var8).getBranchPredicate(0);
                     var10.reverse(this.of);
                     var5.setPredicate(var10);
                     var7.remove(0);
                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }
}
