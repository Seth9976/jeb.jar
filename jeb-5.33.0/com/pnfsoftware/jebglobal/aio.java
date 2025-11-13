package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTerminalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import com.pnfsoftware.jeb.util.base.Assert;

public class aio extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (CUtil.isIfElse(var4)) {
            ICIfStm var5 = (ICIfStm)var4;
            boolean var6 = false;
            ICBlock var7 = var5.getBranchBody(0);
            if (this.pC(var7)) {
               var6 = true;
            } else {
               var7 = var5.getDefaultBlock();
               if (this.pC(var7)) {
                  Assert.a(CUtil.flipIfElse(var4, this.of));
                  var6 = true;
               }
            }

            if (var6) {
               ICBlock var8 = var5.removeDefaultBlock();
               var1.insertAll(var3 + 1, var8);
               var2++;
            }
         }
      }

      return var2;
   }

   private boolean pC(ICBlock var1) {
      if (var1.isEmpty()) {
         return false;
      } else {
         ICStatement var2 = var1.getLast();
         return var2 instanceof ICTerminalStatement || var2 instanceof ICJumpFar || var2 instanceof ICBreak || var2 instanceof ICContinue;
      }
   }
}
