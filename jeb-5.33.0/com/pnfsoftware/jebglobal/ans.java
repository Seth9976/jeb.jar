package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

class ans {
   boolean pC;
   int A;
   int kS;
   Long wS;

   public ans(int var1, long var2) {
      this.pC = true;
      this.A = var1;
      this.wS = var2;
   }

   public ans(int var1, int var2, Long var3) {
      this.pC = false;
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
   }

   @Override
   public String toString() {
      String var1;
      if (this.pC) {
         var1 = Strings.ff("call_%Xh(this+%Xh,...)", this.wS, this.A);
      } else {
         var1 = Strings.ff("%d[this+%Xh]", this.kS, this.A);
         if (this.wS != null) {
            var1 = var1 + Strings.ff("=0x%X", this.wS);
         }
      }

      return var1;
   }
}
