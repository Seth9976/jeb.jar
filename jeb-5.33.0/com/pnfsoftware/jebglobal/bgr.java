package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSValue;

public class bgr implements IJLSValue {
   char pC;
   Object A;

   bgr(char var1, Object var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public char getTypeTag() {
      return this.pC;
   }

   @Override
   public Object getValue() {
      return this.A;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.pC;
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
         bgr var2 = (bgr)var1;
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
      return this.pC + ":" + this.A;
   }
}
