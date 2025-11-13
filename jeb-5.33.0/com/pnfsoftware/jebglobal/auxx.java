package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class auxx extends aum {
   public auxx(long var1, long var3) {
      super(var1, var3);
      this.UT(3);
   }

   public ayx UO() {
      return null;
   }

   @Override
   public String toString() {
      return Strings.ff("GapItem@%X(->%X)", this.getMemoryAddress(), this.getMemoryAddress() + this.getMemorySize());
   }
}
