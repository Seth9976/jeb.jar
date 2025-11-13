package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import com.pnfsoftware.jeb.util.base.Couple;
import java.util.Comparator;

class bK implements Comparator {
   bK(Bu.nI var1) {
      this.q = var1;
   }

   public int q(Couple var1, Couple var2) {
      return Integer.compare((Integer)var2.getSecond(), (Integer)var1.getSecond());
   }
}
