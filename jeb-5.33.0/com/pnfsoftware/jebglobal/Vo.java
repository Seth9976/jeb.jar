package com.pnfsoftware.jebglobal;

import java.util.List;
import java.util.concurrent.Callable;

class Vo implements Callable {
   Vo(ia var1) {
      this.pC = var1;
   }

   public List pC() throws Exception {
      return this.pC.WR();
   }
}
