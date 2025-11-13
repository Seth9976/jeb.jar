package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import java.util.ArrayList;

public class ajr extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5) {
            if (var5.size() == 2 && var5.hasDefaultBlock() && var5.getBranchBody(0).size() == 1) {
               ICStatement var6 = var5.getBranchBody(0).get(0);
               if (var6 instanceof ICGoto) {
                  ICLabel var7 = ((ICGoto)var6).getLabel();
                  if (var5.getDefaultBlock().size() >= 2) {
                     ICStatement var8 = var5.getDefaultBlock().get(0);
                     ICStatement var9 = var5.getDefaultBlock().get(1);
                     if (var8 instanceof ICGoto && var9 == var7) {
                        var5.getDefaultBlock().remove(0);
                        var1.insertAll(var3 + 1, var5.getDefaultBlock());
                        var5.setDefaultBlock(null);
                        var5.getBranchPredicate(0).reverse(this.of);
                        ((ICGoto)var6).setLabel(((ICGoto)var8).getLabel());
                        var2++;
                     }
                  }
               }
            }

            if (var5.hasDefaultBlock()) {
               ArrayList var13 = new ArrayList();

               for (int var14 = 0; var14 < var5.size(); var14++) {
                  ICBlock var16;
                  if (var14 == var5.size() - 1) {
                     var16 = var5.getDefaultBlock();
                  } else {
                     var16 = var5.getBranchBody(var14);
                  }

                  if (var16.isEmpty()) {
                     var13.clear();
                     break;
                  }

                  if (var16.get(0) instanceof ICLabel) {
                     var13.add(var14);
                  }
               }

               int var15 = -1;
               if (!var13.isEmpty()) {
                  ArrayList var17 = new ArrayList();

                  for (int var18 = 0; var18 < var5.size(); var18++) {
                     if (var13.size() != 1 || var18 != (Integer)var13.get(0)) {
                        ICBlock var10;
                        if (var18 == var5.size() - 1) {
                           var10 = var5.getDefaultBlock();
                        } else {
                           var10 = var5.getBranchBody(var18);
                        }

                        ICStatement var11 = var10.getLast();
                        CUtil.BreakFlowStatus var12 = CUtil.getBreakingFlowResult(var11).getStatus();
                        if (var12 == CUtil.BreakFlowStatus.TRUE || var12 == CUtil.BreakFlowStatus.BREAK) {
                           var17.add(var18);
                        }
                     }
                  }

                  if (var17.size() + 1 == var5.size()) {
                     for (Integer var21 : var13) {
                        if (!var17.contains(var21)) {
                           var15 = var21;
                           break;
                        }
                     }
                  } else if (var17.size() == var5.size()) {
                     var15 = (Integer)var13.get(var13.size() - 1);
                  }

                  if (var15 >= var5.size() - 2) {
                     ICBlock var20;
                     if (var15 == var5.size() - 1) {
                        var20 = var5.getDefaultBlock();
                        var5.setDefaultBlock(null);
                     } else {
                        var20 = var5.getBranchBody(var15);
                        var5.getBranchPredicate(var15).reverse(this.of);
                        var5.setBranchBody(var15, var5.getDefaultBlock());
                        var5.setDefaultBlock(null);
                     }

                     var1.insertAll(var3 + 1, var20);
                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }
}
