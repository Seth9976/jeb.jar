package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class rG {
   public byte pC;
   public long A;

   public rG(byte var1, long var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
      return 31 * var1 + (int)(this.A ^ this.A >>> 32);
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
         rG var2 = (rG)var1;
         return this.pC != var2.pC ? false : this.A == var2.A;
      }
   }

   public boolean pC() {
      return un.A(this.pC);
   }

   @Override
   public String toString() {
      return Strings.ff("tag=%c,value=%d(%Xh)", (char)this.pC, this.A, this.A);
   }
}
