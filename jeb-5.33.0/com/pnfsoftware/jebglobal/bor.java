package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bor implements bow {
   public bos pC;
   public int A;
   public int kS = -1;
   public boolean wS;

   public bor(bos var1, int var2, int var3) {
      if (var1 != null && var2 >= 0) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      } else {
         throw new RuntimeException();
      }
   }

   public bos A() {
      return this.pC;
   }

   public int kS() {
      return this.A;
   }

   @Override
   public int pC() {
      return -1;
   }

   public int wS() {
      return this.kS;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + this.A;
      return 31 * var1 + this.kS;
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
         bor var2 = (bor)var1;
         if (this.pC != var2.pC) {
            return false;
         } else {
            return this.A != var2.A ? false : this.kS == var2.kS;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d=>%d", this.pC, this.A, this.kS);
   }
}
