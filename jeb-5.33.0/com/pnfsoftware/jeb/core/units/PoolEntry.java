package com.pnfsoftware.jeb.core.units;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public abstract class PoolEntry {
   @SerId(1)
   private Integer index;

   public void setIndex(int var1) {
      if (this.index != null) {
         throw new IllegalStateException("Index already set");
      } else if (var1 < 0) {
         throw new IllegalArgumentException("Illegal index value");
      } else {
         this.index = var1;
      }
   }

   public int getIndex() {
      return this.index == null ? -1 : this.index;
   }

   @Override
   public String toString() {
      return Strings.ff("%s - entry %d", this.getClass().getSimpleName(), this.getIndex());
   }
}
