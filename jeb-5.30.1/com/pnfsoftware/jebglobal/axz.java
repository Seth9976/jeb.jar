package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axz extends axi implements axy {
   @SerId(1)
   private long q;

   public axz(long var1, long var3) {
      super(0, null);
      this.Uv(3);
      this.q(var1);
      this.q = var3;
   }

   @Override
   public long getMemoryAddress() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoAddress@%X", this.getMemoryAddress());
   }
}
