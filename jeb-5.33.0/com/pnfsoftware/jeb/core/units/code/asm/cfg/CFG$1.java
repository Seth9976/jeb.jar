package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import java.util.Comparator;

class CFG$1 implements Comparator {
   CFG$1(CFG var1) {
      this.this$0 = var1;
   }

   public int compare(BasicBlock var1, BasicBlock var2) {
      return Long.compare(var1.base, var2.base);
   }
}
