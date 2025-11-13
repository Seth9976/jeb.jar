package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axp extends axh {
   @SerId(1)
   private final byte pC;
   @SerId(2)
   private final int A;

   public axp(byte var1, int var2) {
      this.pC = var1;
      this.A = var2;
   }

   public int pC() {
      return this.A;
   }

   public String A() {
      return Strings.ff("%02x:%08x", this.pC, this.A);
   }

   @Override
   public String getType() {
      return "Routine1BConstant";
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
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
         axp var2 = (axp)var1;
         return this.pC != var2.pC ? false : this.A == var2.A;
      }
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof axj ? var1.match(this) : this.equals(var1);
   }

   @Override
   public INativeFeature deepCopy() {
      return new axp(this.pC, this.A);
   }
}
