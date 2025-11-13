package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class fD implements Callable {
   fD(HX var1) {
      this.pC = var1;
   }

   public LD pC() throws Exception {
      aI var1 = this.pC.A.A();
      int var2 = var1.os();

      Object var3;
      try {
         if (var1.A((int)this.pC.getId())) {
            return this.pC.pC(true);
         }

         var3 = null;
      } finally {
         var1.A(var2);
      }

      return (LD)var3;
   }
}
