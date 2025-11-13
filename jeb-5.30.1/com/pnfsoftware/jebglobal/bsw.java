package com.pnfsoftware.jebglobal;

import java.util.Comparator;
import java.util.Set;

class bsw implements Comparator {
   bsw(bsv.eo var1, bsv var2, bte var3, boolean var4) {
      this.Dw = var1;
      this.q = var2;
      this.RF = var3;
      this.xK = var4;
   }

   public int q(bte var1, bte var2) {
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
