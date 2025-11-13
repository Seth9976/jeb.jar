package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class PointerLocation {
   @SerId(1)
   private long loc;
   @SerId(2)
   private Pointer ptr;

   public PointerLocation(long var1, Pointer var3) {
      this.loc = var1;
      this.ptr = var3;
   }

   public long getLocation() {
      return this.loc;
   }

   public Pointer getPointer() {
      return this.ptr;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.loc ^ this.loc >>> 32);
      return 31 * var1 + (this.ptr == null ? 0 : this.ptr.hashCode());
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
         PointerLocation var2 = (PointerLocation)var1;
         if (this.loc != var2.loc) {
            return false;
         } else {
            if (this.ptr == null) {
               if (var2.ptr != null) {
                  return false;
               }
            } else if (!this.ptr.equals(var2.ptr)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%X:%s", this.loc, this.ptr);
   }
}
