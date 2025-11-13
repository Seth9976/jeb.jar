package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class aiu {
   public int pC;
   public int A;

   public aiu(int var1, int var2) {
      if (var1 <= 0) {
         throw new RuntimeException();
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.A;
      return 31 * var1 + this.pC;
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
         aiu var2 = (aiu)var1;
         return this.A != var2.A ? false : this.pC == var2.pC;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d->%d", this.pC, this.A);
   }
}
