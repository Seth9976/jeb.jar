package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bjj extends bkb {
   public bjj(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("callSites", var1, var2);
   }

   public synchronized bjm q(bia[] var1) {
      int var2 = this.Uv.size();
      bjm var3 = new bjm(this.xK, var2, var1);
      this.q(var3.getName(false), var3);
      return var3;
   }
}
