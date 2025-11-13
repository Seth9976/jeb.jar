package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class aB {
   private dN q;
   private int RF;

   public aB(dN var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("eventKind cannot be null");
      } else {
         this.q = var1;
         this.RF = var2;
      }
   }

   public dN q() {
      return this.q;
   }

   public int RF() {
      return this.RF;
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
         aB var2 = (aB)var1;
         return this.q != var2.q ? false : this.RF == var2.RF;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("kind=%s,rid=%Xh", this.q, this.RF);
   }
}
