package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class dt extends LongSegment {
   @SerId(1)
   private long pC;

   public dt(long var1, long var3, long var5) {
      super(var1, var3);
      this.pC = var5;
   }

   public long pC() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("%d-%d (align:%d)", this.getBegin(), this.getEnd(), this.pC());
   }
}
