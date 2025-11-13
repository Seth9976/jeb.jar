package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class iZ {
   @SerId(1)
   Integer q;
   @SerId(2)
   int[] RF;

   public iZ(Integer var1, int[] var2) {
      this.q = var1;
      this.RF = var2;
   }

   public Integer q() {
      return this.q;
   }

   public int[] RF() {
      return this.RF;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("(");
      int var2 = 0;

      for (int var6 : this.RF) {
         if (var2 > 0) {
            var1.append(",");
         }

         String var7 = Xa.xK(var6);
         var1.append(var7);
         var2++;
      }

      var1.append(")");
      if (this.q != null) {
         var1.append(Xa.xK(this.q));
      }

      return var1.toString();
   }
}
