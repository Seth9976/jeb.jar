package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import java.util.concurrent.Callable;

class Kj implements Callable {
   Kj(um var1, String var2, int var3, ICodeUnit var4, boolean var5) {
      this.Uv = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
      this.Dw = var5;
   }

   public xI q() throws Exception {
      return this.Uv.RF(this.q, this.RF, this.xK, this.Dw);
   }
}
