package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;

public class akt implements aky {
   public int q;
   public int RF;

   public akt(int var1, int var2) {
      if (var1 <= 0) {
         throw new RuntimeException();
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   @Override
   public int q() {
      return this.q;
   }

   @Override
   public int RF() {
      return this.RF;
   }

   @Override
   public void q(Map var1) {
      this.q = (Integer)var1.get(this.q);
      if (this.RF > 0) {
         this.RF = (Integer)var1.get(this.RF);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.RF;
      return 31 * var1 + this.q;
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
         akt var2 = (akt)var1;
         return this.RF != var2.RF ? false : this.q == var2.q;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%d->%d", this.q, this.RF);
   }
}
