package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.util.format.Strings;

public class Sv {
   public String pC;
   public long A;

   public Sv(String var1, long var2) {
      this.pC = var1;
      this.A = var2;
   }

   public String pC() {
      return this.pC;
   }

   public long A() {
      return this.A;
   }

   @Override
   public String toString() {
      return Strings.ff("%s@%Xh", this.pC, this.A);
   }
}
