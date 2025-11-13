package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class agv extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICIfStm && var3 + 1 < var1.size() && var1.get(var3 + 1) instanceof ICLabel) {
            ICIfStm var5 = (ICIfStm)var4;
            ICLabel var6 = (ICLabel)var1.get(var3 + 1);
            if (var5.size() == 1
               && var5.getBranchBody(0).size() >= 1
               && var5.getBranchBody(0).get(0) instanceof ICIfStm var8
               && var8.size() == 1
               && var8.getBranchBody(0).size() == 1) {
               ICStatement var9 = var8.getBranchBody(0).get(0);
               if (CUtil.isGotoTo(var9, var6)) {
                  var8.getBranchPredicate(0).reverse(this.of);
                  ICOperation var10 = CUtil.andL(this.m, var5.getBranchPredicate(0).getExpression(), var8.getBranchPredicate(0).getExpression());
                  ICPredicate var11 = this.g.getElementFactory().createPredicate(var10);
                  var5.setBranchPredicate(0, var11);
                  var5.getBranchBody(0).remove(0);
                  var2++;
               }
            }
         }
      }

      return var2;
   }
}
