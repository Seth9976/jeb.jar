package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayj extends axj implements axy {
   @SerId(1)
   private long q;

   public ayj(long var1, long var3) {
      super(0, null);
      this.Uv(3);
      this.q(var1);
      this.q = var3;
   }

   public long xK() {
      return this.q;
   }

   @Override
   public String toString() {
      return Strings.ff("PseudoRegister(regCode:%d)", this.q);
   }
}
