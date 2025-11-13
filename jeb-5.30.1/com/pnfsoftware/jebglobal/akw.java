package com.pnfsoftware.jebglobal;

import java.util.Comparator;
import java.util.Set;

class akw implements Comparator {
   akw(akv.eo var1, akv var2, ald var3, boolean var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public int q(ald var1, ald var2) {
      Set var3 = this.Dw.q.q(var1.q, this.RF.q, this.xK);
      Set var4 = this.Dw.q.q(var2.q, this.RF.q, this.xK);
      int var5 = var3.size();
      int var6 = var4.size();
      if (var5 == var6) {
         if (var3.add(var1.q)) {
            var5++;
         }

         if (var4.add(var2.q)) {
            var6++;
         }
      }

      return var6 - var5;
   }
}
