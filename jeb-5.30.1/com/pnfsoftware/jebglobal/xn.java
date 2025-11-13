package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class xn {
   int q;
   String RF;

   xn(int var1, String var2) {
      this.q = var1;
      this.RF = var2;
   }

   public int q() {
      return this.q;
   }

   public String RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X: %s", this.q(), this.RF());
   }
}
