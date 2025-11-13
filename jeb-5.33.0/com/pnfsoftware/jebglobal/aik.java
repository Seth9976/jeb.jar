package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConditionalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import java.util.List;

public class aik extends AbstractCBlockOptimizer {
   @Override
   protected int optimizeBlock(ICBlock var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var1.size()) {
         ICStatement var4 = var1.get(var3);
         if (var4 instanceof ICConditionalStatement var5) {
            if (var5.hasDefaultBlock()) {
               ICBlock var6 = var5.getDefaultBlock();
               var2 += this.pC(var1, var3, var6);
               if (var6.size() == 0) {
                  if (var5 instanceof ICSwitchStm) {
                     ((ICSwitchStm)var5).setDefaultBlock(null);
                  } else if (var5 instanceof ICIfStm) {
                     ((ICIfStm)var5).setDefaultBlock(null);
                  }
               }
            }

            List var10 = var5.getConditionalBlocks();

            for (int var7 = 0; var7 < var10.size(); var7++) {
               ICBlock var8 = (ICBlock)var10.get(var7);
               var2 += this.pC(var1, var3, var8);
            }
         } else if (var4 instanceof ICDoWhileStm || var4 instanceof ICWhileStm) {
            ICBlock var9 = null;
            if (var4 instanceof ICDoWhileStm var11) {
               var9 = var11.getBody();
            } else {
               ICWhileStm var12 = (ICWhileStm)var4;
               if (var12.getPredicate().isLitteralTrue()) {
                  var9 = var12.getBody();
               }
            }

            if (var9 != null && var9.size() > 0 && var9.get(0) instanceof ICLabel) {
               var1.insert(var3, var9.get(0));
               var9.remove(0);
               var2++;
               continue;
            }
         }

         var3++;
      }

      return var2;
   }

   private int pC(ICBlock var1, int var2, ICBlock var3) {
      int var4 = 0;
      if (!var3.isEmpty()) {
         ICStatement var5 = var3.getLast();
         if (var1.get(var2) instanceof ICIfStm && var5 instanceof ICLabel) {
            var1.insert(var2 + 1, var5);
            var3.remove(var3.size() - 1);
            var4++;
         }

         if (var3.size() > 1 && var1.get(var2) instanceof ICSwitchStm && var5 instanceof ICBreak && ((ICBreak)var5).getLabel() == null) {
            ICStatement var6 = var3.get(var3.size() - 2);
            if (var6 instanceof ICLabel) {
               var1.insert(var2 + 1, var6);
               var3.remove(var3.size() - 2);
               var4++;
            }
         }
      }

      return var4;
   }
}
