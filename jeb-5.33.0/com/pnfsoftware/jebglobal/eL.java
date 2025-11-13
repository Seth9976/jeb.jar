package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class eL {
   public int pC;
   public byte A;

   public eL(int var1, byte var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("@%d:%c", this.pC, this.A);
   }
}
