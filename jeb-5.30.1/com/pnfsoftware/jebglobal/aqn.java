package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class aqn {
   aqf q;
   int RF;
   aqn xK;

   public aqn(aqf var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   public aqn(aqf var1, int var2, aqn var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      return 31 * var1 + this.RF;
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
         aqn var2 = (aqn)var1;
         if (this.q == null) {
            if (var2.q != null) {
               return false;
            }
         } else if (!this.q.equals(var2.q)) {
            return false;
         }

         return this.RF == var2.RF;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d-%d", this.q.Uv, this.RF);
   }
}
