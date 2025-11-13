package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.format.Strings;

public class UnitAddress {
   private IAddressableUnit unit;
   private String address;

   public UnitAddress(IAddressableUnit var1, String var2) {
      this.unit = var1;
      this.address = var2;
   }

   public IAddressableUnit getUnit() {
      return this.unit;
   }

   public String getAddress() {
      return this.address;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.address == null ? 0 : this.address.hashCode());
      return 31 * var1 + (this.unit == null ? 0 : this.unit.hashCode());
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
         UnitAddress var2 = (UnitAddress)var1;
         if (this.address == null) {
            if (var2.address != null) {
               return false;
            }
         } else if (!this.address.equals(var2.address)) {
            return false;
         }

         if (this.unit == null) {
            if (var2.unit != null) {
               return false;
            }
         } else if (!this.unit.equals(var2.unit)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("u={%s},a={%s}", this.getUnit(), this.getAddress());
   }
}
