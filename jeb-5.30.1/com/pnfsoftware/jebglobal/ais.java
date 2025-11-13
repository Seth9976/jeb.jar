package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTerminalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import java.util.HashSet;
import java.util.Set;

public class ais extends AbstractCBlockOptimizer {
   private static boolean q = false;

   private ais.eo q(ICIfStm var1, boolean var2) {
      ais.eo var3 = new ais.eo();
      if (!var2 && !var1.hasDefaultBlock()) {
         var3.q = false;
         return var3;
      } else {
         boolean var4 = true;
         HashSet var5 = new HashSet();

         for (ICBlock var7 : var1.getBlocks()) {
            if (var7.isEmpty()) {
               var4 = false;
               break;
            }

            ICStatement var8 = var7.getLast();
            if (!(var8 instanceof ICGoto) && !(var8 instanceof ICIfStm) && (!q || !(var8 instanceof ICTerminalStatement))) {
               var4 = false;
               break;
            }

            if (var8 instanceof ICGoto) {
               var5.add(((ICGoto)var8).getLabel());
            } else if (var8 instanceof ICIfStm) {
               ais.eo var9 = this.q((ICIfStm)var8, false);
               if (!var9.q) {
                  var4 = false;
                  break;
               }

               var5.addAll(var9.RF);
            }
         }

         var3.q = var4;
         var3.RF = var5;
         return var3;
      }
   }

   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size()) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICIfStm && !((ICIfStm)var4).hasDefaultBlock() && var3 < var1.size() - 1) {
            ICIfStm var5 = (ICIfStm)var4;
            ICBlock var6 = var5.getBranchBody(0);
            ICStatement var7 = var6.getLast();
            ICLabel var8 = CUtil.getGotoLabel(var7);
            if (var8 == null) {
               var3++;
               continue;
            }

            boolean var9 = this.m.visitStatements(new ait(this, var7, var8));
            if (!var9) {
               var3++;
               continue;
            }

            ais.eo var10 = this.q(var5, true);
            if (var10.q) {
               int var15 = var1.size() - 1;
               if (!var10.RF.isEmpty()) {
                  label62:
                  for (var15 = var3 + 1; var15 < var1.size(); var15++) {
                     ICStatement var12 = var1.get(var15);

                     for (ICLabel var14 : var10.RF) {
                        if (CUtil.isContainingLabel(var12, var14)) {
                           break label62;
                        }
                     }
                  }

                  var15--;
               }

               int var16 = var15 - var3;
               if (var16 == 0) {
                  var3++;
                  continue;
               }

               if (var16 == 1 && var1.get(var3 + 1) instanceof ICLabel) {
                  var3++;
                  continue;
               }

               ICBlock var17 = this.ef.createBlock();
               var5.setDefaultBlock(var17);

               while (var16-- > 0) {
                  var17.add(var1.remove(var3 + 1));
               }

               var2++;
            }
         }

         var3++;
      }

      return var2;
   }

   private static class eo {
      boolean q;
      Set RF;
   }
}
