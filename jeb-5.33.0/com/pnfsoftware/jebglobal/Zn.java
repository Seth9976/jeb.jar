package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import java.util.concurrent.Callable;

class Zn implements Callable {
   Zn(ia var1, IDebuggerBreakpoint var2, boolean var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public Boolean pC() throws Exception {
      return this.kS.ys.pC(this.pC, this.A);
   }
}
