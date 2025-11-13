package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeFeature;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axr extends axh {
   @SerId(1)
   private final int pC;

   public axr(int var1) {
      this.pC = var1;
   }

   public Integer pC() {
      return this.pC;
   }

   @Override
   public String getType() {
      return "RoutineSize";
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.pC;
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
         axr var2 = (axr)var1;
         return this.pC == var2.pC;
      }
   }

   @Override
   public boolean match(INativeFeature var1) {
      return var1 instanceof axj ? var1.match(this) : this.equals(var1);
   }

   @Override
   public INativeFeature deepCopy() {
      return new axr(this.pC);
   }
}
