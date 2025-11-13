package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bfo extends bgg {
   public bfo(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("callSites", var1, var2);
   }

   public synchronized bfr pC(beg[] var1) {
      int var2 = this.UT.size();
      bfr var3 = new bfr(this.kS, var2, var1);
      this.pC(var3.getName(false), var3);
      return var3;
   }
}
