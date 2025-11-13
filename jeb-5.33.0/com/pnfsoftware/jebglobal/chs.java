package com.pnfsoftware.jebglobal;

import java.util.Iterator;

class chs implements Iterable {
   chs(chq var1) {
      this.pC = var1;
   }

   @Override
   public Iterator iterator() {
      return this.pC.new Av(1);
   }
}
