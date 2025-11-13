package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import java.util.Iterator;

class CFG$2 implements Iterable {
   CFG$2(CFG var1) {
      this.this$0 = var1;
   }

   @Override
   public Iterator iterator() {
      return this.this$0.new InstructionsIterator();
   }
}
