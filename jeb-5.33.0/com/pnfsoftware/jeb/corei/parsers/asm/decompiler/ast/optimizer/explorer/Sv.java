package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import java.util.HashSet;
import java.util.List;

public class Sv implements K {
   private final RC.Sv pC = RC.Sv.A;

   @Override
   public RC.K pC(List var1) {
      RC.K var2 = new RC.K(this.pC, var1.size());

      for (cq var4 : var1) {
         HashSet var5 = new HashSet();
         HashSet var6 = new HashSet();
         HashSet var7 = new HashSet();

         for (cq.Av var9 : var4.kS) {
            RC.Av var10 = var9.A();
            if (var9.pC()) {
               var7.remove(var10);
               if (var6.contains(var10)) {
                  var6.remove(var10);
               } else if (!var5.contains(var10)) {
                  var6.add(var10);
               }
            } else if (!var5.contains(var10)) {
               var7.add(var10);
            }

            var5.add(var10);
         }

         for (RC.Av var13 : var6) {
            var2.pC(var13);
         }

         for (RC.Av var14 : var7) {
            var2.pC(var14);
         }
      }

      return var2;
   }
}
