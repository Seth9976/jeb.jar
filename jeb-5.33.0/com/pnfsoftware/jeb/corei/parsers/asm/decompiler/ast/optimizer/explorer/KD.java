package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import java.util.HashSet;
import java.util.List;

public class KD implements K {
   private final RC.Sv pC = RC.Sv.kS;

   @Override
   public RC.K pC(List var1) {
      RC.K var2 = new RC.K(this.pC, var1.size());

      for (cq var4 : var1) {
         HashSet var5 = new HashSet();
         HashSet var6 = new HashSet();

         for (cq.Av var8 : var4.kS) {
            RC.Av var9 = var8.A();
            if (!var6.contains(var9) && !var8.pC()) {
               var5.add(var9);
            }

            var6.add(var9);
         }

         for (RC.Av var11 : var5) {
            var2.pC(var11);
         }
      }

      return var2;
   }
}
