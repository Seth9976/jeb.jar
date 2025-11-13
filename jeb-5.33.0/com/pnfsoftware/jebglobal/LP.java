package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class LP {
   private lz pC;
   private int A;

   public LP(lz var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("eventKind cannot be null");
      } else {
         this.pC = var1;
         this.A = var2;
      }
   }

   public lz pC() {
      return this.pC;
   }

   public int A() {
      return this.A;
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
         LP var2 = (LP)var1;
         return this.pC != var2.pC ? false : this.A == var2.A;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("kind=%s,rid=%Xh", this.pC, this.A);
   }
}
