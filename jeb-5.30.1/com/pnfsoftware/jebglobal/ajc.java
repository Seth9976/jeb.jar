package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ajc extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICWhileStm && ((ICWhileStm)var4).getPredicate().isLitteralTrue()) {
            ICWhileStm var9 = (ICWhileStm)var4;
            ICStatement var11 = var9.getBody().get(0);
            if (var11 instanceof ICLabel) {
               var9.getBody().remove(0);
               var1.insert(var3, var11);
               var2++;
               var3++;
            }
         } else if (var4 instanceof ICDoWhileStm var5) {
            ICStatement var6 = var5.getBody().get(0);
            if (var6 instanceof ICLabel) {
               var5.getBody().remove(0);
               var1.insert(var3, var6);
               var2++;
               var3++;
            }
         } else if (var4 instanceof ICIfStm && ((ICIfStm)var4).size() == 1) {
            ICIfStm var8 = (ICIfStm)var4;
            ICBlock var10 = var8.getBranchBody(0);
            if (var10.size() >= 1 && var10.getLast() instanceof ICGenericLoop && var3 + 1 < var1.size() && var1.get(var3 + 1) instanceof ICLabel) {
               ICStatement var7 = var1.remove(var3 + 1);
               var10.add(var7);
               var2++;
            }
         }
      }

      return var2;
   }
}
