package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import java.util.ArrayList;
import java.util.List;

public abstract class tl {
   protected List RF = new ArrayList();

   public boolean q(tl.eo var1) {
      return this.RF.add(var1);
   }

   public List RF() {
      ArrayList var1 = new ArrayList();
      List var2 = this.q();

      for (tl.eo var4 : this.RF) {
         switch (var4) {
            case q:
               this.q(var2, var1);
         }
      }

      return var1;
   }

   private void q(List var1, List var2) {
      for (Class var4 : var1) {
         Vj var5 = new Vj();
         var5.q(1, var4);
         var2.add(var5);
      }
   }

   abstract List q();

   public static enum eo {
      q,
      RF,
      xK;
   }
}
