package com.pnfsoftware.jebglobal;

import java.util.Comparator;
import java.util.Set;

class bov implements Comparator {
   bov(bou.Av var1, bou var2, bpc var3, boolean var4) {
      this.wS = var1;
      this.pC = var2;
      this.A = var3;
      this.kS = var4;
   }

   public int pC(bpc var1, bpc var2) {
      Set var3 = this.wS.pC.pC(var1.pC, this.A.pC, this.kS);
      Set var4 = this.wS.pC.pC(var2.pC, this.A.pC, this.kS);
      int var5 = var3.size();
      int var6 = var4.size();
      if (var5 == var6) {
         if (var3.add(var1.pC)) {
            var5++;
         }

         if (var4.add(var2.pC)) {
            var6++;
         }
      }

      return var6 - var5;
   }
}
