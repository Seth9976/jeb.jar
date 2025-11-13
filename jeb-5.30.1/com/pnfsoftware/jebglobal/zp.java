package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class zp implements Callable {
   zp(Rz var1, long var2, long var4, byte[] var6) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var4;
      this.xK = var6;
   }

   public Long q() throws Exception {
      return this.Dw.RF.findBytes(this.q, this.RF, this.xK);
   }
}
