package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class avn extends auo {
   @SerId(1)
   private long pC;

   public avn(long var1, long var3) {
      super(0, null);
      this.UT(3);
      this.pC(var1);
      this.pC = var3;
   }

   public long kS() {
      return this.pC;
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoRegister(regCode:%d)", this.pC);
   }
}
