package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class oe {
   int pC;
   String A;

   oe(int var1, String var2) {
      this.pC = var1;
      this.A = var2;
   }

   public int pC() {
      return this.pC;
   }

   public String A() {
      return this.A;
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X: %s", this.pC(), this.A());
   }
}
