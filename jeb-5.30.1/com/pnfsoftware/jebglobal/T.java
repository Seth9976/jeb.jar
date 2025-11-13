package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.LongSegment;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class T extends LongSegment {
   @SerId(1)
   private long q;

   public T(long var1, long var3, long var5) {
      super(var1, var3);
      this.q = var5;
   }

   public long q() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("%d-%d (align:%d)", this.getBegin(), this.getEnd(), this.q());
   }
}
