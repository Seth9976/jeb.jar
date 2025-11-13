package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class tE implements Callable {
   tE(dy var1, com.pnfsoftware.jeb.corei.parsers.elf.sy var2, long var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public Boolean pC() throws Exception {
      this.kS.UT.pC(true);
      long var1 = this.kS.UT.pC(this.pC);
      if (var1 != 0L) {
         long var3 = var1 + this.A;
         String var5 = this.kS.pC(var3);
         return this.kS.pC(var5, 0, null, true) != null;
      } else {
         return false;
      }
   }
}
