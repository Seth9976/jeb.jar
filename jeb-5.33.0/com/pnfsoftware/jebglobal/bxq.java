package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDSandboxHooks;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

class bxq implements IDSandboxHooks {
   bxq(bxp var1, String var2) {
      this.A = var1;
      this.pC = var2;
   }

   @Override
   public Wrapper invokeMethod(long var1, String var3, String var4, Object var5, List var6) {
      return ckx.pC(68, 3893068, 880103391, var4) ? Wrapper.wrap(this.pC) : null;
   }
}
