package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import java.util.HashSet;
import java.util.List;

public class oM implements Nt {
   private final Bu.CU q = Bu.CU.RF;

   @Override
   public Bu.nI q(List var1) {
      Bu.nI var2 = new Bu.nI(this.q, var1.size());

      for (EE var4 : var1) {
         HashSet var5 = new HashSet();
         HashSet var6 = new HashSet();
         HashSet var7 = new HashSet();

         for (EE.eo var9 : var4.xK) {
            Bu.eo var10 = var9.RF();
            if (var9.q()) {
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

         for (Bu.eo var13 : var6) {
            var2.q(var13);
         }

         for (Bu.eo var14 : var7) {
            var2.q(var14);
         }
      }

      return var2;
   }
}
