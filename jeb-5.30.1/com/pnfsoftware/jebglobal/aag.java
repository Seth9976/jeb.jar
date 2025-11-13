package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.util.collect.ISegmentFactory;

class aag implements ISegmentFactory {
   aag(aaf var1) {
      this.q = var1;
   }

   public INativeContinuousItem q(Long var1, Long var2) {
      return new axs(var1, var2 - var1);
   }
}
