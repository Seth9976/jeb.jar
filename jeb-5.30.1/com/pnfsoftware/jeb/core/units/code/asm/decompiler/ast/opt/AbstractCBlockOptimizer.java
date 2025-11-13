package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;

public abstract class AbstractCBlockOptimizer extends AbstractCOptimizer {
   @Override
   protected int perform() {
      return this.performOnMethoBlocksRecursivively();
   }

   public final int performOnMethoBlocksRecursivively() {
      return this.performRecursive(this.m.getBody(), null, null);
   }

   private int performRecursive(ICBlock var1, ICCompound var2, ICStatement var3) {
      int var4 = 0;

      for (int var5 = 0; var5 < var1.size(); var5++) {
         ICStatement var6 = var1.get(var5);
         if (var6 instanceof ICCompound) {
            ICStatement var7 = this.getNextSiblingStatement(var5, var1, var3);

            for (ICBlock var10 : ((ICCompound)var6).getBlocks()) {
               var4 += this.performRecursive(var10, (ICCompound)var6, var7);
            }
         }
      }

      return var4 + this.optimizeBlock(var1, var2, var3);
   }

   protected ICStatement getNextSiblingStatement(int var1, ICBlock var2, ICStatement var3) {
      ICStatement var4;
      if (var1 == var2.size() - 1) {
         var4 = var3;
      } else {
         var4 = CUtil.getFirstRealStatement(var2, var1 + 1);
      }

      return var4;
   }

   protected abstract int optimizeBlock(ICBlock var1);

   protected int optimizeBlock(ICBlock var1, ICCompound var2, ICStatement var3) {
      return this.optimizeBlock(var1);
   }
}
