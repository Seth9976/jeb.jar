package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

class aoa {
   ant pC;
   int A;
   int kS;

   public aoa(ant var1, int var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + this.kS;
      return 31 * var1 + this.A;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         aoa var2 = (aoa)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         return this.kS != var2.kS ? false : this.A == var2.A;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d-%d-%d", this.pC.UT, this.A, this.kS);
   }
}
