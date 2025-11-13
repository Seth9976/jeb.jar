package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bkd extends bkb {
   public bkd(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2) {
      super("strings", var1, var2);
   }

   public synchronized bjs q(String var1, boolean var2, boolean var3) {
      if (var3) {
         bjs var4 = (bjs)this.oW.get(var1);
         if (var4 != null) {
            return var4;
         }
      }

      int var6 = this.Uv.size();
      bjs var5 = new bjs(this.xK, var6, var1, var2);
      this.Uv.add(var5);
      this.oW.put(var1, var5);
      return var5;
   }
}
