package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ags extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      if (var1.size() >= 2) {
         ICStatement var2 = var1.get(var1.size() - 1);
         if (var2 instanceof ICGoto) {
            ICStatement var3 = var1.get(var1.size() - 2);
            if (var3 instanceof ICCall && ((ICCall)var3).isNonReturningCall()) {
               var1.set(var1.size() - 1, this.ef.createReturn());
               return 1;
            }
         }
      }

      return 0;
   }
}
