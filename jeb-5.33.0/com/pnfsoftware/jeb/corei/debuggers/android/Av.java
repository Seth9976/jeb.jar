package com.pnfsoftware.jeb.corei.debuggers.android;

import com.pnfsoftware.jebglobal.Jx;
import com.pnfsoftware.jebglobal.ZD;

class Av extends ZD {
   Av(AndroidDbgClient var1) {
      this.pC = var1;
   }

   @Override
   public void pC(long var1, Jx var3, boolean var4) {
      this.pC.E.ys(var1);
   }
}
