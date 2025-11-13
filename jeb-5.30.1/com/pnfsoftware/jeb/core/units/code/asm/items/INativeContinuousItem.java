package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.util.collect.ISegment;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface INativeContinuousItem extends INativeMemoryItem, ISegment {
   long getMemorySize();

   default long getMemoryAddressEnd() {
      return this.getMemoryAddress() + this.getMemorySize();
   }
}
