package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.base.Couple;
import java.util.Comparator;

class sy implements Comparator {
   sy(RC.K var1) {
      this.pC = var1;
   }

   public int pC(Couple var1, Couple var2) {
      return Integer.compare((Integer)var2.getSecond(), (Integer)var1.getSecond());
   }
}
