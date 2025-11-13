package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.ast.optimizer.explorer;

import java.util.Comparator;

class rQ implements Comparator {
   rQ(DH var1) {
      this.pC = var1;
   }

   public int pC(cq var1, cq var2) {
      return var1.kS.size() - var2.kS.size();
   }
}
