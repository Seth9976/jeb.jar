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

public class agw extends AbstractCBlockOptimizer {
   private static boolean pC = false;

   private agw.Av pC(ICIfStm var1, boolean var2) {
      agw.Av var3 = new agw.Av();
      if (!var2 && !var1.hasDefaultBlock()) {
         var3.pC = false;
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
            if (!(var8 instanceof ICGoto) && !(var8 instanceof ICIfStm) && (!pC || !(var8 instanceof ICTerminalStatement))) {
               var4 = false;
               break;
            }

            if (var8 instanceof ICGoto var9) {
               var5.add(var9.getLabel());
            } else if (var8 instanceof ICIfStm var10) {
               agw.Av var11 = this.pC(var10, false);
               if (!var11.pC) {
                  var4 = false;
                  break;
               }

               var5.addAll(var11.A);
            }
         }

         var3.pC = var4;
         var3.A = var5;
         return var3;
      }
   }

   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size()) {
         if (var1.get(var3) instanceof ICIfStm var5 && !var5.hasDefaultBlock() && var3 < var1.size() - 1) {
            ICBlock var6 = var5.getBranchBody(0);
            if (var6.isEmpty()) {
               var3++;
               continue;
            }

            ICStatement var7 = var6.getLast();
            ICLabel var8 = CUtil.getGotoLabel(var7);
            if (var8 == null) {
               var3++;
               continue;
            }

            boolean var9 = this.m.visitStatements(new agx(this, var7, var8));
            if (!var9) {
               var3++;
               continue;
            }

            agw.Av var10 = this.pC(var5, true);
            if (var10.pC) {
               int var15 = var1.size() - 1;
               if (!var10.A.isEmpty()) {
                  label63:
                  for (var15 = var3 + 1; var15 < var1.size(); var15++) {
                     ICStatement var12 = var1.get(var15);

                     for (ICLabel var14 : var10.A) {
                        if (CUtil.isContainingLabel(var12, var14)) {
                           break label63;
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

   private static class Av {
      boolean pC;
      Set A;
   }
}
