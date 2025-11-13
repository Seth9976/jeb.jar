package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.ISegmentFactory;
import com.pnfsoftware.jeb.util.collect.IntegerSegment;

class bep implements ISegmentFactory {
   bep(beo var1) {
      this.pC = var1;
   }

   public IntegerSegment pC(Integer var1, Integer var2) {
      return new IntegerSegment(var1, var2 - var1);
   }
}
