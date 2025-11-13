package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class IntegerSegment implements ISegment, Comparable {
   @SerId(1)
   int address;
   @SerId(2)
   int size;

   public IntegerSegment(int var1, int var2) {
      this.address = var1;
      this.size = var2;
   }

   public int compareTo(IntegerSegment var1) {
      return Integer.compare(this.address, var1.address);
   }

   public Integer getBegin() {
      return this.address;
   }

   public Integer getEnd() {
      return this.address + this.size;
   }

   public int getSize() {
      return this.size;
   }

   @Override
   public String toString() {
      return Strings.ff("%d-%d", this.getBegin(), this.getEnd());
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.address;
      return 31 * var1 + this.size;
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
         IntegerSegment var2 = (IntegerSegment)var1;
         return this.address != var2.address ? false : this.size == var2.size;
      }
   }
}
