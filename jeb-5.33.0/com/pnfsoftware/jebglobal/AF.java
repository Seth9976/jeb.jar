package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class AF implements Callable {
   AF(ia var1, long var2) {
      this.A = var1;
      this.pC = var2;
   }

   public HX pC() throws Exception {
      for (HX var2 : this.A.getThreads()) {
         if (var2.getId() == this.pC) {
            return var2;
         }
      }

      return null;
   }
}
