package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class LongSegment implements ISegment, Comparable {
   @SerId(1)
   long address;
   @SerId(2)
   long size;

   public LongSegment(long var1, long var3) {
      this.address = var1;
      this.size = var3;
   }

   public int compareTo(LongSegment var1) {
      return Long.compare(this.address, var1.address);
   }

   public Long getBegin() {
      return this.address;
   }

   public Long getEnd() {
      return this.address + this.size;
   }

   public long getSize() {
      return this.size;
   }

   public static LongSegment merge(Couple var0, List var1) {
      return new LongSegment((Long)var0.getFirst(), (Long)var0.getSecond() - (Long)var0.getFirst());
   }

   @Override
   public String toString() {
      return Strings.ff("%d-%d", this.getBegin(), this.getEnd());
   }
}
