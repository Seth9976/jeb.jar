package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class CK implements Callable {
   CK(um var1, long var2) {
      this.RF = var1;
      this.q = var2;
   }

   public Cg q() throws Exception {
      for (Cg var2 : this.RF.getThreads()) {
         if (var2.getId() == this.q) {
            return var2;
         }
      }

      return null;
   }
}
