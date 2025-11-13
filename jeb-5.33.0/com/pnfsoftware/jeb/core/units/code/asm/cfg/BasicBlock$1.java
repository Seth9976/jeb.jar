package com.pnfsoftware.jeb.core.units.code.asm.cfg;

import java.util.Iterator;

class BasicBlock$1 implements Iterable {
   BasicBlock$1(BasicBlock var1) {
      this.this$0 = var1;
   }

   @Override
   public Iterator iterator() {
      return this.this$0.new AddressableInstructionsIterator();
   }
}
