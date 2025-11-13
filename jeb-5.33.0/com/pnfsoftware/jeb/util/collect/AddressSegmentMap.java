package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class AddressSegmentMap extends SegmentMap {
   private static final long serialVersionUID = 1L;
   @SerId(1)
   private long invmask;
   @SerId(2)
   private long overflow;

   @SerConstructor
   private AddressSegmentMap() {
      this(1);
   }

   public AddressSegmentMap(int var1) {
      super(new AddressSegmentMap$1());
      if (var1 > 0 && var1 <= 64) {
         this.invmask = MathUtil.makeInverseMask(var1);
         this.overflow = MathUtil.makeOverflow(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public final boolean isValidKey(Long var1) {
      long var2 = var1;
      return (var2 & this.invmask) == 0L || var2 == this.overflow;
   }
}
