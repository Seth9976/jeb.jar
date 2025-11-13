package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;

public class bqu extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else if (this.m.isEmpty()) {
         return 0;
      } else {
         int var1 = 0;
         if (this.m.getBody().getLast() instanceof IJavaReturn var3 && var3.returnsVoid()) {
            this.m.getBody().removeLast();
            var1++;
         }

         int var9 = 0;
         bsb var4 = new bsb(this.m);
         int var5 = 0;

         while (var5 < var4.q()) {
            if (var4.RF(var5) instanceof IJavaReturn var6 && var6.returnsVoid()) {
               int var7 = var4.q(var5, false, true, 1, null);
               if (var7 == var4.q() - 1) {
                  var4.q(var5);
                  var9++;
                  continue;
               }
            }

            var5++;
         }

         if (var9 > 0) {
            this.m.fromFlatList(var4.RF());
            var1 += var9;
         }

         return var1;
      }
   }
}
