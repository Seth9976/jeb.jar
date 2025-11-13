package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class nI implements tw {
   @SerId(1)
   private final long q;
   @SerId(2)
   private final iZ RF;

   public nI(long var1, iZ var3) {
      this.q = var1;
      this.RF = var3;
   }

   @Override
   public iZ q() {
      return this.RF;
   }

   public Long RF() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("ConstantFeature [value=%x, conf=%s]", this.q, this.RF);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.q ^ this.q >>> 32);
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
         nI var2 = (nI)var1;
         return this.q == var2.q;
      }
   }
}
