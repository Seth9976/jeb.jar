package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import java.util.Iterator;

class CFG$3 implements Iterable {
   CFG$3(CFG var1) {
      this.this$0 = var1;
   }

   @Override
   public Iterator iterator() {
      return this.this$0.new AddressableInstructionsIterator();
   }
}
