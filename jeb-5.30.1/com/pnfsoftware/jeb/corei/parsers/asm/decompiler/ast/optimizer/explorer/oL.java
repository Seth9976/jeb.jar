package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import java.util.HashSet;
import java.util.List;

public class oL implements Nt {
   private final Bu.CU q;

   public oL() {
      this.q = Bu.CU.q;
   }

   @Override
   public Bu.nI q(List var1) {
      Bu.nI var2 = new Bu.nI(this.q, var1.size());

      for (EE var4 : var1) {
         HashSet var5 = new HashSet();
         HashSet var6 = new HashSet();

         for (EE.eo var8 : var4.xK) {
            Bu.eo var9 = var8.RF();
            if (var8.q()) {
               var5.remove(var9);
               var6.add(var9);
            } else if (!var6.contains(var9)) {
               var5.add(var9);
            }
         }

         for (Bu.eo var11 : var5) {
            var2.q(var11);
         }
      }

      return var2;
   }
}
