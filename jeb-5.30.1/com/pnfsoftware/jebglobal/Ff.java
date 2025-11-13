package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class Ff implements Callable {
   Ff(Cg var1) {
      this.q = var1;
   }

   public Ht q() throws Exception {
      oH var1 = this.q.RF.RF();
      int var2 = var1.Rr();

      Object var3;
      try {
         if (var1.xK((int)this.q.getId())) {
            return this.q.q(true);
         }

         var3 = null;
      } finally {
         var1.xK(var2);
      }

      return (Ht)var3;
   }
}
