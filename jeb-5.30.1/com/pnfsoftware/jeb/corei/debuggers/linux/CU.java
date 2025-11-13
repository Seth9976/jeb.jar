package com.pnfsoftware.jeb.corei.debuggers.linux;

import com.pnfsoftware.jeb.util.format.Strings;

public class CU {
   public String q;
   public long RF;

   public CU(String var1, long var2) {
      this.q = var1;
      this.RF = var2;
   }

   public String q() {
      return this.q;
   }

   public long RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      return Strings.ff("%s@%Xh", this.q, this.RF);
   }
}
