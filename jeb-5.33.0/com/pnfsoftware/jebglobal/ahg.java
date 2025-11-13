package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;

public class ahg extends AbstractCBlockOptimizer {
   private static boolean pC = true;

   @Override
   protected int optimizeBlock(ICBlock var1) {
      if (!pC) {
         return 0;
      } else {
         int var2 = 0;

         for (int var3 = 0; var3 < var1.size(); var3++) {
            ICStatement var4 = var1.get(var3);
            if (var4 instanceof ICWhileStm var5 && var5.getPredicate().isLitteralTrue()) {
               ICStatement var11 = var5.getBody().get(0);
               if (var11 instanceof ICLabel) {
                  var5.getBody().remove(0);
                  var1.insert(var3, var11);
                  var2++;
                  var3++;
               }
            } else if (var4 instanceof ICDoWhileStm var6) {
               ICStatement var8 = var6.getBody().get(0);
               if (var8 instanceof ICLabel) {
                  var6.getBody().remove(0);
                  var1.insert(var3, var8);
                  var2++;
                  var3++;
               }
            } else if (var4 instanceof ICIfStm var7 && var7.size() == 1) {
               ICBlock var10 = var7.getBranchBody(0);
               if (var10.size() >= 1 && var10.getLast() instanceof ICGenericLoop && var3 + 1 < var1.size() && var1.get(var3 + 1) instanceof ICLabel) {
                  ICStatement var9 = var1.remove(var3 + 1);
                  var10.add(var9);
                  var2++;
               }
            }
         }

         return var2;
      }
   }
}
