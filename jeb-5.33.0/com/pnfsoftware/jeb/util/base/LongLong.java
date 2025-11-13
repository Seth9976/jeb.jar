package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class LongLong implements Comparable {
   @SerId(1)
   public long lo;
   @SerId(2)
   public long hi;

   public static LongLong valueOf(long var0, long var2) {
      return new LongLong(var0, var2);
   }

   private LongLong(long var1, long var3) {
      this.lo = var1;
      this.hi = var3;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.hi ^ this.hi >>> 32);
      return 31 * var1 + (int)(this.lo ^ this.lo >>> 32);
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
         LongLong var2 = (LongLong)var1;
         return this.hi != var2.hi ? false : this.lo == var2.lo;
      }
   }

   public int compareTo(LongLong var1) {
      if (this.hi > var1.hi) {
         return 1;
      } else if (this.hi < var1.hi) {
         return -1;
      } else if (this.lo > var1.lo) {
         return 1;
      } else {
         return this.lo < var1.lo ? -1 : 0;
      }
   }

   public int compareUnsignedTo(LongLong var1) {
      int var2 = Long.compareUnsigned(this.hi, var1.hi);
      if (var2 > 0) {
         return 1;
      } else if (var2 < 0) {
         return -1;
      } else {
         var2 = Long.compareUnsigned(this.lo, var1.lo);
         if (var2 > 0) {
            return 1;
         } else {
            return var2 < 0 ? -1 : 0;
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("0x%016X%016X", this.hi, this.lo);
   }
}
