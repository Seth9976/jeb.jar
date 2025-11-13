package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class aiq extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICIfStm var5 && var5.getDefaultBlock() == null) {
            int var6 = var5.size() - 1;
            if (var5.getBranchBody(var6).size() == 1 && var5.getBranchBody(var6).get(0) instanceof ICIfStm var8 && var8.size() == 1) {
               ICOperation var9 = CUtil.andL(this.m, var5.getBranchPredicate(var6).getExpression(), var8.getBranchPredicate(0).getExpression());
               ICPredicate var10 = this.ef.createPredicate(var9);
               var5.setBranchPredicate(var6, var10);
               var5.setBranchBody(var6, var8.getBranchBody(0));
               var2++;
            }
         }
      }

      return var2;
   }
}
