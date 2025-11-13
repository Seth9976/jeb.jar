package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.util.collect.ISegmentFactory;

class NP implements ISegmentFactory {
   NP(Tw var1) {
      this.pC = var1;
   }

   public INativeContinuousItem pC(Long var1, Long var2) {
      return new auxx(var1, var2 - var1);
   }
}
