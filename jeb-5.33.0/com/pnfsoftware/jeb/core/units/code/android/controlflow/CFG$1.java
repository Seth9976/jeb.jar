package com.pnfsoftware.jeb.core.units.code.android.controlflow;

import java.util.Iterator;

class CFG$1 implements Iterable {
   CFG$1(CFG var1) {
      this.this$0 = var1;
   }

   @Override
   public Iterator iterator() {
      return this.this$0.new InstructionsIterator();
   }
}
