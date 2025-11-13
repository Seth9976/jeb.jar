package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class K implements rQ {
   @SerId(1)
   private final long pC;
   @SerId(2)
   private final DH A;

   public K(long var1, DH var3) {
      this.pC = var1;
      this.A = var3;
   }

   @Override
   public DH pC() {
      return this.A;
   }

   public Long A() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("ConstantFeature [value=%x, conf=%s]", this.pC, this.A);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
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
         K var2 = (K)var1;
         return this.pC == var2.pC;
      }
   }
}
