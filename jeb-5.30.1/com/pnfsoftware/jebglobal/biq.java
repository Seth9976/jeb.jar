package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.IExtractor;

class biq implements IExtractor {
   biq(bip var1) {
      this.q = var1;
   }

   public Integer q(bie var1) {
      return (int)var1.getOffset();
   }
}
