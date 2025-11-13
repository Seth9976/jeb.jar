package com.pnfsoftware.jebglobal;

import java.util.List;
import java.util.concurrent.Callable;

class BW implements Callable {
   BW(ia var1) {
      this.pC = var1;
   }

   public List pC() throws Exception {
      return this.pC.ld();
   }
}
