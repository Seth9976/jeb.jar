package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.CMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class nI extends tl {
   List q = new ArrayList();

   public nI() {
      CMasterOptimizer var1 = new CMasterOptimizer(null);

      for (OptimizerEntry var4 : var1.getRegisteredOptimizers()) {
         this.q.add(var4.getOptimizer().getClass());
      }
   }

   @Override
   List q() {
      return this.q;
   }

   @Override
   public List RF() {
      List var1 = super.RF();

      for (tl.eo var3 : this.RF) {
         switch (var3) {
            case RF:
               this.q(var1, false);
               break;
            case xK:
               this.q(var1, true);
         }
      }

      return var1;
   }

   private void q(List var1, boolean var2) {
      Vj var3 = new Vj();
      CMasterOptimizer var4 = new CMasterOptimizer(null);
      List var5 = var4.getRegisteredOptimizers();
      if (var2) {
         Collections.reverse(var5);
      }

      for (OptimizerEntry var7 : var5) {
         var3.q(var7.getGroup(), var7.getOptimizer().getClass());
      }

      var1.add(var3);
   }
}
