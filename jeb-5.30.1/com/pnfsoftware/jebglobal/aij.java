package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTerminalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aij extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICWhileStm && var3 >= 1 && var1.get(var3 - 1) instanceof ICLabel) {
            ICLabel var12 = (ICLabel)var1.get(var3 - 1);
            ICBlock var14 = ((ICWhileStm)var4).getBody();
            if (var14.size() >= 2) {
               int var15 = var14.size() - 1;
               if (var14.get(var15) instanceof ICTerminalStatement) {
                  var15--;

                  while (var15 >= 0) {
                     ICLabel var18 = CUtil.getIfGotoTarget(var14.get(var15));
                     if (var18 == var12) {
                        break;
                     }

                     var15--;
                  }

                  if (var15 >= 0) {
                     ICIfStm var19 = (ICIfStm)var14.get(var15);
                     ICBlock var20 = var19.getBranchBody(0);
                     var20.remove(0);
                     var19.getBranchPredicate(0).reverse(this.of);
                     int var21 = var15 + 1;

                     while (var21 < var14.size()) {
                        ICStatement var22 = var14.remove(var21);
                        var20.add(var22);
                     }

                     var2++;
                  }
               }
            }
         } else if (var4 instanceof ICDoWhileStm) {
            ICBlock var5 = ((ICDoWhileStm)var4).getBody();
            if (var5.size() >= 3) {
               int var6 = var5.size() - 1;
               if (var5.get(var6) instanceof ICLabel && var5.get(var6 - 1) instanceof ICTerminalStatement) {
                  ICLabel var7 = (ICLabel)var5.get(var6);

                  for (var6 -= 2; var6 >= 0; var6--) {
                     ICLabel var8 = CUtil.getIfGotoTarget(var5.get(var6));
                     if (var8 == var7) {
                        break;
                     }
                  }

                  if (var6 >= 0) {
                     ICIfStm var17 = (ICIfStm)var5.get(var6);
                     ICBlock var9 = var17.getBranchBody(0);
                     var9.remove(0);
                     var17.getBranchPredicate(0).reverse(this.of);
                     int var10 = var6 + 1;

                     while (var10 < var5.size() - 1) {
                        ICStatement var11 = var5.remove(var10);
                        var9.add(var11);
                     }

                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }
}
