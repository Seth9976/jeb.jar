package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSValue;

public class bkn implements IJLSValue {
   char q;
   Object RF;

   bkn(char var1, Object var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public char getTypeTag() {
      return this.q;
   }

   @Override
   public Object getValue() {
      return this.RF;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.q;
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         bkn var2 = (bkn)var1;
         if (this.q != var2.q) {
            return false;
         } else {
            if (this.RF == null) {
               if (var2.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var2.RF)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return this.q + ":" + this.RF;
   }
}
