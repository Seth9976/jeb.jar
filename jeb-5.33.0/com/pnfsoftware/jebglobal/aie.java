package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import java.util.List;

public class aie extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICIfStm) {
            List var5 = ((ICIfStm)var4).getBlocks();
            if (var5.size() == 1) {
               ICPredicate var6 = ((ICIfStm)var4).getBranchPredicate(0);
               List var7 = ((ICBlock)var5.get(0)).getSubElements();
               if (var7.size() == 1 && var7.get(0) instanceof ICDoWhileStm) {
                  ICDoWhileStm var8 = (ICDoWhileStm)var7.get(0);
                  if (var6.equals(var8.getPredicate())) {
                     ICWhileStm var9 = this.ef.createWhileStm(var6, var8.getBody());
                     var1.set(var3, var9);
                     var2++;
                  }
               }
            }
         }
      }

      return var2;
   }
}
