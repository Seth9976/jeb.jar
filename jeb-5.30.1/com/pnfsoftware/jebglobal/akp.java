package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class akp extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 + 1 < var1.size(); var3++) {
         if (var1.get(var3) instanceof ICSwitchStm var5) {
            int var6 = var3 + 1;

            ICReturn var7;
            for (var7 = null; var6 < var1.size(); var6++) {
               if (!(var1.get(var6) instanceof ICLabel)) {
                  if (var1.get(var6) instanceof ICReturn) {
                     var7 = (ICReturn)var1.get(var6);
                  }
                  break;
               }
            }

            if (var7 != null) {
               ICExpression var8 = var7.getExpression();

               for (ICBlock var10 : var5.getBlocks()) {
                  if (!var10.isEmpty()) {
                     ICStatement var11 = var10.getLast();
                     ICReturn var12 = this.ef.createReturn(var8 == null ? null : var8.duplicate());
                     var2 += this.q(var10, var11, var12);
                  }
               }

               if (!var5.hasDefaultBlock()) {
                  ICReturn var13 = this.ef.createReturn(var8 == null ? null : var8.duplicate());
                  var5.setDefaultBlock(this.ef.createBlock(var13));
                  var2++;
               }
            }
         }
      }

      return var2;
   }

   private int q(ICBlock var1, ICStatement var2, ICReturn var3) {
      int var4 = 0;
      if (var2 instanceof ICBreak && ((ICBreak)var2).getLabel() == null) {
         var1.set(var1.size() - 1, var3);
         var4++;
         Object[] var11 = new Object[]{var3.getExpression()};
      } else if (var2 instanceof ICIfStm) {
         for (int var5 = 0; var5 < ((ICIfStm)var2).sizeWithoutDefault(); var5++) {
            ICBlock var6 = ((ICIfStm)var2).getBranchBody(var5);
            ICStatement var7 = var6.getLast();
            var4 += this.q(var6, var7, var3);
         }

         ICBlock var8 = ((ICIfStm)var2).getDefaultBlock();
         if (var8 != null) {
            ICStatement var9 = var8.getLast();
            var4 += this.q(var8, var9, var3);
         } else {
            var1.add(var3);
            var4++;
            Object[] var10000 = new Object[]{var3.getExpression()};
         }
      } else if (CUtil.canFallthrough(var2, var3, false)) {
         var1.add(var3);
         var4++;
         Object[] var10 = new Object[]{var3.getExpression()};
      }

      return var4;
   }
}
