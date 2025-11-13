package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class DH {
   @SerId(1)
   Integer pC;
   @SerId(2)
   int[] A;

   public DH(Integer var1, int[] var2) {
      this.pC = var1;
      this.A = var2;
   }

   public Integer pC() {
      return this.pC;
   }

   public int[] A() {
      return this.A;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("(");
      int var2 = 0;

      for (int var6 : this.A) {
         if (var2 > 0) {
            var1.append(",");
         }

         String var7 = Tb.kS(var6);
         var1.append(var7);
         var2++;
      }

      var1.append(")");
      if (this.pC != null) {
         var1.append(Tb.kS(this.pC));
      }

      return var1.toString();
   }
}
