package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class azh {
   aye pC;
   String A;
   int kS;
   int wS;

   public azh(aye var1, String var2, int var3, int var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var4;
   }

   public aye pC() {
      return this.pC;
   }

   public String A() {
      return this.A;
   }

   @Override
   public String toString() {
      String var1;
      if (this.A == null) {
         var1 = this.pC.toString();
      } else {
         var1 = Strings.ff("\"%s\":%s", this.A, this.pC);
      }

      if (this.kS > 0) {
         var1 = var1 + ":" + this.kS;
      }

      if (this.wS > 0) {
         var1 = var1 + "(align:" + this.wS + ")";
      }

      return var1;
   }
}
