package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ban extends bae {
   @SerId(1)
   private final byte q;
   @SerId(2)
   private final int RF;

   public ban(byte var1, int var2) {
      this.q = var1;
      this.RF = var2;
   }

   public int q() {
      return this.RF;
   }

   public String RF() {
      return Strings.ff("%02x:%08x", this.q, this.RF);
   }

   @Override
   public String getType() {
      return "Routine1BConstant";
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.q;
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
         ban var2 = (ban)var1;
         return this.q != var2.q ? false : this.RF == var2.RF;
      }
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof bag ? var1.match(this) : this.equals(var1);
   }

   @Override
   public INativeFeature deepCopy() {
      return new ban(this.q, this.RF);
   }
}
