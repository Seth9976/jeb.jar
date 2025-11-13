package com.pnfsoftware.jeb.core.output.code.coordinates;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class PackageCoordinates implements ICodeCoordinates {
   @SerId(1)
   private int pkgId;

   public PackageCoordinates(int var1) {
      this.pkgId = var1;
   }

   public int getPackageId() {
      return this.pkgId;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.pkgId;
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
         PackageCoordinates var2 = (PackageCoordinates)var1;
         return this.pkgId == var2.pkgId;
      }
   }

   @Override
   public String toString() {
      return "package:" + this.pkgId;
   }
}
