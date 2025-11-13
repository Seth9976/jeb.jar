package com.pnfsoftware.jeb.core.output.code.coordinates;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class NativeCoordinates implements ICodeCoordinates {
   @SerId(1)
   private long address;

   public NativeCoordinates(long var1) {
      this.address = var1;
   }

   public long getAddress() {
      return this.address;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (int)(this.address ^ this.address >>> 32);
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
         NativeCoordinates var2 = (NativeCoordinates)var1;
         return this.address == var2.address;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%Xh", this.address);
   }
}
