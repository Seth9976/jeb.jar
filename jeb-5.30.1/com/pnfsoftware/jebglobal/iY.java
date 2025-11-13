package com.pnfsoftware.jebglobal;

import java.util.concurrent.Callable;

class iY implements Callable {
   iY(Rz var1, long var2, int var4, byte[] var5, int var6, boolean var7) {
      this.oW = var1;
      this.q = var2;
      this.RF = var4;
      this.xK = var5;
      this.Dw = var6;
      this.Uv = var7;
   }

   public Integer q() throws Exception {
      return this.oW.RF.write(this.q, this.RF, this.xK, this.Dw, this.Uv);
   }
}
