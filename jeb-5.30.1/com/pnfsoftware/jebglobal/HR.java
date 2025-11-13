package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class HR extends CW {
   @SerId(1)
   private double q;
   @SerId(2)
   private String gP;

   public HR(int var1, long var2, double var4, String var6) {
      super(4097, var1, var2, 65536);
      this.q = var4;
      this.gP = var6;
   }

   public String q(IInstruction var1, long var2) {
      return this.q == 0.0 ? "0.0" : Strings.f(this.gP, this.q).toUpperCase();
   }

   public double q() {
      return this.q;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
      long var2 = Double.doubleToLongBits(this.q);
      return 31 * var1 + (int)(var2 ^ var2 >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         HR var2 = (HR)var1;
         if (this.gP == null) {
            if (var2.gP != null) {
               return false;
            }
         } else if (!this.gP.equals(var2.gP)) {
            return false;
         }

         return Double.doubleToLongBits(this.q) == Double.doubleToLongBits(var2.q);
      }
   }
}
