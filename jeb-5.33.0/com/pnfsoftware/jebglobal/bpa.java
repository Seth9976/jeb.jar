package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class bpa implements bow {
   public bpb pC;
   public int A;
   public int kS;
   public int wS = -1;

   public bpa(bpb var1, int var2, int var3, int var4) {
      if (var1 != null && var2 >= 0 && var3 >= 0) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      } else {
         throw new RuntimeException();
      }
   }

   public bpb A() {
      return this.pC;
   }

   public int kS() {
      return this.A;
   }

   @Override
   public int pC() {
      return this.kS;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + this.A;
      var1 = 31 * var1 + this.kS;
      return 31 * var1 + this.wS;
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
         bpa var2 = (bpa)var1;
         if (this.pC != var2.pC) {
            return false;
         } else if (this.A != var2.A) {
            return false;
         } else {
            return this.kS != var2.kS ? false : this.wS == var2.wS;
         }
      }
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%s:%d->%d", this.pC, this.A, this.kS);
      if (this.wS >= 0) {
         var1 = var1 + "=>" + this.wS;
      }

      return var1;
   }
}
