package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class aob {
   ant pC;
   int A;
   aob kS;

   public aob(ant var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   public aob(ant var1, int var2, aob var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
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
         aob var2 = (aob)var1;
         if (this.pC == null) {
            if (var2.pC != null) {
               return false;
            }
         } else if (!this.pC.equals(var2.pC)) {
            return false;
         }

         return this.A == var2.A;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d-%d", this.pC.UT, this.A);
   }
}
