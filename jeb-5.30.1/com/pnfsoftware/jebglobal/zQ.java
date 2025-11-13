package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerBreakpoint;
import java.util.concurrent.Callable;

class zQ implements Callable {
   zQ(um var1, IDebuggerBreakpoint var2, boolean var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public Boolean q() throws Exception {
      return this.xK.nf.q(this.q, this.RF);
   }
}
