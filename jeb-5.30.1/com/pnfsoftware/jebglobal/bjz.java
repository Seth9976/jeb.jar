package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bjz extends bkb {
   public bjz(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("methodHandles", var1, var2);
   }

   public synchronized bjq q(int var1, int var2) {
      int var3 = this.Uv.size();
      bjq var4 = new bjq(this.xK, var3, var1, var2);
      this.q(var4.getName(false), var4);
      return var4;
   }
}
