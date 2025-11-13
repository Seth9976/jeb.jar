package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bap extends bae {
   @SerId(1)
   private final int q;

   public bap(int var1) {
      this.q = var1;
   }

   public Integer q() {
      return this.q;
   }

   @Override
   public String getType() {
      return "RoutineSize";
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
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
         bap var2 = (bap)var1;
         return this.q == var2.q;
      }
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof bag ? var1.match(this) : this.equals(var1);
   }

   @Override
   public INativeFeature deepCopy() {
      return new bap(this.q);
   }
}
