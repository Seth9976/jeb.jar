package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bgi extends bgg {
   public bgi(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("strings", var1, var2);
   }

   public synchronized bfx pC(String var1, boolean var2, boolean var3) {
      if (var3) {
         bfx var4 = (bfx)this.E.get(var1);
         if (var4 != null) {
            return var4;
         }
      }

      int var6 = this.UT.size();
      bfx var5 = new bfx(this.kS, var6, var1, var2);
      this.UT.add(var5);
      this.E.put(var1, var5);
      return var5;
   }
}
