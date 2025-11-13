package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericWhileLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public abstract class agl extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      return 0;
   }

   @Override
   public int optimizeBlock(ICBlock var1, ICCompound var2, ICStatement var3) {
      int var4 = 0;
      int var5 = 0;

      while (var5 < var1.size()) {
         ICStatement var6 = var1.get(var5);
         if (!this.pC(var6)) {
            var5++;
         } else {
            if (CUtil.isWhileTrue(var6)) {
               ICWhileStm var7 = (ICWhileStm)var6;
               if (var7.getBody().get(0) instanceof ICLabel) {
                  ICStatement var8 = var7.getBody().remove(0);
                  var1.insert(var5, var8);
                  var5++;
                  var4++;
               }
            }

            ICGenericWhileLoop var11 = (ICGenericWhileLoop)var6;
            int var12 = this.pC(var11);
            if (var12 == 0) {
               var5++;
            } else {
               ICLabel var9 = this.pC(var5, var1, var3);
               agl.Av var10 = this.pC(var1, var11, var5, var9, var12);
               var4 += var10.pC;
               var5 += var10.A;
            }
         }
      }

      return var4;
   }

   private boolean pC(ICStatement var1) {
      return !(var1 instanceof ICGenericWhileLoop var2) ? false : var2.getBody().size() != 0;
   }

   private ICLabel pC(int var1, ICBlock var2, ICStatement var3) {
      ICStatement var4 = this.getNextSiblingStatement(var1, var2, var3);
      return var4 instanceof ICLabel ? (ICLabel)var4 : null;
   }

   protected abstract int pC(ICGenericWhileLoop var1);

   protected abstract agl.Av pC(ICBlock var1, ICGenericWhileLoop var2, int var3, ICLabel var4, int var5);

   protected static class Av {
      int pC;
      int A = 1;
   }
}
