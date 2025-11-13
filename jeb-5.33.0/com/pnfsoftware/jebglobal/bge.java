package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class bge extends bgg {
   public bge(com.pnfsoftware.jeb.corei.parsers.dex.vi var1, int var2) {
      super("methodHandles", var1, var2);
   }

   public synchronized bfv pC(int var1, int var2) {
      int var3 = this.UT.size();
      bfv var4 = new bfv(this.kS, var3, var1, var2);
      this.pC(var4.getName(false), var4);
      return var4;
   }
}
