package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ahq extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICReturn) {
            ICStatement var5 = CUtil.getFirstRealStatement(var1, var3 + 1);
            if (var5 instanceof ICReturn && this.m.deleteStatement((ICReturn)var5)) {
               var2++;
            }

            var2 += this.pC(var1, var3, (ICReturn)var4);
         }
      }

      return var2;
   }

   private int pC(ICBlock var1, int var2, ICReturn var3) {
      int var4 = 0;
      if (var2 == 0) {
         return var4;
      } else {
         if (var1.get(var2 - 1) instanceof ICIfStm var6) {
            for (int var7 = 0; var7 < var6.getBranchPredicates().size(); var7++) {
               if (this.pC(var3, var6.getBranchBody(var7))) {
                  var4++;
               }
            }

            if (this.pC(var3, var6.getDefaultBlock())) {
               var4++;
               if (var6.getDefaultBlock().size() == 0) {
                  var6.setDefaultBlock(null);
               }
            }
         }

         return var4;
      }
   }

   private boolean pC(ICReturn var1, ICBlock var2) {
      if (var2 != null && var2.size() != 0) {
         ICStatement var3 = var2.get(var2.size() - 1);
         if (var3 instanceof ICReturn && ((ICReturn)var3).equals(var1)) {
            var2.remove(var2.size() - 1);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}
