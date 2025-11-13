package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class SL {
   public long pC;
   public Jx A;

   public SL(long var1, Jx var3) {
      this.pC = var1;
      this.A = var3;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         SL var2 = (SL)var1;
         if (this.pC != var2.pC) {
            return false;
         } else {
            if (this.A == null) {
               if (var2.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var2.A)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,loc=[%s]", this.pC, this.A);
   }

   public SL.Av pC() {
      SL.Av var1 = new SL.Av();
      var1.pC = this.pC;
      var1.A = this.A.kS;
      var1.kS = this.A.wS;
      return var1;
   }

   public static class Av {
      long pC;
      long A;
      long kS;

      private Av() {
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.A ^ this.A >>> 32);
         var1 = 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
         return 31 * var1 + (int)(this.kS ^ this.kS >>> 32);
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
            SL.Av var2 = (SL.Av)var1;
            if (this.A != var2.A) {
               return false;
            } else {
               return this.pC != var2.pC ? false : this.kS == var2.kS;
            }
         }
      }
   }
}
