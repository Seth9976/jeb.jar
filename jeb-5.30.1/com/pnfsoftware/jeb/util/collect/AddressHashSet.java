package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class AddressHashSet extends ConcurrentHashSet {
   @SerId(1)
   private long invmask;

   @SerConstructor
   private AddressHashSet() {
      this(1);
   }

   public AddressHashSet(int var1) {
      if (var1 > 0 && var1 <= 64) {
         this.invmask = MathUtil.makeInverseMask(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public boolean add(Long var1) {
      if ((var1 & this.invmask) != 0L) {
         throw new IllegalArgumentException(Strings.ff("Illegal address: 0x%X", var1));
      } else {
         return super.add(var1);
      }
   }
}
