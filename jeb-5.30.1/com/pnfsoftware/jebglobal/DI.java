package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class DI implements Callable {
   DI(um var1, long var2, int var4, byte[] var5, int var6) {
      this.Uv = var1;
      this.q = var2;
      this.RF = var4;
      this.xK = var5;
      this.Dw = var6;
   }

   public Integer q() throws Exception {
      return this.Uv.RF(this.q, this.RF, this.xK, this.Dw);
   }
}
