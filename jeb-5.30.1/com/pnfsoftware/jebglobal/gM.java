package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class gM implements Callable {
   gM(Rz var1, long var2, int var4, int var5) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var4;
      this.xK = var5;
   }

   public Integer q() throws Exception {
      return this.Dw.RF.check(this.q, this.RF, this.xK);
   }
}
