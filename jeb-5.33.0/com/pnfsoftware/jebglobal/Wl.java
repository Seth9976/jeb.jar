package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Wl extends Yg {
   @SerId(1)
   private double A;
   @SerId(2)
   private String kS;

   public Wl(int var1, long var2, double var4, String var6) {
      super(4097, var1, var2, 65536);
      this.A = var4;
      this.kS = var6;
   }

   public String pC(IInstruction var1, long var2) {
      return this.A == 0.0 ? "0.0" : Strings.f(this.kS, this.A).toUpperCase();
   }

   public double pC() {
      return this.A;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      long var2 = Double.doubleToLongBits(this.A);
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
         Wl var2 = (Wl)var1;
         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         return Double.doubleToLongBits(this.A) == Double.doubleToLongBits(var2.A);
      }
   }
}
