package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class cii {
   public int pC;
   public int A;
   public int kS;

   public cii(int var1, int var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public String toString() {
      return Strings.ff("type:%X,len:%X,off:%X", this.pC, this.A, this.kS);
   }
}
